/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package datamodel;

import entity.Part;
import entity.Supplier;
import java.io.Serializable;
import java.util.Comparator;

/**
 *
 * @author Meiling
 */
public class SupplierWithScore implements Serializable, Comparable<SupplierWithScore>{
    private int rank;
    private Supplier supplier;
    private Double score;
    private Part part;
    private int numOfDelivery;

    public SupplierWithScore() {
    }

    public SupplierWithScore(int rank, Supplier supplier, Double score, Part part, int numOfDelivery) {
        this.rank = rank;
        this.supplier = supplier;
        this.score = score;
        this.part = part;
        this.numOfDelivery = numOfDelivery;
    }

    public SupplierWithScore(Supplier supplier, Double score, Part part, int numOfDelivery) {
        this.supplier = supplier;
        this.score = score;
        this.part = part;
        this.numOfDelivery = numOfDelivery;
    }
    
    
    
    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Part getPart() {
        return part;
    }

    public void setPart(Part part) {
        this.part = part;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getNumOfDelivery() {
        return numOfDelivery;
    }

    public void setNumOfDelivery(int numOfDelivery) {
        this.numOfDelivery = numOfDelivery;
    }
    
    /*
    **  Implement the natural order for this class
    */
    public int compareTo(SupplierWithScore p)
    {
    	return getScore().compareTo(p.getScore());
    }

    static class ScoreComparator implements Comparator<SupplierWithScore> {
        public int compare(SupplierWithScore p1, SupplierWithScore p2) {
            double score1 = p1.getScore();
            double score2 = p2.getScore();

            if (score2 == score1) {
                return 0;
            } else if (score1 > score2) {
                return 1;
            } else {
                return -1;
            }
        }
    }
}
