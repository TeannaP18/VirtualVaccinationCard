package cosc490.morgan.virtualvaccinationcard;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Base64;

//This class:
//is the adapter class for setting data to the items of recycler view
public class VaccinationRVAdapter extends RecyclerView.Adapter<VaccinationRVAdapter.ViewHolder>{

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
        //holder.approvalCheckBox.setChecked(false);
        //holder.approvalSwitch.setChecked(false);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, AdminHome2Activity.class);
                intent.putExtra("name", modal.getUserName());
                intent.putExtra("vac_provider", modal.getVac_provider());
                intent.putExtra("dose1_date", modal.getDose1_date());
                intent.putExtra("dose1_num", modal.getDose1_num());
                intent.putExtra("dose2_date", modal.getDose2_date());
                intent.putExtra("dose2_num", modal.getDose2_num());
                intent.putExtra("booster_date", modal.getBooster_date());
                intent.putExtra("booster_num", modal.getBooster_num());
                intent.putExtra("approval", modal.getApproval_status());
                context.startActivity(intent);
            }
        });

//        //base64String conversion to Bitmap to display retrieve image
//        if(modal.getCard_photo() == null){
//            Resources res = context.getResources();
//            Drawable drawable = ResourcesCompat.getDrawable(res, R.drawable.img, null);
//            holder.vaccineCardPhoto.setImageDrawable(drawable);
//        }else{
//            String imageString = modal.getCard_photo();
//            Bitmap cardPhoto = convertBase64String(imageString);
//            holder.vaccineCardPhoto.setImageBitmap(cardPhoto);
//        }
//        String imageString = modal.getCard_photo();
//        Bitmap cardPhoto = convertBase64String(imageString);
//        holder.vaccineCardPhoto.setImageBitmap(cardPhoto);
    }

    @Override
    public int getItemCount() {
        return vaccinationModalArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView userName, vaccineProvider,  dose1DateTV, dose1NumTV, dose2DateTV, dose2NumTV, boosterDateTV, boosterNumTV;
        private ImageView vaccineCardPhoto;
        private CheckBox approvalCheckBox;

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
            //approvalCheckBox = itemView.findViewById(R.id.cbApproval);
            //approvalSwitch = itemView.findViewById(R.id.sApprovalSwitch);
        }

    }


}
