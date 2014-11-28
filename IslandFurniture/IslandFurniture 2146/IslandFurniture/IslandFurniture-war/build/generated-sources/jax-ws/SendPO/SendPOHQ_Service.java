
package SendPO;

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
 * JAX-WS RI 2.2.8
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "SendPOHQ", targetNamespace = "http://ws.session/", wsdlLocation = "http://172.23.73.240:8080/SendPOHQ/SendPOHQ?wsdl")
public class SendPOHQ_Service
    extends Service
{

    private final static URL SENDPOHQ_WSDL_LOCATION;
    private final static WebServiceException SENDPOHQ_EXCEPTION;
    private final static QName SENDPOHQ_QNAME = new QName("http://ws.session/", "SendPOHQ");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://172.23.73.240:8080/SendPOHQ/SendPOHQ?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        SENDPOHQ_WSDL_LOCATION = url;
        SENDPOHQ_EXCEPTION = e;
    }

    public SendPOHQ_Service() {
        super(__getWsdlLocation(), SENDPOHQ_QNAME);
    }

    public SendPOHQ_Service(WebServiceFeature... features) {
        super(__getWsdlLocation(), SENDPOHQ_QNAME, features);
    }

    public SendPOHQ_Service(URL wsdlLocation) {
        super(wsdlLocation, SENDPOHQ_QNAME);
    }

    public SendPOHQ_Service(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, SENDPOHQ_QNAME, features);
    }

    public SendPOHQ_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public SendPOHQ_Service(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns SendPOHQ
     */
    @WebEndpoint(name = "SendPOHQPort")
    public SendPOHQ getSendPOHQPort() {
        return super.getPort(new QName("http://ws.session/", "SendPOHQPort"), SendPOHQ.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns SendPOHQ
     */
    @WebEndpoint(name = "SendPOHQPort")
    public SendPOHQ getSendPOHQPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://ws.session/", "SendPOHQPort"), SendPOHQ.class, features);
    }

    private static URL __getWsdlLocation() {
        if (SENDPOHQ_EXCEPTION!= null) {
            throw SENDPOHQ_EXCEPTION;
        }
        return SENDPOHQ_WSDL_LOCATION;
    }

}