package com.octo.stateful.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * @author ldu Date: 29/04/11 17:41
 */
@Entity
public class Cart {
	@Id
	@GeneratedValue
	private Long id;

	// the CascadeType.ALL is here because we want to update the lines when we
	// update the cart
	@OneToMany(cascade = CascadeType.ALL)
	private Set<CartLine> lines;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<CartLine> getLines() {
		return lines;
	}

	public void setLines(Set<CartLine> lines) {
		this.lines = lines;
	}

	public Double getTotal() {
		Double total = 0.0;
		for (CartLine line : lines) {
			total += total + line.getTotal();
		}
		return total;
	}
}
