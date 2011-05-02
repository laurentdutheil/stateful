package com.octo.stateful.domain;

import javax.persistence.*;

/**
 * @author ldu
 *         Date: 29/04/11 17:46
 */
@Entity
public class CartLine {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Cart cart;

    private Integer quantity;

    @OneToOne
    @JoinColumn(name = "id")
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

}
