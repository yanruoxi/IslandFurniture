
package storeInfo;

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
@WebServiceClient(name = "GetStoreInfoPOS", targetNamespace = "http://service.Web/", wsdlLocation = "http://172.23.159.18:8080/GetStoreInfoPOS/GetStoreInfoPOS?wsdl")
public class GetStoreInfoPOS_Service
    extends Service
{

    private final static URL GETSTOREINFOPOS_WSDL_LOCATION;
    private final static WebServiceException GETSTOREINFOPOS_EXCEPTION;
    private final static QName GETSTOREINFOPOS_QNAME = new QName("http://service.Web/", "GetStoreInfoPOS");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://172.23.159.18:8080/GetStoreInfoPOS/GetStoreInfoPOS?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        GETSTOREINFOPOS_WSDL_LOCATION = url;
        GETSTOREINFOPOS_EXCEPTION = e;
    }

    public GetStoreInfoPOS_Service() {
        super(__getWsdlLocation(), GETSTOREINFOPOS_QNAME);
    }

    public GetStoreInfoPOS_Service(WebServiceFeature... features) {
        super(__getWsdlLocation(), GETSTOREINFOPOS_QNAME, features);
    }

    public GetStoreInfoPOS_Service(URL wsdlLocation) {
        super(wsdlLocation, GETSTOREINFOPOS_QNAME);
    }

    public GetStoreInfoPOS_Service(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, GETSTOREINFOPOS_QNAME, features);
    }

    public GetStoreInfoPOS_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public GetStoreInfoPOS_Service(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns GetStoreInfoPOS
     */
    @WebEndpoint(name = "GetStoreInfoPOSPort")
    public GetStoreInfoPOS getGetStoreInfoPOSPort() {
        return super.getPort(new QName("http://service.Web/", "GetStoreInfoPOSPort"), GetStoreInfoPOS.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns GetStoreInfoPOS
     */
    @WebEndpoint(name = "GetStoreInfoPOSPort")
    public GetStoreInfoPOS getGetStoreInfoPOSPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://service.Web/", "GetStoreInfoPOSPort"), GetStoreInfoPOS.class, features);
    }

    private static URL __getWsdlLocation() {
        if (GETSTOREINFOPOS_EXCEPTION!= null) {
            throw GETSTOREINFOPOS_EXCEPTION;
        }
        return GETSTOREINFOPOS_WSDL_LOCATION;
    }

}