/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeanKMS;

import entity.kms.Dish;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.inject.Named;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import session.stateless.kms.DishSessionBeanLocal;

/**
 *
 * @author ZiGui
 */
@Named(value = "imageViewManagedBean")
@ManagedBean
public class ImageViewManagedBean {

    @EJB
    private DishSessionBeanLocal dishSessionBean;

    private byte[] image;
    private Dish dishToEdit;

    private StreamedContent graphicText;
    FacesContext con = FacesContext.getCurrentInstance();

    @PostConstruct
    public void init() {
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> paramMap = context.getExternalContext().getRequestParameterMap();
        if (paramMap.containsKey("dish")) {
            String dishIdString = paramMap.get("dish");
            dishToEdit = dishSessionBean.getDishWithId(Long.parseLong(dishIdString));
        }
    }

    public StreamedContent getImage() throws IOException {
        if (con.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            // So, we're rendering the HTML. Return a stub StreamedContent so that it will generate right URL.
            return new DefaultStreamedContent();
        } else {
            return new DefaultStreamedContent(new ByteArrayInputStream(dishToEdit.getImageData()));
        }
    }
}
