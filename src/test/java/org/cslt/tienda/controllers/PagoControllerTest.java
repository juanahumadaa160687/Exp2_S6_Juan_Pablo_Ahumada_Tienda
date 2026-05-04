package org.cslt.tienda.controllers;

import org.cslt.tienda.models.cliente.Cliente;
import org.cslt.tienda.models.compra.Compra;
import org.cslt.tienda.models.pago.Pago;
import org.cslt.tienda.models.producto.Producto;
import org.cslt.tienda.services.PagoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MediaType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = PagoController.class)
public class PagoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PagoService service;

    private Pago pago;
    private Compra compra;

    @BeforeEach
    public void setUp() throws Exception {
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
    }

    @Test
    @DisplayName("Test Get All Pagos")
    public void getAllPagosTest() throws Exception {

        when(service.getAllPagos()).thenReturn(Arrays.asList(pago));

        mockMvc.perform(get("/pagos/all")
                .contentType(String.valueOf(MediaType.APPLICATION_JSON)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].id_pago").value(pago.getId_pago()));
    }

    @Test
    @DisplayName("Test Get Pago by Id")
    public void getPagoByIdTest() throws Exception {

        when(service.getPagoById(pago.getId_pago())).thenReturn(pago);

        mockMvc.perform(get("/pagos/"+pago.getId_pago())
                .contentType(String.valueOf(MediaType.APPLICATION_JSON)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id_pago").value(pago.getId_pago()));
    }

    @Test
    @DisplayName("Test Create New Pago")
    public void createNewPagoTest() throws Exception {

        when(service.newPago(any(Pago.class))).thenReturn(pago);

        mockMvc.perform(post("/pagos/new")
                .contentType(String.valueOf(MediaType.APPLICATION_JSON))
                .content("{\"medio_pago\":\"Crédito\",\"fecha_pago\":\"2024-07-01\",\"total_pago\":100.0,\"compra\":{\"compra_id\":1}}"))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("Test Update Pago by Id")
    public void updatePagoByIdTest() throws Exception {

        when(service.getPagoById(pago.getId_pago())).thenReturn(pago);
        when(service.updatePago(eq(pago.getId_pago()), any(Pago.class))).thenReturn(pago);

        mockMvc.perform(put("/pagos/edit/"+pago.getId_pago())
                .contentType(String.valueOf(MediaType.APPLICATION_JSON))
                .content("{\"medio_pago\":\"Crédito\",\"fecha_pago\":\"2024-07-01\",\"total_pago\":100.0,\"compra\":{\"compra_id\":1}}"))
                .andExpect(status().isOk());
    }

     @Test
     @DisplayName("Test Delete Pago by Id")
     public void deletePagoByIdTest() throws Exception {

         when(service.getPagoById(pago.getId_pago())).thenReturn(pago);

         mockMvc.perform(delete("/pagos/delete/" + pago.getId_pago()))
                 .andExpect(status().isNoContent());
         verify(service, times(1)).deletePagoById(pago.getId_pago());
     }
}
