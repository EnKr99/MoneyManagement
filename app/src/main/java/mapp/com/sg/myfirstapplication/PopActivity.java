package mapp.com.sg.myfirstapplication;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
import android.widget.Toast;

import mapp.com.sg.myfirstapplication.Adapters.ListViewAdapter;

public class PopActivity extends Activity {
    private static final String TAG = "PopActivity";
    private Button buttonSave, buttonClose;
    private EditText editable_memo, editable_money;
    private String selectedMemo;
    private Double selectedMoney;
    private int selectedID;
    DatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width * .8), (int)(height * .5));

        WindowManager.LayoutParams params = getWindow().getAttributes();

        params.gravity = Gravity.CENTER;
        params.x = 0;
        params.y = -20;

        getWindow().setAttributes(params);

        // populate
        buttonClose = (Button) findViewById(R.id.button_close);
        buttonClose.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        editable_memo = (EditText) findViewById(R.id.editable_memo);
        editable_money = (EditText) findViewById(R.id.editable_money);
        myDB = new DatabaseHelper(this);

        // get extras from ListViewContents
        Intent receivedIntent = getIntent();
        selectedID = receivedIntent.getIntExtra("id", -1);
        selectedMemo = receivedIntent.getStringExtra("memo");
        selectedMoney = receivedIntent.getDoubleExtra("money", -1);

        toastMassage(selectedID+", "+selectedMemo+", "+selectedMoney); // TEST

        editable_memo.setText(selectedMemo);
        editable_money.setText("" + selectedMoney);

        buttonSave = (Button) findViewById(R.id.button_save);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputMemo = editable_memo.getText().toString();
                Double inputMoney = Double.valueOf(editable_money.getText().toString());
                if (inputMemo.equals("") | inputMoney.equals("")) {
                    toastMassage("Please enter something!");
                } else {
                    myDB.updateName(selectedID, inputMemo, inputMoney);
                    toastMassage("Edited!");

                    finish();

                    Intent reloadViewListContents = new Intent(PopActivity.this, ViewListContents.class);
                    startActivity(reloadViewListContents);

                }


            }
        });










    } //

    private void toastMassage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }



}