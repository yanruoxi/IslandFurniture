
package furniture;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for warehouse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="warehouse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="address" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="bigArray" type="{http://service.Web/}furnitureArray" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="deletema" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="front" type="{http://service.Web/}front" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="materialCollection" type="{http://service.Web/}furniture" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="RG" type="{http://service.Web/}furnitureArray" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="returnGoodsCollection" type="{http://service.Web/}returnGoods" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="SUArray" type="{http://service.Web/}furnitureArrayArray" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="warehouseName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "warehouse", propOrder = {
    "address",
    "bigArray",
    "deletema",
    "front",
    "id",
    "materialCollection",
    "rg",
    "returnGoodsCollection",
    "suArray",
    "warehouseName"
})
public class Warehouse {

    protected String address;
    @XmlElement(nillable = true)
    protected List<FurnitureArray> bigArray;
    protected String deletema;
    protected Front front;
    protected long id;
    @XmlElement(nillable = true)
    protected List<Furniture> materialCollection;
    @XmlElement(name = "RG", nillable = true)
    protected List<FurnitureArray> rg;
    @XmlElement(nillable = true)
    protected List<ReturnGoods> returnGoodsCollection;
    @XmlElement(name = "SUArray", nillable = true)
    protected List<FurnitureArrayArray> suArray;
    protected String warehouseName;

    /**
     * Gets the value of the address property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the value of the address property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddress(String value) {
        this.address = value;
    }

    /**
     * Gets the value of the bigArray property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the bigArray property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBigArray().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FurnitureArray }
     * 
     * 
     */
    public List<FurnitureArray> getBigArray() {
        if (bigArray == null) {
            bigArray = new ArrayList<FurnitureArray>();
        }
        return this.bigArray;
    }

    /**
     * Gets the value of the deletema property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeletema() {
        return deletema;
    }

    /**
     * Sets the value of the deletema property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeletema(String value) {
        this.deletema = value;
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
     * Gets the value of the id property.
     * 
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     */
    public void setId(long value) {
        this.id = value;
    }

    /**
     * Gets the value of the materialCollection property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the materialCollection property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMaterialCollection().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Furniture }
     * 
     * 
     */
    public List<Furniture> getMaterialCollection() {
        if (materialCollection == null) {
            materialCollection = new ArrayList<Furniture>();
        }
        return this.materialCollection;
    }

    /**
     * Gets the value of the rg property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the rg property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRG().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FurnitureArray }
     * 
     * 
     */
    public List<FurnitureArray> getRG() {
        if (rg == null) {
            rg = new ArrayList<FurnitureArray>();
        }
        return this.rg;
    }

    /**
     * Gets the value of the returnGoodsCollection property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the returnGoodsCollection property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getReturnGoodsCollection().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ReturnGoods }
     * 
     * 
     */
    public List<ReturnGoods> getReturnGoodsCollection() {
        if (returnGoodsCollection == null) {
            returnGoodsCollection = new ArrayList<ReturnGoods>();
        }
        return this.returnGoodsCollection;
    }

    /**
     * Gets the value of the suArray property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the suArray property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSUArray().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FurnitureArrayArray }
     * 
     * 
     */
    public List<FurnitureArrayArray> getSUArray() {
        if (suArray == null) {
            suArray = new ArrayList<FurnitureArrayArray>();
        }
        return this.suArray;
    }

    /**
     * Gets the value of the warehouseName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWarehouseName() {
        return warehouseName;
    }

    /**
     * Sets the value of the warehouseName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWarehouseName(String value) {
        this.warehouseName = value;
    }

}
