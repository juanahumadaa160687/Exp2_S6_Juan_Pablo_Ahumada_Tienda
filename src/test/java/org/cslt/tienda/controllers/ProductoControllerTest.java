package org.cslt.tienda.controllers;

import org.cslt.tienda.models.producto.Producto;
import org.cslt.tienda.services.ProductoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MediaType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductoController.class)
public class ProductoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProductoService service;

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
    @DisplayName("Test Get All Productos")
    public void getAllProductos() throws Exception {

        when(service.getAllProductos()).thenReturn(Arrays.asList(producto));

        mockMvc.perform(get("/productos/all"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].id_producto").value(producto.getId_producto()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].nombre").value(producto.getNombre()));
    }

    @Test
    @DisplayName("Test Get Producto By Id")
    public void getProductoById() throws Exception {

        when(service.getProductoById(1L)).thenReturn(producto);

        mockMvc.perform(get("/productos/" + producto.getId_producto()))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id_producto").value(producto.getId_producto()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nombre").value(producto.getNombre()));
    }

    @Test
    @DisplayName("Test Create New Producto")
    public void createNewProducto() throws Exception {

        when(service.newProducto(any(Producto.class))).thenReturn(producto);

        mockMvc.perform(post("/productos/new")
                .contentType(String.valueOf(MediaType.APPLICATION_JSON))
                .content("{\"nombre\":\"Producto 1\",\"descripcion\":\"Descripción del producto 1\",\"precio\":100.0,\"stock\":10}"))
                .andExpect(status().isCreated());
        verify(service, times(1)).newProducto(any(Producto.class));
    }

    @Test
    @DisplayName("Test Update Producto By Id")
    public void updateProductoById() throws Exception {

        when(service.getProductoById(1L)).thenReturn(producto);
        when(service.updateProducto(eq(producto.getId_producto()), any(Producto.class))).thenReturn(producto);

        mockMvc.perform(put("/productos/edit/" + producto.getId_producto())
                .contentType("application/json")
                .content("{\"nombre\":\"Producto 1\",\"descripcion\":\"Descripción del producto 1\",\"precio\":100.0,\"stock\":10}"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id_producto").value(producto.getId_producto()));
    }

    @Test
    @DisplayName("Test Delete Producto By Id")
    public void deleteProductoById() throws Exception {

        when(service.getProductoById(producto.getId_producto())).thenReturn(producto);

        mockMvc.perform(delete("/productos/delete/" + producto.getId_producto()))
                .andExpect(status().isNoContent());
        verify(service, times(1)).deleteProductoById(producto.getId_producto());
    }

}
