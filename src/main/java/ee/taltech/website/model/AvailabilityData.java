package ee.taltech.website.model;

public class AvailabilityData {

    private Long room;
    private String startDate;

    public AvailabilityData(Long roomId, String startDate, String endDate) {
        this.room = roomId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    private String endDate;

    public Long getRoomId() {
        return room;
    }

    public void setRoomId(Long roomId) {
        this.room = roomId;
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
