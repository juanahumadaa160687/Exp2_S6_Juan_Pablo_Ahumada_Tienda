package org.cslt.tienda.models.compra;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.cslt.tienda.models.cliente.Cliente;
import org.cslt.tienda.models.producto.Producto;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class CompraModel extends RepresentationModel<CompraModel> {

    private Long compra_id;

    private List<Producto> producto;

    private Cliente cliente;

}
