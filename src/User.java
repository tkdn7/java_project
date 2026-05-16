// [역할] Customer/Admin의 공통 부모 클래스. id, password, name, phone 보관
public abstract class User { // 첫 번재 프롬프트에서 멤버 변수를 전부 protected로 제작했지만 캡슐화를 위해 private으로 변경
    private String id;
    private String password;
    private String name;
    private String phone;

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
