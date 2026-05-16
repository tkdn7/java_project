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

    // 로그인 결과를 LoginResult로 반환 (SUCCESS / ID_NOT_FOUND / WRONG_PASSWORD)
    // [검토 TODO] 성공 시 호출자가 어떤 사용자가 로그인했는지 알 수 없음.
    //   - 메뉴 분기(instanceof Customer/Admin)를 하려면 User 객체가 필요한데
    //     login은 라벨만 돌려주고, findById는 private이라 외부에서 못 부름.
    //   - 해결안 A: findById를 public으로 변경해 호출자가 따로 가져오기
    //   - 해결안 B: getUser(String id) public 게터 별도 추가
    //   - 해결안 C: LoginResult 대신 결과 객체(LoginResponse)에 User까지 담아 반환

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
    // [검토 TODO] login이 LoginResult만 돌려주는 구조라면, 호출자가 후속 작업
    //   (이름 출력/메뉴 분기 등)을 하려면 이 메서드가 public이어야 할 가능성 큼.
    private User findById(String id) {
        for (User u : users) {
            if (u.getId().equals(id)) {
                return u;
            }
        }
        return null;
    }
}
