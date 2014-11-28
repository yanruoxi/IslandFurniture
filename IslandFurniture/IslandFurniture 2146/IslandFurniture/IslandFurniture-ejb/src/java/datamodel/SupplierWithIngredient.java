/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package datamodel;

import entity.kms.Ingredient;
import entity.kms.KitchenSupplier;
import java.io.Serializable;

/**
 *
 * @author ZiGui
 */
public class SupplierWithIngredient implements Serializable {
    private KitchenSupplier kitchenSupplier;
    private Ingredient ingredient;
    private Long freq;

    public SupplierWithIngredient(KitchenSupplier kitchenSupplier, Ingredient ingredientt) {
        this.kitchenSupplier = kitchenSupplier;
        this.ingredient = ingredientt;
    }
    
    public SupplierWithIngredient() {
    }

    public KitchenSupplier getKitchenSupplier() {
        return kitchenSupplier;
    }

    public void setKitchenSupplier(KitchenSupplier kitchenSupplier) {
        this.kitchenSupplier = kitchenSupplier;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public Long getFreq() {
        return freq;
    }

    public void setFreq(Long freq) {
        this.freq = freq;
    }
}
