package sst.execute;

import sst.dao.CompanyAccess;
import sst.dao.UserAccess;
import sst.exception.OperationHaltException;
import sst.model.DreamCorp;
import sst.model.Customer;
import sst.util.Methods;

public class ROICheck implements Command {

    private UserAccess userdata;

    public ROICheck(UserAccess userDaoInMemory) {
        this.userdata = userDaoInMemory;
    }

    @Override
    public void execute() throws OperationHaltException {
        String revenueType = selectRevenueType();
        switch (revenueType) {
            case "1": {
                System.out.println("Input user id:");
                String userId = Methods.readNotEmptyString();
                Customer user = userdata.getUser(userId);
                System.out.println("User's revenue: " + user.getRevenue());
                break;
            }
            case "2": {
                DreamCorp company = CompanyAccess.getInstance().getCompany();
                System.out.println("Company's revenue: " + company.getCompanyRevenue());
            }
        }
        postExecute();
    }

    private void postExecute() {
        System.out.println("Press enter to continue..");
        Methods.readLine();
    }

    private String selectRevenueType() {
        System.out.println("Choose the revenue to display:");
        System.out.println("1. User's revenue");
        System.out.println("2. Revenue of whole corporation");
        return Methods.askNumber(1, 2);
    }
}