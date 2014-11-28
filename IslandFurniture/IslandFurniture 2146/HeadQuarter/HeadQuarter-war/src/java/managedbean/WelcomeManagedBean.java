package managedbean;

import java.io.IOException;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Named(value = "welcomeManagedBean")
@RequestScoped
public class WelcomeManagedBean {

    private FacesContext ctx = FacesContext.getCurrentInstance();
    private String selectedLocale;

    public WelcomeManagedBean() {
        ctx.getViewRoot().setLocale(new Locale("en", "SG"));
    }

    public void selectedLocaleValueChangeListener() throws IOException {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        Cookie cookie = new Cookie("selectedLocale", selectedLocale);
        cookie.setPath(request.getContextPath());
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addCookie(cookie);
        
        if (selectedLocale.equals("en_SG")) {
            ctx.getViewRoot().setLocale(new Locale("en", "SG"));
        } else if (selectedLocale.equals("zh_CN")) {
            ctx.getViewRoot().setLocale(new Locale("zh", "CN"));
        }
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("selectedLocale", null);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("selectedLocal", selectedLocale);
        FacesContext.getCurrentInstance().getExternalContext().redirect("home.xhtml?faces-redirect=true");     
    }

    public String getLocalizedCurrencyFormat() {
        return NumberFormat.getCurrencyInstance(ctx.getViewRoot().getLocale()).format(1000);
    }

    public String getLocalizedDateFormat() {
        return DateFormat.getDateInstance(DateFormat.LONG, ctx.getViewRoot().getLocale()).format(new Date());
    }

    public String getSelectedLocale() {
        return selectedLocale;
    }

    public void setSelectedLocale(String selectedLocale) {
        this.selectedLocale = selectedLocale;
    }
}
