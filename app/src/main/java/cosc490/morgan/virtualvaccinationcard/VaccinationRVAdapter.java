package cosc490.morgan.virtualvaccinationcard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

//adapter class for setting data to items of recycler view
public class VaccinationRVAdapter extends RecyclerView.Adapter<VaccinationRVAdapter.ViewHolder> {

    private ArrayList<VaccinationModal> vaccinationModalArrayList;
    private Context context;

    public VaccinationRVAdapter(ArrayList<VaccinationModal> vaccinationModalArrayList,Context context){
        this.vaccinationModalArrayList = vaccinationModalArrayList;
        this.context = context;
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
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View view) {
            super();
        }
    }
}
