package cost.com.costbook;

import android.app.Activity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.Chart;
import lecho.lib.hellocharts.view.LineChartView;


public class Char_view extends Activity{

    private LineChartView chart;
    private Map<String,Integer> table=new TreeMap<>();
    private LineChartData mData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.char_view);
        chart=findViewById(R.id.chart);
        List<CostBean> allDate= (List<CostBean>) getIntent().getSerializableExtra("costlist");
        generateValues(allDate);
        generateDate();

    }

    private void generateDate() {
        List<Line> lines=new ArrayList<>();
        List<PointValue> values=new ArrayList<>();
        int indexX=0;
        for (Integer value:table.values()) {
            values.add(new PointValue(indexX,value));
            indexX++;
        }
        Line line=new Line();
        line.setColor(ChartUtils.COLORS[0]);
        line.setShape(ValueShape.CIRCLE);
        line.setPointColor(ChartUtils.COLORS[1]);
        lines.add(line);
        mData=new LineChartData(lines);
    }
    private void generateValues(List<CostBean> allDate) {
        if (allDate!=null){
            for (int i = 0; i <allDate.size() ; i++) {
                CostBean costBean=allDate.get(i);
                String costdate=costBean.costDate;
                int costMoney=Integer.parseInt(costBean.CostMoney);
                if(!table.containsKey(costdate)){
                    table.put(costdate,costMoney);
                }else {
                    int origiMoney=table.get(costdate);
                    table.put(costdate,origiMoney+costMoney);
                }
             }
        }
    }

}
