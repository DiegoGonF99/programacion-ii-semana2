package umg.edu.tarea13.service;

import umg.edu.tarea13.model.Producto;
import umg.edu.tarea13.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {
    private final ProductoRepository repo;

    public ProductoService(ProductoRepository repo) {
        this.repo = repo;
    }

    public List<Producto> listar() { return repo.findAll(); }
    public Producto crear(Producto p) { return repo.save(p); }
    public Producto buscar(Long id) { return repo.findById(id).orElse(null); }
    public Producto actualizar(Long id, Producto datos) {
        Producto p = repo.findById(id).orElseThrow();
        p.setNombre(datos.getNombre());
        p.setPrecio(datos.getPrecio());
        return repo.save(p);
    }
    public void eliminar(Long id) { repo.deleteById(id); }
}