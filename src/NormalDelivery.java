import java.time.LocalDateTime;

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
        // 부모의 공통 계산식 그대로 사용 (일반배송은 할증 없음)
        return calculateBaseFee(BASE_FEE, WEIGHT_RATE, DISTANCE_RATE);
    }

    public int getBaseFee() {
        return BASE_FEE;
    }
}
