
package sst.model;

import java.io.Serializable;
import java.util.Map;

public class DreamCorp implements Serializable {

    private Customer rootUser;
    private double companyRevenue;
    private double registrationFee;
    private Map<Integer, Double> commission;

    public DreamCorp() {
    }

    public Customer getRootUser() {
        return rootUser;
    }

    public void setRootUser(Customer rootUser) {
        this.rootUser = rootUser;
    }

    public Double getCompanyRevenue() {
        return companyRevenue;
    }

    public void setCompanyRevenue(double companyRevenue) {
        this.companyRevenue = companyRevenue;
    }

    public void addToCompanyRevenue(double amount){
        this.companyRevenue += amount;
    }

    public double getRegistrationFee() {
        return registrationFee;
    }

    public void setRegistrationFee(double registrationFee) {
        this.registrationFee = registrationFee;
    }

    public void setCommission(int generation, double commission) {
        this.commission.put(generation, commission);
    }

    public Map<Integer, Double> getCommission() {
        return commission;
    }

    public void setCommission(Map<Integer, Double> commission) {
        this.commission = commission;
    }
}