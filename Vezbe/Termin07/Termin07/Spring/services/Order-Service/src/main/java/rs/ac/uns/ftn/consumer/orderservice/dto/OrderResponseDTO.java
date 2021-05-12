package rs.ac.uns.ftn.consumer.orderservice.dto;

import rs.ac.uns.ftn.consumer.orderservice.domain.Order;
import rs.ac.uns.ftn.consumer.orderservice.domain.OrderLineItem;
import rs.ac.uns.ftn.consumer.orderservice.domain.OrderStatus;

import java.util.ArrayList;
import java.util.List;

public class OrderResponseDTO {

    private Long id;
    private Long restaurantId;
    private Long consumerId;
    private OrderStatus orderStatus;
    private List<OrderLineItemDTO> items;

    public OrderResponseDTO() {
        this.items = new ArrayList<>();
    }

    public OrderResponseDTO(
            Long id,
            Long restaurantId,
            Long consumerId,
            OrderStatus orderStatus,
            List<OrderLineItemDTO> items) {
        this.id = id;
        this.restaurantId = restaurantId;
        this.consumerId = consumerId;
        this.orderStatus = orderStatus;
        this.items = items;
    }

    public OrderResponseDTO(
            Order order
    ) {
        this.id = order.getId();
        this.restaurantId = order.getRestaurantId();
        this.consumerId = order.getConsumerId();
        this.orderStatus = order.getOrderStatus();
        this.items = new ArrayList<>();
        for (OrderLineItem oli : order.getItems()) {
            this.items.add(new OrderLineItemDTO(oli));
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public List<OrderLineItemDTO> getItems() {
        return items;
    }

    public void setItems(List<OrderLineItemDTO> items) {
        this.items = items;
    }
}
