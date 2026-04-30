package org.cslt.tienda.models;

import org.cslt.tienda.models.compra.Compra;
import org.cslt.tienda.models.pago.Pago;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PagoTest {

    @Test
    @DisplayName("Test Getters Setters")
    public void testGettersSetters(){
        Pago pago = new Pago();
        pago.setId_pago(1L);
        pago.setMedio_pago("Crédito");
        pago.setFecha_pago(Date.valueOf("2024-07-01"));
        pago.setTotal_pago(100.0);
        pago.setCompra(new Compra());

        assertEquals(1L, pago.getId_pago());
        assertEquals("Crédito", pago.getMedio_pago());
    }

    @Test
    @DisplayName("Test Constructor")
    public void testConstructor() {
        Pago pago = new Pago(1L, "Crédito", 100.0, Date.valueOf("2024-07-01"), new Compra());
        assertEquals(1L, pago.getId_pago());
        assertEquals("Crédito", pago.getMedio_pago());
    }
}
