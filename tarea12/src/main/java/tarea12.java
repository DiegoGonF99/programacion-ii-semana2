package tarea12;
import java.sql.*;
import java.util.Scanner;

public class tarea12 {

    public static void main(String[] args) {
        // Configuración de conexión a la base de datos
        String url = "jdbc:mariadb://localhost:3306/demo_db";
        String user = "root";
        String pass = "Diego516@@";

        Scanner scanner = new Scanner(System.in);

        try (Connection conn = DriverManager.getConnection(url, user, pass)) {
            System.out.println("✓ Conexión establecida!\n");

            // 1. INSERTAR: Al menos 3 estudiantes
            System.out.println("=== 1. INSERTANDO ESTUDIANTES ===");
            insertarEstudiante(conn, "Juan González", 20);
            insertarEstudiante(conn, "María Rosales", 22);
            insertarEstudiante(conn, "Carlos Lopez", 21);

            // 2. ASIGNAR: Mínimo 2 cursos por estudiante
            System.out.println("\n=== 2. ASIGNANDO CURSOS ===");
            asignarCurso(conn, 1, "Matemáticas", "Lunes 8:00-10:00");
            asignarCurso(conn, 1, "Programación", "Martes 10:00-12:00");

            asignarCurso(conn, 2, "Física", "Lunes 10:00-12:00");
            asignarCurso(conn, 2, "Química", "Jueves 8:00-10:00");

            asignarCurso(conn, 3, "Historia", "Viernes 14:00-16:00");
            asignarCurso(conn, 3, "Literatura", "Martes 8:00-10:00");

            // 3. LISTAR: Mostrar con SELECT y JOIN
            System.out.println("\n=== 3. LISTADO ===");
            listarConJoin(conn);

            // 4. ACTUALIZAR: Cambiar horario de un curso
            System.out.println("\n=== 4. ACTUALIZAR HORARIO ===");
            System.out.print("ID del curso a actualizar: ");
            int idCurso = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Nuevo horario: ");
            String nuevoHorario = scanner.nextLine();
            actualizarHorario(conn, idCurso, nuevoHorario);

            listarConJoin(conn); // Mostrar cambios

            // 5. ELIMINAR: Borrar estudiante y sus cursos (CASCADE)
            System.out.println("\n=== 5. ELIMINAR ESTUDIANTE ===");
            System.out.print("ID del estudiante a eliminar: ");
            int idEstudiante = scanner.nextInt();
            eliminarEstudiante(conn, idEstudiante);

            listarConJoin(conn); // Mostrar resultado final

        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    // Método para insertar un estudiante
    private static void insertarEstudiante(Connection conn, String nombre, int edad) {
        try (PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO estudiante(nombre, edad) VALUES (?, ?)")) {
            ps.setString(1, nombre);
            ps.setInt(2, edad);
            ps.executeUpdate();
            System.out.println("  ✓ " + nombre + " insertado");
        } catch (SQLException e) {
            System.err.println("  ✗ Error: " + e.getMessage());
        }
    }

    // Método para asignar un curso a un estudiante
    private static void asignarCurso(Connection conn, int estudianteId, String curso, String horario) {
        try (PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO cursos_asignados(estudiante_id, nombre, horario) VALUES (?, ?, ?)")) {
            ps.setInt(1, estudianteId);
            ps.setString(2, curso);
            ps.setString(3, horario);
            ps.executeUpdate();
            System.out.println("  ✓ " + curso + " asignado al estudiante " + estudianteId);
        } catch (SQLException e) {
            System.err.println("  ✗ Error: " + e.getMessage());
        }
    }

    // Método para listar estudiantes con sus cursos usando JOIN
    private static void listarConJoin(Connection conn) {
        // SELECT con INNER JOIN para combinar ambas tablas
        String sql = "SELECT c.id AS id_curso, e.id AS id_estudiante, e.nombre AS estudiante, " +
                "c.nombre AS curso, c.horario " +
                "FROM estudiante e " +
                "INNER JOIN cursos_asignados c ON e.id = c.estudiante_id " +
                "ORDER BY e.id";

        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            System.out.println("\n+----------+----------+--------------------+--------------------+--------------------+");
            System.out.println("| ID Curso | ID Est.  | Estudiante         | Curso              | Horario            |");
            System.out.println("+----------+----------+--------------------+--------------------+--------------------+");

            while (rs.next()) {
                System.out.printf("| %-8d | %-8d | %-18s | %-18s | %-18s |%n",
                        rs.getInt("id_curso"),
                        rs.getInt("id_estudiante"),
                        rs.getString("estudiante"),
                        rs.getString("curso"),
                        rs.getString("horario"));
            }
            System.out.println("+----------+----------+--------------------+--------------------+--------------------+\n");

        } catch (SQLException e) {
            System.err.println("Error al listar: " + e.getMessage());
        }
    }

    // Método para actualizar el horario de un curso (UPDATE)
    private static void actualizarHorario(Connection conn, int idCurso, String nuevoHorario) {
        try (PreparedStatement ps = conn.prepareStatement(
                "UPDATE cursos_asignados SET horario = ? WHERE id = ?")) {
            ps.setString(1, nuevoHorario);
            ps.setInt(2, idCurso);

            if (ps.executeUpdate() > 0) {
                System.out.println("  ✓ Horario actualizado");
            } else {
                System.out.println("  ✗ No se encontró el curso");
            }
        } catch (SQLException e) {
            System.err.println("  ✗ Error: " + e.getMessage());
        }
    }

    // Método para eliminar un estudiante (DELETE)
    // Los cursos se eliminan automáticamente por CASCADE
    private static void eliminarEstudiante(Connection conn, int idEstudiante) {
        try (PreparedStatement ps = conn.prepareStatement(
                "DELETE FROM estudiante WHERE id = ?")) {
            ps.setInt(1, idEstudiante);

            if (ps.executeUpdate() > 0) {
                System.out.println("  ✓ Estudiante eliminado ");
            } else {
                System.out.println("  ✗ No se encontró el estudiante");
            }
        } catch (SQLException e) {
            System.err.println("  ✗ Error: " + e.getMessage());
        }
    }
}
