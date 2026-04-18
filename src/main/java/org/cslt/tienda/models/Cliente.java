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

    private String nro_documento;
    private String name;
    private String phone;
    private String address;
    private String email;

}
