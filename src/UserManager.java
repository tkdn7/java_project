import java.util.ArrayList;
import java.util.List;

// [역할] 메모리에 회원 목록을 보관하고 검색/로그인하는 클래스
// [의존] UserFileRepository (파일 저장/불러오기 위임)

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
        String password = user.getPassword();
        if (password == null || password.length() < 6) {
            throw new IllegalArgumentException("비밀번호는 6자리 이상이어야 합니다.");
        }
        users.add(user);
        fileRepository.saveAll(users); // 추가 후 파일에 반영
    }

    public void removeUser(String id) {
        User user = findById(id);
        if (user == null) {
            throw new IllegalArgumentException("존재하지 않는 ID입니다: " + id);
        }
        users.remove(user);
        fileRepository.saveAll(users);
    }

    // 전체 회원 목록 조회
    public List<User> getAllUsers() {
        return users;
    }

    // 로그인: id 찾고 비밀번호 확인 → 성공 시 User 반환, 실패 시 null
    public LoginResult login(String id, String password) {
        User user = findById(id);
        if (user == null) {
            return LoginResult.ID_NOT_FOUND;
        }
        if (!user.getPassword().equals(password)) {
            return LoginResult.WRONG_PASSWORD;
        }
        return LoginResult.SUCCESS;
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
