package sst.execute;
import sst.Operations;
import sst.dao.CompanyAccess;
import sst.dao.UserAccess;
import sst.exception.OperationHaltException;
import sst.exception.NoUserExistsException;
import sst.util.FileSL;
import java.util.HashMap;
import java.util.Map;

public class Action {

    private static UserAccess accessUse = UserAccess.getInstance();
    private static CompanyAccess accessCom = CompanyAccess.getInstance();
    private static FileSL saveLoadFileUtil = new FileSL();

    private static Map<Operations, Command> commandMap = new HashMap<Operations, Command>() {
        {
            put(Operations.USER_CREATE, new NewUser(accessUse, accessCom));
            put(Operations.GET_USER, new GetUser(accessUse));
            put(Operations.UPDATE_USER, new UserModify(accessUse));
            put(Operations.DELETE_USER, new ClearUser(accessUse));
            put(Operations.DISPLAY_USER_TREE, new DisplayTree());
            put(Operations.REVIEW_REVENUE, new ROICheck(accessUse));
            put(Operations.SAVE_LOAD_IN_FILE, new FileIO(saveLoadFileUtil, accessUse, accessCom));
            put(Operations.CHANGE_FEE, new Fee(accessCom));
            put(Operations.QUIT, new Quit());
        }
    };

    private Action() {
    }

    public static void execute(Operations operation) throws OperationHaltException, NoUserExistsException {
        commandMap.get(operation).execute();
    }
}
