package mapp.com.sg.myfirstapplication;

import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.EditText;

import com.baoyz.swipemenulistview.SwipeMenuListView;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class InputValueScreen extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input_interface);

        // Keyboard part
        EditText inputNumberText = (EditText) findViewById(R.id.inputNumber_txt);
        MyKeyboard keyboard = (MyKeyboard) findViewById(R.id.keyboard);
        inputNumberText.setRawInputType(InputType.TYPE_CLASS_TEXT);
        inputNumberText.setTextIsSelectable(false);
        inputNumberText.setShowSoftInputOnFocus(false); // disable keyboard
        InputConnection ic = inputNumberText.onCreateInputConnection(new EditorInfo());
        keyboard.setInputConnection(ic);

    }
}
