package com.naser.omar.studintmosq;

import android.content.Context;

/**
 * Created by omar on 3/11/2017.
 */

public class products {
    public  DBConnections db;
    String FirstName;
    String LastName;
    String score ;
    int img;
    int position;
    int id;

 products( String LastName, String FirstName, String score, int img, Context context){
     this.FirstName=FirstName ;
     this.LastName=LastName;
     this.score =score;
     this.img=img;
     //this.id=id;
     db=new DBConnections(context);
    // db.InsertInTo(id,LastName,FirstName,Integer.parseInt(score));
    // db.InsertInTo(1,"omar","naser",40);
 }
}
