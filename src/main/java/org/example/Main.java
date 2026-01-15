package org.example;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("MiniEDU - Geleceğini İnşa Et");

        loadGlobalStyles();

        LoginScreen loginScreen = new LoginScreen();
        loginScreen.start(primaryStage);

        primaryStage.setWidth(1280);
        primaryStage.setHeight(720);
        primaryStage.show();
        primaryStage.setMaximized(true);
    }

    private void loadGlobalStyles() {
        try {
            String css = getClass().getResource("/style.css").toExternalForm();
            Application.setUserAgentStylesheet(css);
        } catch (Exception e) {
            System.err.println("Global stil dosyası yüklenemedi: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}