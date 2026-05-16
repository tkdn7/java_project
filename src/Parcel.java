// [구현 순서 1순위] 다른 클래스에 의존하지 않는 데이터 클래스. 가장 먼저 확정.
// TODO 1) 필드 검증 로직(setter): weight/distance가 0 이하인 경우 예외 처리 또는 차단
// TODO 2) toString() 오버라이드: 운송장 출력 시 송신자/수신자 정보 보기 좋게 포매팅
// TODO 3) (선택) 부피(가로/세로/높이) 필드 추가 — 부피무게 적용한 배송비 계산을 도입할 경우
public class Parcel {
    private String contents;
    private double weight;
    private double distance;
    private String senderName;
    private String senderAddress;
    private String receiverName;
    private String receiverAddress;

    public Parcel() {
    }

    public Parcel(String contents, double weight, double distance,
                  String senderName, String senderAddress,
                  String receiverName, String receiverAddress) {
        this.contents = contents;
        this.weight = weight;
        this.distance = distance;
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
        this.weight = weight;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
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
}
