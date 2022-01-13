package Game;

import java.awt.*;
import java.util.*;
import java.util.List;
import javax.swing.*;

import Game.Objects.GameObject;
import Game.Objects.GameField;

/**
 * The main class of the game. Determines the main window and its rendering
 */
public class Game {
    private final JFrame game = new JFrame(); // Основной экран приложения
    private final Container mainContainer = game.getContentPane(); // Главный контейнер приложения
    private final JPanel gameFields = new JPanel(); // Контейнер, в который будем складывать поля игроков

    private final List<GameObject> allGameObjects = new ArrayList<>();
    private final GameField player1Field;
    private final GameField player2Field;

    private Player player1;
    private Player player2;

    public final int appSizeX, appSizeY;

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
        game.setLocation(appBoundX, appBoundY); // Задаем расположение главного окна
        game.setResizable(false); // Задаем возможность изменения размера
        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Задаем способ закрытия приложения

        mainContainer.setLayout(new BorderLayout()); // Выбираем контейнер
        mainContainer.setPreferredSize(new Dimension(appSizeX, appSizeY)); // Выставляем размеры без учета заголовка
        mainContainer.add(gameFields, BorderLayout.CENTER);
        gameFields.setLayout(new GridLayout(1, 2));
        game.pack(); // Заставляем размеры быть применеными

        int bias = 10;
        int gridSizeX = appSizeX/2 - bias*2;
        int gridSizeY = appSizeY - bias*2;
        player1Field = new GameField(10, bias, bias, gridSizeX, gridSizeY, new Color(64,224,208));
        player2Field = new GameField(10, bias, bias, gridSizeX, gridSizeY, new Color(250, 128, 114));

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
        gameFields.add(player1Field);
        gameFields.add(player2Field);

//        gameFields.remove(player1Field);
//        gameFields.remove(player2Field);
//        gameFields.repaint();
    }
}
