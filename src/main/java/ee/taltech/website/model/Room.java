package ee.taltech.website.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Room {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Integer amount;
    private Integer cost;

    public Room(String name, Integer amount, Integer cost) {
        this.name = name;
        this.amount = amount;
        this.cost = cost;
    }

    public Room(Long id, String name, Integer amount, Integer cost) {
        this.name = name;
        this.id = id;
        this.amount = amount;
        this.cost = cost;
    }

    public Room() {
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public Room setId(Long id) {
        this.id = id;
        return this;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
