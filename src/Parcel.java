// [역할] 택배 물품 정보 (내용물, 무게, 거리, 송수신자, 부피)를 담는 데이터 클래스
// 배송비 계산에 필요한 값들을 보관, 음수 입력은 setter에서 막음
public class Parcel {
    // 기본 정보
    private String contents;
    private double weight;
    private double distance;
    private String senderName;
    private String senderAddress;
    private String receiverName;
    private String receiverAddress;
    // 부피무게 계산용
    private double width;
    private double length;
    private double height;

    public Parcel() {
    }

    public Parcel(String contents, double weight, double distance,
                  String senderName, String senderAddress,
                  String receiverName, String receiverAddress) {
        this.contents = contents;
        setWeight(weight); // 이 줄과 아랫줄의 경우 예외 처리를 위해 setter 사용
        setDistance(distance);
        this.senderName = senderName;
        this.senderAddress = senderAddress;
        this.receiverName = receiverName;
        this.receiverAddress = receiverAddress;
    }

    // 무게/거리/가로/세로/높이 모두 음수가 들어오면 동일한 메시지로 막는 공통 검증
    private static void requirePositive(double value, String fieldName) {
        if (value <= 0) {
            throw new IllegalArgumentException(fieldName + "는 0보다 작을 수 없습니다. " + fieldName + ": " + value);
        }
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        requirePositive(weight, "무게");
        this.weight = weight;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        requirePositive(distance, "거리");
        this.distance = distance;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getSenderAddress() {
        return senderAddress;
    }

    public void setSenderAddress(String senderAddress) {
        this.senderAddress = senderAddress;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        requirePositive(width, "가로");
        this.width = width;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        requirePositive(length, "세로");
        this.length = length;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        requirePositive(height, "높이");
        this.height = height;
    }

    // 부피 계산
    public double getVolume() {
        return width * length * height;
    }

    // 부피무게 = (가로*세로*높이) / 5000 — 택배사 표준 공식
    public double getVolumeWeight() {
        return (width * length * height) / 5000.0;
    }

    @Override
    public String toString() {
        return String.format(
            "[내용물: %s, 무게: %.1fkg, 거리: %.1fkm]\n" +
            "   발송인: %s (%s)\n" +
            "   수취인: %s (%s)",
            contents, weight, distance,
            senderName, senderAddress,
            receiverName, receiverAddress
        );
    }
}
