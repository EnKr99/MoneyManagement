package mapp.com.sg.myfirstapplication;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.view.View;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.ExtractedTextRequest;
import android.widget.Toast;


public class MyKeyboard extends LinearLayout implements View.OnClickListener {

    private Button button1, button2, button3, button4, button5, button6,
            button7, button8, button9, button0, buttonBackspace, buttonEnter,
            buttonClear, buttonDiv, buttonMul, buttonSub, buttonAdd, buttonDot;
    private SparseArray<String> keyValues = new SparseArray<>();
    private InputConnection inputConnection;
    DatabaseHelper myDB;



    public MyKeyboard(Context context) {
        this(context, null, 0);
    }
    public MyKeyboard(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
    public MyKeyboard(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        LayoutInflater.from(context).inflate(R.layout.keyboard, this, true);
        button1 = findViewById(R.id.button_1);
        button1.setOnClickListener(this);
        button2 = findViewById(R.id.button_2);
        button2.setOnClickListener(this);
        button3 = findViewById(R.id.button_3);
        button3.setOnClickListener(this);
        button4 = findViewById(R.id.button_4);
        button4.setOnClickListener(this);
        button5 = findViewById(R.id.button_5);
        button5.setOnClickListener(this);
        button6 = findViewById(R.id.button_6);
        button6.setOnClickListener(this);
        button7 = findViewById(R.id.button_7);
        button7.setOnClickListener(this);
        button8 = findViewById(R.id.button_8);
        button8.setOnClickListener(this);
        button9 = findViewById(R.id.button_9);
        button9.setOnClickListener(this);
        button0 = findViewById(R.id.button_0);
        button0.setOnClickListener(this);
        buttonDot = findViewById(R.id.button_dot);
        buttonDot.setOnClickListener(this);
        buttonClear = findViewById(R.id.button_clear);
        buttonClear.setOnClickListener(this);
        buttonBackspace = findViewById(R.id.button_backspace);
        buttonBackspace.setOnClickListener(this);
        buttonDiv = findViewById(R.id.button_div);
        buttonDiv.setOnClickListener(this);
        buttonMul = findViewById(R.id.button_multi);
        buttonMul.setOnClickListener(this);
        buttonAdd = findViewById(R.id.button_add);
        buttonAdd.setOnClickListener(this);
        buttonSub = findViewById(R.id.button_sub);
        buttonSub.setOnClickListener(this);
        buttonEnter = findViewById(R.id.button_enter);
        buttonEnter.setOnClickListener(this);

        keyValues.put(R.id.button_1,"1");
        keyValues.put(R.id.button_2,"2");
        keyValues.put(R.id.button_3,"3");
        keyValues.put(R.id.button_4,"4");
        keyValues.put(R.id.button_5,"5");
        keyValues.put(R.id.button_6,"6");
        keyValues.put(R.id.button_7,"7");
        keyValues.put(R.id.button_8,"8");
        keyValues.put(R.id.button_9,"9");
        keyValues.put(R.id.button_0,"0");
        keyValues.put(R.id.button_dot,".");
        keyValues.put(R.id.button_div,"/");
        keyValues.put(R.id.button_multi,"*");
        keyValues.put(R.id.button_add,"+");
        keyValues.put(R.id.button_sub,"-");

    }


    @Override
    public void onClick(View v) {
        if (inputConnection == null)
            return;

        CharSequence selectedText = inputConnection.getSelectedText(0);

        if (v.getId() == R.id.button_backspace) {
            if (TextUtils.isEmpty(selectedText)) {
                inputConnection.deleteSurroundingText(1, 0);
            } else {
                inputConnection.commitText("", 1);
            }

        } else if (v.getId() == R.id.button_clear) {
            CharSequence currentText = inputConnection.getExtractedText(new ExtractedTextRequest(), 0).text;
            CharSequence beforCursorText = inputConnection.getTextBeforeCursor(currentText.length(), 0);
            CharSequence afterCursorText = inputConnection.getTextAfterCursor(currentText.length(), 0);
            inputConnection.deleteSurroundingText(beforCursorText.length(), afterCursorText.length());

        }
        else if (v.getId() == R.id.button_enter) {

            // NOTE FOR MYSELF: Cant get the editText from another java
//            EditText usernameEditText = (EditText) findViewById(R.id.editText);
//            String sUsername = usernameEditText.getText().toString();

            CharSequence currentText = inputConnection.getExtractedText(new ExtractedTextRequest(), 0).text; // Calc!!!
            EditText memoText = (EditText) findViewById(R.id.memoText);
            String inputMemo = memoText.getText().toString();
            Double inputMoney = Double.valueOf(currentText.toString());

            if (currentText.length() == 0 || memoText.length() == 0) { // user must enter both fields.
                toastMassage("Please fill in the field(s)!");
                return;
            } else {
                // not empty, save the data

    // NEED TO BE MODIFIED AS SALARY / EXPENSE ,, SO THERE ARE 2 METHODS!! -------- !! ----!!-------!_1-!_-1-1_!-1-1_!_!_-1-__!
                AddExpenseData(inputMemo, inputMoney);
//                AddIncomeData(inputMemo, inputMoney);

                // clear after saving, user can continue entering
                CharSequence beforCursorText = inputConnection.getTextBeforeCursor(currentText.length(), 0);
                CharSequence afterCursorText = inputConnection.getTextAfterCursor(currentText.length(), 0);
                inputConnection.deleteSurroundingText(beforCursorText.length(), afterCursorText.length());
                memoText.setText("");
            }

        }
        else {
            String value = keyValues.get(v.getId());
            inputConnection.commitText(value, 1);
        }




    } // end onClick


    public void AddExpenseData(String inputMemo, Double inputMoney) {
        myDB = new DatabaseHelper(getContext());
        boolean insertData = myDB.addExpenseData(inputMemo, inputMoney);

        if (insertData == true) {
            toastMassage("Successfully saved expense!");
        } else {
            toastMassage("Something went wrong to save expense :(");
        }
    }
    public void AddIncomeData(String inputMemo, Double inputMoney) {
        myDB = new DatabaseHelper(getContext());
        boolean insertData = myDB.addIncomeData(inputMemo, inputMoney);

        if (insertData == true) {
            toastMassage("Successfully saved income!");
        } else {
            toastMassage("Something went wrong to save income :(");
        }
    }

    private void toastMassage(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    public void setInputConnection(InputConnection ic) {
        inputConnection = ic;
    }
}
