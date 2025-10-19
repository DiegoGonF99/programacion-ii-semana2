import java.time.*;
import java.util.*;

// ======= ENTIDADES =======
class Cliente {
    private String nombre;
    public Cliente(String nombre){ this.nombre = nombre; }
    public String getNombre(){ return nombre; }
}

class Mascota {
    enum Tipo { PERRO, GATO, OTRO }
    private String nombre;
    private Tipo tipo;
    public Mascota(String nombre, Tipo tipo){
        this.nombre = nombre; this.tipo = tipo;
    }
    public String getNombre(){ return nombre; }
    public Tipo getTipo(){ return tipo; }
}

class Medico {
    private String nombre;
    private int duracionMinima;
    public Medico(String nombre, int duracionMinima){
        this.nombre = nombre; this.duracionMinima = duracionMinima;
    }
    public String getNombre(){ return nombre; }
    public int getDuracionMinima(){ return duracionMinima; }
}

// ======= POLÍTICAS DE COBRO =======
interface PoliticaCobro {
    double calcular(double minutos, int complejidad, LocalDateTime fechaHora);
}

class TarifaFija implements PoliticaCobro {
    private double monto;
    public TarifaFija(double monto){ this.monto = monto; }
    public double calcular(double minutos, int complejidad, LocalDateTime fh){ return monto; }
}

class CobroPorTiempo implements PoliticaCobro {
    private double porMinuto;
    public CobroPorTiempo(double porMinuto){ this.porMinuto = porMinuto; }
    public double calcular(double minutos, int complejidad, LocalDateTime fh){
        return minutos * porMinuto;
    }
}

class CobroPorComplejidad implements PoliticaCobro {
    private double base;
    public CobroPorComplejidad(double base){ this.base = base; }
    public double calcular(double minutos, int complejidad, LocalDateTime fh){
        return base * complejidad;
    }
}

// ======= ATENCIÓN =======
abstract class Atencion {
    private String nombre;
    private int complejidad;
    private PoliticaCobro politica;
    private String razon; // NUEVO

    public Atencion(String nombre, int complejidad, PoliticaCobro politica){
        this.nombre = nombre;
        this.complejidad = complejidad;
        this.politica = politica;
    }

    // NUEVO: guardar y mostrar razón
    public void setRazon(String razon){
        this.razon = razon;
    }

    public String getRazon(){ return razon; }

    // Template
    public double ejecutarYCobrar(double minutos, LocalDateTime fh){
        registrar();
        realizar();
        return politica.calcular(minutos, complejidad, fh);
    }

    protected void registrar(){
        System.out.println("Registrando atención: " + nombre);
        if (razon != null && !razon.isBlank())
            System.out.println("Razón de la consulta: " + razon);
    }

    protected abstract void realizar();

    public String getNombre(){ return nombre; }
    public int getComplejidad(){ return complejidad; }
}

class ConsultaGeneral extends Atencion {
    public ConsultaGeneral(int complejidad, PoliticaCobro p){
        super("Consulta general", complejidad, p);
    }
    protected void realizar(){
        System.out.println("Realizando procedimiento de revisión general...");
    }
}

// ======= ESTADOS =======
enum EstadoCita { PROGRAMADA, CONFIRMADA, EN_CURSO, FINALIZADA, CANCELADA }

// ======= CITA =======
class Cita {
    private Cliente cliente;
    private Mascota mascota;
    private Medico medico;
    private Atencion atencion;
    private LocalDateTime inicio;
    private int duracionMin;
    private EstadoCita estado;
    private String razon;

    public Cita(Cliente c, Mascota m, Medico med, Atencion a,
                LocalDateTime inicio, int duracionMin, String razon){
        this.cliente = c; this.mascota = m; this.medico = med;
        this.atencion = a; this.inicio = inicio; this.duracionMin = duracionMin;
        this.estado = EstadoCita.PROGRAMADA;
        this.razon = razon;
        this.atencion.setRazon(razon); // NUEVO: vincula la razón a la atención
    }

    public EstadoCita getEstado(){ return estado; }
    public int getDuracionMin(){ return duracionMin; }
    public LocalDateTime getInicio(){ return inicio; }
    public Atencion getAtencion(){ return atencion; }
    public String getRazon(){ return razon; }

