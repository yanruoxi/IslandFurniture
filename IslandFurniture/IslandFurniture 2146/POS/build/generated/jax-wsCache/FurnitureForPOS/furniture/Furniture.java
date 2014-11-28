
package furniture;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for furniture complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="furniture">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="backQuantity" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="bom" type="{http://service.Web/}bom" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="category" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="color" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="front" type="{http://service.Web/}front" minOccurs="0"/>
 *         &lt;element name="frontQuantity" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="furnitureName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="height" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="inventory" type="{http://service.Web/}inventory" minOccurs="0"/>
 *         &lt;element name="isDeleted" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lengths" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="materialPosition" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="order" type="{http://service.Web/}adHocOrder" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="POSquantity" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="plan" type="{http://service.Web/}salesPlan" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="pomotion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="position" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="price" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="productDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="safetyStock" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="singleTransactionItemList" type="{http://service.Web/}singleTransactionItem" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="type" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="warehouse" type="{http://service.Web/}warehouse" minOccurs="0"/>
 *         &lt;element name="width" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "furniture", propOrder = {
    "backQuantity",
    "bom",
    "category",
    "color",
    "front",
    "frontQuantity",
    "furnitureName",
    "height",
    "id",
    "inventory",
    "isDeleted",
    "lengths",
    "materialPosition",
    "order",
    "poSquantity",
    "plan",
    "pomotion",
    "position",
    "price",
    "productDescription",
    "safetyStock",
    "singleTransactionItemList",
    "type",
    "warehouse",
    "width"
})
public class Furniture {

    protected Integer backQuantity;
    @XmlElement(nillable = true)
    protected List<Bom> bom;
    protected String category;
    protected String color;
    protected Front front;
    protected Integer frontQuantity;
    protected String furnitureName;
    protected Double height;
    protected Long id;
    protected Inventory inventory;
    protected String isDeleted;
    protected Double lengths;
    protected String materialPosition;
    @XmlElement(nillable = true)
    protected List<AdHocOrder> order;
    @XmlElement(name = "POSquantity")
    protected Integer poSquantity;
    @XmlElement(nillable = true)
    protected List<SalesPlan> plan;
    protected String pomotion;
    protected String position;
    protected BigDecimal price;
    protected String productDescription;
    protected Integer safetyStock;
    @XmlElement(nillable = true)
    protected List<SingleTransactionItem> singleTransactionItemList;
    protected String type;
    protected Warehouse warehouse;
    protected Double width;

    /**
     * Gets the value of the backQuantity property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getBackQuantity() {
        return backQuantity;
    }

    /**
     * Sets the value of the backQuantity property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setBackQuantity(Integer value) {
        this.backQuantity = value;
    }

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
     * Gets the value of the category property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCategory() {
        return category;
    }

    /**
     * Sets the value of the category property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCategory(String value) {
        this.category = value;
    }

    /**
     * Gets the value of the color property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getColor() {
        return color;
    }

    /**
     * Sets the value of the color property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setColor(String value) {
        this.color = value;
    }

    /**
     * Gets the value of the front property.
     * 
     * @return
     *     possible object is
     *     {@link Front }
     *     
     */
    public Front getFront() {
        return front;
    }

    /**
     * Sets the value of the front property.
     * 
     * @param value
     *     allowed object is
     *     {@link Front }
     *     
     */
    public void setFront(Front value) {
        this.front = value;
    }

    /**
     * Gets the value of the frontQuantity property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getFrontQuantity() {
        return frontQuantity;
    }

    /**
     * Sets the value of the frontQuantity property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setFrontQuantity(Integer value) {
        this.frontQuantity = value;
    }

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

    /**
     * Gets the value of the height property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getHeight() {
        return height;
    }

    /**
     * Sets the value of the height property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setHeight(Double value) {
        this.height = value;
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
     * Gets the value of the lengths property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getLengths() {
        return lengths;
    }

    /**
     * Sets the value of the lengths property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setLengths(Double value) {
        this.lengths = value;
    }

    /**
     * Gets the value of the materialPosition property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMaterialPosition() {
        return materialPosition;
    }

    /**
     * Sets the value of the materialPosition property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMaterialPosition(String value) {
        this.materialPosition = value;
    }

    /**
     * Gets the value of the order property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the order property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOrder().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AdHocOrder }
     * 
     * 
     */
    public List<AdHocOrder> getOrder() {
        if (order == null) {
            order = new ArrayList<AdHocOrder>();
        }
        return this.order;
    }

    /**
     * Gets the value of the poSquantity property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getPOSquantity() {
        return poSquantity;
    }

    /**
     * Sets the value of the poSquantity property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setPOSquantity(Integer value) {
        this.poSquantity = value;
    }

    /**
     * Gets the value of the plan property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the plan property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPlan().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SalesPlan }
     * 
     * 
     */
    public List<SalesPlan> getPlan() {
        if (plan == null) {
            plan = new ArrayList<SalesPlan>();
        }
        return this.plan;
    }

    /**
     * Gets the value of the pomotion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPomotion() {
        return pomotion;
    }

    /**
     * Sets the value of the pomotion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPomotion(String value) {
        this.pomotion = value;
    }

    /**
     * Gets the value of the position property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPosition() {
        return position;
    }

    /**
     * Sets the value of the position property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPosition(String value) {
        this.position = value;
    }

    /**
     * Gets the value of the price property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * Sets the value of the price property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPrice(BigDecimal value) {
        this.price = value;
    }

    /**
     * Gets the value of the productDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductDescription() {
        return productDescription;
    }

    /**
     * Sets the value of the productDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductDescription(String value) {
        this.productDescription = value;
    }

    /**
     * Gets the value of the safetyStock property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getSafetyStock() {
        return safetyStock;
    }

    /**
     * Sets the value of the safetyStock property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setSafetyStock(Integer value) {
        this.safetyStock = value;
    }

    /**
     * Gets the value of the singleTransactionItemList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the singleTransactionItemList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSingleTransactionItemList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SingleTransactionItem }
     * 
     * 
     */
    public List<SingleTransactionItem> getSingleTransactionItemList() {
        if (singleTransactionItemList == null) {
            singleTransactionItemList = new ArrayList<SingleTransactionItem>();
        }
        return this.singleTransactionItemList;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setType(String value) {
        this.type = value;
    }

    /**
     * Gets the value of the warehouse property.
     * 
     * @return
     *     possible object is
     *     {@link Warehouse }
     *     
     */
    public Warehouse getWarehouse() {
        return warehouse;
    }

    /**
     * Sets the value of the warehouse property.
     * 
     * @param value
     *     allowed object is
     *     {@link Warehouse }
     *     
     */
    public void setWarehouse(Warehouse value) {
        this.warehouse = value;
    }

    /**
     * Gets the value of the width property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getWidth() {
        return width;
    }

    /**
     * Sets the value of the width property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setWidth(Double value) {
        this.width = value;
    }

}
