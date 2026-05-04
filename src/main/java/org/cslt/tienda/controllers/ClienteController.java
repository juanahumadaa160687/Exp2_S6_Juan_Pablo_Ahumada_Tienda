package org.cslt.tienda.controllers;

import org.cslt.tienda.models.cliente.Cliente;
import org.cslt.tienda.models.cliente.ClienteModel;
import org.cslt.tienda.models.cliente.ClienteModelAssembler;
import org.cslt.tienda.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/clientes")
@ComponentScan(basePackages = "org.cslt.tienda.models.cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ClienteModelAssembler clienteModelAssembler;

    @GetMapping("/all")
    public CollectionModel<ClienteModel> getAllClientes(){

        List<Cliente> clientes = clienteService.getAllClientes();

        List<ClienteModel> clienteModels = clientes.stream()
                .map(clienteModelAssembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(clienteModels);
    }

    @GetMapping("/{id}")
    public EntityModel<ClienteModel> getClienteById(@PathVariable Long id){

        Cliente cliente = clienteService.getClienteById(id);

        if(cliente == null){
            throw new RuntimeException("Cliente con ID " + id + " no encontrado");
        }

        else {
            return EntityModel.of(clienteModelAssembler.toModel(cliente));
        }
    }

    @PostMapping("/new")
    public ResponseEntity<?> createCliente(@RequestBody Cliente cliente){

        clienteService.newCliente(cliente);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<ClienteModel> updateCliente(@PathVariable Long id, @RequestBody Cliente cliente){

        Cliente updatedCliente = clienteService.updateCliente(id, cliente);
        ClienteModel clienteModel = clienteModelAssembler.toModel(updatedCliente);

        return ResponseEntity
                .ok(clienteModel);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCliente(@PathVariable Long id){

        clienteService.deleteClienteById(id);

        return ResponseEntity.noContent().build();
    }

}
