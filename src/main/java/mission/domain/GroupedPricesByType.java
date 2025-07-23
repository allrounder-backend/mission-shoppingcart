package mission.domain;

import java.util.ArrayList;
import java.util.List;

public class GroupedPricesByType {
    final Type type;
    List<Integer> priceList;

    public GroupedPricesByType(Type type, int price){
        this.type = type;
        priceList = new ArrayList<>(List.of(price));
    }

    public boolean isType(Type type){
        return this.type==type;
    }

    public void append(int price){
        if(priceList==null) {
            priceList = new ArrayList<>(List.of(price));
        } else{
            priceList.add(price);
        }
    }

    public List<Integer> getPriceList() {
        return priceList;
    }

    public Type getType() {
        return type;
    }

    public int getFirstPrice(){
        return priceList.getFirst();
    }

    public void emptyPriceList(){
        priceList = null;
    }

    public void resetPriceList(int price){
        emptyPriceList();
        append(price);
    }

    public boolean appendIfType(Type type, int price){
        if(this.isType(type)){
           this.append(price);
           return true;
        }
        return false;
    }
}
