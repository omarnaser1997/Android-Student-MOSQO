package com.naser.omar.studintmosq;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddstudintActivity extends Activity {
    public  DBConnections db;
    EditText FirstName;
    EditText LastName;
    EditText Score;
  //  int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addstudint);
        FirstName=(EditText)findViewById(R.id.editText);
        LastName=(EditText)findViewById(R.id.editText2);
        Score=(EditText)findViewById(R.id.editText3);
        // Button Save=(Button)findViewById(R.id.save);
    }

    public void save(View view) {

       try {
           db = new DBConnections(this);
          // int x = Integer.parseInt(db.NumStudint());
           //x++;
           db.InsertInTo( FirstName.getText().toString(), LastName.getText().toString(), Integer.parseInt(Score.getText().toString()));
           Toast.makeText(this,"تم تسجيل الطالب بنجاح",Toast.LENGTH_LONG).show();
       }catch (Exception e){
           Toast.makeText(this,"الرجاء ادخال عدد النقاط بشكل صحيح",Toast.LENGTH_LONG).show();
       }
    }
}
