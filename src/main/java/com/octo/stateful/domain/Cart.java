package com.octo.stateful.domain;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author ldu
 *         Date: 29/04/11 17:41
 */
@Entity
public class Cart {
    @Id
    @GeneratedValue
    private Long id;

    @Transient
    private Map<Product, CartLine> mapLines = new HashMap<Product, CartLine>();

    @OneToMany(mappedBy = "cart")
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

    public void addProduct(Product product) {
        addProduct(1, product);
    }

    public void addProduct(Integer quantity, Product product) {
        if (mapLines.containsKey(product)) {
            mapLines.get(product).setQuantity(mapLines.get(product).getQuantity() + quantity);
        } else {
            CartLine cartLine = new CartLine();
            cartLine.setQuantity(quantity);
            cartLine.setProduct(product);
            lines.add(cartLine);
            mapLines.put(product, cartLine);
        }
    }

    public Double getTotal() {
        Double total = 0.0;
        for (CartLine line : mapLines.values()) {
            total += total + line.getTotal();
        }
        return total;
    }
}
