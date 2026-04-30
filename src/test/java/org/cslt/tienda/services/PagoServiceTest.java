package org.cslt.tienda.services;

import org.cslt.tienda.models.cliente.Cliente;
import org.cslt.tienda.models.compra.Compra;
import org.cslt.tienda.models.pago.Pago;
import org.cslt.tienda.models.producto.Producto;
import org.cslt.tienda.repositories.ClienteRepository;
import org.cslt.tienda.repositories.CompraRepository;
import org.cslt.tienda.repositories.PagoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PagoServiceTest {

    @Mock
    private PagoRepository pagoRepository;

    @InjectMocks
    private PagoServiceImpl pagoService;

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
    public void testFindAllPagos(){
        List<Pago> expected = Arrays.asList(pago);
        when(pagoRepository.findAll()).thenReturn(expected);
        assertEquals(expected, pagoService.getAllPagos());
    }

    @Test
    @DisplayName("Test Get Pago By Id")
    public void testFindById(){
        when(pagoRepository.findById(1L)).thenReturn(Optional.of(pago));
        assertEquals(pago, pagoService.getPagoById(1L));
    }

    @Test
    @DisplayName("Test Create Pago")
    public void testCreatePago() {
        when(pagoRepository.save(pago)).thenReturn(pago);
        assertEquals(pago, pagoService.newPago(pago));
    }

    @Test
    @DisplayName("Test Update Pago")
    public void testUpdatePago() {
        when(pagoRepository.findById(1L)).thenReturn(Optional.of(pago));
        when(pagoRepository.save(pago)).thenReturn(pago);
        Pago result = pagoService.updatePago(1L, pago);
        assertEquals(pago, result);
        assertEquals(pago.getId_pago(), result.getId_pago());
        verify(pagoRepository, times(1)).save(pago);
    }

    @Test
    @DisplayName("Test Delete Pago")
    public void testDeletePago() {
        pagoService.deletePagoById(1L);
        verify(pagoRepository, times(1)).deleteById(1L);
    }
}
