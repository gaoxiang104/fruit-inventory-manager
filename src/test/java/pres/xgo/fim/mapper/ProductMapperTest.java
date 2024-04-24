package pres.xgo.fim.mapper;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pres.xgo.fim.po.ProductPo;

import java.math.BigDecimal;

import static org.junit.Assert.*;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class ProductMapperTest {

    @Autowired
    ProductMapper mapper;

    @Test
    public void insertProduct() {
        ProductPo po = new ProductPo();
        // 设置属性值
        po.setCategory("水果");
        po.setProductName("苹果");
        po.setProductCode("A001");
        po.setDeliveryPrice(new BigDecimal("5.99"));
        po.setMaxRetailPrice(new BigDecimal("8.99"));
        po.setMaxRetailPricePerKilo(new BigDecimal("3.99"));
        po.setMemberPriceDifference(new BigDecimal("1.00"));
        po.setGrossProfitMargin(new BigDecimal("0.30"));
        po.setQualityDescription("新鲜水果");
        po.setOrigin("中国");
        Integer integer = mapper.insertProduct(po);
        System.out.println("insertProduct integer ");
        log.info("insertProduct integer : {}", integer);
    }
}