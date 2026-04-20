package org.cslt.tienda.models;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "CLIENTE")

@Data
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cliente_id;

    @Column(nullable = false, length = 20, columnDefinition = "VARCHAR(20)")
    private String nro_documento;

    @Column(nullable = false, length = 100, columnDefinition = "VARCHAR(100)")
    private String name;

    @Column(nullable = false, length = 20, columnDefinition = "VARCHAR(20)")
    private String phone;

    @Column(nullable = false, columnDefinition = "VARCHAR(255)")
    private String address;

    @Column(nullable = false, columnDefinition = "VARCHAR(255)")
    private String email;

}
