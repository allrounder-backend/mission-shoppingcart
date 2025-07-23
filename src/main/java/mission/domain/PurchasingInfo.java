package mission.domain;

import java.util.HashMap;
import java.util.Map;

public class PurchasingInfo {
    Map<Type, GroupedPricesByType> precalculationMap;

    public PurchasingInfo(){
        precalculationMap = new HashMap<>();
    }

    public void add(Type type, int price){
        GroupedPricesByType value = precalculationMap.get(type);
        if(value==null){
            precalculationMap.put(type, new GroupedPricesByType(type, price));
        }else{
            value.appendIfType(type, price);
        }
    }

    public GroupedPricesByType getGroupedPrices(Type type) {
        return precalculationMap.get(type);
    }
}
