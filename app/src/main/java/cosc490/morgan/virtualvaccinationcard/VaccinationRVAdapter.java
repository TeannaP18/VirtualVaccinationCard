package cosc490.morgan.virtualvaccinationcard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

//adapter class for setting data to items of recycler view
public class VaccinationRVAdapter extends RecyclerView.Adapter<VaccinationRVAdapter.ViewHolder> {

    private final ArrayList<VaccinationModal> vaccinationModalArrayList;

    public VaccinationRVAdapter(ArrayList<VaccinationModal> vaccinationModalArrayList, Context context){
        this.vaccinationModalArrayList = vaccinationModalArrayList;
    }


    @NonNull
    @Override
    public VaccinationRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_vaccination_record, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VaccinationRVAdapter.ViewHolder holder, int position) {
        VaccinationModal modal = vaccinationModalArrayList.get(position);
        holder.userName.setText(LoginActivity.returnUserName());
        holder.dose1DateTV.setText(modal.getDose1_date());
        holder.dose1NumTV.setText(modal.getDose1_num());
        holder.dose2DateTV.setText(modal.getDose2_date());
        holder.dose2NumTV.setText(modal.getDose2_num());
        holder.boosterDateTV.setText(modal.getBooster_date());
        holder.boosterNumTV.setText(modal.getBooster_num());
        holder.cardPhotoTV.setText(modal.getCard_photo());
    }

    @Override
    public int getItemCount() {
        return vaccinationModalArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView userName;
        private final TextView dose1DateTV;
        private final TextView dose1NumTV;
        private final TextView dose2DateTV;
        private final TextView dose2NumTV;
        private final TextView boosterDateTV;
        private final TextView boosterNumTV;
        private final TextView cardPhotoTV;

        public ViewHolder(View itemView) {
            super(itemView);
            userName = itemView.findViewById(R.id.tvUserName);
            dose1DateTV = itemView.findViewById(R.id.tvDose1Date);
            dose1NumTV = itemView.findViewById(R.id.tvDose1Num);
            dose2DateTV = itemView.findViewById(R.id.tvDose2Date);
            dose2NumTV = itemView.findViewById(R.id.tvDose2Num);
            boosterDateTV = itemView.findViewById(R.id.tvBoosterDate);
            boosterNumTV = itemView.findViewById(R.id.tvBoosterNum);
            cardPhotoTV = itemView.findViewById(R.id.imageVaccination);
        }
    }
}
