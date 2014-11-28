
package SendPO;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for evoucher complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="evoucher">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="faceValue" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="point" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="redemptionCart" type="{http://ws.session/}redemptionCart" minOccurs="0"/>
 *         &lt;element name="termCondition" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "evoucher", propOrder = {
    "faceValue",
    "id",
    "point",
    "redemptionCart",
    "termCondition"
})
public class Evoucher {

    protected Integer faceValue;
    protected Long id;
    protected Integer point;
    protected RedemptionCart redemptionCart;
    protected String termCondition;

    /**
     * Gets the value of the faceValue property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getFaceValue() {
        return faceValue;
    }

    /**
     * Sets the value of the faceValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setFaceValue(Integer value) {
        this.faceValue = value;
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
     * Gets the value of the point property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getPoint() {
        return point;
    }

    /**
     * Sets the value of the point property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setPoint(Integer value) {
        this.point = value;
    }

    /**
     * Gets the value of the redemptionCart property.
     * 
     * @return
     *     possible object is
     *     {@link RedemptionCart }
     *     
     */
    public RedemptionCart getRedemptionCart() {
        return redemptionCart;
    }

    /**
     * Sets the value of the redemptionCart property.
     * 
     * @param value
     *     allowed object is
     *     {@link RedemptionCart }
     *     
     */
    public void setRedemptionCart(RedemptionCart value) {
        this.redemptionCart = value;
    }

    /**
     * Gets the value of the termCondition property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTermCondition() {
        return termCondition;
    }

    /**
     * Sets the value of the termCondition property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTermCondition(String value) {
        this.termCondition = value;
    }

}
