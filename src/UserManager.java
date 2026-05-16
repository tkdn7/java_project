import java.util.ArrayList;
import java.util.List;

// [역할] 메모리에 회원 목록을 보관하고 검색/로그인 처리
// 회원 추가/삭제는 UserFileRepository를 통해 users.txt에 즉시 반영
public class UserManager {
    private List<User> users;
    private UserFileRepository fileRepository;

    public UserManager() {
        this.fileRepository = new UserFileRepository("users.txt");
        this.users = fileRepository.loadAll(); // 프로그램 시작시 자동 로드
    }

    // 회원 추가 - id 중복 거부, 비밀번호 6자 미만 거부, 성공시 파일 저장
    public void addUser(User user) {
        if (findById(user.getId()) != null) {
            throw new IllegalArgumentException("이미 존재하는 ID입니다: " + user.getId());
        }
        String password = user.getPassword();
        if (password == null || password.length() < 6) {
            throw new IllegalArgumentException("비밀번호는 6자리 이상이어야 합니다.");
        }
        users.add(user);
        fileRepository.saveAll(users);
    }

    // 회원 삭제 - 없는 ID면 예외, 성공시 파일 저장
    public void removeUser(String id) {
        User user = findById(id);
        if (user == null) {
            throw new IllegalArgumentException("존재하지 않는 ID입니다: " + id);
        }
        users.remove(user);
        fileRepository.saveAll(users);
    }

    // 외부에서 내부 리스트가 직접 수정되지 않도록 복사본 반환
    public List<User> getAllUsers() {
        return new ArrayList<>(users);
    }

    // 로그인 결과를 LoginResult로 반환 (SUCCESS / ID_NOT_FOUND / WRONG_PASSWORD)
    // 성공 후 실제 User 객체가 필요하면 getUserById()로 따로 조회
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

    // 로그인 성공 후 실제 User 객체가 필요하면 사용하는 메소드
    // findById()는 내부 검색용으로 private 유지해주고
    // 외부에서는 이 메소드를 통해서 회원 조회
    public User getUserById(String id) {
        return findById(id);
    }

    // 내부 헬퍼 - id로 회원 찾기, 없으면 null
    private User findById(String id) {
        for (User u : users) {
            if (u.getId().equals(id)) {
                return u;
            }
        }
        return null;
    }
}
