package gof.behavioral.visitor;

import java.util.List;

/** Draws one symbol per object. The same type checks as in {@link ScoreCalculator}, written again. */
public class MinimapRenderer {

    public String render(List<MapObject> objects) {
        StringBuilder map = new StringBuilder();
        for (MapObject object : objects) {
            if (object instanceof Monster) {
                map.append('M');
            } else if (object instanceof Chest) {
                map.append('$');
            } else if (object instanceof Trap) {
                map.append('^');
            } else {
                map.append('?');
            }
        }
        return map.toString();
    }
}
