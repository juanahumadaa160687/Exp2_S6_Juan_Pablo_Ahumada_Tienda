package org.cslt.tienda.models.producto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ProductoModel extends RepresentationModel<ProductoModel> {

    private Long id_producto;

    private String nombre;

    private String descripcion;

    private Double precio;

    private int stock;

}
