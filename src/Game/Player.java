package Game;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

import Game.Objects.*;
import Game.Objects.Ships.*;

public class Player {
    private final List<Ship> ships;
    private JPanel field;
    private final List<List<FieldPart>> fieldParts;
    private volatile boolean enemyCanShootAgain;
    private volatile CountDownLatch canContinueGame;

    public CountDownLatch getCanContinueGame() {
        return canContinueGame;
    }

    public void setField(JPanel field) {
        this.field = field;
    }
    public boolean isEnemyCanShootAgain() {
        return enemyCanShootAgain;
    }

    public Player() {
        ships = new ArrayList<>(10);
        fieldParts = new ArrayList<>(10);
        enemyCanShootAgain = false;
    }

    public boolean canPlay() {
        return ships.size() != 0;
    }

    public void prepareGameField() {
        field.setLayout(new GridLayout(10, 10));
        for (int i = 0; i < 10; ++i) {
            fieldParts.add(new ArrayList<>(10));
            for (int j = 0; j < 10; ++j) {
                FieldPart part = new FieldPart(i, j, this::shootAtMe);
                field.add(part);
                fieldParts.get(i).add(part);
            }
        }
    }

    public void setEnableMyField(boolean toSet) {
        for (List<FieldPart> listParts : fieldParts)
            for (FieldPart part : listParts)
                part.setEnabled(toSet);
    }

    public void setVisibleForShips(boolean visible) {
        for (Ship ship : ships)
            ship.show(visible);

        field.repaint();
    }

    public void shootAtMe(ActionEvent event) {
        FieldPart part = (FieldPart) event.getSource();
        fieldParts.get(part.getRow()).remove(part);

        for (Ship ship : ships) {
            switch (ship.isShipWasHit(part)) {
                case Miss -> {}
                case Hit -> {
                    enemyWasHit(part, "???? ????????????! ???? ???????????? ???????????????? ?????? ??????!");
                    return;
                }
                case Destroy -> {
                    enemyWasHit(part, "?????????????? ??????????????????! ???? ???????????? ???????????????? ?????? ??????!");
                    enemyWasDestroyed(ship);
                    return;
                }
            }
        }

        part.itWasWater();
        JOptionPane.showMessageDialog(field, "???? ????????????????????????! ?????? ?????????????????? ?????????????? ????????????!");

        enemyCanShootAgain = false;
        canContinueGame.countDown();
    }

    private void enemyWasHit(FieldPart part, String message) {
        part.itWasShipPart();
        JOptionPane.showMessageDialog(field, message);

        enemyCanShootAgain = true;
        canContinueGame.countDown();
    }

    private void enemyWasDestroyed(Ship ship) {
        ships.remove(ship);
        ship.shipWasDestroyed();
        for (FieldPart part : ship.getFieldsNearby())
            fieldParts.get(part.getRow()).remove(part);
    }

    public void arrangeShips(JPanel ships) {
        throw new RuntimeException("Not ready!");
    }

    public void arrangeShipsAutomatically() {
        ShipCreator creator = new ShipCreator();
        List<Integer> shipSizes = Arrays.asList(4, 3, 3, 2, 2, 2, 1, 1, 1, 1);

        for (int shipSize : shipSizes) {
            Ship ship = creator.createShip(fieldParts, shipSize);
            ships.add(ship);
        }
    }

    public void makeMove() {
        canContinueGame = new CountDownLatch(1);
        try {
            canContinueGame.await();
        } catch (InterruptedException ignored) {
        }
    }

    public void removeAll() {
        field.removeAll();
        ships.clear();
        fieldParts.clear();
    }
}
