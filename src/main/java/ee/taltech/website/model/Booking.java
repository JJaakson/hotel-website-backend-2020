package ee.taltech.website.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Booking {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String startDate;
    private String endDate;
    private String paymentInfo;
    @OneToOne
    private Room room;

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Booking(Long id, String name, String startDate, String endDate, Room room, String paymentInfo) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.room = room;
        this.paymentInfo = paymentInfo;
    }

    public Booking(String name, String startDate, String endDate, Room room, String paymentInfo) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.room = room;
        this.paymentInfo = paymentInfo;
    }

    public Booking() {
    }

    public String getPaymentInfo() {
        return paymentInfo;
    }

    public void setPaymentInfo(String paymentInfo) {
        this.paymentInfo = paymentInfo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
