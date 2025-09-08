public class Paciente {
        private long id;
        private String nombre;
        private String especie;
        private String raza;
        private int edadMeses;
        private double pesoKg;
        private boolean esCachorro;

        // Constructor completo
        public Paciente(long id, String nombre, String especie, String raza, int edadMeses, double pesoKg) {
            this.id = id;
            this.nombre = nombre;
            this.especie = especie;
            this.raza = raza;
            this.edadMeses = edadMeses;
            this.pesoKg = pesoKg;
            this.esCachorro = edadMeses < 12;
        }

        // Constructor simplificado
        public Paciente(long id, String nombre, String especie) {
            this(id, nombre, especie, "mestizo", 8, 1.0);
        }

        public boolean esCachorro() {
            return esCachorro;
        }

        public int getEdadMeses() {
            return edadMeses;
        }

        public String getEspecie() {
            return especie;
        }

        public double getPesoKg() {
            return pesoKg;
        }

        public String resumen() {
            return "Mascota " + id + ": " + nombre + " (" + especie + ", " + raza + ") - Edad: " + edadMeses + " meses - Peso: " + pesoKg + "kg";
        }
    }

