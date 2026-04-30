package org.cslt.tienda.repositories;

import org.cslt.tienda.models.cliente.Cliente;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ClienteRepositoryTest {

    @Autowired
    private ClienteRepository clienteRepository;

    private Cliente cliente;

    @BeforeEach
    public void setUp(){
        cliente = new Cliente();
        cliente.setNro_documento("12345678");
        cliente.setName("Juan Perez");
        cliente.setEmail("jperez@email.com");
        cliente.setPhone("1234567890");
        cliente.setAddress("Calle Falsa 123");

        clienteRepository.save(cliente);
    }

    @AfterEach
    public void tearDown(){
        clienteRepository.deleteAll();
    }

    @Test
    @DisplayName("Test Get all Clientes")
    public void testFindAll(){
        List<Cliente> clientes = clienteRepository.findAll();

        assertNotNull(clientes);
        assertFalse(clientes.isEmpty());
    }

    @Test
    @DisplayName("Test Find Cliente by ID")
    public void testFindById(){

        Cliente cli = clienteRepository.findById(cliente.getCliente_id()).orElse(null);

        assertNotNull(cli);
        assertEquals(cliente.getCliente_id(), cli.getCliente_id());

    }

    @Test
    @DisplayName("Test Crear Cliente")
    public void testCreateCliente(){

        clienteRepository.save(cliente);

        assertNotNull(cliente);
    }

    @Test
    @DisplayName("Test Eliminar Cliente")
    public void testDeleteCliente() {
        clienteRepository.deleteById(cliente.getCliente_id());

        assertFalse(clienteRepository.findById(cliente.getCliente_id()).isPresent());

    }
}
