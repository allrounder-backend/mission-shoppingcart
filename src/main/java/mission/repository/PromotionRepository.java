package mission.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import mission.domain.GroupedPricesByType;
import mission.domain.Type;

public class PromotionRepository {
    Map<Type, Function<GroupedPricesByType, Integer>> repository = new HashMap<>();

    public void add(String typeName, Function<GroupedPricesByType, Integer> lambda){
        repository.put(Type.createType(typeName), lambda);
    }

    public Function<GroupedPricesByType, Integer> findByType(Type type){
        return repository.get(type);
    }
}
