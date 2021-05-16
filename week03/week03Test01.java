
class Person {
}

class Student extends Person {
}

class Animal {
}

public class week03Test01 {
    public static void main(String[] args) {
        Person p = new Person();
        Student s = new Student();
        Animal a = new Animal();

        System.out.println("p instanceof Person : " + (p instanceof Person));
        // p instanceof Person : true

        System.out.println("p instanceof Student : " + (p instanceof Student));
        // p instanceof Student : false

        // System.out.println("p instanceof Animal : " + (p instanceof Animal));
        // 오류

        System.out.println("s instanceof Person : " + (s instanceof Person));
        // s instanceof Person : true

        System.out.println("s instanceof Student : " + (s instanceof Student));
        // s instanceof Student : true

        // System.out.println("s instanceof Animal : " + (s instanceof Animal));
        // System.out.println("a instanceof Person : " + (a instanceof Person));
        // System.out.println("a instanceof Student : " + (a instanceof Student));
        // 오류

        System.out.println("a instanceof Animal : " + (a instanceof Animal));
        // a instanceof Animal : true
    }
}