import java.time.LocalDateTime;

// [구현 순서 3순위] Customer, Parcel, DeliveryStatus가 모두 정해진 뒤 작업.
// TODO 1) 운송장 번호 자동 생성: 생성자에서 UUID 또는 "YYYYMMDD + 일련번호" 방식으로 trackingNumber 부여
//        → 별도 WaybillNumberGenerator 유틸 클래스로 분리하는 것을 권장
// TODO 2) createdAt 자동 초기화: 생성자에서 LocalDateTime.now() 기본값 세팅
// TODO 3) 상태 변경 이력(List<상태,시각>) 필드를 추가할지 검토 — 기초설계 "배송 상태 추적"을 강화하려면 필요
// TODO 4) calculateFee() 공식 확정: 예) BASE + (weight * 무게단가) + (distance * 거리단가)
//        → 공통 계산식은 여기서 protected 메서드로 만들고, 자식이 종류별 가중치만 적용하는 구조도 가능
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

    // 배송 종류별 배송비 계산 (하위 클래스에서 구현)
    public abstract int calculateFee();

    // 공통 요금 계산식: 기본료 + 무게요금 + 거리요금
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
