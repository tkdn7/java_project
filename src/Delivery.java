import java.time.LocalDateTime;

// [역할] 배송 정보의 추상 부모. 운송장 번호, 발송 고객, 택배 물품, 상태, 생성일시 보관
// 배송비 공식이 종류별로 다르기 때문에 calculateFee()는 추상으로 두고 자식이 구현
public abstract class Delivery {
    protected String trackingNumber;
    protected Customer sender;
    protected Parcel parcel;
    protected DeliveryStatus status;
    protected LocalDateTime createdAt;

    public Delivery() {

    }

    public Delivery(String trackingNumber, Customer sender, Parcel parcel,
                    DeliveryStatus status, LocalDateTime createdAt) {
        this.trackingNumber = trackingNumber;
        this.sender = sender;
        this.parcel = parcel;
        this.status = status;
        this.createdAt = createdAt;
    }

    // 배송 종류별 배송비 (자식이 구현)
    public abstract int calculateFee();

    // 공통 요금식 - 기본료 + 무게요금 + 거리요금
    // 실측 무게와 부피무게 중 큰 값으로 과금 (택배사 표준 방식)
    protected int calculateBaseFee(int baseFee, int weightRate, int distanceRate) {
        double chargeWeight = Math.max(parcel.getWeight(), parcel.getVolumeWeight());
        return baseFee
                + (int) (chargeWeight * weightRate)
                + (int) (parcel.getDistance() * distanceRate);
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public Customer getSender() {
        return sender;
    }

    public void setSender(Customer sender) {
        this.sender = sender;
    }

    public Parcel getParcel() {
        return parcel;
    }

    public void setParcel(Parcel parcel) {
        this.parcel = parcel;
    }

    public DeliveryStatus getStatus() {
        return status;
    }

    public void setStatus(DeliveryStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
