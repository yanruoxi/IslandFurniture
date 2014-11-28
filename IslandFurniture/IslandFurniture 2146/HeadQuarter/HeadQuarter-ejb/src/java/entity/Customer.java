/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author Ruoxi
 */
@Entity
public class Customer implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String isDeleted="no";
    private String password;
    private String customerName;
    private String email;
    private String dob;
    private String country;
    private String addressLine1;
    private String gender;
    private String postalCode;
    private String status;
    private String phone;
    
    @OneToOne(cascade={CascadeType.PERSIST})
    private Shoppingcart shoppingcart;
@OneToMany(cascade = {CascadeType.PERSIST}, mappedBy = "customer")
     private List<TransactionOrderHQ> transationOrderList= new ArrayList(){};
	
    @OneToOne(mappedBy="customer")
    private RedemptionCart cart;
    @OneToOne(mappedBy="customer")
    private Loyalty loyalty;

    public Shoppingcart getShoppingcart() {
        return shoppingcart;
    }

    public void setShoppingcart(Shoppingcart shoppingcart) {
        this.shoppingcart = shoppingcart;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Customer)) {
            return false;
        }
        Customer other = (Customer) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
    
     public String byteArrToString(byte[] b) {
        String res = null;
        StringBuffer sb = new StringBuffer(b.length * 2);
        for (int i = 0; i < b.length; i++) {
            int j = b[i] & 0xff;
            if (j < 16) {
                sb.append('0');
            }
            sb.append(Integer.toHexString(j));
        }
        res = sb.toString();
        return res.toUpperCase();
    }

    @Override
    public String toString() {
        return "entity.Customer[ id=" + id + " ]";
    }

    /**
     * @return the isDeleted
     */
    public String getIsDeleted() {
        return isDeleted;
    }

    /**
     * @param isDeleted the isDeleted to set
     */
    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the customerName
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * @param customerName the customerName to set
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the dob
     */
    public String getDob() {
        return dob;
    }

    /**
     * @param dob the dob to set
     */
    public void setDob(String dob) {
        this.dob = dob;
    }

    /**
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country the country to set
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * @return the addressLine1
     */
    public String getAddressLine1() {
        return addressLine1;
    }

    /**
     * @param addressLine1 the addressLine1 to set
     */
    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }


    /**
     * @return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(String gender) {
        this.gender = gender;
    }
    
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Loyalty getLoyalty() {
        return loyalty;
    }

    public void setLoyalty(Loyalty loyalty) {
        this.loyalty = loyalty;
    }
    
    
    public String hashPassword(String password) {
        try {

            String passwordMD5 = null;
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] tmp = password.getBytes();
            md5.update(tmp);

            passwordMD5 = byteArrToString(md5.digest());
            return passwordMD5;

        } catch (Exception ex) {
            System.out.println("password cannot be hashed");

        }
        return null;
    }
/**
     * @return the transationOrderList
     */
    public List<TransactionOrderHQ> getTransationOrderList() {
        return transationOrderList;
    }

    /**
     * @param transationOrderList the transationOrderList to set
     */
    public void setTransationOrderList(List<TransactionOrderHQ> transationOrderList) {
        this.transationOrderList = transationOrderList;
    }

    /**
     * @return the cart
     */
    public RedemptionCart getCart() {
        return cart;
    }

    /**
     * @param cart the cart to set
     */
    public void setCart(RedemptionCart cart) {
        this.cart = cart;
    }
    
}
