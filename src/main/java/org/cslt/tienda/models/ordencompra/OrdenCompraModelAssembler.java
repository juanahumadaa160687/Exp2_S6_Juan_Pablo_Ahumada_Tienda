package org.cslt.tienda.models.ordencompra;

import org.cslt.tienda.controllers.OrdenCompraController;
import org.cslt.tienda.controllers.PagoController;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class OrdenCompraModelAssembler implements RepresentationModelAssembler<OrdenCompra, OrdenCompraModel> {

    @Override
    public OrdenCompraModel toModel(OrdenCompra ordenCompra) {

        OrdenCompraModel ordenCompraModel = new OrdenCompraModel();
        ordenCompraModel.setId(ordenCompra.getId());
        ordenCompraModel.setFecha_orden(ordenCompra.getFecha_orden());
        ordenCompraModel.setPago(ordenCompra.getPago());

        //Self link
        ordenCompraModel.add(linkTo(methodOn(OrdenCompraController.class).getOrdenCompraById(ordenCompra.getId())).withSelfRel());

        //Link ver pago
        ordenCompraModel.add(linkTo(methodOn(PagoController.class).getPagoById(ordenCompra.getPago().getId_pago())).withRel("pago"));

        //Link eliminar orden de compra
        ordenCompraModel.add(linkTo(methodOn(OrdenCompraController.class).deleteOrdenCompraById(ordenCompra.getId())).withRel("eliminar-orden-compra"));

        //Link todas las órdenes de compra
        ordenCompraModel.add(linkTo(methodOn(OrdenCompraController.class).getAllOrdenesCompra()).withRel("todos"));

        return ordenCompraModel;
    }
}
