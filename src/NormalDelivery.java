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
        // TODO [4순위] 일반배송 요금 공식 구현
        //   예: BASE_FEE + (parcel.getWeight() * 무게단가) + (parcel.getDistance() * 거리단가)
        //   - 무게단가/거리단가는 상수로 추가
        //   - 결과를 int로 반환할지 BigDecimal/double로 바꿀지 결정 (돈 계산은 정밀도 주의)
        return 0;
    }

    public int getBaseFee() {
        return BASE_FEE;
    }
}
