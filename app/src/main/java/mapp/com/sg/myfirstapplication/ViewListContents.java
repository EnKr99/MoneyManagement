package mapp.com.sg.myfirstapplication;


import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import mapp.com.sg.myfirstapplication.Adapters.ListViewAdapter;
import mapp.com.sg.myfirstapplication.Model.Expense;
import mapp.com.sg.myfirstapplication.Model.Income;
import android.content.Intent;


public class ViewListContents extends AppCompatActivity {
    DatabaseHelper myDB;
//    SwipeMenuListView listView;
    ListView listView;
    ArrayList<Expense> arrayList_expense;
    ArrayList<Income> arrayList_income;
    ListViewAdapter listViewAdapter;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewcontents_layout);

        listView = (ListView) findViewById(R.id.listView);
        myDB = new DatabaseHelper(this);
        arrayList_expense = new ArrayList<>();
        arrayList_income = new ArrayList<>();

        // Populate the data into the ListView
        loadDataInListView();

        // STOP HERE
        TextView textView = (TextView) findViewById(R.id.viewAllExpenses);
        FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(R.id.addButton);

        textView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ViewListContents.this, ViewAllListContents.class);
                startActivity(i);
            }
        });

        floatingActionButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ViewListContents.this, InputValueScreen.class);
                startActivity(i);
            }
        });

    } // End onCreate



    private void loadDataInListView() {
        arrayList_expense = myDB.getTodayExpenseData(); // test
//        arrayList_income = myDB.getAllIncomeData();
//        myDB.dropTable(); // RECREATE

        listViewAdapter = new ListViewAdapter(this, arrayList_expense);
        listViewAdapter.notifyDataSetChanged();
        listView.setAdapter(listViewAdapter);

    } // End loadDataInListView



    private void toastMassage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
