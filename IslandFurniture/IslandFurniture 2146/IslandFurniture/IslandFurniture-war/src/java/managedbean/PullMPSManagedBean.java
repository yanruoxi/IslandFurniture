/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean;

import SendPO.ProductionOrder;
import SendPO.SendPOHQ_Service;

import entity.Furniture;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.xml.ws.WebServiceRef;
import session.stateless.FurnitureSessionLocal;
import session.stateless.pullMPSSessionLocal;


/**
 *
 * @author Ruoxi
 */
@Named(value = "pullMPSManagedBean")
@SessionScoped
public class PullMPSManagedBean implements Serializable {
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/172.23.73.240_8080/SendPOHQ/SendPOHQ.wsdl")
    private SendPOHQ_Service service_1;
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/172.28.179.97_8080/SendPOHQ/SendPOHQ.wsdl")
    private SendPOHQ_Service service;
 


    @EJB
    private FurnitureSessionLocal furnitureSession;
    @EJB
    private pullMPSSessionLocal MPSSession;

    @Inject
    private WelcomeMessage welcomeMessage;

    @PostConstruct
    public void init() {
        furnitureList = furnitureSession.getAllFurniture();

    }

    private List<Furniture> furnitureList;
    private Furniture selectedFurniture;
    private String year;
    private String month;
    private List<ProductionOrder> poList;

    public void pullMPSHQ() {

       String companyName = welcomeMessage.getCompany().getCompanyName();

        poList = sendPO(companyName, year, month, selectedFurniture.getFurnitureName());
        if (poList.isEmpty()) {
            FacesMessage msg;
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No Production Order ");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {
            for (ProductionOrder pos : poList) {
                String date = month + "-" + year;
                Integer amount = pos.getQuantity();

                if (MPSSession.existMPS(date, selectedFurniture.getFurnitureName())) {

                    MPSSession.creatMPSHQ(date, amount, selectedFurniture.getFurnitureName());
                    FacesMessage msg;
                    msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Successfully pulled from HQ");
                    FacesContext.getCurrentInstance().addMessage(null, msg);

                } else {
                    FacesMessage msg;
                    msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Already Pulled PO");
                    FacesContext.getCurrentInstance().addMessage(null, msg);
                }

            }

        }

    }

    /**
     * Creates a new instance of PullMPSManagedBean
     */
    public PullMPSManagedBean() {
    }

    /**
     * @return the furnitureList
     */
    public List<Furniture> getFurnitureList() {
        return furnitureList;
    }

    /**
     * @param furnitureList the furnitureList to set
     */
    public void setFurnitureList(List<Furniture> furnitureList) {
        this.furnitureList = furnitureList;
    }

    /**
     * @return the selectedFurniture
     */
    public Furniture getSelectedFurniture() {
        return selectedFurniture;
    }

    /**
     * @param selectedFurniture the selectedFurniture to set
     */
    public void setSelectedFurniture(Furniture selectedFurniture) {
        this.selectedFurniture = selectedFurniture;
    }

    /**
     * @return the year
     */
    public String getYear() {
        return year;
    }

    /**
     * @param year the year to set
     */
    public void setYear(String year) {
        this.year = year;
    }

    /**
     * @return the month
     */
    public String getMonth() {
        return month;
    }

    /**
     * @param month the month to set
     */
    public void setMonth(String month) {
        this.month = month;
    }

    /**
     * @param welcomeMessage the welcomeMessage to set
     */
    public void setWelcomeMessage(WelcomeMessage welcomeMessage) {
        this.welcomeMessage = welcomeMessage;
    }

    private java.util.List<SendPO.ProductionOrder> sendPO(java.lang.String factoryName, java.lang.String year, java.lang.String month, java.lang.String productName) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        SendPO.SendPOHQ port = service_1.getSendPOHQPort();
        return port.sendPO(factoryName, year, month, productName);
    }

   
   


}
