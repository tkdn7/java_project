import java.time.LocalDateTime;

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
