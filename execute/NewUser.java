
package sst.execute;

import sst.dao.CompanyAccess;
import sst.dao.UserAccess;
import sst.exception.OperationHaltException;
import sst.model.Customer;
import sst.util.Methods;
import sst.util.SecurityEncryption;
import java.util.Arrays;
import java.util.List;

public class NewUser implements Command {

    private static final int LAST_GENERATION = 5;
    private UserAccess userDaoInMemory;
    private CompanyAccess companyDaoInMemory;

    public NewUser(UserAccess userDaoInMemory, CompanyAccess companyDaoInMemory) {
        this.userDaoInMemory = userDaoInMemory;
        this.companyDaoInMemory = companyDaoInMemory;
    }

    @Override
    public void execute() throws OperationHaltException {
        List<String> userData = readUserData();
        Customer user = createNewUser(userData);
        userDaoInMemory.addUser(user);
        Customer uplineUser = userDaoInMemory.getUser(userData.get(3));
        uplineUser.getDirectDownlines().add(user);
        userDaoInMemory.updateUser(uplineUser);
        payComissionToUplinesAndCompany(user);
        postExecute();
    }

    private void payComissionToUplinesAndCompany(Customer user) {
        Double registrationFee = companyDaoInMemory.getCompany().getRegistrationFee();
        double remain = registrationFee;
        Customer upline = user.getDirectUpline();
        for (int gen = 1; gen <= LAST_GENERATION; gen++) {
            Double commissionPercent = companyDaoInMemory.getCompany().getCommission().get(gen);
            Double uplineComission = registrationFee * commissionPercent;
            remain = remain - uplineComission;
            upline.addToRevenue(uplineComission);
            userDaoInMemory.updateUser(upline);
            if (!upline.isRootUser()) {
                upline = upline.getDirectUpline();
            } else break;
        }
        companyDaoInMemory.getCompany().addToCompanyRevenue(remain);
    }

    private void postExecute() {
        System.out.println("New user was registered");
        System.out.println("Press enter to continue..");
        Methods.readLine();
    }

    private Customer createNewUser(List<String> userData) {
        String userId = userData.get(0);
        String userName = userData.get(1);
        String encryptionKey = userData.get(2);
        String uplineUserId = userData.get(3);

        Customer user = new Customer();
        user.setId(userId);
        String encryptedName = new SecurityEncryption(encryptionKey).encrypt(userName);
        user.setEncryptedName(encryptedName);
        user.setDirectUpline(userDaoInMemory.getUser(uplineUserId));
        return user;
    }

    private List<String> readUserData() {
        System.out.println("New user Registration");
        System.out.println("Input user id:");
        String userId = Methods.readNotEmptyString();
        System.out.println("Input user name:");
        String userName = Methods.readNotEmptyString();
        System.out.println("Input key for user name encryption:");
        String encryptionKey = Methods.readNotEmptyString();
        System.out.println("Input upline user id:");
        String uplineUserId = Methods.readNotEmptyString();
        return Arrays.asList(userId, userName, encryptionKey, uplineUserId);
    }
}
