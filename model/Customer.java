
package sst.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Customer implements Serializable {

    private String id;
    private String encryptedName;
    private double revenue;
    private Customer directUpline;
    private List<Customer> directDownlines = new ArrayList<>();

    public Customer() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEncryptedName() {
        return encryptedName;
    }

    public void setEncryptedName(String encryptedName) {
        this.encryptedName = encryptedName;
    }

    public Double getRevenue() {
        return revenue;
    }

    public void setRevenue(double revenue) {
        this.revenue = revenue;
    }

    public void addToRevenue(double amount) {
        this.revenue += amount;
    }

    public Customer getDirectUpline() {
        return directUpline;
    }

    public boolean isRootUser() {
        return directUpline == null;
    }

    public void setDirectUpline(Customer directUpline) {
        this.directUpline = directUpline;
    }

    public List<Customer> getDirectDownlines() {
        return directDownlines;
    }

    public void setDirectDownlines(List<Customer> directDownlines) {
        this.directDownlines = directDownlines;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer user = (Customer) o;
        return Objects.equals(getId(), user.getId()) &&
                Objects.equals(getEncryptedName(), user.getEncryptedName());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getEncryptedName());
    }

}