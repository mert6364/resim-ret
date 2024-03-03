import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class resim {

    public static void main(String[] args) {
        // WebDriver'ı başlat
        WebDriver driver = new ChromeDriver();

        // Görüntüleri depolamak için bir liste oluştur
        List<String> imageHashes = new ArrayList<>();

        // 2500 görüntü oluştur
        for (int i = 0; i < 2500; i++) {
            // Görüntüyü bul
            WebElement image = driver.findElement(By.xpath("//xpath/to/your/image"));

            // Görüntüyü işle ve hash değerini hesapla
            String imageHash = getImageHash(image);

            // Oluşturulan hash değerini listeye ekle
            imageHashes.add(imageHash);
        }

        // WebDriver'ı kapat
        driver.quit();

        // Oluşturulan hash değerlerini karşılaştır
        if (imageHashes.stream().distinct().count() == 1) {
            System.out.println("Tüm görüntüler aynı, herhangi bir değişiklik yok.");
        } else {
            System.out.println("Görüntülerde değişiklikler var.");
        }
    }

    private static String getImageHash(WebElement image) {
        try {
            // Görüntüyü işle ve hash değerini hesapla
            byte[] imageBytes = image.getScreenshotAs(OutputType.BYTES);
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digest = md.digest(imageBytes);

            // Hash değerini hexadecimal formata dönüştür
            StringBuilder hashBuilder = new StringBuilder();
            for (byte b : digest) {
                hashBuilder.append(String.format("%02x", b));
            }
            return hashBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}