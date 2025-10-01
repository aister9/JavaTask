package ClassStudy;
import java.util.Random;
import java.util.Scanner;

enum RPSenum {
    ROCK, SCISSOR, PAPER;

    // 비교 메소드
    public String compare(RPSenum other) {
        if (this == other) return "무승부";

        switch (this) {
            case ROCK:
                return (other == SCISSOR) ? "승리" : "패배";
            case SCISSOR:
                return (other == PAPER) ? "승리" : "패배";
            case PAPER:
                return (other == ROCK) ? "승리" : "패배";
        }
        return ""; // 안전 장치
    }

    @Override
    public String toString() {
        switch (this) {
            case ROCK: return "바위";
            case SCISSOR: return "가위";
            case PAPER: return "보";
        }
        return "";
    }

    // 문자열 -> enum 변환
    public static RPSenum fromString(String input) {
        switch (input) {
            case "바위": return ROCK;
            case "가위": return SCISSOR;
            case "보": return PAPER;
            default: return null;
        }
    }
}

public class RPSwithEnum {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.print("가위/바위/보를 선택하세요: ");
        String input = scanner.nextLine();

        RPSenum player = RPSenum.fromString(input);
        if (player == null) {
            System.out.println("가위, 바위, 보만 선택 가능합니다.");
            return;
        }

        RPSenum computer = RPSenum.values()[random.nextInt(3)];

        System.out.println("플레이어 선택: " + player);
        System.out.println("컴퓨터 선택: " + computer);
        System.out.println("결과: " + player.compare(computer));
    }
}
