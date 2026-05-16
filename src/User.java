// [구현 순서 2순위] Customer/Admin의 공통 부모. Parcel과 함께 빨리 마무리.
// TODO 1) 로그인 메서드 login(String id, String password) 시그니처를 여기에 둘지,
//        별도 AuthService로 분리할지 결정 (분리 권장)
// TODO 2) 필드 캡슐화 강화: protected → private 로 바꾸고 자식은 getter로 접근하는 안도 고려
public abstract class User {
    protected String id;
    protected String password;
    protected String name;
    protected String phone;

    public User() {
    }

    public User(String id, String password, String name, String phone) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.phone = phone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
