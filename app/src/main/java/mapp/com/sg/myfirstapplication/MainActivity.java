package mapp.com.sg.myfirstapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.ExtractedTextRequest;
import android.view.inputmethod.InputConnection;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListPopupWindow;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input_interface);

        // spinner
//        Spinner mySpinner = (Spinner) findViewById(R.id.spinner1);
//        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(MainActivity.this,
//                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.months));
//        listViewAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        mySpinner.setAdapter(listViewAdapter);


        // Keyboard part
        EditText inputNumberText = (EditText) findViewById(R.id.inputNumber_txt);
        MyKeyboard keyboard = (MyKeyboard) findViewById(R.id.keyboard);
        inputNumberText.setRawInputType(InputType.TYPE_CLASS_TEXT);
        inputNumberText.setTextIsSelectable(false);
        inputNumberText.setShowSoftInputOnFocus(false); // disable keyboard
        InputConnection ic = inputNumberText.onCreateInputConnection(new EditorInfo());
        keyboard.setInputConnection(ic);


        Button viewButton = (Button) findViewById(R.id.viewButton);
        viewButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, ViewListContents.class);
                startActivity(i);
            }
        });


    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.overviewBtn:
                Intent i = new Intent(this, OverallMoney.class);
                startActivity(i);
                break;
        }



    }

    private void toastMassage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }


}
