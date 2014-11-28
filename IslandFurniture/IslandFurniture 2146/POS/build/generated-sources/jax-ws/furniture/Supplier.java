
package furniture;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for supplier complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="supplier">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="contactPersonName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="contracts" type="{http://service.Web/}contract" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="faxNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mobileNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="parts" type="{http://service.Web/}part" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="supplierAcct" type="{http://service.Web/}supplierAcct" minOccurs="0"/>
 *         &lt;element name="supplierAddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="supplierDeleteStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="supplierEmailAddr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="supplierId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="supplierName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="telephoneNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "supplier", propOrder = {
    "contactPersonName",
    "contracts",
    "faxNum",
    "mobileNum",
    "parts",
    "supplierAcct",
    "supplierAddress",
    "supplierDeleteStatus",
    "supplierEmailAddr",
    "supplierId",
    "supplierName",
    "telephoneNum"
})
public class Supplier {

    protected String contactPersonName;
    @XmlElement(nillable = true)
    protected List<Contract> contracts;
    protected String faxNum;
    protected String mobileNum;
    @XmlElement(nillable = true)
    protected List<Part> parts;
    protected SupplierAcct supplierAcct;
    protected String supplierAddress;
    protected String supplierDeleteStatus;
    protected String supplierEmailAddr;
    protected Long supplierId;
    protected String supplierName;
    protected String telephoneNum;

    /**
     * Gets the value of the contactPersonName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContactPersonName() {
        return contactPersonName;
    }

    /**
     * Sets the value of the contactPersonName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContactPersonName(String value) {
        this.contactPersonName = value;
    }

    /**
     * Gets the value of the contracts property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the contracts property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getContracts().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Contract }
     * 
     * 
     */
    public List<Contract> getContracts() {
        if (contracts == null) {
            contracts = new ArrayList<Contract>();
        }
        return this.contracts;
    }

    /**
     * Gets the value of the faxNum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFaxNum() {
        return faxNum;
    }

    /**
     * Sets the value of the faxNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFaxNum(String value) {
        this.faxNum = value;
    }

    /**
     * Gets the value of the mobileNum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMobileNum() {
        return mobileNum;
    }

    /**
     * Sets the value of the mobileNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMobileNum(String value) {
        this.mobileNum = value;
    }

    /**
     * Gets the value of the parts property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the parts property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getParts().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Part }
     * 
     * 
     */
    public List<Part> getParts() {
        if (parts == null) {
            parts = new ArrayList<Part>();
        }
        return this.parts;
    }

    /**
     * Gets the value of the supplierAcct property.
     * 
     * @return
     *     possible object is
     *     {@link SupplierAcct }
     *     
     */
    public SupplierAcct getSupplierAcct() {
        return supplierAcct;
    }

    /**
     * Sets the value of the supplierAcct property.
     * 
     * @param value
     *     allowed object is
     *     {@link SupplierAcct }
     *     
     */
    public void setSupplierAcct(SupplierAcct value) {
        this.supplierAcct = value;
    }

    /**
     * Gets the value of the supplierAddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSupplierAddress() {
        return supplierAddress;
    }

    /**
     * Sets the value of the supplierAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSupplierAddress(String value) {
        this.supplierAddress = value;
    }

    /**
     * Gets the value of the supplierDeleteStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSupplierDeleteStatus() {
        return supplierDeleteStatus;
    }

    /**
     * Sets the value of the supplierDeleteStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSupplierDeleteStatus(String value) {
        this.supplierDeleteStatus = value;
    }

    /**
     * Gets the value of the supplierEmailAddr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSupplierEmailAddr() {
        return supplierEmailAddr;
    }

    /**
     * Sets the value of the supplierEmailAddr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSupplierEmailAddr(String value) {
        this.supplierEmailAddr = value;
    }

    /**
     * Gets the value of the supplierId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getSupplierId() {
        return supplierId;
    }

    /**
     * Sets the value of the supplierId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setSupplierId(Long value) {
        this.supplierId = value;
    }

    /**
     * Gets the value of the supplierName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSupplierName() {
        return supplierName;
    }

    /**
     * Sets the value of the supplierName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSupplierName(String value) {
        this.supplierName = value;
    }

    /**
     * Gets the value of the telephoneNum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTelephoneNum() {
        return telephoneNum;
    }

    /**
     * Sets the value of the telephoneNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTelephoneNum(String value) {
        this.telephoneNum = value;
    }

}
