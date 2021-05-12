package rs.ac.uns.ftn.kitchen.kitchenservice.dto;

import rs.ac.uns.ftn.kitchen.kitchenservice.domain.Ticket;
import rs.ac.uns.ftn.kitchen.kitchenservice.domain.TicketLineItem;

import java.util.ArrayList;
import java.util.List;

public class TicketResponseDTO {

    private String restaurantName;
    private String ticketState;
    private List<TicketLineItemDTO> items;

    public TicketResponseDTO() {}

    public TicketResponseDTO(
            Ticket ticket
    ) {
        this.restaurantName = ticket.getRestaurant().getName();
        this.ticketState = ticket.getTicketState().toString();
        this.items = new ArrayList<>();
        for (TicketLineItem item : ticket.getItems()) {
            this.items.add(new TicketLineItemDTO(item));
        }
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getTicketState() {
        return ticketState;
    }

    public void setTicketState(String ticketState) {
        this.ticketState = ticketState;
    }

    public List<TicketLineItemDTO> getItems() {
        return items;
    }

    public void setItems(List<TicketLineItemDTO> items) {
        this.items = items;
    }
}
