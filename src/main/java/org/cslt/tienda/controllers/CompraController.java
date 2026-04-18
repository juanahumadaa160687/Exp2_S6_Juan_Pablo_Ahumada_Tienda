package org.cslt.tienda.controllers;

import org.cslt.tienda.models.Compra;
import org.cslt.tienda.services.CompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/compras")
public class CompraController {

    @Autowired
    private CompraService compraService;

    @GetMapping("/all")
    public List<Compra> getAllCompras() {
        return compraService.getAllCompras();
    }

    @GetMapping("/{id}")
    public Compra getCompraById(@PathVariable Long id){
        return compraService.findCompraById(id);
    }

    @PostMapping("/new")
    public Compra newCompra(@RequestBody Compra compra){
        return compraService.newCompra(compra);
    }

    @PutMapping("/edit/{id}")
    public Compra editCompra(@PathVariable Long id, @RequestBody Compra compra){
        return compraService.updateCompra(id, compra);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCompra(@PathVariable Long id) {
        compraService.deleteCompraById(id);
    }

}
