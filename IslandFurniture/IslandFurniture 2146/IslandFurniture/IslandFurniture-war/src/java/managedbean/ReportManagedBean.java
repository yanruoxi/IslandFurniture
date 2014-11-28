/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean;

import entity.PurchaseOrder;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.HashMap;
import javax.annotation.Resource;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

/**
 *
 * @author ZiGui
 */
@Named(value = "reportManagedBean")
@SessionScoped
public class ReportManagedBean implements Serializable {

    @Resource(name = "islandFurnitureDataSource")
    private DataSource islandFurnitureDataSource;
    
    private PurchaseOrder selectedPurchaseOrder;

    public PurchaseOrder getSelectedPurchaseOrder() {
        return selectedPurchaseOrder;
    }

    public void setSelectedPurchaseOrder(PurchaseOrder selectedPurchaseOrder) {
        this.selectedPurchaseOrder = selectedPurchaseOrder;
    }
    

    // Export Inventory Report for Parts
    public void partsPDF(ActionEvent event) {
        JasperPrint jasperPrint;

        try {
            jasperPrint = JasperFillManager.fillReport("C:\\Users\\ZiGui\\Desktop\\IslandFurniture\\IslandFurniture-war\\web\\jasperReports\\InventoryPartReport.jasper", new HashMap(), islandFurnitureDataSource.getConnection());
            HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            httpServletResponse.addHeader("Content-disposition", " attachment; filename=InventoryPartReport.pdf");
            ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
            FacesContext.getCurrentInstance().responseComplete();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

// Export Inventory Report for Furniture
    public void furniturePDF(ActionEvent event) {
        JasperPrint jasperPrint;

        try {
            jasperPrint = JasperFillManager.fillReport("C:\\Users\\ZiGui\\Desktop\\IslandFurniture\\IslandFurniture-war\\web\\jasperReports\\InventoryFurnitureReport.jasper", new HashMap(), islandFurnitureDataSource.getConnection());
            HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            httpServletResponse.addHeader("Content-disposition", " attachment; filename=InventoryFurnitureReport.pdf");
            ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
            FacesContext.getCurrentInstance().responseComplete();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    // Export Inventory Report for Retail Products
    public void retailProductsPDF(ActionEvent event) throws JRException, IOException, SQLException {
    }

    // Export Shipping Order Invoice
    public void soPDF(ActionEvent event) throws JRException, IOException, SQLException {
    }

    // Export Purchase Order Invoice
    public void spoPDF(ActionEvent event) {
        JasperPrint jasperPrint;
        
        try {
            jasperPrint = JasperFillManager.fillReport("C:\\Users\\ZiGui\\Desktop\\IslandFurniture\\IslandFurniture-war\\web\\jasperReports\\SupplierPurchaseOrder.jasper", new HashMap(), islandFurnitureDataSource.getConnection());
            HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            httpServletResponse.addHeader("Content-disposition", " attachment; filename=SupplierPurchaseOrder.pdf");
            ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
            FacesContext.getCurrentInstance().responseComplete();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
