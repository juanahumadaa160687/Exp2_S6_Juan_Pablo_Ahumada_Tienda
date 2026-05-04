package org.cslt.tienda.controllers;

import org.cslt.tienda.models.ordencompra.OrdenCompra;
import org.cslt.tienda.models.ordencompra.OrdenCompraModel;
import org.cslt.tienda.models.ordencompra.OrdenCompraModelAssembler;
import org.cslt.tienda.services.OrdenCompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/ordenes-compra")
@ComponentScan(basePackages = "org.cslt.tienda.models.ordencompra")
public class OrdenCompraController {

    @Autowired
    private OrdenCompraService ordenCompraService;

    @Autowired
    private PagoController pagoController;

    @Autowired
    private OrdenCompraModelAssembler ordenCompraModelAssembler;

    @GetMapping("/all")
    public CollectionModel<OrdenCompraModel> getAllOrdenesCompra(){

        List<OrdenCompra> ordenesCompra = ordenCompraService.getAllOrdenCompra();

        List<OrdenCompraModel> ordenCompraModels = ordenesCompra.stream()
                .map(ordenCompraModelAssembler::toModel)
                .toList();

        return CollectionModel.of(ordenCompraModels);
    }

    @GetMapping("/{id}")
    public EntityModel<OrdenCompraModel> getOrdenCompraById(@PathVariable Long id){

        OrdenCompra ordenCompra = ordenCompraService.getOrdenCompraById(id);
        OrdenCompraModel ordenCompraModel = ordenCompraModelAssembler.toModel(ordenCompra);

        return EntityModel.of(ordenCompraModel);

    }

    @PostMapping("/new/{compra_id}")
    public ResponseEntity<?> newOrdenCompra(@PathVariable Long compra_id){

        OrdenCompra ordenCompra = new OrdenCompra();

        ordenCompra.setFecha_orden(Date.valueOf(LocalDate.now()));
        ordenCompra.setPago(pagoController.getPagoByCompraId(compra_id));

        ordenCompraService.newOrdenCompra(ordenCompra);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteOrdenCompraById(@PathVariable Long id){

        ordenCompraService.deleteOrdenCompraById(id);

        return ResponseEntity.noContent().build();
    }

}
