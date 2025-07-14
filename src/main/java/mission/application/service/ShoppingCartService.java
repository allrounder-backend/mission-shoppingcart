package mission.application.service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import mission.Config;
import mission.application.domain.Lecture;
import mission.application.domain.enums.LectureType;
import mission.application.domain.enums.MessageConstant;
import mission.application.port.inport.Input;
import mission.application.port.inport.LectureDatabase;
import mission.application.port.outport.Logger;

public class ShoppingCartService {
    private static final ShoppingCartService instance = new ShoppingCartService();

    private final Logger logger;
    private final Input input;
    private final LectureDatabase lectureDatabase;

    private ShoppingCartService() {
        Config config = new Config();
        logger = config.getLogger();
        input = config.getInput();
        lectureDatabase = config.getLectureDatabase();
    }

    public static ShoppingCartService getInstance() {
        return instance;
    }

    public void run() {
        int budget = getTotalBudget();
        Map<LectureType, Integer> categorizedBudget = getCategorizedBudget();
        List<Lecture> lectures = getPurchaseLectures();

        Map<LectureType, Integer> costs = getCategorisedCost(lectures);
        int totalCost = costs.values().stream().mapToInt(Integer::intValue).sum();

        printCost(budget, categorizedBudget, totalCost, costs);
    }

    private int getTotalBudget() {
        logger.print(MessageConstant.INPUT_TOTAL_BUDGET_GUIDE);
        int budget = input.getInt();
        if (budget < 0) {
            throw new IllegalArgumentException("Invalid budget");
        }
        return budget;
    }

    private Map<LectureType, Integer> getCategorizedBudget() {
        logger.print(MessageConstant.INPUT_EACH_BUDGET_GUIDE);
        String categorizedBudgetLine = input.getString();
        return Stream.of(categorizedBudgetLine.split(","))
                .map(String::trim)
                .collect(Collectors.toMap(
                        item -> LectureType.getLectureType(item.split("-")[0]),
                        item -> Integer.parseInt(item.split("-")[1])
                ));
    }

    private List<Lecture> getPurchaseLectures() {
        logger.print(MessageConstant.INPUT_LECTURE_ID_GUIDE);
        String lecturesIdLine = input.getString();
        return Arrays.stream(lecturesIdLine.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .map(lectureDatabase::findLecture)
                .toList();
    }

    private Map<LectureType, Integer> getCategorisedCost(List<Lecture> lectures) {
        return lectures.stream()
                .collect(Collectors.groupingBy(Lecture::getType))
                .entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getKey().getLecture().calculateCost(entry.getValue())
                ));
    }

    private void printCost(int totalBudget,Map<LectureType, Integer> budget,
                           int totalCost, Map<LectureType, Integer> cost) {
        if (withinBudget(totalBudget, budget, totalCost, cost)) {
            logger.print(MessageConstant.OUTPUT_BUDGET_FIT_MESSAGE);
            return;
        }
        logger.print(MessageConstant.OUTPUT_BUDGET_EXCESS_MESSAGE);

        printEachCost("총 예산", totalBudget, totalCost);
        budget.forEach((key, value) -> printEachCost(key.getName(), value, cost.getOrDefault(key, 0)));
    }

    private boolean withinBudget(int totalBudget,Map<LectureType, Integer> budget,
                                   int totalCost, Map<LectureType, Integer> cost) {
        return totalBudget >= totalCost &&
                cost.entrySet().stream()
                        .noneMatch(entity ->
                                entity.getValue() > budget.getOrDefault(entity.getKey(), 0)
                        );
    }

    private void printEachCost(String type, int budget, int cost) {
        if (budget >= cost) {
            logger.print(MessageConstant.OUTPUT_EACH_BUDGET_FIT_MESSAGE, type);
            return;
        }
        logger.print(MessageConstant. OUTPUT_EACH_BUDGET_EXCESS_MESSAGE, type, cost - budget);
    }
}