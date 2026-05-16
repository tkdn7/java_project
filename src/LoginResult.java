// AI가 로그인시 id, password중 어떤 것이 틀려도 null을 반환해
// 사용자가 어떤 것을 틀렸는지 알 수 없는점을 수정하고자 enum LoginResult 추가

public enum LoginResult {
    SUCCESS, // 로그인 성공
    ID_NOT_FOUND, // ID 없음, 혹은 틀림
    WRONG_PASSWORD // 비밀번호 틀림
}
