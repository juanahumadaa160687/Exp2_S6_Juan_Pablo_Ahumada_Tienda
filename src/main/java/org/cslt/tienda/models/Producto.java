package org.cslt.tienda.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "PRODUCTO")

@Data

public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_producto;

    private String nombre;
    private String descripcion;
    private Double precio;
    private Integer stock;

}
