package pres.xgo.fim.utils;

import javafx.scene.control.Alert;

public class AlertUtils {
    private AlertUtils() {
    }

    public static void showError(String msg){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.getDialogPane().setMaxWidth(600);
        alert.setTitle("错误提示");
        alert.setHeaderText(msg);
        alert.show();
    }

    public static void showInfo(String msg){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.getDialogPane().setMaxWidth(600);
        alert.setTitle("提示");
        alert.setHeaderText(msg);
        alert.show();
    }

}
