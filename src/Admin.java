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
    }

    // 전체 택배 조회
    public List<Delivery> viewAllDeliveries() {
        return null;
    }

    // 운송장 삭제
    public boolean deleteWaybill(String trackingNumber) {
        return false;
    }
}
