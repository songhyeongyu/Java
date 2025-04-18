# 자바 8의 새로운 기술

## 1.1


* 스트림 API
    * synchorinzed를 사용하지 않음
        * 코드를 전달하는 간결 기법
        * 인터페이스의 디폴트 메서드가 존재
        -> 함수형 프로그래밍을 사용할 수 있다.
* 메서드에 코드를 전달하는 기법
* 인터페이스의 디폴트 메서드

## 1.2

왜 아직도 자바?
1. 처음부터 유용한 라이브러리를 포함해 잘 설계된 객체지향 언어로 시작
2. 스레드와 락을 통한 도잇성 지원
3. JVM이 컴파일

하지만 병렬 프로세싱을 활용해야 하는데 지금까지의 자바로는 대응 불가

**스트림 처리**

스트림 : 한 번에 한 개씩 만들어지는 연속적인 데이터 항목

**동작 파라미터화**

메서드를 다른 메서드의 인수로 넘겨주는 기능을 제공

## 1.3

자바의 함수 : 수학적인 함수처럼 사용되며 부작용이 없다.

프로그래밍 언엉의 핵심은 값을 바꾸는 것
일급값 : 값이 바뀔 수 있는것
이급값: 구조체와 같이 자유롭게 전달 할 수 없느 것.(메서드)

메서드를 런타임에 전달할 수 있다면 -> 이급 시민을 일급시민으로 바꿀 수 있으면 매우 좋다.

1. 메서드 참조
~~~java
File[] hiddneFiles = new File(".").listfiles(new FiledFilter()){
    public boolean accept(File file){
        return file.isHidden();
    }
};
~~~

굳이 fileFilter을 인스턴스화 해야되나 ?
~~~java
File[] hiddenFiles = new File(".").listFiles(File::isHidden);
~~~

isHidden이라는 함수는 준비되어 있으 자바 8의 메서드 참조를 이용해서 직접 전달.

**람다 : 익명 함수**

(int x) -> x+1 :x+1로 반환

## 1.4

멀티 스레딩 코드를 구현해서 병렬성을 이용하는 것은 쉽지 않았다.

스트림은 스트림 내의 요소를 쉽게 병렬로 처리 할 수 있는 환경을 제공

컬렉션을 가장 빨리 필터링 할 수 있는 방법은 컬랙션 -> stream -> 병렬처리 -> 리스트로 복원

