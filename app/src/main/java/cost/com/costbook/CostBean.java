package cost.com.costbook;

import java.io.Serializable;

/**
 * Created by Scott on 2017/9/25.
 */
//Serializabl可以实现系列化
public class CostBean implements Serializable{
    public String costTitle;
    public String costDate;
    public String CostMoney;
}
