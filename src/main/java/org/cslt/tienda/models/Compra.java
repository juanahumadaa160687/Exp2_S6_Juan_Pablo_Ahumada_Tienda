package org.cslt.tienda.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "COMPRA")

@Data

public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "compra_seq", sequenceName = "compra_seq", allocationSize = 1, initialValue = 1111)
    private Long compra_id;

    @ManyToMany
    @JoinTable(
            name = "compra_producto",
            joinColumns = @JoinColumn(name = "compra_id"),
            inverseJoinColumns = @JoinColumn(name = "producto_id")
    )
    private List<Producto> producto;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "cliente_id", foreignKey = @ForeignKey(name = "FK_COMPRA_CLIENTE"))
    private Cliente cliente;

}
