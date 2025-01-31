# Apply Poly

~~~java
class Animal{
public void sound() {
System.out.println("동물 울음 소리");
}
}

class Dog extends Animal {
@Override
public void sound() { 
    System.out.println("멍멍");
    }
}

class Cat extends Animal {
@Override
public void sound() { 
    System.out.println("냐옹");
    }
}

public class Caw extends Animal{
@Override
public void sound() {
    System.out.println("음매");
    }
}

private static void soundAnimal(Animal animal) {
System.out.println("동물 소리 테스트 시작"); animal.sound();
System.out.println("동물 소리 테스트 종료");
}
~~~

* `다형적 참조` 덕분에 animal 변수는 Dog, Cat, Caw의 인스턴스를 참조할 수 있다.

* `메서드 오버라이딩` 덕분에 animal.sound()를 호출해도 각각의 인스턴스의 메서드를 호출할 수 있다.

~~~java

Animal[] animalArr = {dog, cat, caw};
//변하지 않는 부분
for (Animal animal : animalArr) {
System.out.println("동물 소리 테스트 시작"); animal.sound();
System.out.println("동물 소리 테스트 종료");}
~~~

* 다형적 참조로 animal들의 sub 클래스를 맞출 수 있다.


하지만 여기서 2가지의 문제가 생긴다.

1. Animal을 생성할 수 있는 문제
2. sound()를 오버라이딩 하지 않을 가능성

이러한 문제를 해결해 보자


# abstract class

**실제로 생성되면 안되는 클래스(인스턴스가 존재하지 않는다.)**

1. **추상 메서드가 하나라도 있는 클래스는 추상 클래스로 선언해야 한다.**

2. **추상 메서드는 상속 받는 자식 클래스가 반드시 오버라이딩 해서 사용해야 한다.**



~~~java

class AbstractAnimal {
public abstract void sound();

public void move() { System.out.println("동물이 움직입니다.");
    }
}
//추상클래스 생성 불가
//AbstractAnimal animal = new AbstractAnimal();
Dog dog = new Dog();
Cat cat = new Cat();
Caw caw = new Caw();
cat.sound();
cat.move();
soundAnimal(cat);
soundAnimal(dog);
soundAnimal(caw);

//동물이 추가 되어도 변하지 않는 코드
private static void soundAnimal(AbstractAnimal animal) {
System.out.println("동물 소리 테스트 시작"); 
animal.sound();
System.out.println("동물 소리 테스트 종료");
}
~~~


**추상 메서드는 반드시 오버라이딩 해야 한다.**

❗️위의 두 가지 문제를 모두 해결했다.

### 순수 추상 클래스

**모든 메서드가 추상 메서드인 클래스**

~~~java
public abstract void sound();
public abstract void move();
~~~
코드를 실행할 바디 부분이 전혀 없다.

특징

**1. 인스턴스 생성 ❌**

**2. 상속시 자식은 모든 메서드를 오버라이딩**

**3. 주로 polymorphism을 위해 사용**

## Interface

순수 추상 클래스를 조금 더 편리하게 사용할 수 있다.


특징 
**1. 인스턴스 생성 ❌**

**2. 상속시 자식은 모든 메서드를 오버라이딩**

**3. 주로 polymorphism을 위해 사용**

**4. 인터페이스의 메서드는 모두 `public`, `abstract`이다.**

**5. 메서드에 `public abstract` 를 생략할 수 있다. 참고로 생략이 권장된다.**

**6. 인터페이스는 다중 구현(다중 상속)을 지원한다.**


~~~java

public interface InterfaceAnimal {
    void sound();
    void move();
}

Dog implements InterfaceAnimal {
@Override
public void sound() { 
    System.out.println("멍멍");
}

@Override
public void move() {
    System.out.println("개 이동"); 
}
}
~~~

**클래스, 추상 클래스, 인터페이스는 모두 똑같다.**

모두 메모리 구조상 똑같다. 모두 자바에서 .class로 다뤄진다.

**인터페이스를 사용해야 하는 이유**

* 제약
* 다중 구현

~~~java

public interface InterfaceA {
    void methodA();
    void methodCommon();
}

public interface InterfaceB {
    void methodB();
    void methodCommon();
}

public class Child implements InterfaceA, InterfaceB {
    @Override
    public void methodA() {
        System.out.println("Child.methodA");
}
    @Override
    public void methodB() {
        System.out.println("Child.methodB");
    }
    @Override
    public void methodCommon() {
        System.out.println("Child.methodCommon");
    }
}
~~~
**methodCommon**은 양쪽에 다 있지만 같은 메서드이므로 구현은 하나만 하면 된다.
