# 시작

**동작 파미터화** : 아직은 어떻게 실행할 것인지 결정하지 않은 코드 블록


# 2.1

~~~java
enum Color {RED,GREEN}

 public static List<Apple> filterGreenApples(List<Apple> invnetory) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : invnetory
        if(GREEN.equals(apple.getColor()))) {
            result.add(apple);
        }
        return result;
    }
~~~

이 코드에서 요구사항이 점점 더 늘어나면서 코드가 더 복잡해진다.

~~~java
public static List<Apple> filterApples(List<Apple> inventory, FilteringApples.Color color, int weight, boolean flag) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if ((flag && apple.getColor().equals(color) || (!flag && apple.getWeight() > weight))) {
                result.add(apple);
            }
        }
        return result;
    }
~~~
정말 이상한 코드가 만들어졌다.
요구사항이 바꼈을 때 유연하게 대처할 수 없다. -> 이러한 동작 파라미터화를 이용해 유연성을 얻을 수 있다.


# 2.2 동작 파라미터화

**프레디케이트** : 참 또는 거짓을 반환하는 함수이다.

Predicate 인터페이스를 활용해 설정할 수 있다.

~~~java
 public interface ApplePredicate {

    boolean test(Apple a);

  }

  static class AppleWeightPredicate implements ApplePredicate {

    @Override
    public boolean test(Apple apple) {
      return apple.getWeight() > 150;
    }

  }

  static class AppleColorPredicate implements ApplePredicate {

    @Override
    public boolean test(Apple apple) {
      return apple.getColor() == Color.GREEN;
    }

  }
~~~

이런 코드를 strategy design pattern 이라고 부른다.
**ApplePredicate** : 알고리즘 패밀리
**AppleWeightPredicate,AppleColorPredicate** : 전략

매서드가 다양한 동작을 받아서 내부적으로 다양한 동작을 수행할 수 있다.

결국 ApplePredicate를 사용해서 
~~~java
public static List<Apple> filter(List<Apple> inventory, ApplePredicate p) {
    List<Apple> result = new ArrayList<>();
    for (Apple apple : inventory) {
      if (p.test(apple)) {
        result.add(apple);
      }
    }
    return result;
~~~ 
이런 코드를 작성할 수 있다.
이러한 코드는
~~~java
static class AppleRedAndHeavyPredicate implements ApplePredicate {

    @Override
    public boolean test(Apple apple) {
      return apple.getColor() == Color.RED && apple.getWeight() > 150;
    }

  }
  List<Apple> redAndHeavyApples = filter(inventory, new AppleRedAndHeavyPredicate());
    System.out.println(redAndHeavyApples);
~~~
이런 식으로 사용할 수 있다. 즉, filterApples 메서드의 동작을 파라미터화한 것이다.

* 동작 파라미터화에서는 메서드 내부적으로 다양한 동작을 수행할 수 있도록 코드를 메서드 인수로 전달.
