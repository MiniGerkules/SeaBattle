package Game;

import Game.Objects.Winner;

public class MainGameLoop extends Thread {
    private Winner whoWin;
    private final Player player1;
    private final Player player2;

    public Winner getWhoWin() {
        return whoWin;
    }

    public MainGameLoop(Player player1, Player player2) {
        whoWin = Winner.NONE;
        this.player1 = player1;
        this.player2 = player2;
    }

    @Override
    public void run() {
        setName("Game main loop");
        Player currentMover = player1;
        currentMover.setEnableMyField(false);
        currentMover.setVisibleForShips(true);

        Player currentEnemy = player2;
        currentEnemy.setEnableMyField(true);

        while (player1.canPlay() && player2.canPlay()) {
            currentEnemy.makeMove();

            if (!currentEnemy.isEnemyCanShootAgain()) {
                Player temp = currentMover;
                currentMover = currentEnemy;
                currentEnemy = temp;

                currentMover.setEnableMyField(false);
                currentMover.setVisibleForShips(true);
                currentEnemy.setEnableMyField(true);
                currentEnemy.setVisibleForShips(false);
            }
        }

        if (player1.canPlay())
            whoWin = Winner.PLAYER1;
        else
            whoWin = Winner.PLAYER2;
    }
}
