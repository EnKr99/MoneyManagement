package mapp.com.sg.myfirstapplication;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import mapp.com.sg.myfirstapplication.Adapters.ListViewAdapter;
import mapp.com.sg.myfirstapplication.Model.Expense;
import mapp.com.sg.myfirstapplication.Model.Income;


public class ViewAllListContents extends AppCompatActivity {
    DatabaseHelper myDB;
    SwipeMenuListView listView;
    ArrayList<Expense> arrayList_expense;
    ArrayList<Income> arrayList_income;
    ListViewAdapter listViewAdapter;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewallcontents_layout);

        Log.i("----------------------------------------------------------", "viewallcontent");
        listView = (SwipeMenuListView) findViewById(R.id.listView);
        myDB = new DatabaseHelper(this);
        arrayList_expense = new ArrayList<>();
        arrayList_income = new ArrayList<>();
        loadDataInListView();



    } // End onCreate



    private void loadDataInListView() {
//        arrayList_expense = myDB.getTodayExpenseData(); // test
        arrayList_expense = myDB.getAllExpenseData(); // test
//        arrayList_income = myDB.getAllIncomeData();
//        myDB.dropTable(); // RECREATE

        listViewAdapter = new ListViewAdapter(this, arrayList_expense);
        listViewAdapter.notifyDataSetChanged();
        listView.setAdapter(listViewAdapter);



        // Peekvalue area -----------------------------------------------------------------------------------------------------
//        TextView incomeText = (TextView) findViewById(R.id.incomeValue_txt);
//        TextView spentText = (TextView) findViewById(R.id.spentValue_txt);
//        TextView balanceText = (TextView) findViewById(R.id.balanceValue_txt);
//
//
//        double income = 0.0;
//        double expense = 0.0;
//        double balance = 0.0;
//        for (int i = 0; i < arrayList_income.size(); i++) {
//            income += Double.valueOf(arrayList_income.get(i).getMoney()); // Get all incomes
//        }
//        incomeText.setText("$ "+income);
//        incomeText.setTextColor(Color.parseColor("#00FF00"));
//
//
//        for (int i = 0; i < arrayList_expense.size(); i++) {
//            expense += Double.valueOf(arrayList_expense.get(i).getMoney()); // Get all expenses
//        }
//        spentText.setText("$ "+expense);
//        spentText.setTextColor(Color.parseColor("#FF0000"));
//
//        balance = (income - expense);
//        balanceText.setText("$ " + balance); // stop hereeee


        // Swipe listView ------------------------------------------------------------------------------------------------------
        SwipeMenuCreator creator = new SwipeMenuCreator() {
            @Override
            public void create(SwipeMenu menu) {
                // create "open" item
                SwipeMenuItem editItem = new SwipeMenuItem(getApplicationContext());
                // set item background
                editItem.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9,0xCE)));
                // set item width
                editItem.setWidth(170);
                // set a icon
                editItem.setIcon(R.drawable.ic_edit);
                // set item title font color
                editItem.setTitleColor(Color.WHITE);
                // add to menu
                menu.addMenuItem(editItem);

                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(getApplicationContext());
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9, 0x3F, 0x25)));
                // set item width
                deleteItem.setWidth(170);
                // set a icon
                deleteItem.setIcon(R.drawable.ic_delete);
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };

        // set creator
        listView.setMenuCreator(creator);
        listView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                arrayList_expense = myDB.getTodayExpenseData();

                switch (index) {
                    case 0:
                        // edit
                        PopEditWindow(arrayList_expense.get(position).getId(), arrayList_expense.get(position).getMemo(), Double.valueOf(arrayList_expense.get(position).getMoney()));

//                        toastMassage( arrayList.get(position).getId() + ", " + arrayList.get(position).getMemo() + ", " + arrayList.get(position).getMoney());
                        break;
                    case 1:
                        // delete
                        myDB.deleteName(arrayList_expense.get(position).getId());
//                        loadDataInListView();

                        break;
                }

                loadDataInListView();

                // false : close the menu; true : not close the menu
                return false;
            }


            private void PopEditWindow(int id, String memo, Double money) {
                Intent intent = new Intent(ViewAllListContents.this, PopActivity.class);
                intent.putExtra("id", id);
                intent.putExtra("memo", memo);
                intent.putExtra("money", money);

                startActivity(intent);
            }




        });


    } // End loadDataInListView



    private void toastMassage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
