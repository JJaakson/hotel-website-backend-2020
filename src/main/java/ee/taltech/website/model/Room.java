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
    private RoomType roomType;
    private Integer amount;

    public Room(String name, RoomType roomType) {
        this.name = name;
        this.roomType = roomType;
    }

    public Room(Long id, String name, RoomType roomType, Integer amount) {
        this.name = name;
        this.id = id;
        this.roomType = roomType;
        this.amount = amount;
    }

    public Room() {
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
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
