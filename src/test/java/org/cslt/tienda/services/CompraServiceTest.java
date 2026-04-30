package org.cslt.tienda.services;

import org.cslt.tienda.models.cliente.Cliente;
import org.cslt.tienda.models.compra.Compra;
import org.cslt.tienda.models.producto.Producto;
import org.cslt.tienda.repositories.CompraRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CompraServiceTest {

    @Mock
    private CompraRepository compraRepository;

    @InjectMocks
    private CompraServiceImpl compraService;

    private Compra compra;
    private Cliente cliente;
    private List<Producto> productos;
    private Producto producto;

    @BeforeEach
    public void setUp() {
        cliente = new Cliente();
        cliente.setCliente_id(1L);
        cliente.setNro_documento("12345678");
        cliente.setName("Juan Perez");
        cliente.setEmail("jperez@email.com");
        cliente.setPhone("1234567890");
        cliente.setAddress("Calle Falsa 123");

        producto = new Producto();
        producto.setId_producto(1L);
        producto.setNombre("Producto 1");
        producto.setDescripcion("Descripción del producto 1");
        producto.setPrecio(100.0);
        producto.setStock(10);

        productos = new ArrayList<>();
        productos.add(producto);

        compra = new Compra();
        compra.setCompra_id(1L);
        compra.setCliente(cliente);
        compra.setProducto(productos);
    }

    @Test
    @DisplayName("Get all Compras")
    public void getAllCompras() {
        List<Compra> compras = compraService.getAllCompras();

        when(compraRepository.findAll()).thenReturn(compras);

        assertEquals(compras, compraService.getAllCompras());
    }

    @Test
    @DisplayName("Get Compra by Id")
    public void getCompraById() {
        when(compraRepository.findById(1L)).thenReturn(Optional.of(compra));
        assertEquals(compra, compraService.findCompraById(1L));
    }

    @Test
    @DisplayName("Create new Compra")
    public void createCompra() {
        when(compraRepository.save(compra)).thenReturn(compra);
        assertEquals(compra, compraService.newCompra(compra));
    }

    @Test
    @DisplayName("Update Compra")
    public void updateCompra() {
        when(compraRepository.findById(1L)).thenReturn(Optional.of(compra));
        when(compraRepository.save(compra)).thenReturn(compra);

        Compra result = compraService.updateCompra(1L, compra);
        assertEquals(compra, result);
        assertEquals(compra.getCompra_id(), result.getCompra_id());
        verify(compraRepository, times(1)).save(compra);
    }

    @Test
    @DisplayName("Delete Compra")
    public void deleteCompra() {
        compraService.deleteCompraById(1L);
        verify(compraRepository, times(1)).deleteById(1L);
    }
}
