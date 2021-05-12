package rs.ac.uns.ftn.consumer.orderservice.domain;

import rs.ac.uns.ftn.consumer.orderservice.dto.OrderLineItemDTO;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class OrderLineItem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private Long menuItemId;

    @Column(nullable = false)
    private String menuItemName;

    @Column(nullable = false)
    private int quantity;

    public OrderLineItem() {}

    public OrderLineItem(
            OrderLineItemDTO orderLineItemDTO
    ) {
        this.menuItemId = orderLineItemDTO.getMenuItemId();
        this.menuItemName = orderLineItemDTO.getMenuItemName();
        this.quantity = orderLineItemDTO.getQuantity();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMenuItemId() {
        return menuItemId;
    }

    public void setMenuItemId(Long menuItemId) {
        this.menuItemId = menuItemId;
    }

    public String getMenuItemName() {
        return menuItemName;
    }

    public void setMenuItemName(String menuItemName) {
        this.menuItemName = menuItemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
