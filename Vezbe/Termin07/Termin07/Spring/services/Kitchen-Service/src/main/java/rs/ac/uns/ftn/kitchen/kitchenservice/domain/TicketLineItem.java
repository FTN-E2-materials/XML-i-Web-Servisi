package rs.ac.uns.ftn.kitchen.kitchenservice.domain;


import javax.persistence.*;
import java.io.Serializable;

@Entity
public class TicketLineItem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private MenuItem item;

    @Column(nullable = false)
    private int quantity;

    public TicketLineItem() {}

    public TicketLineItem(
            MenuItem menuItem,
            int quantity
    ) {
        this.item = menuItem;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MenuItem getItem() {
        return item;
    }

    public void setItem(MenuItem item) {
        this.item = item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
