/**
 *  벽에 막혔을 때 나타나는 예외
 */
public class WallCollisionException extends Exception{
    public WallCollisionException() {
        super("벽에 막혔습니다.");
    }
}
