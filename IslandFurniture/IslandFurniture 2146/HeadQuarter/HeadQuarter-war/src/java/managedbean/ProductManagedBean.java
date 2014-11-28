/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean;

import entity.Product;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import session.stateless.ProductSessionLocal;
import entity.Product;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author WangYan
 */
@Named(value = "productManagedBean")
@ViewScoped
public class ProductManagedBean implements Serializable {

    @EJB
    private ProductSessionLocal productSessionLocal;

    private String productName;
    private String type;
    private List<Product> productList = new ArrayList<>();
    private String activeProductName;
    private String category;
    private List<String> categoryList = new ArrayList<>();
    private String price;
    private UploadedFile file;
    private String path="";
    private String productDescription;
    private String color;
    private String height;
    private String width;
    private String length;
    private List<String> typeList =new ArrayList<>();
    private final String destination = "/img/";
    private String instructions;
    // private String destination= "img/";
    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public List<String> getTypeList() {
        typeList.clear();
        typeList.add("Furniture");
        typeList.add("Retail Goods");
        return typeList;
    }

    public void setTypeList(List<String> typeList) {
        this.typeList = typeList;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }
    
    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
    

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public List<String> getCategoryList() {
        categoryList.clear();
        categoryList.add("SOFA");
        categoryList.add("ARMCHAIR");
        categoryList.add("TV");
        categoryList.add("MEDIA");
        categoryList.add("BEDROOM LIGHTING");
        categoryList.add("BATHTUB");
        
        categoryList.add("SHOWER");
        categoryList.add("VANITY");
        categoryList.add("TAP");
        categoryList.add("SINK");
        categoryList.add("WORKTOP");
        categoryList.add("KITCHEN CABINET");
        categoryList.add("BED FRAME");
        categoryList.add("WARDROBE");
        categoryList.add("DECORATION");
        return categoryList;
    }

    public void setCategoryList(List<String> categoryList) {

        this.categoryList = categoryList;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public String getActiveProductName() {
        return activeProductName;
    }

    public void setActiveProductName(String activeProductName) {
        this.activeProductName = activeProductName;
    }

    public void createProduct(ActionEvent event) {
       if(path.isEmpty()||path.length()==0){
          FacesMessage msg;
                msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Product Cannot be Created", "You have to  upload a picture first!!");
                FacesContext.getCurrentInstance().addMessage(null, msg);       
       }else{
        Product product = productSessionLocal.getProduct1(productName, category);

        if (product != null) {
            FacesMessage msg;
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Product Already Exist", "The product you are trying to create is already registered");
            FacesContext.getCurrentInstance().addMessage(null, msg);

        } else {
            try {
                BigDecimal pri = BigDecimal.valueOf(Long.valueOf(price));
                productSessionLocal.createProduct(productName, type, category, pri,Double.valueOf(height),Double.valueOf(width),Double.valueOf(length),color,productDescription,instructions,path);
                path="";
                FacesMessage msg;
                msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Product Successfully Created", "You have successfully added a product");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } catch (NumberFormatException ex) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "FAILED FORMAT", "Invalid PRICE format. FLOAT expected."));

            }

        }
    }
    }

    public void searchProduct() {

        Product result = productSessionLocal.getProduct(productName);

        if (result == null) {
            FacesMessage msg;
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "No such product", "The product you are searching for does not exist");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            getProductList().clear();

        } else {
            productList.clear();
            productList.add(result);
        }
    }

    public void viewAllProduct() {
        productList.clear();
        productList = productSessionLocal.getAllProduct();
    }

    public void deleteProcut(String productName) {

        productSessionLocal.deleteProduct(productName);
        productList.clear();

        FacesMessage msg;
        msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Product Deleted Successfully", "");
        FacesContext.getCurrentInstance().addMessage(null, msg);

    }

    public void resetProductName() {
        if (productSessionLocal.getProduct(productName) != null) {
            FacesMessage msg;
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Cannot have two product with the same name", "");
            FacesContext.getCurrentInstance().addMessage(null, msg);

        } else {
            productSessionLocal.resetProductName(activeProductName, productName);
            FacesMessage msg;
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "You have successfully reset product name", "");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            productList.clear();
            productList = productSessionLocal.getAllProduct();
        }

    }
    
    public void upload(FileUploadEvent event) {
        System.out.println(event.getSource());
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

    /**
     * @return the productSessionLocal
     */
    public ProductSessionLocal getProductSessionLocal() {
        return productSessionLocal;
    }

    /**
     * @param productSessionLocal the productSessionLocal to set
     */
    public void setProductSessionLocal(ProductSessionLocal productSessionLocal) {
        this.productSessionLocal = productSessionLocal;
    }

    /**
     * @return the instructions
     */
    public String getInstructions() {
        return instructions;
    }

    /**
     * @param instructions the instructions to set
     */
    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }
}
