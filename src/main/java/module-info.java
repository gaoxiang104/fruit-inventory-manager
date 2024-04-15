module fruit.inventory.manager {
    requires javafx.controls;
    requires javafx.fxml;

    opens pres.xgo.fim to javafx.fxml;
    opens pres.xgo.fim.controller to javafx.fxml;

    exports pres.xgo.fim;
    exports pres.xgo.fim.controller;
}