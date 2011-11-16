package knights;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author jdmr
 */
public class KnightsMain {
    public static void main(String[] args) throws QuestException {
        ApplicationContext context = new ClassPathXmlApplicationContext("knights.xml");
        
        Knight knight = (Knight) context.getBean("knight");
        knight.embarkOnQuest();
    }
}
