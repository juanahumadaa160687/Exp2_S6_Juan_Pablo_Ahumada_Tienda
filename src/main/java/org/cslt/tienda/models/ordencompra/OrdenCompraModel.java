package org.cslt.tienda.models.ordencompra;

import lombok.*;
import org.cslt.tienda.models.pago.Pago;
import org.springframework.hateoas.RepresentationModel;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class OrdenCompraModel extends RepresentationModel<OrdenCompraModel> {

    private Long id;

    private Date fecha_orden;

    private Pago pago;

}
