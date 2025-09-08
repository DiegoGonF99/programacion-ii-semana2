public class Dueno {
        private long id;
        private String nombreCompleto;
        private String telefono;
        private String email;
        private String direccion;

        // Constructor completo
        public Dueno(long id, String nombre, String telefono, String email, String direccion) {
            this.id = id;
            this.nombreCompleto = nombre;
            this.telefono = telefono;
            this.email = email;
            this.direccion = direccion;
        }

        // Constructor simplificado
        public Dueno(long id, String nombre, String telefono) {
            this(id, nombre, telefono, "email@desconocido.com", "sin direccion");
        }

        public String resumen() {
            return "Dueño " + id + ": " + nombreCompleto + " - Tel: " + telefono;
        }
    }

