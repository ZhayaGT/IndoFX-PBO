package indofx.admin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 * ============================================================================
 * CONTOH BASE JFRAME UNTUK TIM: DashboardFrame (Menu 1 - Ringkasan Dashboard)
 * ============================================================================
 * Ukuran Standar Window: 1024 x 680 px (Konsisten sesuai Wireframe FigCraft)
 * - Header Atas (NORTH) : 1024 x 56 px
 * - Sidebar Kiri (WEST) : 220 x 592 px
 * - Konten Utama (CENTER): 804 x 592 px
 * - Pop-up Dialog       : 420 x 250 px
 *
 * CARA COPY-PASTE UNTUK TEMAN TIM:
 * 1. Copy file ini di dalam folder `src/indofx/admin/`, lalu rename sesuai menu
 *    (contoh: `ModerasiAsetFrame.java`, `KategoriFrame.java`, dll).
 * 2. Ubah `ACTIVE_MENU_INDEX` di bawah (1 s/d 8) agar menu di sidebar menyala oranye.
 * 3. Ubah isi method `createContentPanel()` dan `showActionPopup()` sesuai wireframe.
 * ============================================================================
 */
public class DashboardFrame extends JFrame {

    // ========================================================================
    // 1. KONFIGURASI HALAMAN (UBAH ANGKA INI SAAT COPY-PASTE KE MENU LAIN: 1 - 8)
    // ========================================================================
    private static final int ACTIVE_MENU_INDEX = 1; // 1 = Ringkasan Dashboard

    // Standar Ukuran Window (Jangan diubah agar seluruh tim konsisten)
    public static final int WINDOW_WIDTH = 1024;
    public static final int WINDOW_HEIGHT = 680;

    // Palet Warna Standar IndoFX (Sesuai Wireframe FigCraft)
    public static final Color COLOR_PRIMARY_ORANGE = new Color(0xF9, 0x73, 0x16); // #F97316
    public static final Color COLOR_DARK_ORANGE    = new Color(0xEA, 0x58, 0x0C); // #EA580C
    public static final Color COLOR_BG_CREAM       = new Color(0xFF, 0xF7, 0xED); // #FFF7ED
    public static final Color COLOR_BG_LIGHT       = new Color(0xF8, 0xFA, 0xFC); // #F8FAFC
    public static final Color COLOR_TABLE_HEADER   = new Color(0xF1, 0xF5, 0xF9); // #F1F5F9
    public static final Color COLOR_WHITE          = Color.WHITE;
    public static final Color COLOR_BORDER         = new Color(0xCB, 0xD5, 0xE1); // #CBD5E1
    public static final Color COLOR_TEXT_DARK      = new Color(0x0F, 0x17, 0x2A); // #0F172A
    public static final Color COLOR_TEXT_BODY      = new Color(0x33, 0x41, 0x55); // #334155
    public static final Color COLOR_TEXT_MUTED     = new Color(0x64, 0x74, 0x8B); // #64748B
    public static final Color COLOR_GREEN          = new Color(0x16, 0xA3, 0x4A); // #16A34A
    public static final Color COLOR_RED            = new Color(0xDC, 0x26, 0x26); // #DC2626

    // Komponen Tabel untuk disimpan referensinya saat tombol Pop-up diklik
    private JTable tableAntrean;
    private DefaultTableModel tableModel;

    public DashboardFrame() {
        setTitle("IndoFX Admin Desktop — Ringkasan Dashboard (1024 x 680)");
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setMinimumSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Muncul tepat di tengah layar
        setLayout(new BorderLayout());

        // Susun 3 bagian utama JFrame: Header (NORTH), Sidebar (WEST), Content (CENTER)
        add(createHeaderPanel(), BorderLayout.NORTH);
        add(createSidebarPanel(), BorderLayout.WEST);
        add(createContentPanel(), BorderLayout.CENTER);
    }

    // ========================================================================
    // 2. HEADER / NAVBAR ATAS (1024 x 56 px) — SAMA UNTUK SEMUA HALAMAN
    // ========================================================================
    private JPanel createHeaderPanel() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(COLOR_PRIMARY_ORANGE);
        header.setPreferredSize(new Dimension(WINDOW_WIDTH, 56));
        header.setBorder(new EmptyBorder(0, 20, 0, 20));

        // Kiri: Logo IndoFX ADMIN
        JPanel leftBox = new JPanel(new FlowLayout(FlowLayout.LEFT, 12, 14));
        leftBox.setOpaque(false);

