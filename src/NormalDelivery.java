import java.time.LocalDateTime;

public class NormalDelivery extends Delivery {
    private static final int BASE_FEE = 3000;

    public NormalDelivery() {
        super();
    }

    public NormalDelivery(String trackingNumber, Customer sender, Parcel parcel,
                          DeliveryStatus status, LocalDateTime createdAt) {
        super(trackingNumber, sender, parcel, status, createdAt);
    }

    @Override
    public int calculateFee() {
        return 0;
    }

    public int getBaseFee() {
        return BASE_FEE;
    }
}
