import java.time.LocalDateTime;

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
        // (기본료 + 무게요금 + 거리요금) 전체에 긴급 배수 적용
        // 캐스팅 순서 주의: 합을 먼저 구하고 마지막에 곱해야 의도대로 1.5배가 됨
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
