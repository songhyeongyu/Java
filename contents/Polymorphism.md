# Polymorphism
객체지향 프로그래밍의 대표적 특징
1. 캡슐화
2. 상속
3. **`다형성`**

**다형성** : 다향한 형태

`2가지의 핵심 이론`
1. `다형적 참조`
2. `매서드 오버라이딩`

### 다형적 참조

**부모 타입의 변수가 자식 인스턴스 참조**

~~~java
class Parent {
      public void parentMethod() {
          System.out.println("Parent.parentMethod");
    }
}

 Child extends Parent {
      public void childMethod() {
        System.out.println("Child.childMethod");
    }
}
~~~

**`다형적 참조`: 부모는 자식을 품을 수 있다.**
❗️하지만 자식의 기능은 호출할 수 없다.
~~~java
//부모 변수가 부모 인스턴스 참조 
System.out.println("Parent -> Parent"); 
Parent parent = new Parent(); parent.parentMethod();
//자식 변수가 자식 인스턴스 참조 
System.out.println("Child -> Child"); 
Child child = new Child(); 
child.parentMethod(); 
child.childMethod();
//부모 변수가 자식 인스턴스 참조(다형적 참조) 
System.out.println("Parent -> Child"); 
Parent poly = new Child(); 
poly.parentMethod();
//Child child1 = new Parent(); 자식은 부모를 담을 수 없다.
//자식의 기능은 호출할 수 없다. 컴파일 오류 발생
//poly.childMethod();
~~~
~~자식은 부모를 담을 수 없다~~

### 다형적 참조와 인스턴스 실행

`poly`는 `Parent`타입 이기 때문에 `Parent`부터 필요한 method를 찾는다.
따라서 `child`에 있는 method는 사용하지 못한다.

* `Parent` 는 최상위 부모이다. 상속 관계는 부모로만 찾아서 올라갈 수 있다. `childMethod()` 는 자식 타입에 있으므로 호출할 수 없다. 따라서 컴파일 오류가 발생한다.

**다형적 참조의 핵심은 부모는 자식을 품을 수 있다는 것이다.**

## 다형성과 캐스팅

`Parent poly = new Child()` 와 같이 부모 타입의 변수를 사용하게 되면 `poly.childMethod()` 와 같이 자식 타입에 있는 기능은 호출할 수 없다.


부모는 자식을 담을 수 있지만 자식은 부모를 담을 수 없다.**

* `Parent parent = new Child()` : 부모는 자식을 담을 수 있다.
* `Parent parent = child //Child child 변수` : 부모는 자식을 담을 수 있다.

`casting`: 특정 타입으로 변경하는 것

~~~java
Child child = (Child) poly //다운캐스팅을 통해 부모타입을 자식 타입으로 변환한 다음에 대입 시도 
Child child = (Child) x001 //참조값을 읽은 다음 자식 타입으로 지정
Child child = x001 //최종 결과
~~~
캐스팅을 한다고 해서 poly의 타입이 변하는 것은 아니다. (child가 되는 것은 아니다.)

**캐스팅**

* upcasting : super로 변경
* downcasting : sub로 변경

#### 다운 캐스팅
~~~java
CastingMain2 {
      public static void main(String[] args) {
//부모 변수가 자식 인스턴스 참조(다형적 참조) 
Parent poly = new Child();
//단 자식의 기능은 호출할 수 없다. 컴파일 오류 발생 
//poly.childMethod();
//일시적 다운캐스팅 - 해당 메서드를 호출하는 순간만 다운캐스팅
((Child) poly).childMethod();
    }
}
~~~
#### 업캐스팅
~~~java
public static void main(String[] args) {
Child child = new Child();
Parent parent1 = (Parent) child; //업캐스팅은 생략 가능, 생략 권장 
Parent parent2 = child; //업캐스팅 생략
          parent1.parentMethod();
          parent2.parentMethod();
      }
~~~


**주의점**

~~~java
Parent parent1 = new Child();
Child child1 = (Child) parent1; child1.childMethod(); //문제 없음
Parent parent2 = new Parent();
Child child2 = (Child) parent2; //런타임 오류 - ClassCastException 
child2.childMethod(); //실행 불가
~~~

이유 : new parent()로 부모 타입 객체 생성 메모리 상에 자식 타입은 존재 x
이 상황에서 Child c = (Child) parent2를 해버리면 메모리에 child가 존재하지 않아서 child를 사용할 수 없다.

* `A a = new B()` :A로업케스팅
* `B b = new B()` :자신과같은타입
* `C c = new B()` :하위타입은 대입할수없음,**컴파일오류**
* `C c = (C) new B()` :하위타입으로강제다운캐스팅,하지만 B인스턴스에 C와 관련된 부분이 없으므로 잘못된 캐스팅, `ClassCastException` **런타임 오류 발생**


## Instanceof

**어떤 인스턴스를 참조하고 있는지 확인할 수 있다.**

~~~java

Parent parent2 = new Child(); System.out.println("parent2 호출"); call(parent2);

 private static void call(Parent parent) {
          parent.parentMethod();
    if (parent instanceof Child child) {
System.out.println("Child 인스턴스 맞음");
child.childMethod();
    }
}
~~~

~~~
parent2 호출 
Parent.parentMethod 
Child 인스턴스 맞음 
Child.childMethod
~~~


## 다형성과 메서드 오버라이딩

 **오버라이딩 된 메서드가 항상 우선권을 가진다**

~~~java
//자식 변수가 자식 인스턴스 참조
Child child = new Child(); System.out.println("Child -> Child"); System.out.println("value = " + child.value); 
child.method();
//부모 변수가 부모 인스턴스 참조
Parent parent = new Parent(); System.out.println("Parent -> Parent"); System.out.println("value = " + parent.value); parent.method();
//부모 변수가 자식 인스턴스 참조(다형적 참조)
Parent poly = new Child();
System.out.println("Parent -> Child"); System.out.println("value = " + poly.value); //변수는 오버라이딩X 
poly.method(); //메서드 오버라이딩!
~~~

**실행 결과**

~~~
Child -> Child
value = child
Child.method

Parent -> Parent
value = parent
Parent.method

Parent -> Child
value = parent
Child.method
~~~

* **`다형적 참조`**: 하나의 변수 타입으로 다양한 자식 인스턴스를 참조할 수 있는 기능 
* **`메서드 오버라이딩`**: 기존 기능을 하위 타입에서 새로운 기능으로 재정의