    public void confirmar(){
        if(estado == EstadoCita.PROGRAMADA) estado = EstadoCita.CONFIRMADA;
    }
    public void iniciar(){
        if(estado == EstadoCita.CONFIRMADA || estado == EstadoCita.PROGRAMADA)
            estado = EstadoCita.EN_CURSO;
    }
    public void finalizar(){
        if(estado == EstadoCita.EN_CURSO) estado = EstadoCita.FINALIZADA;
    }
    public void cancelar(){
        if(estado != EstadoCita.FINALIZADA) estado = EstadoCita.CANCELADA;
    }
}

// ======= REGLAS DE AGENDAMIENTO =======
interface ReglaAgendamiento { void validar(Medico med, LocalDateTime ini, int minutos) throws Exception; }

class ReglaDuracionMinima implements ReglaAgendamiento {
    public void validar(Medico med, LocalDateTime ini, int minutos) throws Exception{
        if(minutos < med.getDuracionMinima())
            throw new Exception("Duración menor a la mínima del médico.");
    }
}

class ReglaHorarioLaboral implements ReglaAgendamiento {
    public void validar(Medico med, LocalDateTime ini, int minutos) throws Exception{
        LocalTime abre = LocalTime.of(8,0);
        LocalTime cierra = LocalTime.of(20,0);
        LocalTime fin = ini.toLocalTime().plusMinutes(minutos);
        if(ini.toLocalTime().isBefore(abre) || fin.isAfter(cierra))
            throw new Exception("Fuera del horario laboral.");
    }
}

class ServicioAgenda {
    private List<ReglaAgendamiento> reglas = new ArrayList<>();
    public void agregarRegla(ReglaAgendamiento r){ reglas.add(r); }

    public Cita agendar(Cliente c, Mascota m, Medico med, Atencion a,
                        LocalDateTime ini, int minutos, String razon) throws Exception {
        for(ReglaAgendamiento r : reglas) r.validar(med, ini, minutos);
        return new Cita(c, m, med, a, ini, minutos, razon);
    }
}

// ======= DEMO =======
public class Clinica {
    public static void main(String[] args) throws Exception {
        Cliente cli = new Cliente("Diego");
        Mascota pet = new Mascota("Luna", Mascota.Tipo.PERRO);
        Medico  doc = new Medico("Dra. Pérez", 20);

        PoliticaCobro p1 = new TarifaFija(150);
        PoliticaCobro p2 = new CobroPorTiempo(2.5);
        PoliticaCobro p3 = new CobroPorComplejidad(80);

        Atencion consulta = new ConsultaGeneral(2, p2);

        ServicioAgenda agenda = new ServicioAgenda();
        agenda.agregarRegla(new ReglaDuracionMinima());
        agenda.agregarRegla(new ReglaHorarioLaboral());

        LocalDateTime inicio = LocalDateTime.of(LocalDate.now(), LocalTime.of(17,30));
        int duracion = 30;
        String razon = "Vacunación anual y revisión de piel";

        Cita cita = agenda.agendar(cli, pet, doc, consulta, inicio, duracion, razon);

        System.out.println("\n=== Detalle de la Cita ===");
        System.out.println("Cliente: " + cli.getNombre());
        System.out.println("Mascota: " + pet.getNombre());
        System.out.println("Médico: " + doc.getNombre());
        System.out.println("Razón de la consulta: " + cita.getRazon());
        System.out.println("Estado inicial: " + cita.getEstado());
        System.out.println("===========================\n");

        cita.confirmar();
        System.out.println("Tras confirmar: " + cita.getEstado());

        cita.iniciar();
        System.out.println("Tras iniciar: " + cita.getEstado());

        double total = consulta.ejecutarYCobrar(cita.getDuracionMin(), cita.getInicio());
        cita.finalizar();
        System.out.println("Estado final: " + cita.getEstado());
        System.out.println("Total a cobrar: Q" + total);

        Atencion consultaFija = new ConsultaGeneral(2, p1);
        Atencion consultaComp = new ConsultaGeneral(3, p3);
        System.out.println("\nCobro Tarifa Fija: Q" + consultaFija.ejecutarYCobrar(20, inicio));
        System.out.println("Cobro Por Complejidad: Q" + consultaComp.ejecutarYCobrar(20, inicio));
    }
}
