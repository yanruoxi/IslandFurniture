
package Login;

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
 * JAX-WS RI 2.2.6-1b01 
 * Generated source version: 2.2
 * 
 */
@WebService(name = "LoginPOS", targetNamespace = "http://service.Web/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface LoginPOS {


    /**
     * 
     * @param password
     * @param userName
     * @return
     *     returns boolean
     */
    @WebMethod(operationName = "LoginForPOS")
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "LoginForPOS", targetNamespace = "http://service.Web/", className = "Login.LoginForPOS")
    @ResponseWrapper(localName = "LoginForPOSResponse", targetNamespace = "http://service.Web/", className = "Login.LoginForPOSResponse")
    @Action(input = "http://service.Web/LoginPOS/LoginForPOSRequest", output = "http://service.Web/LoginPOS/LoginForPOSResponse")
    public boolean loginForPOS(
        @WebParam(name = "userName", targetNamespace = "")
        String userName,
        @WebParam(name = "password", targetNamespace = "")
        String password);

    /**
     * 
     * @param userName
     * @return
     *     returns Login.SystemUser
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getSystemUser", targetNamespace = "http://service.Web/", className = "Login.GetSystemUser")
    @ResponseWrapper(localName = "getSystemUserResponse", targetNamespace = "http://service.Web/", className = "Login.GetSystemUserResponse")
    @Action(input = "http://service.Web/LoginPOS/getSystemUserRequest", output = "http://service.Web/LoginPOS/getSystemUserResponse")
    public SystemUser getSystemUser(
        @WebParam(name = "userName", targetNamespace = "")
        String userName);

}