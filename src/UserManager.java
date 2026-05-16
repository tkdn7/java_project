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

    // 전체 회원 목록 조회 (내부 리스트가 외부에서 수정되지 않도록 복사본 반환)
    public List<User> getAllUsers() {
        return new ArrayList<>(users);
    }

    // 로그인 결과를 LoginResult로 반환 (SUCCESS / ID_NOT_FOUND / WRONG_PASSWORD)
    // 성공 후 실제 User 객체가 필요하면 getUserById(id)로 따로 조회한다.
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

    // 내부 헬퍼: id로 회원 찾기 (없으면 null)
    // 외부에서는 public getUserById()를 통해 우회 접근하도록 private 유지
    private User findById(String id) {
        for (User u : users) {
            if (u.getId().equals(id)) {
                return u;
            }
        }
        return null;
    }
}
