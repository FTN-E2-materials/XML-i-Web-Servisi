package rs.ac.uns.ftn.kitchen.kitchenservice.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Ticket implements Serializable {

    @Id
    private Long id;

    @OneToOne
    private Restaurant restaurant;

    @Enumerated(EnumType.STRING)
    private TicketState ticketState;

    @OneToMany(cascade = CascadeType.ALL)
    private List<TicketLineItem> items;

    public Ticket() {
        this.items = new ArrayList<>();
    }

    public void add(TicketLineItem tli) {
        this.items.add(tli);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public TicketState getTicketState() {
        return ticketState;
    }

    public void setTicketState(TicketState ticketState) {
        this.ticketState = ticketState;
    }

    public List<TicketLineItem> getItems() {
        return items;
    }

    public void setItems(List<TicketLineItem> items) {
        this.items = items;
    }
}
