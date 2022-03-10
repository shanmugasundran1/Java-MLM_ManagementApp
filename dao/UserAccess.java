
package sst.dao;

import sst.exception.UnsupportedRootUserDeletion;
import sst.exception.NoUserExistsException;
import sst.model.Customer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserAccess {

    private static Map<String, Customer> users = new HashMap<>();

    private UserAccess() {
    }

    private static UserAccess instance;

    public static UserAccess getInstance() {
        if (instance == null) {
            instance = new UserAccess();
        }
        return instance;
    }

    public Map<String, Customer> getUsers() {
        return users;
    }

    public void addUser(Customer user) {
        users.put(user.getId(), user);
    }

    public Customer getUser(String userId) throws NoUserExistsException {
        if (!users.containsKey(userId)) throw new NoUserExistsException();
        return users.get(userId);
    }

    public void updateUser(Customer user) {
        users.put(user.getId(), user);
    }

    public void removeUser(String userId) throws NoUserExistsException, UnsupportedRootUserDeletion {
        if (!users.containsKey(userId)) throw new NoUserExistsException();
        if (userId.equals(CompanyAccess.getInstance().getCompany().getRootUser().getId())) {
            throw new UnsupportedRootUserDeletion();
        }
        Customer userToRemove = users.get(userId);
        Customer uplineUser = userToRemove.getDirectUpline();
        List<Customer> downlineUsers = userToRemove.getDirectDownlines();
        uplineUser.setDirectDownlines(downlineUsers);
        downlineUsers.forEach(u -> u.setDirectUpline(userToRemove.getDirectUpline()));
        users.remove(userId);
    }

}

