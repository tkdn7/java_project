// AI가 로그인시 id, password중 어떤 것이 틀려도 null을 반환해
// 사용자가 어떤 것을 틀렸는지 알 수 없는점을 수정하고자 enum LoginResult 추가

public enum LoginResult {
    SUCCESS, // 로그인 성공
    ID_NOT_FOUND, // 입력한 ID에 해당하는 회원이 존재하지 않음 (※ "ID가 틀렸다"는 곧 "없다"와 같음 — "혹은 틀림"은 군더더기)
    WRONG_PASSWORD // ID는 있으나 비밀번호 불일치
}
