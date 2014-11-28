
package customer;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the customer package. 
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

    private final static QName _GetCustomerForPOSResponse_QNAME = new QName("http://ws.session/", "getCustomerForPOSResponse");
    private final static QName _GetCustomerForPOS_QNAME = new QName("http://ws.session/", "getCustomerForPOS");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: customer
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetCustomerForPOS }
     * 
     */
    public GetCustomerForPOS createGetCustomerForPOS() {
        return new GetCustomerForPOS();
    }

    /**
     * Create an instance of {@link GetCustomerForPOSResponse }
     * 
     */
    public GetCustomerForPOSResponse createGetCustomerForPOSResponse() {
        return new GetCustomerForPOSResponse();
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
     * Create an instance of {@link Evoucher }
     * 
     */
    public Evoucher createEvoucher() {
        return new Evoucher();
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
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCustomerForPOSResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.session/", name = "getCustomerForPOSResponse")
    public JAXBElement<GetCustomerForPOSResponse> createGetCustomerForPOSResponse(GetCustomerForPOSResponse value) {
        return new JAXBElement<GetCustomerForPOSResponse>(_GetCustomerForPOSResponse_QNAME, GetCustomerForPOSResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCustomerForPOS }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.session/", name = "getCustomerForPOS")
    public JAXBElement<GetCustomerForPOS> createGetCustomerForPOS(GetCustomerForPOS value) {
        return new JAXBElement<GetCustomerForPOS>(_GetCustomerForPOS_QNAME, GetCustomerForPOS.class, null, value);
    }

}
