package org.cslt.tienda.services;

import org.cslt.tienda.models.cliente.Cliente;
import org.cslt.tienda.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    @Override
    public List<Cliente> getAllClientes() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente getClienteById(Long id) {
        return clienteRepository.findById(id).orElse(null);
    }

    @Override
    public Cliente newCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public Cliente updateCliente(Long id, Cliente cliente) {

        Cliente updateCliente = clienteRepository.findById(id).orElse(null);

        if(updateCliente != null){
            updateCliente.setName(cliente.getName());
            updateCliente.setEmail(cliente.getEmail());
            updateCliente.setNro_documento(cliente.getNro_documento());
            updateCliente.setPhone(cliente.getPhone());
            updateCliente.setAddress(cliente.getAddress());

            clienteRepository.save(updateCliente);

            return updateCliente;
        }
        return null;
    }

    @Override
    public void deleteClienteById(Long id) {
        clienteRepository.deleteById(id);
    }

}
