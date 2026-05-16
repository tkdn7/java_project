import java.util.ArrayList;
import java.util.List;

// [역할] 메모리에 회원 목록을 보관하고 검색/로그인하는 클래스
// [의존] UserFileRepository (파일 저장/불러오기 위임)
//
// ===== 작업 순서 가이드 (쉬운 것부터) =====
// TODO [★☆☆ 1순위] 코드 흐름 따라가며 각 메서드가 어떤 일을 하는지 직접 읽어보기
// TODO [★☆☆ 2순위] Main 클래스를 만들어 UserManager를 생성하고
//                    addUser() / login() 을 호출해 동작이 맞는지 확인
// TODO [★★☆ 3순위] addUser()에서 비밀번호가 비어있거나 너무 짧으면(예: 4자 미만) 예외 던지기
// TODO [★★☆ 4순위] removeUser(String id) 메서드 추가 — Admin의 회원 삭제 기능에서 사용
// TODO [★★★ 5순위] login 실패 사유를 호출자에게 어떻게 전달할지 정책 결정
//                    (지금은 null 반환 → "ID 없음"과 "비밀번호 불일치"가 구분 안 됨)
public class UserManager {
    private List<User> users;
    private UserFileRepository fileRepository;

    public UserManager() {
        this.fileRepository = new UserFileRepository("users.txt");
        this.users = fileRepository.loadAll(); // 프로그램 시작 시 자동 로드
    }

    // 회원 추가 (id 중복 확인 후 파일에도 즉시 저장)
    public void addUser(User user) {
        if (findById(user.getId()) != null) { // 같은 id가 이미 있으면 예외
            throw new IllegalArgumentException("이미 존재하는 ID입니다: " + user.getId());
        }
        users.add(user);
        fileRepository.saveAll(users); // 추가 후 파일에 반영
    }

    // 전체 회원 목록 조회
    public List<User> getAllUsers() {
        return users;
    }

    // 로그인: id 찾고 비밀번호 확인 → 성공 시 User 반환, 실패 시 null
    public User login(String id, String password) {
        User user = findById(id);
        if (user == null) {
            return null; // ID 없음
        }
        if (!user.getPassword().equals(password)) {
            return null; // 비밀번호 불일치
        }
        return user;
    }

    // 내부 헬퍼: id로 회원 찾기 (없으면 null)
    private User findById(String id) {
        for (User u : users) {
            if (u.getId().equals(id)) {
                return u;
            }
        }
        return null;
    }
}
