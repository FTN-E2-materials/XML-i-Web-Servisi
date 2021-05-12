package rs.ac.uns.ftn.kitchen.kitchenservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.kitchen.kitchenservice.client.OrderClient;
import rs.ac.uns.ftn.kitchen.kitchenservice.domain.*;
import rs.ac.uns.ftn.kitchen.kitchenservice.dto.TicketLineItemDTO;
import rs.ac.uns.ftn.kitchen.kitchenservice.dto.TicketLineItemsDTO;
import rs.ac.uns.ftn.kitchen.kitchenservice.exceptions.NotFoundException;
import rs.ac.uns.ftn.kitchen.kitchenservice.repository.MenuItemRepository;
import rs.ac.uns.ftn.kitchen.kitchenservice.repository.RestaurantRepository;
import rs.ac.uns.ftn.kitchen.kitchenservice.repository.TicketRepository;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;

    private final RestaurantRepository restaurantRepository;

    private final MenuItemRepository menuItemRepository;

    private final OrderClient orderClient;

    @Autowired
    public TicketService(
            TicketRepository ticketRepository,
            RestaurantRepository restaurantRepository,
            MenuItemRepository menuItemRepository,
            OrderClient orderClient
    ) {
        this.ticketRepository = ticketRepository;
        this.restaurantRepository = restaurantRepository;
        this.menuItemRepository = menuItemRepository;
        this.orderClient = orderClient;
    }

    public boolean verify(Long restaurantId, TicketLineItemsDTO items) {

        if (!this.restaurantRepository.existsById(restaurantId)) {
            throw new NotFoundException("Restaurant does not exist!");
        }

        for (TicketLineItemDTO tlidto : items.getItems()) {
            if (!this.menuItemRepository.existsByIdAndRestaurantId(tlidto.getMenuItemId(), restaurantId)) {
                throw new NotFoundException(String.format("Menu item %s does not exist in the restaurant!", tlidto.getMenuItemName()));
            }
        }

        return true;
    }

    public void create(Long restaurantId, Long orderId, TicketLineItemsDTO items) {

        Restaurant restaurant = this.restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new NotFoundException("Restaurant does not exist!"));

        Ticket ticket = new Ticket();
        ticket.setId(orderId);
        ticket.setRestaurant(restaurant);
        ticket.setTicketState(TicketState.PENDING);
        for (TicketLineItemDTO tlidto : items.getItems()) {
            MenuItem menuItem = this.menuItemRepository.findById(tlidto.getMenuItemId())
                    .orElseThrow(() -> new NotFoundException(String.format("Menu item %s does not exist in the restaurant!", tlidto.getMenuItemName())));
            ticket.add(new TicketLineItem(menuItem, tlidto.getQuantity()));
        }
        this.ticketRepository.save(ticket);
    }

    public Ticket update(Long ticketId, TicketState ticketState) {

        Ticket ticket = this.ticketRepository.findById(ticketId)
                .orElseThrow(() -> new NotFoundException("Ticket with that id does not exist!"));

        ticket.setTicketState(ticketState);
        this.orderClient.update(ticket.getId(), ticketState);
        return this.ticketRepository.save(ticket);
    }
}
