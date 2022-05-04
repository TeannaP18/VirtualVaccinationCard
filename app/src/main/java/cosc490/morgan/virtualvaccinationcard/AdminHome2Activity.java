package cosc490.morgan.virtualvaccinationcard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class AdminHome2Activity extends AppCompatActivity {

    private TextView userName_2, vaccineProvider_2, dose1Date_2, dose1Num_2, dose2Date_2, dose2Num_2, boosterDate_2, boosterNum_2;
    private ImageView cardImage_2;
    private Button goBack, approved;
    private DBHandler dbHandler;
    String userName2, vaccineProvider2, dose1date2, dose1Num2, dose2Date2, dose2Num2, boosterDate2, boosterNum2, imageString2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home2);

        //initializing all variables
        userName_2 = findViewById(R.id.tvUserName_2);
        vaccineProvider_2 = findViewById(R.id.tvVaccineProvider_2);
        dose1Date_2 = findViewById(R.id.tvDose1Date_2);
        dose1Num_2 = findViewById(R.id.tvDose1Num_2);
        dose2Date_2 = findViewById(R.id.tvDose2Date_2);
        dose2Num_2 = findViewById(R.id.tvDose2Num_2);
        boosterDate_2 = findViewById(R.id.tvBoosterDate_2);
        boosterNum_2 = findViewById(R.id.tvBoosterNum_2);
        cardImage_2 = findViewById(R.id.ivVaccinePhoto_2);

        //initialize buttons
        goBack = findViewById(R.id.btnGoBack);
        approved = findViewById(R.id.btnApproveRecord);

        dbHandler = new DBHandler(AdminHome2Activity.this);

        //getting which data is passed into adapter class
        userName2 = getIntent().getStringExtra("name");
        vaccineProvider2 = getIntent().getStringExtra("vaccine_provider");
        dose1date2 = getIntent().getStringExtra("dose1_date");
        dose1Num2 = getIntent().getStringExtra("dose1_num");
        dose2Date2 = getIntent().getStringExtra("dose2_date");
        dose2Num2 = getIntent().getStringExtra("dose2_num");
        boosterDate2 = getIntent().getStringExtra("booster_date");
        boosterNum2 = getIntent().getStringExtra("booster_num");
        imageString2 = getIntent().getStringExtra("card_photo");

        //setting data to the text views
        userName_2.setText(userName2);
        vaccineProvider_2.setText(vaccineProvider2);
        dose1Date_2.setText(dose1date2);
        dose1Num_2.setText(dose1Num2);
        dose2Date_2.setText(dose2Date2);
        dose2Num_2.setText(dose2Num2);
        boosterDate_2.setText(boosterDate2);
        boosterNum_2.setText(boosterNum2);
        //cardImage_2.setImageBitmap;

        //go back button
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goBackToAdminHome();
            }
        });

        //approved button
        approved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //dbHandler.updateRecord("0", userName2, vaccineProvider2, dose1date2, dose1Num2, dose2Date2, dose2Num2, boosterDate2, boosterNum2, imageString2, 1);
                dbHandler.updateRecord("0", 1);
                Toast.makeText(AdminHome2Activity.this, "Vaccination Record APPROVED!", Toast.LENGTH_SHORT).show();
                goBackToAdminHome();
            }
        });

    }

    public void goBackToAdminHome(){
        Intent intent = new Intent(this, AdminHomeActivity.class);
        startActivity(intent);
    }
}