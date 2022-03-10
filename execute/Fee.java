
package sst.execute;

import sst.dao.CompanyAccess;
import sst.exception.OperationHaltException;
import sst.util.Methods;
import java.util.Map;

public class Fee implements Command {

    private CompanyAccess accessComp;

    public Fee(CompanyAccess companyDO) {
        this.accessComp = companyDO;
    }

    @Override
    public void execute() throws OperationHaltException {
        String action = chooseAction();

        if (action.equals("1")) {
            Double currentRegistrationFee = accessComp.getCompany().getRegistrationFee();

            System.out.println("Previous registration fee: " + currentRegistrationFee);
            System.out.println("Please enter new registration fee:");
            Double newRegistrationFee = Double.parseDouble(Methods.askDouble());
            accessComp.getCompany().setRegistrationFee(newRegistrationFee);
            System.out.println("Successfully updated registration fee.");
        } else if (action.equals("2")) {
            Map<Integer, Double> currentCommission = accessComp.getCompany().getCommission();
            System.out.println("Previous commission: ");
            currentCommission.forEach((key, value) ->
                    System.out.println(String.format("Generation %d: %f", key, value)));
            System.out.println("Please enter the generation for new commision set:");
            int gen = Integer.parseInt(Methods.askNumber(1,5));
            System.out.println("Enter new commission for generation " + gen + ":");
            Double newCommission = Double.parseDouble(Methods.askDouble());
            accessComp.getCompany().setCommission(gen, newCommission);
            System.out.println("Successfully updated commision fee.");
        }
        postExecute();
    }

    private String chooseAction() {
        System.out.println("1. Change registration fee");
        System.out.println("2. Change commission");
        return Methods.askNumber(1, 2);
    }

    private void postExecute() {
        System.out.println("Press enter to continue..");
        Methods.readLine();
    }
}
