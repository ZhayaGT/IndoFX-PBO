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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.plaf.basic.BasicButtonUI;

public class RoundedButton extends JButton {
    private final int radius;
    private final Color borderColor;
    private Color hoverBorderColor;
    private int hoverBorderThickness = 2;
    private Color hoverBackground;
    private boolean hovered = false;

    public RoundedButton(int radius) {
        this(radius, null);
    }

    public RoundedButton(int radius, Color borderColor) {
        this.radius = radius;
        this.borderColor = borderColor;
        setUI(new BasicButtonUI());
        setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusPainted(false);
        setOpaque(false);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                hovered = true;
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                hovered = false;
                repaint();
            }
        });
    }

    public void setHoverBorder(Color color, int thickness) {
        this.hoverBorderColor = color;
        this.hoverBorderThickness = thickness;
    }

    public void setHoverBackground(Color color) {
        this.hoverBackground = color;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        Color activeBg = (hovered && hoverBackground != null) ? hoverBackground : getBackground();
        Color activeBorder = (hovered && hoverBorderColor != null) ? hoverBorderColor : borderColor;
        int thickness = (hovered && hoverBorderColor != null) ? hoverBorderThickness : 1;

        int pad = thickness / 2;
        int w = getWidth() - 1 - (pad * 2);
        int h = getHeight() - 1 - (pad * 2);

        // Gambar latar tombol melengkung
        g2.setColor(activeBg);
        g2.fillRoundRect(pad, pad, w, h, radius, radius);

        // Gambar border normal atau border hover
        if (activeBorder != null) {
            g2.setColor(activeBorder);
            g2.setStroke(new BasicStroke(thickness));
            g2.drawRoundRect(pad, pad, w, h, radius, radius);
        }

        g2.dispose();
        super.paintComponent(g);
    }
}