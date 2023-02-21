package BeanAnnotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

public class BeanTag {
    public static class Samsung{
        public void config(){
            System.out.println("Octa Core, 4GB RAM, 12MP Camera");
            cpu.process();
        }

        @Autowired
        MobileProcessor cpu;

        public MobileProcessor getCpu() {
            return cpu;
        }

        public void setCpu(MobileProcessor cpu) {
            this.cpu = cpu;
        }
    }

    @Configuration
    public static class AppConfig{
        @Bean
        public Samsung getPhone(){
            return new Samsung();
        }

        @Bean
        public MobileProcessor getProcessor(){
            return new Snapdragon();
        }
    }

    public interface MobileProcessor{
        void process();
    }

    public static class Snapdragon implements MobileProcessor {
        public void process(){
            System.out.println("World's best CPU!");
        }
    }
    public static void main(String[] args) {
      ApplicationContext factory = new AnnotationConfigApplicationContext(AppConfig.class);

      Samsung s7 = factory.getBean(Samsung.class);
      s7.config();

    }
}
