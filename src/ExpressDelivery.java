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
        // TODO [4순위] 긴급배송 요금 공식 구현
        //   예: (BASE_FEE + 무게요금 + 거리요금) * EXPRESS_MULTIPLIER
        //   - NormalDelivery와 중복 계산이 생기면 부모 Delivery 쪽에 protected 메서드로 빼는 것을 고려
        //   - 야간/주말 할증 같은 추가 옵션을 둘지 결정
        return 0;
    }

    public int getBaseFee() {
        return BASE_FEE;
    }

    public double getExpressMultiplier() {
        return EXPRESS_MULTIPLIER;
    }
}
