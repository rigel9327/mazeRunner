import java.util.Random;
import java.util.Scanner;

public class MazeRunner {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 미로 크기 입력 받기
        System.out.print("미로의 행 개수를 입력하세요: ");
        int numRows = scanner.nextInt();
        System.out.print("미로의 열 개수를 입력하세요: ");
        int numCols = scanner.nextInt();
        scanner.nextLine(); // 버퍼 비우기

        // 미로 생성
        Integer[][] maze = generateMaze(numRows, numCols);

        // 주인공 생성
        Character<Integer> character = new Character<>(0, 0);

        while (true) {
            // 미로 출력
            character.displayMazeWithCharacter(maze);

            System.out.print("방향을 입력하세요 (↑:u, ↓:d, ←:l, →:r): ");
            String input = scanner.nextLine();

            try {
                // 입력에 따라 방향 결정
                Direction direction = Direction.fromInput(input);
                if (direction == null) {
                    System.out.println("올바른 방향을 입력하세요.");
                    continue;
                }

                // 주인공 이동
                boolean continueGame = character.move(direction, maze);
                if (!continueGame) {
                    break; // 게임 종료
                }
                character.displayPosition();
            } catch (WallCollisionException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    // 미로 생성 메서드
    private static Integer[][] generateMaze(int numRows, int numCols) {
        Integer[][] maze = new Integer[numRows][numCols];
        Random random = new Random();

        // 미로 초기화 (벽을 랜덤하게 생성)
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                // 랜덤하게 벽 생성 (40%의 확률로 벽)
                if (random.nextDouble() < 0.4) {
                    maze[i][j] = 1;
                } else {
                    maze[i][j] = 0;
                }
            }
        }

        // 시작 위치와 출구 위치 설정 (임의로 시작 위치는 (0, 0), 출구 위치는 (numRows-1, numCols-1)로 설정)
        maze[0][0] = 0;
        maze[numRows - 1][numCols - 1] = 0;

        return maze;
    }
}
