package umg.edu.tarea13.repository;

import umg.edu.tarea13.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {}
