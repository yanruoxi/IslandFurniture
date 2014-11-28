/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package managedBeanKMS;

import java.io.Serializable;
import java.util.HashMap;
import javax.annotation.Resource;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

/**
 *
 * @author ZiGui
 */
@Named(value = "kitchenReportManagedBean")
@SessionScoped
public class KitchenReportManagedBean  implements Serializable {

    @Resource(name = "islandFurnitureDataSource")
    private DataSource islandFurnitureDataSource;
    
    // Export Inventory Report for Kitchen 
    public void kmsInventory(ActionEvent event) {
        JasperPrint jasperPrint;

        try {
            jasperPrint = JasperFillManager.fillReport("C:\\Users\\ZiGui\\Desktop\\IslandFurniture\\IslandFurniture-war\\web\\jasperReports\\kmsInventoryReport.jasper", new HashMap(), islandFurnitureDataSource.getConnection());
            HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            httpServletResponse.addHeader("Content-disposition", " attachment; filename=kmsInventory.pdf");
            ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
            FacesContext.getCurrentInstance().responseComplete();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
