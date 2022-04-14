package cosc490.morgan.virtualvaccinationcard;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UserHomeActivity extends AppCompatActivity {

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
        EditText vaccinePhoto = findViewById(R.id.EdtVaccinationPhoto);

        Button btnSubmit = findViewById(R.id.btnSubmit);

        DBHandler dbHandler = new DBHandler(UserHomeActivity.this);


        //submit button
        btnSubmit.setOnClickListener(view -> {
            String vaccine_provider = vaccineProvider.getText().toString();
            String dose_1 = dose1.getText().toString();
            String dose_1_num = dose1num.getText().toString();
            String dose_2 = dose2.getText().toString();
            String dose_2_num = dose2num.getText().toString();
            String _booster = booster.getText().toString();
            String booster_num = boosterNum.getText().toString();
            String vaccine_photo = vaccinePhoto.getText().toString();

            dbHandler.addNewVaccine(vaccine_provider, dose_1, dose_1_num, dose_2, dose_2_num,
                    _booster, booster_num, vaccine_photo);

            Toast.makeText(UserHomeActivity.this, "Vaccination Record added successfully", Toast.LENGTH_SHORT).show();
            vaccineProvider.setText("");
            dose1.setText("");
            dose1num.setText("");
            dose2.setText("");
            dose2num.setText("");
            booster.setText("");
            boosterNum.setText("");
            vaccinePhoto.setText("");

            //navigate to home page with vaccination record on it
            Intent intent = new Intent(this, UserHome2Activity.class);
            startActivity(intent);

        });
    }

}
