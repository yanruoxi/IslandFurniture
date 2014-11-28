/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.security.MessageDigest;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author Meiling
 */
@Entity
public class SupplierAcct implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long supplierAcctId;
    private String supplierUsername;
    private String supplierPwd;
    private boolean isDeleted;
    private String status;
    @OneToOne(mappedBy = "supplierAcct")
    private Supplier supplier;
    private String salt;

    public Long getSupplierAcctId() {
        return supplierAcctId;
    }

    public void setSupplierAcctId(Long supplierAcctId) {
        this.supplierAcctId = supplierAcctId;
    }

    public String getSupplierUsername() {
        return supplierUsername;
    }

    public void setSupplierUsername(String supplierUsername) {
        this.supplierUsername = supplierUsername;
    }

    public String getSupplierPwd() {
        return supplierPwd;
    }

    public void setSupplierPwd(String supplierPwd) {
        this.supplierPwd = supplierPwd;
    }

    public boolean isIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    // Added
    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (supplierAcctId != null ? supplierAcctId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SupplierAcct)) {
            return false;
        }
        SupplierAcct other = (SupplierAcct) object;
        if ((this.supplierAcctId == null && other.supplierAcctId != null) || (this.supplierAcctId != null && !this.supplierAcctId.equals(other.supplierAcctId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.SupplierAcct[ id=" + supplierAcctId + " ]";
    }

    public String hashPassword(String password) {
        try {
            String passwordMD5 = null;
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] tmp = password.getBytes();
            md5.update(tmp);
            passwordMD5 = byteArrToString(md5.digest());
            return passwordMD5;
        } 
        catch (Exception ex) {
            System.out.println("password cannot be hashed");
        }
        return null;
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
}
