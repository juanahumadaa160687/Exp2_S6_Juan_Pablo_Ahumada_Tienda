package org.cslt.tienda.models;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;

@Entity
@Table(name = "ORDEN_COMPRA")

@Data

public class OrdenCompra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nro_orden;
    private Date fecha_orden;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "cliente_id", nullable = false, foreignKey = @ForeignKey(name = "FK_ORDEN_COMPRA_CLIENTE"))
    private Cliente cliente;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "compra_id", nullable = false, foreignKey = @ForeignKey(name = "FK_ORDEN_COMPRA_COMPRA"))
    private Compra compra;

    @OneToOne(fetch = FetchType.LAZY , cascade = CascadeType.REMOVE)
    @JoinColumn(name = "pago_id", nullable = false, foreignKey = @ForeignKey(name = "FK_ORDEN_COMPRA_PAGO"))
    private Pago pago;


}