        JLabel lblLogo = new JLabel("IndoFX ADMIN");
        lblLogo.setFont(new Font("SansSerif", Font.BOLD, 18));
        lblLogo.setForeground(COLOR_WHITE);

        JLabel lblSubtitle = new JLabel("Panel Admin Desktop");
        lblSubtitle.setFont(new Font("SansSerif", Font.PLAIN, 12));
        lblSubtitle.setForeground(new Color(0xFF, 0xED, 0xD5));

        leftBox.add(lblLogo);
        leftBox.add(lblSubtitle);

        // Kanan: Nama Admin + Tombol Logout
        JPanel rightBox = new JPanel(new FlowLayout(FlowLayout.RIGHT, 14, 12));
        rightBox.setOpaque(false);

        JLabel lblAdmin = new JLabel("Admin: Andi Pratama");
        lblAdmin.setFont(new Font("SansSerif", Font.BOLD, 13));
        lblAdmin.setForeground(COLOR_WHITE);

        JButton btnLogout = new JButton("Logout");
        btnLogout.setPreferredSize(new Dimension(96, 32));
        btnLogout.setBackground(COLOR_WHITE);
        btnLogout.setForeground(COLOR_DARK_ORANGE);
        btnLogout.setFont(new Font("SansSerif", Font.BOLD, 12));
        btnLogout.setFocusPainted(false);
        btnLogout.setBorder(BorderFactory.createLineBorder(COLOR_WHITE, 1));
        btnLogout.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnLogout.addActionListener(e -> showLogoutConfirmDialog());

        rightBox.add(lblAdmin);
        rightBox.add(btnLogout);

