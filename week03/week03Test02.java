
class Person {
}

class Student extends Person {
}

public class week03Test02 {
    public static void main(String[] args) {
        Person p = new Person();
        Student s = new Student();
        // Person p1 = new Person();
        // Student s1;
        Person p2 = null;
        Student s2 = new Student();

        System.out.println("p instanceof Student : " + (p instanceof Student));
        // p instanceof Student : false
        // 부모 객체 p가 자식클래스 Student로 형변환이 안된다는 의미(다운캐스팅해야함)

        // s1 = p1;으로 하면 오류 발생

        System.out.println("s instanceof Person : " + (s instanceof Person));
        // s instanceof Person : true
        // 자식 객체 s가 부모클래스 Person으로 형변환이 가능하다라는 의미(업캐스팅 가능)

        p2 = s2;
        // p = (Person)s;
        // 자식 객체 s가 부모클래스 Person으로 형변환
    }
}
