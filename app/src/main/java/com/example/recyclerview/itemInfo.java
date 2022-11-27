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

    //SQL

    //SQl 객체
    itemInfo.mySQLiteHelper mySQLiteHelper = new mySQLiteHelper(this);
    SQLiteDatabase sqlDB;

    EditText ingredientNameEditTxt, ingredientAmountEditTxt, ingredientDateEditTxt, ingredientInfoEditTxt;
    Button itemAddBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_info);

        ingredientNameEditTxt = (EditText) findViewById(R.id.ingredientNameEditTxt);
        ingredientAmountEditTxt = (EditText) findViewById(R.id.ingredientAmountEditTxt);
        ingredientDateEditTxt = (EditText) findViewById(R.id.ingredientDateEditTxt);
        ingredientInfoEditTxt = (EditText) findViewById(R.id.ingredientInfoEditTxt);

        itemAddBtn = (Button) findViewById(R.id.itemAddBtn);

        // 유통기한 입력 시 하이픈 자동 입력
        ingredientDateEditTxt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (ingredientDateEditTxt.isFocusable() && !s.toString().equals("")) {
                    try {
                        Integer textlength = ingredientDateEditTxt.getText().toString().length();
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                        return;
                    }

                    if (ingredientDateEditTxt.getText().toString().length() == 4 && before != 1) {

                        ingredientDateEditTxt.setText(ingredientDateEditTxt.getText().toString() + "-");
                        ingredientDateEditTxt.setSelection(ingredientDateEditTxt.getText().length());

                    } else if (ingredientDateEditTxt.getText().toString().length() == 7 && before != 1) {

                        ingredientDateEditTxt.setText(ingredientDateEditTxt.getText().toString() + "-");
                        ingredientDateEditTxt.setSelection(ingredientDateEditTxt.getText().length());

                    } else if (ingredientDateEditTxt.getText().toString().length() == 5 && !ingredientDateEditTxt.getText().toString().contains("-")) {

                        ingredientDateEditTxt.setText(ingredientDateEditTxt.getText().toString().substring(0, 4) + "-" + ingredientDateEditTxt.getText().toString().substring(4));
                        ingredientDateEditTxt.setSelection(ingredientDateEditTxt.getText().length());

                    } else if (ingredientDateEditTxt.getText().toString().length() == 8 && !ingredientDateEditTxt.getText().toString().substring(7, 8).equals("-")) {

                        ingredientDateEditTxt.setText(ingredientDateEditTxt.getText().toString().substring(0, 7) + "-" + ingredientDateEditTxt.getText().toString().substring(7));
                        ingredientDateEditTxt.setSelection(ingredientDateEditTxt.getText().length());

                    }
                }


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        sqlDB = mySQLiteHelper.getWritableDatabase();
        mySQLiteHelper.onUpgrade(sqlDB, 1, 2);
        sqlDB.close();

        //추가버튼//
        itemAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //입력한 거 데이터베이스에 넣기
                sqlDB = mySQLiteHelper.getWritableDatabase();
                sqlDB.execSQL("INSERT INTO itemData VALUES ( '" + ingredientNameEditTxt.getText().toString() + "' , '" + ingredientAmountEditTxt.getText().toString() + "', '" + ingredientDateEditTxt.getText().toString() + "', '" + ingredientInfoEditTxt.getText().toString() + "');");
                sqlDB.close();

                //db 가져와 표시
                sqlDB = mySQLiteHelper.getReadableDatabase();
                Cursor cursor;
                cursor = sqlDB.rawQuery("SELECT * FROM itemData;", null);

                String itemName = "재료명" + "\r\n";
                String itemCount = "수량" + "\r\n";
                String itemDate = "유통기한" + "\r\n";
                String itemInfo = "재료정보" + "\r\n";


                while (cursor.moveToNext()) {
                    itemName += cursor.getString(0) + "\r\n";
                    itemCount += cursor.getString(1) + "\r\n";
                    itemDate += cursor.getString(2) + "\r\n";
                    itemInfo += cursor.getString(3) + "\r\n";

                }


                cursor.close();
                sqlDB.close();
            }
        });


        //
    }

    //SQL 객체 선언
    public class mySQLiteHelper extends SQLiteOpenHelper {
        public mySQLiteHelper(Context context) {
            super(context, "itemData", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE itemData ( itemName TEXT, itemCount TEXT, itemDate TEXT, itemInfo Text );");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS itemData");
            onCreate(db);

        }

    }

}