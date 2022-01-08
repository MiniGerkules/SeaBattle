package Game;

import javax.swing.*;
import java.awt.*;

/**
 * The main class of the game. Determines the main window and its rendering
 */
public class Game {
    private final JFrame game = new JFrame(); // Основной экран приложения
    private static final Toolkit toolkit = Toolkit.getDefaultToolkit();
    public static final int maxX = toolkit.getScreenSize().width;
    public static final int maxY = toolkit.getScreenSize().height;
    public static final int appSizeX = maxX / 2;
    public static final int appSizeY = maxY / 2;

    /**
     *
     */
    public Game() {
        int appBoundX = (maxX - appSizeX) / 2;
        int appBoundY = (maxY - appSizeY) / 2;

        game.setTitle("Sea Battle"); // Устанавливаем заголовок
        game.setBounds(appBoundX, appBoundY, appSizeX, appSizeY); // Устанавливаем границы и размеры
        game.setResizable(false);
        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Задаем способ закрытия приложения
    }

    public void drawGameField() {
        game.setVisible(true);
    }
}
