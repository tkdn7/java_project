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

    // 운송장 조회
    public Delivery viewWaybill(String trackingNumber) {
        return null;
    }

    // 배송 상태 확인
    public DeliveryStatus checkStatus(String trackingNumber) {
        return null;
    }
}
