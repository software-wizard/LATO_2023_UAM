package pl.psi;

import lombok.Getter;
import lombok.ToString;

import java.util.Objects;

/**
 * TODO: Describe this class (The first line - until the first dot - will interpret as the brief description).
 */
@Getter
@ToString
public class Segment
{
    private final Point endPoint;
    private final Point startPoint;

    public Segment( Point aP1, Point aP2 )
    {
        startPoint = new Point( aP1 );
        endPoint = aP2;
    }

    @Override
    public boolean equals( Object aO )
    {
        if( this == aO )
            return true;
        if( !(aO instanceof Segment segment) )
            return false;

        if( !Objects.equals( endPoint, segment.endPoint ) )
            return false;
        return Objects.equals( startPoint, segment.startPoint );
    }

    @Override
    public int hashCode()
    {
        int result = endPoint != null ? endPoint.hashCode() : 0;
        result = 31 * result + (startPoint != null ? startPoint.hashCode() : 0);
        return result;
    }
}
