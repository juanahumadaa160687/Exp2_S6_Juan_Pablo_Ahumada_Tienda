package org.cslt.tienda.repositories;

import org.cslt.tienda.models.pago.Pago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PagoRepository extends JpaRepository<Pago, Long> {

    @Query("SELECT p FROM Pago p WHERE p.compra.compra_id = ?1")
    Pago findByCompra(Long compra_id);

}
