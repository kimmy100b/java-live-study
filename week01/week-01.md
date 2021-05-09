# 💎JVM은 무엇이며 자바 코드는 어떻게 실행하는 것인가.

# 목표

자바 소스 파일(.java)을 JVM으로 실행하는 과정 이해하기.

# 목차

- [JVM이란 무엇인가](#JVM이란-무엇인가)
- [JVM 구성 요소](#JVM-구성-요소)
- [JRE와 JDK의 차이](#JRE와-JDK의-차이)
- [컴파일 하는 방법](#컴파일-하는-방법)
- [실행하는 방법](#실행하는-방법)
- [바이트코드란 무엇인가](#바이트코드란-무엇인가)
- [JIT 컴파일러란 무엇이며 어떻게 동작하는지](#JIT-컴파일러란-무엇이며-어떻게-동작하는지)

------

# JVM이란 무엇인가

JVM은 'Java Virtual Machine'을 줄인 것으로 '자바를 실행하기 위한 가상 기계'이다. Machine은 영어권에서 컴퓨터를 machine이라고도 부르기 때문에 '자바를 실행하기 위한 가상 컴퓨터'라고 해석할 수 있다.<br>

자바로 작성된 애플리케이션은 모두 이 가상 컴퓨터(JVM)에서만 실행되기 때문에, 자바 애플리케이션이 실행되기 위해서는 반드시 JVM이 필요하다. 일반 애플리케이션의 코드는 OS만 거치고 하드웨어로 전달되지만, Java 애플리케이션은 JVM을 거친 후 OS를 거치기 때문에, 그리고 하드웨어에 맞게 완전히 컴파일된 상태가 아니고 실행 시에 해석(interpret)되기 때문에 속도가 느리다는 단점이 있다. 최근에는 바이트코드를 하드웨어의 기계로 바로 변환해주는 JIT 컴파일러와 향상된 기술이 적용되어 속도의 차이를 크게 줄였다. <br>

앞서 언급했던 부분에 대해 더 자세히 다루려고 한다. 일반 애플리케이션은 OS와 바로 맞붙어 있어서 OS에 종속적이다. 그래서 다른 OS에서 실행시키기 위해서는 애플리케이션을 그 OS에 맞게 변경해줘야 한다. 반면에 Java 애플리케이션은 OS와의 사이에 JVM이 있어서 JVM하고만 상호작용을 한다. 그래서 OS와 하드웨어에 독립적이라 다른 OS에서도 프로그램의 변경 없이 실행할 수 있다. (단, JVM이 OS에 종속적이기 때문에 해당 OS에서 실행 가능한 JVM이 필요하다) 이렇게 함으로써 자바의 중요한 장점 중의 하나인 "Write once, run anywhere"가 가능하게 된다.<br>

# JVM 구성 요소

JVM은 크게 클래스 로더 시스템, 메모리, 실행 엔진 그리고 네이티브 메서드로 나눠져 있다.<br>

- **1. Class Loader(클래스 로더)**<br>

클래스 로더는 컴파일된 바이트코드들을 읽어 연결한 뒤 메모리에 저장하는 역할을 한다. 내부적으로는 로딩, 링크, 초기화의 단계가 존재하고 있다. 초기화 단계에서 전역 변수를 메모리에 할당하기 때문에 필요 이상으로 전역 변수를 남용할 경우 메모리 이슈를 겪을 수 있기에 유의해야 한다.<br>

- **2. Run-time Data Area(메모리)**<br>

메모리는 런타임 시 필요한 메모리 영역이다. 스택, 힙, PC레지스터, 메소드, 네이티브 메서드 스택이 있다.<br>

- **3. Execution Engine(실행엔진)**<br>

실행엔진은 클래스 로더에 의해 실행에 필요한 준비 과정이 완료된 후 인터프리터나 JIT 컴파일러를 통해 바이트코드를 번역하여 실행하는 역할을 한다. 실행 엔진의 내부적으로는 인터프리터, JIT 컴파일러, GC(Garbage Collection)가 있다.

# JRE와 JDK의 차이

JRE와 JDK의 차이점을 알기 전에 각 개념에 대해 간단히 알아보자

## JRE(Java Runtime Environment)

> JVM + 자바 클래스 라이브러리

JRE는 자바실행환경으로 JVM과 클래스 라이브러리(Java API)를 포함하고 있다. 클래스 라이브러리로 인해 자바로 개발된 class를 실행 및 운영할 수 있으며 JVM로 인해 자바로 작성된 응용프로그램이 실행되기 위한 최소 환경을 만들 수 있다.

## JDK(Java Development Kit)

> JRE + 개발에 필요한 실행파일

JDK는 자바개발도구로 JRE와 개발에 필요한 실행파일(javac.exe 등)을 포함하고 있다.<br>

그러므로 JDK를 설치하면 JVM과 자바 클래스 라이브러리가 함께 설치되고 그 외에 자바를 개발하는데 필요한 프로그램들이 설치된다. (실행파일은 JDK의 bin디렉토리에 저장된다.)

- javac.exe : 자바 컴파일러, 자바소스코드를 바이트코드로 컴파일한다.

- java.exe : 자바 인터프리터, 컴파일러가 생성한 바이트코드를 해석하고 실행한다.

- javap.exe : 역어셈블러, 컴파일된 클래스파일을 원래의 소스로 변환한다.

- javadoc.exe : 자동문서생성기, 소스파일에 있는 주석(/\*\* \*\*/)을 이용하여 Java API문서와 같은 형식의 문서를 자동으로 생성한다.

- jar.exe : 압축프로그램, 클래스파일과 프로그램의 실행에 관련된 파일을 하나의 jar파일(.jar)로 압축하거나 압축해지한다.

## JRE vs JDK

- 자바 언어로 프로그램을 개발하기 위해서는 JDK를 설치
- 자바 언어로 작성된 프로그램을 실행하기 위해서는 JRE를 설치
- 운영할 수 있는 환경인가? 개발에 필요한 환경인가?

- 운영이면 JRE, 개발이면 JDK가 필요하다.

- JDK를 설치하면 JRE가 포함되어 같이 설치

# 컴파일 하는 방법

> javac <options> <source files>

`javac`를 사용하여 컴파일해준다. 컴파일해주면 해당 파일이 `.java`파일에서 `.class`파일로 변환된 것을 확인할 수 있다.

    javac의 option
    - `-classpath(-cp)` : 컴파일러가 컴파일할 때 필요로하는 라이브러리나 클래스들의 경로를 지정해주는 옵션
    - `-d` : 클래스 파일을 생성할 루트 디렉토리 설정
    - `-encoding` : 소스 파일에 사용된 문자열 설정
    - `-g` : 디버깅 정보옵션
    - `nowarn` : 경고 메시지 관련 옵션. 이 옵션을 사용할 경우 경고 메시지를 생성하지 않음

# 실행하는 방법

1. 프로그램 실행

2. JVM이 OS로 부터 메모리를 할당 받고, 용도에 따라 나누어 관리

3. 자바 컴파일러(javac)가 소스 코드(_*.java)를 읽어 바이트 코드로 변환(*_.class)

4. JVM의 Class Loader가 변환된 파일(\*\*.class)들을 로드

5. Execution engine(실행 엔진) 은 로딩된 class 파일을 해석

6. 해석된 파일들은 Runtime Data Area(할당 메모리)에 배치되고 실질적 수행이 이루어짐

## Java 프로그램을 실행해보기(실습)

1. Hello.java파일을 만들어준다.

```java

public class Hello{

public static void main(String[] args){

System.out.println("Hello World!");

}

}

```

2. cmd창을 열어 Hello.java가 있는 폴더로 이동(파일 확인)

```

D:\workspace\java>dir

D 드라이브의 볼륨: 드라이브

D:\workspace\java 디렉터리

2020-11-15  오후 02:10    <DIR>          .

2020-11-15  오후 02:10    <DIR>          ..

2020-11-15  오후 02:09               119 Hello.java

1개 파일                 119 바이트

2개 디렉터리  56,440,229,888 바이트 남음

```

3. Hello.java를 **컴파일**해주기(javac는 컴파일러)<br>

컴파일하고 나면 Hello.class가 생긴다.

```

D:\workspace\java>javac Hello.java

D:\workspace\java>dir

D 드라이브의 볼륨: 드라이브

D:\workspace\java 디렉터리

2020-11-15  오후 02:10    <DIR>          .

2020-11-15  오후 02:10    <DIR>          ..

2020-11-15  오후 02:12               416 Hello.class

2020-11-15  오후 02:09               119 Hello.java

2개 파일                 535 바이트

2개 디렉터리  56,440,229,888 바이트 남음

```

4. Hello클래스를 **실행**시키기

```

D:\workspace\java>java Hello

Hello World!

```

## Java 버전에 따른 실행

💡 Java 14버전으로 컴파일 된 파일을 Java 8버전으로 실행을 한다면 어떻게 될까?<br>

📢 실행이 안된다.상위 버전의 바이트코드는 하위 버전의 자바에서 실행할 수 없다.

💡 Java 8버전으로 컴파일 된 파일을 Java 14버전으로 실행을 한다면 어떻게 될까?<br>

📢 실행이 된다.

💡 그러면 상위 버전의 바이트코드는 하위 버전의 자바에서 실행을 전혀 할 수 없을까?<br>

📢 Java Compiler version option을 주게 될 경우 가능하다.`C\:>javac -source 1.6 -target 1.6 -bootclasspath C:\jdk1.6.0\lib\rt.jar -extdirs "" OldCode.java` 이런 식으로

# 바이트코드란 무엇인가

> JVM이 이해할 수 있는 기계어

JVM은 바이트코드를 해당 OS의 기계어로 변환하여 OS로 전달함

# JIT 컴파일러란 무엇이며 어떻게 동작하는지

JIT 컴파일은 'Just-In-Time Compilation'의 약어로 정적 컴파일 방식과 인터프리터가 합쳐진 방식이다. 그래서 프로그램을 실제 실행하는 시점에(실시간으로) 기계어로 변환된 코드를 캐시에 저장한다. 이후 컴파일할 때는 변경된 부분만 컴파일하고 나머지는 캐싱된 코드를 사용한다. 이때, 기계어 변환은 코드가 실행되는 과정에서 실시간으로 일어나기 때문에 'Just-In-Time'이라는 단어가 붙었다. JIT 컴파일러는 JRE 안에 존재한다.<br><br>
장점 : 일반적인 인터프리터 언어에 비해 훨씬 좋은 성능을 낸다. 심지어 경우에 따라 정적 컴파일러 언어보다 좋은 성능을 낼 수도 있다.

# 참고

- [자바 스터디 1주차](https://github.com/whiteship/live-study/issues/1)
- [Java Compile version option](https://stackoverflow.com/questions/15492948/javac-source-and-target-options)
- [Javac Option](http://www.cs.yorku.ca/tech/other/java/docs/tooldocs/solaris/javac.html)
- [Javac Option 명령어/옵션](https://team621.tistory.com/38)
- 박명철, 코드를 통해 본 빵형의 실전Java, 남기람북스
