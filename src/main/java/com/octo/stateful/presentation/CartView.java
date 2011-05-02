package com.octo.stateful.presentation;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.octo.stateful.domain.Cart;
import com.octo.stateful.domain.CartLine;
import com.octo.stateful.domain.Product;
import com.octo.stateful.facade.CartGateway;

/**
 * @author ldu Date: 02/05/11 13:13
 */
@ManagedBean(name = "cart")
@SessionScoped
public class CartView {
	@EJB
	CartGateway cartGateway;

	@PostConstruct
	public void findCart() {
		// search the cart of the current user
		// here, as we don't manager user, we create a new cart
		Cart currentCart = new Cart();
		currentCart.setLines(new ArrayList<CartLine>());
		Product product1 = new Product();
		product1.setLabel("product1");
		product1.setPrice(10.0);
		currentCart.addProduct(product1);
		Product product2 = new Product();
		product2.setLabel("product2");
		product2.setPrice(12.0);
		currentCart.addProduct(product2);
		Product product3 = new Product();
		product3.setLabel("product3");
		product3.setPrice(15.0);
		currentCart.addProduct(product3);

		cartGateway.create(currentCart);
		cartGateway.save();
	}

	public Cart getCurrentCart() {
		return cartGateway.getCurrent();
	}

	public void updateCart() {
		cartGateway.save();
	}
}
