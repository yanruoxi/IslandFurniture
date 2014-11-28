/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package webservice;


import entity.Product;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import session.stateless.ProductSessionLocal;
import session.stateless.SystemUserSessionLocal;

/**
 * REST Web Service
 *
 * @author li
 */
@Path("generic")
public class GenericResource {

    @Context
    private UriInfo context;
    @EJB
    private ProductSessionLocal productSession;
    @EJB
    private SystemUserSessionLocal userSession;

    /**
     * Creates a new instance of GenericResource
     */
    public GenericResource() {
    }

    @GET
    // Path: http://localhost/<appln-folder-name>/register/doregister
    @Path("/getProduct")  
    // Produces JSON as response
    @Produces(MediaType.APPLICATION_JSON) 
    // Query parameters are parameters: http://localhost/<appln-folder-name>/register/doregister?name=pqrs&username=abc&password=xyz
    public List<Product> getProducts(){
        List<Product> products = productSession.getAllProduct();
        // get all products from db
        //product = new Product[dbRows.Length];
        // create product array
        return products;
    }
    @GET
    @Path("/getInventory")
    @Produces(MediaType.APPLICATION_JSON) 
    public List<Product> getInventory(){
        List<Product> products = productSession.getAllProduct();
        return products;
        // product id, quantity available
        // 2,0
        // get all product's inventory from db
        //product = new ProductInventory[dbRows.Length];
        // create product array
        // return list of product and inventory levels
    }
    
    //@GET
    //@Path("/checkCustomer")
    //@Produces(MediaType.APPLICATION_JSON) 
    //public Result checkCustomer(@QueryParam("name") String username, @QueryParam("password") String password) {
    //    SystemUser user = new SystemUser();
    //    String pass = user.hashPassword(password);
        
     //   Result result = userSession.checkValid(username,pass);
        
     //   return result;
    //}
}
