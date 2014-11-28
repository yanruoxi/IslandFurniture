
package furniture;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getFurnitureForPOS complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getFurnitureForPOS">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="furnitureName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getFurnitureForPOS", propOrder = {
    "furnitureName"
})
public class GetFurnitureForPOS {

    protected String furnitureName;

    /**
     * Gets the value of the furnitureName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFurnitureName() {
        return furnitureName;
    }

    /**
     * Sets the value of the furnitureName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFurnitureName(String value) {
        this.furnitureName = value;
    }

}
