/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean;

import entity.Customer;
import entity.Product;
import entity.SystemUser;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.PhaseEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import manager.CustomerEmailManager;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.UploadedFile;
import session.stateless.CustomerSession;
import session.stateless.CustomerSessionLocal;
import session.stateless.FeedbackSessionLocal;
import session.stateless.LogSessionLocal;
import session.stateless.SystemUserSessionLocal;
import session.stateless.ecommerceSessionBeanLocal;

/**
 *
 * @author wangyan
 */
@Named(value = "eCommerceManagedBean")
@ViewScoped
public class eCommerceManagedBean implements Serializable {

    @PostConstruct
    public void init() {
        userName2 = null;
    }

    @EJB
    LogSessionLocal logSessionLocal;
    @EJB
    ecommerceSessionBeanLocal ecommerceSB;
    @EJB
    CustomerSessionLocal customerSB;
    @EJB
    FeedbackSessionLocal feedbackSB;
    private String furniture;
    private List<Product> productList = new ArrayList<>();
    private List<Product> searchList = new ArrayList<>();
    private String country;
    private List<Product> sofaList = new ArrayList<>();
    private List<Product> armChairList = new ArrayList<>();
    private List<Product> TVList = new ArrayList<>();
    private List<Product> bathtubList = new ArrayList<>();
    private List<Product> showerList = new ArrayList<>();
    private List<Product> vanityList = new ArrayList<>();
    private List<Product> lightList = new ArrayList<>();
    private List<Product> frameList = new ArrayList<>();
    private List<Product> wardrobeList = new ArrayList<>();
    private List<Product> cabinetList = new ArrayList<>();
    private List<Product> worktopList = new ArrayList<>();
    private List<Product> tapSinkList = new ArrayList<>();
    private List<Product> livingRoomList = new ArrayList<>();
    private List<Product> bathRoomList = new ArrayList<>();
    private List<Product> bedRoomList = new ArrayList<>();
    private List<Product> kitchenList = new ArrayList<>();
    private List<Product> decorationList = new ArrayList<>();
    private List<Product> promotionList = new ArrayList<>();
    private List<Customer> customerList = new ArrayList<>();
    private Long id;// proudct id;
    private Product product;
    private List<Product> nextPageproductList = new ArrayList<>();
    private String customerId;
    private String destination = "/Users/user/Desktop/HeadQuarter/HeadQuarter/HeadQuarter-war/web/img/";
    // private String destination= "img/";
    private String userName2;

    private UploadedFile file;
    private String path;
    private String userName = "";
    private String password;
    private String password2;
    private String userEmail;
    private String userGender;
    private String userAddress;
    private String postalCode;
    private List<String> countries = new ArrayList<>();
    private boolean showCommandLink0;
    private boolean showCommandLink;
    private Collection<Product> myshoppingcartList = new ArrayList<>();
    private String feedbackName;
    private String feedbackEmail;
    private String feedbackComponent;
    private String phoneCountry;
    private String userPhone;
   private String newPassword;
   private String confirmNewPassword;

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmNewPassword() {
        return confirmNewPassword;
    }

    public void setConfirmNewPassword(String confirmNewPassword) {
        this.confirmNewPassword = confirmNewPassword;
    }
   
   
    public String getUserName2() {
        return userName2;
    }

    public void setUserName2(String userName2) {
        this.userName2 = userName2;
    }

    public String getPhoneCountry() {
        return phoneCountry;
    }

