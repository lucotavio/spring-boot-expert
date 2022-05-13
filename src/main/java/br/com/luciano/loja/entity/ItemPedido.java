package br.com.luciano.loja.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "ITEM_PEDIDO")
public class ItemPedido {
	
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	
	@ManyToOne
	@JoinColumn(name = "PEDIDO_ID")
	private Pedido pedido;
	
	@ManyToOne
	@JoinColumn(name = "PRODUTO_ID")
	private Produto produto;
	
	@Column(name = "QUANTIDADE")
	private Integer quantidade;
	

}
