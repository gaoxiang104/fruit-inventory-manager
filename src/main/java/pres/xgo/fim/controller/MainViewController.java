package pres.xgo.fim.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import org.springframework.stereotype.Controller;
import pres.xgo.fim.dto.MenuItemDto;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class MainViewController implements Initializable {

    @FXML
    public BorderPane mainView;
    @FXML
    public TreeView menuTree;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // 初始化 菜单
        TreeItem<MenuItemDto> root = new TreeItem<>(new MenuItemDto("功能目录","/views/defaultCenterView.fxml"));
        // 默认展开
        root.setExpanded(true);
        root.getChildren().addAll(
                new TreeItem<>(new MenuItemDto("果品信息", "/views/fruitInfoView.fxml"))
                , new TreeItem<>(new MenuItemDto("果品库存", "/views/inventoryInfoView.fxml"))
        );
        menuTree.setRoot(root);

    }

    /**
     * 点击菜单按钮
     * @param mouseEvent
     */
    public void handleTreeViewClick(MouseEvent mouseEvent) throws IOException {
        TreeItem<MenuItemDto> selectedItem =
                (TreeItem<MenuItemDto>) menuTree.getSelectionModel().getSelectedItem();

        if (null != selectedItem) {
            MenuItemDto dto = selectedItem.getValue();
            String fxmlUrlStr = dto.getFxmlUrlStr();
            mainView.setCenter((Node) load(fxmlUrlStr));
        }
    }

    public Object load(String sourceName) throws IOException {
        URL resource = getClass().getResource(sourceName);
        Object load = FXMLLoader.load(resource);
        return load;
    }
}
