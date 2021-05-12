package rs.ac.uns.ftn.consumer.orderservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.consumer.orderservice.client.ConsumerClient;
import rs.ac.uns.ftn.consumer.orderservice.client.KitchenClient;
import rs.ac.uns.ftn.consumer.orderservice.domain.Order;
import rs.ac.uns.ftn.consumer.orderservice.domain.OrderStatus;
import rs.ac.uns.ftn.consumer.orderservice.dto.OrderLineItemsDTO;
import rs.ac.uns.ftn.consumer.orderservice.dto.OrderRequestDTO;
import rs.ac.uns.ftn.consumer.orderservice.exceptions.NotFoundException;
import rs.ac.uns.ftn.consumer.orderservice.repository.OrderRepository;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    private final ConsumerClient consumerClient;

    private final KitchenClient kitchenClient;

    @Autowired
    public OrderService(
            OrderRepository orderRepository,
            ConsumerClient consumerClient,
            KitchenClient kitchenClient
    ) {
        this.orderRepository = orderRepository;
        this.consumerClient = consumerClient;
        this.kitchenClient = kitchenClient;
    }

    public Order createOrder(OrderRequestDTO orderRequestDTO) {

        this.consumerClient.verify(orderRequestDTO.getConsumerId());
        this.kitchenClient.verify(orderRequestDTO.getRestaurantId(), new OrderLineItemsDTO(orderRequestDTO.getItems()));

        Order order = this.orderRepository.save(new Order(orderRequestDTO));
        this.kitchenClient.create(orderRequestDTO.getRestaurantId(), order.getId(), new OrderLineItemsDTO(orderRequestDTO.getItems()));
        return order;
    }

    public Order update(Long orderId, OrderStatus orderStatus) {

        Order order = this.orderRepository.findById(orderId)
                .orElseThrow(() -> new NotFoundException("Order with that id does not exist!"));

        order.setOrderStatus(orderStatus);
        return this.orderRepository.save(order);
    }
}
