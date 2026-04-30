package org.cslt.tienda.models.cliente;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class ClienteModel extends RepresentationModel<ClienteModel> {

    private Long cliente_id;

    private String nro_documento;

    private String name;

    private String phone;

    private String address;

    private String email;

}
