package com.example.lxs;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;

import com.example.lxs.Intent.A1;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.l,menu);
        GG(menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected( MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_1:
                A1.actionStart(MainActivity.this, Button1.class,"1327117422","1327117422");
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    private void GG(Menu menu){
        SubMenu subMenu=menu.addSubMenu("666");
        MenuItem menuItem=subMenu.add("确实6");
        MenuItem menuItem2=subMenu.add("一般般6");
    }
}