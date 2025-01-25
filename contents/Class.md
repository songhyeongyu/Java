# Class

**class는 왜 필요한가**

코드를 예로 들어 설명하겠습니다.



## 학생 정보 코드 
### 코드 1 (변수 이용)
~~~
    String name = "sutdent1";
    int student1Age = 13;
    Stirng student1Grade = 'A';

    String name = "sutdent2";
    int student2Age = 14;
    Stirng student2Grade = 'B';

System.out.println("이름:" + student1Name + " 나이:" + student1Age + " 성 적:" + student1Grade);


System.out.println("이름:" + student2Name + " 나이:" + student2Age + " 성 적:" + student2Grade);
~~~

학생을 추가하기 위해서는 변수를 추가해서 선언해야 되고 코드를 추가해야 된다. ~~상당히 불편~~

**그렇다면 배열을 이용?**

## 코드 2(배열 이용)
~~~
    String[] studentNames = {"student1","student2"};

    int[] sutdentAges = {13,14};

    char[] studentGrade = {'A','B'};

    for(int i = 0 ; i<studentNames.length;i++){
        System.out.println("이름:" + studentName[i] + " 나이:" + studentAge[i] + " 성 적:" + studentGrade[i]);
    }
~~~
학생이 추가되어도 배열에 학생의 데이터만 추가된다. ~~출력 부분에서만 유지할 수 있다. 사람이 관리하기에는 별로 좋지않다.~~


## 코드3 (Class 이용)

~~~
package class1;

public class Student {
    String name;
    int age;
    char grade;
}

Student student1 = new Student(); student1.name = "학생1"; student1.age = 15;
student1.grade = 'A';
Student student2 = new Student(); student2.name = "학생2"; student2.age = 16;
student2.grade = 'B';
//배열 선언
Student[] students = new Student[]{student1, student2};
//for문 적용
for (int i = 0; i < students.length; i++) {
System.out.println("이름:" + students[i].name + " 나이:" + students[i].age + " 성적:" + students[i].grade);
}
~~~

클래스에 정의한 변수

1. Member Variable : 이 변수들은 특정 클래스에 소속된 멤버이다.
2. Field 
~~서로 같은 뜻이다~~


용어
 1. 클래스 : 사용자 정의 타입을 만들려는 설계도
 2. 객체 : 실제 메모리에 만들어진 실체
 3. 인스턴스 : ~~객체랑 같은 뜻~~

❗️ 변수에는 인스턴스 자체가 들어있는 것이 아니다! -> 참조값만 복사된다.

~~~
    student[0] = "객체 참조값 ex(x100)";
~~~
들어가게 된다.
