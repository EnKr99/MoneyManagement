package mapp.com.sg.myfirstapplication.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import mapp.com.sg.myfirstapplication.Model.Expense;
import mapp.com.sg.myfirstapplication.R;

public class AllContentAdapter extends RecyclerView.Adapter<AllContentAdapter.AllContentHolder> {
    private List<Expense> expenseList = new ArrayList<Expense>();

    @NonNull
    @Override
    public AllContentHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewallcontents_layout, parent, false);
        return new AllContentHolder(itemView); // STOP HERE
    }

    @Override
    public void onBindViewHolder(@NonNull AllContentHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class AllContentHolder extends RecyclerView.ViewHolder {
        private TextView textViewMemo;
        private TextView textViewMoney;

        public AllContentHolder(View itemView) {
            super(itemView);
            textViewMemo = itemView.findViewById(R.id.cardtextview1);
            textViewMoney = itemView.findViewById(R.id.cardtextview2);
        }



    }

}
