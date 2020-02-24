package springbook.user.dao;

/**
 * Created with IntelliJ IDEA.
 * User: 노상현
 * Date: 2019-01-15
 */
public class DuplicateUserIdException extends RuntimeException {
    public DuplicateUserIdException(Throwable cause) {
        super(cause);
    }
}
