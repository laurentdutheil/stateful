package com.octo.stateful.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 * @author ldu Date: 29/04/11 17:46
 */
@Entity
public class CartLine {
	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	private Cart cart;

	private Integer quantity;

	// CascadeType.ALL is here only for the POC, because we create a cart and
	// some products on the first load
	// in real life, the CascadeType.ALL is useless because the Products are
	// already created
	@OneToOne(cascade = CascadeType.ALL)
	private Product product;

	/**
	 * @return the total of the line
	 */
	public Double getTotal() {
		return quantity * product.getPrice();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

}
