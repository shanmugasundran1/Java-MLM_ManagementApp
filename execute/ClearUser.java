
package sst.execute;

import sst.dao.UserAccess;
import sst.exception.OperationHaltException;
import sst.util.Methods;

public class ClearUser implements Command {

    private UserAccess userDaoInMemory;

    public ClearUser(UserAccess userDaoInMemory) {
        this.userDaoInMemory = userDaoInMemory;
    }

    @Override
    public void execute() throws OperationHaltException {
        String userId = readUserId();
        userDaoInMemory.removeUser(userId);
        postExecute();
    }

    private void postExecute(){
        System.out.println("Successfully removed user");
        System.out.println("Press enter to continue..");
        Methods.readLine();
    }

    private String readUserId(){
        System.out.println("READY TO REMOVE USER\nPlease enter user id:");
        return Methods.readNotEmptyString();
    }
}
