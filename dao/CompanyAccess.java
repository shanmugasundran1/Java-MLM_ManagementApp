package sst.dao;

import sst.model.DreamCorp;
import sst.model.Customer;
import java.util.HashMap;
import java.util.Map;

public class CompanyAccess {

    private DreamCorp company;

    {
        DreamCorp company = new DreamCorp();
        double registrationFee = 50.0;
        Map<Integer, Double> PercentageCommission = new HashMap<>();
        PercentageCommission.put(1, 0.50);
        PercentageCommission.put(2, 0.12);
        PercentageCommission.put(3, 0.09);
        PercentageCommission.put(4, 0.06);
        PercentageCommission.put(5, 0.03);
        company.setRegistrationFee(registrationFee);
        company.setCommission(PercentageCommission);

        Customer rootUser = new Customer();
        rootUser.setId("1");
        rootUser.setEncryptedName("SSTCORP");
        UserAccess.getInstance().addUser(rootUser);
        company.setRootUser(rootUser);
        this.company = company;
    }

    private CompanyAccess() {
    }

    private static CompanyAccess instance;

    public static CompanyAccess getInstance() {
        if (instance == null) {
            instance = new CompanyAccess();
        }
        return instance;
    }

    public void setCompany(DreamCorp company) {
        this.company = company;
    }

    public DreamCorp getCompany() {
        return this.company;
    }
}
