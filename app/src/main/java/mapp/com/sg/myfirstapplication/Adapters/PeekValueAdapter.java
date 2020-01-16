package mapp.com.sg.myfirstapplication.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import mapp.com.sg.myfirstapplication.Model.Expense;
import mapp.com.sg.myfirstapplication.R;

public class PeekValueAdapter extends BaseAdapter {
    Context context;
    ArrayList<Expense> arrayList;

    public PeekValueAdapter(Context context, ArrayList<Expense> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.mycustompeekvalueview, null);
        TextView t1_income = (TextView) convertView.findViewById(R.id.incomeValue_txt);
        TextView t2_spent = (TextView) convertView.findViewById(R.id.spentValue_txt);
        TextView t3_balance = (TextView) convertView.findViewById(R.id.balanceValue_txt);

        Expense expense = arrayList.get(position);

        t1_income.setText(String.valueOf(expense.getId()));
        t2_spent.setText(expense.getMemo());
        t3_balance.setText(expense.getMoney().toString());

        return convertView;
    }

    @Override
    public int getCount() {
        return this.arrayList.size();
    }
}
