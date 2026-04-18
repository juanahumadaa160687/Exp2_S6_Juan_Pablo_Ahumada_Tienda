package org.cslt.tienda.controllers;

import org.cslt.tienda.models.OrdenCompra;
import org.cslt.tienda.services.OrdenCompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ordenes-compra")
public class OrdenCompraController {

    @Autowired
    OrdenCompraService ordenCompraService;

    @GetMapping("/all")
    public List<OrdenCompra> getAllOrdenCompra(){
        return ordenCompraService.getAllOrdenCompra();
    }

    @GetMapping("/{id}")
    public OrdenCompra getOrdenCompraById(@PathVariable Long id){
        return ordenCompraService.getOrdenCompraById(id);
    }

    @PostMapping("/new")
    public OrdenCompra newOrdenCompra(@RequestBody OrdenCompra ordenCompra) {
        return ordenCompraService.newOrdenCompra(ordenCompra);
    }

    @PutMapping("/edit/{id}")
    public OrdenCompra updateOrdenCompra(@PathVariable Long id, @RequestBody OrdenCompra ordenCompra) {
        return ordenCompraService.updateOrdenCompra(id, ordenCompra);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteOrdenCompraById(@PathVariable Long id){
        ordenCompraService.deleteOrdenCompraById(id);
    }

}
