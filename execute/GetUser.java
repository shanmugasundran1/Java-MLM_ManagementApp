package sst.execute;

import sst.dao.UserAccess;
import sst.exception.OperationHaltException;
import sst.exception.NoUserExistsException;
import sst.model.Customer;
import sst.util.Methods;
import sst.util.SecurityEncryption;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class GetUser implements Command {

    private UserAccess userDaoInMemory;

    public GetUser(UserAccess userDaoInMemory) {
        this.userDaoInMemory = userDaoInMemory;
    }

    @Override
    public void execute() throws OperationHaltException, NoUserExistsException {
        List<String> userData = readUserData();
        String userId = userData.get(0);
        String decryptionKey = userData.get(1);
        Customer user = userDaoInMemory.getUser(userId);
        printUserInfo(user, decryptionKey);
        postExecute();
    }

    private void postExecute() {
        System.out.println("Press enter to continue..");
        Methods.readLine();
    }

    private List<String> readUserData() {
        System.out.println("Enter user id:");
        String userId = Methods.readNotEmptyString();
        userDaoInMemory.getUser(userId);
        System.out.println("Decrypt user name?");
        System.out.println("1. Yes");
        System.out.println("2. No");
        String input = Methods.askNumber(1, 2);
        String decryptKey = null;
        if (input.equals("1")) {
            System.out.println("Enter key for decryption:");
            decryptKey = Methods.readNotEmptyString();
        }
        return Arrays.asList(userId, decryptKey);
    }

    private void printUserInfo(Customer user, String decryptionKey) {
        System.out.println("USER's INFO");
        System.out.println("User Id: " + user.getId());

        if (Objects.isNull(decryptionKey) || user.isRootUser()) {
            System.out.println("Encrypted name: " + user.getEncryptedName());
        } else {
            System.out.println("Decrypted name: " + new SecurityEncryption(decryptionKey).decrypt(user.getEncryptedName()));
        }

        System.out.println("User Revenue: " + user.getRevenue());
        if (user.isRootUser()) {
            System.out.println("Root user");
        } else {
            System.out.println("Direct upline user id: " + user.getDirectUpline().getId());
        }
        if (user.getDirectDownlines().isEmpty()) {
            System.out.println("The selected user has no direct downlines");
        } else {
            System.out.println("Direct downline user id: "
                    + String.join(", ", user.getDirectDownlines().stream()
                            .map(Customer::getId)
                            .collect(Collectors.toList())));
        }
    }

}   
