package org.cslt.tienda.models.compra;

import org.cslt.tienda.controllers.ClienteController;
import org.cslt.tienda.controllers.CompraController;
import org.cslt.tienda.controllers.ProductoController;
import org.cslt.tienda.models.producto.Producto;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CompraModelAssembler implements RepresentationModelAssembler<Compra, CompraModel> {

    @Override
    public CompraModel toModel(Compra compra) {
        CompraModel model = new CompraModel();
        model.setCompra_id(compra.getCompra_id());
        model.setProducto(compra.getProducto());
        model.setCliente(compra.getCliente());

        //Link Self
        model.add(linkTo(methodOn(CompraController.class).getCompraById(compra.getCompra_id())).withSelfRel());

        //Link producto
        if(model.getProducto() != null){
            for(Producto producto : model.getProducto()){
                //Link producto
                model.add(linkTo(methodOn(ProductoController.class).getProductoById(producto.getId_producto())).withRel("producto-" + producto.getId_producto()));
            }
        }

        //Link cliente
        model.add(linkTo(methodOn(ClienteController.class).getClienteById(compra.getCliente().getCliente_id())).withRel("cliente"));

        //Link eliminar compra
        model.add(linkTo(methodOn(CompraController.class).deleteCompra(compra.getCompra_id())).withRel("eliminar-compra"));

        //Link todas las compras
        model.add(linkTo(methodOn(CompraController.class).getAllCompras()).withRel("todos"));

        return model;
    }
}
