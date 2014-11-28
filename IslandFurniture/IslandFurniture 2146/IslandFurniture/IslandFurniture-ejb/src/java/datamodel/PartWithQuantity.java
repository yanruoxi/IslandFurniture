package datamodel;

import entity.Part;
import java.io.Serializable;



public class PartWithQuantity implements Serializable 
{
    private Part part;
    private Integer quantity;
    
    // Added
    private String replenishmentType;

    public PartWithQuantity() {
    }

    
    
    public PartWithQuantity(Part part, Integer quantity, String replenishmentType) {
        this.part = part;
        this.quantity = quantity;
        this.replenishmentType = replenishmentType;
    }

    
    
    public Part getPart() {
        return part;
    }

    public void setPart(Part part) {
        this.part = part;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getReplenishmentType() {
        return replenishmentType;
    }

    public void setReplenishmentType(String replenishmentType) {
        this.replenishmentType = replenishmentType;
    }
    
    
    @Override
    public boolean equals(Object object)
    {
        boolean sameSame = false;

        if (object != null && object instanceof PartWithQuantity)
        {
            sameSame = this.part == ((PartWithQuantity) object).part;
        }

        return sameSame;
    }

}