package rs.ac.uns.ftn.consumer.orderservice.domain;

import rs.ac.uns.ftn.consumer.orderservice.dto.OrderLineItemDTO;
import rs.ac.uns.ftn.consumer.orderservice.dto.OrderRequestDTO;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "order_table")
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private Long restaurantId;

    @Column(nullable = false)
    private Long consumerId;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderLineItem> items;

    public Order() {
        this.items = new ArrayList<>();
    }

    public Order(OrderRequestDTO orderRequestDTO) {
        this.restaurantId = orderRequestDTO.getRestaurantId();
        this.consumerId = orderRequestDTO.getConsumerId();
        this.orderStatus = OrderStatus.PENDING;
        this.items = new ArrayList<>();
        for (OrderLineItemDTO olidto : orderRequestDTO.getItems()) {
            this.items.add(new OrderLineItem(olidto));
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

    public Long getConsumerId() {
        return consumerId;
    }

    public void setConsumerId(Long consumerId) {
        this.consumerId = consumerId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public List<OrderLineItem> getItems() {
        return items;
    }

    public void setItems(List<OrderLineItem> items) {
        this.items = items;
    }
}
