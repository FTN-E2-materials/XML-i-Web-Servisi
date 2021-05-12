package rs.ac.uns.ftn.consumer.consumerservice.domain;

import rs.ac.uns.ftn.consumer.consumerservice.dto.ConsumerRequestDTO;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Consumer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    public Consumer() {
    }

    public Consumer(ConsumerRequestDTO consumer) {
        this.email = consumer.getEmail();
        this.password = consumer.getPassword();
        this.name = consumer.getName();
        this.surname = consumer.getSurname();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
