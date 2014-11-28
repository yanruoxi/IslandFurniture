
package furniture;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for salesPlan complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="salesPlan">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="forecast" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="furniture" type="{http://service.Web/}furniture" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="inventory" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="mps" type="{http://service.Web/}mps" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="productionPlan" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="time" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="workingDays" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "salesPlan", propOrder = {
    "forecast",
    "furniture",
    "id",
    "inventory",
    "mps",
    "productionPlan",
    "time",
    "workingDays"
})
public class SalesPlan {

    protected int forecast;
    protected Furniture furniture;
    protected Long id;
    protected int inventory;
    @XmlElement(nillable = true)
    protected List<Mps> mps;
    protected int productionPlan;
    protected String time;
    protected int workingDays;

    /**
     * Gets the value of the forecast property.
     * 
     */
    public int getForecast() {
        return forecast;
    }

    /**
     * Sets the value of the forecast property.
     * 
     */
    public void setForecast(int value) {
        this.forecast = value;
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
     */
    public int getInventory() {
        return inventory;
    }

    /**
     * Sets the value of the inventory property.
     * 
     */
    public void setInventory(int value) {
        this.inventory = value;
    }

    /**
     * Gets the value of the mps property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the mps property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMps().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Mps }
     * 
     * 
     */
    public List<Mps> getMps() {
        if (mps == null) {
            mps = new ArrayList<Mps>();
        }
        return this.mps;
    }

    /**
     * Gets the value of the productionPlan property.
     * 
     */
    public int getProductionPlan() {
        return productionPlan;
    }

    /**
     * Sets the value of the productionPlan property.
     * 
     */
    public void setProductionPlan(int value) {
        this.productionPlan = value;
    }

    /**
     * Gets the value of the time property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTime() {
        return time;
    }

    /**
     * Sets the value of the time property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTime(String value) {
        this.time = value;
    }

    /**
     * Gets the value of the workingDays property.
     * 
     */
    public int getWorkingDays() {
        return workingDays;
    }

    /**
     * Sets the value of the workingDays property.
     * 
     */
    public void setWorkingDays(int value) {
        this.workingDays = value;
    }

}
