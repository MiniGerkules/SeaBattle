package Game;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Shot {
    public final Rectangle2D shot;
    public final Color shotColor;

    public Shot(Rectangle2D shot, Color shotColor) {
        this.shot = shot;
        this.shotColor = shotColor;
    }
}
