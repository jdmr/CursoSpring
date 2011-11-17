package springidol;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author jdmr
 */
public class SpringIdolMain {

    public static void main(String[] args) throws PerformanceException {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("springidol/spring-idol.xml");
        Performer performer = (Performer) ctx.getBean("duke");
        performer.perform();
        
        performer = (Performer) ctx.getBean("poeticDuke");
        performer.perform();
        
        Stage stage = (Stage) ctx.getBean("theStage");
        stage.performance(performer);
    }
}
