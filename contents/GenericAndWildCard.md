# GenericV2

~~~java

    private Dog animal;
public void set(Dog animal) {
        this.animal = animal;
        public void checkup() {
        System.out.println("동물 이름: " + animal.getName()); System.out.println("동물 크기: " + animal.getSize()); animal.sound();
}
public Dog bigger(Dog target) 
{
    return animal.getSize()>target.getSize() ? animal : target;
}
}

 private Cat animal;
    public void set(Cat animal) {
        this.animal = animal;
}
public void checkup() {
    System.out.println("동물 이름: " + animal.getName()); System.out.println("동물 크기: " + animal.getSize()); animal.sound();
}
    public Cat getBigger(Cat target) {
        return animal.getSize() > target.getSize() ? animal : target;
}
public static void main(String[] args) {
         DogHospital dogHospital = new DogHospital();
         CatHospital catHospital = new CatHospital();
Dog dog = new Dog("멍멍이1", 100); Cat cat = new Cat("냐옹이1", 300);
// 개 병원 
dogHospital.set(dog); dogHospital.checkup();
// 고양이 병원 
catHospital.set(cat); catHospital.checkup();
// 문제1: 개 병원에 고양이 전달
// dogHospital.set(cat); // 다른 타입 입력: 컴파일 오류
// 문제2: 개 타입 반환
dogHospital.set(dog);
Dog biggerDog = dogHospital.bigger(new Dog("멍멍이2", 200)); System.out.println("biggerDog = " + biggerDog);
}
~~~
**이 코드는 개 병원은 개만 받고, 고양이 병원은 고양이만 받을 수 있다.**

**문제**
1. 코드 재사용X: 개 병원과 고양이 병원은 중복이 많이 보인다.
2. 타입 안정성O: 타입 안정성이 명확하게 지켜진다.

## Generic 도입과 실패

~~~java
private T animal;
     public void set(T animal) {
         this.animal = animal;
}
public void checkup() {
// T의 타입을 메서드를 정의하는 시점에는 알 수 없다. Object의 기능만 사용 가능 animal.toString();
animal.equals(null);
// 컴파일 오류
//System.out.println("동물 이름: " + animal.getName()); //animal.sound();
}
public T getBigger(T target) { // 컴파일 오류
 //return animal.getSize() > target.getSize() ? animal : target;
        return null;
    }
~~~
* <T>를 통해서 제네릭 타입을 선언했다.

자바 컴파일러는 어떤 타입이 들어올 지 알 수 없기 때문에 T를 어떤 타입이든 받을 수 있는 모든 객체의 최종 부모인 `Object`타임으로 정의한다.

### type parameter 제한

~~~java
public class AnimalHospitalV3<T extends Animal> {
     private T animal;
     public void set(T animal) {
         this.animal = animal;
}
public void checkup() {
System.out.println("동물 이름: " + animal.getName()); System.out.println("동물 크기: " + animal.getSize()); animal.sound();
}
     public T getBigger(T target) {
         return animal.getSize() > target.getSize() ? animal : target;
} }
~~~
* T의 상한을 Animal로 두는 것이다.


### 기존 문제와 해결

1. 타입 안정성x
    * 개 병원에 고양이 전달 -> 해결
    * Animal 타입을 받으려면 다운 케스팅 -> 해결
    * 실수로 고양이를 입력했는데, 개를 반환하는 상황  -> 해결
2. 제네릭 도입 문제
    * 제네릭에서 타입 매개변수를 사용하면 어떤 타입이든 들어온다 -> 해결
    * Object 기능만 사용가능하다 -> 해결

**정리**
타입 매개변수 상환을 사용해 타입 안정성을 지키면서 원하는 상위 타입의 원하는 기능까지 사용할 수 있다.

### generic method

~~~java
 public static Object objMethod(Object obj) {
         System.out.println("object print: " + obj);
         return obj;
}
     public static <T> T genericMethod(T t) {
         System.out.println("generic print: " + t);
         return t;
}
     public static <T extends Number> T numberMethod(T t) {
         System.out.println("bound print: " + t);
         return t;
     }
~~~
* 제네릭 클래스와 똑같은 방법으로 상한을 지정해 사용할 수 있다.

# WildCard

~~~java
 static <T extends Animal> void printGenericV2(Box<T> box) {
        T t = box.get();
System.out.println("이름 = " + t.getName()); }
static void printWildcardV2(Box<? extends Animal> box) { Animal animal = box.get();
System.out.println("이름 = " + animal.getName());
}
~~~
* 와일드카드는 **`?`**를 사용해서 정의한다.

**❗️참고**
와일드카드는 이미 만들어진 제네릭 타입을 활용할 때 사용한다.

~~~java

//이것은 제네릭 메서드가 아니다. 일반적인 메서드이다.

//Box<Dog> dogBox를 전달한다. 와일드카드 ?는 모든 타입을 받을 수 있다. 
static void printWildcardV1(Box<?> box) {
     System.out.println("? = " + box.get());
 }
~~~
* 두 메서드(generic, wild card)는 비슷한 기능을 한다.
* 와일드카드는 제네릭 타입이나 제네릭 메서드를 정의할 때 사용하는 것은 아니다.
* 와일드카드인 `?`는 모든 타입을 다 받을 수 있다.

#### extends wildCard
~~~java
static void printWildcardV2(Box<? extends Animal> box) { Animal animal = box.get();
System.out.println("이름 = " + animal.getName());
}
~~~
* 제네릭 메서드와 마찬가지로 상한 제한을 둘 수 있다.
* Animal과 그 하위 타입만 입력이 가능하다.

~~~java
Dog dog = WildcardEx.printAndReturnGeneric(dogBox)


Animal animal = WildcardEx.printAndReturnWildcard(dogBox)
~~~

* Generic은 전달한 타입을 명확히 받는다.
* wildCard는 전달한 타입을 명확하게 반환 할 수 없다.
