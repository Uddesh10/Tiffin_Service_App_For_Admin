package com.uddesh.tiffinserviceappforadmin.DataModels;

public class PersonalDetailsModel {

    private final String providername;
    private final String contactno;
    private final boolean vegnonveg;
    private final boolean lunch;
    private final boolean dinner;
    private final String upiid;
    private final String lunchtimefrom;
    private final String lunchtimeto;
    private final String dinnertimefrom;
    private final String dinnertimeto;
    private final String logoimage;

    public PersonalDetailsModel(String providername, String contactno, boolean vegnonveg, boolean lunch, boolean dinner, String upiid, String lunchtimefrom, String lunchtimeto, String dinnertimefrom, String dinnertimeto, String logoimage) {
        this.providername = providername;
        this.contactno = contactno;
        this.vegnonveg = vegnonveg;
        this.lunch = lunch;
        this.dinner = dinner;
        this.upiid = upiid;
        this.lunchtimefrom = lunchtimefrom;
        this.lunchtimeto = lunchtimeto;
        this.dinnertimefrom = dinnertimefrom;
        this.dinnertimeto = dinnertimeto;
        this.logoimage = logoimage;
    }

    public String getProvidername() {
        return providername;
    }

    public String getContactno() {
        return contactno;
    }

    public boolean isVegnonveg() {
        return vegnonveg;
    }

    public boolean isLunch() {
        return lunch;
    }

    public boolean isDinner() {
        return dinner;
    }

    public String getUpiid() {
        return upiid;
    }

    public String getLunchtimefrom() {
        return lunchtimefrom;
    }

    public String getLunchtimeto() {
        return lunchtimeto;
    }

    public String getDinnertimefrom() {
        return dinnertimefrom;
    }

    public String getDinnertimeto() {
        return dinnertimeto;
    }

    public String getLogoimage() {
        return logoimage;
    }
}
