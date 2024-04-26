package pres.xgo.fim.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import pres.xgo.fim.dto.ProductSearchDto;
import pres.xgo.fim.po.ProductPo;

import java.util.List;

@Repository
public interface ProductMapper {

    Integer insertProduct(ProductPo po);

    Integer updateProduct(ProductPo po);
    List<ProductPo> queryAll();

    /**
     *查询商品是否存在
     * @param productCode
     * @return
     */
    Integer countByProductCode(@Param("productCode") String productCode);

    Integer queryCount(ProductSearchDto productSearchDto);

    List<ProductPo> queryBySerchDto(ProductSearchDto productSearchDto);
}
