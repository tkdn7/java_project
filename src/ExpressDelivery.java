import java.time.LocalDateTime;

public class ExpressDelivery extends Delivery {
    private static final int BASE_FEE = 5000;
    private static final double EXPRESS_MULTIPLIER = 1.5;

    public ExpressDelivery() {
        super();
    }

    public ExpressDelivery(String trackingNumber, Customer sender, Parcel parcel,
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

    public double getExpressMultiplier() {
        return EXPRESS_MULTIPLIER;
    }
}
