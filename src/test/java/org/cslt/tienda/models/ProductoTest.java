package org.cslt.tienda.models;


import org.cslt.tienda.models.producto.Producto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProductoTest {

    @Test
    @DisplayName("Test Getters & Setters")
    public void testGettersSetters(){

        Producto producto = new Producto();
        producto.setId_producto(1L);
        producto.setNombre("Producto 1");
        producto.setDescripcion("Descripción del producto 1");
        producto.setPrecio(100.0);
        producto.setStock(10);

        assertEquals(1L, producto.getId_producto());
        assertEquals("Producto 1", producto.getNombre());
    }

    @Test
    @DisplayName("Test Constructor")
    public void testConstructor() {
        Producto producto = new Producto(1L, "Producto 1", "Descripción del producto 1", 100.0, 10);
        assertEquals(1L, producto.getId_producto());
        assertEquals("Producto 1", producto.getNombre());
    }
}
