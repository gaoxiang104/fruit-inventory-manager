package pres.xgo.fim;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pres.xgo.fim.service.ProductService;

public class Demo {
    public static void main(String[] args) {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext.xml");
        ProductService bean = context.getBean(ProductService.class);
        bean.allList();
    }
}
