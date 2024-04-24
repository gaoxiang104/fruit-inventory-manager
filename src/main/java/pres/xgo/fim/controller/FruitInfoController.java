package pres.xgo.fim.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pres.xgo.fim.service.ProductService;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * 果品信息
 */
@Controller
public class FruitInfoController implements Initializable {
    @FXML
    public Button importBtn;
    public VBox fruitInfoVBox;
    @Autowired
    private ProductService productService;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        productService.allList();
    }

    @FXML
    public void chooseFile(ActionEvent actionEvent) {

        importBtn.setDisable(true);

        try {
            Stage stage = (Stage) fruitInfoVBox.getScene().getWindow();
            FileChooser fileChooser = new FileChooser();

            // 设置支持的文件类型
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Excel files (*.xlsx, *.xls)"
                            , "*.xlsx", "*.xls")
            );

            fileChooser.setTitle("选择数据Excel文件");
            File selectedFile = fileChooser.showOpenDialog(stage);
            if (selectedFile != null) {
                System.out.println("Selected file: " + selectedFile.getAbsolutePath());
                productService.importProduct(selectedFile.getAbsolutePath());
            } else {
                System.out.println("close windows");
            }
        } catch (Exception e) {
            importBtn.setDisable(false);
            throw new RuntimeException(e);
        } finally {
            importBtn.setDisable(false);
        }
    }
}
