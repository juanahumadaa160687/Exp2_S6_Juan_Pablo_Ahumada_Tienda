package org.cslt.tienda.models;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@Entity
@Table(name = "PAGO")

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_pago;

    @Column(length = 100, nullable = false, columnDefinition = "VARCHAR(100)")
    private String medio_pago;

    @Column(nullable = false, columnDefinition = "DECIMAL(10,2)")
    private Double total_pago;

    @Column(nullable = false, columnDefinition = "DATE")
    private Date fecha_pago;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "compra_id", foreignKey = @ForeignKey(name = "FK_PAGO_COMPRA"))
    private Compra compra;

}
