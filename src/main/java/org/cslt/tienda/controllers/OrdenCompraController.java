package org.cslt.tienda.controllers;

import org.cslt.tienda.models.OrdenCompra;
import org.cslt.tienda.services.OrdenCompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/ordenes-compra")
public class OrdenCompraController {

    @Autowired
    OrdenCompraService ordenCompraService;

    @Autowired
    PagoController pagoController;

    @GetMapping("/all")
    public List<OrdenCompra> getAllOrdenCompra(){
        try {
            System.out.println("Obteniendo todas las ordenes de compra...");
            return ordenCompraService.getAllOrdenCompra();
        }
        catch (Exception e){
            throw new RuntimeException("Error al obtener las ordenes de compra: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public OrdenCompra getOrdenCompraById(@PathVariable Long id){
        try {
            System.out.println("Orden de compra obtenida: " + ordenCompraService.getOrdenCompraById(id).getId());
            return ordenCompraService.getOrdenCompraById(id);
        }
        catch (Exception e){
            throw new RuntimeException("Error al obtener la orden de compra: " + e.getMessage());
        }
    }

    @PostMapping("/new/{compra_id}")
    public OrdenCompra newOrdenCompra(@PathVariable Long compra_id){
        try {

            OrdenCompra ordenCompra = new OrdenCompra();

            ordenCompra.setFecha_orden(Date.valueOf(LocalDate.now()));
            ordenCompra.setPago(pagoController.getPagoByCompraId(compra_id));

            System.out.println("Recibida orden de compra para compra: " + compra_id);

            return ordenCompraService.newOrdenCompra(ordenCompra);
        }
        catch (Exception e){
            throw new RuntimeException("Error al obtener la orden de compra: " + e.getMessage());
        }

    }

    @DeleteMapping("/delete/{id}")
    public void deleteOrdenCompraById(@PathVariable Long id){
        try {
            System.out.println("Eliminando orden de compra con ID: " + id);
            ordenCompraService.deleteOrdenCompraById(id);
        }
        catch (Exception e){
            throw new RuntimeException("Error al obtener la orden de compra: " + e.getMessage());
        }
    }

}
