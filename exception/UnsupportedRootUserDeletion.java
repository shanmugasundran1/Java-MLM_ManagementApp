
package sst.exception;

public class UnsupportedRootUserDeletion extends RuntimeException {
    @Override
    public String getMessage() {
        return "Invalid command.Cannot remove Root User";
    }
}

