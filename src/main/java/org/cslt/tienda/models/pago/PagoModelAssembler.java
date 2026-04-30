package org.cslt.tienda.models.pago;

import org.cslt.tienda.controllers.CompraController;
import org.cslt.tienda.controllers.PagoController;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class PagoModelAssembler implements RepresentationModelAssembler<Pago, PagoModel> {

    @Override
    public PagoModel toModel(Pago pago) {
        PagoModel pagoModel = new PagoModel();
        pagoModel.setId_pago(pago.getId_pago());
        pagoModel.setMedio_pago(pago.getMedio_pago());
        pagoModel.setTotal_pago(pago.getTotal_pago());
        pagoModel.setFecha_pago(pago.getFecha_pago());
        pagoModel.setCompra(pago.getCompra());

        //Self link
        pagoModel.add(linkTo(methodOn(PagoController.class).getPagoById(pago.getId_pago())).withSelfRel());

        //Link eliminar pago
        pagoModel.add(linkTo(methodOn(PagoController.class).deletePago(pago.getId_pago())).withRel("eliminar-pago"));

        //Link compra
        pagoModel.add(linkTo(methodOn(CompraController.class).getCompraById(pago.getCompra().getCompra_id())).withRel("compra"));

        //Link todos los pagos
        pagoModel.add(linkTo(methodOn(PagoController.class).getPagos()).withRel("all-pagos"));

        return pagoModel;

    }

}
