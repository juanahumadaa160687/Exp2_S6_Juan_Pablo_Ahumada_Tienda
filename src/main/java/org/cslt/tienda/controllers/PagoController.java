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
        try {
            System.out.println("Obteniendo todos los pagos...");
            return pagoService.getAllPagos();
        }
        catch(Exception e){
            throw new RuntimeException("Error al obtener los pagos: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public Pago getPagoById(@PathVariable Long id){
        try {
            System.out.println("Pago obtenido: " + pagoService.getPagoById(id).getId_pago());
            return pagoService.getPagoById(id);
        }
        catch(Exception e){
            throw new RuntimeException("Error al obtener pago: " + e.getMessage());
        }
    }

    @PostMapping("/new")
    public Pago newPago(@RequestBody Pago pago){
        try {
            System.out.println("Recibido pago: " + pago.getId_pago());
            return pagoService.newPago(pago);
        }
        catch(Exception e){
            throw new RuntimeException("Error al crear el pago: " + e.getMessage());
        }
    }

    @PutMapping("/edit/{id}")
    public Pago editPago(@PathVariable Long id, @RequestBody Pago pago){
        try {
            System.out.println("Actualizando pago con ID: " + pago.getId_pago());
            return pagoService.updatePago(id, pago);
        }
        catch(Exception e){
            throw new RuntimeException("Error al editar pago: " + e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public void deletePagoById(@PathVariable Long id){
        try {
            System.out.println("Eliminando pago con ID: " + id);
            pagoService.deletePagoById(id);
        }
        catch(Exception e){
            throw new RuntimeException("Error al eliminar el pago: " + e.getMessage());
        }
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
