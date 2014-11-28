
package Login;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the Login package. 
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

    private final static QName _LoginForPOSResponse_QNAME = new QName("http://service.Web/", "LoginForPOSResponse");
    private final static QName _GetSystemUserResponse_QNAME = new QName("http://service.Web/", "getSystemUserResponse");
    private final static QName _GetSystemUser_QNAME = new QName("http://service.Web/", "getSystemUser");
    private final static QName _LoginForPOS_QNAME = new QName("http://service.Web/", "LoginForPOS");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: Login
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetSystemUser }
     * 
     */
    public GetSystemUser createGetSystemUser() {
        return new GetSystemUser();
    }

    /**
     * Create an instance of {@link LoginForPOS }
     * 
     */
    public LoginForPOS createLoginForPOS() {
        return new LoginForPOS();
    }

    /**
     * Create an instance of {@link LoginForPOSResponse }
     * 
     */
    public LoginForPOSResponse createLoginForPOSResponse() {
        return new LoginForPOSResponse();
    }

    /**
     * Create an instance of {@link GetSystemUserResponse }
     * 
     */
    public GetSystemUserResponse createGetSystemUserResponse() {
        return new GetSystemUserResponse();
    }

    /**
     * Create an instance of {@link SystemUser }
     * 
     */
    public SystemUser createSystemUser() {
        return new SystemUser();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LoginForPOSResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.Web/", name = "LoginForPOSResponse")
    public JAXBElement<LoginForPOSResponse> createLoginForPOSResponse(LoginForPOSResponse value) {
        return new JAXBElement<LoginForPOSResponse>(_LoginForPOSResponse_QNAME, LoginForPOSResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetSystemUserResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.Web/", name = "getSystemUserResponse")
    public JAXBElement<GetSystemUserResponse> createGetSystemUserResponse(GetSystemUserResponse value) {
        return new JAXBElement<GetSystemUserResponse>(_GetSystemUserResponse_QNAME, GetSystemUserResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetSystemUser }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.Web/", name = "getSystemUser")
    public JAXBElement<GetSystemUser> createGetSystemUser(GetSystemUser value) {
        return new JAXBElement<GetSystemUser>(_GetSystemUser_QNAME, GetSystemUser.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LoginForPOS }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.Web/", name = "LoginForPOS")
    public JAXBElement<LoginForPOS> createLoginForPOS(LoginForPOS value) {
        return new JAXBElement<LoginForPOS>(_LoginForPOS_QNAME, LoginForPOS.class, null, value);
    }

}
