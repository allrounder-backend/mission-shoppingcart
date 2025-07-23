package mission;

import mission.controller.ShoppingCartController;
import mission.repository.LectureRepository;
import mission.repository.PromotionRepository;
import mission.service.GeminiApiService;
import mission.service.RepositoryInitializingService;
import mission.service.ShoppingCartCalculationService;
import mission.view.InputView;
import mission.view.OutputView;

public class Config {
    public static boolean DEBUG_MODE = true;
    private LectureRepository lectureRepository;
    private PromotionRepository promotionRepository;

    public Config(){
        setAllRepository();
    }

    public void setAllRepository(){
        lectureRepository = new LectureRepository();
        promotionRepository = new PromotionRepository();
    }

    public ShoppingCartController shoppingCartController(){
        return new ShoppingCartController(
                inputView(),
                outView(),
                shoppingCartCalculationService(),
                repositoryInitializingService()
        );
    }

    public InputView inputView(){
        return new InputView();
    }

    public OutputView outView(){
        return new OutputView();
    }

    public ShoppingCartCalculationService shoppingCartCalculationService(){
        return new ShoppingCartCalculationService(lectureRepository, promotionRepository);
    }

    public GeminiApiService geminiApiService(){
        return new GeminiApiService();
    }

    public RepositoryInitializingService repositoryInitializingService(){
        return new RepositoryInitializingService(geminiApiService(), lectureRepository, promotionRepository);
    }




}
