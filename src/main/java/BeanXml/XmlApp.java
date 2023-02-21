package BeanXml;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class XmlApp {
    public static class DataSource {
        public String getData(){
            return "data";
        }
    }
    public static class Service {
//        private final DataSource dataSource;
        private DataSource dataSource;

//        @Autowired
//        public Service(DataSource dataSource) {
//            this.dataSource = dataSource;
//        }

        public void getInfo(){
            System.out.println(dataSource.getData());
        }

        public void setDataSource(DataSource dataSource){
            this.dataSource = dataSource;
        }
    }
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

        context.getBean("service", Service.class).getInfo();
    }
}
