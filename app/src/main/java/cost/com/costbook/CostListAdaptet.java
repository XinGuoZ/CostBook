package cost.com.costbook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Scott on 2017/9/25.
 */

public class CostListAdaptet extends BaseAdapter {
    private Context context;
    private List<CostBean> list;
    private LayoutInflater layoutInflater;

    public CostListAdaptet(Context context, List<CostBean> list) {
        this.context = context;
        this.list = list;
        this.layoutInflater = layoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHoder viewHoder;
        if(view==null){
            viewHoder=new ViewHoder();
            view=layoutInflater.inflate(R.layout.list_item,null);
            viewHoder.mCostTitle=view.findViewById(R.id.tv_title);
            viewHoder.mCostDate=view.findViewById(R.id.tv_date);
            viewHoder.mMoney=view.findViewById(R.id.tv_cost);
            view.setTag(viewHoder);
        }else{
            viewHoder= (ViewHoder) view.getTag();
        }
        CostBean bean=list.get(i);
        viewHoder.mCostTitle.setText(bean.costTitle);
        viewHoder.mCostDate.setText(bean.costDate);
        viewHoder.mMoney.setText(bean.CostMoney);
        return view;
    }
    private static class ViewHoder{
        public TextView mCostTitle;
        public TextView mCostDate;
        public TextView mMoney;
    }
}
