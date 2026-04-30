package org.cslt.tienda.repositories;

import org.cslt.tienda.models.cliente.Cliente;
import org.cslt.tienda.models.compra.Compra;
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
public class PagoRepositoryTest {

    @Autowired
    private PagoRepository pagoRepository;

    @Autowired
    private CompraRepository compraRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    private Compra compra;
    private Pago pago;
    private Cliente cliente;

    @BeforeEach
    public void setUp() {
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
    }

    @AfterEach
    public void tearDown() {
        pagoRepository.deleteAll();
    }

    @Test
    @DisplayName("Test Find All Pago")
    public void testFindAll() {

        List<Pago> pagos = pagoRepository.findAll();

        assertNotNull(pagos);
        assertFalse(pagos.isEmpty());
    }

    @Test
    @DisplayName("Test Find By Id")
    public void testFindById() {

        Pago pago1 = pagoRepository.findById(pago.getId_pago()).orElse(null);

        assertNotNull(pago1);
        assertEquals(pago.getId_pago(), pago1.getId_pago());
    }

    @Test
    @DisplayName("Test Create Pago")
    public void testCreatePago() {

        pagoRepository.save(pago);

        assertNotNull(pago);
    }

    @Test
    @DisplayName("Test Delete Pago")
    public void testDeletePago() {
        pagoRepository.deleteById(pago.getId_pago());
        assertFalse(pagoRepository.findById(pago.getId_pago()).isPresent());
    }


}
