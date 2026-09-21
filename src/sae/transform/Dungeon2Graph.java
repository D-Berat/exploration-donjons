package sae.transform;

import sae.dungeon.Dungeon;
import sae.dungeon.Room;
import sae.dungeon.Direction;
import sae.dungeon.DungeonSoluce;
import sae.graph.Graph;
import sae.graph.Node;
import sae.graph.GraphSoluce;

import java.util.Map;
import java.util.HashMap;
import java.util.List;

public class Dungeon2Graph {

    private Graph graph;
    private Map<Room, Node> map;
    private Map<Node, Room> reverseMap;

    public Dungeon2Graph(Dungeon dungeon) {
        this.graph = new Graph();
        this.map = new HashMap<>();
        this.reverseMap = new HashMap<>();

        for (Room r : dungeon.getRooms()) {
            Node n = new Node(r.getName(), r.getCoords());
            this.map.put(r, n);
            this.reverseMap.put(n, r);
            this.graph.addNode(n);
        }

        for (Room sourceRoom : this.map.keySet()) {
            Node sourceNode = this.map.get(sourceRoom);
            for (Room targetRoom : sourceRoom.getNextRooms().values()) {
                Node targetNode = this.map.get(targetRoom);
                this.graph.addEdge(sourceNode, targetNode);
            }
        }
    }

    public DungeonSoluce transform(GraphSoluce graphSoluce) {
        DungeonSoluce res = new DungeonSoluce();
        List<Node> nodes = graphSoluce.getSoluce();

        for (int i = 0; i < nodes.size() - 1; i++) {
            Node current = nodes.get(i);
            Node next = nodes.get(i + 1);

            Room r1 = this.reverseMap.get(current);
            Room r2 = this.reverseMap.get(next);

            for (Map.Entry<Direction, Room> entry : r1.getNextRooms().entrySet()) {
                if (entry.getValue().equals(r2)) {
                    res.addDirection(entry.getKey());
                    break;
                }
            }
        }
        return res;
    }

    public Node mappedNode(Room room) {
        return this.map.get(room);
    }

    public Graph getGraph() {
        return this.graph;
    }
}