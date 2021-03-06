
package SendPO;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.8
 * Generated source version: 2.2
 * 
 */
@WebService(name = "SendPOHQ", targetNamespace = "http://ws.session/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface SendPOHQ {


    /**
     * 
     * @param month
     * @param year
     * @param factoryName
     * @param productName
     * @return
     *     returns java.util.List<SendPO.ProductionOrder>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "sendPO", targetNamespace = "http://ws.session/", className = "SendPO.SendPO")
    @ResponseWrapper(localName = "sendPOResponse", targetNamespace = "http://ws.session/", className = "SendPO.SendPOResponse")
    @Action(input = "http://ws.session/SendPOHQ/sendPORequest", output = "http://ws.session/SendPOHQ/sendPOResponse")
    public List<ProductionOrder> sendPO(
        @WebParam(name = "factoryName", targetNamespace = "")
        String factoryName,
        @WebParam(name = "year", targetNamespace = "")
        String year,
        @WebParam(name = "month", targetNamespace = "")
        String month,
        @WebParam(name = "productName", targetNamespace = "")
        String productName);

}
