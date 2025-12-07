package m1maxflow2025;

public class TestFlow {

    public static void main(String[] args) {
        FlowNetwork fn = new FlowNetwork("flowtest.gv");
        System.out.println("//////////////////////////////////////////////");
        System.out.println("       The Maximum Flow Subject Example       ");
        System.out.println("//////////////////////////////////////////////\n");
        fn.fordFulkerson();
        fn = new FlowNetwork("maxFlowTest_STLabels.gv");
        System.out.println("/////////////////////////////////////////////");
        System.out.println("  Another Maximum Flow Example (with Label)  ");
        System.out.println("/////////////////////////////////////////////\n");
        fn.fordFulkerson();
        fn = new FlowNetwork("maxFlowTest_STNames.gv");
        System.out.println("////////////////////////////////////////////////");
        System.out.println("  Another Maximum Flow Example (without Label)  ");
        System.out.println("////////////////////////////////////////////////\n");
        fn.fordFulkerson();
    }
}
