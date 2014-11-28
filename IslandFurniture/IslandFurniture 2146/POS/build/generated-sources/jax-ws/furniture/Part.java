
package furniture;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for part complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="part">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="bom" type="{http://service.Web/}bom" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="contract" type="{http://service.Web/}contract" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="inventory" type="{http://service.Web/}inventory" minOccurs="0"/>
 *         &lt;element name="isDeleted" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="leadTime" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="lotSize" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="partName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="suppliers" type="{http://service.Web/}supplier" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "part", propOrder = {
    "bom",
    "contract",
    "id",
    "inventory",
    "isDeleted",
    "leadTime",
    "lotSize",
    "partName",
    "suppliers"
})
public class Part {

    @XmlElement(nillable = true)
    protected List<Bom> bom;
    protected Contract contract;
    protected Long id;
    protected Inventory inventory;
    protected String isDeleted;
    protected int leadTime;
    protected Integer lotSize;
    protected String partName;
    @XmlElement(nillable = true)
    protected List<Supplier> suppliers;

    /**
     * Gets the value of the bom property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the bom property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBom().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Bom }
     * 
     * 
     */
    public List<Bom> getBom() {
        if (bom == null) {
            bom = new ArrayList<Bom>();
        }
        return this.bom;
    }

    /**
     * Gets the value of the contract property.
     * 
     * @return
     *     possible object is
     *     {@link Contract }
     *     
     */
    public Contract getContract() {
        return contract;
    }

    /**
     * Sets the value of the contract property.
     * 
     * @param value
     *     allowed object is
     *     {@link Contract }
     *     
     */
    public void setContract(Contract value) {
        this.contract = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setId(Long value) {
        this.id = value;
    }

    /**
     * Gets the value of the inventory property.
     * 
     * @return
     *     possible object is
     *     {@link Inventory }
     *     
     */
    public Inventory getInventory() {
        return inventory;
    }

    /**
     * Sets the value of the inventory property.
     * 
     * @param value
     *     allowed object is
     *     {@link Inventory }
     *     
     */
    public void setInventory(Inventory value) {
        this.inventory = value;
    }

    /**
     * Gets the value of the isDeleted property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsDeleted() {
        return isDeleted;
    }

    /**
     * Sets the value of the isDeleted property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsDeleted(String value) {
        this.isDeleted = value;
    }

    /**
     * Gets the value of the leadTime property.
     * 
     */
    public int getLeadTime() {
        return leadTime;
    }

    /**
     * Sets the value of the leadTime property.
     * 
     */
    public void setLeadTime(int value) {
        this.leadTime = value;
    }

    /**
     * Gets the value of the lotSize property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getLotSize() {
        return lotSize;
    }

    /**
     * Sets the value of the lotSize property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setLotSize(Integer value) {
        this.lotSize = value;
    }

    /**
     * Gets the value of the partName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPartName() {
        return partName;
    }

    /**
     * Sets the value of the partName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartName(String value) {
        this.partName = value;
    }

    /**
     * Gets the value of the suppliers property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the suppliers property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSuppliers().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Supplier }
     * 
     * 
     */
    public List<Supplier> getSuppliers() {
        if (suppliers == null) {
            suppliers = new ArrayList<Supplier>();
        }
        return this.suppliers;
    }

}
