package com.example.recyclerview;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class itemInfo extends AppCompatActivity {

    //SQl 객체
    ItemData.mySQLiteHelper mySQLiteHelper;
    SQLiteDatabase sqlDB;

    EditText ingredientNameEditTxt, ingredientAmountEditTxt, ingredientDateEditTxt;
    Button itemAddBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_info);

        ingredientNameEditTxt = (EditText) findViewById(R.id.ingredientNameEditTxt);
        ingredientAmountEditTxt = (EditText) findViewById(R.id.ingredientAmountEditTxt);
        ingredientDateEditTxt = (EditText) findViewById(R.id.ingredientDateEditTxt);

        itemAddBtn = (Button) findViewById(R.id.itemAddBtn);

        // 유통기한 입력 시 하이픈 자동 입력
        ingredientDateEditTxt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(ingredientDateEditTxt.isFocusable() && !s.toString().equals("")) {
                    try{
                        Integer textlength = ingredientDateEditTxt.getText().toString().length();
                    }catch (NumberFormatException e){
                        e.printStackTrace();
                        return;
                    }

                    if (ingredientDateEditTxt.getText().toString().length() == 4 && before != 1) {

                        ingredientDateEditTxt.setText(ingredientDateEditTxt.getText().toString()+"-");
                        ingredientDateEditTxt.setSelection(ingredientDateEditTxt.getText().length());

                    }else if (ingredientDateEditTxt.getText().toString().length() == 7&& before != 1){

                        ingredientDateEditTxt.setText(ingredientDateEditTxt.getText().toString()+"-");
                        ingredientDateEditTxt.setSelection(ingredientDateEditTxt.getText().length());

                    }else if(ingredientDateEditTxt.getText().toString().length() == 5 && !ingredientDateEditTxt.getText().toString().contains("-")){

                        ingredientDateEditTxt.setText(ingredientDateEditTxt.getText().toString().substring(0,4)+"-"+ingredientDateEditTxt.getText().toString().substring(4));
                        ingredientDateEditTxt.setSelection(ingredientDateEditTxt.getText().length());

                    }else if(ingredientDateEditTxt.getText().toString().length() == 8 && !ingredientDateEditTxt.getText().toString().substring(7,8).equals("-")){

                        ingredientDateEditTxt.setText(ingredientDateEditTxt.getText().toString().substring(0,7)+"-"+ingredientDateEditTxt.getText().toString().substring(7));
                        ingredientDateEditTxt.setSelection(ingredientDateEditTxt.getText().length());

                    }
                }


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


}

