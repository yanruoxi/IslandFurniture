
package storeTO;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the storeTO package. 
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

    private final static QName _CreateTransationOrderforPOS_QNAME = new QName("http://service.Web/", "createTransationOrderforPOS");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: storeTO
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CreateTransationOrderforPOS }
     * 
     */
    public CreateTransationOrderforPOS createCreateTransationOrderforPOS() {
        return new CreateTransationOrderforPOS();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateTransationOrderforPOS }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.Web/", name = "createTransationOrderforPOS")
    public JAXBElement<CreateTransationOrderforPOS> createCreateTransationOrderforPOS(CreateTransationOrderforPOS value) {
        return new JAXBElement<CreateTransationOrderforPOS>(_CreateTransationOrderforPOS_QNAME, CreateTransationOrderforPOS.class, null, value);
    }

}
