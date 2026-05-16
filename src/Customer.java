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
        // TODO [5순위] DeliveryManager(또는 Repository)에서 trackingNumber로 검색해 반환
        //   - 본인이 sender인 운송장만 조회 가능하도록 권한 체크 필요
        //   - 없는 번호일 때 null vs Optional vs 예외 중 정책 결정
        return null;
    }

    // 배송 상태 확인
    public DeliveryStatus checkStatus(String trackingNumber) {
        // TODO [5순위] viewWaybill() 호출 후 getStatus() 반환하는 식으로 단순 구현 가능
        return null;
    }
}
