# Procedural Programming

1. 실행 순서를 중요하게 생각하는 방식이다.
2. 프로그램의 흐름을 순차적으로 따르며 처리한다. "어떻게"를 중심으로 프로그래밍 한다.


# Object Programming

1. 객체를 중요하게 생각하는 방식이다.
2. 실제 세계의 사물이나 사건을 객체로 보고, 이러한 객체들 간의 상호작용을 중심으로 프로그래밍 하는 방식이다. 
"무엇을" 중심으로 프로그래밍 한다.


## Procedural Programming example

* 절차 지향은 데이터와 해당 데이터에 대한 처리 방식이 분리되어 있다. 하지만 객체 지향에서는 데이터와 데이터에 대한 메서드가 하나의 '객체'안에 함께 포함되어 있다.


~~~java
public static void main(String[] args) { MusicPlayerData data = new MusicPlayerData(); 
on(data);
volumeUp(data);
volumeUp(data);
volumeDown(data);
showStatus(data);
off(data);
}
static void on(MusicPlayerData data) { 
data.isOn = true;
System.out.println("음악 플레이어를 시작합니다");
}
static void off(MusicPlayerData data) { 
data.isOn = false;
System.out.println("음악 플레이어를 종료합니다");
}
static void volumeUp(MusicPlayerData data) {
data.volume++;
System.out.println("음악 플레이어 볼륨:" + data.volume);
}
static void volumeDown(MusicPlayerData data) {
          data.volume--;
System.out.println("음악 플레이어 볼륨:" + data.volume); }
static void showStatus(MusicPlayerData data) { System.out.println("음악 플레이어 상태 확인"); if (data.isOn) {
System.out.println("음악 플레이어 ON, 볼륨:" + data.volume); } else {
System.out.println("음악 플레이어 OFF"); }
}
~~~
* 중복 제거 , 변경 영향 범위(기능을 수정할 떄 해당 메서드 내부만 변경)


**단점 : 데이터와 기능이 분리 되어있다. 유지보수 관점에서도 관리 포인트가 2곳으로 늘어난다. but 객체지향 프로그래밍은 데이터와 기능을 온전히 하나로 묶는다.**



## Object Programmin example

~~~java
public class MusicPlayer {
      int volume = 0;
      boolean isOn = false;
      void on() {
          isOn = true;
System.out.println("음악 플레이어를 시작합니다");
}
      void off() {
          isOn = false;
System.out.println("음악 플레이어를 종료합니다"); }
      void volumeUp() {
          volume++;
System.out.println("음악 플레이어 볼륨:" + volume); }
      void volumeDown() {
          volume--;
System.out.println("음악 플레이어 볼륨:" + volume); }
void showStatus() {
System.out.println("음악 플레이어 상태 확인"); if (isOn) {
System.out.println("음악 플레이어 ON, 볼륨:" + volume); } else {
System.out.println("음악 플레이어 OFF");    }
    } 
}

public static void main(String[] args) {
MusicPlayer player = new MusicPlayer(); 
player.on();
player.volumeUp(); 
player.volumeUp(); 
player.volumeDown();  
player.showStatus();  
player.off();
}
~~~

* MusicPlayer 객체를 생성하고 필요한 메서드를 호출하기만 하면 된다.

1. MusicPlayer 입장에서는  volume,isOn 같은 데이터는 전혀 사용하지 않는다.
2. MusicPlater를 사용하는 입장에서는 단순하게 MusicPlayer가 제공하는 기능 중에 필요한 기능을 호출해서 사용하기만 하면 된다.

### 캡슐화

**속성과 기능을 하나로 묶어서 필요한 기능을 메서드를 통해 외부에 제공하는 것을 캡슐화.**


#### 정리

* 차이 : 절차 지향은 데이터와 데이터 처리 방식이 분리 하지만 객체 지향은 데이터와 메서드가 하나의 객체안에 포함