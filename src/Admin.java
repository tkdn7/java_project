import java.util.List;

public class Admin extends User {
    private String department;

    public Admin() {
        super();
    }

    public Admin(String id, String password, String name, String phone, String department) {
        super(id, password, name, phone);
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    // 배송 상태 변경
    public void changeStatus(String trackingNumber, DeliveryStatus newStatus) {
        // TODO [5순위] DeliveryManager에서 운송장 찾아 setStatus(newStatus) 호출
        //   - 상태 역행 방지 로직 검토 (예: DELIVERED → READY 불가)
        //   - 상태 변경 이력을 남길지 결정 (Delivery TODO 3번과 연동)
    }

    // 전체 택배 조회
    public List<Delivery> viewAllDeliveries() {
        // TODO [5순위] DeliveryManager가 보관하는 전체 목록 반환
        //   - 정렬 기준(생성일, 상태별 등) 결정
        return null;
    }

    // 운송장 삭제
    public boolean deleteWaybill(String trackingNumber) {
        // TODO [5순위] DeliveryManager.remove(trackingNumber) 호출
        //   - 이미 배송완료된 건은 삭제 불가로 막을지 정책 결정
        //   - 성공/실패 boolean 반환 유지 vs 예외 throw 중 선택
        return false;
    }
}
