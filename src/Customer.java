public class Customer extends User {
    private String address;

    public Customer() {
        super();
    }

    public Customer(String id, String password, String name, String phone, String address) {
        super(id, password, name, phone);
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    // 운송장 조회: DeliveryManager에서 검색해 반환 (없으면 null)
    public Delivery viewWaybill(DeliveryManager deliveryManager, String trackingNumber) {
        return deliveryManager.findByTrackingNumber(trackingNumber);
    }

    // 배송 상태 확인: 운송장이 있으면 현재 상태, 없으면 null
    public DeliveryStatus checkStatus(DeliveryManager deliveryManager, String trackingNumber) {
        Delivery delivery = deliveryManager.findByTrackingNumber(trackingNumber);
        if (delivery == null) {
            return null;
        }
        return delivery.getStatus();
    }
}
