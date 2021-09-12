package com.example.lxs.ContentResolver;

import android.Manifest;
import android.content.ContentValues;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.lxs.R;

import java.util.ArrayList;
import java.util.List;

public class ContentResolVer_1 extends AppCompatActivity {
    ArrayAdapter<String>adapter;
    List<String>contentList=new ArrayList<String>();
    TextView view2;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lxs_conten);
        ListView listView=findViewById(R.id.lxs_content_listView);
        Uri uri=Uri.parse("content://com.example.lxs/table1");
        adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,contentList);
        listView.setAdapter(adapter);
        if (ContextCompat.checkSelfPermission(ContentResolVer_1.this,Manifest.permission.READ_CONTACTS)!=PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(ContentResolVer_1.this,new String[]{Manifest.permission.READ_CONTACTS},1);
        }else{
            readContent();
        }
        view2=findViewById(R.id.lxs_Text);
        for (int i=0;i<contentList.size();i++) {
            view2.setText(contentList.get(i));
            Log.d("6",contentList.get(i));
        }
    }
    public void  readContent() {
     Cursor cursor=null;
     try {
         cursor=getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,null,null,null);
         if (cursor!=null){
             if (cursor.moveToFirst()){
                 String uu=cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                 String oo=cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                 contentList.add(uu+"\n"+oo);
             }
             adapter.notifyDataSetChanged();
         }
     }catch (Exception e){
         e.printStackTrace();
     }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 1:
                if (grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    readContent();
                }else{
                    Toast.makeText(ContentResolVer_1.this,"成功",Toast.LENGTH_SHORT).show();
                }
                break;
            default:
        }
    }
}
