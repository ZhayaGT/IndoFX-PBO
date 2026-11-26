/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package indofx.admin;

/**
 *
 * @author ROG
 */

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

public class RoundedPanel extends JPanel {
    private final int radius;
    private final Color borderColor;

    public RoundedPanel(int radius, Color borderColor) {
        this.radius = radius;
        this.borderColor = borderColor;
        setOpaque(false); // Wajib false agar sudut luar tidak tertutup kotak putih
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int w = getWidth() - 1;
        int h = getHeight() - 1;

        // Gambar isi panel melengkung
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, w, h, radius, radius);

        // Gambar garis tepi melengkung
        if (borderColor != null) {
            g2.setColor(borderColor);
            g2.setStroke(new BasicStroke(1));
            g2.drawRoundRect(0, 0, w, h, radius, radius);
        }
        g2.dispose();
    }
}