package pres.xgo.fim.po;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class ProductPo {

    private Long id;

    // 商品分类
    private String category;

    // 商品名称
    private String productName;

    // 商品代码
    private String productCode;

    // 配送价
    private BigDecimal deliveryPrice;

    // 最高限价
    private BigDecimal maxRetailPrice;

    // 最高限价(斤)
    private BigDecimal maxRetailPricePerKilo;

    // 会员差额
    private BigDecimal memberPriceDifference;

    // 毛利率
    private BigDecimal grossProfitMargin;

    // 品质状况描述
    private String qualityDescription;

    // 产地
    private String origin;
    // 创建时间
    private Date createTime;
    // 修改时间
    private Date updateTime;
}
