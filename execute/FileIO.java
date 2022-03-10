
package sst.execute;

import sst.dao.CompanyAccess;
import sst.dao.UserAccess;
import sst.exception.OperationHaltException;
import sst.util.Methods;
import sst.util.FileSL;
import java.util.ArrayList;

public class FileIO implements Command {

    private FileSL saveLoadFileUtil;
    private UserAccess userDaoInMemory;
    private CompanyAccess companyDaoInMemory;

    public FileIO(FileSL saveLoadFileUtil,
                                   UserAccess userDaoInMemory,
                                   CompanyAccess companyDaoInMemory) {
        this.saveLoadFileUtil = saveLoadFileUtil;
        this.userDaoInMemory = userDaoInMemory;
        this.companyDaoInMemory = companyDaoInMemory;
    }

    @Override
    public void execute() throws OperationHaltException {
        String action = chooseAction();
        if (action.equals("1")) {
            saveLoadFileUtil.storeUsers(new ArrayList<>(userDaoInMemory.getUsers().values()));
            saveLoadFileUtil.storeCompany(companyDaoInMemory.getCompany());
            System.out.println("Successfully stored data in file");
        } else if (action.equals("2")) {
            saveLoadFileUtil.loadUsers().forEach(u -> userDaoInMemory.addUser(u));
            companyDaoInMemory.setCompany(saveLoadFileUtil.loadCompany());
            System.out.println("Data is retrieved from file");
        }
        postExecute();
    }

    private void postExecute() {
        System.out.println("Press enter to continue..");
        Methods.readLine();
    }

    private String chooseAction() {
        System.out.println("1. Save");
        System.out.println("2. Retrieve");
        return Methods.askNumber(1, 2);
    }
}