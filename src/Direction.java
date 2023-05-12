/**
 * 방향을 나타내는 ENUM 타입
 * 주어진 입력 값과 연결된 방향을 나타냄
 * 주어진 입력 값과 일치하는 방향을 찾는 static 메서드 'fromInput' 제공
 */
public enum Direction {
    UP("u"),
    DOWN("d"),
    LEFT("l"),
    RIGHT("r");

    private final String input;

    Direction(String input) {
        this.input = input;
    }

    // 주어진 입력 값에 해당하는 방향을 반환
    // 입력 값과 일치하는 방향이 없을 경우 'null' 반환
    public static Direction fromInput(String input) {
        for (Direction direction : values()) {
            if (direction.input.equals(input)) {
                return direction;
            }
        }
        return null;
    }
}
