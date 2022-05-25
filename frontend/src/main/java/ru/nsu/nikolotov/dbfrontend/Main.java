package ru.nsu.nikolotov.dbfrontend;

import kong.unirest.Unirest;
import ru.nsu.nikolotov.dbfrontend.app.App;

import javax.swing.*;
import java.net.URISyntaxException;

public class Main {

    public static void main(String[] args) {
        App app = new App();
        //Unirest.config().defaultBaseUrl("http://localhost:8080");
        app.start();
    }
}
