
package hqNonMember;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.6-1b01 
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "HQTransactionOrderNonMember", targetNamespace = "http://ws.session/", wsdlLocation = "http://172.23.73.34:8080/HQTransactionOrderNonMember/HQTransactionOrderNonMember?wsdl")
public class HQTransactionOrderNonMember_Service
    extends Service
{

    private final static URL HQTRANSACTIONORDERNONMEMBER_WSDL_LOCATION;
    private final static WebServiceException HQTRANSACTIONORDERNONMEMBER_EXCEPTION;
    private final static QName HQTRANSACTIONORDERNONMEMBER_QNAME = new QName("http://ws.session/", "HQTransactionOrderNonMember");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://172.23.73.34:8080/HQTransactionOrderNonMember/HQTransactionOrderNonMember?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        HQTRANSACTIONORDERNONMEMBER_WSDL_LOCATION = url;
        HQTRANSACTIONORDERNONMEMBER_EXCEPTION = e;
    }

    public HQTransactionOrderNonMember_Service() {
        super(__getWsdlLocation(), HQTRANSACTIONORDERNONMEMBER_QNAME);
    }

    public HQTransactionOrderNonMember_Service(WebServiceFeature... features) {
        super(__getWsdlLocation(), HQTRANSACTIONORDERNONMEMBER_QNAME, features);
    }

    public HQTransactionOrderNonMember_Service(URL wsdlLocation) {
        super(wsdlLocation, HQTRANSACTIONORDERNONMEMBER_QNAME);
    }

    public HQTransactionOrderNonMember_Service(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, HQTRANSACTIONORDERNONMEMBER_QNAME, features);
    }

    public HQTransactionOrderNonMember_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public HQTransactionOrderNonMember_Service(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns HQTransactionOrderNonMember
     */
    @WebEndpoint(name = "HQTransactionOrderNonMemberPort")
    public HQTransactionOrderNonMember getHQTransactionOrderNonMemberPort() {
        return super.getPort(new QName("http://ws.session/", "HQTransactionOrderNonMemberPort"), HQTransactionOrderNonMember.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns HQTransactionOrderNonMember
     */
    @WebEndpoint(name = "HQTransactionOrderNonMemberPort")
    public HQTransactionOrderNonMember getHQTransactionOrderNonMemberPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://ws.session/", "HQTransactionOrderNonMemberPort"), HQTransactionOrderNonMember.class, features);
    }

    private static URL __getWsdlLocation() {
        if (HQTRANSACTIONORDERNONMEMBER_EXCEPTION!= null) {
            throw HQTRANSACTIONORDERNONMEMBER_EXCEPTION;
        }
        return HQTRANSACTIONORDERNONMEMBER_WSDL_LOCATION;
    }

}
