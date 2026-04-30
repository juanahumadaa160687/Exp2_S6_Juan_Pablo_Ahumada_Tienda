package org.cslt.tienda.repositories;

import org.cslt.tienda.models.producto.Producto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductoRepositoryTest {

    @Autowired
    private ProductoRepository productoRepository;

    private Producto producto;

    @BeforeEach
    public void setup() {
        producto = new Producto();
        producto.setNombre("Producto 1");
        producto.setDescripcion("Descripción del producto 1");
        producto.setPrecio(100.0);
        producto.setStock(10);

        productoRepository.save(producto);
    }

    @AfterEach
    public void tearDown() {
        productoRepository.deleteAll();
    }

    @Test
    @DisplayName("Test Get all Productos")
    public void testFindAll() {
        List<Producto> productos = productoRepository.findAll();
        assertNotNull(productos);
        assertFalse(productos.isEmpty());
    }

    @Test
    @DisplayName("Test Get producto by Id")
    public void testFindById() {
        Producto producto1 = productoRepository.findById(producto.getId_producto()).orElse(null);
        assertNotNull(producto1);
        assertEquals(producto.getId_producto(), producto1.getId_producto());
    }

    @Test
    @DisplayName("Test crear producto")
    public void testCreate() {
        productoRepository.save(producto);
        assertNotNull(producto);
    }

    @Test
    @DisplayName("Test eliminar producto")
    public void testDelete() {
        productoRepository.deleteById(producto.getId_producto());
        assertFalse(productoRepository.findById(producto.getId_producto()).isPresent());
    }
}