    public void setPhoneCountry(String phoneCountry) {
        this.phoneCountry = phoneCountry;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public boolean SPhoneLength() {
        if (phoneCountry.equals("+65")) {
            return true;
        } else {
            return false;
        }
    }
    public boolean ZPhoneLength1() {
       if(SPhoneLength()){
           return false;
       }else{
           return true;
       }
    }

   

    public void beforePhaseListener(PhaseEvent event) {
        String locale = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("selectedLocale");
        if (locale != null) {

            if (locale.equals("en_SG")) {
                FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale("en", "SG"));
            } else if (locale.equals("zh_CN")) {
                FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale("zh", "CN"));
            }
        } else {
            FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale("en", "SG"));
        }
    }

    public String getFeedbackName() {
        return feedbackName;
    }

    public void setFeedbackName(String feedbackName) {
        this.feedbackName = feedbackName;
    }

    public String getFeedbackEmail() {
        return feedbackEmail;
    }

    public void setFeedbackEmail(String feedbackEmail) {
        this.feedbackEmail = feedbackEmail;
    }

    public String getFeedbackComponent() {
        return feedbackComponent;
    }

    public void setFeedbackComponent(String feedbackComponent) {
        this.feedbackComponent = feedbackComponent;
    }

    public List<Customer> getCustomerList() {
        customerList = (List<Customer>) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("customerList");
        
        
     //   customerList.clear();
     //   Customer customer = customerSB.getCustomer(userName);
    //    customerList.add(customer);
        return customerList;
    }

    public void setCustomerList(List<Customer> customerList) {
        this.customerList = customerList;
    }

    public void handleFileUpload(FileUploadEvent event) {

        FacesMessage msg = new FacesMessage("Success! ", event.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        // Do what you want with the file        
        try {
            copyFile(event.getFile().getFileName(), event.getFile().getInputstream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void forgetPassword() throws IOException {
        Customer c = customerSB.getForgetEmail(userName2);

        if (c == null) {
            FacesMessage msg = new FacesMessage("Name not exist ", "");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {
            String custEmail = c.getCustomerName() + "<" + c.getEmail() + ">";
            Random random = new Random();
            Integer value = random.nextInt(50000000);
            String salt = value.toString();
            Integer value2 = random.nextInt(50000000);
            String custPassword = value2.toString();
            String custPasswordSalt = custPassword + salt;

            CustomerEmailManager custEmailManager = new CustomerEmailManager();
            custEmailManager.setToEmailAddress(custEmail);
            custEmailManager.emailCustAcct(c.getCustomerName(), c.getEmail(), custPasswordSalt);

            FacesMessage msg;
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "New Password is sent successfully ", "Please check your email for new password");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void redirectHome() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("home.xhtml");
    }

    public boolean isShowCommandLink0() {
        return showCommandLink0();
    }

    public void setShowCommandLink0(boolean showCommandLink0) {
        this.showCommandLink0 = showCommandLink0;
    }

    public boolean isShowCommandLink() {
        return showCommandLink();
    }

    public void setShowCommandLink(boolean showCommandLink) {
        this.showCommandLink = showCommandLink;
    }

    public boolean showCommandLink0() {

        //  userName=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userName");
        if (showCommandLink() == true) {
            return false;
        } else {
            return true;
        }
    }

    public boolean showCommandLink() {
        String userName1 = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userName");

        if (userName1 != null && !userName1.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public Collection<Product> getMyshoppingcartList() {
        String userName1 = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userName");
        Customer customer = customerSB.getCustomer(userName1);
        if (customer == null) {
            FacesMessage msg;
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Dear", "Login first/Register first");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            myshoppingcartList.clear();
            return myshoppingcartList;
        } else {
            myshoppingcartList.clear();
            myshoppingcartList = ecommerceSB.getShoppingCartList(customer);
            return myshoppingcartList;
        }
    }

    public void setMyshoppingcartList(Collection<Product> myshoppingcartList) {
        this.myshoppingcartList = myshoppingcartList;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public String getUserGender() {
        String gender = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userGender");
        return gender;
    }

    public void setUserGender(String userGender) {
        this.userGender = userGender;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserName() {
        String userName1 = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userName");

        return userName1;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public List<String> getCountries() {
        countries.clear();
        countries.add("Singapore");
        countries.add("China");
        return countries;
    }

    public void setCountries(List<String> countries) {
        this.countries = countries;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Product> getSearchList() {
        return searchList;
    }

    public void setSearchList(List<Product> searchList) {
        this.searchList = searchList;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public List<Product> getNextPageproductList() {
        return (List<Product>) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("nextPageproductList");
        // System.out.println(nextPageproductList.size());
        //   System.out.println(sofaList.size());
        //  return nextPageproductList;
    }

    public void setNextPageproductList(List<Product> nextPageproductList) {
        this.nextPageproductList = nextPageproductList;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Product> getWardrobeList() {
        wardrobeList.clear();
        wardrobeList = ecommerceSB.getAllWardrobe();
        return wardrobeList;
    }

    public void setWardrobeList(List<Product> wardrobeList) {
        this.wardrobeList = wardrobeList;
    }

    public List<Product> getLivingRoomList() {
        livingRoomList.clear();
        livingRoomList = ecommerceSB.getAllLivingRoom();
        return livingRoomList;
    }

    public void setLivingRoomList(List<Product> livingRoomList) {
        this.livingRoomList = livingRoomList;
    }

    public List<Product> getBathRoomList() {
        bathRoomList.clear();
        bathRoomList = ecommerceSB.getAllBathRoom();
        return bathRoomList;
    }

    public void setBathRoomList(List<Product> bathRoomList) {
        this.bathRoomList = bathRoomList;
    }

    public List<Product> getBedRoomList() {
        bedRoomList.clear();
        bedRoomList = ecommerceSB.getAllBedRoom();
        return bedRoomList;
    }

    public void setBedRoomList(List<Product> bedRoomList) {
        this.bedRoomList = bedRoomList;
    }

    public List<Product> getKitchenList() {
        kitchenList.clear();
        kitchenList = ecommerceSB.getAllKitchen();
        return kitchenList;
    }

    public void setKitchenList(List<Product> kitchenList) {
        this.kitchenList = kitchenList;
    }

    public List<Product> getDecorationList() {
        decorationList.clear();
        decorationList = ecommerceSB.getAllDecoration();
        return decorationList;
    }

    public void setDecorationList(List<Product> decorationList) {
        this.decorationList = decorationList;
    }

    public List<Product> getPromotionList() {
        promotionList.clear();
        promotionList = ecommerceSB.getAllPromotion();
        return promotionList;
    }

    public void setPromotionList(List<Product> promotionList) {
        this.promotionList = promotionList;
    }

    public List<Product> getCabinetList() {
        cabinetList.clear();
        cabinetList = ecommerceSB.getAllCabinet();
        return cabinetList;
    }

    public void setCabinetList(List<Product> cabinetList) {
        this.cabinetList = cabinetList;
    }

    public List<Product> getWorktopList() {
        worktopList.clear();
        worktopList = ecommerceSB.getAllWorktop();
        return worktopList;
    }

    public void setWorktopList(List<Product> worktopList) {
        this.worktopList = worktopList;
    }

    public List<Product> getTapSinkList() {
        tapSinkList.clear();
        tapSinkList = ecommerceSB.getAllTapSink();
        return tapSinkList;
    }

    public void setTapSinkList(List<Product> TapSinkList) {
        this.tapSinkList = TapSinkList;
    }

    public List<Product> getFrameList() {
        frameList.clear();
        frameList = ecommerceSB.getAllFrame();
        return frameList;
    }

    public void setFrameList(List<Product> frameList) {
        this.frameList = frameList;
    }

    public List<Product> getLightList() {
        lightList.clear();
        lightList = ecommerceSB.getAllLight();
        return lightList;
    }

    public void setLightList(List<Product> lightList) {
        this.lightList = lightList;
    }

    public List<Product> getVanityList() {
        vanityList.clear();
        vanityList = ecommerceSB.getAllVanity();
        return vanityList;
    }

    public void setVanityList(List<Product> vanityList) {
        this.vanityList = vanityList;
    }

    public List<Product> getTVList() {
        TVList.clear();
        TVList = ecommerceSB.getAllTV();
        return TVList;
    }

    public void setTVList(List<Product> TVList) {
        this.TVList = TVList;
    }

    public List<Product> getBathtubList() {
        bathtubList.clear();
        bathtubList = ecommerceSB.getAllBathtub();
        return bathtubList;
    }

    public List<Product> getShowerList() {
        showerList.clear();
        showerList = ecommerceSB.getAllShower();
        return showerList;
    }

    public void setShowerList(List<Product> showerList) {

        this.showerList = showerList;
    }

    public void setBathtubList(List<Product> bathtubList) {
        this.bathtubList = bathtubList;
    }

    public List<Product> getArmChairList() {
        armChairList.clear();
        armChairList = ecommerceSB.getAllArmChair();

        return armChairList;
    }

    public void setArmChairList(List<Product> armChairList) {
        this.armChairList = armChairList;
    }

    public List<Product> getSofaList() {
        sofaList.clear();
        sofaList = ecommerceSB.getAllSofa();
        return sofaList;
    }

    public void setSofaList(List<Product> sofaList) {
        this.sofaList = sofaList;
    }

    public String getFurniture() {
        return furniture;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<Product> getProductList() {
        productList.clear();
        productList = ecommerceSB.getAllProducts();

        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public void setFurniture(String furniture) {
        this.furniture = furniture;
    }

    public String navigate() {
        //change this method later
        return "e-product";

    }

    public void getAllProducts(ActionEvent event) {
        productList.clear();
        productList = ecommerceSB.getAllProducts();

    }

    public void onRowEditCustomer(RowEditEvent event) {

        Customer c = (Customer) event.getObject();
        customerSB.editCustomer(c);
        customerList.clear();

        userName = c.getCustomerName();
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("userName", userName);

        customerList.add(customerSB.getCustomer(c.getCustomerName()));

        FacesMessage msg = new FacesMessage("Edit successfully");
        FacesContext.getCurrentInstance().addMessage(null, msg);

        // FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowCancelCustomer(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", "");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowEditProduct(RowEditEvent event) {

        Customer c = (Customer) event.getObject();
        customerSB.editCustomer(c);
        customerList.clear();

        customerList.add(customerSB.getCustomer(c.getCustomerName()));

        FacesMessage msg = new FacesMessage("Edit successfully");
        FacesContext.getCurrentInstance().addMessage(null, msg);

        // FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowCancelProduct(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", ((Product) event.getObject()).getProductName());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void search() {
        System.out.println("search");
    }

    public void viewShoppingCart() {
        System.out.println("viewShoppingCart");
    }

    public void searchfunction(ActionEvent event) {
        //get all the related material which customer has inputed to website search icon
        searchList.clear();
        searchList = ecommerceSB.getSearchList(furniture);
        nextPageproductList.clear();
        nextPageproductList = searchList;
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("nextPageproductList", nextPageproductList);
        furniture = "";
    }

    public void nextpageSofa(ActionEvent event) {
        //   id = (Long)event.getComponent().getAttributes().get("Id");

        FacesContext context = FacesContext.getCurrentInstance();
        Product data = context.getApplication().evaluateExpressionGet(context, "#{sofa}", Product.class);
        id = data.getId();
        product = ecommerceSB.getProduct(id);
        nextPageproductList.clear();
        nextPageproductList.add(product);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("nextPageproductList", nextPageproductList);

    }

    public void nextpageLight(ActionEvent event) {
        //   id = (Long)event.getComponent().getAttributes().get("Id");

        FacesContext context = FacesContext.getCurrentInstance();
        Product data = context.getApplication().evaluateExpressionGet(context, "#{light}", Product.class);
        id = data.getId();
        product = ecommerceSB.getProduct(id);
        nextPageproductList.clear();
        nextPageproductList.add(product);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("nextPageproductList", nextPageproductList);

    }

    public void nextpageShower(ActionEvent event) {
        FacesContext context = FacesContext.getCurrentInstance();
        Product data = context.getApplication().evaluateExpressionGet(context, "#{shower}", Product.class);
        id = data.getId();
        product = ecommerceSB.getProduct(id);
        nextPageproductList.clear();
        nextPageproductList.add(product);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("nextPageproductList", nextPageproductList);
    }

    public void nextpageFrame(ActionEvent event) {
        FacesContext context = FacesContext.getCurrentInstance();
        Product data = context.getApplication().evaluateExpressionGet(context, "#{frame}", Product.class);
        id = data.getId();
        product = ecommerceSB.getProduct(id);
        nextPageproductList.clear();
        nextPageproductList.add(product);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("nextPageproductList", nextPageproductList);
    }

    public void nextpageWardrobe(ActionEvent event) {
        FacesContext context = FacesContext.getCurrentInstance();
        Product data = context.getApplication().evaluateExpressionGet(context, "#{wardrobe}", Product.class);
        id = data.getId();
        product = ecommerceSB.getProduct(id);
        nextPageproductList.clear();
        nextPageproductList.add(product);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("nextPageproductList", nextPageproductList);
    }

    public void nextpageCabinet(ActionEvent event) {
        FacesContext context = FacesContext.getCurrentInstance();
        Product data = context.getApplication().evaluateExpressionGet(context, "#{cabinet}", Product.class);
        id = data.getId();
        product = ecommerceSB.getProduct(id);
        nextPageproductList.clear();
        nextPageproductList.add(product);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("nextPageproductList", nextPageproductList);
    }

    public void nextpageWorktop(ActionEvent event) {
        FacesContext context = FacesContext.getCurrentInstance();
        Product data = context.getApplication().evaluateExpressionGet(context, "#{worktop}", Product.class);
        id = data.getId();
        product = ecommerceSB.getProduct(id);
        nextPageproductList.clear();
        nextPageproductList.add(product);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("nextPageproductList", nextPageproductList);
    }

    public void nextpageTapSink(ActionEvent event) {
        FacesContext context = FacesContext.getCurrentInstance();
        Product data = context.getApplication().evaluateExpressionGet(context, "#{tapsink}", Product.class);
        id = data.getId();
        product = ecommerceSB.getProduct(id);
        nextPageproductList.clear();
        nextPageproductList.add(product);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("nextPageproductList", nextPageproductList);
    }

    public void nextpageLivingRoom(ActionEvent event) {
        FacesContext context = FacesContext.getCurrentInstance();
        Product data = context.getApplication().evaluateExpressionGet(context, "#{livingroom}", Product.class);
        id = data.getId();
        product = ecommerceSB.getProduct(id);
        nextPageproductList.clear();
        nextPageproductList.add(product);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("nextPageproductList", nextPageproductList);
    }

    public void nextpageBedRoom(ActionEvent event) {
        FacesContext context = FacesContext.getCurrentInstance();
        Product data = context.getApplication().evaluateExpressionGet(context, "#{bedroom}", Product.class);
        id = data.getId();
        product = ecommerceSB.getProduct(id);
        nextPageproductList.clear();
        nextPageproductList.add(product);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("nextPageproductList", nextPageproductList);
    }

    public void nextpageBathtub(ActionEvent event) {
        FacesContext context = FacesContext.getCurrentInstance();
        Product data = context.getApplication().evaluateExpressionGet(context, "#{bathtub}", Product.class);
        id = data.getId();
        product = ecommerceSB.getProduct(id);
        nextPageproductList.clear();
        nextPageproductList.add(product);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("nextPageproductList", nextPageproductList);
    }

    public void nextpageBathRoom(ActionEvent event) {
        FacesContext context = FacesContext.getCurrentInstance();
        Product data = context.getApplication().evaluateExpressionGet(context, "#{bathroom}", Product.class);
        id = data.getId();
        product = ecommerceSB.getProduct(id);
        nextPageproductList.clear();
        nextPageproductList.add(product);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("nextPageproductList", nextPageproductList);
    }

    public void nextpageKitchen(ActionEvent event) {
        FacesContext context = FacesContext.getCurrentInstance();
        Product data = context.getApplication().evaluateExpressionGet(context, "#{kitchen}", Product.class);
        id = data.getId();
        product = ecommerceSB.getProduct(id);
        nextPageproductList.clear();
        nextPageproductList.add(product);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("nextPageproductList", nextPageproductList);
    }

    public void nextpageDecoration(ActionEvent event) {
        FacesContext context = FacesContext.getCurrentInstance();
        Product data = context.getApplication().evaluateExpressionGet(context, "#{decoration}", Product.class);
        id = data.getId();
        product = ecommerceSB.getProduct(id);
        nextPageproductList.clear();
        nextPageproductList.add(product);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("nextPageproductList", nextPageproductList);
    }

    public void nextpagePromotion(ActionEvent event) {
        FacesContext context = FacesContext.getCurrentInstance();
        Product data = context.getApplication().evaluateExpressionGet(context, "#{promotion}", Product.class);
        id = data.getId();
        product = ecommerceSB.getProduct(id);
        nextPageproductList.clear();
        nextPageproductList.add(product);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("nextPageproductList", nextPageproductList);
    }

    public void nextpageArmchair(ActionEvent event) {
        //   id = (Long)event.getComponent().getAttributes().get("Id");

        FacesContext context = FacesContext.getCurrentInstance();
        Product data = context.getApplication().evaluateExpressionGet(context, "#{armchair}", Product.class);
        id = data.getId();
        product = ecommerceSB.getProduct(id);
        nextPageproductList.clear();
        nextPageproductList.add(product);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("nextPageproductList", nextPageproductList);

    }

    public void nextpageTV(ActionEvent event) {
        //   id = (Long)event.getComponent().getAttributes().get("Id");

        FacesContext context = FacesContext.getCurrentInstance();
        Product data = context.getApplication().evaluateExpressionGet(context, "#{TV}", Product.class);
        id = data.getId();
        product = ecommerceSB.getProduct(id);
        nextPageproductList.clear();
        nextPageproductList.add(product);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("nextPageproductList", nextPageproductList);

    }

    public void nextpageVanity(ActionEvent event) {
        FacesContext context = FacesContext.getCurrentInstance();
        Product data = context.getApplication().evaluateExpressionGet(context, "#{vanity}", Product.class);
        id = data.getId();
        product = ecommerceSB.getProduct(id);
        nextPageproductList.clear();
        nextPageproductList.add(product);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("nextPageproductList", nextPageproductList);
    }

    public String nextpage1() {

        return "e-product";
    }

    public void addToShoppingCart(ActionEvent event) {
        String userName1 = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userName");
        Customer result = customerSB.getCustomer(userName1);
        if (result == null) {
            FacesMessage msg;
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid", "Login first/Register first");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {

            id = (Long) event.getComponent().getAttributes().get("productId");
            product = ecommerceSB.getProduct(id);
            ecommerceSB.addToShoppingCart(product, result);

            // FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, statusMessage, ""));
        }
    }

    public String shoppingfirst() {
        String userName1 = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userName");
        if (customerSB.getCustomer(userName1) == null) {
            return "customerRegister";//register page

        } else {
            return "my-shoppingcart";
        }
    }

    public void upload(FileUploadEvent event) {
        path = event.getFile().getFileName();
        FacesMessage msg = new FacesMessage("Success! ", event.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        // Do what you want with the file        
        try {
            copyFile(event.getFile().getFileName(), event.getFile().getInputstream());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void copyFile(String fileName, InputStream in) {
        try {
            System.err.println("copy file to " + destination + fileName);
            // write the inputStream to a FileOutputStream
            OutputStream out = new FileOutputStream(new File(destination + fileName));

            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = in.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }

            in.close();
            out.flush();
            out.close();

            System.out.println("New file created!");
        } catch (IOException e) {
            System.out.println(e.getMessage() + "t");
        }
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public void doLogin(ActionEvent event) throws IOException {

        Customer result = customerSB.getCustomer(userName);
        if (result == null) {
            userName = "";
            password = "";
            userEmail = "";
            userAddress = "";
            postalCode = "";
            country = "";
            userGender = "";
            feedbackName = "";
            feedbackComponent = "";
            feedbackEmail = "";
            FacesMessage msg;
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid", "Wrong user name or password.");
            FacesContext.getCurrentInstance().addMessage(null, msg);

        } else {
            if (comparePassword()) {

                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("userName", userName);
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("userGender", result.getGender());
                //     String useName=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userName");
//                ((HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true)).invalidate();

                System.out.println("success login");

                System.out.println(result.getCustomerName());

                FacesContext.getCurrentInstance().getExternalContext().redirect("home.xhtml");

            } else {
                userName = "";
                password = "";
                userEmail = "";
                userAddress = "";
                postalCode = "";
                country = "";
                userGender = "";
                feedbackName = "";
                feedbackComponent = "";
                feedbackEmail = "";
                FacesMessage msg;
                msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid User Name or Password", "Wrong user name or password.");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }

        }

    }

    public boolean comparePassword() {
        boolean check = false;
        Customer systemUser = customerSB.getCustomer(userName);

        char[] databasePassword = systemUser.getPassword().toCharArray();
        char[] enteredPassword = null;
        try {

            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] tmp = password.getBytes();

            md5.update(tmp);
            enteredPassword = systemUser.byteArrToString(md5.digest()).toCharArray();
            int correctCount = 0;
            if (databasePassword.length != enteredPassword.length) {
                return check;
            }
            for (int i = 0; i < databasePassword.length; i++) {
                if (databasePassword[i] == enteredPassword[i]) {
                    correctCount++;
                }
            }
            if (databasePassword.length == correctCount) {
                check = true;
                return check;
            } else {
                return check;
            }
        } catch (NoSuchAlgorithmException ex) {
            System.out.println("cannot check password ");
        }

        return check;

    }

    public boolean userPhoneExist() {

        return customerSB.getPhoneNumber(phoneCountry + userPhone);

    }

    public void doRegister(ActionEvent event) throws IOException {

        try {
            Long phoneNumber = Long.valueOf(userPhone);

            System.out.println("Text userName:" + userName);
            System.out.println("Text password:" + password);
            if (!password.equals(password2) ) {
                userName = "";
                password = "";
                userEmail = "";
                userAddress = "";
                postalCode = "";
                country = "";
                userGender = "";
                feedbackName = "";
                feedbackComponent = "";
                feedbackEmail = "";
                FacesMessage msg;
                msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Passwords are not the same ", "");
                FacesContext.getCurrentInstance().addMessage(null, msg);

            }else if(userPhoneExist()){
                userName = "";
                password = "";
                userEmail = "";
                userAddress = "";
                postalCode = "";
                country = "";
                userGender = "";
                feedbackName = "";
                feedbackComponent = "";
                feedbackEmail = "";
                FacesMessage msg;
                msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Phone has been registered already ", "");
                FacesContext.getCurrentInstance().addMessage(null, msg);

            }
            else {

                Customer result = customerSB.getCustomer(userName);
                //new for check email
                Customer result2 = customerSB.getCustomerByEmail(userEmail);
                // System.out.println(result);
                if (result != null) {
                    userName = "";
                    password = "";
                    userEmail = "";
                    userAddress = "";
                    postalCode = "";
                    country = "";
                    userGender = "";
                    feedbackName = "";
                    feedbackComponent = "";
                    feedbackEmail = "";
                    FacesMessage msg;
                    msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "UserName has been used,please change to another valid username ", "");
                    FacesContext.getCurrentInstance().addMessage(null, msg);

                } //new for check email
                else if (result2 != null) {
                    userName = "";
                    password = "";
                    userEmail = "";
                    userAddress = "";
                    postalCode = "";
                    country = "";
                    userGender = "";
                    FacesMessage msg;
                    msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "This email address has been registered,please change to another valid email address ", "");
                    FacesContext.getCurrentInstance().addMessage(null, msg);
                } else {
                //    Calendar cal = Calendar.getInstance();
                    //   logSessionLocal.createLog(loginManagedBean.getCurrentUser().getUserName(), "Create new account", cal);
                    System.out.println("userName:" + userName);
                    customerSB.registerCustomer(userGender, userName, password, userEmail, userAddress, postalCode, country,phoneCountry+userPhone);
                    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("userName", userName);
                    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("userGender", userGender);
                    FacesMessage msg;
                    msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Account Successfully Created", "");
                    FacesContext.getCurrentInstance().addMessage(null, msg);
                    FacesContext.getCurrentInstance().getExternalContext().redirect("home.xhtml");
                }

            }
        } catch (NumberFormatException e) {
            String statusMessage = "invalid phone format";

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "PLEASE ENTER AN valid for phone: " + statusMessage, ""));

        }
    }

    public void customerLogout(ActionEvent event) {
        ((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true)).invalidate();

        userName = "";
        password = "";
        userEmail = "";
        userAddress = "";
        postalCode = "";
        country = "";
        userGender = "";
        feedbackName = "";
        feedbackComponent = "";
        feedbackEmail = "";
    }

    public void feedbackSubmission(ActionEvent event) {
        if (feedbackName == null || feedbackName.isEmpty()) {
            feedbackName = "Anonym";

        }
        if (feedbackEmail == null || feedbackEmail.isEmpty()) {
            feedbackEmail = "NAN";

        }
        if (feedbackComponent == null || feedbackComponent.isEmpty()) {
            feedbackComponent = "NAN";
        }
        feedbackSB.addFeedback(feedbackName, feedbackEmail, feedbackComponent);
        FacesMessage msg;
        msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Thanks for your feedback submission", "");
        FacesContext.getCurrentInstance().addMessage(null, msg);

    }

    public void viewCustomerList(ActionEvent event) {
        userName = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userName");
        customerList.clear();

        customerList.add(customerSB.getCustomer(userName));
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("customerList", customerList);
    }
    
    public void resetPassword(ActionEvent event) {
         
        if (comparePassword2()) {
            if (!newPassword.equals(confirmNewPassword)) {
                FacesMessage msg;
                msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Password entered not the same", "Password entered not the same");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } else {

                Calendar cal = Calendar.getInstance();
                logSessionLocal.createLog(userName, "Reset password", cal);

                customerSB.resetPassword(userName, newPassword);
                FacesMessage msg;
                msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Seccessfully Reset Password", "You have successfully reset your password");
                FacesContext.getCurrentInstance().addMessage(null, msg);

            }

        } else {

            FacesMessage msg;
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Old Password is Wrong", "Wrong Old Password");
            FacesContext.getCurrentInstance().addMessage(null, msg);

        }

    }
    
    public boolean comparePassword2() {
        boolean check = false;
       userName = (String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userName");
      Customer c = customerSB.getCustomer(userName);
       
       
        char[] databasePassword = c.getPassword().toCharArray();
        char[] enteredPassword = null;
        try {

            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] tmp = password.getBytes();
 
            md5.update(tmp);
            enteredPassword = c.byteArrToString(md5.digest()).toCharArray();
            int correctCount = 0;
            if (databasePassword.length != enteredPassword.length) {
                return check;
            }
            for (int i = 0; i < databasePassword.length; i++) {
                if (databasePassword[i] == enteredPassword[i]) {
                    correctCount++;
                }
            }
            if (databasePassword.length == correctCount) {
                check = true;
                return check;
            } else {
                return check;
            }
        } catch (NoSuchAlgorithmException ex) {
            System.out.println("cannot check password ");
        }

        return check;

    }
    
    
}
