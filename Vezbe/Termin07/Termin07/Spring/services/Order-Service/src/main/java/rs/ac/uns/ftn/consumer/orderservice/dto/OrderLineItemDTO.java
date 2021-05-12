package rs.ac.uns.ftn.consumer.orderservice.dto;

import rs.ac.uns.ftn.consumer.orderservice.domain.OrderLineItem;

public class OrderLineItemDTO {

    private Long menuItemId;
    private String menuItemName;
    private int quantity;

    public OrderLineItemDTO() {}

    public OrderLineItemDTO(
            Long menuItemId,
            String menuItemName,
            int quantity
    ) {
        this.menuItemId = menuItemId;
        this.menuItemName = menuItemName;
        this.quantity = quantity;
    }

    public OrderLineItemDTO(
            OrderLineItem orderLineItem
    ) {
        this.menuItemId = orderLineItem.getMenuItemId();
        this.menuItemName = orderLineItem.getMenuItemName();
        this.quantity = orderLineItem.getQuantity();
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
