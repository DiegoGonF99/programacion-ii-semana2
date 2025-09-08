import java.util.Random;
import java.time.LocalDateTime;

    public static void main(String[] args) {


        Dueno[] duenos = new Dueno[500];
        Paciente[] pacientes = new Paciente[1000];
        Cita[] citas = new Cita[40];
        Random rand = new Random();

        // Generación de dueños
        for (int i = 0; i < duenos.length; i++) {
            String telefono = "502" + (rand.nextInt(9000000) + 1000000);
            duenos[i] = new Dueno(i + 1, "Dueño" + (i + 1), telefono);
        }

        // Generación de mascotas (2 por dueño)
        for (int i = 0; i < pacientes.length; i++) {
            String especie = (i % 2 == 0) ? "perro" : "gato";
            int edadMeses = (i % 10) + 1;
            double peso = 1.0 + (i % 4) * 0.3;
            pacientes[i] = new Paciente(i + 1, "Mascota" + (i + 1), especie, "mestizo", edadMeses, peso);
        }

        // Verificación: imprimir 3 dueños y 6 mascotas
        System.out.println("\n--- Primeros 3 Dueños ---");
        for (int i = 0; i < 3; i++) {
            System.out.println(duenos[i].resumen());
        }

        System.out.println("\n--- Primeras 6 Mascotas ---");
        for (int i = 0; i < 6; i++) {
            System.out.println(pacientes[i].resumen());
        }

        // Parte C - Reportes
        int totalCachorros = 0, totalPerros = 0, totalGatos = 0;
        Paciente[] top5Longevos = new Paciente[5];

        for (int i = 0; i < pacientes.length; i++) {
            if (pacientes[i].esCachorro()) totalCachorros++;
            if (pacientes[i].getEspecie().equals("perro")) totalPerros++;
            else if (pacientes[i].getEspecie().equals("gato")) totalGatos++;

            // Actualizar top 5 más longevos
            for (int j = 0; j < 5; j++) {
                if (top5Longevos[j] == null || pacientes[i].getEdadMeses() > top5Longevos[j].getEdadMeses()) {
                    // Shift hacia abajo
                    for (int k = 4; k > j; k--) {
                        top5Longevos[k] = top5Longevos[k - 1];
                    }
                    top5Longevos[j] = pacientes[i];
                    break;
                }
            }
        }

        System.out.println("\n--- Reportes ---");
        System.out.println("Cachorros: " + totalCachorros);
        System.out.println("Perros: " + totalPerros);
        System.out.println("Gatos: " + totalGatos);

        System.out.println("\nTop 5 mascotas más longevas:");
        for (int i = 0; i < 5; i++) {
            if (top5Longevos[i] != null)
                System.out.println(top5Longevos[i].resumen());
        }

        // Parte D - Citas
        System.out.println("\n--- Citas ---");
        for (int i = 0; i < 40; i++) {
            Dueno d = duenos[i / 2];
            LocalDateTime fecha = LocalDateTime.now().plusDays(i / 2).withHour(10).withMinute(0);
            Cita cita = new Cita(fecha, d);
            cita.verificarEstado();
            citas[i] = cita;
            System.out.println(cita.resumen());
        }
    }