        header.add(leftBox, BorderLayout.WEST);
        header.add(rightBox, BorderLayout.EAST);
        return header;
    }

    // ========================================================================
    // 3. SIDEBAR NAVIGASI KIRI (220 x 592 px) — SAMA UNTUK SEMUA HALAMAN
    // ========================================================================
    private JPanel createSidebarPanel() {
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBackground(COLOR_WHITE);
        sidebar.setPreferredSize(new Dimension(220, 592));
        sidebar.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 0, 1, COLOR_BORDER),
                new EmptyBorder(14, 12, 14, 12)
        ));

        JLabel lblSection = new JLabel("MENU NAVIGASI ADMIN");
        lblSection.setFont(new Font("SansSerif", Font.BOLD, 11));
        lblSection.setForeground(COLOR_TEXT_MUTED);
        lblSection.setAlignmentX(Component.LEFT_ALIGNMENT);
        sidebar.add(lblSection);
        sidebar.add(Box.createVerticalStrut(10));

        // Daftar 8 Menu sesuai Wireframe FigCraft
        String[] menuItems = {
            "1. Ringkasan Dashboard",
            "2. Moderasi Aset (3)",
            "3. Kategori Aset",
            "4. Manajemen Pengguna",
            "5. Persetujuan & Laporan (2)",
            "6. Monitoring Transaksi",
            "7. Verifikasi Pencairan (2)",
            "8. Pengaturan Komisi"
        };

        for (int i = 0; i < menuItems.length; i++) {
            int menuNumber = i + 1;
            boolean isActive = (menuNumber == ACTIVE_MENU_INDEX);

            JButton btnMenu = new JButton(menuItems[i]);
            btnMenu.setMaximumSize(new Dimension(196, 38));
            btnMenu.setPreferredSize(new Dimension(196, 38));
            btnMenu.setHorizontalAlignment(SwingConstants.LEFT);
            btnMenu.setFocusPainted(false);
            btnMenu.setCursor(new Cursor(Cursor.HAND_CURSOR));
            btnMenu.setAlignmentX(Component.LEFT_ALIGNMENT);

            if (isActive) {
                btnMenu.setBackground(COLOR_PRIMARY_ORANGE);
                btnMenu.setForeground(COLOR_WHITE);
                btnMenu.setFont(new Font("SansSerif", Font.BOLD, 12));
                btnMenu.setBorder(BorderFactory.createEmptyBorder(0, 12, 0, 12));
            } else {
                btnMenu.setBackground(COLOR_BG_LIGHT);
                btnMenu.setForeground(COLOR_TEXT_BODY);
                btnMenu.setFont(new Font("SansSerif", Font.PLAIN, 12));
                btnMenu.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(new Color(0xE2, 0xE8, 0xF0), 1),
                        new EmptyBorder(0, 11, 0, 11)
                ));
            }

            // Contoh aksi saat tombol sidebar diklik (nanti tinggal dihubungkan ke JFrame teman)
            final String selectedMenuName = menuItems[i];
            btnMenu.addActionListener(e -> {
                if (!isActive) {
                    JOptionPane.showMessageDialog(
                            this,
                            "Navigasi ke: " + selectedMenuName + "\n"
                            + "(Silakan hubungkan tombol ini ke JFrame halaman terkait setelah dibuat oleh tim).",
                            "Info Navigasi Menu",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                }
            });

            sidebar.add(btnMenu);
            sidebar.add(Box.createVerticalStrut(8));
        }

        return sidebar;
    }

    // ========================================================================
    // 4. AREA KONTEN UTAMA (804 x 592 px) — BAGIAN INI YANG DISESUAIKAN TIM
    // ========================================================================
    private JPanel createContentPanel() {
        JPanel content = new JPanel(new BorderLayout(0, 16));
        content.setBackground(COLOR_BG_CREAM);
        content.setBorder(new EmptyBorder(20, 24, 20, 24));

        // --- BAGIAN ATAS KONTEN: Judul Halaman + 4 Kartu Statistik ---
        JPanel topSection = new JPanel();
        topSection.setLayout(new BoxLayout(topSection, BoxLayout.Y_AXIS));
        topSection.setOpaque(false);

        JLabel lblTitle = new JLabel("Ringkasan Aktivitas Sistem");
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 18));
        lblTitle.setForeground(COLOR_TEXT_DARK);
        lblTitle.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lblSubtitle = new JLabel("Statistik utama platform & antrean moderasi aset terbaru");
        lblSubtitle.setFont(new Font("SansSerif", Font.PLAIN, 12));
        lblSubtitle.setForeground(COLOR_TEXT_MUTED);
        lblSubtitle.setAlignmentX(Component.LEFT_ALIGNMENT);

        topSection.add(lblTitle);
        topSection.add(Box.createVerticalStrut(4));
        topSection.add(lblSubtitle);
        topSection.add(Box.createVerticalStrut(14));

        // 4 Kartu Statistik (GridLayout 1 baris, 4 kolom, gap horizontal 16px)
        JPanel statsGrid = new JPanel(new GridLayout(1, 4, 16, 0));
        statsGrid.setOpaque(false);
        statsGrid.setPreferredSize(new Dimension(756, 88));
        statsGrid.setMaximumSize(new Dimension(756, 88));
        statsGrid.setAlignmentX(Component.LEFT_ALIGNMENT);

        statsGrid.add(createStatCard("Total Aset Aktif", "1,240", COLOR_TEXT_DARK));
        statsGrid.add(createStatCard("Pending Moderasi", "18 Aset", COLOR_DARK_ORANGE));
        statsGrid.add(createStatCard("Total Pengguna", "3,450", COLOR_TEXT_DARK));
        statsGrid.add(createStatCard("Komisi Bulan Ini", "Rp 14.5 Jt", COLOR_GREEN));

        topSection.add(statsGrid);

        // --- BAGIAN TENGAH KONTEN: Card Putih Berisi JTable + Tombol Pop-up ---
        JPanel tableCard = new JPanel(new BorderLayout(0, 12));
        tableCard.setBackground(COLOR_WHITE);
        tableCard.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(COLOR_BORDER, 1),
                new EmptyBorder(14, 16, 14, 16)
        ));

        // Header di atas Tabel: Judul Tabel di kiri + Tombol Pop-up di kanan
        JPanel tableTopBar = new JPanel(new BorderLayout());
        tableTopBar.setOpaque(false);

        JLabel lblTableTitle = new JLabel("Antrean Moderasi Aset Terbaru");
        lblTableTitle.setFont(new Font("SansSerif", Font.BOLD, 14));
        lblTableTitle.setForeground(COLOR_TEXT_DARK);

        JButton btnTinjauPopup = createPrimaryButton("Tinjau Baris Terpilih (Pop-up)", 220, 34);
        btnTinjauPopup.addActionListener(e -> showQuickModerasiDialog());

        tableTopBar.add(lblTableTitle, BorderLayout.WEST);
        tableTopBar.add(btnTinjauPopup, BorderLayout.EAST);

        // Definisi Kolom & Data Dummy Frontend JTable
        String[] columns = {"ID Aset", "Nama Aset", "Kreator", "Kategori", "Harga", "Status"};
        Object[][] dummyData = {
            {"AST-101", "Cyberpunk City 3D Pack", "StudioNusa", "3D Environment", "Rp 250.000", "PENDING"},
            {"AST-102", "Cinematic Fire VFX Vol.2", "RakaVFX", "Footage / VFX", "Rp 120.000", "PENDING"},
            {"AST-103", "Sci-Fi Laser Sound Pack", "AudioWave", "Audio & SFX", "Rp 75.000", "PENDING"},
            {"AST-100", "Indonesian Temple Ruins", "GarudaMesh", "3D Environment", "Rp 300.000", "DISETUJUI"}
        };

        tableModel = new DefaultTableModel(dummyData, columns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Tabel read-only agar rapi
            }
        };

        tableAntrean = new JTable(tableModel);
        styleTable(tableAntrean);
        tableAntrean.setRowSelectionInterval(0, 0); // Pilih baris pertama secara default

        JScrollPane scrollPane = new JScrollPane(tableAntrean);
        scrollPane.setBorder(BorderFactory.createLineBorder(COLOR_BORDER, 1));
        scrollPane.getViewport().setBackground(COLOR_WHITE);

        JLabel lblFooterHint = new JLabel(
                "Petunjuk: Klik salah satu baris aset pada tabel di atas, lalu klik tombol [Tinjau Baris Terpilih (Pop-up)]."
        );
        lblFooterHint.setFont(new Font("SansSerif", Font.PLAIN, 12));
        lblFooterHint.setForeground(COLOR_TEXT_MUTED);

        tableCard.add(tableTopBar, BorderLayout.NORTH);
        tableCard.add(scrollPane, BorderLayout.CENTER);
        tableCard.add(lblFooterHint, BorderLayout.SOUTH);

        content.add(topSection, BorderLayout.NORTH);
        content.add(tableCard, BorderLayout.CENTER);
        return content;
    }

    // ========================================================================
    // 5. POP-UP DIALOG SEDERHANA (JDialog — 420 x 250 px)
    // ========================================================================
    private void showQuickModerasiDialog() {
        int selectedRow = tableAntrean.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(
                    this,
                    "Silakan klik/pilih salah satu baris aset pada tabel terlebih dahulu!",
                    "Belum Ada Baris Dipilih",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        // Ambil data dari baris JTable yang sedang dipilih
        String idAset   = tableModel.getValueAt(selectedRow, 0).toString();
        String namaAset = tableModel.getValueAt(selectedRow, 1).toString();
        String kreator  = tableModel.getValueAt(selectedRow, 2).toString();
        String harga    = tableModel.getValueAt(selectedRow, 4).toString();

        // Buat JDialog Modal ukuran 420 x 250 px sesuai Wireframe FigCraft
        JDialog dialog = new JDialog(this, "Konfirmasi Cepat Moderasi — " + idAset, true);
        dialog.setSize(420, 250);
        dialog.setResizable(false);
        dialog.setLocationRelativeTo(this);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(COLOR_WHITE);
        panel.setBorder(new EmptyBorder(16, 20, 16, 20));

        JLabel lblHeader = new JLabel("Moderasi Cepat: " + idAset + " (" + namaAset + ")");
        lblHeader.setFont(new Font("SansSerif", Font.BOLD, 14));
        lblHeader.setForeground(COLOR_TEXT_DARK);
        lblHeader.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lblMeta = new JLabel("Kreator: " + kreator + "   |   Harga: " + harga);
        lblMeta.setFont(new Font("SansSerif", Font.PLAIN, 12));
        lblMeta.setForeground(COLOR_TEXT_BODY);
        lblMeta.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lblCatatan = new JLabel("Catatan / Alasan Penolakan (Opsional):");
        lblCatatan.setFont(new Font("SansSerif", Font.BOLD, 12));
        lblCatatan.setForeground(COLOR_TEXT_BODY);
        lblCatatan.setAlignmentX(Component.LEFT_ALIGNMENT);

        JTextField txtAlasan = new JTextField();
        txtAlasan.setMaximumSize(new Dimension(380, 36));
        txtAlasan.setPreferredSize(new Dimension(380, 36));
        txtAlasan.setFont(new Font("SansSerif", Font.PLAIN, 12));
        txtAlasan.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Baris Tombol Bawah: [Batal] [Tolak Aset] [Setujui Aset]
        JPanel btnRow = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
        btnRow.setOpaque(false);
        btnRow.setAlignmentX(Component.LEFT_ALIGNMENT);

        JButton btnBatal = createSecondaryButton("Batal", 85, 34);
        btnBatal.addActionListener(e -> dialog.dispose());

        JButton btnTolak = createColoredButton("Tolak Aset", COLOR_RED, 100, 34);
        btnTolak.addActionListener(e -> {
            tableModel.setValueAt("DITOLAK", selectedRow, 5);
            dialog.dispose();
            JOptionPane.showMessageDialog(this, "Aset " + idAset + " telah diubah menjadi DITOLAK.");
        });

        JButton btnSetujui = createColoredButton("Setujui Aset", COLOR_GREEN, 110, 34);
        btnSetujui.addActionListener(e -> {
            tableModel.setValueAt("DISETUJUI", selectedRow, 5);
            dialog.dispose();
            JOptionPane.showMessageDialog(this, "Aset " + idAset + " berhasil DISETUJUI!");
        });

        btnRow.add(btnBatal);
        btnRow.add(btnTolak);
        btnRow.add(btnSetujui);

        panel.add(lblHeader);
        panel.add(Box.createVerticalStrut(4));
        panel.add(lblMeta);
        panel.add(Box.createVerticalStrut(14));
        panel.add(lblCatatan);
        panel.add(Box.createVerticalStrut(6));
        panel.add(txtAlasan);
        panel.add(Box.createVerticalStrut(16));
        panel.add(btnRow);

        dialog.setContentPane(panel);
        dialog.setVisible(true);
    }

    // Pop-up Konfirmasi Logout (Sesuai Wireframe 00_JOptionPane_Logout_360x200)
    private void showLogoutConfirmDialog() {
        int result = JOptionPane.showConfirmDialog(
                this,
                "Keluar dari Akun Admin?\nSesi Anda akan ditutup dan kembali ke LoginFrame.",
                "Konfirmasi Logout",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );
        if (result == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(this, "Anda telah logout (Nanti diarahkan ke LoginFrame).");
        }
    }

    // ========================================================================
    // 6. HELPER PEMBUAT KOMPONEN (BISA LANGSUNG DIPAKAI DI HALAMAN LAIN)
    // ========================================================================
    protected JPanel createStatCard(String label, String value, Color valueColor) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(COLOR_WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(COLOR_BORDER, 1),
                new EmptyBorder(14, 14, 14, 14)
        ));

        JLabel lblTitle = new JLabel(label);
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 11));
        lblTitle.setForeground(COLOR_TEXT_MUTED);

        JLabel lblValue = new JLabel(value);
        lblValue.setFont(new Font("SansSerif", Font.BOLD, 20));
        lblValue.setForeground(valueColor);

        card.add(lblTitle);
        card.add(Box.createVerticalStrut(8));
        card.add(lblValue);
        return card;
    }

    protected JButton createPrimaryButton(String text, int width, int height) {
        return createColoredButton(text, COLOR_PRIMARY_ORANGE, width, height);
    }

    protected JButton createColoredButton(String text, Color bgColor, int width, int height) {
        JButton btn = new JButton(text);
        btn.setPreferredSize(new Dimension(width, height));
        btn.setBackground(bgColor);
        btn.setForeground(COLOR_WHITE);
        btn.setFont(new Font("SansSerif", Font.BOLD, 12));
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder(0, 12, 0, 12));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }

    protected JButton createSecondaryButton(String text, int width, int height) {
        JButton btn = new JButton(text);
        btn.setPreferredSize(new Dimension(width, height));
        btn.setBackground(COLOR_TABLE_HEADER);
        btn.setForeground(COLOR_TEXT_BODY);
        btn.setFont(new Font("SansSerif", Font.BOLD, 12));
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createLineBorder(COLOR_BORDER, 1));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }

    protected void styleTable(JTable table) {
        table.setRowHeight(36);
        table.setFont(new Font("SansSerif", Font.PLAIN, 12));
        table.setForeground(COLOR_TEXT_DARK);
        table.setGridColor(new Color(0xE2, 0xE8, 0xF0));
        table.setSelectionBackground(COLOR_BG_CREAM);
        table.setSelectionForeground(COLOR_TEXT_DARK);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setFillsViewportHeight(true);

        JTableHeader header = table.getTableHeader();
        header.setPreferredSize(new Dimension(header.getWidth(), 36));
        header.setBackground(COLOR_TABLE_HEADER);
        header.setForeground(COLOR_TEXT_BODY);
        header.setFont(new Font("SansSerif", Font.BOLD, 12));
        header.setReorderingAllowed(false);
    }

    // Method main() agar file JFrame ini bisa langsung di-Run sendiri di VS Code!
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception ignored) {
        }

        SwingUtilities.invokeLater(() -> {
            new DashboardFrame().setVisible(true);
        });
    }
}
