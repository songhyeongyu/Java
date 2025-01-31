# 공유 참조와 사이드 이펙트

~~~java
Address a = new Address("서울"); Address b = a;
        System.out.println("a = " + a);
        System.out.println("b = " + b);
        b.setValue("부산"); //b의 값을 부산으로 변경해야함 
        System.out.println("부산 -> b"); 
        System.out.println("a = " + a); //사이드 이펙트 발생 
        System.out.println("b = " + b);
~~~

같은 객체를 참조하고 있어서 a값도 부산으로 변경된다. 

**❗️해결 방안으로는 처음부터 서로 다른 인스턴스를 참조해야된다.**


# Immutable Object

**객체의 상태가 변하지 않는 객체**

**위에서 발생한 문제 상황의 근본적인 원인은 공유된 객체의 값을 변경하는 것에 있다.**

`Address` 클래스를 불변 클래스로 바꾼다.

~~~java
class ImmutableAddress {
     private final String value;
     public ImmutableAddress(String value) {
         this.value = value;
}public String getValue() {
    return value;
}
@Override
public String toString() {
    return "Address{" +
            "value='" + value + '\'' +
'}';}
}
~~~
1. 내부 값이 변경되면 안된다. -> value를 final
2. setValue()를 제거
3. 생성자를 통해서만 값을 설정 이후에는 변경 불가

**불변이라는 단순한 제약으로 사이드 이펙트 문제를 막을 수 있다.**

1. 객체의 공유 참조는 막을 수 없다. -> 불변 객체를 만든다.
2. 불변 객체는 값을 변경할 수 없다. -> 새로운 객체를 만든다.


## Immutable Object - value change

불변 객체를 사용하지만 그래도 값을 변경해야 된다면??


~~~java
class MutableObj {

private int value;
 public MutableObj(int value) {
         this.value = value;
}
     public void add(int addValue) {
         value = value + addValue;
}
     public int getValue() {
         return value;
}
     public void setValue(int value) {
         this.value = value;
}
}

 class MutableMain {
public static void main(String[] args) { MutableObj obj = new MutableObj(10); obj.add(20);
//계산 이후 기존 값은 사라짐
System.out.println("obj = " + obj.getValue());
}
 }
~~~

`mutable`하게 코드를 구현했다.


이번에는 `immutable`하게 해보겠다.

~~~java

     private final int value;
     public ImmutableObj(int value) {
         this.value = value;
}
     public ImmutableObj add(int addValue) {
         int result = value + addValue;
         return new ImmutableObj(result);
}
     public int getValue() {
         return value;
}

ImmutableObj obj1 = new ImmutableObj(10);
        ImmutableObj obj2 = obj1.add(20);
//계산 이후에도 기존값과 신규값 모두 확인 가능
 System.out.println("obj1 = " + obj1.getValue()); 
 System.out.println("obj2 = " + obj2.getValue());
~~~

덧셈을 하면 새로운 객체를 반환 시킨다.

**❗️만약 반환을 하지 않으면 아무것도 처리되지 않은 것 처럼 보인다**

