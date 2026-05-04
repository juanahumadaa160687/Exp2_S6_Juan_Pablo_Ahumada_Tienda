package org.cslt.tienda.controllers;

import org.cslt.tienda.models.compra.Compra;
import org.cslt.tienda.models.compra.CompraModel;
import org.cslt.tienda.models.compra.CompraModelAssembler;
import org.cslt.tienda.services.CompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/compras")
@ComponentScan(basePackages = "org.cslt.tienda.models.compra")
public class CompraController {

    @Autowired
    private CompraService compraService;

    @Autowired
    private CompraModelAssembler compraModelAssembler;

    @GetMapping("/all")
    public CollectionModel<CompraModel> getAllCompras() {

        List<Compra> compras = compraService.getAllCompras();

        List<CompraModel> compraModels = compras.stream()
                .map(compraModelAssembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(compraModels);
    }

    @GetMapping("/{id}")
    public EntityModel<CompraModel> getCompraById(@PathVariable Long id){

        Compra compra = compraService.findCompraById(id);
        CompraModel compraModel = compraModelAssembler.toModel(compra);

        return EntityModel.of(compraModel);
    }

    @PostMapping("/new")
    public ResponseEntity<CompraModel> newCompra(@RequestBody Compra compra){

        Compra newCompra = compraService.newCompra(compra);
        CompraModel compraModel = compraModelAssembler.toModel(newCompra);


        return ResponseEntity
                .ok(compraModel);

    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<CompraModel> editCompra(@PathVariable Long id, @RequestBody Compra compra){

        Compra updatedCompra = compraService.updateCompra(id, compra);
        CompraModel compraModel = compraModelAssembler.toModel(updatedCompra);

        return ResponseEntity
                .ok(compraModel);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCompra(@PathVariable Long id) {

        compraService.deleteCompraById(id);

        return ResponseEntity.noContent().build();

    }

}
