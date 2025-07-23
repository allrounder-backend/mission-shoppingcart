package mission.service;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.StandardLocation;
import javax.tools.ToolProvider;
import mission.domain.GroupedPricesByType;
import mission.dto.GeminiResponseDTO;
import mission.repository.LectureRepository;
import mission.repository.PromotionRepository;
import mission.utility.DebugLogging;
import mission.utility.JavaSourceFromString;
import mission.utility.ResourceReader;
import mission.validator.ResourceValidator;

public class RepositoryInitializingService {
    GeminiApiService geminiApiService;
    LectureRepository lectureRepository;
    PromotionRepository promotionRepository;

    public RepositoryInitializingService(
            GeminiApiService geminiApiService,
            LectureRepository lectureRepository,
            PromotionRepository promotionRepository
    ){
        this.geminiApiService = geminiApiService;
        this.lectureRepository = lectureRepository;
        this.promotionRepository = promotionRepository;
    }

    public void initializingLecture(){
        try{
            List<String> rawLectureList = ResourceReader.readFile("lectures.txt");

            rawLectureList.removeFirst();
            rawLectureList = rawLectureList.stream()
                    .flatMap(i -> Arrays.stream(i.split("\t")))
                    .collect(Collectors.toList());;

            ResourceValidator.isLectureValid(rawLectureList).forEach(item ->
                    lectureRepository.add(item.index(), item.name(), item.type(), item.price())
            );
        }catch (Exception e){
            DebugLogging.addTrace(Thread.currentThread().getStackTrace()[1].getMethodName()+"()");
            DebugLogging.addTrace(this.getClass().getName());
            throw e;
        }
    }

    public void initializingPromotion() throws Exception {
        try {
            File compiledDir = new File("compiled");
            if (!compiledDir.exists()) compiledDir.mkdirs();

            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            StandardJavaFileManager fileManager = compiler.getStandardFileManager(null,null,null);
            fileManager.setLocation(StandardLocation.CLASS_OUTPUT, List.of(compiledDir));

            List<String> rawPromotionList = ResourceReader.readFile("promotion.txt");
            rawPromotionList.removeFirst();
            rawPromotionList = rawPromotionList.stream()
                    .flatMap(i -> Arrays.stream(i.split("\t")))
                    .collect(Collectors.toList());

            List<JavaFileObject> sources = new ArrayList<>();
            List<String> functionClassNames = new ArrayList<>();
            List<String> typeNames = new ArrayList<>();

            for (int i = 0; i < rawPromotionList.size(); i+=2) {
                String typeName = rawPromotionList.get(i);
                String description = rawPromotionList.get(i+1);
                GeminiResponseDTO response = geminiApiService.getFunction(description);

                sources.add(new JavaSourceFromString(response.className(), response.function()));
                functionClassNames.add(response.className());
                typeNames.add(typeName);
            }

            compiler.getTask(null, fileManager, null, null, null, sources).call();
            fileManager.close();

            URLClassLoader classLoader = URLClassLoader.newInstance(new URL[] { compiledDir.toURI().toURL() });

            for(int j = 0; j < functionClassNames.size(); j++){
                String fullName = "mission.domain."+functionClassNames.get(j);
                Function<GroupedPricesByType, Integer> func = (Function<GroupedPricesByType, Integer>)
                        Class.forName(fullName, true, classLoader)
                                .getDeclaredConstructor()
                                .newInstance();
                promotionRepository.add(typeNames.get(j), func);
            }
        }catch (Exception e){
            DebugLogging.addTrace(Thread.currentThread().getStackTrace()[1].getMethodName()+"()");
            DebugLogging.addTrace(this.getClass().getName());
            throw e;
        }
    }
}
