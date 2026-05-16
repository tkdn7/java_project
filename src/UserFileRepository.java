import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// [역할] users.txt 파일에 회원 목록을 저장하고 불러오는 클래스
// [파일 형식] 한 줄에 한 명, 쉼표로 필드 구분
//   Customer: CUSTOMER,id,password,name,phone,address
//   Admin   : ADMIN,id,password,name,phone,department
public class UserFileRepository {
    private String filename;

    public UserFileRepository(String filename) {
        this.filename = filename;
    }

    // 전체 회원을 파일에 저장 (덮어쓰기)
    public void saveAll(List<User> users) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (User user : users) {
                writer.write(toLine(user));
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("파일 저장 실패: " + filename, e);
        }
    }

    // 파일에서 전체 회원을 불러옴, 파일이 없으면 빈 목록 반환 (첫 실행 대응)
    public List<User> loadAll() {
        List<User> users = new ArrayList<>();
        File file = new File(filename);
        if (!file.exists()) {
            return users;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.isEmpty()) continue;
                users.add(fromLine(line));
            }
        } catch (IOException e) {
            throw new RuntimeException("파일 로드 실패: " + filename, e);
        }
        return users;
    }

    // User -> 한 줄 텍스트
    private String toLine(User user) {
        if (user instanceof Customer) {
            Customer c = (Customer) user;
            return "CUSTOMER," + c.getId() + "," + c.getPassword() + ","
                    + c.getName() + "," + c.getPhone() + "," + c.getAddress();
        } else if (user instanceof Admin) {
            Admin a = (Admin) user;
            return "ADMIN," + a.getId() + "," + a.getPassword() + ","
                    + a.getName() + "," + a.getPhone() + "," + a.getDepartment();
        }
        throw new IllegalArgumentException("알 수 없는 User 타입입니다");
    }

    // 한 줄 텍스트 -> User
    private User fromLine(String line) {
        String[] parts = line.split(",");
        if (parts.length != 6) {
            throw new IllegalArgumentException("잘못된 형식의 줄입니다: " + line);
        }
        String type = parts[0];
        if (type.equals("CUSTOMER")) {
            return new Customer(parts[1], parts[2], parts[3], parts[4], parts[5]);
        } else if (type.equals("ADMIN")) {
            return new Admin(parts[1], parts[2], parts[3], parts[4], parts[5]);
        }
        throw new IllegalArgumentException("알 수 없는 회원 타입: " + type);
    }
}
