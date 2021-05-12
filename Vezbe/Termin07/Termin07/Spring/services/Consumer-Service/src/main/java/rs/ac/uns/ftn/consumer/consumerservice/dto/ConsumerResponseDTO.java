package rs.ac.uns.ftn.consumer.consumerservice.dto;

import rs.ac.uns.ftn.consumer.consumerservice.domain.Consumer;

public class ConsumerResponseDTO {

    private Long id;
    private String email;
    private String name;
    private String surname;

    public ConsumerResponseDTO() {}

    public ConsumerResponseDTO(
            Long id,
            String email,
            String name,
            String surname
    ) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.surname = surname;
    }

    public ConsumerResponseDTO(Consumer consumer) {
        this.id = consumer.getId();
        this.email = consumer.getEmail();
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
