package ee.taltech.website.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Room {

    @Id
    @GeneratedValue
    private long id;
    private String name;
    private int amount;

    public Room(String name, Long id, int amount) {
        this.name = name;
        this.id = id;
        this.amount = amount;
    }

    public Room() {
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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
