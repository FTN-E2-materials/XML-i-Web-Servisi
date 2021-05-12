package rs.ac.uns.ftn.consumer.orderservice.dto;

import java.util.ArrayList;
import java.util.List;

public class OrderRequestDTO {

    private Long restaurantId;
    private Long consumerId;
    private List<OrderLineItemDTO> items;

    public OrderRequestDTO() {
        this.items = new ArrayList<>();
    }

    public OrderRequestDTO(
            Long restaurantId,
            Long consumerId,
            List<OrderLineItemDTO> items
    ) {
        this.restaurantId = restaurantId;
        this.consumerId = consumerId;
        this.items = items;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Long getConsumerId() {
        return consumerId;
    }

    public void setConsumerId(Long consumerId) {
        this.consumerId = consumerId;
    }

    public List<OrderLineItemDTO> getItems() {
        return items;
    }

    public void setItems(List<OrderLineItemDTO> items) {
        this.items = items;
    }
}
