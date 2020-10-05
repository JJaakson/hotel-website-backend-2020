package ee.taltech.website.model;

public enum RoomType {
    STANDARD_SINGLE(4),
    STANDARD_DOUBLE(3),
    DELUXE(2);

    private final int amount;

    RoomType(final int newAmount) {
        amount = newAmount;
    }

    public int getAmount() { return amount; }
}
