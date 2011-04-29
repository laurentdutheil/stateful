package com.octo.stateful.domain;

import javax.persistence.*;
import java.util.Map;

/**
 * @author ldu
 *         Date: 29/04/11 17:41
 */
@Entity
public class Cart {
    @Id
    @GeneratedValue
    private Long id;

    @MapKey(name = "product")
    @OneToMany
    private Map<Product, CartLine> lines;

    public void addProduct(Product product) {
        addProduct(1, product);
    }

    public void addProduct(Integer quantity, Product product) {
        if (lines.containsKey(product)) {
            lines.get(product).setQuantity(lines.get(product).getQuantity() + quantity);
        } else {
            CartLine cartLine = new CartLine();
            cartLine.setQuantity(quantity);
            cartLine.setProduct(product);
            lines.put(product, cartLine);
        }
    }
}
