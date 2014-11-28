
package customer;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for loyalty complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="loyalty">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="customer" type="{http://ws.session/}customer" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="pointsAvailable" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="pointsRedeemed" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="totalExpenditure" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "loyalty", propOrder = {
    "customer",
    "id",
    "pointsAvailable",
    "pointsRedeemed",
    "totalExpenditure"
})
public class Loyalty {

    protected Customer customer;
    protected Long id;
    protected Integer pointsAvailable;
    protected Integer pointsRedeemed;
    protected float totalExpenditure;

    /**
     * Gets the value of the customer property.
     * 
     * @return
     *     possible object is
     *     {@link Customer }
     *     
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Sets the value of the customer property.
     * 
     * @param value
     *     allowed object is
     *     {@link Customer }
     *     
     */
    public void setCustomer(Customer value) {
        this.customer = value;
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
     * Gets the value of the pointsAvailable property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getPointsAvailable() {
        return pointsAvailable;
    }

    /**
     * Sets the value of the pointsAvailable property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setPointsAvailable(Integer value) {
        this.pointsAvailable = value;
    }

    /**
     * Gets the value of the pointsRedeemed property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getPointsRedeemed() {
        return pointsRedeemed;
    }

    /**
     * Sets the value of the pointsRedeemed property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setPointsRedeemed(Integer value) {
        this.pointsRedeemed = value;
    }

    /**
     * Gets the value of the totalExpenditure property.
     * 
     */
    public float getTotalExpenditure() {
        return totalExpenditure;
    }

    /**
     * Sets the value of the totalExpenditure property.
     * 
     */
    public void setTotalExpenditure(float value) {
        this.totalExpenditure = value;
    }

}
