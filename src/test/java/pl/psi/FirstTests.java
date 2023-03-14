package pl.psi;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * TODO: Describe this class (The first line - until the first dot - will interpret as the brief description).
 */
public class FirstTests {

    @Test
    void test1(){
        Point p1 = new Point(1, 1);
        Point p2 = new Point(1, 1);

        assertThat(p1).isEqualTo(p2);
        assertThat(p1.equals(p2)).isTrue();

        assertThat(p1 == p2).isFalse();

        Segment s1 = new Segment(p1, p2);
        Segment s2 = new Segment(p1, p2);
        assertThat(s1).isEqualTo(s2);
//        s1.getStartPoint().setX(4);
//        assertThat(s1).isNotEqualTo(s2);


        Set<Point> set = new HashSet<>();
        set.add(p1);
        p1.setX(2);
        set.add(p1);
        set.add(p2);

//        assertThat(set).hasSize(1);
    }
}
