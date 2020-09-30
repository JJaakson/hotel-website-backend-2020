package ee.taltech.website.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class Booking {

    @Id
    @GeneratedValue
    private long id;
    //private Room room;
    private LocalDate startDate;
    private LocalDate endDate;

    public Booking(long id, Room room, LocalDate startDate, LocalDate endDate) {
        this.id = id;
        //this.room = room;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Booking() {
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    /*public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }*/

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
