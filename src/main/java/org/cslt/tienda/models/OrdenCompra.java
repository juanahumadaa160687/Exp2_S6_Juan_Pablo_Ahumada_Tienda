package org.cslt.tienda.models;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;

@Entity
@Table(name = "ORDEN_COMPRA")

@Data

public class OrdenCompra {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "orden_compra_seq", sequenceName = "orden_compra_seq", allocationSize = 1, initialValue = 1000)
    private Long id;

    @Column(nullable = false, columnDefinition = "DATE")
    private Date fecha_orden;

    @OneToOne
    @JoinColumn(name = "pago_id", foreignKey = @ForeignKey(name = "FK_ORDEN_COMPRA_PAGO"))
    private Pago pago;

}
