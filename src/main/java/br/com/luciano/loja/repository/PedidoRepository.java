package br.com.luciano.loja.repository;

import br.com.luciano.loja.enumeration.StatusPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import br.com.luciano.loja.entity.Pedido;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PedidoRepository extends JpaRepository<Pedido, Integer>{

    @Query("SELECT p FROM Pedido p " +
            "LEFT JOIN FETCH p.itens i " +
            "WHERE p.id = :id")
    Pedido findByIdFetchItens(@Param("id") Integer id);

    @Query("UPDATE Pedido p SET p.status = :status WHERE p.id = :id")
    void atualizarStatus(@Param("id") Integer id, @Param("status") StatusPedido status);
}
