package indofx.admin;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 * Entry Point Utama Aplikasi Desktop IndoFX Admin.
 * Jalankan file ini di VS Code (klik tombol "Run" di atas method main).
 */
public class Main {
    public static void main(String[] args) {
        // Gunakan Look and Feel Cross-Platform (Metal/Default) agar warna tombol konsisten di Windows/Mac/Linux
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception ignored) {
        }

        SwingUtilities.invokeLater(() -> {
            DashboardFrame frame = new DashboardFrame();
            frame.setVisible(true);
        });
    }
}
