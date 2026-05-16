import java.time.LocalDateTime;

// [역할] 일반배송 - 할증 없이 공통 공식 그대로 적용
public class NormalDelivery extends Delivery {
    private static final int BASE_FEE = 3000;
    private static final int WEIGHT_RATE = 500;     // kg당 추가 요금
    private static final int DISTANCE_RATE = 100;   // km당 추가 요금

    public NormalDelivery() {
        super();
    }

    public NormalDelivery(String trackingNumber, Customer sender, Parcel parcel,
                          DeliveryStatus status, LocalDateTime createdAt) {
        super(trackingNumber, sender, parcel, status, createdAt);
    }

    @Override
    public int calculateFee() {
        return calculateBaseFee(BASE_FEE, WEIGHT_RATE, DISTANCE_RATE);
    }

    public int getBaseFee() {
        return BASE_FEE;
    }
}
