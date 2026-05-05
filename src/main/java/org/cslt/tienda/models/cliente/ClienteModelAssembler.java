package org.cslt.tienda.models.cliente;

import org.cslt.tienda.controllers.ClienteController;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ClienteModelAssembler implements RepresentationModelAssembler<Cliente, ClienteModel> {

    @Override
    public ClienteModel toModel(Cliente cliente) {

        ClienteModel clienteModel = new ClienteModel();

        clienteModel.setCliente_id(cliente.getCliente_id());
        clienteModel.setNro_documento(cliente.getNro_documento());
        clienteModel.setName(cliente.getName());
        clienteModel.setPhone(cliente.getPhone());
        clienteModel.setAddress(cliente.getAddress());
        clienteModel.setEmail(cliente.getEmail());

        //Link Self
        clienteModel.add(linkTo(methodOn(ClienteController.class).getClienteById(cliente.getCliente_id())).withSelfRel());

        //Link eliminar cliente
        clienteModel.add(linkTo(methodOn(ClienteController.class).deleteCliente(cliente.getCliente_id())).withRel("eliminar-cliente"));

        //Link all-clientes
        clienteModel.add(linkTo(methodOn(ClienteController.class).getAllClientes()).withRel("todos"));

        return clienteModel;
    }
}
