
package storeInfo;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the storeInfo package. 
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

    private final static QName _GetCompanySingleResultResponse_QNAME = new QName("http://service.Web/", "getCompanySingleResultResponse");
    private final static QName _GetCompanySingleResult_QNAME = new QName("http://service.Web/", "getCompanySingleResult");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: storeInfo
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetCompanySingleResultResponse }
     * 
     */
    public GetCompanySingleResultResponse createGetCompanySingleResultResponse() {
        return new GetCompanySingleResultResponse();
    }

    /**
     * Create an instance of {@link GetCompanySingleResult }
     * 
     */
    public GetCompanySingleResult createGetCompanySingleResult() {
        return new GetCompanySingleResult();
    }

    /**
     * Create an instance of {@link Company }
     * 
     */
    public Company createCompany() {
        return new Company();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCompanySingleResultResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.Web/", name = "getCompanySingleResultResponse")
    public JAXBElement<GetCompanySingleResultResponse> createGetCompanySingleResultResponse(GetCompanySingleResultResponse value) {
        return new JAXBElement<GetCompanySingleResultResponse>(_GetCompanySingleResultResponse_QNAME, GetCompanySingleResultResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCompanySingleResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.Web/", name = "getCompanySingleResult")
    public JAXBElement<GetCompanySingleResult> createGetCompanySingleResult(GetCompanySingleResult value) {
        return new JAXBElement<GetCompanySingleResult>(_GetCompanySingleResult_QNAME, GetCompanySingleResult.class, null, value);
    }

}
