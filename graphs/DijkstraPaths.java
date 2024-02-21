import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class DijkstraPaths {
    
    private City startCity;
    private Map<City, Integer> distances;
    private Map<City, City> predecessors;

    public DijkstraPaths() {
        this.distances = new HashMap<>();
        this.predecessors = new HashMap<>();
    }

    private void initialize(City startCity) {
        this.startCity = startCity;
        for (City city : distances.keySet()) {
            distances.put(city, Integer.MAX_VALUE);
            predecessors.put(city, null);
        }
        distances.put(startCity, 0);
    }

    public Integer shortestPath(City startCity, City targetCity) {
        initialize(startCity);
        PriorityQueue<City> priorityQueue = new PriorityQueue<>(
            (city1, city2) -> distances.get(city1) - distances.get(city2)
        );

        priorityQueue.add(startCity);

        while (!priorityQueue.isEmpty()) {
            City currentCity = priorityQueue.poll();

            for (Connection connection : currentCity.getConnections()) {
                City neighborCity = connection.getCity();
                int newDist = distances.get(currentCity) + connection.getDistance();

                if (newDist < distances.getOrDefault(neighborCity, Integer.MAX_VALUE)) {
                    distances.put(neighborCity, newDist);
                    predecessors.put(neighborCity, currentCity);

                    // Update the priority of the neighbor in the priority queue
                    priorityQueue.remove(neighborCity);
                    priorityQueue.add(neighborCity);
                }
            }
        }

        return distances.get(targetCity);
    }

    public static void main(String[] args) {
        Map map = new Map("trains.csv");
        String from = args[0];
        String to = args[1];
        DijkstraPaths dijkstra = new DijkstraPaths();
        long t0 = System.nanoTime();
        Integer dist = dijkstra.shortestPath(map.lookup(from), map.lookup(to));
        long time = (System.nanoTime() - t0) / 1_000_000;
        System.out.println("Shortest path: " + dist + " min (" + time + " ms)");
    }
}
