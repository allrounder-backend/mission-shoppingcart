package mission.service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import mission.dto.GeminiResponseDTO;
import mission.message.ExceptionMessage;
import mission.utility.DebugLogging;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class GeminiApiService {

    //원래라면 env키로 분리해야하지만 제 코드 돌리셔야할테니 걍 포함시켜두겠슴다
    private static final String API_KEY = "AIzaSyBBJHQkglHto6CseMN9I2T7UiXo3JbUPas";
    private static final String ENDPOINT = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent";
    private static int COUNTER = 1;

    public String getContent(String text) throws Exception {
        HttpClient client = HttpClient.newHttpClient();

        String requestBody = String.format("""
            {
              "contents": [
                {
                  "parts": [
                    {
                      "text": "%s"
                    }
                  ]
                }
              ]
            }
            """,text);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(ENDPOINT + "?key=" + API_KEY))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if(response.statusCode()!=200) throw new IllegalStateException(response.body());

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response.body()); // String containing your whole JSON

        String rawText = root.path("candidates").get(0)
                .path("content")
                .path("parts").get(0)
                .path("text")
                .asText();

        return rawText
                .replaceAll("(?s)```java\\s*", "")  // Remove starting ```java
                .replaceAll("(?s)```\\s*$", "")     // Remove ending ```
                .trim();
    }

    public GeminiResponseDTO getFunction(String targetText) throws Exception{
        String text = """
                          다음은 계산의 대상이 될 입력 형식과 연관된 코드야.
                          
                                                    
                          public class GroupedPricesByType {
                          
                          private final Type type;
                          
                          private List<Integer> priceList;
                          
                          
                          
                          public GroupedPricesByType(Type type, int price){
                          
                          this.type = type;
                          
                          priceList = new ArrayList<>(List.of(price));
                          
                          }
                          
                          public boolean isType(Type type){
                          
                          return this.type==type;
                          
                          }
                          
                          
                          
                          public void append(int price){
                          
                          priceList.add(price);
                          
                          }
                          
                          
                          
                          public boolean appendIfType(Type type, int price){
                          
                          if(this.isType(type)){
                          
                          this.append(price);
                          
                          return true;
                          
                          }
                          
                          return false;
                          
                          }
                          
                          }
                          

                          이것을 참고해 주어진 GroupedPricesByType의 priceList에만 적용되는 '' 속의 텍스트를 최종 가격을 계산하는 Function<GroupedPricesByType, Integer>의 java 클래스로 바꾸고 설명 없이 코드만 텍스트로 돌려줘. 
                          package mission.domain; 하고, import mission.domain.GroupedPricesByType; 하는 것 잊지 마. 다른 것도 import 잘됐는지 확인해야해. 절대 type의 Type을 검사하지 마. 실질적으로 사용하는건 priceList만이니까. LectureDiscount"""+COUNTER+" 클래스명을 써.\n\n"+"'"+targetText+"'";
        String function = getContent(text);
        COUNTER++;

        Pattern pattern = Pattern.compile("public\\s+class\\s+(\\w+)");
        Matcher matcher = pattern.matcher(function);

        if (!matcher.find()) {
            throw new IllegalArgumentException(ExceptionMessage.GEMINI_FUNCTION_ERROR);
        }
        String className = matcher.group(1);

        return new GeminiResponseDTO(className, function);
    }
}

