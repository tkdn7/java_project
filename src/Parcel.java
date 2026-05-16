// [구현 순서 1순위] 다른 클래스에 의존하지 않는 데이터 클래스. 가장 먼저 확정.
// TODO 3) (선택) 부피(가로/세로/높이) 필드 추가 — 부피무게 적용한 배송비 계산을 도입할 경우
public class Parcel {
    // 기본 정보 멤버 변수
    private String contents;
    private double weight;
    private double distance;
    private String senderName;
    private String senderAddress;
    private String receiverName;
    private String receiverAddress;
    // 부피, 무게 적용하여 계산을 위한 멤버 변수
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
        if (weight <= 0) { // 무게가 음수일 수 없음 예외 처리
            throw new IllegalArgumentException("무게는 0보다 작을 수 없습니다. 무게: " + weight);
        }
        this.weight = weight;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        if (distance <= 0) { // 거리가 음수일 수 없음 예외 처리
            throw new IllegalArgumentException("거리는 0보다 작을 수 없습니다. 거리: " + distance);
        }
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

    public double getWidth() { return width; }

    public void setWidth(double width) {
        if (width <= 0) { // 가로 길이가 음수일 수 없음 예외 처리
            throw new IllegalArgumentException("가로 길이는 0보다 작을 수 없습니다. 가로: " + width);
        }
        this.width = width;
    }

    public double getLength() { return length; }

    public void setLength(double length) {
        if (length <= 0) { // 가로 길이가 음수일 수 없음 예외 처리
            throw new IllegalArgumentException("세로 길이는 0보다 작을 수 없습니다. 세로: " + length);
        }
        this.length = length;
    }

    public double getHeight() { return height; }

    public void setHeight(double height) {
        if (height <= 0) { // 가로 길이가 음수일 수 없음 예외 처리
            throw new IllegalArgumentException("높이는 0보다 작을 수 없습니다. 높이: " + height);
        }
        this.height = height;
    }

    public double getVolume() { // 부피 계산
        return width * length * height;
    }

    public double getVolumeWeight() {
        return (width * length * height) / 5000.0;
    }

    @Override
    public String toString() {
        return String.format (
            "[내용물: %s, 무게: %.1fkg, 거리: %.1fkm]\n" +
            "   발송인: %s (%s)\n" + // 이름, 주소
            "   수취인: %s (%s)",
            contents, weight, distance,
            senderName, senderAddress,
            receiverName, receiverAddress
        );
    }
}
