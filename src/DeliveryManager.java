import java.util.ArrayList;
import java.util.List;

// [역할] 메모리에서 배송 목록을 보관하고 조회/상태변경/삭제 처리
// 회원과 달리 배송 정보는 파일 저장 없이 메모리에서만 관리 → 프로그램 종료시 사라짐 (요구사항)
public class DeliveryManager {
    private List<Delivery> deliveries = new ArrayList<>();

    // 배송 추가 - 운송장 번호 중복시 예외
    public void addDelivery(Delivery delivery) {
        if (findByTrackingNumber(delivery.getTrackingNumber()) != null) {
            throw new IllegalArgumentException("이미 존재하는 운송장 번호입니다: " + delivery.getTrackingNumber());
        }
        deliveries.add(delivery);
    }

    // 운송장 번호로 검색, 없으면 null
    public Delivery findByTrackingNumber(String trackingNumber) {
        for (Delivery d : deliveries) {
            if (d.getTrackingNumber().equals(trackingNumber)) {
                return d;
            }
        }
        return null;
    }

    // 외부에서 내부 리스트가 직접 수정되지 않도록 복사본 반환
    public List<Delivery> getAllDeliveries() {
        return new ArrayList<>(deliveries);
    }

    // 상태 변경 - 없는 운송장이면 예외
    public void changeStatus(String trackingNumber, DeliveryStatus newStatus) {
        Delivery delivery = findByTrackingNumber(trackingNumber);
        if (delivery == null) {
            throw new IllegalArgumentException("존재하지 않는 운송장 번호입니다: " + trackingNumber);
        }
        delivery.setStatus(newStatus);
    }

    // 삭제 - 성공시 true, 없으면 false
    public boolean removeDelivery(String trackingNumber) {
        Delivery delivery = findByTrackingNumber(trackingNumber);
        if (delivery == null) {
            return false;
        }
        deliveries.remove(delivery);
        return true;
    }
}
