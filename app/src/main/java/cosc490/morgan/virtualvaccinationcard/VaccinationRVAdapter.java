package cosc490.morgan.virtualvaccinationcard;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Base64;

//adapter class for setting data to items of recycler view
public class VaccinationRVAdapter extends RecyclerView.Adapter<VaccinationRVAdapter.ViewHolder> {

    private final ArrayList<VaccinationModal> vaccinationModalArrayList;
    private final Context context;

    public VaccinationRVAdapter(ArrayList<VaccinationModal> vaccinationModalArrayList, Context context){
        this.vaccinationModalArrayList = vaccinationModalArrayList;
        this.context = context;
    }


    @NonNull
    @Override
    public VaccinationRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_vaccination_record, parent, false);
        return new ViewHolder(view);
    }

    //try the below holders with the set card functions from vaccination modal

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull VaccinationRVAdapter.ViewHolder holder, int position) {
        VaccinationModal modal = vaccinationModalArrayList.get(position);
        holder.userName.setText(modal.getUserName());
        holder.vaccineProvider.setText(modal.getVac_provider());
        holder.dose1DateTV.setText(modal.getDose1_date());
        holder.dose1NumTV.setText(modal.getDose1_num());
        holder.dose2DateTV.setText(modal.getDose2_date());
        holder.dose2NumTV.setText(modal.getDose2_num());
        holder.boosterDateTV.setText(modal.getBooster_date());
        holder.boosterNumTV.setText(modal.getBooster_num());

        //base64String conversion to Bitmap to display retrieve image
        String imageString = modal.getCard_photo();
        Bitmap cardPhoto = convertBase64String(imageString);
        holder.vaccineCardPhoto.setImageBitmap(cardPhoto);
    }

    @Override
    public int getItemCount() {
        return vaccinationModalArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView userName, vaccineProvider,  dose1DateTV, dose1NumTV, dose2DateTV, dose2NumTV, boosterDateTV, boosterNumTV;
        private ImageView vaccineCardPhoto;

        public ViewHolder(View itemView) {
            super(itemView);
            userName = itemView.findViewById(R.id.tvUserName);
            vaccineProvider = itemView.findViewById(R.id.tvVaccineProvider);
            dose1DateTV = itemView.findViewById(R.id.tvDose1Date);
            dose1NumTV = itemView.findViewById(R.id.tvDose1Num);
            dose2DateTV = itemView.findViewById(R.id.tvDose2Date);
            dose2NumTV = itemView.findViewById(R.id.tvDose2Num);
            boosterDateTV = itemView.findViewById(R.id.tvBoosterDate);
            boosterNumTV = itemView.findViewById(R.id.tvBoosterNum);
            vaccineCardPhoto = itemView.findViewById(R.id.ivVaccinePhoto);
        }
    }

    //convert base64 string to uri or bitmap
    @RequiresApi(api = Build.VERSION_CODES.O)
    public Bitmap convertBase64String(String base64String){
        byte[] decodedBase64String = Base64.getDecoder().decode(base64String);
        Bitmap decodedByteArray = BitmapFactory.decodeByteArray(decodedBase64String, 0, base64String.length());

        return decodedByteArray;
    }


}
