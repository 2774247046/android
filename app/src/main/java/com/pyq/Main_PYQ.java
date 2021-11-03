package com.pyq;
import android.os.Bundle;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.lxs.R;
import com.pyq.MyAdapter.Grid_View_data;
import com.pyq.MyAdapter.ListViewAdapter;

import java.util.ArrayList;
import java.util.List;
public class Main_PYQ extends AppCompatActivity{
    private ListView pyq_list_view;
    private List<Grid_View_data>list=new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view_layout);
        ListData();
        pyq_list_view=findViewById(R.id.pyq_list_view);
        pyq_list_view.setAdapter(new ListViewAdapter(this,list));
    }
    private void ListData(){
            Grid_View_data grid_view_data = new Grid_View_data
                    ("https://tse1-mm.cn.bing.net/th/id/R-C.d60a56a3d4dff5053a28902b39af7d4e?rik=7zyKGrZR7CTYpw&riu=http%3a%2f%2fup.keaitupian.com%2fpic%2f0c%2fe6%2f77%2f0ce677fca329bf8dc3f76c0fd6db65ab.jpg&ehk=kLSP8auLy4X20cyP9mxCGH3%2fIMji8i3NhO0NX8Ap1fQ%3d&risl=&pid=ImgRaw&r=0");
            list.add(grid_view_data);
            Grid_View_data grid_view_data2 = new Grid_View_data
                    ("https://tse2-mm.cn.bing.net/th/id/OIP-C.mn85OM1Vs_Zyl-xn_M1fkgHaK2?w=206&h=302&c=8&rs=1&qlt=90&o=6&pid=3.1&rm=2&w=1024&h=1500");
            list.add(grid_view_data2);
            Grid_View_data grid_view_data3 = new Grid_View_data
                    ("https://tse1-mm.cn.bing.net/th/id/R-C.167fe9711c5dee047c86b7fc1f8c6f78?rik=NqOuw1u%2fS0VZOg&riu=http%3a%2f%2fimg.gexing.me%2fuploads%2fallimg%2f170405%2f1-1F321123425945.jpg&ehk=MCdR9%2bxYvh7rRSZvkQa%2f6J382OcPbtCu9FjmrrZH5c8%3d&risl=&pid=ImgRaw&r=0");
            list.add(grid_view_data3);
            Grid_View_data grid_view_data4 = new Grid_View_data
                ("https://pic2.zhimg.com/v2-ffdb112aa665dfdc6623b3bfc2933c5f_r.jpg");
            list.add(grid_view_data4);
            Grid_View_data grid_view_data5 = new Grid_View_data
                ("https://tse1-mm.cn.bing.net/th/id/R-C.21432784ec2e1dd98d73d5f6228110dd?rik=TWAx0wJahPD8bg&riu=http%3a%2f%2fup.keaitupian.com%2fuploads%2fimgs%2f2019%2f06%2fnmT_1560071387.jpg&ehk=91DLzhNt9Jm9KXpHTEUS8O6mlpsieA%2bo9FpSo9vh1%2bc%3d&risl=&pid=ImgRaw&r=0");
            list.add(grid_view_data5);
            Grid_View_data grid_view_data6 = new Grid_View_data
                ("https://uploadfile.bizhizu.cn/up/52/ca/99/52ca99e1d9524b0e834682f1302f0e12.jpg");
            list.add(grid_view_data6);
    }
}
