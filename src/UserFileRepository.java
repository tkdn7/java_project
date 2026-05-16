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
//
// ===== 작업 순서 가이드 (쉬운 것부터) =====
// TODO [★☆☆ 1순위] UserManager로 회원을 한두 명 추가한 뒤
//                    생성된 users.txt 파일을 직접 열어 형식이 어떤지 눈으로 확인
// TODO [★☆☆ 2순위] 파일이 비어있거나 처음 실행할 때 정상 동작하는지 확인 (예외 안 터지는지)
// TODO [★★☆ 3순위] 잘못된 줄을 만났을 때(필드 수 부족 등) 어떻게 처리할지 정책 결정
//                    — 지금은 예외가 터져 프로그램이 멈춤. 그 줄만 무시할지 결정
// TODO [★★★ 4순위] 이름이나 주소에 쉼표(,)가 들어가면 파싱이 깨짐
//                    → 입력 단에서 쉼표 금지 vs 구분자를 다른 문자(예: |)로 변경 중 선택
public class UserFileRepository {
    private String filename;

    public UserFileRepository(String filename) {
        this.filename = filename;
    }

    // 전체 회원을 파일에 저장 (덮어쓰기 방식)
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

    // 파일에서 전체 회원을 불러옴 (파일이 없으면 빈 목록 반환)
    public List<User> loadAll() {
        List<User> users = new ArrayList<>();
        File file = new File(filename);
        if (!file.exists()) {
            return users; // 첫 실행 시에는 파일이 없으므로 빈 목록 반환
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.isEmpty()) continue; // 빈 줄 스킵
                users.add(fromLine(line));
            }
        } catch (IOException e) {
            throw new RuntimeException("파일 로드 실패: " + filename, e);
        }
        return users;
    }

    // User 객체 → 한 줄 텍스트 변환
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

    // 한 줄 텍스트 → User 객체 변환
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
