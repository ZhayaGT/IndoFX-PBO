/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package indofx.admin;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * Form Drag & Drop NetBeans untuk Menu 4: Manajemen Pengguna & Kreator (1024 x 680 px).
 * Data masih dummy (in-memory) karena tahap ini fokus pada Front-End.
 *
 * @author zhaya
 */
public class FrmManajemenPengguna extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(FrmManajemenPengguna.class.getName());

    private static final String SEARCH_PLACEHOLDER = "Cari nama / email";

    /**
     * Creates new form FrmManajemenPengguna
     */
    public FrmManajemenPengguna() {
        initComponents();
        loadDummyData();
        initFilter();
    }

    /**
     * Mengisi data contoh (dummy) pengguna ke JTable untuk tampilan Front-End.
     * Nanti tinggal diganti dengan query database saat masuk tahap Back-End.
     */
    private void loadDummyData() {
        DefaultTableModel model = (DefaultTableModel) tblUser.getModel();
        model.setRowCount(0);
        model.addRow(new Object[]{"USR-01", "Dimas Prasetyo", "dimas@studionusa.id", "Kreator", "Aktif"});
        model.addRow(new Object[]{"USR-02", "Raka Wijaya", "raka@rakavfx.id", "Kreator", "Aktif"});
        model.addRow(new Object[]{"USR-03", "Andi Pratama", "andi.pratama@mail.com", "Pembeli", "Aktif"});
        model.addRow(new Object[]{"USR-04", "Sari Lestari", "sari.lestari@mail.com", "Pembeli", "Aktif"});
        model.addRow(new Object[]{"USR-05", "Bayu Saputra", "bayu.spam@mail.com", "Pembeli", "Suspended"});
        if (tblUser.getRowCount() > 0) {
            tblUser.setRowSelectionInterval(0, 0);
        }
    }

    /**
     * Memasang fitur pencarian & filter role pada tabel pengguna.
     */
    private void initFilter() {
        final javax.swing.table.TableRowSorter<DefaultTableModel> sorter
                = new javax.swing.table.TableRowSorter<>((DefaultTableModel) tblUser.getModel());
        tblUser.setRowSorter(sorter);

        txtSearchBar.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                applyFilter();
            }

            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                applyFilter();
            }

            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                applyFilter();
            }
        });
        txtSearchBar.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent e) {
                if (SEARCH_PLACEHOLDER.equals(txtSearchBar.getText())) {
                    txtSearchBar.setText("");
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent e) {
                if (txtSearchBar.getText().trim().isEmpty()) {
                    txtSearchBar.setText(SEARCH_PLACEHOLDER);
                }
            }
        });
        cmbFilter.addActionListener(e -> applyFilter());
    }

    /**
     * Menerapkan filter pencarian nama/email dan filter role ke tabel.
     */
    private void applyFilter() {
        @SuppressWarnings("unchecked")
        javax.swing.table.TableRowSorter<DefaultTableModel> sorter
                = (javax.swing.table.TableRowSorter<DefaultTableModel>) tblUser.getRowSorter();
        if (sorter == null) {
            return;
        }

        String keyword = txtSearchBar.getText().trim();
        if (keyword.equalsIgnoreCase(SEARCH_PLACEHOLDER)) {
            keyword = "";
        }
        final String key = keyword.toLowerCase();
        final String role = String.valueOf(cmbFilter.getSelectedItem());

        java.util.List<javax.swing.RowFilter<Object, Object>> filters = new java.util.ArrayList<>();
        if (!key.isEmpty()) {
            filters.add(new javax.swing.RowFilter<Object, Object>() {
                @Override
                public boolean include(javax.swing.RowFilter.Entry<?, ?> entry) {
                    String nama = String.valueOf(entry.getValue(1)).toLowerCase();
                    String email = String.valueOf(entry.getValue(2)).toLowerCase();
                    return nama.contains(key) || email.contains(key);
                }
            });
        }
        if (role != null && !role.equals("Semua Role")) {
            filters.add(new javax.swing.RowFilter<Object, Object>() {
                @Override
                public boolean include(javax.swing.RowFilter.Entry<?, ?> entry) {
                    return role.equals(String.valueOf(entry.getValue(3)));
                }
            });
        }

        sorter.setRowFilter(filters.isEmpty() ? null : javax.swing.RowFilter.andFilter(filters));
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
        btnLogout = new javax.swing.JButton();
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
        pnlStats = new javax.swing.JPanel();
        cardTotalUser = new javax.swing.JPanel();
        lblStat1Title = new javax.swing.JLabel();
        lblStat1Val = new javax.swing.JLabel();
        cardKreator = new javax.swing.JPanel();
        lblStat2Title = new javax.swing.JLabel();
        lblStat2Val = new javax.swing.JLabel();
        cardPembeli = new javax.swing.JPanel();
        lblStat3Title = new javax.swing.JLabel();
        lblStat3Val = new javax.swing.JLabel();
        cardSuspended = new javax.swing.JPanel();
        lblStat4Title = new javax.swing.JLabel();
        lblStat4Val = new javax.swing.JLabel();
        pnlTableCard = new javax.swing.JPanel();
        btnUbah = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblUser = new javax.swing.JTable();
        lblHint = new javax.swing.JLabel();
        txtSearchBar = new javax.swing.JTextField();
        cmbFilter = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("IndoFX Admin — Manajemen Pengguna (1024 x 680)");
        setPreferredSize(new java.awt.Dimension(1024, 680));
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

        btnMenu2.setBackground(new java.awt.Color(248, 250, 252));
        btnMenu2.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        btnMenu2.setForeground(new java.awt.Color(51, 65, 85));
        btnMenu2.setText("2. Moderasi Aset (3)");
        btnMenu2.setFocusPainted(false);
        btnMenu2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnMenu2.addActionListener(this::btnMenu2ActionPerformed);

        btnMenu3.setBackground(new java.awt.Color(248, 250, 252));
        btnMenu3.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        btnMenu3.setForeground(new java.awt.Color(51, 65, 85));
        btnMenu3.setText("3. Kategori Aset");
        btnMenu3.setFocusPainted(false);
        btnMenu3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnMenu3.addActionListener(this::btnMenu3ActionPerformed);

        btnMenu4.setBackground(new java.awt.Color(249, 115, 22));
        btnMenu4.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnMenu4.setForeground(new java.awt.Color(255, 255, 255));
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
        lblTitle.setText("Manajemen Pengguna & Kreator");

        pnlStats.setOpaque(false);
        pnlStats.setLayout(new java.awt.GridLayout(1, 4, 16, 0));

        cardTotalUser.setBackground(new java.awt.Color(255, 255, 255));
        cardTotalUser.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(203, 213, 225)));

        lblStat1Title.setFont(new java.awt.Font("SansSerif", 1, 11)); // NOI18N
        lblStat1Title.setForeground(new java.awt.Color(100, 116, 139));
        lblStat1Title.setText("Total Pengguna");

        lblStat1Val.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        lblStat1Val.setForeground(new java.awt.Color(15, 23, 42));
        lblStat1Val.setText("3.450");

        javax.swing.GroupLayout cardTotalUserLayout = new javax.swing.GroupLayout(cardTotalUser);
        cardTotalUser.setLayout(cardTotalUserLayout);
        cardTotalUserLayout.setHorizontalGroup(
            cardTotalUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardTotalUserLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(cardTotalUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblStat1Title)
                    .addComponent(lblStat1Val))
                .addContainerGap(57, Short.MAX_VALUE))
        );
        cardTotalUserLayout.setVerticalGroup(
            cardTotalUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardTotalUserLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(lblStat1Title)
                .addGap(8, 8, 8)
                .addComponent(lblStat1Val)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        pnlStats.add(cardTotalUser);

        cardKreator.setBackground(new java.awt.Color(255, 255, 255));
        cardKreator.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(203, 213, 225)));

        lblStat2Title.setFont(new java.awt.Font("SansSerif", 1, 11)); // NOI18N
        lblStat2Title.setForeground(new java.awt.Color(100, 116, 139));
        lblStat2Title.setText("Kreator Aktif");

        lblStat2Val.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        lblStat2Val.setForeground(new java.awt.Color(234, 88, 12));
        lblStat2Val.setText("420");

        javax.swing.GroupLayout cardKreatorLayout = new javax.swing.GroupLayout(cardKreator);
        cardKreator.setLayout(cardKreatorLayout);
        cardKreatorLayout.setHorizontalGroup(
            cardKreatorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardKreatorLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(cardKreatorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblStat2Title)
                    .addComponent(lblStat2Val))
                .addContainerGap(91, Short.MAX_VALUE))
        );
        cardKreatorLayout.setVerticalGroup(
            cardKreatorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardKreatorLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(lblStat2Title)
                .addGap(8, 8, 8)
                .addComponent(lblStat2Val)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        pnlStats.add(cardKreator);

        cardPembeli.setBackground(new java.awt.Color(255, 255, 255));
        cardPembeli.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(203, 213, 225)));

        lblStat3Title.setFont(new java.awt.Font("SansSerif", 1, 11)); // NOI18N
        lblStat3Title.setForeground(new java.awt.Color(100, 116, 139));
        lblStat3Title.setText("Pembeli");

        lblStat3Val.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        lblStat3Val.setForeground(new java.awt.Color(22, 163, 74));
        lblStat3Val.setText("3,018");

        javax.swing.GroupLayout cardPembeliLayout = new javax.swing.GroupLayout(cardPembeli);
        cardPembeli.setLayout(cardPembeliLayout);
        cardPembeliLayout.setHorizontalGroup(
            cardPembeliLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardPembeliLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(cardPembeliLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblStat3Title)
                    .addComponent(lblStat3Val))
                .addContainerGap(111, Short.MAX_VALUE))
        );
        cardPembeliLayout.setVerticalGroup(
            cardPembeliLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardPembeliLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(lblStat3Title)
                .addGap(8, 8, 8)
                .addComponent(lblStat3Val)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        pnlStats.add(cardPembeli);

        cardSuspended.setBackground(new java.awt.Color(255, 255, 255));
        cardSuspended.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(203, 213, 225)));

        lblStat4Title.setFont(new java.awt.Font("SansSerif", 1, 11)); // NOI18N
        lblStat4Title.setForeground(new java.awt.Color(100, 116, 139));
        lblStat4Title.setText("Akun Suspended");

        lblStat4Val.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        lblStat4Val.setForeground(new java.awt.Color(220, 38, 38));
        lblStat4Val.setText("12");

        javax.swing.GroupLayout cardSuspendedLayout = new javax.swing.GroupLayout(cardSuspended);
        cardSuspended.setLayout(cardSuspendedLayout);
        cardSuspendedLayout.setHorizontalGroup(
            cardSuspendedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardSuspendedLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(cardSuspendedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblStat4Title)
                    .addComponent(lblStat4Val))
                .addContainerGap(47, Short.MAX_VALUE))
        );
        cardSuspendedLayout.setVerticalGroup(
            cardSuspendedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardSuspendedLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(lblStat4Title)
                .addGap(8, 8, 8)
                .addComponent(lblStat4Val)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        pnlStats.add(cardSuspended);

        pnlTableCard.setBackground(new java.awt.Color(255, 255, 255));
        pnlTableCard.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(203, 213, 225)));

        btnUbah.setBackground(new java.awt.Color(249, 115, 22));
        btnUbah.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnUbah.setForeground(new java.awt.Color(255, 255, 255));
        btnUbah.setText("Ubah Status / Role");
        btnUbah.setFocusPainted(false);
        btnUbah.addActionListener(this::btnUbahActionPerformed);

        tblUser.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        tblUser.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID User", "Nama", "Email", "Role", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblUser.setRowHeight(32);
        tblUser.setSelectionBackground(new java.awt.Color(255, 247, 237));
        tblUser.setSelectionForeground(new java.awt.Color(15, 23, 42));
        jScrollPane1.setViewportView(tblUser);

        lblHint.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        lblHint.setForeground(new java.awt.Color(100, 116, 139));
        lblHint.setText("Petunjuk: Klik salah satu baris pengguna pada tabel di atas, lalu klik tombol [Ubah Status / Role].");

        txtSearchBar.setText("Cari nama / email");
        txtSearchBar.addActionListener(this::txtSearchBarActionPerformed);

        cmbFilter.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Semua Role", "Kreator", "Pembeli" }));

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
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(pnlTableCardLayout.createSequentialGroup()
                        .addComponent(txtSearchBar, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnUbah, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(16, 16, 16))
        );
        pnlTableCardLayout.setVerticalGroup(
            pnlTableCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTableCardLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(pnlTableCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtSearchBar)
                    .addComponent(cmbFilter)
                    .addComponent(btnUbah, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE))
                .addGap(12, 12, 12)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(lblHint)
                .addGap(14, 14, 14))
        );

        javax.swing.GroupLayout pnlContentLayout = new javax.swing.GroupLayout(pnlContent);
        pnlContent.setLayout(pnlContentLayout);
        pnlContentLayout.setHorizontalGroup(
            pnlContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContentLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(pnlContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblTitle)
                    .addComponent(pnlStats, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlTableCard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        pnlContentLayout.setVerticalGroup(
            pnlContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContentLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblTitle)
                .addGap(20, 20, 20)
                .addComponent(pnlStats, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pnlTableCard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(60, Short.MAX_VALUE))
        );

        getContentPane().add(pnlContent, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        int jawab = JOptionPane.showConfirmDialog(
                this,
                "Keluar dari Akun Admin?\nSesi Anda akan ditutup dan kembali ke LoginFrame.",
                "Konfirmasi Logout",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );
        if (jawab == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(this, "Berhasil Logout! (Nanti hubungkan ke FrmLogin).");
        }
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void btnUbahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUbahActionPerformed
        int viewRow = tblUser.getSelectedRow();
        if (viewRow == -1) {
            JOptionPane.showMessageDialog(
                    this,
                    "Silakan klik/pilih salah satu baris pengguna pada tabel terlebih dahulu!",
                    "Belum Ada Baris Dipilih",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        int row = tblUser.convertRowIndexToModel(viewRow);
        String id         = String.valueOf(tblUser.getModel().getValueAt(row, 0));
        String nama       = String.valueOf(tblUser.getModel().getValueAt(row, 1));
        String email      = String.valueOf(tblUser.getModel().getValueAt(row, 2));
        String role       = String.valueOf(tblUser.getModel().getValueAt(row, 3));
        String statusAkun = String.valueOf(tblUser.getModel().getValueAt(row, 4));

        DlgKelolaUser dialog = new DlgKelolaUser(this, true);
        dialog.setDataUser(id, nama, email, role, statusAkun);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);

        if (dialog.tersimpan) {
            tblUser.getModel().setValueAt(dialog.hasilRole, row, 3);
            tblUser.getModel().setValueAt(dialog.hasilStatusAkun, row, 4);
            JOptionPane.showMessageDialog(this, "Data pengguna " + id + " berhasil diperbarui!");
        }
    }//GEN-LAST:event_btnUbahActionPerformed

    private void btnMenu2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenu2ActionPerformed
        showNavPlaceholder("2. Moderasi Aset");
    }//GEN-LAST:event_btnMenu2ActionPerformed

    private void btnMenu3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenu3ActionPerformed
        showNavPlaceholder("3. Kategori Aset");
    }//GEN-LAST:event_btnMenu3ActionPerformed

    private void btnMenu4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenu4ActionPerformed
        showNavPlaceholder("4. Manajemen Pengguna");
    }//GEN-LAST:event_btnMenu4ActionPerformed

    private void btnMenu5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenu5ActionPerformed
        showNavPlaceholder("5. Persetujuan & Laporan");
    }//GEN-LAST:event_btnMenu5ActionPerformed

    private void btnMenu6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenu6ActionPerformed
        showNavPlaceholder("6. Monitoring Transaksi");
    }//GEN-LAST:event_btnMenu6ActionPerformed

    private void btnMenu7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenu7ActionPerformed
        showNavPlaceholder("7. Verifikasi Pencairan");
    }//GEN-LAST:event_btnMenu7ActionPerformed

    private void btnMenu8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenu8ActionPerformed
        showNavPlaceholder("8. Pengaturan Komisi");
    }//GEN-LAST:event_btnMenu8ActionPerformed

    private void txtSearchBarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchBarActionPerformed
        applyFilter();
    }//GEN-LAST:event_txtSearchBarActionPerformed

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
        java.awt.EventQueue.invokeLater(() -> new FrmManajemenPengguna().setVisible(true));
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
    private javax.swing.JButton btnUbah;
    private javax.swing.JPanel cardKreator;
    private javax.swing.JPanel cardPembeli;
    private javax.swing.JPanel cardSuspended;
    private javax.swing.JPanel cardTotalUser;
    private javax.swing.JComboBox<String> cmbFilter;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAdmin;
    private javax.swing.JLabel lblHint;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblMenuTitle;
    private javax.swing.JLabel lblStat1Title;
    private javax.swing.JLabel lblStat1Val;
    private javax.swing.JLabel lblStat2Title;
    private javax.swing.JLabel lblStat2Val;
    private javax.swing.JLabel lblStat3Title;
    private javax.swing.JLabel lblStat3Val;
    private javax.swing.JLabel lblStat4Title;
    private javax.swing.JLabel lblStat4Val;
    private javax.swing.JLabel lblSubLogo;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JPanel pnlContent;
    private javax.swing.JPanel pnlHeader;
    private javax.swing.JPanel pnlSidebar;
    private javax.swing.JPanel pnlStats;
    private javax.swing.JPanel pnlTableCard;
    private javax.swing.JTable tblUser;
    private javax.swing.JTextField txtSearchBar;
    // End of variables declaration//GEN-END:variables
}
