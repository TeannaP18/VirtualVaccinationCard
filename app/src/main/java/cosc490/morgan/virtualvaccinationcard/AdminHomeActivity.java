package cosc490.morgan.virtualvaccinationcard;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdminHomeActivity extends AppCompatActivity {

    private final RecyclerView vaccinationsRV;

    public AdminHomeActivity(RecyclerView vaccinationsRV) {
        this.vaccinationsRV = vaccinationsRV;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        ArrayList<VaccinationModal> vaccinationModalArrayList;
        DBHandler dbHandler = new DBHandler(AdminHomeActivity.this);

        vaccinationModalArrayList = dbHandler.readVaccinations();

        VaccinationRVAdapter vaccinationRVAdapter = new VaccinationRVAdapter(vaccinationModalArrayList, AdminHomeActivity.this);
        vaccinationsRV.findViewById(R.id.VaccinationsRV);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(AdminHomeActivity.this, RecyclerView.VERTICAL, false);
        vaccinationsRV.setLayoutManager(linearLayoutManager);

        vaccinationsRV.setAdapter(vaccinationRVAdapter);


    }
}
