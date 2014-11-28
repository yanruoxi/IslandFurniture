
package furniture;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for supplierAcct complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="supplierAcct">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="isDeleted" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="salt" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="supplier" type="{http://service.Web/}supplier" minOccurs="0"/>
 *         &lt;element name="supplierAcctId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="supplierPwd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="supplierUsername" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "supplierAcct", propOrder = {
    "isDeleted",
    "salt",
    "status",
    "supplier",
    "supplierAcctId",
    "supplierPwd",
    "supplierUsername"
})
public class SupplierAcct {

    protected boolean isDeleted;
    protected String salt;
    protected String status;
    protected Supplier supplier;
    protected Long supplierAcctId;
    protected String supplierPwd;
    protected String supplierUsername;

    /**
     * Gets the value of the isDeleted property.
     * 
     */
    public boolean isIsDeleted() {
        return isDeleted;
    }

    /**
     * Sets the value of the isDeleted property.
     * 
     */
    public void setIsDeleted(boolean value) {
        this.isDeleted = value;
    }

    /**
     * Gets the value of the salt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSalt() {
        return salt;
    }

    /**
     * Sets the value of the salt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSalt(String value) {
        this.salt = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatus(String value) {
        this.status = value;
    }

    /**
     * Gets the value of the supplier property.
     * 
     * @return
     *     possible object is
     *     {@link Supplier }
     *     
     */
    public Supplier getSupplier() {
        return supplier;
    }

    /**
     * Sets the value of the supplier property.
     * 
     * @param value
     *     allowed object is
     *     {@link Supplier }
     *     
     */
    public void setSupplier(Supplier value) {
        this.supplier = value;
    }

    /**
     * Gets the value of the supplierAcctId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getSupplierAcctId() {
        return supplierAcctId;
    }

    /**
     * Sets the value of the supplierAcctId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setSupplierAcctId(Long value) {
        this.supplierAcctId = value;
    }

    /**
     * Gets the value of the supplierPwd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSupplierPwd() {
        return supplierPwd;
    }

    /**
     * Sets the value of the supplierPwd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSupplierPwd(String value) {
        this.supplierPwd = value;
    }

    /**
     * Gets the value of the supplierUsername property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSupplierUsername() {
        return supplierUsername;
    }

    /**
     * Sets the value of the supplierUsername property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSupplierUsername(String value) {
        this.supplierUsername = value;
    }

}
