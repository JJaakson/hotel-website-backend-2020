package ee.taltech.website.dto;

public class RoomDto {

    private Long id;
    private String name;
    private Integer amount;
    private Integer cost;

    public RoomDto(Long id, String name, Integer amount, Integer cost) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.cost = cost;
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

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
