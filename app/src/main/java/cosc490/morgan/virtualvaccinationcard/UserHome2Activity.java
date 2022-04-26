package cosc490.morgan.virtualvaccinationcard;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class UserHome2Activity extends AppCompatActivity {

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

        //if approved from admin side, approval status = "Approved"
        approvalStatus.setText("Not Approved");
    }
}