package org.cslt.tienda.controllers;

import org.cslt.tienda.models.Pago;
import org.cslt.tienda.services.PagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pagos")
public class PagoController {

    @Autowired
    private PagoService pagoService;

    @GetMapping("/all")
    public List<Pago> getAllPagos(){
        return pagoService.getAllPagos();
    }

    @GetMapping("/{id}")
    public Pago getPagoById(@PathVariable Long id){
        return pagoService.getPagoById(id);
    }

    @PostMapping("/new")
    public Pago newPago(@RequestBody Pago pago){
        return pagoService.newPago(pago);
    }

    @PutMapping("/edit/{id}")
    public Pago editPago(@PathVariable Long id, @RequestBody Pago pago){
        return pagoService.updatePago(id, pago);
    }

    @DeleteMapping("/delete/{id}")
    public void deletePagoById(@PathVariable Long id){
        pagoService.deletePagoById(id);
    }

}
