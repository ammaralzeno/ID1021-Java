
public class Connection {
    private City city;
    private int distance;

    public Connection(City city, int distance) {
        this.city = city;
        this.distance = distance;
    }

    public City getCity() {
        return city;
    }

    public int getDistance() {
        return distance;
    }
}
