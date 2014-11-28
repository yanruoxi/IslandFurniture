package managedbean;

import java.io.IOException;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.inject.Named;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Named(value = "homeManagedBean")
@RequestScoped
public class HomeManagedBean {
    
    private FacesContext ctx = FacesContext.getCurrentInstance();

    public HomeManagedBean() {
       
    }
    
   /*@PostConstruct
    public void init()
    {
        String country = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("country").toString();
        
        if(country != null)
        {
            if(country.equals("en_SG"))
            {
                ctx.getViewRoot().setLocale(new Locale("en", "SG"));
            }
            else if(country.equals("zh_CN"))
            {
                ctx.getViewRoot().setLocale(new Locale("zh", "CN"));
            }
        }
    }*/
    public void beforePhaseListener(PhaseEvent event) throws IOException {
        if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("selectedLocale") == null) {
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            Cookie cookie = null;

            Cookie[] userCookies = request.getCookies();
            if (userCookies != null && userCookies.length > 0) {
                for (int i = 0; i < userCookies.length; i++) {
                    if (userCookies[i].getName().equals("selectedLocale")) {
                        cookie = userCookies[i];
                        break;
                    }
                }
            }

            if (cookie != null && cookie.getValue() != null) {
                if(cookie.getValue().equals("en_SG"))
                {
                    ctx.getViewRoot().setLocale(new Locale("en", "SG"));
                }
                else if(cookie.getValue().equals("zh_CN"))
                {
                    ctx.getViewRoot().setLocale(new Locale("zh", "CN"));
                }
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("selectedLocale", null);
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("selectedLocale", cookie.getValue());
            } else {
                FacesContext.getCurrentInstance().getExternalContext().redirect("welcome.xhtml?faces-redirect=true");
            }
        }
    }
}
