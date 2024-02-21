import java.util.ArrayList;
import java.util.List;

public class City {
    private String name;
    private List<Connection> connections;

    public City(String name) {
        this.name = name;
        this.connections = new ArrayList<>();
    }

    public void connect(City nxt, int dst) {
        connections.add(new Connection(nxt, dst));
    }

    public String getName() {
        return name;
    }

    public List<Connection> getConnections() {
        return connections;
    }
}
