package pres.xgo.fim.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pres.xgo.fim.service.ProductService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class ProductServiceImplTest {

    @Autowired
    ProductService service;


//    @Test
    public void allList() {
        service.allList();
    }
}