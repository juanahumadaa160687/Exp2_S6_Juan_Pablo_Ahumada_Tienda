package org.cslt.tienda.controllers;

import org.cslt.tienda.models.cliente.Cliente;
import org.cslt.tienda.models.compra.Compra;
import org.cslt.tienda.models.ordencompra.OrdenCompra;
import org.cslt.tienda.models.pago.Pago;
import org.cslt.tienda.models.producto.Producto;
import org.cslt.tienda.services.OrdenCompraService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(OrdenCompraController.class)
public class OrdenCompraControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private OrdenCompraService service;

    @MockitoBean
    private PagoController pagoController;

    private OrdenCompra ordenCompra;
    private Pago pago;
    private Compra compra;

    @BeforeEach
    public void setup() {
        compra = new Compra();
        List<Producto> productos = new ArrayList<>();
        Cliente cliente = new Cliente();
        compra.setCompra_id(1L);
        compra.setCliente(cliente);
        compra.setProducto(productos);

        pago = new Pago();
        pago.setId_pago(1L);
        pago.setMedio_pago("Crédito");
        pago.setFecha_pago(Date.valueOf("2024-07-01"));
        pago.setTotal_pago(100.0);
        pago.setCompra(compra);

        ordenCompra = new OrdenCompra();
        ordenCompra.setId(1L);
        ordenCompra.setFecha_orden(Date.valueOf("2024-07-01"));
        ordenCompra.setPago(pago);

    }

    @Test
    @DisplayName("Test Get All Ordenes de Compra")
    public void getAllOrdenesCompraTest() throws Exception {

        when(service.getAllOrdenCompra()).thenReturn(java.util.Arrays.asList(ordenCompra));

        mockMvc.perform(get("/ordenes-compra/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].id").value(ordenCompra.getId()));
    }

    @Test
    @DisplayName("Test Get Orden Compra")
    public void getOrdenCompraTest() throws Exception {

        when(service.getOrdenCompraById(ordenCompra.getId())).thenReturn(ordenCompra);
        mockMvc.perform(get("/ordenes-compra/" + ordenCompra.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(ordenCompra.getId()));
    }

    @Test
    @DisplayName("Test Create New Orden Compra")
    public void createOrdenCompraTest() throws Exception {

        when(service.newOrdenCompra(any(OrdenCompra.class))).thenReturn(ordenCompra);

        mockMvc.perform(post("/ordenes-compra/new/" + compra.getCompra_id())
                        .contentType("application/json")
                        .content("{\"fecha_orden\":\"2024-07-01\",\"pago\":{\"id_pago\":1,\"medio_pago\":\"Crédito\",\"fecha_pago\":\"2024-07-01\",\"total_pago\":100.0,\"compra\":{\"compra_id\":1}}}"))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("Test Delete Orden Compra")
    public void deleteOrdenCompraTest() throws Exception {

        when(service.getOrdenCompraById(ordenCompra.getId())).thenReturn(ordenCompra);

        mockMvc.perform(delete("/ordenes-compra/delete/" + ordenCompra.getId()))
                .andExpect(status().isNoContent());
        verify(service, times(1)).deleteOrdenCompraById(ordenCompra.getId());
    }
}
