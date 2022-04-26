package cosc490.morgan.virtualvaccinationcard;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UserHomeActivity extends AppCompatActivity {

    private static String rProvider, rDose1Date, rDose1Num, rDose2Date, rDose2Num, rBoosterDate, rBoosterNum;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);

        EditText vaccineProvider = findViewById(R.id.EdtVaccineProvider);
        EditText dose1 = findViewById(R.id.EdtDose1);
        EditText dose1num = findViewById(R.id.EdtDose1Num);
        EditText dose2 = findViewById(R.id.EdtDose2);
        EditText dose2num = findViewById(R.id.EdtDose2Num);
        EditText booster = findViewById(R.id.EdtBooster);
        EditText boosterNum = findViewById(R.id.EdtBoosterNum);
        //EditText vaccinePhoto = findViewById(R.id.EdtVaccinationPhoto);

        Button btnSubmit = findViewById(R.id.btnSubmit);

        DBHandler dbHandler = new DBHandler(UserHomeActivity.this);

        String userName = LoginActivity.returnUserName();
        String userPassword = LoginActivity.returnUserPassword();

        rProvider = vaccineProvider.getText().toString();
        rDose1Date = dose1.getText().toString();
        rDose1Num = dose1num.getText().toString();
        rDose2Date = dose2.getText().toString();
        rDose2Num = dose2num.getText().toString();
        rBoosterDate = booster.getText().toString();
        rBoosterNum = boosterNum.getText().toString();



        //submit button
        btnSubmit.setOnClickListener(view -> {
            String vaccine_provider = vaccineProvider.getText().toString();
            String dose_1 = dose1.getText().toString();
            String dose_1_num = dose1num.getText().toString();
            String dose_2 = dose2.getText().toString();
            String dose_2_num = dose2num.getText().toString();
            String _booster = booster.getText().toString();
            String booster_num = boosterNum.getText().toString();


            dbHandler.addNewRecord(userName, userPassword, vaccine_provider, dose_1, dose_1_num, dose_2, dose_2_num,
                    _booster, booster_num);

            Toast.makeText(UserHomeActivity.this, "Vaccination Record added successfully", Toast.LENGTH_SHORT).show();
            vaccineProvider.setText("");
            dose1.setText("");
            dose1num.setText("");
            dose2.setText("");
            dose2num.setText("");
            booster.setText("");
            boosterNum.setText("");


            //navigate to home page with vaccination record on it
            Intent intent = new Intent(this, UserHome2Activity.class);
            startActivity(intent);

        });
    }

    public static String returnVaccineProvider(){return rProvider;}
    public static String returnDose1Date(){return rDose1Date;}
    public static String returnDose1Num(){return rDose1Num;}
    public static String returnDose2Date(){return rDose2Date;}
    public static String returnDose2Num(){return rDose2Num;}
    public static String returnBooster(){return rBoosterDate;}
    public static String returnBoosterNum(){return rBoosterNum;}

}
