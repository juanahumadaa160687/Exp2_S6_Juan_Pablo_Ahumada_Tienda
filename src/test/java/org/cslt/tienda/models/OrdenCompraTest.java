package org.cslt.tienda.models;

import org.cslt.tienda.models.ordencompra.OrdenCompra;
import org.cslt.tienda.models.pago.Pago;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrdenCompraTest {

    @Test
    @DisplayName("Test Getters & Setters")
    public void testGettersAndSetters() {
        OrdenCompra ordenCompra = new OrdenCompra();
        ordenCompra.setId(1L);
        ordenCompra.setFecha_orden(Date.valueOf("2024-07-01"));
        ordenCompra.setPago(new Pago());

        assertEquals(1L, ordenCompra.getId());
        assertEquals(Date.valueOf("2024-07-01"), ordenCompra.getFecha_orden());
    }

    @Test
    @DisplayName("Test Constructor")
    public void testConstructor() {
        OrdenCompra ordenCompra = new OrdenCompra(1L, Date.valueOf("2024-07-01"), new Pago());
        assertEquals(1L, ordenCompra.getId());
        assertEquals(Date.valueOf("2024-07-01"), ordenCompra.getFecha_orden());
    }
}
