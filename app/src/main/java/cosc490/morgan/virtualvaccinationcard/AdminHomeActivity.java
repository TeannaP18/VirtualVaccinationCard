package cosc490.morgan.virtualvaccinationcard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Switch;

import java.util.ArrayList;

//This class:
//gets all vaccination records from db and displays them in a recycler view
//updates the approval status column for each vaccination record if the switch is set to on/true
public class AdminHomeActivity extends AppCompatActivity {

    private ArrayList<VaccinationModal> vaccinationModalArrayList;
    private DBHandler dbHandler;
    private VaccinationRVAdapter vaccinationRVAdapter;
    private RecyclerView vaccinationsRV;
    private Switch aSwitch;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        vaccinationModalArrayList = new ArrayList<>();
        dbHandler = new DBHandler(AdminHomeActivity.this);

        vaccinationModalArrayList = dbHandler.readVaccinations();

        vaccinationRVAdapter = new VaccinationRVAdapter(vaccinationModalArrayList, AdminHomeActivity.this);
        vaccinationsRV = findViewById(R.id.VaccinationsRV);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(AdminHomeActivity.this, RecyclerView.VERTICAL, false);
        vaccinationsRV.setLayoutManager(linearLayoutManager);

        vaccinationsRV.setAdapter(vaccinationRVAdapter);



    }
}
