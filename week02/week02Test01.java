package week02;

public class week02Test01 {
    static int staticVariable = 10; // class 변수
    int instanceVariable = 20; // instance 변수

    public void testMethod(int localVariable1) { // local 변수
        int localVariable2 = 40; // local 변수

        System.out.println("localVariable1 : " + localVariable1);
        System.out.println("localVariable2 : " + localVariable2);
    }

    public static void main(String[] args) {
        System.out.println("staticVariable : " + staticVariable);

        week02Test01 w = new week02Test01();
        System.out.println("instanceVariable : " + w.instanceVariable);

        w.testMethod(30);
    }
}
