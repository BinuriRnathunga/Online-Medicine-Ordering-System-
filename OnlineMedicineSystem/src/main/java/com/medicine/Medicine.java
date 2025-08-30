package com.medicine;

public class Medicine {
    private int medid;
    private String medname;
    private int medqty;
    private double medprice;
    private String category; 
    private String medexpiredate;

   
    public Medicine() {
    }

    // Parameterized constructor
    public Medicine(int medid, String medname, int medqty, double medprice,  String medexpiredate) {
        this.medid = medid;
        this.medname = medname;
        this.medqty = medqty;
        this.medprice = medprice;
        this.medexpiredate = medexpiredate;
    }

    // Getter and Setter methods 

    public int getMedid() {
        return medid;
    }

    public void setMedid(int medid) {
        this.medid = medid;
    }

    public String getMedname() {
        return medname;
    }

    public void setMedname(String medname) {
        this.medname = medname;
    }

    public int getMedqty() {
        return medqty;
    }

    public void setMedqty(int medqty) {
        this.medqty = medqty;
    }

    public double getMedprice() {
        return medprice;
    }

    public void setMedprice(double medprice) {
        this.medprice = medprice;
    }

    
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getMedexpiredate() {
        return medexpiredate;
    }

    public void setMedexpiredate(String medexpiredate) {
        this.medexpiredate = medexpiredate;
    }
}