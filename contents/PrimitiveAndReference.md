# Primitive and Reference


### Primitive 
**사용하는 값을 변수에 직접 넣을 수 있다**
| 타입    | 범주   | 크기       | 기본값    |
|---------|--------|------------|-----------|
| byte    | 정수형 | 1 byte     | 0         |
| short   | 정수형 | 2 byte     | 0         |
| int     | 정수형 | 4 byte     | 0         |
| long    | 정수형 | 8 byte     | 0L        |
| float   | 실수형 | 4 byte     | 0.0f      |
| double  | 실수형 | 8 byte     | 0.0d      |
| char    | 문자형 | 2 byte     | '\u0000'  |
| boolean | 논리형 | 1 bit      | false     |

총 8가지의 타입이 존재한다!


### Reference
**객체(instance)가 저장된 메모리의 위치를 가리키는 참조값을 넣을 수 있다**
1. 객체는 .(dot)을 통해 생성된 객체를 찾아감
2. 배열은 []를 통해서 메모리상에 생성된 배열 찾아감

~~❗️ String은 사실 클래스이다~~


### Primitive vs Reference
**자바는 항상 변수의 값을 복사해서 대입한다**

~~~java
    /*기본형 대입*/
    int a = 1;
    int b = a;

     
    //a 변경
    a = 20;
    System.out.println("변경 a = 20");  
    System.out.println("a = " + a); 
    System.out.println("b = " + b);
    //b 변경
    b = 30;
    System.out.println("변경 b = 30");  
    System.out.println("a = " + a);     
    System.out.println("b = " + b);
~~~
실행결과 
```
a = 1
b = 1
변경 a = 20 
a = 20
b = 1
변경 b = 30 
a = 20
b = 30
```
~~~java
    /*참조형 대입*/
    Student s1 = new Student();
    Student s2 = s1;


  public class Data {
      int value;
}
    
    Data dataA = new Data();
    dataA.value = 10;
    Data dataB = dataA;
    

    System.out.println("dataA 참조값=" + dataA); 
    System.out.println("dataB 참조값=" + dataB); 
    System.out.println("dataA.value = " + dataA.value);
     System.out.println("dataB.value = " + dataB.value);
    //dataA 변경
    dataA.value = 20;
    System.out.println("변경 dataA.value = 20"); 
    System.out.println("dataA.value = " + dataA.value); 
    System.out.println("dataB.value = " + dataB.value);
    //dataB 변경
    dataB.value = 30;
    System.out.println("변경 dataB.value = 30"); 
    System.out.println("dataA.value = " + dataA.value); 
    System.out.println("dataB.value = " + dataB.value);
~~~
실행결과
```
dataA 참조값=ref.Data@x001 
dataB 참조값=ref.Data@x001 
dataA.value = 10 
dataB.value = 10
변경 dataA.value = 20 
dataA.value = 20 
dataB.value = 20
변경 dataB.value = 30 
dataA.value = 30 
dataB.value = 30
```
**같은 곳을 참조하고 있어서 dataA와 dataB 둘 중 아무거나 바껴도 둘 다 바뀐다**

~~~java
    public static void main(String[] args) {
    int a = 10;
    System.out.println("메서드 호출 전: a = " + a); changePrimitive(a); System.out.println("메서드 호출 후: a = " + a);
}
   
   
      static void changePrimitive(int x) {
          x = 20;
}
~~~

실행결과

```
메서드 호출 전: a = 10 
메서드 호출 후: a = 10
```

~~~java
public static void main(String[] args) {
Data dataA = new Data();
dataA.value = 10;
System.out.println("메서드 호출 전: dataA.value = " + dataA.value); changeReference(dataA);
System.out.println("메서드 호출 후: dataA.value = " + dataA.value); }

 static void changeReference(Data dataX) {
          dataX.value = 20;
}
~~~

실행결과

```
메서드 호출 전: dataA.value = 10 
메서드 호출 후: dataA.value = 20
```



**기본형**: 메서드로 기본형 데이터를 전달하면, 해당 값이 복사되어 전달된다. 이 경우, 메서드 내부에서 매개변수 (파라미터)의 값을 변경해도, 호출자의 변수 값에는 영향이 없다.
**참조형**: 메서드로 참조형 데이터를 전달하면, 참조값이 복사되어 전달된다. 이 경우, 메서드 내부에서 매개변수(파 라미터)로 전달된 객체의 멤버 변수를 변경하면, 호출자의 객체도 변경된다.
**기본형 변수는 null을 할당할 수 없지만, 참조형 변수는 null을 할당할 수 있따.**


# Method의 객체 반환

~~~java

Student student1 = createStudent("학생1", 15, 90);

static Student createStudent(String name, int age, int grade) {
          Student student = new Student();
          student.name = name;
          student.age = age;
          student.grade = grade;
          return student;
      }
~~~

이런 식으로 객체를 반환하는 함수를 만들 수 있다.

### NULL
객체가 있는 위치를 가리키는 대상이 없을 경우

### NullPointerException
***참조값이 없는 곳을 찾아갈 때 발생하는 예외**

.(dot)을 찍었을 때 발생한다.


