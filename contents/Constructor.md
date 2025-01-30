# Constructor

**객체를 생성하는 시점에 어떤 작업을 하고 싶다면 생성자(constructor)을 이용**

~~~java
static void initMember(MemberInit member, String name, int age, int grade)
  {
          member.name = name;
          member.age = age;
          member.grade = grade;
}

MemberInit member1 = new MemberInit();
          initMember(member1, "user1", 15, 90);
MemberInit member2 = new MemberInit();
          initMember(member2, "user2", 16, 80);
~~~
이러한 코드는 중복을 제거하긴 하지만, 이런 경우 객체지향 프로그래밍에 입각해 속성과 기능을 한 곳에 두는 것이 더 나은 방법이다.

~~~java
public class MemberInit {
      String name;
      int age;
      int grade;


void initMember(String name, int age, int grade) {
          this.name = name;
          this.age = age;
          this.grade = grade;
} }
~~~

### this

멤버 변수와 메스드의 매개변수의 이름이 같으면?

* 매개변수가 우선순위를 가진다.
* 멤버 변수에 접근하려면 앞에 this.을 붙이면 된다. this는 인스턴스 자신의 참조값을 가르킨다.

~~name = name은 둘다 매개변수를 뜻하게 되어 멤버변수의 값이 변경되지 않는다.~~

**매개변수의 이름과 멤버 변수의 이름이 같은 경우 this 사용, this는 인스턴스 자신을 가르킨다.**


### 생성자 - 도입한 이유

* 객체를 생성하고 이후에 바로 초기값 할당을 해야 되는 경우가 많은데 생성하자마자 즉시 필요한 기능을 좀 더 편리하게 수행할 수 있도록 생성자를 제공

#### 생성자 vs method

* 생성자의 이름은 클래스 이름과 같아야 된다.(첫글자 대문자)
* 생성자는 반환 타입이 없다.


### 생성자의 장점

* 중복호출 제거 : 생성자 덕분에 객체를 생성하면서 동시에 필요한 작업을 한번에 처리

### 생성자의 제약

* 생성자 호출 필수 : 직접 정의한 생성자를 호출해야된다.


#### 기본 생성자

* 매개변수가 없는 생성자
* 기본 생성자를 자바에서 자동으로 만들어준다.
* 생성자가 하나라도 있으면 자바는 기본 생성자를 만들지 않는다.


#### 생성자 - 오버로딩과 this()

* 생성자도 오버로딩처럼 매개변수가 다르면 여러 생성자 제공 가능

~~~java
MemberConstruct(String name, int age) {
          this.name = name;
          this.age = age;
          this.grade = 50;
}

MemberConstruct(String name, int age, int grade) { System.out.println("생성자 호출 name=" + name + ",age=" + age + ",grade="
  + grade);
          this.name = name;
          this.age = age;
          this.grade = grade;
      }
~~~
두 생성자를 비교하면 중복 되는 부분이 있다.

~~~java

MemberConstruct(String name, int age) { this(name, age, 50); 
}

MemberConstruct(String name, int age, int grade) { System.out.println("생성자 호출 name=" + name + ",age=" + age + ",grade="
  + grade);
          this.name = name;
          this.age = age;
          this.grade = grade;
      }
~~~

**이 코드는 첫번째 생성자 내부에서 두번째 생성자를 호출한다.**
* 생성자 내부에서 다른 생성자를 호출 할 수 있다.



**this()의 규칙**

* this()는 생성자 코드의 첫줄에만 작성할 수 있다.


~~~java

  public MemberConstruct(String name, int age) {
      System.out.println("go");
      this(name, age, 50);
}
~~~
~~규칙을 위반해 컴파일 오류가 발생~~





