package pl.psi;
import java.util.List;
import lombok.Getter;
import pl.psi.Hero;

public class Players {
    @Getter
    private final List< Hero > players;
    public Players(final List< Hero > aPlayers){
        players = aPlayers;
    }

}
