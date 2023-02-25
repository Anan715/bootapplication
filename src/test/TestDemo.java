import cn.hutool.core.util.IdcardUtil;
import com.alilang.stu.entity.Book;
import com.alilang.stu.util.IDGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Objects;


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


}
