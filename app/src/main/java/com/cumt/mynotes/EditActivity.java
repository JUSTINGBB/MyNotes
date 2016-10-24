package com.cumt.mynotes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *用来编辑日记
 *主要包括一个方法，isSave()用来保存数据;
 */
public class EditActivity extends AppCompatActivity {

    EditText ed1,ed2;
    Button bt1;
    MyDataBase myDatabase;
    Store store;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        ed1=(EditText) findViewById(R.id.editText1);
        ed2=(EditText) findViewById(R.id.editText2);
        bt1=(Button) findViewById(R.id.button1);
        myDatabase=new MyDataBase(this);

        Intent intent=this.getIntent();
        id=intent.getIntExtra("_id", 0);
        //默认为0，不为0,则为修改数据时跳转过来的
        if(id!=0){
            store=myDatabase.getTiandCon(id);
            ed1.setText(store.getTitle());
            ed2.setText(store.getContent());
        }
        //保存按钮的点击事件，他和返回按钮是一样的功能，所以都调用isSave()方法；
        bt1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                isSave();
            }
        });
    }
   /**
     * 返回按钮调用的方法。
     * @see android.app.Activity#onBackPressed()
     * */

    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        //super.onBackPressed();
        SimpleDateFormat   formatter   =   new SimpleDateFormat("yyyy.MM.dd  HH:mm:ss");
        Date   curDate   =   new Date(System.currentTimeMillis());//获取当前时间
        String times   =   formatter.format(curDate);
        String title=ed1.getText().toString();
        String content=ed2.getText().toString();
        //是要修改数据
        if(id!=0){
            store=new Store(id,title, content, times);
            myDatabase.toUpdate(store);
            Intent intent=new Intent(EditActivity.this,MainActivity.class);
            startActivity(intent);
            EditActivity.this.finish();
        }
        //新建日记
        else{
            if(title.equals("")&&content.equals("")){
                Intent intent=new Intent(EditActivity.this,MainActivity.class);
                startActivity(intent);
                EditActivity.this.finish();
            }
            else{
                store=new Store(title,content,times);
                myDatabase.toInsert(store);
                Intent intent=new Intent(EditActivity.this,MainActivity.class);
                startActivity(intent);
                EditActivity.this.finish();
            }

        }
    }
    private void isSave(){
        SimpleDateFormat   formatter   =   new   SimpleDateFormat   ("yyyy.MM.dd  HH:mm:ss");
        Date   curDate   =   new   Date(System.currentTimeMillis());//获取当前时间
        String times   =   formatter.format(curDate);
        String title=ed1.getText().toString();
        String content=ed2.getText().toString();
        //是要修改数据
        if(id!=0){
            store=new Store(id,title, content, times);
            myDatabase.toUpdate(store);
            Intent intent=new Intent(EditActivity.this,MainActivity.class);
            startActivity(intent);
            EditActivity.this.finish();
        }
        //新建日记
        else{
            store=new Store(title,content,times);
            myDatabase.toInsert(store);
            Intent intent=new Intent(EditActivity.this,MainActivity.class);
            startActivity(intent);
            EditActivity.this.finish();
        }
    }

}
