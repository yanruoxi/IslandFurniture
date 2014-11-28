
package furniture;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for mrp complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="mrp">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="grossRequirment" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="mps" type="{http://service.Web/}mps" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="onHandAfter" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="onHandBefore" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="plannedOrder" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="schedulesReceipts" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "mrp", propOrder = {
    "grossRequirment",
    "id",
    "mps",
    "onHandAfter",
    "onHandBefore",
    "plannedOrder",
    "schedulesReceipts"
})
public class Mrp {

    protected int grossRequirment;
    protected Long id;
    @XmlElement(nillable = true)
    protected List<Mps> mps;
    protected int onHandAfter;
    protected int onHandBefore;
    protected int plannedOrder;
    protected int schedulesReceipts;

    /**
     * Gets the value of the grossRequirment property.
     * 
     */
    public int getGrossRequirment() {
        return grossRequirment;
    }

    /**
     * Sets the value of the grossRequirment property.
     * 
     */
    public void setGrossRequirment(int value) {
        this.grossRequirment = value;
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
     * Gets the value of the onHandAfter property.
     * 
     */
    public int getOnHandAfter() {
        return onHandAfter;
    }

    /**
     * Sets the value of the onHandAfter property.
     * 
     */
    public void setOnHandAfter(int value) {
        this.onHandAfter = value;
    }

    /**
     * Gets the value of the onHandBefore property.
     * 
     */
    public int getOnHandBefore() {
        return onHandBefore;
    }

    /**
     * Sets the value of the onHandBefore property.
     * 
     */
    public void setOnHandBefore(int value) {
        this.onHandBefore = value;
    }

    /**
     * Gets the value of the plannedOrder property.
     * 
     */
    public int getPlannedOrder() {
        return plannedOrder;
    }

    /**
     * Sets the value of the plannedOrder property.
     * 
     */
    public void setPlannedOrder(int value) {
        this.plannedOrder = value;
    }

    /**
     * Gets the value of the schedulesReceipts property.
     * 
     */
    public int getSchedulesReceipts() {
        return schedulesReceipts;
    }

    /**
     * Sets the value of the schedulesReceipts property.
     * 
     */
    public void setSchedulesReceipts(int value) {
        this.schedulesReceipts = value;
    }

}
