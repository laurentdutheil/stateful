package com.octo.stateful.facade;

import com.octo.stateful.domain.Product;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author ldu
 *         Date: 02/05/11 11:08
 */
@Stateless
public class ProductService {
    @PersistenceContext(name = "pu")
	private EntityManager em;

	public Product findProductById(Long id) {
		return em.find(Product.class, id);
	}

	public Product createProduct(Product product) {
		em.persist(product);
		return product;
	}

	public Product updateProduct(Product product) {
		em.merge(product);
		return product;
	}

	public List<Product> getAllProducts() {
		return em.createNamedQuery("findAllProducts").getResultList();
	}
}
