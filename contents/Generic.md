# Generic


~~~java
public class IntegerBox {
     private Integer value;

       public void set(Integer value) {
        this.value = value;
}
    public Integer get() {
        return value;
} }
public class StringBox {
    private String value;
    public void set(String object) {
        this.value = object;
}
    public String get() {
        return value;
} }
~~~

문자열과 숫자를 보관하고 꺼낼 수 있는 단순한 기능을 제공한다.

**문제**

IntegerBox를 생성해 integer을 꺼내고 StringBox를 꺼내 String을 꺼낸 다음에 출력해야 되면 모든 타입에 대해서 Box를 만들어야 된다.

## 다형성으로 해결 시도

~~~java
public class ObjectBox {
     private Object value;
     public void set(Object object) {
         this.value = object;
}
     public Object get() {
         return value;
}
}
~~~

모든 타입의 부모인 `Object`타입을 통해 작성했다. 따라서 모든 타입을 사용할 수 있다.


**문제**

~~~java
integerBox.set("문자100");
Integer result = (Integer) integerBox.get()
~~~

IntegerBox로 생성했지만 문자열을 넣었다. 컴파일 오류가 안나고 ClassCastException이 발생하게 된다.

이걸 개발한 사람의 목적은 변수 이름과 같은 숫자 타입이 입력되기를 기대했다. 하지만 자바 언어 입장에서는 아무런 문제가 되지 않아 컴파일 오류가 발생하지 않는다.


**`정리`**
1. 코드 재사용성 : o
2. 타입 안정성 : x

## 제네릭 적용

제네릭을 사용하면 재사용성, 안정성을 모두 잡을 수 있다.

사용법은 다음과 같다

~~~java
package generic.ex1;
 public class GenericBox<T> {
     private T value;
     public void set(T value) {
         this.value = value;
}
     public T get() {
         return value;
} }
~~~
1. <>를 사용한 클래스를 제네릭 클래스라 한다.
2. Integer, String과 같은 타입을 미리 결정하지 않는다.
3. T를 타입 매개변수라 한다.
4. 클래스 내부에 T 타입이 필요하면 T와 같이 타입 매개변수를 적어두면 된다.

~~~java

integerBox.set(10);

GenericBox<String> stringBox = new GenericBox<String>(); stringBox.set("hello"); // String 타입만 허용
String str = stringBox.get(); // String 타입만 반환 System.out.println("str = " + str);
//원하는 모든 타입 사용 가능
GenericBox<Double> doubleBox = new GenericBox<Double>(); doubleBox.set(10.5);
Double doubleValue = doubleBox.get(); System.out.println("doubleValue = " + doubleValue);
//타입 추론: 생성하는 제네릭 타입 생략 가능
         GenericBox<Integer> integerBox2 = new GenericBox<>();
~~~
**integerBox.set("문자100");** 이런 코드를 작성하게 된다면 컴파일 오류가 난다.

**`정리`**

1. 원하는 모든 타입 사용 가능
2. 타입 추론
~~~java
GenericBox<Integer> integerBox = new GenericBox<Integer>() // 타입 직접 입력 
GenericBox<Integer> integerBox2 = new GenericBox<>() 
~~~
이런 식으로 선언하면 자바 스스로 타입 정보를 추론해서 개발자가 타입 정보를 생략할 수 있다.


1. 코드 재사용성 : o
2. 타입 안정성 : x

## 제네릭 용어와 관례
**`Generic`은 사용할 타입을 미리 결정하지 않는다는 점이다.**

1. 메서드는 매개변수에 인자를 전달해서 사용할 값을 결정한다.
2. 제네릭 클래스는 타입 매개변수에 타입 인자를 전달해서 사용할 타입을 결정한다.

**용어 정리**

* 제네릭 : 범용적인
* 제네릭 타입 : 제니릭 클래스, 인터페이스를 합쳐서 제네릭 타입이라고 한다. ex) GenericBox<T>
* 타입 매개변수 : 실제 타입으로 대체
* 타입 인자 : 제공되는 실제 타입


