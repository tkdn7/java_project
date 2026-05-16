import java.time.LocalDateTime;

// [역할] 긴급배송 - 일반배송보다 단가가 높고 합계에 1.5배 할증 적용
public class ExpressDelivery extends Delivery {
    private static final int BASE_FEE = 5000;
    private static final int WEIGHT_RATE = 700;      // 긴급은 단가 자체가 일반보다 높음
    private static final int DISTANCE_RATE = 150;
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
        // 합계 먼저 구한 다음 마지막에 배수 적용 (순서 바꾸면 1.5배 의미가 어긋남)
        int base = calculateBaseFee(BASE_FEE, WEIGHT_RATE, DISTANCE_RATE);
        return (int) Math.round(base * EXPRESS_MULTIPLIER);
    }

    public int getBaseFee() {
        return BASE_FEE;
    }

    public double getExpressMultiplier() {
        return EXPRESS_MULTIPLIER;
    }
}
