package org.cslt.tienda.controllers;

import org.cslt.tienda.models.cliente.Cliente;
import org.cslt.tienda.services.ClienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MediaType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = ClienteController.class)
public class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ClienteService service;

    private Cliente cliente;

    @BeforeEach
    public void setup() {
        cliente = new Cliente();
        cliente.setCliente_id(1L);
        cliente.setNro_documento("12345678");
        cliente.setName("Juan Perez");
        cliente.setEmail("jperez@email.com");
        cliente.setPhone("1234567890");
        cliente.setAddress("Calle Falsa 123");
    }

    @Test
    @DisplayName("Test Get All Clientes")
    public void getAllClientesTest() throws Exception {

        when(service.getAllClientes()).thenReturn(Arrays.asList(cliente));

        mockMvc.perform(get("/clientes/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].cliente_id").value(cliente.getCliente_id()));
    }

    @Test
    @DisplayName("Test Get Cliente By Id")
    public void getClienteByIdTest() throws Exception {

        when(service.getClienteById(cliente.getCliente_id())).thenReturn(cliente);

        mockMvc.perform(get("/clientes/"+cliente.getCliente_id()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cliente_id").value(cliente.getCliente_id()));
    }

    @Test
    @DisplayName("Test Create New Cliente")
    public void createNewClienteTest() throws Exception {

        when(service.newCliente(any(Cliente.class))).thenReturn(cliente);

        mockMvc.perform(post("/clientes/new")
                .contentType("application/json")
                .content("{\"nro_documento\":\"12345678\",\"name\":\"Juan Perez\",\"email\":\"jperez@email.com\",\"phone\":\"1234567890\",\"address\":\"Calle Falsa 123\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.cliente_id").value(cliente.getCliente_id()));
        verify(service, times(1)).newCliente(any(Cliente.class));
    }

    @Test
    @DisplayName("Test Update Cliente By Id")
    public void updateClienteByIdTest() throws Exception {

        when(service.getClienteById(cliente.getCliente_id())).thenReturn(cliente);
        when(service.updateCliente(eq(cliente.getCliente_id()), any(Cliente.class))).thenReturn(cliente);

        mockMvc.perform(put("/clientes/edit/"+cliente.getCliente_id())
                .contentType(String.valueOf(MediaType.APPLICATION_JSON))
                .content("{\"nro_documento\":\"12345678\",\"name\":\"Juan Perez\",\"email\":\"jperez@email.com\",\"phone\":\"1234567890\",\"address\":\"Calle Falsa 123\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cliente_id").value(cliente.getCliente_id()));
    }

    @Test
    @DisplayName("Test Delete Cliente By Id")
    public void deleteClienteByIdTest() throws Exception {

        when(service.getClienteById(cliente.getCliente_id())).thenReturn(cliente);

        mockMvc.perform(delete("/clientes/delete/"+cliente.getCliente_id()))
                .andExpect(status().isNoContent());
        verify(service, times(1)).deleteClienteById(cliente.getCliente_id());
    }

}
