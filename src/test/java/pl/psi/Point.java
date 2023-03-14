package pl.psi;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

/**
 * TODO: Describe this class (The first line - until the first dot - will interpret as the brief description).
 */
@ToString
@Setter
public class Point {
    private int x;
    private int y;

    public Point(int aX, int aY) {
        x = aX;
        y = aY;
    }

    public Point(Point aP1) {
        this(aP1.x, aP1.y);
    }

    @Override
    public boolean equals(Object aO) {
        if (this == aO) return true;
        if (aO == null || getClass() != aO.getClass()) return false;
        Point point = (Point) aO;
        return x == point.x && y == point.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
