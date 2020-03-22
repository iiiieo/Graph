public class Graph {

    private int nodesAmount;
    private Node[] nodes;
    private int[][] matrix;
    private boolean[] visited;
    private int[] distance;
    private int[] lastNodeIndex;

    public Graph(int nodeAmount) {
        this.nodes = new Node[nodeAmount];
        this.matrix = new int[nodeAmount][nodeAmount];
        this.nodesAmount = 0;
    }

    public int getNodeIndexByNode(Node node) {
        int index = -1;
        for (int i = 0; i < nodesAmount; i++) {
            if (nodes[i].equals(node)) {
                index = i;
            }
        }
        return index;
    }

    public int getNodeIndex(String nodeName) {
        int index = -1;
        for (int i = 0; i < nodesAmount; i++) {
            if (nodes[i].getName().equals(nodeName)) {
                index = i;
            }
        }
        return index;
    }

    public void insertNode(String nodeName) {
        if (nodesAmount < nodes.length) {
            int nodeAlreadyExisting = getNodeIndex(nodeName);
            if (nodeAlreadyExisting == -1 && !nodeName.isBlank()) {
                Node createdNode = new Node(nodeName);
                nodes[nodesAmount] = createdNode;
                matrix[nodesAmount][nodesAmount] = 0;
                nodesAmount++;
            } else {
                System.out.println("Invalid node name!");
            }
        } else {
            System.out.println("Too many nodes!");
        }
    }

    public void insertPath(String place1, String place2, int length) {
        int indexPlace1 = getNodeIndex(place1);
        int indexPlace2 = getNodeIndex(place2);
        matrix[indexPlace1][indexPlace2] = length;
        matrix[indexPlace2][indexPlace1] = length;
    }

    @Override
    public String toString() {
        String output = "Node amount : " + nodesAmount + "\nMax Nodes : " + nodes.length + "\n\nMatrix :";

        for (int j = 0; j < nodesAmount; j++) {
            output += "\n | ";
            for (int i = 0; i < nodesAmount; i++) {
                output += matrix[j][i] + " \t";
            }
        }

        return output;
    }

    public void depthFirstSearch(String startNodeName) {
        int startIndex = getNodeIndex(startNodeName);
        if (startIndex != -1) {
            visited = new boolean[nodesAmount];
            visitNode(startIndex);
        } else {
            System.out.println("Node not available");
        }
    }

    private void visitNode(int indexNode) {
        visited[indexNode] = true;
        System.out.println("Visited node : " + nodes[indexNode].getName());
        for (int indexTargetNode = 0; indexTargetNode < nodesAmount; indexTargetNode++) {
            if (matrix[indexNode][indexTargetNode] > 0 && !visited[indexTargetNode]) {
                visitNode(indexTargetNode);
            }
        }
    }

    /*
     *
     * Muss nicht gemacht werden
     * Vgl Buch Seite 118-122
     * neue Variablen sind hier, weil man sie für den Rest nicht braucht
     *
     */

    public void searchShortestWayBruteForce(String startNodeName, String endNodeName) {
        int indexStart = getNodeIndex(startNodeName);
        int indexEnd = getNodeIndex(endNodeName);

        if (indexStart != -1 && indexEnd != -1 && indexStart != indexEnd) {
            visited = new boolean[nodesAmount];
            runPath(indexStart, indexEnd, startNodeName, 0);
        }
    }

    private void runPath(int indexNode, int indexEnd, String path, int length) {
        int currentLength;
        String currentPath;

        visited[indexNode] = true;

        if (indexNode == indexEnd) {
            System.out.println("End reached : " + path + " : " + length);
        } else {
            for (int indexTargetNode = 0; indexTargetNode < nodesAmount; indexTargetNode++) {
                if (matrix[indexNode][indexTargetNode] > 0 && !visited[indexTargetNode]) {
                    currentPath = path + " - " + nodes[indexTargetNode].getName();
                    currentLength = length + matrix[indexNode][indexTargetNode];
                    runPath(indexTargetNode, indexEnd, currentPath, currentLength);
                }
            }
        }
        visited[indexNode] = false;
    }

    private int getClosestNode() {
        int indexOfClosestNode = -1;
        int minimalDistance = Integer.MAX_VALUE;
        for (int targetNode = 0; targetNode < nodesAmount; targetNode++) {
            if (!visited[targetNode] && distance[targetNode] < minimalDistance) {
                minimalDistance = distance[targetNode];
                indexOfClosestNode = targetNode;
            }
        }
        return indexOfClosestNode;
    }

    //Greedy-Algorithm by E. W. Dijkstra
    public void searchShortestWay(String startNodeName, String endNodeName) {
        int indexStart = getNodeIndex(startNodeName);
        int indexEnd = getNodeIndex(endNodeName);
        int indexActiveNode;
        int currentDistance;

        visited = new boolean[nodesAmount];
        distance = new int[nodesAmount];
        lastNodeIndex = new int[nodesAmount];
        for (int i = 0; i < nodesAmount; i++) {
            distance[i] = Integer.MAX_VALUE;
        }

        distance[indexStart] = 0;
        lastNodeIndex[indexStart] = indexStart;

        for (int i = 1; i < nodesAmount; i++) {
            indexActiveNode = getClosestNode();
            visited[indexActiveNode] = true;

            for (int targetNode = 0; targetNode < nodesAmount; targetNode++) {
                int distanceToTargetNode = matrix[indexActiveNode][targetNode];

                if (distanceToTargetNode > 0 && !visited[targetNode]) {
                    currentDistance = distance[indexActiveNode] + distanceToTargetNode;

                    if (currentDistance < distance[targetNode]) {
                        System.out.println(currentDistance);
                        distance[targetNode] = currentDistance;
                        lastNodeIndex[targetNode] = indexActiveNode;
                    }
                }
            }

        }

        String shortestPath = endNodeName;
        indexActiveNode = indexEnd;
        while (indexActiveNode != indexStart) {
            indexActiveNode = lastNodeIndex[indexActiveNode];
            shortestPath = nodes[indexActiveNode].getName() + " - " + shortestPath;
        }
        System.out.println("Shortest Path : " + shortestPath + " - Distance : " + distance[indexEnd]);

    }
}