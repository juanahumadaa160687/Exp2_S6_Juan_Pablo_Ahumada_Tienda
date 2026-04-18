package org.cslt.tienda.controllers;

import org.cslt.tienda.models.Producto;
import org.cslt.tienda.services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping("/all")
    public List<Producto> getAllProductos(){
        return productoService.getAllProductos();
    }

    @GetMapping("/{id}")
    public Producto getProductoById(@PathVariable Long id){
        return productoService.getProductoById(id);
    }

    @PostMapping("/new")
    public Producto newProducto(@RequestBody Producto producto){
        return productoService.newProducto(producto);
    }

    @PutMapping("/edit/{id}")
    public Producto editProducto(@PathVariable Long id, @RequestBody Producto producto){
        return productoService.updateProducto(id, producto);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteProducto(@PathVariable Long id){
        productoService.deleteProductoById(id);
    }

}
