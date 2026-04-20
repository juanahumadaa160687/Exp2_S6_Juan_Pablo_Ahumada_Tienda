package org.cslt.tienda.controllers;

import org.cslt.tienda.models.Producto;
import org.cslt.tienda.services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping("/all")
    public List<Producto> getAllProductos(){
        try {
            System.out.println("Obteniendo todos los productos...");
            return productoService.getAllProductos();
        }
        catch(Exception e) {
            throw new RuntimeException("Error al obtener los productos: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public Producto getProductoById(@PathVariable Long id){
        try {
            System.out.println("Producto obtenido: " + productoService.getProductoById(id).getNombre());
            return productoService.getProductoById(id);
        }
        catch(Exception e) {
            throw new RuntimeException("Error al obtener el producto: " + e.getMessage());
        }
    }

    @PostMapping("/new")
    public Producto newProducto(@RequestBody Producto producto){
        try {
            System.out.println("Recibido producto: " + producto.getNombre());
            return productoService.newProducto(producto);
        }
        catch(Exception e) {
            throw new RuntimeException("Error al recibir producto: " + e.getMessage());
        }
    }

    @PutMapping("/edit/{id}")
    public Producto editProducto(@PathVariable Long id, @RequestBody Producto producto){
        try {
            System.out.println("Actualizando producto con ID: " + producto.getId_producto());
            return productoService.updateProducto(id, producto);
        }
        catch(Exception e) {
            throw new RuntimeException("Error al recibir producto: " + e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public void deleteProducto(@PathVariable Long id){
        try {
            System.out.println("Eliminando producto con ID: " + id);
            productoService.deleteProductoById(id);
        }
        catch(Exception e) {
            throw new RuntimeException("Error al recibir producto: " + e.getMessage());
        }
    }

}
