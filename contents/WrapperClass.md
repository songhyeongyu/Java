# Wrapper

### primitive type의 한계
**객체가 아니기 때문에 한계가 존재**

1. 객체지향 프로그래밍의 장점을 살릴 수 없다.
ex) 객체가 아니므로 유용한 메서드를 제공할 수 없다.

2. null값을 가질 수 없음

~~~java
public static void main(String[] args) {
         int value = 10;
         int i1 = compareTo(value, 5);
         int i2 = compareTo(value, 10);
         int i3 = compareTo(value, 20);
         System.out.println("i1 = " + i1);
         System.out.println("i2 = " + i2);
         System.out.println("i3 = " + i3);
}
     public static int compareTo(int value, int target) {
         if (value < target) {
             return -1;
         } else if (value > target) {
             return 1;
         } else {
return 0; }
}
~~~

비교하기 위해서 compareTo()라는 외부 메서드를 사용해야 된다.

**만약 `value`가 객체라면 자기 자신의 값과 다른 값을 비교하는 메서드를 만드는 것이 더 유용하다.**


### Wrapper Class

`int`는 클래스가 아니지만 `int`를 감싸는 것 처럼 보이게 만든다.

~~~java
private final int value;
    public MyInteger(int value) {
        this.value = value;
}
    public int getValue() {
        return value;
}
    public int compareTo(int target) {
        if (value < target) {
            return -1;
        } else if (value > target) {
            return 1;
        } else {
return 0; }
}
    @Override
    public String toString() {
return S;
    }
    MyInteger myInteger = new MyInteger(10);
    int i1 = myInteger.compareTo(5);
    int i2 = myInteger.compareTo(10);
    int i3 = myInteger.compareTo(20);
    System.out.println("i1 = " + i1);
    System.out.println("i2 = " + i2);
    System.out.println("i3 = " + i3);
~~~

* 자기 자신의 값을 외부의 값과 비교
* `MyInteger`는 객체이므로 자신이 가진 메서드 호출
* `int`는 기본형이기 떄문에 메서드 못가짐


**기본형과 null**

기본형은 항상 값을 가져아 된다 하지만 때로는 데이터가 null 상태가 필요할 수 있다.

~~~java

int[] intArr = {-1, 0, 1, 2, 3};
System.out.println(findValue(intArr, -1)); //-1
System.out.println(findValue(intArr, 0));
System.out.println(findValue(intArr, 1));
System.out.println(findValue(intArr, 100)); //-1
 private static int findValue(int[] intArr, int target) {
    for (int value : intArr) {
            if (value == target) {
                return value;
            }
}
return -1; }
~~~
* 찾는 값이 없으면 -1을 반환
* 하지만 배열에 -1이 존재하면 어떤 것인지 잘 모름

but 객체의 경우 `null`을 반환하면 된다.

~~~java
int[] intArr = {-1, 0, 1, 2, 3};
    System.out.println(findValue(intArr, -1));
    System.out.println(findValue(intArr, 0));
    System.out.println(findValue(intArr, 1));
    System.out.println(findValue(intArr, 100));

private static MyInteger findValue(MyInteger[] intArr, int target) {
         for (MyInteger myInteger : intArr) {
             if (myInteger.getValue() == target) {
                 return myInteger;
}
}
         return null;
     }
~~~

* 없는 값을 조회하면 `null`을 반환한다.

물론 `NullPointerException`이 발생할 수 있기 때문에 조심히 사용해야 된다.

**특징**

* 불변이다.
* `equals`로 비교해야 된다.


~~~java
Integer integerObj = Integer.valueOf(10); //-128 ~ 127 자주 사용하는 숫자 값
재사용, 불변
Long longObj = Long.valueOf(100);
Double doubleObj = Double.valueOf(10.5);
System.out.println("newInteger = " + newInteger);
System.out.println("integerObj = " + integerObj);
System.out.println("longObj = " + longObj);
System.out.println("doubleObj = " + doubleObj);
System.out.println("내부 값 읽기");
int intValue = integerObj.intValue(); System.out.println("intValue = " + intValue); 
long longValue = longObj.longValue(); System.out.println("longObj = " + longValue);
System.out.println("비교");
System.out.println("==: " + (newInteger == integerObj)); 
System.out.println("equals: " + newInteger.equals(integerObj));
~~~

**래퍼 클래스 생성**

* Integer.valueOf()를 사용하면된다.

**intValue()**

* wrapper 클래스에 있는 기본형을 다시 primitive로 바꿈

**equals()를 사용**

* `equals()`를 사용해야된다.

`Math` class 정리

1. 기본 연산 메서드 
    * `abs(x)` : 절대값 
    * `max(a, b)` : 최대값 
    * `min(a, b)` : 최소값
2. 지수 및 로그 연산 메서드 
    * `exp(x)` : e^x 계산 
    * `log(x)` : 자연 로그
    * `log10(x)` : 로그 10 
    * `pow(a, b)` :a의b제곱
3. 반올림 및 정밀도 메서드 
    * `ceil(x)` : 올림
    * `floor(x)` : 내림
    * `rint(x)` : 가장 가까운 정수로 반올림
    * `round(x)` : 반올림
4. 삼각 함수 메서드 
    * `sin(x)` : 사인
    * `cos(x)` : 코사인 
    * `tan(x)` : 탄젠트
5. 기타 유용한 메서드 
    * `sqrt(x)` : 제곱근
    * `cbrt(x)` : 세제곱근
    * `random()` : 0.0과 1.0 사이의 무작위 값 생성
