/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package indofx.admin;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;

/**
 * Form Drag & Drop NetBeans untuk Menu 2: Moderasi & Persetujuan Aset (1024 x 680 px).
 * Klik [Tinjau & Approve (Pop-up)] atau klik ganda baris tabel untuk membuka DlgDetailAset.
 *
 * @author zhaya
 */
public class FrmModerasiAset extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(FrmModerasiAset.class.getName());
    private static final String SEARCH_PLACEHOLDER = "Cari nama aset / kreator...";

    // Simpan metadata tambahan (email kreator, spesifikasi file, catatan admin) per ID aset
    private final Map<String, String[]> assetDetails = new HashMap<>();

    /**
     * Creates new form FrmModerasiAset
     */
    public FrmModerasiAset() {
        initComponents();
        setupTampilan();
        loadDummyData();
        initFilter();
    }

    private void setupTampilan() {
        btnLogout.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnTinjau.setCursor(new Cursor(Cursor.HAND_CURSOR));
        if (btnLogout instanceof RoundedButton rbHeaderLogout) {
            rbHeaderLogout.setHoverBackground(new Color(255, 247, 237));
            rbHeaderLogout.setHoverBorder(new Color(154, 52, 18), 2);
        }

        // Styling tabel & viewport agar bersih putih dan proporsional seperti wireframe
        jScrollPane1.getViewport().setBackground(Color.WHITE);
        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(203, 213, 225)));
        tblAset.setFillsViewportHeight(true);
        tblAset.setGridColor(new Color(226, 232, 240));
        tblAset.setShowVerticalLines(false);
        tblAset.setIntercellSpacing(new Dimension(10, 1));

        JTableHeader header = tblAset.getTableHeader();
        header.setPreferredSize(new Dimension(header.getWidth(), 36));
        header.setBackground(new Color(241, 245, 249));
        header.setForeground(new Color(51, 65, 85));
        header.setFont(new Font("SansSerif", Font.BOLD, 12));
        header.setReorderingAllowed(false);

        int[] colWidths = {78, 215, 108, 125, 98, 98};
        for (int i = 0; i < colWidths.length && i < tblAset.getColumnCount(); i++) {
            tblAset.getColumnModel().getColumn(i).setPreferredWidth(colWidths[i]);
        }

        // Pewarnaan teks kolom Status (PENDING = oranye, DISETUJUI = hijau, DITOLAK = merah)
        tblAset.getColumnModel().getColumn(5).setCellRenderer(new javax.swing.table.DefaultTableCellRenderer() {
            @Override
            public java.awt.Component getTableCellRendererComponent(javax.swing.JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                String status = value == null ? "" : value.toString();
                setFont(new Font("SansSerif", Font.BOLD, 12));
                setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 8, 0, 8));
                if ("DISETUJUI".equalsIgnoreCase(status)) {
                    setForeground(new Color(22, 163, 74));
                } else if ("DITOLAK".equalsIgnoreCase(status)) {
                    setForeground(new Color(220, 38, 38));
                } else {
                    setForeground(new Color(217, 119, 6));
                }
                return this;
            }
        });
    }

    /**
     * Mengisi data contoh (dummy) ke JTable sesuai wireframe Menu 2.
     */
    private void loadDummyData() {
        DefaultTableModel model = (DefaultTableModel) tblAset.getModel();
        model.setRowCount(0);
        assetDetails.clear();

        addAssetRow(model, "AST-101", "Cyberpunk City 3D Pack", "StudioNusa", "dimas@studionusa.id",
                "3D Environment", "Rp 250.000", "Blender (.blend), FBX, 4K Textures (1.2 GB)", "PENDING", "");
        addAssetRow(model, "AST-102", "Cinematic Fire VFX Vol.2", "RakaVFX", "raka@rakavfx.id",
                "Footage / VFX", "Rp 120.000", "MOV ProRes 4444 Alpha Channel (850 MB)", "PENDING", "");
        addAssetRow(model, "AST-103", "Sci-Fi Laser Sound Pack", "AudioWave", "hello@audiowave.id",
                "Audio & SFX", "Rp 75.000", "WAV 24-bit 96kHz (120 Sound Effects)", "PENDING", "");
        addAssetRow(model, "AST-100", "Indonesian Temple Ruins", "GarudaMesh", "garuda@mesh.id",
                "3D Environment", "Rp 300.000", "OBJ, FBX, Unreal Engine 5 Asset Pack (980 MB)", "DISETUJUI", "Kualitas mesh dan tekstur sudah sesuai standar.");
        addAssetRow(model, "AST-099", "Explosion Dust Pack", "VFXMaster", "support@vfxmaster.id",
                "Footage / VFX", "Rp 150.000", "MP4 1080p (30 Clip Pack)", "DITOLAK", "Resolusi minimal untuk kategori VFX adalah 4K.");

        if (tblAset.getRowCount() > 0) {
            tblAset.setRowSelectionInterval(0, 0);
        }
        updatePendingBadge();
    }

    private void addAssetRow(DefaultTableModel model, String id, String nama, String kreator,
            String email, String kategori, String harga, String spesifikasi, String status, String catatan) {
        model.addRow(new Object[]{id, nama, kreator, kategori, harga, status});
        assetDetails.put(id, new String[]{email, spesifikasi, catatan});
    }

    private void updatePendingBadge() {
        int pendingCount = 0;
        DefaultTableModel model = (DefaultTableModel) tblAset.getModel();
        for (int i = 0; i < model.getRowCount(); i++) {
            if ("PENDING".equalsIgnoreCase(String.valueOf(model.getValueAt(i, 5)))) {
                pendingCount++;
            }
        }
        btnMenu2.setText("2. Moderasi Aset (" + pendingCount + ")");
    }

    private void initFilter() {
        final TableRowSorter<DefaultTableModel> sorter
                = new TableRowSorter<>((DefaultTableModel) tblAset.getModel());
        tblAset.setRowSorter(sorter);

        txtSearch.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                applyFilter();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                applyFilter();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                applyFilter();
            }
        });

        txtSearch.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent e) {
                if (SEARCH_PLACEHOLDER.equals(txtSearch.getText())) {
                    txtSearch.setText("");
                    txtSearch.setForeground(new Color(15, 23, 42));
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent e) {
                if (txtSearch.getText().trim().isEmpty()) {
                    txtSearch.setText(SEARCH_PLACEHOLDER);
                    txtSearch.setForeground(new Color(100, 116, 139));
                }
            }
        });

        cmbKategori.addActionListener(e -> applyFilter());
        cmbStatus.addActionListener(e -> applyFilter());
    }

    private void applyFilter() {
        @SuppressWarnings("unchecked")
        TableRowSorter<DefaultTableModel> sorter
                = (TableRowSorter<DefaultTableModel>) tblAset.getRowSorter();
        if (sorter == null) {
            return;
        }

        String keyword = txtSearch.getText().trim();
        if (keyword.equalsIgnoreCase(SEARCH_PLACEHOLDER)) {
            keyword = "";
        }
        final String key = keyword.toLowerCase();
        final String kategori = String.valueOf(cmbKategori.getSelectedItem());
        final String status = String.valueOf(cmbStatus.getSelectedItem());

        List<RowFilter<Object, Object>> filters = new ArrayList<>();
        if (!key.isEmpty()) {
            filters.add(new RowFilter<Object, Object>() {
                @Override
                public boolean include(RowFilter.Entry<?, ?> entry) {
                    String id = String.valueOf(entry.getValue(0)).toLowerCase();
                    String nama = String.valueOf(entry.getValue(1)).toLowerCase();
                    String kreator = String.valueOf(entry.getValue(2)).toLowerCase();
                    return id.contains(key) || nama.contains(key) || kreator.contains(key);
                }
            });
        }
        if (kategori != null && !kategori.equals("Semua Kategori")) {
            filters.add(RowFilter.regexFilter("^" + java.util.regex.Pattern.quote(kategori) + "$", 3));
        }
        if (status != null && !status.equals("Status: Semua")) {
            filters.add(RowFilter.regexFilter("^" + java.util.regex.Pattern.quote(status) + "$", 5));
        }

        sorter.setRowFilter(filters.isEmpty() ? null : RowFilter.andFilter(filters));
    }

    private void openDetailDialog() {
        int viewRow = tblAset.getSelectedRow();
        if (viewRow == -1) {
            JOptionPane.showMessageDialog(
                    this,
                    "Silakan klik/pilih salah satu baris aset pada tabel terlebih dahulu!",
                    "Belum Ada Baris Dipilih",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        int modelRow = tblAset.convertRowIndexToModel(viewRow);
        DefaultTableModel model = (DefaultTableModel) tblAset.getModel();

        String id       = String.valueOf(model.getValueAt(modelRow, 0));
        String nama     = String.valueOf(model.getValueAt(modelRow, 1));
        String kreator  = String.valueOf(model.getValueAt(modelRow, 2));
        String kategori = String.valueOf(model.getValueAt(modelRow, 3));
        String harga    = String.valueOf(model.getValueAt(modelRow, 4));
        String status   = String.valueOf(model.getValueAt(modelRow, 5));

        String[] detail = assetDetails.getOrDefault(id, new String[]{"", "-", ""});
        String emailKreator = detail[0];
        String spesifikasi  = detail[1];
        String catatanLama  = detail[2];

        DlgDetailAset dialog = new DlgDetailAset(this, true);
        dialog.setDataAset(id, nama, kreator, emailKreator, kategori, harga, spesifikasi, status, catatanLama);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);

        if (dialog.hasilStatus != null) {
            model.setValueAt(dialog.hasilStatus, modelRow, 5);
            assetDetails.put(id, new String[]{emailKreator, spesifikasi, dialog.hasilCatatan});
            updatePendingBadge();
            JOptionPane.showMessageDialog(
                    this,
                    "Status aset " + id + " berhasil diperbarui menjadi " + dialog.hasilStatus + "."
            );
        }
    }

    private void showNavPlaceholder(String menuName) {
        JOptionPane.showMessageDialog(
                this,
                "Navigasi ke: " + menuName + "\n"
                + "(Hubungkan ke JFrame Form milik anggota tim setelah selesai dibuat).",
                "Info Navigasi Menu",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlHeader = new javax.swing.JPanel();
        lblLogo = new javax.swing.JLabel();
        lblSubLogo = new javax.swing.JLabel();
        lblAdmin = new javax.swing.JLabel();
        btnLogout = new RoundedButton(8);
        pnlSidebar = new javax.swing.JPanel();
        lblMenuTitle = new javax.swing.JLabel();
        btnMenu1 = new javax.swing.JButton();
        btnMenu2 = new javax.swing.JButton();
        btnMenu3 = new javax.swing.JButton();
        btnMenu4 = new javax.swing.JButton();
        btnMenu5 = new javax.swing.JButton();
        btnMenu6 = new javax.swing.JButton();
        btnMenu7 = new javax.swing.JButton();
        btnMenu8 = new javax.swing.JButton();
        pnlContent = new javax.swing.JPanel();
        lblTitle = new javax.swing.JLabel();
        lblSubtitle = new javax.swing.JLabel();
        pnlFilterBar = new javax.swing.JPanel();
        txtSearch = new javax.swing.JTextField();
        cmbKategori = new javax.swing.JComboBox<>();
        cmbStatus = new javax.swing.JComboBox<>();
        btnTinjau = new javax.swing.JButton();
        pnlTableCard = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblAset = new javax.swing.JTable();
        lblHint = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("IndoFX Admin — Moderasi & Persetujuan Aset (1024 x 680)");
        setResizable(false);

        pnlHeader.setBackground(new java.awt.Color(249, 115, 22));
        pnlHeader.setPreferredSize(new java.awt.Dimension(1024, 56));

        lblLogo.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        lblLogo.setForeground(new java.awt.Color(255, 255, 255));
        lblLogo.setText("IndoFX ADMIN");

        lblSubLogo.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        lblSubLogo.setForeground(new java.awt.Color(255, 237, 213));
        lblSubLogo.setText("Panel Admin Desktop");

        lblAdmin.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        lblAdmin.setForeground(new java.awt.Color(255, 255, 255));
        lblAdmin.setText("Admin: Andi Pratama");

        btnLogout.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnLogout.setForeground(new java.awt.Color(234, 88, 12));
        btnLogout.setText("Logout");
        btnLogout.setFocusPainted(false);
        btnLogout.addActionListener(this::btnLogoutActionPerformed);

        javax.swing.GroupLayout pnlHeaderLayout = new javax.swing.GroupLayout(pnlHeader);
        pnlHeader.setLayout(pnlHeaderLayout);
        pnlHeaderLayout.setHorizontalGroup(
            pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHeaderLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblLogo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblSubLogo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 472, Short.MAX_VALUE)
                .addComponent(lblAdmin)
                .addGap(18, 18, 18)
                .addComponent(btnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        pnlHeaderLayout.setVerticalGroup(
            pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHeaderLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(pnlHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblLogo)
                    .addComponent(lblSubLogo)
                    .addComponent(lblAdmin)
                    .addComponent(btnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        getContentPane().add(pnlHeader, java.awt.BorderLayout.NORTH);

        pnlSidebar.setBackground(new java.awt.Color(255, 255, 255));
        pnlSidebar.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 1, new java.awt.Color(203, 213, 225)));
        pnlSidebar.setPreferredSize(new java.awt.Dimension(220, 624));

        lblMenuTitle.setFont(new java.awt.Font("SansSerif", 1, 11)); // NOI18N
        lblMenuTitle.setForeground(new java.awt.Color(100, 116, 139));
        lblMenuTitle.setText("MENU NAVIGASI ADMIN");

        btnMenu1.setBackground(new java.awt.Color(248, 250, 252));
        btnMenu1.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        btnMenu1.setForeground(new java.awt.Color(51, 65, 85));
        btnMenu1.setText("1. Ringkasan Dashboard");
        btnMenu1.setFocusPainted(false);
        btnMenu1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnMenu1.addActionListener(this::btnMenu1ActionPerformed);

        btnMenu2.setBackground(new java.awt.Color(249, 115, 22));
        btnMenu2.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnMenu2.setForeground(new java.awt.Color(255, 255, 255));
        btnMenu2.setText("2. Moderasi Aset (3)");
        btnMenu2.setFocusPainted(false);
        btnMenu2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        btnMenu3.setBackground(new java.awt.Color(248, 250, 252));
        btnMenu3.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        btnMenu3.setForeground(new java.awt.Color(51, 65, 85));
        btnMenu3.setText("3. Kategori Aset");
        btnMenu3.setFocusPainted(false);
        btnMenu3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnMenu3.addActionListener(this::btnMenu3ActionPerformed);

        btnMenu4.setBackground(new java.awt.Color(248, 250, 252));
        btnMenu4.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        btnMenu4.setForeground(new java.awt.Color(51, 65, 85));
        btnMenu4.setText("4. Manajemen Pengguna");
        btnMenu4.setFocusPainted(false);
        btnMenu4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnMenu4.addActionListener(this::btnMenu4ActionPerformed);

        btnMenu5.setBackground(new java.awt.Color(248, 250, 252));
        btnMenu5.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        btnMenu5.setForeground(new java.awt.Color(51, 65, 85));
        btnMenu5.setText("5. Persetujuan & Laporan (2)");
        btnMenu5.setFocusPainted(false);
        btnMenu5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnMenu5.addActionListener(this::btnMenu5ActionPerformed);

        btnMenu6.setBackground(new java.awt.Color(248, 250, 252));
        btnMenu6.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        btnMenu6.setForeground(new java.awt.Color(51, 65, 85));
        btnMenu6.setText("6. Monitoring Transaksi");
        btnMenu6.setFocusPainted(false);
        btnMenu6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnMenu6.addActionListener(this::btnMenu6ActionPerformed);

        btnMenu7.setBackground(new java.awt.Color(248, 250, 252));
        btnMenu7.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        btnMenu7.setForeground(new java.awt.Color(51, 65, 85));
        btnMenu7.setText("7. Verifikasi Pencairan (2)");
        btnMenu7.setFocusPainted(false);
        btnMenu7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnMenu7.addActionListener(this::btnMenu7ActionPerformed);

        btnMenu8.setBackground(new java.awt.Color(248, 250, 252));
        btnMenu8.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        btnMenu8.setForeground(new java.awt.Color(51, 65, 85));
        btnMenu8.setText("8. Pengaturan Komisi");
        btnMenu8.setFocusPainted(false);
        btnMenu8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnMenu8.addActionListener(this::btnMenu8ActionPerformed);

        javax.swing.GroupLayout pnlSidebarLayout = new javax.swing.GroupLayout(pnlSidebar);
        pnlSidebar.setLayout(pnlSidebarLayout);
        pnlSidebarLayout.setHorizontalGroup(
            pnlSidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSidebarLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(pnlSidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblMenuTitle)
                    .addComponent(btnMenu1, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
                    .addComponent(btnMenu2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnMenu3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnMenu4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnMenu5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnMenu6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnMenu7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnMenu8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(12, Short.MAX_VALUE))
        );
        pnlSidebarLayout.setVerticalGroup(
            pnlSidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSidebarLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(lblMenuTitle)
                .addGap(10, 10, 10)
                .addComponent(btnMenu1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(btnMenu2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(btnMenu3, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(btnMenu4, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(btnMenu5, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(btnMenu6, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(btnMenu7, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(btnMenu8, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(223, Short.MAX_VALUE))
        );

        getContentPane().add(pnlSidebar, java.awt.BorderLayout.WEST);

        pnlContent.setBackground(new java.awt.Color(255, 247, 237));

        lblTitle.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(15, 23, 42));
        lblTitle.setText("Moderasi & Persetujuan Aset");

        lblSubtitle.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        lblSubtitle.setForeground(new java.awt.Color(100, 116, 139));
        lblSubtitle.setText("Tinjau pengajuan aset kreator dan kelola persetujuan melalui pop-up detail");

        pnlFilterBar.setBackground(new java.awt.Color(255, 255, 255));
        pnlFilterBar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(203, 213, 225)));

        txtSearch.setBackground(new java.awt.Color(248, 250, 252));
        txtSearch.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        txtSearch.setForeground(new java.awt.Color(100, 116, 139));
        txtSearch.setText("Cari nama aset / kreator...");
        txtSearch.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(148, 163, 184)), javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 10)));
        txtSearch.addActionListener(this::txtSearchActionPerformed);

        cmbKategori.setBackground(new java.awt.Color(248, 250, 252));
        cmbKategori.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        cmbKategori.setForeground(new java.awt.Color(51, 65, 85));
        cmbKategori.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Semua Kategori", "3D Environment", "Footage / VFX", "Audio & SFX", "Textures & Materials" }));

        cmbStatus.setBackground(new java.awt.Color(248, 250, 252));
        cmbStatus.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        cmbStatus.setForeground(new java.awt.Color(51, 65, 85));
        cmbStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Status: Semua", "PENDING", "DISETUJUI", "DITOLAK" }));

        btnTinjau.setBackground(new java.awt.Color(249, 115, 22));
        btnTinjau.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnTinjau.setForeground(new java.awt.Color(255, 255, 255));
        btnTinjau.setText("Tinjau & Approve (Pop-up)");
        btnTinjau.setFocusPainted(false);
        btnTinjau.addActionListener(this::btnTinjauActionPerformed);

        javax.swing.GroupLayout pnlFilterBarLayout = new javax.swing.GroupLayout(pnlFilterBar);
        pnlFilterBar.setLayout(pnlFilterBarLayout);
        pnlFilterBarLayout.setHorizontalGroup(
            pnlFilterBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFilterBarLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(cmbKategori, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(cmbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(btnTinjau, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );
        pnlFilterBarLayout.setVerticalGroup(
            pnlFilterBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFilterBarLayout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(pnlFilterBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtSearch, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                    .addComponent(cmbKategori)
                    .addComponent(cmbStatus)
                    .addComponent(btnTinjau, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(9, Short.MAX_VALUE))
        );

        pnlTableCard.setBackground(new java.awt.Color(255, 255, 255));
        pnlTableCard.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(203, 213, 225)));

        tblAset.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        tblAset.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Aset", "Nama Aset", "Kreator", "Kategori", "Harga", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblAset.setRowHeight(34);
        tblAset.setSelectionBackground(new java.awt.Color(255, 247, 237));
        tblAset.setSelectionForeground(new java.awt.Color(15, 23, 42));
        tblAset.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblAsetMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblAset);

        lblHint.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        lblHint.setForeground(new java.awt.Color(100, 116, 139));
        lblHint.setText("Petunjuk: Klik salah satu baris aset (atau klik ganda), lalu klik tombol [Tinjau & Approve (Pop-up)].");

        javax.swing.GroupLayout pnlTableCardLayout = new javax.swing.GroupLayout(pnlTableCard);
        pnlTableCard.setLayout(pnlTableCardLayout);
        pnlTableCardLayout.setHorizontalGroup(
            pnlTableCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTableCardLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(pnlTableCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(pnlTableCardLayout.createSequentialGroup()
                        .addComponent(lblHint)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(16, 16, 16))
        );
        pnlTableCardLayout.setVerticalGroup(
            pnlTableCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTableCardLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(lblHint)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlContentLayout = new javax.swing.GroupLayout(pnlContent);
        pnlContent.setLayout(pnlContentLayout);
        pnlContentLayout.setHorizontalGroup(
            pnlContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContentLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(pnlContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblTitle)
                    .addComponent(lblSubtitle)
                    .addComponent(pnlFilterBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlTableCard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        pnlContentLayout.setVerticalGroup(
            pnlContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContentLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblTitle)
                .addGap(4, 4, 4)
                .addComponent(lblSubtitle)
                .addGap(14, 14, 14)
                .addComponent(pnlFilterBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addComponent(pnlTableCard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        getContentPane().add(pnlContent, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        DlgLogout dialog = new DlgLogout(this, true);
        dialog.setVisible(true);
        if (dialog.isConfirmed()) {
            new FrmLogin().setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void btnTinjauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTinjauActionPerformed
        openDetailDialog();
    }//GEN-LAST:event_btnTinjauActionPerformed

    private void tblAsetMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblAsetMouseClicked
        if (evt.getClickCount() == 2) {
            openDetailDialog();
        }
    }//GEN-LAST:event_tblAsetMouseClicked

    private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchActionPerformed
        applyFilter();
    }//GEN-LAST:event_txtSearchActionPerformed

    private void btnMenu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenu1ActionPerformed
        new FrmDashboard().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnMenu1ActionPerformed

    private void btnMenu3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenu3ActionPerformed
        new FrmKategoriAset().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnMenu3ActionPerformed

    private void btnMenu4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenu4ActionPerformed
        new FrmManajemenPengguna().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnMenu4ActionPerformed

    private void btnMenu5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenu5ActionPerformed
        new FrmLaporan().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnMenu5ActionPerformed

    private void btnMenu6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenu6ActionPerformed
        new FrmTransaksi().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnMenu6ActionPerformed

    private void btnMenu7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenu7ActionPerformed
        showNavPlaceholder("7. Verifikasi Pencairan");
    }//GEN-LAST:event_btnMenu7ActionPerformed

    private void btnMenu8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenu8ActionPerformed
        showNavPlaceholder("8. Pengaturan Komisi");
    }//GEN-LAST:event_btnMenu8ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new FrmModerasiAset().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnMenu1;
    private javax.swing.JButton btnMenu2;
    private javax.swing.JButton btnMenu3;
    private javax.swing.JButton btnMenu4;
    private javax.swing.JButton btnMenu5;
    private javax.swing.JButton btnMenu6;
    private javax.swing.JButton btnMenu7;
    private javax.swing.JButton btnMenu8;
    private javax.swing.JButton btnTinjau;
    private javax.swing.JComboBox<String> cmbKategori;
    private javax.swing.JComboBox<String> cmbStatus;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAdmin;
    private javax.swing.JLabel lblHint;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblMenuTitle;
    private javax.swing.JLabel lblSubLogo;
    private javax.swing.JLabel lblSubtitle;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JPanel pnlContent;
    private javax.swing.JPanel pnlFilterBar;
    private javax.swing.JPanel pnlHeader;
    private javax.swing.JPanel pnlSidebar;
    private javax.swing.JPanel pnlTableCard;
    private javax.swing.JTable tblAset;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
