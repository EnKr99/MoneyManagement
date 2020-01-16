package mapp.com.sg.myfirstapplication.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import mapp.com.sg.myfirstapplication.Model.Expense;
import mapp.com.sg.myfirstapplication.R;

public class ListViewAdapter extends BaseAdapter {
    Context context;
    ArrayList<Expense> arrayList;


    public ListViewAdapter(Context context) {
        this.context = context;
        this.arrayList = getArrayList();

    }

    public ListViewAdapter(Context context, ArrayList<Expense> arrayList) {
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
        convertView = inflater.inflate(R.layout.mycustomlistview, null);
        TextView t1_id = (TextView) convertView.findViewById(R.id.id_txt);
        TextView t2_memo = (TextView) convertView.findViewById(R.id.memo_txt);
        TextView t3_money = (TextView) convertView.findViewById(R.id.money_txt);


        Expense expense = arrayList.get(position);


        t1_id.setText(String.valueOf(expense.getId()));
        t2_memo.setText(expense.getMemo());
        t2_memo.setTextColor(Color.parseColor("#ff0000"));
        t3_money.setText(expense.getMoney().toString());
        t3_money.setTextColor(Color.parseColor("#ff0000"));

        return convertView;
    }

    @Override
    public int getCount() {
        return this.arrayList.size();
    }

    public ArrayList<Expense> getArrayList() {
        return arrayList;
    }
}
