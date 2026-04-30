package org.cslt.tienda.services;

import org.cslt.tienda.models.cliente.Cliente;
import org.cslt.tienda.models.compra.Compra;
import org.cslt.tienda.models.ordencompra.OrdenCompra;
import org.cslt.tienda.models.pago.Pago;
import org.cslt.tienda.models.producto.Producto;
import org.cslt.tienda.repositories.OrdenCompraRepository;
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
public class OrdenCompraServiceTest {

    @Mock
    private OrdenCompraRepository ordenCompraRepository;

    @InjectMocks
    private OrdenCompraServiceImpl ordenCompraService;

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
    @DisplayName("Get all Ordenes-Compra")
    public void getAllOrdenesCompra() {
        List<OrdenCompra> expected = Arrays.asList(ordenCompra);
        when(ordenCompraRepository.findAll()).thenReturn(expected);

        assertEquals(expected, ordenCompraService.getAllOrdenCompra());
    }

    @Test
    @DisplayName("Get Ordenes-Compra by id")
    public void getOrdenCompraById() {
        when(ordenCompraRepository.findById(1L)).thenReturn(Optional.of(ordenCompra));
        assertEquals(ordenCompra, ordenCompraService.getOrdenCompraById(1L));
    }

    @Test
    @DisplayName("Test crear OrdenCompra")
    public void createOrdenCompra() {
        when(ordenCompraRepository.save(ordenCompra)).thenReturn(ordenCompra);

        assertEquals(ordenCompra, ordenCompraService.newOrdenCompra(ordenCompra));
    }

    @Test
    @DisplayName("Test delete OrdenCompra")
    public void deleteOrdenCompra() {
        ordenCompraService.deleteOrdenCompraById(1L);
        verify(ordenCompraRepository, times(1)).deleteById(1L);
    }
}
