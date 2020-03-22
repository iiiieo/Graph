public class Main {
    public static void main(String[] args){
        Graph g = new Graph(4);
        g.insertNode("c1");
        g.insertNode("c2");
        g.insertNode("c3");
        g.insertNode("c4");
        g.insertPath("c1", "c2", 150);
        g.insertPath("c1", "c3", 100);
        g.insertPath("c2", "c3", 100);
        g.insertPath("c2", "c4", 100);
        g.insertPath("c3", "c4", 100);
        System.out.println(g.toString());

        g.searchShortestWay("c4", "c1");

    }

}
