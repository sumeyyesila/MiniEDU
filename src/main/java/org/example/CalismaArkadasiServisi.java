package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CalismaArkadasiEkrani {

    public static double getKursOrtalamasi(int kursId, int ogrenciId) {
        String sql = "SELECT AVG(alinan_puan) FROM QuizSonuclari qs " +
                "JOIN DersIcerikleri di ON qs.icerik_id = di.id " +
                "WHERE di.kurs_id = ? AND qs.ogrenci_id = ?";
        try (Connection conn = Database.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, kursId);
            stmt.setInt(2, ogrenciId);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next() ? rs.getDouble(1) : 0.0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return 0.0;
        }
    }

    public static boolean zatenTalepVarMi(int kursId, int ogrenciId) {
        String sql = "SELECT id FROM CalismaTalepleri WHERE ogrenci_id=? AND kurs_id=? AND durum='BEKLIYOR'";
        try (Connection conn = Database.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, ogrenciId);
            stmt.setInt(2, kursId);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            return false;
        }
    }

    public static String getEslestirmeSorgusu(double puan) {
        if (puan >= 75) {
            return "SELECT ogrenci_id, id FROM CalismaTalepleri WHERE kurs_id = ? AND durum = 'BEKLIYOR' AND ortalama_puan < 50 AND ogrenci_id != ? LIMIT 1";
        } else if (puan < 50) {
            return "SELECT ogrenci_id, id FROM CalismaTalepleri WHERE kurs_id = ? AND durum = 'BEKLIYOR' AND ortalama_puan >= 75 AND ogrenci_id != ? LIMIT 1";
        } else {
            return "SELECT ogrenci_id, id FROM CalismaTalepleri WHERE kurs_id = ? AND durum = 'BEKLIYOR' AND (ortalama_puan BETWEEN 50 AND 75) AND ogrenci_id != ? LIMIT 1";
        }
    }

    public static boolean eslestir(Connection conn, int kursId, int ogrenciId, double puan) throws SQLException {
        String matchSql = getEslestirmeSorgusu(puan);
        try (PreparedStatement stmt = conn.prepareStatement(matchSql)) {
            stmt.setInt(1, kursId);
            stmt.setInt(2, ogrenciId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int partnerId = rs.getInt("ogrenci_id");
                    int talepId = rs.getInt("id");

                    PreparedStatement esStmt = conn.prepareStatement("INSERT INTO Eslesmeler (kurs_id, mentor_ogrenci_id, mentee_ogrenci_id, durum) VALUES (?, ?, ?, 'AKTIF')");
                    esStmt.setInt(1, kursId);
                    esStmt.setInt(2, (puan >= 50 ? ogrenciId : partnerId));
                    esStmt.setInt(3, (puan < 50 ? ogrenciId : partnerId));
                    esStmt.executeUpdate();

                    PreparedStatement upStmt = conn.prepareStatement("UPDATE CalismaTalepleri SET durum = 'ESLESTI' WHERE id = ?");
                    upStmt.setInt(1, talepId);
                    upStmt.executeUpdate();
                    return true;
                }
            }
        }
        return false;
    }

    public static void talepOlustur(Connection conn, int kursId, int ogrenciId, double puan) throws SQLException {
        String sql = "INSERT INTO CalismaTalepleri (ogrenci_id, kurs_id, ortalama_puan) VALUES (?, ?, ?)";
        try (PreparedStatement ins = conn.prepareStatement(sql)) {
            ins.setInt(1, ogrenciId);
            ins.setInt(2, kursId);
            ins.setDouble(3, puan);
            ins.executeUpdate();
        }
    }
}