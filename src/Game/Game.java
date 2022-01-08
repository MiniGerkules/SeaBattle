package Game;

import java.awt.*;
import java.util.*;
import java.util.List;
import javax.swing.*;

import Game.Objects.GameObject;
import Game.Objects.GameObjectsContainer;

/**
 * The main class of the game. Determines the main window and its rendering
 */
public class Game {
    public final int appSizeX, appSizeY;

    private final JFrame game = new JFrame(); // Основной экран приложения
    private final List<GameObject> allGameObjects = new ArrayList<>();
    private final GameObjectsContainer container;

    private Player player1;
    private Player player2;

    /**
     * Constructor of the Game class. Initializes the main frame of the game.
     */
    public Game() {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        appSizeX = dimension.width / 2;
        appSizeY = dimension.height / 2;
        int appBoundX = (dimension.width - appSizeX) / 2;
        int appBoundY = (dimension.height - appSizeY) / 2;

        game.setTitle("Sea Battle"); // Устанавливаем заголовок
        game.setBounds(appBoundX, appBoundY, appSizeX, appSizeY); // Устанавливаем границы и размеры
        game.setResizable(false); // Задаем возможность изменения размера
        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Задаем способ закрытия приложения

        int bias = 10;
        int contSizeX = appSizeX / 2 - bias*2;
        int contSizeY = appSizeY - bias*2;
        int contBoundX = (appSizeX - contSizeX) / 2;
        int contBoundY = (appSizeY - contSizeY) / 2;
        container = new GameObjectsContainer(contBoundX, contBoundY, contSizeX, contSizeY, new Color(64,224,208));

        drawPreStartMenu();
    }

    /**
     * The method renders the prelaunch menu
     */
    private void drawPreStartMenu() {
        game.setVisible(true);
    }

    /**
     * The method starts the game if all right
     */
    public void startGame() {

    }

    private void drawGameField() {
        game.setVisible(true);
    }
}
