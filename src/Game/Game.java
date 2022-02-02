package Game;

import Game.Objects.Winner;

import java.awt.*;
import javax.swing.*;

/**
 * The main class of the game. Determines the main window and its rendering
 */
public class Game {
    public static final Color waterColor = Color.white;
    public static final Color hitColor = Color.RED;
    public static final Color missColor = Color.GRAY;
    public static final Color shipColor = Color.MAGENTA;

    private final JFrame game = new JFrame();
    private final Container mainContainer = game.getContentPane();

    private final Player player1 = new Player();
    private final Player player2 = new Player();

    private final Timer timer;
    private final MainGameLoop gameLogic = new MainGameLoop(player1, player2);

    public final int appSizeX, appSizeY;
    private final Font font = new Font("TimesRoman", Font.BOLD, 30);

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

        mainContainer.setLayout(new GridBagLayout()); // Выбираем контейнер
        mainContainer.setPreferredSize(new Dimension(appSizeX, appSizeY)); // Выставляем размеры без учета заголовка
        game.pack(); // Заставляем размеры быть применеными

        timer = new Timer(500, event2 -> {
            if (gameLogic.getWhoWin() != Winner.NONE) {
                if (gameLogic.getWhoWin() == Winner.PLAYER1)
                    JOptionPane.showMessageDialog(game, "Вы победили!");
                else
                    JOptionPane.showMessageDialog(game, "Вы проиграли!");

                endGame();
            }
        });
    }

    /**
     * The method renders the prelaunch menu
     */
    public void drawPreStartMenu() {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;

        JLabel label = new JLabel("МОРСКОЙ БОЙ!");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(font);
        mainContainer.add(label, constraints);

        JButton button = new JButton("Начать игру");
        button.setFont(font);
        constraints.gridx = 0;
        constraints.gridy = 1;
        mainContainer.add(button, constraints);

        game.setVisible(true);

        button.addActionListener(event1 -> {
            mainContainer.removeAll();
            mainContainer.repaint();

            startGame();
        });
    }

    private void startGame() {
        initGame();
        mainContainer.revalidate();

        gameLogic.start();
        timer.start();
    }

    private void endGame() {
        timer.stop();

        mainContainer.removeAll();
        player1.removeAll();
        player2.removeAll();

        int result = JOptionPane.showConfirmDialog(game, "Хотите еще раз сыграть?", "Морской бой",
                JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION)
            startGame();
    }

    private void initGame() {
        JLabel label = new JLabel("Ваша область синяя, вражеская -- красная");
        JPanel shipsPlace = new JPanel();
        JPanel fieldPl1 = new JPanel();
        JPanel fieldPl2 = new JPanel();
        addInMainContainerWithConstrains(label, shipsPlace, fieldPl1, fieldPl2);

        player1.setField(fieldPl1);
        player1.prepareGameField();
        player1.arrangeShipsAutomatically();

        player2.setField(fieldPl2);
        player2.prepareGameField();
        player2.arrangeShipsAutomatically();
    }

    private void addInMainContainerWithConstrains(JLabel label, JPanel panel1, JPanel panel2, JPanel panel3) {
        GridBagConstraints constraints = new GridBagConstraints();

        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(font);
        setConstrains(constraints, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 1,
                1, 0, 0, GridBagConstraints.REMAINDER);
        mainContainer.add(label, constraints);

        panel1.setBackground(Color.YELLOW);
        setConstrains(constraints, GridBagConstraints.BOTH, new Insets(5, 10, 10, 5), 1,
                10, 0, 1, 1);
        mainContainer.add(panel1, constraints);

        setConstrains(constraints, GridBagConstraints.BOTH, new Insets(5, 5, 10, 5), 2,
                10, 1, 1, 2);
        mainContainer.add(panel2, constraints);

        setConstrains(constraints, GridBagConstraints.BOTH, new Insets(5, 5, 10, 10), 2,
                10, 3, 1, 2);
        mainContainer.add(panel3, constraints);
    }

    private void setConstrains(GridBagConstraints constraints, int fill, Insets insets, double weightx,
                               double weighty, int gridx, int gridy, int gridwidth) {
        constraints.fill = fill;
        constraints.insets = insets;
        constraints.weightx = weightx;
        constraints.weighty = weighty;
        constraints.gridx = gridx;
        constraints.gridy = gridy;
        constraints.gridwidth = gridwidth;
        constraints.gridheight = 1;
    }
}
