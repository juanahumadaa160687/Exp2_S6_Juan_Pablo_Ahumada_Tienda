package org.cslt.tienda.controllers;

import org.cslt.tienda.models.pago.Pago;
import org.cslt.tienda.models.pago.PagoModel;
import org.cslt.tienda.models.pago.PagoModelAssembler;
import org.cslt.tienda.services.PagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pagos")
@ComponentScan(basePackages = "org.cslt.tienda.models.pago")
public class PagoController {

    @Autowired
    private PagoService pagoService;

    @Autowired
    private PagoModelAssembler pagoModelAssembler;

    @GetMapping("/all")
    public CollectionModel<PagoModel> getPagos(){

        List<Pago> pagos = pagoService.getAllPagos();

        List<PagoModel> pagoModels = pagos.stream()
                .map(pagoModelAssembler::toModel)
                .toList();

        return CollectionModel.of(pagoModels);
    }

    @GetMapping("/{id}")
    public EntityModel<PagoModel> getPagoById(@PathVariable Long id){

        Pago pago = pagoService.getPagoById(id);
        PagoModel pagoModel = pagoModelAssembler.toModel(pago);

        return EntityModel.of(pagoModel);
    }

    @PostMapping("/new")
    public ResponseEntity<?> newPago(@RequestBody Pago pago){

        pagoService.newPago(pago);
        PagoModel pagoModel = pagoModelAssembler.toModel(pago);

        return ResponseEntity.created(pagoModel.getRequiredLink("self").toUri()).body(pagoModel);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<PagoModel> editPago(@PathVariable Long id, @RequestBody Pago pago){

        Pago updatedPago = pagoService.updatePago(id, pago);
        PagoModel pagoModel = pagoModelAssembler.toModel(updatedPago);

        return ResponseEntity.ok(pagoModel);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletePago(@PathVariable Long id){

        pagoService.deletePagoById(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/compra/{compra_id}")
    public Pago getPagoByCompraId(@PathVariable Long compra_id){
        try {
            System.out.println("Obteniendo pago para compra con ID: " + compra_id);
            return pagoService.findByCompra(compra_id);
        }
        catch(Exception e){
            throw new RuntimeException("Error al obtener pago: " + e.getMessage());
        }
    }

}
