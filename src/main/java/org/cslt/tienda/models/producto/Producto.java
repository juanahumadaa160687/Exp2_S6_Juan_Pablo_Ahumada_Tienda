package org.cslt.tienda.models.producto;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "PRODUCTO")

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_producto;

    @Column(nullable = false, columnDefinition = "VARCHAR(100)")
    private String nombre;

    @Column(columnDefinition = "VARCHAR(255)")
    private String descripcion;

    @Column(nullable = false, columnDefinition = "DECIMAL(10, 2)")
    private Double precio;

    @Column(nullable = false, columnDefinition = "INTEGER")
    private int stock;

}
