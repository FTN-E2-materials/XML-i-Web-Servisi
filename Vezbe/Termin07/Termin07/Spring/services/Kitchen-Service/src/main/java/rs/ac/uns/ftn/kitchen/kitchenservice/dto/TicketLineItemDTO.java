package rs.ac.uns.ftn.kitchen.kitchenservice.dto;

import rs.ac.uns.ftn.kitchen.kitchenservice.domain.TicketLineItem;

public class TicketLineItemDTO {

    private Long menuItemId;
    private String menuItemName;
    private int quantity;

    public TicketLineItemDTO() {}

    public TicketLineItemDTO(
            Long menuItemId,
            String menuItemName,
            int quantity
    ) {
        this.menuItemId = menuItemId;
        this.menuItemName = menuItemName;
        this.quantity = quantity;
    }

    public TicketLineItemDTO(
        TicketLineItem ticketLineItem
    ) {
        this.menuItemId = ticketLineItem.getItem().getId();
        this.menuItemName = ticketLineItem.getItem().getName();
        this.quantity = ticketLineItem.getQuantity();
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
