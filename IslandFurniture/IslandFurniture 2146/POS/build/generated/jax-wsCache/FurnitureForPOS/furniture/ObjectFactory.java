
package furniture;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the furniture package. 
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

    private final static QName _GetFurnitureForPOSResponse_QNAME = new QName("http://service.Web/", "getFurnitureForPOSResponse");
    private final static QName _GetFurnitureForPOS_QNAME = new QName("http://service.Web/", "getFurnitureForPOS");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: furniture
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetFurnitureForPOS }
     * 
     */
    public GetFurnitureForPOS createGetFurnitureForPOS() {
        return new GetFurnitureForPOS();
    }

    /**
     * Create an instance of {@link GetFurnitureForPOSResponse }
     * 
     */
    public GetFurnitureForPOSResponse createGetFurnitureForPOSResponse() {
        return new GetFurnitureForPOSResponse();
    }

    /**
     * Create an instance of {@link AdHocOrder }
     * 
     */
    public AdHocOrder createAdHocOrder() {
        return new AdHocOrder();
    }

    /**
     * Create an instance of {@link Bom }
     * 
     */
    public Bom createBom() {
        return new Bom();
    }

    /**
     * Create an instance of {@link SingleTransactionItem }
     * 
     */
    public SingleTransactionItem createSingleTransactionItem() {
        return new SingleTransactionItem();
    }

    /**
     * Create an instance of {@link TransactionOrder }
     * 
     */
    public TransactionOrder createTransactionOrder() {
        return new TransactionOrder();
    }

    /**
     * Create an instance of {@link Part }
     * 
     */
    public Part createPart() {
        return new Part();
    }

    /**
     * Create an instance of {@link Contract }
     * 
     */
    public Contract createContract() {
        return new Contract();
    }

    /**
     * Create an instance of {@link Mrp }
     * 
     */
    public Mrp createMrp() {
        return new Mrp();
    }

    /**
     * Create an instance of {@link Inventory }
     * 
     */
    public Inventory createInventory() {
        return new Inventory();
    }

    /**
     * Create an instance of {@link Warehouse }
     * 
     */
    public Warehouse createWarehouse() {
        return new Warehouse();
    }

    /**
     * Create an instance of {@link SupplierAcct }
     * 
     */
    public SupplierAcct createSupplierAcct() {
        return new SupplierAcct();
    }

    /**
     * Create an instance of {@link FurnitureArrayArray }
     * 
     */
    public FurnitureArrayArray createFurnitureArrayArray() {
        return new FurnitureArrayArray();
    }

    /**
     * Create an instance of {@link SalesPlan }
     * 
     */
    public SalesPlan createSalesPlan() {
        return new SalesPlan();
    }

    /**
     * Create an instance of {@link Mps }
     * 
     */
    public Mps createMps() {
        return new Mps();
    }

    /**
     * Create an instance of {@link FurnitureArray }
     * 
     */
    public FurnitureArray createFurnitureArray() {
        return new FurnitureArray();
    }

    /**
     * Create an instance of {@link ReturnGoods }
     * 
     */
    public ReturnGoods createReturnGoods() {
        return new ReturnGoods();
    }

    /**
     * Create an instance of {@link Furniture }
     * 
     */
    public Furniture createFurniture() {
        return new Furniture();
    }

    /**
     * Create an instance of {@link Supplier }
     * 
     */
    public Supplier createSupplier() {
        return new Supplier();
    }

    /**
     * Create an instance of {@link Front }
     * 
     */
    public Front createFront() {
        return new Front();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetFurnitureForPOSResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.Web/", name = "getFurnitureForPOSResponse")
    public JAXBElement<GetFurnitureForPOSResponse> createGetFurnitureForPOSResponse(GetFurnitureForPOSResponse value) {
        return new JAXBElement<GetFurnitureForPOSResponse>(_GetFurnitureForPOSResponse_QNAME, GetFurnitureForPOSResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetFurnitureForPOS }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.Web/", name = "getFurnitureForPOS")
    public JAXBElement<GetFurnitureForPOS> createGetFurnitureForPOS(GetFurnitureForPOS value) {
        return new JAXBElement<GetFurnitureForPOS>(_GetFurnitureForPOS_QNAME, GetFurnitureForPOS.class, null, value);
    }

}
