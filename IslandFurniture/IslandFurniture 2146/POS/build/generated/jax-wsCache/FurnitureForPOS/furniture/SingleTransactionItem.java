
package furniture;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for singleTransactionItem complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="singleTransactionItem">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="amount" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="furniture" type="{http://service.Web/}furniture" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="transactionOrder" type="{http://service.Web/}transactionOrder" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "singleTransactionItem", propOrder = {
    "amount",
    "furniture",
    "id",
    "transactionOrder"
})
public class SingleTransactionItem {

    protected Integer amount;
    protected Furniture furniture;
    protected Long id;
    protected TransactionOrder transactionOrder;

    /**
     * Gets the value of the amount property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getAmount() {
        return amount;
    }

    /**
     * Sets the value of the amount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setAmount(Integer value) {
        this.amount = value;
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
     * Gets the value of the transactionOrder property.
     * 
     * @return
     *     possible object is
     *     {@link TransactionOrder }
     *     
     */
    public TransactionOrder getTransactionOrder() {
        return transactionOrder;
    }

    /**
     * Sets the value of the transactionOrder property.
     * 
     * @param value
     *     allowed object is
     *     {@link TransactionOrder }
     *     
     */
    public void setTransactionOrder(TransactionOrder value) {
        this.transactionOrder = value;
    }

}
