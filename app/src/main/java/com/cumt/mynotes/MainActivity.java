package com.cumt.mynotes;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * 这个类主要包括五个点击事件，分别为
 * 1，ListView的长按点击事件，用来AlertDialog来判断是否删除数据。
 * 2，ListView的点击事件，跳转到第二个界面，用来修改数据
 * 3，新建便签按钮的点击事件，跳转到第二界面，用来新建便签
 */
public class MainActivity extends AppCompatActivity {

    Button bt;
    ListView lv;
    LayoutInflater inflater;
    ArrayList<Store> array;
    MyDataBase mdb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv=(ListView) findViewById(R.id.listView1);
        bt=(Button) findViewById(R.id.newnote);
        inflater=getLayoutInflater();

        mdb=new MyDataBase(this);
        array=mdb.getArray();
        MyAdapter adapter=new MyAdapter(inflater,array);
        lv.setAdapter(adapter);
        /**
		 * 点击listView里面的item,进入到第二个页面，用来修改日记
		 */
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent intent=new Intent(getApplicationContext(),EditActivity.class);
                intent.putExtra("_id",array.get(position).getId() );
                startActivity(intent);
                MainActivity.this.finish();
            }
        });
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view,
                                           final int position, long id) {
                //AlertDialog是否删除note
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("删除")
                        .setMessage("是否删除笔记")
                        .setNegativeButton("取消",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).setPositiveButton("确定",new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mdb.toDelete(array.get(position).getId());
                        array=mdb.getArray();
                        MyAdapter adapter=new MyAdapter(inflater,array);
                        lv.setAdapter(adapter);
                    }
                }).create().show();
                return true;
            }
        });
        /**
		 * 按钮点击事件，用来新建日记
		 */
        bt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent=new Intent(MainActivity.this,EditActivity.class);
                startActivity(intent);
            }
        });


    }
}
