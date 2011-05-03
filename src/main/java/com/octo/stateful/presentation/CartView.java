package com.octo.stateful.presentation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

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
	@Inject
	CartGateway cartGateway;

	@PostConstruct
	public void findCart() {
		// search the cart of the current user
		// here, as we don't manager user, we create a new cart
		Cart currentCart = new Cart();
		HashSet<CartLine> lines = new HashSet<CartLine>();

		CartLine line1 = new CartLine();
		Product product1 = new Product();
		product1.setLabel("product1");
		product1.setPrice(10.0);
		line1.setQuantity(1);
		line1.setProduct(product1);
		lines.add(line1);

		CartLine line2 = new CartLine();
		Product product2 = new Product();
		product2.setLabel("product2");
		product2.setPrice(12.0);
		line2.setQuantity(1);
		line2.setProduct(product2);
		lines.add(line2);

		CartLine line3 = new CartLine();
		Product product3 = new Product();
		product3.setLabel("product3");
		product3.setPrice(15.0);
		line3.setQuantity(1);
		line3.setProduct(product3);
		lines.add(line3);

		currentCart.setLines(lines);

		cartGateway.create(currentCart);
		cartGateway.save();
	}

	public Cart getCurrentCart() {
		return cartGateway.getCurrent();
	}

	/**
	 * As the tags ui:repeat, p:DataGrid or p:DataList use only List, we have to
	 * transform Set to List
	 * 
	 * @return List of CartLines
	 */
	public List<CartLine> getLinesAsList() {
		return new ArrayList<CartLine>(cartGateway.getCurrent().getLines());
	}

	public void updateCart() {
		cartGateway.save();
	}

	@PreDestroy
	public void destroy() {
		cartGateway.closeGate();
	}
}
