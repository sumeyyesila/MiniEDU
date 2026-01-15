package org.example;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.sql.*;

public class CalismaArkadasiEkrani {

    private final Kullanici ogrenci;
    private final Kurs kurs;

    public CalismaArkadasiEkrani(Kullanici ogrenci, Kurs kurs) {
        this.ogrenci = ogrenci;
        this.kurs = kurs;
    }

    public void display() {
        Stage stage = new Stage();
        stage.setTitle("Çalışma Arkadaşı Bul");

        VBox root = new VBox(20);
        root.setPadding(new Insets(30));
        root.setAlignment(Pos.CENTER);
        TemaHelper.arkaplanUygula(root, ogrenci.getTemaRenk());

        Label title = new Label("🤝 " + kurs.getBaslik());
        title.setStyle("-fx-font-size: 22px; -fx-font-weight: bold; -fx-text-fill: white;");

        Label info = new Label("Sistem sizi başarı puanınıza göre en uygun arkadaşla eşleştirecektir.");
        info.setWrapText(true);
        info.setAlignment(Pos.CENTER);
        info.setStyle("-fx-text-fill: white;");

        Button findBtn = new Button("Çalışma Arkadaşı Ara ✨");
        TemaHelper.butonStilUygula(findBtn, ogrenci.getTemaRenk());
        findBtn.setPrefWidth(250);

        findBtn.setOnAction(e -> handleProcess(stage));

        root.getChildren().addAll(title, info, findBtn);
        stage.setScene(new Scene(root, 450, 350));
        stage.show();
    }

    private void handleProcess(Stage stage) {
        double puan = CalismaArkadasiServisi.getKursOrtalamasi(kurs.getId(), ogrenci.getId());

        try (Connection conn = Database.connect()) {
            if (CalismaArkadasiServisi.eslestir(conn, kurs.getId(), ogrenci.getId(), puan)) {
                new Alert(Alert.AlertType.INFORMATION, "Harika! 🎉\nUygun arkadaş bulundu. Sohbetten ulaşabilirsiniz.").showAndWait();
            } else {
                CalismaArkadasiServisi.talepOlustur(conn, kurs.getId(), ogrenci.getId(), puan);
                new Alert(Alert.AlertType.INFORMATION, "Talebiniz alındı! ⏳\nUygun biri olduğunda haber vereceğiz.").showAndWait();
            }
            stage.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}