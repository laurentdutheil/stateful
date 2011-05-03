package com.octo.stateful.facade;

import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import com.octo.stateful.domain.Cart;

/**
 * @author ldu Date: 02/05/11 09:35
 */
@Stateful
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class CartGateway {
	@PersistenceContext(type = PersistenceContextType.EXTENDED, name = "pu")
	EntityManager em;

	private Cart current;

	public Cart find(long id) {
		this.current = this.em.find(Cart.class, id);
		return this.current;
	}

	public Cart getCurrent() {
		return current;
	}

	public void create(Cart cart) {
		this.em.persist(cart);
		this.current = cart;
	}

	public void remove(long id) {
		Cart ref = this.em.getReference(Cart.class, id);
		this.em.remove(ref);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void save() {
		// nothing to do
	}

	@Remove
	public void closeGate() {
		// nothing to do
	}

}
