// [구현 순서 1순위 - 완료] 다른 클래스가 모두 참조하는 enum이므로 가장 먼저 확정.
// TODO: 7단계가 부족하다고 느껴지면 CANCELED(배송취소), RETURNED(반송) 등 예외 상태를 추가할지 검토
public enum DeliveryStatus {
    READY("상품준비"),
    PICKUP_STARTED("집화출발"),
    PICKED_UP("상품인수"),
    IN_TRANSIT("상품이동중"),
    ARRIVED("배송지도착"),
    OUT_FOR_DELIVERY("배송출발"),
    DELIVERED("배달완료");

    private final String label;

    DeliveryStatus(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
