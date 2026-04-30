package org.cslt.tienda.models.pago;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.cslt.tienda.models.compra.Compra;
import org.springframework.hateoas.RepresentationModel;

import java.sql.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class PagoModel extends RepresentationModel<PagoModel> {

    private Long id_pago;

    private String medio_pago;

    private Double total_pago;

    private Date fecha_pago;

    private Compra compra;

}
