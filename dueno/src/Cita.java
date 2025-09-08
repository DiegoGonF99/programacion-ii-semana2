import java.time.LocalDateTime;

public class Cita {
        private LocalDateTime fechaHora;
        private EstadoCita estado;
        private Dueno dueno;

        public Cita(LocalDateTime fechaHora, Dueno dueno) {
            this.fechaHora = fechaHora;
            this.dueno = dueno;
            this.estado = EstadoCita.AGENDADA;
        }

        public void verificarEstado() {
            if (fechaHora.getDayOfMonth() % 3 == 0) {
                estado = EstadoCita.CANCELADA;
            } else if (fechaHora.getHour() >= 7 && fechaHora.getHour() <= 10) {
                fechaHora = fechaHora.plusDays(1);
            } else {
                estado = EstadoCita.MARCADA;
            }
        }

        public String resumen() {
            return fechaHora + " - " + dueno.resumen() + " - Estado: " + estado;
        }
    }

