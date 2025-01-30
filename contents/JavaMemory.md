# Java Memory Architecture

* `method`: 클래스 정보 보관
* `stack` : 메서드를 실행할 때 마다
* `heap`: 객체가 생성되는 영역



* 자바는 stack 영역을 사용해서 메서드 호출과 지역 변수를 관리한다.
* 메서드를 계속 호출하면 스택 프레임이 계속 쌓인다.
* 지역변수는 스택 영역에서 관리한다.
* 스택 프레임이 종료되면 지역 변수도 함께 제거된다. 
* 스택 프레임이 모두 제거되면 프로그램도 종료된다.
* 힙 영역 안에서만 인스턴스끼리 서로 참조하는 경우에도 GC의 대상이 된다.

## static 변수

~~~java
Data1 data1 = new Data1("A");
System.out.println("A count=" + data1.count);
Data1 data2 = new Data1("B");
System.out.println("B count=" + data2.count);
Data1 data3 = new Data1("C");
System.out.println("C count=" + data3.count);
~~~

**인스턴스를 생성하면 `count`값이 0으로 초기화가 된다.**

~~~java
 public String name;
public Data2(String name, Counter counter) {
this.name = name;
counter.count++; }
~~~
* 생성자에서 Counter 인스턴스를 추가로 전달 받는다
* 생성자가 호출되면 counter 인스턴스에 있는 count 변수의 값을 하나 증가시킨다.
**`Counter` 인스턴스를 공용으로 사용한 덕분에 객체를 생성할 때 마다 값을 정확하게 증가시킬 수 있다.**

~~`Data2` 클래스와 관련된 일인데 `Counter`라는 별도의 클래스를 추가 해야된다.~~


~~또한 생성자가 복잡해진다~~


~~~java
public String name;
public static int count;
public Data3(String name) {
        this.name = name;
        count++;
}

Data3 data1 = new Data3("A");
System.out.println("A count=" + Data3.count);
Data3 data2 = new Data3("B");
System.out.println("B count=" + Data3.count);
Data3 data3 = new Data3("C");
System.out.println("C count=" + Data3.count);
~~~

* `static int count` 부분을 보자 이렇게 static이 붙이게 되면 `정적 변수` 또는 `클래스 변수`라 한다.
* 객체가 생성되면 `count의 값을 하나 증가시킨다.
* `.`을 사용하여 접근하기 때문에 클래스에 직접 접근하는 것 같다.

* **`static`이 붙은 멤버 변수는 메서드 영역에서 관리한다.**
    * `count`는 인스턴스 영역에 생성되지 않는다.

### 정리
**`static` 변수는 쉽게 이야기해서 클래스인 붕어빵 틀이 특별히 관리하는 변수이다. 붕어빵 틀은 1개이므로 클래스 변
수도 하나만 존재한다. 반면에 인스턴스 변수는 붕어빵인 인스턴스의 수 만큼 존재한다.**

### 멤버 변수의 종류

* **인스턴스 변수** : `static`이 붙지 않은 멤버 변수
    * 인스턴스를 만들 때 마다 새로 만들어진다.

* **클래스 변수** : `static`이 붙은 멤버 변수는 인스턴스와 무관하게 클래스에 바로 접근 가능
    * 인스턴스와는 다르게 보통 여러곳에서 공유하는 목적으로 사용

* 지역변수 : 지역 변수는 스택 영역에 있는 스택 프레임 안에 보관. 메서드가 종료되면 스택 프레임도 제거 되는데 이때 해당 스택 프레임에 포함된 지역 변수도 제거
* 인스턴스 변수 : 힙 역역은 GC가 발생하기 전까지는 생존하기 때문에 지역변수보다 생존 주기가 길다
* 클래스 변수 : 클래스 변수는 메서드 영역에 생성. 클래스 변수는 해당 클래스가 JVM에 로딩 되는 순간 생성. JVM이 종료 될 때 없어진다.


## static method

~~~java
public class DecoUtil1 {
      public String deco(String str) {
          String result = "*" + str + "*";
          return result;
} 
}

public class DecoMain1 {
      public static void main(String[] args) {
          String s = "hello java";
          DecoUtil1 utils = new DecoUtil1();
          String deco = utils.deco(s);
          System.out.println("before: " + s);
          System.out.println("after: " + deco);
      }
}
~~~

`DecoUtil`의 인스턴스를 먼저 생성해야 되지만, `DecoUtil`은 단순히 기능만 제공하는 클래스이다.


~~~java
 public class DecoUtil2 {
      public static String deco(String str) {
          String result = "*" + str + "*";
          return result;
} 
}

public static void main(String[] args) {
    String s = "hello java";
    String deco = DecoUtil2.deco(s);
    System.out.println("before: " + s);
    System.out.println("after: " + deco);
    }
~~~

**인스턴스 생성 없이 클래스 명을 통해서 바로 호출할 수 있다.**


#### 인스턴스 메서드
`static`이 붙지 않은 메서드는 인스턴스를 생성해야 호출할 수 있다.

#### 클래스 메서드
메서드 앞에 `static`을 붙일 수 있다. 이것을 **정적 메서드**또는 **클래스 메서드**라 한다.
인스턴스 생성 없이 마치 클래스에 있는 메서드를 바로 호출하는 것 같기 때문이다.

~~하지만 언제나 사용할 수 있는 것은 아니다~~


**사용법**
* `static`메서드는 `static`만 사용할 수 있다.
    * `static`이 붙은 정적 메서드나 정적 변수만 사용가능
* 모든 곳에서 `static`을 호출 가능
    * 정적 메서드는 공용 기능이다. 
* **import static Class명을 통해서 메서드 명만 적어주면 된다.**
* `main()` 메서드는 프로그램을 시작하는데 `static method`이기 때문에 바로 작동
    * `static method`는 같은 클래스 내부에서 `static method`만 호출할 수 있다.
    * 따라서 main()메서드와 같은 클래스에서 호출하는 메서드도 정적 메거드로 선언해 사용한다.


    












