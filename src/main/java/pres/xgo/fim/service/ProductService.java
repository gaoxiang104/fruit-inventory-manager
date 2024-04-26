package pres.xgo.fim.service;

import pres.xgo.fim.dto.Pageable;
import pres.xgo.fim.dto.ProductSearchDto;
import pres.xgo.fim.po.ProductPo;

import java.util.List;

public interface ProductService {

    /**
     * 导入 果品
     * @param filePath
     * @return
     */
    Integer importProduct(String filePath);

    List<ProductPo> allList();

    Pageable<ProductPo> queryBySerchDto(ProductSearchDto productSearchDto);
}
