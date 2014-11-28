
package furniture;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for mps complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="mps">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="grossRequirment" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="MPSweeklyDemand" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="mrp" type="{http://service.Web/}mrp" minOccurs="0"/>
 *         &lt;element name="onHandAfter" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="onHandBefore" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="partName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="periodDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="plan" type="{http://service.Web/}salesPlan" minOccurs="0"/>
 *         &lt;element name="plannedOrder" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="scheduledReceipts" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="week" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
@XmlType(name = "mps", propOrder = {
    "grossRequirment",
    "id",
    "mpSweeklyDemand",
    "mrp",
    "onHandAfter",
    "onHandBefore",
    "partName",
    "periodDate",
    "plan",
    "plannedOrder",
    "scheduledReceipts",
    "week",
    "workingDays"
})
public class Mps {

    protected int grossRequirment;
    protected Long id;
    @XmlElement(name = "MPSweeklyDemand")
    protected int mpSweeklyDemand;
    protected Mrp mrp;
    protected int onHandAfter;
    protected int onHandBefore;
    protected String partName;
    protected String periodDate;
    protected SalesPlan plan;
    protected int plannedOrder;
    protected int scheduledReceipts;
    protected int week;
    protected int workingDays;

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
     * Gets the value of the mpSweeklyDemand property.
     * 
     */
    public int getMPSweeklyDemand() {
        return mpSweeklyDemand;
    }

    /**
     * Sets the value of the mpSweeklyDemand property.
     * 
     */
    public void setMPSweeklyDemand(int value) {
        this.mpSweeklyDemand = value;
    }

    /**
     * Gets the value of the mrp property.
     * 
     * @return
     *     possible object is
     *     {@link Mrp }
     *     
     */
    public Mrp getMrp() {
        return mrp;
    }

    /**
     * Sets the value of the mrp property.
     * 
     * @param value
     *     allowed object is
     *     {@link Mrp }
     *     
     */
    public void setMrp(Mrp value) {
        this.mrp = value;
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
     * Gets the value of the periodDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPeriodDate() {
        return periodDate;
    }

    /**
     * Sets the value of the periodDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPeriodDate(String value) {
        this.periodDate = value;
    }

    /**
     * Gets the value of the plan property.
     * 
     * @return
     *     possible object is
     *     {@link SalesPlan }
     *     
     */
    public SalesPlan getPlan() {
        return plan;
    }

    /**
     * Sets the value of the plan property.
     * 
     * @param value
     *     allowed object is
     *     {@link SalesPlan }
     *     
     */
    public void setPlan(SalesPlan value) {
        this.plan = value;
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
     * Gets the value of the scheduledReceipts property.
     * 
     */
    public int getScheduledReceipts() {
        return scheduledReceipts;
    }

    /**
     * Sets the value of the scheduledReceipts property.
     * 
     */
    public void setScheduledReceipts(int value) {
        this.scheduledReceipts = value;
    }

    /**
     * Gets the value of the week property.
     * 
     */
    public int getWeek() {
        return week;
    }

    /**
     * Sets the value of the week property.
     * 
     */
    public void setWeek(int value) {
        this.week = value;
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
