package com.javarush.task.task35.task3513;

import javax.swing.*;
import java.awt.*;

public class View extends JPanel {

    private static final Color BG_COLOR = new Color(0xbbada0);
    private static final String FONT_NAME = "Arial";
    private static final int TILE_SIZE = 96;
    private static final int TILE_MARGIN = 12;

    private Controller controller;

    boolean isGameWon = false;
    boolean isGameLost = false;

    public View(Controller controller) {
        setFocusable(true);
        this.controller = controller;
        addKeyListener(controller);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(BG_COLOR);
        g.fillRect(0, 0, this.getSize().width, this.getSize().height);

        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                drawTile(g, controller.getGameTiles()[y][x], x, y);
            }
        }

        g.drawString("Score: " + controller.getScore(), 140, 465);

        if (isGameWon) {
            JOptionPane.showMessageDialog(this, "You've won!");
        } else if (isGameLost) {
            JOptionPane.showMessageDialog(this, "You've lost :(");
        }
    }

    private void drawTile(Graphics g2, Tile tile, int x, int y) {
        Graphics2D graphics2D = ((Graphics2D) g2);
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int tileValue = tile.value;
        int xOffset = offsetCoors(x);
        int yOffset = offsetCoors(y);
        graphics2D.setColor(tile.getTileColor());
        graphics2D.fillRoundRect(xOffset, yOffset, TILE_SIZE, TILE_SIZE, 8, 8);
        graphics2D.setColor(tile.getFontColor());
        final int size = tileValue < 100 ? 36 : tileValue < 1000 ? 32 : 24;
        final Font font = new Font(FONT_NAME, Font.BOLD, size);
        graphics2D.setFont(font);

        String valueOfTile = String.valueOf(tileValue);
        final FontMetrics fontMetrics = getFontMetrics(font);

        final int width = fontMetrics.stringWidth(valueOfTile);
        final int height = -(int) fontMetrics.getLineMetrics(valueOfTile, graphics2D).getBaselineOffsets()[2];

        if (tileValue != 0)
            graphics2D.drawString(valueOfTile, xOffset + (TILE_SIZE - width) / 2, yOffset + TILE_SIZE - (TILE_SIZE - height) / 2 - 2);
    }

    private static int offsetCoors(int arg) {
        return arg * (TILE_MARGIN + TILE_SIZE) + TILE_MARGIN;
    }
}
