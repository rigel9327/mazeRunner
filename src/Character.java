/**
 * 미로를 이동하는 사용자 클래스
 * 캐릭터의 이동과 위치 출력, 미로 상태 출력을 처리
 */
public
class Character<T> {

    // 'x'와 'y'는 캐릭터의 현재 위치를 나타내는 변수
    private int x;
    private int y;

    // 'Character' 클래스는 'x', 'y'를 매개변수로 받는 생성자를 가진다.
    // 이 생성자를 통해 캐릭터의 초기 위치를 설정
    public Character(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // 'isExit' 메서드는 현재 위치가 미로의 출구인지를 판단
    // 'x', 'y'의 값이 미로의 5시 방향 끝과 일치하면 출구로 판단
    private boolean isExit(int x, int y, T[][] maze) {
        int numRows = maze.length;
        int numCols = maze[0].length;
        return x == numCols - 1 && y == numRows - 1;
    }

    // 'move' 메서드는 입력된 방향으로 캐릭터를 이동시킨다.
    // 이동 가능할 경우 'true' 반환, 불가능할 경우 'WallConllisionException'을 발생
    // 캐릭터가 출구에 도달할 경우 "Clear!" 메세지를 출력하고 false를 반환, 게임을 종료
    public boolean move(Direction direction, T[][] maze) throws WallCollisionException {
        int newX = x;
        int newY = y;

        switch (direction) {
            case UP:
                newY--;
                break;
            case DOWN:
                newY++;
                break;
            case LEFT:
                newX--;
                break;
            case RIGHT:
                newX++;
                break;
        }

        // 벽에 부딪힌 경우 예외를 던짐
        if (isWall(newX, newY, maze)) {
            throw new WallCollisionException();
        }

        if (isExit(newX, newY, maze)) {
            System.out.println("Clear!");
            return false; // 더 이상 입력을 받지 않고 게임 종료
        }

        // 이동
        x = newX;
        y = newY;

        return true; // 계속해서 입력을 받음
    }

    private boolean isWall(int x, int y, T[][] maze) {
        int numRows = maze.length;
        int numCols = maze[0].length;

        // 범위를 벗어나는 경우 벽으로 처리
        if (x < 0 || y < 0 || x >= numCols || y >= numRows) {
            return true;
        }

        // 미로에서 해당 위치가 벽인지 확인
        return maze[y][x].equals(1);
    }

    // 'displayPosition' 메서드는 캐릭터의 현재 위치를 출력
    public void displayPosition() {
        System.out.println("현재 위치: (" + x + ", " + y + ")");
    }

    // 'displayMazeWithCharacter' 메서드는 주인공이 있는 미로 상태를 출력
    // 현재 위치 : '●' , 벽 : '■', 빈 공간 : '□'
    public void displayMazeWithCharacter(T[][] maze) {
        System.out.println("미로 상태:");
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                if (i == y && j == x) {
                    System.out.print("● "); // 내 위치를 '●'로 표시
                } else if (maze[i][j].equals(1)) {
                    System.out.print("■ "); // 벽을 '■'로 표시
                } else {
                    System.out.print("□ "); // 빈 공간을 '□'로 표시
                }
            }
            System.out.println();
        }
        System.out.println();
    }
}

