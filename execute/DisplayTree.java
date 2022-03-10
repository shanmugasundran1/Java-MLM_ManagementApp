
package sst.execute;

import sst.dao.CompanyAccess;
import sst.exception.OperationHaltException;
import sst.model.Customer;
import sst.util.Methods;

public class DisplayTree implements Command {

    @Override
    public void execute() throws OperationHaltException {
        Customer rootUser = CompanyAccess.getInstance().getCompany().getRootUser();
        System.out.println("MLM USER TREE");
        Methods.recursivePrint(rootUser, 0);
        postExecute();
    }

    private void postExecute() {
        System.out.println("Press enter to continue..");
        Methods.readLine();
    }
}
