package cost.com.costbook;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //对象的集合
    private List<CostBean> listBean;
    //数据库对象
    private CostData costdata;
    //适配器
    private CostListAdaptet costListAdaptet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listBean=new ArrayList<CostBean>();
        //创建listview适配器
        costListAdaptet=new CostListAdaptet(this,listBean);
        //设置listview适配器
        ListView list= (ListView) findViewById(R.id.lv_main);
        list.setAdapter(costListAdaptet);
        costdata=new CostData(this);

        initData();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                //获取布局文件
                LayoutInflater inflater= LayoutInflater.from(MainActivity.this);
                View Dialog=inflater.inflate(R.layout.new_data_cost,null);
                final EditText title=Dialog.findViewById(R.id.cost_title);
                final EditText meney=Dialog.findViewById(R.id.cost_meney);
                final DatePicker date=Dialog.findViewById(R.id.datePicker);
                //将布局文件装入Dialog
                builder.setView(Dialog);
                //设置Dialog按钮
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        CostBean bean= new CostBean();
                        bean.costTitle=title.getText().toString().trim();
                        bean.CostMoney=meney.getText().toString().trim();
                        bean.costDate=date.getYear()+"-"+(date.getMonth()+1)+"-"+date.getDayOfMonth();
                        listBean.add(bean);
                        costdata.insertCost(bean);
                        costListAdaptet.notifyDataSetChanged();


                    }
                });
                builder.setNegativeButton("Cancel",null);
                builder.create().show();

                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
            }
        });
    }

    private void initData() {
         /*   costdata.deleteCostDate();
        for (int i=0;i<6;i++) {
            CostBean costbean=new CostBean();
            costbean.costTitle="球"+i;
            costbean.costDate="11-1"+i;
            costbean.CostMoney=""+i;
            costdata.insertCost(costbean);
        }*/
        Cursor cursor=costdata.getAllCostDate();
        if(cursor!=null){
            while(cursor.moveToNext()){
                CostBean bean=new CostBean();
                bean.costTitle=cursor.getString(cursor.getColumnIndex("cost_title"));
                bean.costDate=cursor.getString(cursor.getColumnIndex("cost_date"));
                bean.CostMoney=cursor.getString(cursor.getColumnIndex("cost_money"));
                listBean.add(bean);
            }
            cursor.close();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_chart) {
            Intent intent=new Intent(MainActivity.this,Char_view.class);
            intent.putExtra("costlist", (Serializable) listBean);
            startActivity(intent);
            return true;
        }
        if(id==R.id.clean){
            costdata.deleteCostDate();
            System.exit(0);
        }

        return super.onOptionsItemSelected(item);
    }
}
