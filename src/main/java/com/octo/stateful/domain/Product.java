package com.octo.stateful.domain;

import javax.persistence.*;

/**
 * @author ldu
 *         Date: 29/04/11 17:31
 */
@Entity
@NamedQuery(name = "findAllProducts", query = "SELECT p FROM Product p")
public class Product {
    @Id
    @GeneratedValue
    private Long id;

    private String label;

    private Double price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
