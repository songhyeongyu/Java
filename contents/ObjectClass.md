# Object Class

자바의 모든 클래스의 최상위 부모 클래스는 `Object`클래스이다.

* 클래스에 상속 받을 부모 클래스가 없으면 묵시적으로 Object 클래스 상속
    * 자바가 extends Object를 넣어줌



~~~java
Child child = new Child();
child.childMethod();
child.parentMethod();
// toString()은 Object 클래스의 메서드 
String string = child.toString(); System.out.println(string);
~~~

`toString()`은 `Object`클래스의 메서드이다.

**자바에서 모든 객체의 최종 부모는 `Object`이다.**


## Object 클래스가 최상위 부모 클래스인 이유

* 공통 기능 제공 - 프로그래밍이 단순화 + 일관성 가짐
* 다형성의 기본 구현 - 타입이 다른 객체들을 어딘가에 보관해야 된다면 `Object`에 보관


## Object 다형성
**`Object`는 모든 객체를 참조할 수 있다.**

~~~java
public static void main(String[] args) {
         Dog dog = new Dog();
         Car car = new Car();
         action(dog);
         action(car);
     }
private static void action(Object obj) {
     //obj.sound(); //컴파일 오류, Object는 sound()가 없다. 
     //obj.move(); //컴파일 오류, Object는 move()가 없다.
//객체에 맞는 다운캐스팅 필요
if (obj instanceof Dog dog) {
             dog.sound();
         } else if (obj instanceof Car car) {
             car.move();
         }
}
~~~


`Object` 는 모든 타입의 부모다. 부모는 자식을 담을 수 있으므로 앞의 코드를 다음과 같이 변경해도 된다. 
~~~java
 Object dog = new Dog(); //Dog -> Object
 Object car = new Car(); //Car -> Object
 ~~~

**Object의 한계**
~~~java
private static void action(Object obj) {
obj.sound(); //컴파일 오류, Object는 sound()가 없다. 
}
~~~

### Object 배열

~~~java
Dog dog = new Dog();
Car car = new Car();
Object object = new Object(); //Object 인스턴스도 만들 수 있다.
Object[] objects = {dog, car, object};  size(objects);

private static void size(Object[] objects) { 
    System.out.println("전달된 객체의 수는: " + objects.length);
}
~~~

### toString()

**`toStrig()`** 메서드는 객체의 정보를 문자열 형태로 제공한다. -> Object 클래스여서 모든 클래스에서 상속받아 사용 ⭕️


### toStirng() 오버라이딩
**toString()을 오버라이딩해서 유용한 정보 제공**

~~~java
@Override
public String toString() {
    return "Dog{" +
            "dogName='" + dogName + '\'' +
", age=" + age +
'}';}
~~~


### Object와 OCP

만약 Object가 제공하는 toString()이 없다면 객체의 정보를 출력하기 어렵다
**BadObjectPrinter** 
~~~java
public class BadObjectPrinter {
public static void print(Car car) { //Car 전용 메서드
String string = "객체 정보 출력: " + car.carInfo(); //carInfo() 메서드 만듬
System.out.println(string);
     }
public static void print(Dog dog) { //Dog 전용 메서드
String string = "객체 정보 출력: " + dog.dogInfo(); //dogInfo() 메서드 만듬 
System.out.println(string);
} 
}
~~~
`BadObjectPrinter`는 구체적인 Car,Dog에 의존한다. 클래스가 늘어나면 늘어날수록 계속 늘어나게 된다.

하지만
**Object로 인해 추상적인 것에 의존**
~~~java
public class ObjectPrinter {
     public static void print(Object obj) {
String string = "객체 정보 출력: " + obj.toString();
         System.out.println(string);
     }
}
~~~
`ObjectPrinter`클레스가 `Object`에 의존한다.

구조를 보면

1. **다형적 참조** : Object 타입을 매개변수로 사용해서 Car,Dog 인스턴스를 포함한 세상의 모든 객체 인스턴스를 인수로 받을 수 있다.

2. **메서드 오버라이딩** : `Object`는 모든 클래스의 부모여서 Dog,Car와 같은 구체적인 것에 의존을 하지 않고 추상적인 `Object`에 의존하여 런타임에 인스턴스의 toString()을 호출


**OCP 원칙**
기본편에서 학습한 OCP 원칙을 떠올려보자.
1. **Open**: 새로운 클래스를 추가하고, `toString()` 을 오버라이딩해서 기능을 확장할 수 있다.
2. **Closed**: 새로운 클래스를 추가해도 `Object` 와 `toString()` 을 사용하는 클라이언트 코드인 `ObjectPrinter` 는 변경하지 않아도 된다.

### euqals()

* 동일성(identity) : == 연산자를 사용해 참조가 동일한 객체를 가리키고 있는지

* 동등성(Eqaulity) : `equals()` 메서드를 사용하여 두 객체가 논리적으로 동등한지

