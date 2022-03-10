package sst.util;

import sst.model.DreamCorp;
import sst.model.Customer;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class FileSL {

    private static final String USER_DATA_STORE_LOCATION = "user-data-store.txt";
    private static final String COMPANY_DATA_STORE_LOCATION = "company-data-store.txt";
    private static final File userDataStoreFile = new File(USER_DATA_STORE_LOCATION);
    private static final File companyDataStoreFile = new File(COMPANY_DATA_STORE_LOCATION);

    public boolean storeUsers(List<Customer> users) {
        try {
            FileOutputStream fos = new FileOutputStream(userDataStoreFile);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(users);
            oos.close();
            fos.close();
        } catch (IOException ex) {
            return false;
        }
        return true;
    }

    public boolean storeCompany(DreamCorp company) {
        try {
            FileOutputStream fos = new FileOutputStream(companyDataStoreFile);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(company);
            oos.close();
            fos.close();
        } catch (IOException ex) {
            return false;
        }
        return true;
    }

    public List<Customer> loadUsers() {
        List<Customer> users = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream(userDataStoreFile);
            ObjectInputStream ois = new ObjectInputStream(fis);
            users = (ArrayList<Customer>) ois.readObject();
            ois.close();
            fis.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return users;
    }

    public DreamCorp loadCompany() {
        DreamCorp company = null;
        try {
            FileInputStream fis = new FileInputStream(companyDataStoreFile);
            ObjectInputStream ois = new ObjectInputStream(fis);
            company = (DreamCorp) ois.readObject();
            ois.close();
            fis.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return company;
    }

}
