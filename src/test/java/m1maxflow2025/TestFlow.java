package m1maxflow2025;

public class TestFlow {

    public static void main(String[] args) {
        FlowNetwork fn = new FlowNetwork("flowtest.gv");
        System.out.println(fn.toDotString());
        System.out.println("////////////////////////////////////////");
        System.out.println("                The Max Flow            ");
        System.out.println("////////////////////////////////////////");
        fn.fordFulkerson();
        System.out.println(fn.toDotString());
    }
}
