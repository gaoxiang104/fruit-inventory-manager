package pres.xgo.fim.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.springframework.stereotype.Controller;
import pres.xgo.fim.utils.AlertUtils;

/**
 * 库存信息
 */
@Controller
public class InventoryInfoController {
    @FXML
    public Button errorBtn;

    public void showErrorBtn(ActionEvent actionEvent) {
        AlertUtils.showError("点击了错误按钮");
    }
}
