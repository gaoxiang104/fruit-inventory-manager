module fruit.inventory.manager {
    requires javafx.controls;
    requires javafx.fxml;

    requires spring.core;
    requires spring.beans;
    requires spring.context;
    requires spring.aop;
    requires spring.jdbc;
    requires spring.tx;

    requires org.mybatis;
    requires org.mybatis.spring;
    requires java.sql;
    requires org.slf4j;

    requires org.apache.poi.poi;
    requires org.apache.poi.ooxml;
    requires org.apache.commons.lang3;

    requires static lombok;

    opens pres.xgo.fim;
    opens pres.xgo.fim.controller;
    opens pres.xgo.fim.service;
    opens pres.xgo.fim.service.impl;
    opens pres.xgo.fim.mapper;
    opens pres.xgo.fim.po;
    opens pres.xgo.fim.dto;

    exports pres.xgo.fim;
    exports pres.xgo.fim.controller;
    exports pres.xgo.fim.service;
    exports pres.xgo.fim.service.impl;
    exports pres.xgo.fim.mapper;
    exports pres.xgo.fim.po;
    exports pres.xgo.fim.dto;
}