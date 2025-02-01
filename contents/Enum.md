## String type

고객은 3등급으로 나누고, 상품 구매시 등급별로 할인을 적용한다. 할인시 소수점 이하는 버린다. 
* `BASIC` 10% 할인
* `GOLD` 20% 할인 
* `DIAMOND` 30% 할인

~~~java
if (grade.equals("BASIC")) {
            discountPercent = 10;
        } else if (grade.equals("GOLD")) {
            discountPercent = 20;
        } else if (grade.equals("DIAMOND")) {
            discountPercent = 30;
} else {
System.out.println(grade + ": 할인X");
}

DiscountService discountService = new DiscountService();
    int basic = discountService.discount("BASIC", price);
    int gold = discountService.discount("GOLD", price);
    int diamond = discountService.discount("DIAMOND", price);
~~~
**오타가 발생하기 쉽고, 유효하지 않는 값이 입력될 수 있다.**

**String 타입 안정성 부족 문제**

* 타입 안정성 부족: 오타가 쉽고, 유효하지 않은 값이 입력
* 데이터 일관성: GOLD,gold,Gold등 다양한 형식으로 입력
* 값의 제한 부족: String으로 상태나 카테고리를 표현하면, 잘못된 문자열을 실수로 입력
* 컴파일 시 오류 감지 불가: 잘못된 값은 컴파일 시에는 감지 되지 않는다.

~~~java
public static final String BASIC = "BASIC";
public static final String GOLD = "GOLD";
public static final String DIAMOND = "DIAMOND";
~~~

**실수로 상수의 이름을 잘못 입력하면 컴파일 시점에 오류가 발생**


# Enum Type
열거형 패턴을 쉽게 사용할 수 있도록 프로그래밍 언어에서 지원

~~~java
public enum Grade {
     BASIC, GOLD, DIAMOND
}
~~~

* 열거형을 정의할 때는 `class` 대신에 `enum`을 사용
* 원하는 상수의 이름을 나열하면 된다.
* `enum`은 열거형 내부에서 상수로 지정하는 거 못한다.

**`ENUM`의 장점**

1. 타입 안정성 향상 : 유효하지 않은 값이 입력될 가능성이 없다.
2. 간결성 및 일관성 : 간결해지고 명확해지며, 데이터의 일관성이 보장
3. 확장성 : 새로운 회원 등급을 추가하고 싶을때 새로운 상수를 추가하기만 하면 된다.


**주요 메서드**

**ENUM - 주요 메서드**
* **values()**: 모든 ENUM 상수를 포함하는 배열을 반환한다.
* **valueOf(String name)**: 주어진 이름과 일치하는 ENUM 상수를 반환한다.
* **name()**: ENUM 상수의 이름을 문자열로 반환한다.
* **toString()**: ENUM 상수의 이름을 문자열로 반환한다. `name()` 메서드와 유사하지만, `toString()` 은 직접 오버라이드 할 수 있다.


### enum - 사용법

~~~java
enum Grade {
     BASIC(10), GOLD(20), DIAMOND(30);
     private final int discountPercent;
     Grade(int discountPercent) {
         this.discountPercent = discountPercent;
}
     public int getDiscountPercent() {
         return discountPercent;
    }
}
~~~

* `BASIC(10)` 과 같이 상수 마지막에 괄호를 열고 생성자에 맞는 인수를 전달하면 적절한 생성자가 호출된다.

* `discountPercent` 필드를 추가하고, 생성자를 통해서 필드에 값을 저장한다.

* 값을 조회하기 위해 `getDiscountPercent()` 메서드를 추가했다. 열거형도 클래스이므로 메서드를 추가할 수 있다.