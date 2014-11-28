
package hqNonMember;

import java.math.BigDecimal;
import java.util.List;
import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.6-1b01 
 * Generated source version: 2.2
 * 
 */
@WebService(name = "HQTransactionOrderNonMember", targetNamespace = "http://ws.session/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface HQTransactionOrderNonMember {


    /**
     * 
     * @param date
     * @param totalAmount
     * @param transactionReference
     * @param furnitureNameList
     * @param amountList
     */
    @WebMethod
    @Oneway
    @RequestWrapper(localName = "createHQTransationOrderforPOS", targetNamespace = "http://ws.session/", className = "hqNonMember.CreateHQTransationOrderforPOS")
    @Action(input = "http://ws.session/HQTransactionOrderNonMember/createHQTransationOrderforPOS")
    public void createHQTransationOrderforPOS(
        @WebParam(name = "furnitureNameList", targetNamespace = "")
        List<String> furnitureNameList,
        @WebParam(name = "amountList", targetNamespace = "")
        List<Integer> amountList,
        @WebParam(name = "totalAmount", targetNamespace = "")
        BigDecimal totalAmount,
        @WebParam(name = "date", targetNamespace = "")
        String date,
        @WebParam(name = "transactionReference", targetNamespace = "")
        String transactionReference);

}
