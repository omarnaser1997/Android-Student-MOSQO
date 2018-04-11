package com.naser.omar.studintmosq;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity1 extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public  DBConnections db;
    GridView list;
    Button button1;
    Button button2;
    int position_=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent Addstudiint =new Intent(MainActivity1.this,AddstudintActivity.class);
                startActivity(Addstudiint);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        db=new DBConnections(this);
        list=(GridView)findViewById(R.id.GridView);
        list.setAdapter(new ListResource(this));
        button1=(Button)findViewById(R.id.button1);
        button2=(Button)findViewById(R.id.button2);

        //   imageView.setImageResource(R.drawable.k);

        // db.InsertInTo(2,"ahmad","naser",70);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_activity1, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }




    public void AddStudint(View view) {

        Intent Addstudiint =new Intent(this,AddstudintActivity.class);
        startActivity(Addstudiint);
        // Bundle b=new Bundle();
        //b.putString("FirstName",temp.FirstName);
        //myintent.putExtras(b);


        //   int x=Integer.parseInt(db.NumStudint());
        // Toast.makeText(this,""+x ,Toast.LENGTH_LONG).show();

        //db=new DBConnections(this);
        //db.InsertInTo(1,"omar","naser",60);
        //db.InsertInTo(2,"ahmad","naser",70);
    }


    public   class  ListResource extends BaseAdapter {
        ArrayList<products> mydata;
        Context context;
        //DBConnections db=new DBConnections(context);
        ListResource(Context context){
            this.context=context;
            mydata=new ArrayList<products>();
            //   db.InsertInTo(0,"","",0);
            int x=Integer.parseInt(db.NumStudint());//Number item in data base
            //int y=0;
            // y=x;
            // y--;
            //Toast.makeText(context,"عدد الطلاب : "+y, Toast.LENGTH_LONG).show();
            mydata.clear();
            for(int i=2;x>=i;i++)
            {
                mydata.add(new products(db.getAllrecord(Integer.toString(i),"firstname").toString(),db.getAllrecord(Integer.toString(i),"lastname").toString()
                        ,db.getAllrecord(Integer.toString(i),"score").toString(),R.drawable.ff,context));

            }

            //Toast.makeText(context,db.getAllrecord(Integer.toString(1),"firstname")+x,Toast.LENGTH_LONG).show();



        }

        @Override
        public int getCount() {
            return mydata.size();
        }

        @Override
        public Object getItem(int position) {
            return mydata.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater=getLayoutInflater();
            final View row=inflater.inflate(R.layout.list,parent,false);
            TextView title=(TextView)row.findViewById(R.id.txttitel);
            TextView detals=(TextView)row.findViewById(R.id.txtcost);
            ImageButton image=(ImageButton) row.findViewById(R.id.imageButton);
            final products temp=mydata.get(position);

            temp.position=position;

            image.setImageResource(R.drawable.ff);
            image.setOnClickListener(new View.OnClickListener(){


                @Override
                public void onClick(View v) {


                    //Toast.makeText(context," "+position+1,Toast.LENGTH_LONG).show();

                    Intent myintent =new Intent(context,Main2Activity.class);
                    Bundle b=new Bundle();
                    b.putString("FirstName",temp.FirstName);
                    b.putString("LastName",temp.LastName);
                    b.putString("score",temp.score);
                    b.putInt("Image",temp.img);
                    b.putInt("position",temp.position);
                    myintent.putExtras(b);
                    startActivity(myintent);
                }
            });




            title.setText(temp.LastName+" "+temp.FirstName);
            detals.setText(temp.score);
            //   image.setImageResource(temp.img);


            return row;
        }
    }
}
