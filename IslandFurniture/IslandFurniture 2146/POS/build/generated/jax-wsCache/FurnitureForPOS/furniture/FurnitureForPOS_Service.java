
package furniture;

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
@WebServiceClient(name = "FurnitureForPOS", targetNamespace = "http://service.Web/", wsdlLocation = "http://172.23.159.18:8080/FurnitureForPOS/FurnitureForPOS?wsdl")
public class FurnitureForPOS_Service
    extends Service
{

    private final static URL FURNITUREFORPOS_WSDL_LOCATION;
    private final static WebServiceException FURNITUREFORPOS_EXCEPTION;
    private final static QName FURNITUREFORPOS_QNAME = new QName("http://service.Web/", "FurnitureForPOS");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://172.23.159.18:8080/FurnitureForPOS/FurnitureForPOS?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        FURNITUREFORPOS_WSDL_LOCATION = url;
        FURNITUREFORPOS_EXCEPTION = e;
    }

    public FurnitureForPOS_Service() {
        super(__getWsdlLocation(), FURNITUREFORPOS_QNAME);
    }

    public FurnitureForPOS_Service(WebServiceFeature... features) {
        super(__getWsdlLocation(), FURNITUREFORPOS_QNAME, features);
    }

    public FurnitureForPOS_Service(URL wsdlLocation) {
        super(wsdlLocation, FURNITUREFORPOS_QNAME);
    }

    public FurnitureForPOS_Service(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, FURNITUREFORPOS_QNAME, features);
    }

    public FurnitureForPOS_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public FurnitureForPOS_Service(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns FurnitureForPOS
     */
    @WebEndpoint(name = "FurnitureForPOSPort")
    public FurnitureForPOS getFurnitureForPOSPort() {
        return super.getPort(new QName("http://service.Web/", "FurnitureForPOSPort"), FurnitureForPOS.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns FurnitureForPOS
     */
    @WebEndpoint(name = "FurnitureForPOSPort")
    public FurnitureForPOS getFurnitureForPOSPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://service.Web/", "FurnitureForPOSPort"), FurnitureForPOS.class, features);
    }

    private static URL __getWsdlLocation() {
        if (FURNITUREFORPOS_EXCEPTION!= null) {
            throw FURNITUREFORPOS_EXCEPTION;
        }
        return FURNITUREFORPOS_WSDL_LOCATION;
    }

}
