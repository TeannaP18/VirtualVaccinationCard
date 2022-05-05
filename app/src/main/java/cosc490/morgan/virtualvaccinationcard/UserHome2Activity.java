package cosc490.morgan.virtualvaccinationcard;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

//This class:
//gets user inputted vaccination details and image from UserHomeActivity and/or the database and displays it on the screen 
public class UserHome2Activity extends AppCompatActivity {

    String vaccine_provider, dose1_date, dose1_num, dose2_date, dose2_num, booster_date, booster_num;
    int approval_status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home2);

        TextView vaccineProvider = findViewById(R.id.VaccineProvider);
        TextView dose1 = findViewById(R.id.Dose1Date);
        TextView dose1num = findViewById(R.id.Dose1Num);
        TextView dose2 = findViewById(R.id.Dose2Date);
        TextView dose2num = findViewById(R.id.Dose2Num);
        TextView booster = findViewById(R.id.BoosterDate);
        TextView boosterNum = findViewById(R.id.BoosterNum);
        ImageView vaccinationCard = findViewById(R.id.UserVaccinationCard);
        TextView approvalStatus = findViewById(R.id.UserApprovalStatus);

        vaccineProvider.setText(UserHomeActivity.returnVaccineProvider());
        dose1.setText(UserHomeActivity.returnDose1Date());
        dose1num.setText(UserHomeActivity.returnDose1Num());
        dose2.setText(UserHomeActivity.returnDose2Date());
        dose2num.setText(UserHomeActivity.returnDose2Num());
        booster.setText(UserHomeActivity.returnBooster());
        boosterNum.setText(UserHomeActivity.returnBoosterNum());

        vaccine_provider = getIntent().getExtras().getString("vac_provider");
        dose1_date = getIntent().getExtras().getString("dose1_date");
        dose1_num = getIntent().getExtras().getString("dose1_num");
        dose2_date = getIntent().getExtras().getString("dose2_date");
        dose2_num = getIntent().getExtras().getString("dose2_num");
        booster_date = getIntent().getExtras().getString("booster_date");
        booster_num = getIntent().getExtras().getString("booster_num");
        approval_status = getIntent().getExtras().getInt("approval");

        vaccineProvider.setText(vaccine_provider);
        dose1.setText(dose1_date);
        dose1num.setText(dose1_num);
        dose2.setText(dose2_date);
        dose2num.setText(dose2_num);
        booster.setText(booster_date);
        boosterNum.setText(booster_num);

        if (approval_status == 0){
            approvalStatus.setText("NOT Approved");
        }
        if (approval_status == 1){
            approvalStatus.setText("Approved!");
        }
    }
}