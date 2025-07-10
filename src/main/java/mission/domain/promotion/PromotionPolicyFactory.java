package mission.domain.promotion;

import java.util.EnumMap;
import java.util.Map;
import mission.domain.lecture.LectureType;
import mission.domain.promotion.implement.CsPromotionPolicy;
import mission.domain.promotion.implement.DbmsPromotionPolicy;
import mission.domain.promotion.implement.DevOpsPromotionPolicy;
import mission.domain.promotion.implement.FwPromotionPolicy;
import mission.domain.promotion.implement.LangPromotionPolicy;

public class PromotionPolicyFactory {
    private static final Map<LectureType, PromotionPolicy> strategies = new EnumMap<>(LectureType.class);

    static {
        strategies.put(LectureType.DEVOPS, new DevOpsPromotionPolicy());
        strategies.put(LectureType.DBMS, new DbmsPromotionPolicy());
        strategies.put(LectureType.LANG, new LangPromotionPolicy());
        strategies.put(LectureType.FW, new FwPromotionPolicy());
        strategies.put(LectureType.CS, new CsPromotionPolicy());
    }

    public static PromotionPolicy getPolicy(LectureType type) {
        return strategies.get(type);
    }
}
