package org.cslt.tienda.models.producto;

import org.cslt.tienda.controllers.ProductoController;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ProductoModelAssembler implements RepresentationModelAssembler<Producto, ProductoModel> {

    @Override
    public ProductoModel toModel(Producto producto) {
        ProductoModel productoModel = new ProductoModel();
        productoModel.setId_producto(producto.getId_producto());
        productoModel.setNombre(producto.getNombre());
        productoModel.setDescripcion(producto.getDescripcion());
        productoModel.setPrecio(producto.getPrecio());
        productoModel.setStock(producto.getStock());

        //Self Link
        productoModel.add(linkTo(methodOn(ProductoController.class).getProductoById(producto.getId_producto())).withSelfRel());

        //Link eliminar producto
        productoModel.add(linkTo(methodOn(ProductoController.class).deleteProducto(producto.getId_producto())).withRel("eliminar-producto"));

        //Link todos los productos
        productoModel.add(linkTo(methodOn(ProductoController.class).getAllProducto()).withRel("todos-los-productos"));

        return productoModel;

    }
}
