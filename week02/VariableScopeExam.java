package week02;

public class VariableScopeExam {
    int globalScope = 10;
    static int staticValue = 30;

    public void scopeTest(int value) {
        int localScope = 20;
        System.out.println(globalScope);
        System.out.println(localScope);
        System.out.println(value);
    }

    public static void main(String[] args) {
        // System.out.println(globalScope); // 오류
        // System.out.println(localScope); // 오류
        // System.out.println(value); // 오류
        System.out.println(staticValue); // 30

        VariableScopeExam v = new VariableScopeExam();
        System.out.println(v.globalScope); // 10
        // v.scopeTest(5);

        /* 전역변수와 static변수의 값을 변경해보기 */
        VariableScopeExam v1 = new VariableScopeExam();
        VariableScopeExam v2 = new VariableScopeExam();
        v1.globalScope = 40;
        v2.globalScope = 50;
        System.out.println(v1.globalScope); // 40
        System.out.println(v2.globalScope); // 50

        v1.staticValue = 60;
        v2.staticValue = 70;
        System.out.println(v1.staticValue); // 70
        System.out.println(v2.staticValue); // 70
    }
}