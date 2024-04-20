package pres.xgo.fim;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.net.URL;

/**
 * Hello world!
 *
 */
public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {

        // 初始化 Spring 容器并加载配置文件
        ApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext.xml");

        URL resource = getClass().getResource("/views/mainView.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(resource);
        BorderPane root = fxmlLoader.load();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("FIM");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}