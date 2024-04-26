package pres.xgo.fim.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pres.xgo.fim.dto.Pageable;
import pres.xgo.fim.dto.ProductSearchDto;
import pres.xgo.fim.error.FimException;
import pres.xgo.fim.po.ProductPo;
import pres.xgo.fim.service.ProductService;
import pres.xgo.fim.utils.AlertUtils;

import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * 果品信息
 */
@Controller
@Slf4j
public class FruitInfoController implements Initializable {
    @FXML
    public VBox fruitInfoVBox;
    @FXML
    public Button importBtn;
    @FXML
    public TableView productTable;

    // 表单
    @FXML
    public TextField productCodeTf; // 商品代码
    @FXML
    public TextField productNameTf; // 商品名称
    @FXML
    public TextField categoryTf; // 商品分类

    @FXML
    public Button searchBtn; // 搜索按钮
    public Button resetBtn; // 重置按钮

    // 分页
    @FXML
    public Label curPageLb;
    @FXML
    public Label pageCountLb;
    @FXML
    public Button prePageBtn; // 上一页
    public Button nextPageBtn; // 下一页


    private Integer currentPage = 1; // 当前页
    private Integer pageCount = 1; // 共计页

    @Autowired
    private ProductService productService;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initTable(); // 初始化表格
    }

    @FXML
    public void chooseFile(ActionEvent actionEvent) {

        importBtn.setDisable(true);

        try {
            Stage stage = (Stage) fruitInfoVBox.getScene().getWindow();
            FileChooser fileChooser = new FileChooser();

            // 设置支持的文件类型
            fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Excel files (*.xlsx, *.xls)", "*.xlsx", "*.xls"));

            fileChooser.setTitle("选择数据Excel文件");
            File selectedFile = fileChooser.showOpenDialog(stage);
            if (selectedFile != null) {
                System.out.println("Selected file: " + selectedFile.getAbsolutePath());
                Integer integer = productService.importProduct(selectedFile.getAbsolutePath());
                AlertUtils.showInfo("导入数据：" + integer + "条");
                resetTableData(); // 重置表格数据
            } else {
                System.out.println("close windows");
            }
        } catch (FimException e) {
            log.error("FruitInfoController exception : {}", e);
            AlertUtils.showError(e.getMessage());
        } catch (Exception e) {
            log.error("FruitInfoController error : {}", e);
            AlertUtils.showError("执行异常");
        } finally {
            importBtn.setDisable(false);
        }
    }

    // 初始化表格
    private void initTable() {
        initTableColumn(); // 初始化表格头

        fruitInfoVBox.widthProperty().addListener((obs, oldWidth, newWidth) -> {
            System.out.println("fruitInfoVBox宽度变化为：" + newWidth);
            // 在这里执行宽度变化后的逻辑
            BigDecimal newTableWith = new BigDecimal(newWidth.floatValue()).subtract(new BigDecimal(30));
            productTable.setPrefWidth(newTableWith.doubleValue());
        });

        fruitInfoVBox.heightProperty().addListener((obs, oldWidth, newWidth) -> {
            System.out.println("fruitInfoVBox高度变化为：" + newWidth);
            // 在这里执行宽度变化后的逻辑
            BigDecimal newTableHeight = new BigDecimal(newWidth.floatValue()).subtract(new BigDecimal(200));
            productTable.setPrefHeight(newTableHeight.doubleValue());
        });


        loadData(); // 加装数据
    }

    // 初始化表格头
    private void initTableColumn() {
        TableColumn<ProductPo, String> column1 = new TableColumn<>("商品分类");
        column1.setCellValueFactory(new PropertyValueFactory<>("category"));
        TableColumn<ProductPo, String> column2 = new TableColumn<>("商品名称");
        column2.setCellValueFactory(new PropertyValueFactory<>("productName"));
        TableColumn<ProductPo, String> column3 = new TableColumn<>("商品代码");
        column3.setCellValueFactory(new PropertyValueFactory<>("productCode"));
        TableColumn<ProductPo, String> column4 = new TableColumn<>("配送价");
        column4.setCellValueFactory(new PropertyValueFactory<>("deliveryPrice"));
        TableColumn<ProductPo, String> column5 = new TableColumn<>("最高限价");
        column5.setCellValueFactory(new PropertyValueFactory<>("maxRetailPrice"));
        TableColumn<ProductPo, String> column6 = new TableColumn<>("最高限价(斤)");
        column6.setCellValueFactory(new PropertyValueFactory<>("maxRetailPricePerKilo"));
        TableColumn<ProductPo, String> column7 = new TableColumn<>("会员差额");
        column7.setCellValueFactory(new PropertyValueFactory<>("memberPriceDifference"));
        TableColumn<ProductPo, String> column8 = new TableColumn<>("毛利率");
        column8.setCellValueFactory(new PropertyValueFactory<>("grossProfitMargin"));
        TableColumn<ProductPo, String> column9 = new TableColumn<>("品质状况描述");
        column9.setCellValueFactory(new PropertyValueFactory<>("qualityDescription"));
        TableColumn<ProductPo, String> column10 = new TableColumn<>("产地");
        column10.setCellValueFactory(new PropertyValueFactory<>("origin"));

        productTable.getColumns().addAll(column1, column2, column3, column4, column5, column6, column7, column8, column9, column10);
    }

    // 加载数据
    private void loadData() {
        // 配置搜索项
        ProductSearchDto productSearchDto = new ProductSearchDto();
        productSearchDto.setCurrentPage(currentPage);
        String productCode = productCodeTf.getText();
        if (StringUtils.isNoneEmpty(productCode)) {
            productSearchDto.setProductCode(productCode);
        }
        String productName = productNameTf.getText();
        if (StringUtils.isNoneEmpty(productName)) {
            productSearchDto.setProductName(productName);
        }
        String category = categoryTf.getText();
        if (StringUtils.isNoneEmpty(category)) {
            productSearchDto.setCategory(category);
        }

        Pageable<ProductPo> pageable = productService.queryBySerchDto(productSearchDto);

        this.pageCount = pageable.getCountPage();
        ObservableList<ProductPo> newItems = FXCollections.observableList(pageable.getData());
        productTable.setItems(newItems);

        initPagination(); // 设置分页

    }

    // 设置分页
    private void initPagination() {
        curPageLb.setText(String.valueOf(currentPage));
        pageCountLb.setText(String.valueOf(pageCount));
        if (1 == currentPage) {
            prePageBtn.setDisable(true);
        } else {
            prePageBtn.setDisable(false);
        }
        if (currentPage == pageCount) {
            nextPageBtn.setDisable(true);
        } else {
            nextPageBtn.setDisable(false);
        }
    }

    // 点击上一页
    @FXML
    public void handlePrePage(ActionEvent actionEvent) {
        prePageBtn.setDisable(true);
        try {
            this.currentPage--;
            loadData();
        } finally {
            prePageBtn.setDisable(false);
        }
    }

    // 点击下一页
    public void handleNextPageBtn(ActionEvent actionEvent) {
        nextPageBtn.setDisable(true);
        try {
            this.currentPage++;
            loadData();
        } finally {
            nextPageBtn.setDisable(false);
        }
    }

    // 点击搜索
    public void handleSearch(ActionEvent actionEvent) {
        searchBtn.setDisable(true);
        try {
            this.currentPage = 1;
            loadData();
        } finally {
            searchBtn.setDisable(false);
        }
    }

    // 点击重置
    public void handleReset(ActionEvent actionEvent) {
        searchBtn.setDisable(true);
        try {
            resetTableData();
        } finally {
            searchBtn.setDisable(false);
        }
    }

    // 重置搜索条件，并且刷新表格数据
    private void resetTableData(){
        productCodeTf.setText(null);
        productNameTf.setText(null);
        categoryTf.setText(null);
        this.currentPage = 1;
        loadData();
    }
}
