
package sst.execute;

import sst.dao.UserAccess;
import sst.exception.OperationHaltException;
import sst.model.Customer;
import sst.util.Methods;
import sst.util.SecurityEncryption;

public class UserModify implements Command {

    private UserAccess userDaoInMemory;

    public UserModify(UserAccess userDaoInMemory) {
        this.userDaoInMemory = userDaoInMemory;
    }

    @Override
    public void execute() throws OperationHaltException {
        Customer user = userDaoInMemory.getUser(readUserId());
        updateUserData(user);
        postExecute();
    }

    private void postExecute() {
        System.out.println("User data was updated");
        System.out.println("Press enter to continue..");
        Methods.readLine();
    }

    private String readUserId() {
        System.out.println("USER UPDATE IN PROGRESS....\nPlease nter ID of user you want to update:");
        return Methods.readNotEmptyString();
    }

    private void updateUserData(Customer user) {
        boolean actionSelected = false;
        while (!actionSelected) {
            System.out.println("Please choose the item to update:");
            System.out.println("1. User name");
            System.out.println("2. Revenue");
            String input = Methods.askNumber(1, 2);
            if (input.equals("1")) {
                System.out.println("Input new user name:");
                String newUserName = Methods.readNotEmptyString();
                System.out.println("Input key for user name encryption:");
                String encryptionKey = Methods.readNotEmptyString();
                String encryptedName = new SecurityEncryption(encryptionKey).encrypt(newUserName);
                user.setEncryptedName(encryptedName);
                userDaoInMemory.updateUser(user);
                actionSelected = true;
            } else if (input.equals("2")) {
                System.out.println("Input new revenue value:");
                Double newRevenueValue = Double.parseDouble(Methods.askDouble());
                user.setRevenue(newRevenueValue);
                userDaoInMemory.updateUser(user);
                actionSelected = true;
            }
        }
    }
}