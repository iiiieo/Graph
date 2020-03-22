public class Main {
    public static void main(String[] args){
        Graph g = new Graph(4);
        g.insertNode("c1");
        g.insertNode("c2");
        g.insertNode("c3");
        g.insertNode("c4");
        g.insertPath("c1", "c3", 111);
        g.insertPath("c1", "c4", 255);
        g.insertPath("c2", "c3", 420);
        g.insertPath("c2", "c4", 123);
        g.insertPath("c3", "c4", 321);
        System.out.println(g.toString());

        g.searchShortestWay("c1", "c3");
        g.searchShortestWay("c1", "c2");
        g.searchShortestWay("c2", "c1");

    }

}
