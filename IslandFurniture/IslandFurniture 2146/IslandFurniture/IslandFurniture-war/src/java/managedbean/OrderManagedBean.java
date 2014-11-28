/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package managedbean;

import entity.AdHocOrder;
import entity.Furniture;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import session.stateless.adhocOrderSessionBeanLocal;

/**
 *
 * @author li
 */
@Named(value = "orderManagedBean")
@ViewScoped
public class OrderManagedBean implements Serializable {
    @EJB
    private adhocOrderSessionBeanLocal orderSessionLocal;
    @ManagedProperty(value = "#{loginManagedBean}")
    private LoginManagedBean loginManagedBean;
    private long id;
    private int amount;
    private String deadline;
    private String status;
    private String requestStore;
    private String plant;
    private Furniture furniture = new Furniture();
    private List<AdHocOrder> orderList = new ArrayList<>();
    /**
     * Creates a new instance of OrderManagedBean
     */
    
    public void updateOrderStatus(ActionEvent event)
    {
        id = (Long)event.getComponent().getAttributes().get("orderId");
    }
    
    public void setLoginManagedBean(LoginManagedBean loginManagedBean) {
        this.loginManagedBean = loginManagedBean;
    }
    
    public OrderManagedBean() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRequestStore() {
        return requestStore;
    }

    public void setRequestStore(String requestStore) {
        this.requestStore = requestStore;
    }

    public String getPlant() {
        return plant;
    }

    public void setPlant(String plant) {
        this.plant = plant;
    }

    public Furniture getFurniture() {
        return furniture;
    }

    public void setFurniture(Furniture furniture) {
        this.furniture = furniture;
    }

    public List<AdHocOrder> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<AdHocOrder> orderList) {
        this.orderList = orderList;
    }
    
    public void searchOrder() {
        ArrayList<AdHocOrder> order1 = orderSessionLocal.viewOrder(plant);
        if (order1.isEmpty()) {
            FacesMessage msg;
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "No orders for the plant or no this plant", "The order for the plant you are searching for does not exist");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {
            orderList.clear();
            orderList = order1;
        }

    }
    
    public void viewAllOrder() {
        ArrayList<AdHocOrder> order1 = orderSessionLocal.viewAllOrders();
        if (order1.isEmpty()) {
            FacesMessage msg;
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "No orders", "The order you are searching for does not exist");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {
            orderList.clear();
            orderList = order1;
        }
        
    }
    
     public void resetAccountSatus() {

        plant = orderSessionLocal.updataOrderStatus(id,status);
        FacesMessage msg;
        msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "You have successfully reset order status", "");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        orderList.clear();
        orderList = orderSessionLocal.viewOrder(plant);
    }
    
}
