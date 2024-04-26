package pres.xgo.fim.dto;

import lombok.Data;

/**
 * 商品搜索 Dto
 */
@Data
public class ProductSearchDto extends BasePageReqDto{
    private String category; // 商品分类
    private String productName; // 商品名称
    private String productCode; // 商品代码
}
