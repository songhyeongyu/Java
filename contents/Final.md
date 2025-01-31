# final 변수와 상수
**`final` 키워드가 붙으면 값을 변경할 수 없다.**

~~~java
final int data1;
data1 = 10; //최초 한번만 할당 가능
data1 = 20; //컴파일 오류
//final 지역 변수2
final int data2 = 10; 
data2 = 20; //컴파일 오류
~~~

* final을 지역변수에 설정할 경우 한번만 할당 가능


### final - 필드

~~~java
 final int value;
      public ConstructInit(int value) {
          this.value = value;
}
~~~

~~~java
static final int CONST_VALUE = 10;
final int value = 10;
~~~

사용하는 이유

* 필드 초기화는 필드의 코드에 해당 값이 미리 정해짐
* 모든 인스턴스가 같은 값을 사용하기 땜에 메모리 낭비
* `static`을 사용해서 중복을 피한다.

* `final` 키워드를 사용해서 초기화 값이 변하지
않는다.
* `static` 영역은 단 하나만 존재하는 영역이다. 중복과 메모리 비효율 문제를 모두 해결할 수 있다.

#### 상수의 특징

* `static final`을 사용한다.
* 필드를 직접 접근해서 사용한다
    * 고정된 값 자체를 사용하는 것이 목적


~~~java
final Data data = new Data();
data = new Data();  //final 변경 불가 컴파일 오류
//참조 대상의 값은 변경 가능 
data.value = 10; 
System.out.println(data.value); 
data.value = 20; 
System.out.println(data.value);
~~~

* 참조형 변수에 `final`이 붙어있어서 다른 값으로 변경 못함(다른 객체로 참조 못함) 하지만 data.value는 `final`이 아니여서 변경 가능