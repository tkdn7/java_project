import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

// [역할] 콘솔 진입점. Scanner로 사용자 입력을 받아 시작 메뉴 -> 로그인 -> Customer/Admin 메뉴 분기 진행
// 회원 정보는 users.txt에 영속 저장되고, 배송 정보는 메모리에서만 관리됨 (종료시 사라짐)
public class Main {
    private static final Scanner in = new Scanner(System.in);
    private static final UserManager userManager = new UserManager();
    private static final DeliveryManager deliveryManager = new DeliveryManager();

    public static void main(String[] args) {
        while (true) {
            showMainMenu();
            String choice = in.nextLine().trim();
            switch (choice) {
                case "1":
                    signUp();
                    break;
                case "2":
                    login();
                    break;
                case "3":
                    showAllUsers();
                    break;
                case "0":
                    System.out.println("프로그램을 종료합니다.");
                    return;
                default:
                    System.out.println("잘못된 선택입니다. 0~3 사이로 입력해주세요.");
            }
        }
    }

    // ================= 시작 메뉴 =================

    private static void showMainMenu() {
        System.out.println("\n========================");
        System.out.println("   택배 운송 시스템");
        System.out.println("========================");
        System.out.println("1. 회원가입");
        System.out.println("2. 로그인");
        System.out.println("3. 전체 회원 목록 조회");
        System.out.println("0. 종료");
        System.out.print("선택: ");
    }

    // ================= 회원가입 =================

