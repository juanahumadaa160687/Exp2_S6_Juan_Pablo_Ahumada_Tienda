package org.cslt.tienda.controllers;

import org.cslt.tienda.models.producto.Producto;
import org.cslt.tienda.models.producto.ProductoModel;
import org.cslt.tienda.models.producto.ProductoModelAssembler;
import org.cslt.tienda.services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
@ComponentScan(basePackages = "org.cslt.tienda.models.producto")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private ProductoModelAssembler productoModelAssembler;

    @GetMapping("/all")
    public CollectionModel<ProductoModel> getAllProducto(){

        List<Producto> productos = productoService.getAllProductos();

        List<ProductoModel> productoModels = productos.stream()
                .map(productoModelAssembler::toModel)
                .toList();

        return CollectionModel.of(productoModels);
    }

    @GetMapping("/{id}")
    public EntityModel<ProductoModel> getProductoById(@PathVariable Long id){

        Producto producto = productoService.getProductoById(id);
        ProductoModel productoModel = productoModelAssembler.toModel(producto);

        return EntityModel.of(productoModel);
    }

    @PostMapping("/new")
    public ResponseEntity<ProductoModel> newProducto(@RequestBody Producto producto){

        Producto newProducto = productoService.newProducto(producto);
        ProductoModel productoModel = productoModelAssembler.toModel(newProducto);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<ProductoModel> editProducto(@PathVariable Long id, @RequestBody Producto producto){

        Producto editProducto = productoService.updateProducto(id, producto);
        ProductoModel productoModel = productoModelAssembler.toModel(editProducto);

        return ResponseEntity
                .ok(productoModel);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?>  deleteProducto(@PathVariable Long id){

        productoService.deleteProductoById(id);
        return ResponseEntity.noContent().build();
    }

}
