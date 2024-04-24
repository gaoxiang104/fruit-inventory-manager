package pres.xgo.fim.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import pres.xgo.fim.po.ProductPo;

import java.util.List;

@Repository
public interface ProductMapper {

    Integer insertProduct(ProductPo po);

    List<ProductPo> queryAll();
}
