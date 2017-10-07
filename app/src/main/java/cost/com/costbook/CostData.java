package cost.com.costbook;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Sco tt on 2017/9/25.
 */

public class CostData extends SQLiteOpenHelper {

    public static final String COST_TITLE = "cost_title";
    public static final String COST_DATE = "cost_date";
    public static final String COST_MONEY = "cost_money";
    public static final String COST = "cost";

    public CostData(Context context) {
        super(context, "Costdata", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("create table if not exists cost(" +
                "id integer primary key," +
                "cost_title varchar," +
                "cost_date varchar," +
                "cost_money varchar)");
    }

    public void insertCost(CostBean costBean){
        SQLiteDatabase database=getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(COST_TITLE,costBean.costTitle);
        cv.put(COST_DATE,costBean.costDate);
        cv.put(COST_MONEY,costBean.CostMoney);
        database.insert(COST,null,cv);
    }
    public Cursor getAllCostDate(){
        SQLiteDatabase database=getWritableDatabase();
        return  database.query(COST,null,null,null,null,null,COST_DATE+" " + "ASC");
    }

    public void deleteCostDate(){
        SQLiteDatabase database=getWritableDatabase();
        database.delete(COST,null,null);
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
