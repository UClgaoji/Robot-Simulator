package application;

import javafx.scene.shape.Path;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class CollisionDetectors {

    public static boolean RectangleIntersection(Rectangle shape, Rectangle world) {
        Path result = (Path) Shape.intersect(shape, world);
        return !result.getElements().isEmpty();
    }
    
    public static boolean PolylineIntersection(Rectangle shape, Polyline world) {
        Path result = (Path) Shape.intersect(shape, world);
        return !result.getElements().isEmpty();
    }
    
}
