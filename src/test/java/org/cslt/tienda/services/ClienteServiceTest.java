package org.cslt.tienda.services;

import org.cslt.tienda.models.cliente.Cliente;
import org.cslt.tienda.repositories.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteServiceImpl clienteService;

    private Cliente cliente;

    @BeforeEach
    public void setUp() {
        cliente = new Cliente();
        cliente.setCliente_id(1L);
        cliente.setNro_documento("12345678");
        cliente.setName("Juan Perez");
        cliente.setEmail("jperez@email.com");
        cliente.setPhone("1234567890");
        cliente.setAddress("Calle Falsa 123");

    }

    @Test
    @DisplayName("Test get all clientes")
    public void getAllClientes(){

        List<Cliente> expected = Arrays.asList(cliente);
        when(clienteRepository.findAll()).thenReturn(expected);

        assertEquals(expected, clienteService.getAllClientes());
    }

    @Test
    @DisplayName("Test get cliente by id")
    public void getClientById(){

        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));

        assertEquals(cliente, clienteService.getClienteById(1L));
    }

    @Test
    @DisplayName("Test crear cliente")
    public void createCliente(){
        when(clienteRepository.save(cliente)).thenReturn(cliente);

        assertEquals(cliente, clienteService.newCliente(cliente));
    }

    @Test
    @DisplayName("Test modificar cliente")
    public void modifyCliente(){

        when(clienteRepository.findById(cliente.getCliente_id())).thenReturn(Optional.of(cliente));

        when(clienteRepository.save(cliente)).thenReturn(cliente);

        Cliente result = clienteService.updateCliente(cliente.getCliente_id(), cliente);

        assertEquals(cliente, result);
        assertEquals(cliente.getName(), result.getName());
        verify(clienteRepository,  times(1)).save(cliente);
    }

    @Test
    @DisplayName("Test deletar cliente")
    public void deleteCliente(){
        clienteService.deleteClienteById(1L);
        verify(clienteRepository, times(1)).deleteById(1L);
    }
}
