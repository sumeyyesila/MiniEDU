# 🎓 MiniEDU - Akıllı Eğitim ve Mentorluk Platformu
MiniEDU, geleneksel Öğrenim Yönetim Sistemlerini (LMS) **Akran Öğrenimi (Peer Learning)** modeliyle birleştiren masaüstü tabanlı bir eğitim platformudur. 
Bu proje, öğrencilerin başarısız olduğu konularda sistem tarafından otomatik olarak o konunun uzmanı olan diğer öğrencilerle eşleştirilmesini sağlar.

## 🚀 Projenin Amacı ve Çözdüğü Problem
Standart eğitim sistemlerinde öğrenciler bir konuda zorlandıklarında genellikle yalnız kalırlar. Eğitmenler ise kalabalık sınıflarda her öğrenciyle birebir ilgilenemeyebilir.

**MiniEDU Çözümü:**
* Sistem, **Quiz Puanlama Algoritması** ile öğrencinin durumunu analiz eder.
* Bir konudan **50 puan altı** alan öğrenci tespit edilir.
* Aynı sınavdan **80 üzeri** almış başarılı bir öğrenci (Mentor) veritabanından bulunur.
* Sistem bu ikiliyi otomatik eşleştirir ve **Özel Sohbet Odası** açar.

## ✨ Temel Özellikler

### 👨‍🏫 Eğitmen Modülü
* **Kurs Yönetimi:** Yeni kurs oluşturma, içerik (PDF/Metin) ekleme.
* **Sınav (Quiz) Sistemi:** Her derse özel çoktan seçmeli sınav hazırlama.
* **Sınıf Analizi:** Hangi konuda sınıfın zorlandığını gösteren veri analitiği ve grafikler.
* **Otomatik Uyarı:** Sınıf ortalaması düşük olan dersler için eğitmeni uyaran sistem.

### 🎓 Öğrenci Modülü
* **Ders Takibi:** Kayıtlı olunan dersleri görüntüleme ve ilerleme takibi.
* **Akıllı Sınav:** Barajı geçmeden sonraki içeriğe erişimin kısıtlanması.
* **Gamification (Oyunlaştırma):** Başarılara göre (Örn: "Quiz Ustası", "İlk Adım") otomatik **Rozet** kazanımı.
* **Sosyal Etkileşim:** Sınıf genel sohbet grubu ve Mentor-Mentee özel mesajlaşma.

## 🛠️ Kullanılan Teknolojiler

* **Dil:** Java (JDK 21)
* **Arayüz:** JavaFX & CSS
* **Veritabanı:** MySQL
* **Veri Erişimi:** JDBC (PreparedStatement ile güvenli yapı)
* **Mimari:** OOP Prensiplerine uygun modüler yapı

## 🗄️ Veritabanı Yapısı (ER Diyagramı)

Proje veritabanı **3. Normal Form (3NF)** kurallarına uygun tasarlanmıştır.

* **Kullanıcılar & Roller:** Eğitmen ve Öğrenci ayrımı.
* **Kurs & İçerik:** 1:N ilişkisi ile hiyerarşik yapı.
* **Eşleşmeler:** Mentor ve Mentee arasındaki ilişkiyi yöneten tablo.

## 👤 Geliştirici

**Sümeyye Sıla Tur**
* [LinkedIn Profilim](https://www.linkedin.com/public-profile/settings?lipi=urn%3Ali%3Apage%3Ad_flagship3_profile_self_edit_contact-info%3Bu0jLDBy2RFudz7cEYF8Y9Q%3D%3D)
* [GitHub Profilim](https://github.com/sumeyyesila)

---
*Bu proje Veritabanı Yönetim Sistemleri ve Java Programlama dersi final ödevi olarak geliştirilmiştir.*
