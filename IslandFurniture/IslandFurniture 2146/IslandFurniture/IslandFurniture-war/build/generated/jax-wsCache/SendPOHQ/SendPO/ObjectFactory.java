
package SendPO;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the SendPO package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _SendPOResponse_QNAME = new QName("http://ws.session/", "sendPOResponse");
    private final static QName _SendPO_QNAME = new QName("http://ws.session/", "sendPO");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: SendPO
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SendPO }
     * 
     */
    public SendPO createSendPO() {
        return new SendPO();
    }

    /**
     * Create an instance of {@link SendPOResponse }
     * 
     */
    public SendPOResponse createSendPOResponse() {
        return new SendPOResponse();
    }

    /**
     * Create an instance of {@link TransactionOrderHQ }
     * 
     */
    public TransactionOrderHQ createTransactionOrderHQ() {
        return new TransactionOrderHQ();
    }

    /**
     * Create an instance of {@link SingleTransactionItemHQ }
     * 
     */
    public SingleTransactionItemHQ createSingleTransactionItemHQ() {
        return new SingleTransactionItemHQ();
    }

    /**
     * Create an instance of {@link Factory }
     * 
     */
    public Factory createFactory() {
        return new Factory();
    }

    /**
     * Create an instance of {@link Product }
     * 
     */
    public Product createProduct() {
        return new Product();
    }

    /**
     * Create an instance of {@link Loyalty }
     * 
     */
    public Loyalty createLoyalty() {
        return new Loyalty();
    }

    /**
     * Create an instance of {@link Store }
     * 
     */
    public Store createStore() {
        return new Store();
    }

    /**
     * Create an instance of {@link ProductionOrder }
     * 
     */
    public ProductionOrder createProductionOrder() {
        return new ProductionOrder();
    }

    /**
     * Create an instance of {@link RedemptionCart }
     * 
     */
    public RedemptionCart createRedemptionCart() {
        return new RedemptionCart();
    }

    /**
     * Create an instance of {@link DefaultFactory }
     * 
     */
    public DefaultFactory createDefaultFactory() {
        return new DefaultFactory();
    }

    /**
     * Create an instance of {@link SalesForcast }
     * 
     */
    public SalesForcast createSalesForcast() {
        return new SalesForcast();
    }

    /**
     * Create an instance of {@link Evoucher }
     * 
     */
    public Evoucher createEvoucher() {
        return new Evoucher();
    }

    /**
     * Create an instance of {@link Shoppingcart }
     * 
     */
    public Shoppingcart createShoppingcart() {
        return new Shoppingcart();
    }

    /**
     * Create an instance of {@link Customer }
     * 
     */
    public Customer createCustomer() {
        return new Customer();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SendPOResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.session/", name = "sendPOResponse")
    public JAXBElement<SendPOResponse> createSendPOResponse(SendPOResponse value) {
        return new JAXBElement<SendPOResponse>(_SendPOResponse_QNAME, SendPOResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SendPO }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.session/", name = "sendPO")
    public JAXBElement<SendPO> createSendPO(SendPO value) {
        return new JAXBElement<SendPO>(_SendPO_QNAME, SendPO.class, null, value);
    }

}
