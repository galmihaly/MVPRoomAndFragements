package hu.unideb.inf.mvproomandfragements.Fragments.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import hu.unideb.inf.mvproomandfragements.Database.Models.PersonEntity;
import hu.unideb.inf.mvproomandfragements.R;

public class DatabaseFragmentViewAdapter extends RecyclerView.Adapter<DatabaseFragmentViewAdapter.DatabaseFragmentViewHolder> {

    private List<PersonEntity> personEntityList;

    public DatabaseFragmentViewAdapter(List<PersonEntity> personEntityList) {
        this.personEntityList = personEntityList;
    }

    @NonNull
    @Override
    public DatabaseFragmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.database_item_layout,
                parent, false);

        return new DatabaseFragmentViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull DatabaseFragmentViewHolder holder, int position) {
        if(personEntityList != null){
            holder.getPersonItem().setText(personEntityList.get(position).toString());
        }
    }

    @Override
    public int getItemCount() {
        if(personEntityList == null) return -1;

        return personEntityList.size();
    }

    public class DatabaseFragmentViewHolder extends RecyclerView.ViewHolder {

        private TextView personItem;
        public DatabaseFragmentViewHolder(@NonNull View itemView) {
            super(itemView);
            personItem = itemView.findViewById(R.id.personItem);
        }

        public TextView getPersonItem() {
            return personItem;
        }
    }
}
