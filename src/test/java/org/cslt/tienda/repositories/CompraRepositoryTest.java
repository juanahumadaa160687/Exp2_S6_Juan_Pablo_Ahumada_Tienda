package org.cslt.tienda.repositories;

import org.cslt.tienda.models.cliente.Cliente;
import org.cslt.tienda.models.compra.Compra;
import org.cslt.tienda.models.producto.Producto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CompraRepositoryTest {

    @Autowired
    private CompraRepository compraRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProductoRepository productoRepository;

    private Compra compra;
    private Cliente cliente;
    private Producto producto;
    private List<Producto> productos;

    @BeforeEach
    public void setUp() {
        cliente = new Cliente();
        cliente.setNro_documento("12345678");
        cliente.setName("Juan Perez");
        cliente.setEmail("jperez@email.com");
        cliente.setPhone("1234567890");
        cliente.setAddress("Calle Falsa 123");

        clienteRepository.save(cliente);

        producto = new Producto();
        producto.setNombre("Producto 1");
        producto.setDescripcion("Descripción del producto 1");
        producto.setPrecio(100.0);
        producto.setStock(10);

        productoRepository.save(producto);

        productos = new ArrayList<>();
        productos.add(producto);

        compra = new Compra();
        compra.setCliente(cliente);
        compra.setProducto(productos);

        compraRepository.save(compra);
    }

    @AfterEach
    public void tearDown() {
        compraRepository.deleteAll();
    }

    @Test
    @DisplayName("Test Get all Compras")
    public void testGetAll() {

        List<Compra> compras = compraRepository.findAll();

        assertNotNull(compras);
        assertFalse(compras.isEmpty());
    }

    @Test
    @DisplayName("Test Get Compra by Id")
    public void testGetById() {

        Compra compra1 = compraRepository.findById(compra.getCompra_id()).orElse(null);
        assertNotNull(compra1);
        assertEquals(compra.getCompra_id(), compra1.getCompra_id());
    }

    @Test
    @DisplayName("Test Save Compra")
    public void testSave() {
        compraRepository.save(compra);

        assertNotNull(compra);
    }

    @Test
    @DisplayName("Test Delete Compra")
    public void testDelete() {
        compraRepository.deleteById(compra.getCompra_id());
        assertFalse(compraRepository.findById(compra.getCompra_id()).isPresent());
    }

}
