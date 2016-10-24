package com.cumt.mynotes;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import java.util.ArrayList;

/**
 * 专门用来数据库操作的类，把所有的数据库操作都放到了这里来，
 * 其他直接调用就好了。数据的增，删，改，查，都在这里实现
 */

public class MyDataBase {
    Context context;
    MyOpenHelper myOpenHelper;
    SQLiteDatabase myDatabase;
    /**
     * 别的类实例化这个类的同时，创建数据库
     */
    public MyDataBase(Context context){
        this.context=context;
        myOpenHelper=new MyOpenHelper(context);
    }
    /**
     * 得到填充ListView用的array数据，从数据库里查找后解析。第一个界面调用
     */
    public ArrayList<Store> getArray(){
        ArrayList<Store> array=new ArrayList<Store>();
        ArrayList<Store> array1=new ArrayList<Store>();
        myDatabase=myOpenHelper.getWritableDatabase();
        Cursor cursor= myDatabase.rawQuery("select _id,title,time from notes",null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            int id=cursor.getInt(cursor.getColumnIndex("_id"));
            String title=cursor.getString(cursor.getColumnIndex("title"));
            String time=cursor.getString(cursor.getColumnIndex("time"));
            Store store=new Store(id,title,time);
            array.add(store);
            cursor.moveToNext();
        }
        myDatabase.close();;
        for(int i=array.size();i>0;i--){
            array1.add(array.get(i-1));
        }
        return array1;
    }
    /**
     * 返回时可能要修改数据，第二个界面用
     */
    public Store getTiandCon(int id){
        myDatabase=myOpenHelper.getWritableDatabase();
        Cursor cursor=myDatabase.rawQuery("select title,content from notes where _id='"+
                id+"'" , null);
        cursor.moveToFirst();
        String title=cursor.getString(cursor.getColumnIndex("title"));
        String content=cursor.getString(cursor.getColumnIndex("content"));
        Store store=new Store(title,content);
        myDatabase.close();
        return store;
    }
    /**
     * 第二个界面调用，用来修改日记
     */
    public void toUpdate(Store store){
        myDatabase=myOpenHelper.getWritableDatabase();
        myDatabase.execSQL("update notes set title='"+ store.getTitle()+
                "',time='"+store.getTime()+"',content='"+store.getContent() +
                "' where _id='"+ store.getId()+"'");
        myDatabase.close();
    }
    /**
     *第二个界面调用，用来增加新的日记
     */
    public void toInsert(Store store){
        myDatabase=myOpenHelper.getWritableDatabase();
        myDatabase.execSQL("insert into notes(title,content,time)values('"+
                store.getTitle()+"','"+store.getContent()+"','"+store.getTime()+"')");
        myDatabase.close();
    }
    /**
     * 第一个界面调用，长按点击后选择删除日记
     */
    public void toDelete(int id){
        myDatabase=myOpenHelper.getWritableDatabase();
        myDatabase.execSQL("delete  from notes where _id="+id+"");
        myDatabase.close();
    }
}

