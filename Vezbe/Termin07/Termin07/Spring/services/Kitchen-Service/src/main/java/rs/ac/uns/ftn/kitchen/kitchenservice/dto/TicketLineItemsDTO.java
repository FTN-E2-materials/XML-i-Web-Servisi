package rs.ac.uns.ftn.kitchen.kitchenservice.dto;

import java.util.ArrayList;
import java.util.List;

public class TicketLineItemsDTO {

    private List<TicketLineItemDTO> items;

    public TicketLineItemsDTO() {
        this.items = new ArrayList<>();
    }

    public TicketLineItemsDTO(
            List<TicketLineItemDTO> items
    ) {
        this.items = items;
    }

    public List<TicketLineItemDTO> getItems() {
        return items;
    }

    public void setItems(List<TicketLineItemDTO> items) {
        this.items = items;
    }
}
