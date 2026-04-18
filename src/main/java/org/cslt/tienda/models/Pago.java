package org.cslt.tienda.models;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;

@Entity
@Table(name = "PAGO")
@Data

public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_pago;

    private String medio_pago;
    private String total_pago;
    private Date fecha_pago;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "cliente_id", nullable = false, foreignKey = @ForeignKey(name = "FK_PAGO_CLIENTE"))
    private Cliente cliente;

    @OneToOne(fetch = FetchType.LAZY,  cascade = CascadeType.REMOVE)
    @JoinColumn(name = "compra_id", nullable = false, foreignKey = @ForeignKey(name = "FK_PAGO_COMPRA"))
    private Compra compra;

}
