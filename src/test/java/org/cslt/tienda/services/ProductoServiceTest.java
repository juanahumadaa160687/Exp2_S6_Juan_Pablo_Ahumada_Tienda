package org.cslt.tienda.services;

import org.cslt.tienda.models.producto.Producto;
import org.cslt.tienda.repositories.ProductoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductoServiceTest {

    @Mock
    private ProductoRepository productoRepository;

    @InjectMocks
    private ProductoServiceImpl productoService;

    private Producto producto;

    @BeforeEach
    public void setUp() {
        producto = new Producto();
        producto.setId_producto(1L);
        producto.setNombre("Producto 1");
        producto.setDescripcion("Descripción del producto 1");
        producto.setPrecio(100.0);
        producto.setStock(10);

    }

    @Test
    @DisplayName("Test get all productos")
    public void getAllProductos() {
        List<Producto> expected = Arrays.asList(producto);
        when(productoRepository.findAll()).thenReturn(expected);

        assertEquals(expected, productoService.getAllProductos());

    }

    @Test
    @DisplayName("Test get producto by id")
    public void getProductoById() {
        when(productoRepository.findById(producto.getId_producto())).thenReturn(Optional.of(producto));

        assertEquals(producto, productoService.getProductoById(producto.getId_producto()));

    }

    @Test
    @DisplayName("Test create producto")
    public void createProducto() {
        when(productoRepository.save(producto)).thenReturn(producto);
        assertEquals(producto, productoService.newProducto(producto));
    }

    @Test
    @DisplayName("Test update producto")
    public void updateProducto() {
        when(productoRepository.findById(producto.getId_producto())).thenReturn(Optional.of(producto));
        when(productoRepository.save(producto)).thenReturn(producto);

        Producto result = productoService.updateProducto(producto.getId_producto(), producto);
        assertEquals(producto, result);
        assertEquals(producto.getId_producto(), result.getId_producto());
        verify(productoRepository, times(1)).save(producto);
    }

    @Test
    @DisplayName("Test delete producto")
    public void deleteProducto() {
        productoService.deleteProductoById(producto.getId_producto());
        verify(productoRepository, times(1)).deleteById(producto.getId_producto());
    }
}
