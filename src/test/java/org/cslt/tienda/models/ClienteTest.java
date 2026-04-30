package org.cslt.tienda.models;


import org.cslt.tienda.models.cliente.Cliente;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClienteTest {

    @Test
    @DisplayName("Test Getters Setters")
    public void testGetterSetters(){
        Cliente cliente = new Cliente();
        cliente.setCliente_id(1L);
        cliente.setNro_documento("12345678");
        cliente.setName("Juan Perez");
        cliente.setEmail("jperez@email.com");
        cliente.setPhone("1234567890");
        cliente.setAddress("Calle Falsa 123");

        assertEquals(1L, cliente.getCliente_id());
        assertEquals("12345678", cliente.getNro_documento());
    }

    @Test
    @DisplayName("Test Constructor")
    public void testConstructor() {
        Cliente cliente = new Cliente(1L, "12345678", "Juan Perez", "12345678", "Calle Falsa 123", "jperez@email.com");
        assertEquals(1L, cliente.getCliente_id());
        assertEquals("12345678", cliente.getNro_documento());
    }
}
