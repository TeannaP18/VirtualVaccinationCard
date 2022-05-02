package cosc490.morgan.virtualvaccinationcard;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;


@SuppressWarnings("ALL")
public class UserHomeActivity extends AppCompatActivity implements View.OnClickListener {

    private static String rProvider, rDose1Date, rDose1Num, rDose2Date, rDose2Num, rBoosterDate, rBoosterNum;
    private static final int RESULT_LOAD_IMAGE = 1;
    ImageView ivVaccinationCard;
    Button btnSubmit;
    EditText vaccineProvider, dose1, dose1num, dose2, dose2num, booster, boosterNum;
    String base64Image, userName, userPassword;
    DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);

        //edit texts
        vaccineProvider = (EditText) findViewById(R.id.EdtVaccineProvider);
        dose1 = (EditText) findViewById(R.id.EdtDose1);
        dose1num = (EditText) findViewById(R.id.EdtDose1Num);
        dose2 = (EditText) findViewById(R.id.EdtDose2);
        dose2num = (EditText) findViewById(R.id.EdtDose2Num);
        booster = (EditText) findViewById(R.id.EdtBooster);
        boosterNum = (EditText) findViewById(R.id.EdtBoosterNum);
        //image view
        ivVaccinationCard = (ImageView) findViewById(R.id.ivVaccinationCard);
        //button
        btnSubmit = (Button) findViewById(R.id.btnSubmit);

        ivVaccinationCard.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);

        dbHandler = new DBHandler(UserHomeActivity.this);

        userName = LoginActivity.returnUserName();
        userPassword = LoginActivity.returnUserPassword();

    }



    public static String returnVaccineProvider(){return rProvider;}
    public static String returnDose1Date(){return rDose1Date;}
    public static String returnDose1Num(){return rDose1Num;}
    public static String returnDose2Date(){return rDose2Date;}
    public static String returnDose2Num(){return rDose2Num;}
    public static String returnBooster(){return rBoosterDate;}
    public static String returnBoosterNum(){return rBoosterNum;}

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ivVaccinationCard:
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE);
                break;
            case R.id.btnSubmit:
                String vaccine_provider = vaccineProvider.getText().toString();
                String dose_1 = dose1.getText().toString();
                String dose_1_num = dose1num.getText().toString();
                String dose_2 = dose2.getText().toString();
                String dose_2_num = dose2num.getText().toString();
                String _booster = booster.getText().toString();
                String booster_num = boosterNum.getText().toString();


                dbHandler.addNewRecord(userName, userPassword, vaccine_provider, dose_1, dose_1_num, dose_2, dose_2_num,
                        _booster, booster_num, base64Image);

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

                intent.putExtra("VaccineProvider", vaccine_provider);
                intent.putExtra("Dose1Date", dose_1);
                intent.putExtra("Dose1Num", dose_1_num);
                intent.putExtra("Dose2Date", dose_2);
                intent.putExtra("Dose2Num", dose_2_num);
                intent.putExtra("BoosterDate", _booster);
                intent.putExtra("BoosterNum", booster_num);

                startActivity(intent);
                finish();
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null){
            Uri selectedImage = data.getData();
            ivVaccinationCard.setImageURI(selectedImage);

            //uri to bitmap conversion

            if(selectedImage != null){
                //uri to bitmap conversion
                Bitmap bitmap = null;
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //bitmap to base64 string conversion
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
                byte[] bytes = outputStream.toByteArray();

                base64Image = Base64.getEncoder().encodeToString(bytes);
            }
            
        }
    }
}
