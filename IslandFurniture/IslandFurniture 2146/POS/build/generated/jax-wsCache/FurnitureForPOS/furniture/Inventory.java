
package furniture;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for inventory complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="inventory">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="dateUpdated" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="furniture" type="{http://service.Web/}furniture" minOccurs="0"/>
 *         &lt;element name="inventoryId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="part" type="{http://service.Web/}part" minOccurs="0"/>
 *         &lt;element name="quantity" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "inventory", propOrder = {
    "dateUpdated",
    "furniture",
    "inventoryId",
    "part",
    "quantity"
})
public class Inventory {

    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dateUpdated;
    protected Furniture furniture;
    protected Long inventoryId;
    protected Part part;
    protected int quantity;

    /**
     * Gets the value of the dateUpdated property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDateUpdated() {
        return dateUpdated;
    }

    /**
     * Sets the value of the dateUpdated property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDateUpdated(XMLGregorianCalendar value) {
        this.dateUpdated = value;
    }

    /**
     * Gets the value of the furniture property.
     * 
     * @return
     *     possible object is
     *     {@link Furniture }
     *     
     */
    public Furniture getFurniture() {
        return furniture;
    }

    /**
     * Sets the value of the furniture property.
     * 
     * @param value
     *     allowed object is
     *     {@link Furniture }
     *     
     */
    public void setFurniture(Furniture value) {
        this.furniture = value;
    }

    /**
     * Gets the value of the inventoryId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getInventoryId() {
        return inventoryId;
    }

    /**
     * Sets the value of the inventoryId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setInventoryId(Long value) {
        this.inventoryId = value;
    }

    /**
     * Gets the value of the part property.
     * 
     * @return
     *     possible object is
     *     {@link Part }
     *     
     */
    public Part getPart() {
        return part;
    }

    /**
     * Sets the value of the part property.
     * 
     * @param value
     *     allowed object is
     *     {@link Part }
     *     
     */
    public void setPart(Part value) {
        this.part = value;
    }

    /**
     * Gets the value of the quantity property.
     * 
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the value of the quantity property.
     * 
     */
    public void setQuantity(int value) {
        this.quantity = value;
    }

}
