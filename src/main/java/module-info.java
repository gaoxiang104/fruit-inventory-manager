module fruit.inventory.manager {
    requires javafx.controls;
    requires javafx.fxml;

    requires spring.core;
    requires spring.beans;
    requires spring.context;

    requires static lombok;

    opens pres.xgo.fim to javafx.fxml;
    opens pres.xgo.fim.controller to javafx.fxml;

    exports pres.xgo.fim;
    exports pres.xgo.fim.controller;
}