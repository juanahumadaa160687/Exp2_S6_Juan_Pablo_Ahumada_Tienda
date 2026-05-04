package org.cslt.tienda.controllers;


import org.cslt.tienda.models.cliente.Cliente;
import org.cslt.tienda.models.compra.Compra;
import org.cslt.tienda.models.producto.Producto;
import org.cslt.tienda.services.CompraService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MediaType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CompraController.class)
public class CompraControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CompraService service;

    private Compra compra;
    private Cliente cliente;
    private Producto producto;
    private List<Producto> productos;

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
    @DisplayName("Test Get All Compras")
    public void getAllComprasTest() throws Exception {

        when(service.getAllCompras()).thenReturn(List.of(compra));

        mockMvc.perform(get("/compras/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].compra_id").value(compra.getCompra_id()));
    }

    @Test
    @DisplayName("Test Get Compra by Id")
    public void getCompraTest() throws Exception {

        when(service.findCompraById(compra.getCompra_id())).thenReturn(compra);

        mockMvc.perform(get("/compras/" + compra.getCompra_id()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.compra_id").value(compra.getCompra_id()));
    }

    @Test
    @DisplayName("Test Create New Compra")
    public void createNewCompraTest() throws Exception {

        when(service.newCompra(any(Compra.class))).thenReturn(compra);

        mockMvc.perform(post("/compras/new")
                .contentType(String.valueOf(MediaType.APPLICATION_JSON))
                .content("{\"compra_id\":1,\"cliente\":{\"cliente_id\":1,\"nro_documento\":\"12345678\",\"name\":\"Juan Perez\",\"email\":\"jperez@email.com\",\"phone\":\"1234567890\",\"address\":\"Calle Falsa 123\"},\"producto\":[{\"id_producto\":1,\"nombre\":\"Producto 1\",\"descripcion\":\"Descripción del producto 1\",\"precio\":100.0,\"stock\":10}]}"))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("Test Update Compra by Id")
    public void updateCompraTest() throws Exception {

        when(service.findCompraById(compra.getCompra_id())).thenReturn(compra);
        when(service.updateCompra(eq(compra.getCompra_id()), any(Compra.class))).thenReturn(compra);

        mockMvc.perform(put("/compras/edit/" + compra.getCompra_id())
                .contentType(String.valueOf(MediaType.APPLICATION_JSON))
                .content("{\"compra_id\":1,\"cliente\":{\"cliente_id\":1,\"nro_documento\":\"12345678\",\"name\":\"Juan Perez\",\"email\":\"jperez@email.com\",\"phone\":\"1234567890\",\"address\":\"Calle Falsa 123\"},\"producto\":[{\"id_producto\":1,\"nombre\":\"Producto 1\",\"descripcion\":\"Descripción del producto 1\",\"precio\":100.0,\"stock\":10}]}"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Test Delete Compra by Id")
    public void deleteCompraTest() throws Exception {
        when(service.findCompraById(compra.getCompra_id())).thenReturn(compra);
        mockMvc.perform(delete("/compras/delete/" + compra.getCompra_id()))
                .andExpect(status().isNoContent());
        verify(service, times(1)).deleteCompraById(compra.getCompra_id());
    }
}
