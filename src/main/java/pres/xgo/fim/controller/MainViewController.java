package pres.xgo.fim.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import pres.xgo.fim.dto.MenuItemDto;
import pres.xgo.fim.service.ProductService;
import pres.xgo.fim.service.impl.ProductServiceImpl;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Slf4j
@Controller
public class MainViewController implements Initializable {


    @FXML
    public BorderPane mainView;
    @FXML
    public TreeView menuTree;

    // Spring 上下文
    private ApplicationContext context;

    // 存放 选中的 checkedMenuItem
    private MenuItemDto checkedMenuItem = null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // 初始化 菜单
        TreeItem<MenuItemDto> root = new TreeItem<>(new MenuItemDto("功能目录", "/views/defaultCenterView.fxml"));
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
     *
     * @param mouseEvent
     */
    public void handleTreeViewClick(MouseEvent mouseEvent) throws IOException {
        TreeItem<MenuItemDto> selectedItem =
                (TreeItem<MenuItemDto>) menuTree.getSelectionModel().getSelectedItem();

        if (null != selectedItem) {
            MenuItemDto dto = selectedItem.getValue();
            if(dto.equals(checkedMenuItem)){ // 重复点击
                log.debug("repeat click");
                return;
            }
            String fxmlUrlStr = dto.getFxmlUrlStr();
            mainView.setCenter((Node) load(fxmlUrlStr));
            checkedMenuItem = dto;
        }
    }

    public Object load(String sourceName) throws IOException {
        URL resource = getClass().getResource(sourceName);

        FXMLLoader loader = new FXMLLoader(resource);
        loader.setControllerFactory((param) -> context.getBean(param));
        return loader.load();
    }

    public void setContext(ApplicationContext context) {
        this.context = context;
    }
}
