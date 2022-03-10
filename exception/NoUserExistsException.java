
package sst.exception;

public class NoUserExistsException extends RuntimeException {
    @Override
    public String getMessage() {
        return "User not exists";
    }
}

