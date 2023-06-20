package pl.psi;

import lombok.Data;
import lombok.Getter;
import lombok.Value;
import lombok.experimental.NonFinal;

/**
 * TODO: Describe this class (The first line - until the first dot - will interpret as the brief description).
 */
@Data
public class Point
{
    private final int x;
    private final int y;

    public Point( final int aX, final int aY )
    {
        x = aX;
        y = aY;
    }

    public double distance( Point aPoint )
    {
        return distance( aPoint.getX(), aPoint.getY() );
    }

    public double distance( double px, double py )
    {
        px -= getX();
        py -= getY();
        return Math.sqrt( px * px + py * py );
    }
}
