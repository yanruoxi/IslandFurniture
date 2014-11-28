
package hqNonMember;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the hqNonMember package. 
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

    private final static QName _CreateHQTransationOrderforPOS_QNAME = new QName("http://ws.session/", "createHQTransationOrderforPOS");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: hqNonMember
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CreateHQTransationOrderforPOS }
     * 
     */
    public CreateHQTransationOrderforPOS createCreateHQTransationOrderforPOS() {
        return new CreateHQTransationOrderforPOS();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateHQTransationOrderforPOS }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.session/", name = "createHQTransationOrderforPOS")
    public JAXBElement<CreateHQTransationOrderforPOS> createCreateHQTransationOrderforPOS(CreateHQTransationOrderforPOS value) {
        return new JAXBElement<CreateHQTransationOrderforPOS>(_CreateHQTransationOrderforPOS_QNAME, CreateHQTransationOrderforPOS.class, null, value);
    }

}
