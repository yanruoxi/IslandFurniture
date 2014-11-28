
package customer;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for singleTransactionItemHQ complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="singleTransactionItemHQ">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="amount" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="furntiture" type="{http://ws.session/}product" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="transactionOrder" type="{http://ws.session/}transactionOrderHQ" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "singleTransactionItemHQ", propOrder = {
    "amount",
    "furntiture",
    "id",
    "transactionOrder"
})
public class SingleTransactionItemHQ {

    protected Integer amount;
    protected Product furntiture;
    protected Long id;
    protected TransactionOrderHQ transactionOrder;

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
     * Gets the value of the furntiture property.
     * 
     * @return
     *     possible object is
     *     {@link Product }
     *     
     */
    public Product getFurntiture() {
        return furntiture;
    }

    /**
     * Sets the value of the furntiture property.
     * 
     * @param value
     *     allowed object is
     *     {@link Product }
     *     
     */
    public void setFurntiture(Product value) {
        this.furntiture = value;
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
     *     {@link TransactionOrderHQ }
     *     
     */
    public TransactionOrderHQ getTransactionOrder() {
        return transactionOrder;
    }

    /**
     * Sets the value of the transactionOrder property.
     * 
     * @param value
     *     allowed object is
     *     {@link TransactionOrderHQ }
     *     
     */
    public void setTransactionOrder(TransactionOrderHQ value) {
        this.transactionOrder = value;
    }

}
