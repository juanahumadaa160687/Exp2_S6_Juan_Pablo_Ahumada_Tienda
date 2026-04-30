package org.cslt.tienda.models;

import org.cslt.tienda.models.cliente.Cliente;
import org.cslt.tienda.models.compra.Compra;
import org.cslt.tienda.models.producto.Producto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CompraTest {

    @Test
    @DisplayName("Test Getters Setters")
    public void testGetterSetters(){
        List<Producto> productos = new ArrayList<>();
        Cliente cliente = new Cliente();
        Compra compra = new Compra();
        compra.setCompra_id(1L);
        compra.setCliente(cliente);
        compra.setProducto(productos);

        assertEquals(1L, compra.getCompra_id());
        assertEquals(cliente, compra.getCliente());
    }

    @Test
    @DisplayName("Test Constructor")
    public void testConstructor() {
        List<Producto> productos = new ArrayList<>();
        Cliente cliente = new Cliente();
        Compra compra = new Compra(1L, productos, cliente);
        assertEquals(1L, compra.getCompra_id());
        assertEquals(cliente, compra.getCliente());
    }
}
