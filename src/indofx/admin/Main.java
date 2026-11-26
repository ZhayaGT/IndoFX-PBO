package indofx.admin;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 * Entry Point Utama Aplikasi Desktop IndoFX Admin (NetBeans Java Ant).
 * Tekan F6 di NetBeans untuk menjalankan FrmDashboard.
 */
public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception ignored) {
        }

        SwingUtilities.invokeLater(() -> {
            FrmDashboard frame = new FrmDashboard();
            frame.setVisible(true);
        });
    }
}
