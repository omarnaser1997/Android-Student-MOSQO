package com.naser.omar.studintmosq;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 *
 */
                                                           //Omar Naser\\
public class  DBConnections extends SQLiteOpenHelper {
    public static final int version=1;
    public static final String DbName="student.db";
    public DBConnections (Context context){
        super(context, DbName, null, version);
    }


    //this Function for create DataBase
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table IF NOT EXISTS student(" +
                "id INTEGER PRIMARY KEY   AUTOINCREMENT ," +
                "firstname varchar(250)," +
                "lastname varchar(250)," +
                "score INTEGER);"
                );
        db.execSQL("insert into student(firstname,lastname,score)values(' ',' ',0);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop table if EXISTS admin");
        onCreate(db);

    }

    //this function((getWritableDatabase)) importent for red from DataBase
    public  void InsertInTo(String firstname,String lastname,int score){//for Write in DB
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues contentValues =new ContentValues();
        contentValues.put("firstname",firstname);
        //contentValues.put("id",id);

        contentValues.put("lastname",lastname);
        contentValues.put("score",score);
        //@param (student)name of the tabel,which we need insert the data
        //@param (contentValues)data which insert in tabel student
        /*
        this Data put in ContentValues then put in Function (insert)
         */
        db.insert("student",null,contentValues);
    }//omar


/*this function brinds all the data that have (id)from tabel(string)
  where id and string is parameter pass for this function */
    public String getAllrecord(String id,String string){//for red from DB

        //ArrayList arrayList =new ArrayList();
        String oo=new String();
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor res =db.rawQuery("select * from student where id like "+id,null);
        //this function ((rawQuery))using for execution any steatment in sql lite

        res.moveToFirst();
        while (res.isAfterLast()== false){
            // arrayList.add(res.getString(res.getColumnIndex(string)));
            // arrayList.add(res.getString(res.getColumnIndex("mobile")));
          //  arrayList.add(res.getString(res.getColumnIndex(string)));
            oo+=res.getString(res.getColumnIndex(string));
            res.moveToNext();
        }
        return  oo;
    //return arrayList;
    }//omarnaser

public String NumStudint(){
    String oo=new String();
    SQLiteDatabase db=this.getReadableDatabase();
    Cursor res =db.rawQuery("select id from student",null);
    res.moveToFirst();
    while (res.isAfterLast()== false){
        //   arrayList.add(res.getString(res.getColumnIndex("id")));
        // arrayList.add(res.getString(res.getColumnIndex("mobile")));
        oo=res.getString(res.getColumnIndex("id"));
        res.moveToNext();

    }
    return  oo;
}
    /*void query(String Query){
        SQLiteDatabase db=this.getReadableDatabase();
        db.rawQuery(Query,null);

    }*/
    /*public String[] getAllrecordresev(){

        String names[] = new String[150];
        //String oo=new String();
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor res =db.rawQuery("select * from admin",null);
        res.moveToFirst();

       for(int i=0; (res.isAfterLast()== false);i++){
            //  arrayList.add(res.getString(res.getColumnIndex("user")));
            // arrayList.add(res.getString(res.getColumnIndex("mobile")));
            names[i]=res.getString(res.getColumnIndex("mobile"));
            res.moveToNext();
        }
        return  names;
    }*/

    public void updataR(int score,int id){
        SQLiteDatabase db=this.getWritableDatabase();
        db.execSQL("update student set score='"+Integer.toString(score)+"'where id="+Integer.toString(id));
        //db.execSQL("update student set score=1 where id=2");
    }

}
