package org.cslt.tienda.repositories;

import org.cslt.tienda.models.cliente.Cliente;
import org.cslt.tienda.models.compra.Compra;
import org.cslt.tienda.models.ordencompra.OrdenCompra;
import org.cslt.tienda.models.pago.Pago;
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
public class OrdenCompraRepositoryTest {

    @Autowired
    private OrdenCompraRepository ordenCompraRepository;

    @Autowired
    private PagoRepository pagoRepository;

    @Autowired
    private CompraRepository compraRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    private OrdenCompra ordenCompra;
    private Pago pago;
    private Compra compra;
    private Cliente cliente;

    @BeforeEach
    public void setup() {
        cliente = new Cliente();
        cliente.setNro_documento("12345678");
        cliente.setName("Juan Perez");
        cliente.setEmail("jperez@email.com");
        cliente.setPhone("1234567890");
        cliente.setAddress("Calle Falsa 123");

        clienteRepository.save(cliente);

        compra = new Compra();
        List<Producto> productos = new ArrayList<>();
        compra.setCliente(cliente);
        compra.setProducto(productos);

        compraRepository.save(compra);

        pago = new Pago();
        pago.setMedio_pago("Crédito");
        pago.setFecha_pago(Date.valueOf("2024-07-01"));
        pago.setTotal_pago(100.0);
        pago.setCompra(compra);

        pagoRepository.save(pago);

        ordenCompra = new OrdenCompra();
        ordenCompra.setFecha_orden(Date.valueOf("2024-07-01"));
        ordenCompra.setPago(pago);

        ordenCompraRepository.save(ordenCompra);

    }

    @AfterEach
    public void tearDown() {
        ordenCompraRepository.deleteAll();
    }

    @Test
    @DisplayName("Test Get all Ordenes de Compra")
    public void testFindAll() {
        List<OrdenCompra> ordenes = ordenCompraRepository.findAll();
        assertNotNull(ordenes);
        assertFalse(ordenes.isEmpty());
    }

    @Test
    @DisplayName("Test Find Orden de Compra")
    public void testFindById() {
        OrdenCompra ordenCompra = ordenCompraRepository.findById(this.ordenCompra.getId()).orElse(null);
        assertNotNull(ordenCompra);
        assertEquals(this.ordenCompra.getId(), ordenCompra.getId());
    }

    @Test
    @DisplayName("Test Crear Orden de Compra")
    public void testCreateOrdenCompra() {
        OrdenCompra nuevaOrden = ordenCompraRepository.save(ordenCompra);
        assertNotNull(nuevaOrden);
    }

    @Test
    @DisplayName("Test Eliminar Orden de Compra")
    public void testDeleteOrdenCompra() {
        ordenCompraRepository.deleteById(this.ordenCompra.getId());
        assertFalse(ordenCompraRepository.findById(this.ordenCompra.getId()).isPresent());
    }
}
