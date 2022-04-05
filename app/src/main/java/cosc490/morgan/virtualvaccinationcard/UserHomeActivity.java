package cosc490.morgan.virtualvaccinationcard;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class UserHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);

        EditText vaccineProvider = (EditText) findViewById(R.id.EdtVaccineProvider);
        EditText dose1 = (EditText) findViewById(R.id.EdtDose1);
        EditText dose1num = (EditText) findViewById(R.id.EdtDose1Num);
        EditText dose2 = (EditText) findViewById(R.id.EdtDose2);
        EditText dose2num = (EditText) findViewById(R.id.EdtDose2Num);
        EditText booster = (EditText) findViewById(R.id.EdtBooster);
        EditText boosterNum = (EditText) findViewById(R.id.EdtBoosterNum);
        EditText vaccinePhoto = (EditText) findViewById(R.id.EdtVaccinationPhoto);

        Button btnSubmit = (Button) findViewById(R.id.btnSubmit);

        //submit button
        btnSubmit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //add fields to database
            }
        });
    }

}
