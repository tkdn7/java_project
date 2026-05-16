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

    // 배송 상태 변경 (DeliveryManager에 위임)
    public void changeStatus(DeliveryManager deliveryManager, String trackingNumber, DeliveryStatus newStatus) {
        deliveryManager.changeStatus(trackingNumber, newStatus);
    }

    // 전체 택배 조회
    public List<Delivery> viewAllDeliveries(DeliveryManager deliveryManager) {
        return deliveryManager.getAllDeliveries();
    }

    // 운송장 삭제: 성공 시 true, 없으면 false
    public boolean deleteWaybill(DeliveryManager deliveryManager, String trackingNumber) {
        return deliveryManager.removeDelivery(trackingNumber);
    }
}
