import java.util.Scanner;

// [역할] UserManager / UserFileRepository 동작 확인용 테스트 진입점
//
// ===== 사용 방법 =====
// 1) 첫 실행: 회원 2명이 추가되고 users.txt 파일이 생성됨
// 2) 두 번째 실행: "이미 존재하는 ID" 메시지가 뜸 → 파일에서 잘 불러왔다는 증거
// 3) src 폴더의 users.txt 를 열어 형식 확인 (CUSTOMER,c1,1234,...)
//
// ===== 작업 가이드 =====
// TODO [★★☆ 3순위] 키보드 입력(Scanner)으로 ID/비밀번호 받아 로그인하는 콘솔 UI로 확장
// TODO [★★★ 4순위] 로그인 성공 후 Customer / Admin에 따라 다른 메뉴를 보여주는 분기 추가
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        UserManager manager = new UserManager();

        // 1. 시작 시 회원 목록 출력
        System.out.println("=== 시작 시 회원 목록 ===");
        printAllUsers(manager);

        // 2. 회원 추가 (이미 있으면 예외 발생 → 메시지만 출력하고 계속 진행)
        System.out.println("\n=== 회원 추가 ===");
        tryAddUser(manager, new Customer("c1", "1234", "홍길동", "010-1111-2222", "서울시 강남구"));
        tryAddUser(manager, new Admin("a1", "admin", "관리자", "010-9999-9999", "배송팀"));

        // 3. 추가 후 회원 목록
        System.out.println("\n=== 추가 후 회원 목록 ===");
        printAllUsers(manager);

        // 4. 로그인 테스트
        System.out.println("\n=== 로그인 테스트 ===");
        tryLogin(manager, "c1", "1234");       // 성공 케이스
        tryLogin(manager, "c1", "wrongpw");    // 실패: 비밀번호 불일치
        tryLogin(manager, "nobody", "1234");   // 실패: 없는 ID
    }

    // 전체 회원 출력 (회원이 없으면 안내 문구)
    private static void printAllUsers(UserManager manager) {
        if (manager.getAllUsers().isEmpty()) {
            System.out.println("(등록된 회원 없음)");
            return;
        }
        for (User u : manager.getAllUsers()) {
            String type = (u instanceof Admin) ? "관리자" : "고객";
            System.out.println("- [" + type + "] " + u.getId() + " / " + u.getName());
        }
    }

    // 회원 추가 시도 (중복이면 예외 잡아서 메시지만 출력)
    private static void tryAddUser(UserManager manager, User user) {
        try {
            manager.addUser(user);
            System.out.println("추가 성공: " + user.getId());
        } catch (IllegalArgumentException e) {
            System.out.println("추가 실패: " + e.getMessage());
        }
    }

    // 로그인 시도 결과 출력
    private static void tryLogin(UserManager manager, String id, String password) {
        LoginResult result = manager.login(id, password);
        switch (result) {
            case SUCCESS:
                // [검토 TODO] 메뉴 분기로 확장하려면 여기서 로그인 한 User 객체가 필요함.
                //   현재는 id 문자열밖에 없어서 Customer/Admin 판별 불가.
                //   UserManager에 사용자 조회 수단(public getUser 등)을 마련해야 함.
                System.out.println("로그인 성공 -> id: " + id);
                break;
            case ID_NOT_FOUND:
                System.out.println("로그인 실패 -> 존재하지 않는 ID: " + id);
                break;
            case WRONG_PASSWORD:
                System.out.println("로그인 실패 -> 비밀번호가 틀렸습니다.");
                break;
            // [검토 TODO] LoginResult에 라벨이 추가될 때 누락 방지용 default 케이스를 둘지 결정
        }
    }
}
