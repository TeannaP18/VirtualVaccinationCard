package cosc490.morgan.virtualvaccinationcard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

import cosc490.morgan.virtualvaccinationcard.R;

public class AdminHomeActivity extends AppCompatActivity {

    private ArrayList<VaccinationModal> vaccinationModalArrayList;
    private DBHandler dbHandler;
    private VaccinationRVAdapter vaccinationRVAdapter;
    private RecyclerView vaccinationsRV;

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