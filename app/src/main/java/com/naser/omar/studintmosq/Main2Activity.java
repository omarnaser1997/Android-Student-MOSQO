package com.naser.omar.studintmosq;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Main2Activity extends Activity {
    DBConnections db;

    int position;
    TextView txt3;
    TextView txt1;
    TextView txt2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Bundle b=getIntent().getExtras();
        String FirstName=b.getString("FirstName");
        String LastName=b.getString("LastName");
        db=new DBConnections(this);

        int Score=b.getInt("score");
        int Image=b.getInt("Image");
        position=b.getInt("position");
        position +=2;


        txt1=(TextView) findViewById(R.id.txt1);
        txt2=(TextView) findViewById(R.id.txt2);
        txt3=(TextView) findViewById(R.id.txt3);
        ImageView image=(ImageView)findViewById(R.id.imageView);

        txt1.setText(db.getAllrecord(""+position,"firstname").toString());
        txt2.setText(db.getAllrecord(""+position,"lastname").toString());
        txt3.setText(db.getAllrecord(""+position,"score").toString());
        image.setImageResource(Image);

        //  db.InsertInTo(2,"omar","naser",25);
    }

    public void bbu(View view) {

        int x=Integer.parseInt( db.getAllrecord(""+position,"score").toString());
        x++;
       // position++;
        db.updataR(x,position);
        txt3.setText(db.getAllrecord(""+position,"score").toString());
    }

    public void buu(View view) {
        int x=Integer.parseInt( db.getAllrecord(""+position,"score").toString());
        x--;
        //position++;
        db.updataR(x,position);
        txt3.setText(db.getAllrecord(""+position,"score").toString());
    }
}