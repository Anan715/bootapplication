import cn.hutool.core.util.IdcardUtil;
import com.alilang.stu.util.IDGenerator;
import com.github.plexpt.chatgpt.Chatbot;
import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.service.OpenAiService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.Map;


@SpringBootTest(classes = {TestDemo.class})
public class TestDemo {

    @Test
    public void testMethod() {
        for (int i = 0; i < 100; i++) {
            IDGenerator card = new IDGenerator();
            String idCard = card.getIDCard();
            if (IdcardUtil.isValidCard(idCard)) {
                System.out.println("身份证号为：" + card.getIDCard());
            }
        }


    }

    @Test
    public void testMethod2(){
        Chatbot chatbot = new Chatbot("sessionToken", "cf_clearance", "user-agent");
        chatbot.setHost("http://proxyaddr:5000");

        Map<String, Object> chatResponse = chatbot.getChatResponse("hello");
        System.out.println(chatResponse.get("message"));
    }


    @Test
    public void testMethod3() throws IOException {
        String token = "";//System.getenv("OPENAI_TOKEN");

        OpenAiService service = new OpenAiService(token);
        CompletionRequest completionRequest = CompletionRequest.builder()
                .model("text-davinci-003")
                .prompt("今天天气怎么样？")
                .temperature(0.5)
                .maxTokens(2048)
                .topP(1D)
                .frequencyPenalty(0D)
                .presencePenalty(0D)
                .build();
        service.createCompletion(completionRequest).getChoices().forEach(System.out::println);

    }


}
