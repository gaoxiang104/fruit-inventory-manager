package pres.xgo.fim;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pres.xgo.fim.controller.MainViewController;

import java.io.IOException;
import java.net.URL;

/**
 * Hello world!
 *
 */
public class App extends Application {

    private ApplicationContext context;
    @Override
    public void start(Stage primaryStage) throws IOException {

        URL resource = getClass().getResource("/views/mainView.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(resource);
        BorderPane root = fxmlLoader.load();

        MainViewController controller = fxmlLoader.getController();
        controller.setContext(context);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("FIM");
        primaryStage.show();
    }

    @Override
    public void init() throws Exception {
        // 初始化 Spring 容器并加载配置文件
        context = new ClassPathXmlApplicationContext("applicationContext.xml");
    }

    public static void main(String[] args) {
        launch(args);
    }
}