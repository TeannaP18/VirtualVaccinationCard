package cosc490.morgan.virtualvaccinationcard;

import android.graphics.Bitmap;

//modal class for storing vaccination card data
public class VaccinationModal {

    private String userName = LoginActivity.returnUserName();

    //vaccination provider
    private String vac_provider;
    //dose 1 date
    private String dose1_date;
    //dose 1 #
    private String dose1_num;
    //dose 2 date
    private String dose2_date;
    //dose 2 #
    private String dose2_num;
    //booster date
    private String booster_date;
    //booster #
    private String booster_num;

    //bitmap of card photo
    private String card_photo;

    private int id;


    public String getUserName(){
        return userName;
    }
    public void setUserName(String userName){
        this.userName = userName;
    }

    public String getVac_provider(){
        return vac_provider;
    }
    public void setVac_provider(String vac_provider){
        this.vac_provider = vac_provider;
    }

    public String getDose1_date(){
        return dose1_date;
    }
    public void setDose1_date(String dose1_date){
        this.dose1_date = dose1_date;
    }

    public String getDose1_num(){
        return dose1_num;
    }
    public void setDose1_num(String dose1_num){
        this.dose1_num = dose1_num;
    }

    public String getDose2_date(){
        return dose2_date;
    }
    public void setDose2_date(String dose2_date){
        this.dose2_date = dose2_date;
    }

    public String getDose2_num(){
        return dose2_num;
    }
    public void setDose2_num(String dose2_num){
        this.dose2_num = dose2_num;
    }

    public String getBooster_date(){ return booster_date; }
    public void setBooster_date(String Booster_date){
        this.booster_date = booster_date;
    }

    public String getBooster_num(){
        return booster_num;
    }
    public void setBooster_num(String Booster_num){
        this.booster_num = booster_num;
    }

    public String getCard_photo(){
        return card_photo;
    }
    public void setCard_photo(String card_photo) {
        this.card_photo = card_photo;
    }

    public int getId(){
        return id;
    }
    public void setId(){
        this.id = id;
    }

    public VaccinationModal(String vac_provider, String dose1_date, String dose1_num,
                            String dose2_date, String dose2_num, String booster_date, String booster_num, String card_photo){
        this.vac_provider = vac_provider;
        this.dose1_date = dose1_date;
        this.dose1_num = dose1_num;
        this.dose2_date = dose2_date;
        this.dose2_num = dose2_num;
        this.booster_date = booster_date;
        this.booster_num = booster_num;
        this.card_photo = card_photo;
    }
}
