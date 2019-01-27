package com.roly.yazan.rolly.Models;
import com.google.firebase.firestore.IgnoreExtraProperties;


//REMEBER TO FUCKING STICK TO NAMING CONVENTIONS BECAUSE APARENTLY FIRESTORE FUCKING HATES THAT SHIT
@IgnoreExtraProperties
public class ContactModel {

    private String name;
    private String company;
    private int usefullnessBar;
    private String notes;

    public ContactModel () {}

    public ContactModel(String name, String company, int usefullness, String notes){
        this.name = name;
        this.company = company;
        this.usefullnessBar = usefullness;
        this.notes = notes;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public int getUsefullnessBar() {
        return usefullnessBar;
    }

    public void setUsefullnessBar(int usefullnessBar) {
        this.usefullnessBar = usefullnessBar;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }





}
