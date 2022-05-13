package br.com.luciano.loja.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import br.com.luciano.loja.enumeration.StatusPedido;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "PEDIDO")
public class Pedido {
	
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "CLIENTE_ID")
	private Cliente cliente;

	@Column(name = "DATA_PEDIDO", length = 20, precision = 2)
	private LocalDate dataPedido = LocalDate.now();

	@Column(name = "TOTAL")
	private BigDecimal total;

	@Enumerated(EnumType.STRING)
	private StatusPedido status;

	@JsonIgnore
	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, fetch =FetchType.LAZY)
	private Set<ItemPedido> itens = new HashSet<>();

	@PrePersist
	private void adicionarItemPedido(){
		itens.forEach(ip -> ip.setPedido(this));
	}

}
