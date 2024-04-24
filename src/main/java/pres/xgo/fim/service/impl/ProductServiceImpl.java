package pres.xgo.fim.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pres.xgo.fim.error.FimException;
import pres.xgo.fim.mapper.ProductMapper;
import pres.xgo.fim.po.ProductPo;
import pres.xgo.fim.service.ProductService;
import pres.xgo.fim.utils.ExcelUtils;
import pres.xgo.fim.utils.RegexUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper mapper;

    @Override
    public Integer importProduct(String filePath) {

        if (StringUtils.isEmpty(filePath)) {
            log.error("文件路径是空的");
            throw new FimException("文件路径是空的");
        }

        List<ProductPo> productPos = parseExcel(filePath);

        return productPos.size();
    }

    private List<ProductPo> parseExcel(String filePath) {
        List<ProductPo> result = null;
        try {
            FileInputStream fis = new FileInputStream(new File(filePath));
            // 通过文件流创建工作簿对象
            Workbook workbook = WorkbookFactory.create(fis);

            Sheet sheet = workbook.getSheetAt(0);

            result = new ArrayList<>(sheet.getLastRowNum() + 1);

            // 获取总行数
            int rowCount = sheet.getPhysicalNumberOfRows();
            // 从第三行开始遍历
            for (int i = 2; i < rowCount; i++) {

                Row row = sheet.getRow(i);

                String category = ExcelUtils.getCellValue(row.getCell(2)); // 商品分类
                String productName = ExcelUtils.getCellValue(row.getCell(3)); // 商品名称
                String productCode = ExcelUtils.getCellValue(row.getCell(4)); // 商品代码
                if(StringUtils.isEmpty(productCode)){
                    continue;
                }
                String deliveryPriceStr = ExcelUtils.getCellValue(row.getCell(5)); // 配送价
                BigDecimal deliveryPrice = RegexUtils.extractFirstNumberBd(deliveryPriceStr);
                String maxRetailPriceStr = ExcelUtils.getCellValue(row.getCell(7)); // 最高限价
                BigDecimal maxRetailPrice = RegexUtils.extractFirstNumberBd(maxRetailPriceStr);
                String maxRetailPricePerKiloStr = ExcelUtils.getCellValue(row.getCell(8)); // 最高限价(斤)
                BigDecimal maxRetailPricePerKilo = RegexUtils.extractFirstNumberBd(maxRetailPricePerKiloStr);
                String memberPriceDifferenceStr = ExcelUtils.getCellValue(row.getCell(9)); // 会员差额
                BigDecimal memberPriceDifference = RegexUtils.extractFirstNumberBd(memberPriceDifferenceStr);
                String grossProfitMarginStr = ExcelUtils.getCellValue(row.getCell(10)); // 毛利率
                BigDecimal grossProfitMargin = RegexUtils.extractFirstNumberBd(grossProfitMarginStr);
                String qualityDescription = ExcelUtils.getCellValue(row.getCell(18)); // 品质状况描述
                String origin = ExcelUtils.getCellValue(row.getCell(23)); // 产地


                log.info("row :[ {},{},{},{},{},{},{},{},{},{} ]", category, productName, productCode, deliveryPrice, maxRetailPrice, maxRetailPricePerKilo, memberPriceDifference, grossProfitMargin, qualityDescription, origin);

                ProductPo po = new ProductPo();
                po.setCategory(category);
                po.setProductName(productName);
                po.setProductCode(productCode);
                po.setDeliveryPrice(deliveryPrice);
                po.setMaxRetailPrice(maxRetailPrice);
                po.setMaxRetailPricePerKilo(maxRetailPricePerKilo);
                po.setMemberPriceDifference(memberPriceDifference);
                po.setGrossProfitMargin(grossProfitMargin);
                po.setQualityDescription(qualityDescription);
                po.setOrigin(origin);

                result.add(po);
            }

            log.debug("result : {}", result);

            return result;
        } catch (IOException e) {
            log.error("parseExcel error : {}", e);
            throw new FimException("Excel 解析错误");
        }
    }

    @Override
    public void allList() {
        List<ProductPo> productPos = mapper.queryAll();
        log.info("productPos : {}", productPos);
    }
}
