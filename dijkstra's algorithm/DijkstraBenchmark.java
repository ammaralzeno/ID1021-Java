import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

public class DijkstraBenchmark {

    private ArrayList<City> cities = new ArrayList<>();

    private class City {
        public String name;
        public Integer id;
        public ArrayList<Connection> neighbours = new ArrayList<>();

        public City(String name, Integer id) {
            this.name = name;
            this.id = id;
        }
    }

    private class Connection {
        private City city;
        private Integer dist;

        public Connection(City city, Integer dist) {
            this.city = city;
            this.dist = dist;
        }
    }

    private class Path implements Comparable<Path> {
        private City city;
        private City prev;
        private Integer dist;
        private Integer index;

        public Path(City city, City prev, Integer dist) {
            this.city = city;
            this.prev = prev;
            this.dist = dist;
        }

        @Override
        public int compareTo(Path other) {
            return this.dist.compareTo(other.dist);
        }
    }

    public DijkstraBenchmark(String file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                City from = lookup(parts[0]);
                City to = lookup(parts[1]);
                int dist = Integer.parseInt(parts[2]);
                from.neighbours.add(new Connection(to, dist));
                to.neighbours.add(new Connection(from, dist));
            }
        } catch (Exception e) {
            System.out.println("Error reading CSV file: " + e.getMessage());
        }
    }

    private City lookup(String name) {
        int hash = hash(name);
        if (hash >= cities.size() || cities.get(hash) == null) {
            while (cities.size() <= hash) {
                cities.add(null);
            }
            cities.set(hash, new City(name, hash));
        }
        return cities.get(hash);
    }

    private int hash(String name) {
        int mod = 541;
        int hash = 0;
        for (int i = 0; i < name.length(); i++) {
            hash = (hash * 31 % mod) + name.charAt(i);
        }
        return hash % mod;
    }

    public Integer shortestPath(String fromName, String toName) {
        City from = lookup(fromName);
        City to = lookup(toName);
        ArrayList<Path> done = new ArrayList<>(Collections.nCopies(cities.size(), null));
        PriorityQueue<Path> queue = new PriorityQueue<>();
        queue.add(new Path(from, null, 0));

        while (!queue.isEmpty()) {
            Path path = queue.poll();
            if (done.get(path.city.id) != null) continue;
            done.set(path.city.id, path);
            if (path.city == to) return path.dist;

            for (Connection conn : path.city.neighbours) {
                if (done.get(conn.city.id) == null) {
                    queue.add(new Path(conn.city, path.city, path.dist + conn.dist));
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("Usage: DijkstraBenchmark <path_to_csv> <from_city> <to_city>");
            return;
        }

        DijkstraBenchmark benchmark = new DijkstraBenchmark(args[0]);
        long t0 = System.nanoTime();
        Integer dist = benchmark.shortestPath(args[1], args[2]);
        long time = (System.nanoTime() - t0) / 1_000;
        System.out.println("Shortest path: " + dist + " min (" + time + " us)");
    }
}