    private static void signUp() {
        System.out.println("\n=== 회원가입 ===");
        System.out.println("1. 고객 (Customer)");
        System.out.println("2. 관리자 (Admin)");
        System.out.print("회원 종류 선택: ");
        String type = in.nextLine().trim();

        System.out.print("ID: ");
        String id = in.nextLine().trim();
        System.out.print("비밀번호 (6자 이상): ");
        String pw = in.nextLine().trim();
        System.out.print("이름: ");
        String name = in.nextLine().trim();
        System.out.print("전화번호: ");
        String phone = in.nextLine().trim();

        try {
            if (type.equals("1")) {
                System.out.print("주소: ");
                String address = in.nextLine().trim();
                userManager.addUser(new Customer(id, pw, name, phone, address));
                System.out.println("회원가입 성공! (고객: " + id + ")");
            } else if (type.equals("2")) {
                System.out.print("소속 부서: ");
                String department = in.nextLine().trim();
                userManager.addUser(new Admin(id, pw, name, phone, department));
                System.out.println("회원가입 성공! (관리자: " + id + ")");
            } else {
                System.out.println("잘못된 회원 종류입니다.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("회원가입 실패: " + e.getMessage());
        }
    }

    // ================= 로그인 =================

    private static void login() {
        System.out.println("\n=== 로그인 ===");
        System.out.print("ID: ");
        String id = in.nextLine().trim();
        System.out.print("비밀번호: ");
        String pw = in.nextLine().trim();

        LoginResult result = userManager.login(id, pw);
        switch (result) {
            case SUCCESS:
                User user = userManager.getUserById(id);
                System.out.println("로그인 성공: " + user.getName() + "님 환영합니다.");
                // instanceof로 회원 타입에 따라 다른 메뉴로 분기
                if (user instanceof Admin) {
                    adminMenu((Admin) user);
                } else if (user instanceof Customer) {
                    customerMenu((Customer) user);
                }
                break;
            case ID_NOT_FOUND:
                System.out.println("로그인 실패: 존재하지 않는 ID 입니다.");
                break;
            case WRONG_PASSWORD:
                System.out.println("로그인 실패: 비밀번호가 일치하지 않습니다.");
                break;
        }
    }

    // ================= 회원 목록 =================

    private static void showAllUsers() {
        List<User> users = userManager.getAllUsers();
        if (users.isEmpty()) {
            System.out.println("등록된 회원이 없습니다.");
            return;
        }
        System.out.println("\n=== 전체 회원 목록 ===");
        for (User u : users) {
            String type = (u instanceof Admin) ? "관리자" : "고객";
            System.out.println("- [" + type + "] " + u.getId() + " / " + u.getName() + " / " + u.getPhone());
        }
    }

    // ================= 고객 메뉴 =================

    private static void customerMenu(Customer customer) {
        while (true) {
            System.out.println("\n--- 고객 메뉴 (" + customer.getName() + ") ---");
            System.out.println("1. 택배 접수");
            System.out.println("2. 운송장 번호로 배송 조회");
            System.out.println("3. 배송 상태 확인");
            System.out.println("4. 배송비 계산 결과 보기");
            System.out.println("0. 로그아웃");
            System.out.print("선택: ");
            String choice = in.nextLine().trim();
            switch (choice) {
                case "1":
                    createDelivery(customer);
                    break;
                case "2":
                    customerViewDelivery(customer);
                    break;
                case "3":
                    customerCheckStatus(customer);
                    break;
                case "4":
                    customerShowFee(customer);
                    break;
                case "0":
                    System.out.println("로그아웃 되었습니다.");
                    return;
                default:
                    System.out.println("잘못된 선택입니다.");
            }
        }
    }

    private static void createDelivery(Customer customer) {
        System.out.println("\n=== 택배 접수 ===");
        try {
            System.out.print("운송장 번호: ");
            String trackingNumber = in.nextLine().trim();
            System.out.print("물품명: ");
            String contents = in.nextLine().trim();
            System.out.print("무게(kg): ");
            double weight = Double.parseDouble(in.nextLine().trim());
            System.out.print("거리(km): ");
            double distance = Double.parseDouble(in.nextLine().trim());
            System.out.print("송신자 이름: ");
            String senderName = in.nextLine().trim();
            System.out.print("송신자 주소: ");
            String senderAddress = in.nextLine().trim();
            System.out.print("수신자 이름: ");
            String receiverName = in.nextLine().trim();
            System.out.print("수신자 주소: ");
            String receiverAddress = in.nextLine().trim();
            System.out.print("가로(cm): ");
            double width = Double.parseDouble(in.nextLine().trim());
            System.out.print("세로(cm): ");
            double length = Double.parseDouble(in.nextLine().trim());
            System.out.print("높이(cm): ");
            double height = Double.parseDouble(in.nextLine().trim());
            System.out.print("배송 종류 [1: 일반배송 / 2: 긴급배송]: ");
            String deliveryType = in.nextLine().trim();

            Parcel parcel = new Parcel(contents, weight, distance,
                    senderName, senderAddress, receiverName, receiverAddress);
            parcel.setWidth(width);
            parcel.setLength(length);
            parcel.setHeight(height);

            Delivery delivery;
            if (deliveryType.equals("2")) {
                delivery = new ExpressDelivery(trackingNumber, customer, parcel,
                        DeliveryStatus.READY, LocalDateTime.now());
            } else if (deliveryType.equals("1")) {
                delivery = new NormalDelivery(trackingNumber, customer, parcel,
                        DeliveryStatus.READY, LocalDateTime.now());
            } else {
                System.out.println("잘못된 배송 종류입니다. (1 또는 2)");
                return;
            }

            deliveryManager.addDelivery(delivery);
            System.out.println("접수 완료! 운송장 번호: " + delivery.getTrackingNumber());
            System.out.println("배송비: " + delivery.calculateFee() + "원");
        } catch (NumberFormatException e) {
            System.out.println("숫자 입력이 잘못되었습니다.");
        } catch (IllegalArgumentException e) {
            System.out.println("접수 실패: " + e.getMessage());
        }
    }

    private static void customerViewDelivery(Customer customer) {
        Delivery delivery = customer.viewWaybill(deliveryManager, promptTrackingNumber());
        if (delivery == null) {
            printTrackingNotFound();
            return;
        }
        printDelivery(delivery);
    }

    private static void customerCheckStatus(Customer customer) {
        DeliveryStatus status = customer.checkStatus(deliveryManager, promptTrackingNumber());
        if (status == null) {
            printTrackingNotFound();
            return;
        }
        System.out.println("현재 배송 상태: " + status.getLabel());
    }

    private static void customerShowFee(Customer customer) {
        Delivery delivery = customer.viewWaybill(deliveryManager, promptTrackingNumber());
        if (delivery == null) {
            printTrackingNotFound();
            return;
        }
        String type = (delivery instanceof ExpressDelivery) ? "긴급배송" : "일반배송";
        System.out.println(type + " 배송비: " + delivery.calculateFee() + "원");
    }

    // ================= 관리자 메뉴 =================

    private static void adminMenu(Admin admin) {
        while (true) {
            System.out.println("\n--- 관리자 메뉴 (" + admin.getName() + ") ---");
            System.out.println("1. 전체 배송 조회");
            System.out.println("2. 배송 상태 변경");
            System.out.println("3. 운송장 삭제");
            System.out.println("4. 전체 회원 조회");
            System.out.println("0. 로그아웃");
            System.out.print("선택: ");
            String choice = in.nextLine().trim();
            switch (choice) {
                case "1":
                    adminShowAllDeliveries(admin);
                    break;
                case "2":
                    adminChangeStatus(admin);
                    break;
                case "3":
                    adminDeleteWaybill(admin);
                    break;
                case "4":
                    showAllUsers();
                    break;
                case "0":
                    System.out.println("로그아웃 되었습니다.");
                    return;
                default:
                    System.out.println("잘못된 선택입니다.");
            }
        }
    }

    private static void adminShowAllDeliveries(Admin admin) {
        List<Delivery> deliveries = admin.viewAllDeliveries(deliveryManager);
        if (deliveries.isEmpty()) {
            System.out.println("등록된 배송이 없습니다.");
            return;
        }
        System.out.println("\n=== 전체 배송 목록 ===");
        for (Delivery d : deliveries) {
            printDelivery(d);
        }
    }

    private static void adminChangeStatus(Admin admin) {
        String trackingNumber = promptTrackingNumber();
        System.out.println("변경할 상태 선택:");
        DeliveryStatus[] statuses = DeliveryStatus.values();
        for (int i = 0; i < statuses.length; i++) {
            System.out.println((i + 1) + ". " + statuses[i].getLabel());
        }
        System.out.print("선택: ");
        try {
            int idx = Integer.parseInt(in.nextLine().trim()) - 1;
            if (idx < 0 || idx >= statuses.length) {
                System.out.println("잘못된 번호입니다.");
                return;
            }
            admin.changeStatus(deliveryManager, trackingNumber, statuses[idx]);
            System.out.println("상태 변경 완료: " + statuses[idx].getLabel());
        } catch (NumberFormatException e) {
            System.out.println("숫자를 입력해주세요.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void adminDeleteWaybill(Admin admin) {
        String trackingNumber = promptTrackingNumber();
        if (admin.deleteWaybill(deliveryManager, trackingNumber)) {
            System.out.println("운송장 삭제 완료: " + trackingNumber);
        } else {
            printTrackingNotFound();
        }
    }

    // ================= 공용 헬퍼 =================

    // 운송장 번호 입력받는 부분이 5곳에서 반복되어 분리
    private static String promptTrackingNumber() {
        System.out.print("운송장 번호: ");
        return in.nextLine().trim();
    }

    // 운송장 없을 때 안내 메시지도 여러 곳에서 동일해서 분리
    private static void printTrackingNotFound() {
        System.out.println("해당 운송장 번호를 찾을 수 없습니다.");
    }

    private static void printDelivery(Delivery delivery) {
        String type = (delivery instanceof ExpressDelivery) ? "긴급" : "일반";
        System.out.println("------------------------------");
        System.out.println("운송장 번호: " + delivery.getTrackingNumber());
        System.out.println("배송 종류  : " + type);
        System.out.println("배송 상태  : " + delivery.getStatus().getLabel());
        System.out.println("접수 일시  : " + delivery.getCreatedAt());
        System.out.println("발송 고객  : " + delivery.getSender().getName() + " (" + delivery.getSender().getId() + ")");
        System.out.println("택배 정보  : " + delivery.getParcel());
        System.out.println("배송비    : " + delivery.calculateFee() + "원");
    }
}
