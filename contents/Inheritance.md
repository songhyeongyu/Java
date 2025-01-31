# Inheritance


## 상속
객체 지향 프로그래밍의 핵심 요소 중 하나로, 기존 클래스의 필드와 메서드를 새로운 클래스에서 재사용하게 해준다.


### 용어

* **부모(super class)** : 상속을 통해 자신의 필드와 메서드를 다른 클래스에 제공

* **자식(sub class)** : 부모 클래스로부터 필드와 메서드를 상속받음

~~~java
public class Car {
      public void move() {
System.out.println("차를 이동합니다."); }
}

public class ElectricCar extends Car {
public void charge() { System.out.println("충전합니다.");
} }


      public static void main(String[] args) {
          ElectricCar electricCar = new ElectricCar();
          electricCar.move();
          electricCar.charge();}

~~~
❗️`Car`가 부모 클래스
❗️`ElectricCar`가 자식 클래스
**상속 받은 클래스에서 super 클래스의 메서드 상속**


**`❗️단일 상속`** 만 가능하다


❗️메모리에 `ElectricCar`을 생성하면 `Car`까지 포함되서 인스턴스를 생성한다.

만약 메서드가 호출되면 `super`에서 찾을지 `sub`에서 찾을지 선택해야 된다. 이때는 **호출하는 변수의 클래스를 기준으로 선택**
만약 sub를 불렀으면 sub에서 찾고 super을 불렀으면 super에서 찾는다. 
하지만 sub 클래스에 동일한 method가 없으면 super로 가서 호출한다.


* 상속 관계의 객체를 생성하면 그 내부에는 부모와 자식이 모두 생성된다.
* 상속 관계의 객체를 호출할 때, 대상 타입을 정해야 한다. 이때 호출자의 타입을 통해 대상 타입을 찾는다.
* 현재 타입에서 기능을 찾지 못하면 상위 부모 타입으로 기능을 찾아서 실행한다. 기능을 찾지 못하면 컴파일 오류 가 발생한다.


### 상속과 오버라이딩

super class에 있는 method를 재정의 하고 싶을 수 있다.

**`Overriding`**: super에게 상속 받은 기능을 sub가 재정의 하는 것

`@Override` 애노테이션을 붙여서 사용해야 된다.

### 오버라이딩 조건

**super 메서드와 같은 메서드를 오버라이딩 할 수 있다.**

1. `메서드 이름`이 같아야됨
2. `메서드 매개변수` 타입, 순서, 개수가 같아야 된다.
3. 접근 제어가자 상위 클래스보다 더 `제한적이어선` 안된다.
4. `예외` super class의 메서드 보다 더 많은 `throw`를 선언할 수 없다.
5. 생성자 오버라이딩 불가
6. `static, final, private는 불가.


### suepr-부모참조

부모와 자식의 필드명이 같거나 메서드가 오버라이딩 되어 있으면 자식에서 부모의 필드나 메서드를 호출 할 수 없다.
하지만 `super`를 사용하면 부모 클래스 참조 가능

~~~java
 class Parent {
    public String value = "parent";
    public void hello() {
        System.out.println("Parent.hello");
}}
class Child extends Parent {
    public String value = "child";
 @Override
      public void hello() {
          System.out.println("Child.hello");
      }
public void call() {
System.out.println("this value = " + this.value); //this 생략 가능 
System.out.println("super value = " + super.value);
this.hello(); //this 생략 가능
super.hello();
      }
~~~

### super - 생성자

**❗️상속 관계를 사용하면 자식 클랳스의 생성자에서 부모 클래스의 생성자를 반드시 호출해야 된다.**

~~~java
class ClassA {
public ClassA() { System.out.println("ClassA 생성자");
}
}
public class ClassB extends ClassA {
    public ClassB(int a) {
super(); //기본 생성자 생략 가능 
System.out.println("ClassB 생성자 a="+a);
}
public ClassB(int a, int b) {
super(); //기본 생성자 생략 가능 System.out.println("ClassB 생성자 a="+a + " b=" + b);
}
}
ClassC extends ClassB {
    public ClassC() {
        super(10, 20);
System.out.println("ClassC 생성자"); }
}
~~~

`this`를 사용하더라도 `super`는 꼭 사용해야 된다.

~~~java
public ClassB(int a) {
this(a, 0); //기본 생성자 생략 가능 
System.out.println("ClassB 생성자 a=" + a);
}
public ClassB(int a, int b) {
super(); //기본 생성자 생략 가능 
System.out.println("ClassB 생성자 a=" + a + " b=" + b);
}
~~~

클래스에서 `final`이 사용되면 상속 안됨
메서드에서 `final`이 사용되면 상속 안됨