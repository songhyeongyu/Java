# Array

배열과 같이 여러 데이터를 구조화해서 다루는 것을 자료구조라고 한다.

~~~java
int[] arr = new int[5];
//index 입력: O(1) 
System.out.println("==index 입력: O(1)=="); arr[0] = 1;
         arr[1] = 2;
         arr[2] = 3;
         System.out.println(Arrays.toString(arr));
//index 변경: O(1) 
System.out.println("==index 변경: O(1)=="); arr[2] = 10; System.out.println(Arrays.toString(arr));
~~~

**배열의 특징**
1. 배열에서 자료를 찾을 때 index로 접근하면 매우 빠르게 찾을 수 있다.
2. 인덱스를 통해 입력,변경,조회의 경우 한번의 계산으로 위치를 찾을 수 있다.

**배열의 검색**
배열에 들어있는 데이터를 검색 할 땐 배열에 들어있는 데이터를 하나하나 비교해야된다.

**배열 정리**
* 배열의 인덱스 사용 : O(1)
* 배열의 순차 검색 : O(N)

**배열의 데이터를 추가할 때 위치에 따른 성능 변화**

* 배열의 첫번째 위치에 추가
    * O(N)
* 배열의 중간 위치에 추가
    * O(N)
* 배열의 마지막 위치에 추가
    * O(1)    

~~~java
 private static void addLast(int[] arr, int newValue) {
         arr[arr.length - 1] = newValue;
}
     private static void addFirst(int[] arr, int newValue) {
         for (int i = arr.length - 1; i > 0; i--) {
             arr[i] = arr[i - 1];
         }
         arr[0] = newValue;
     }
     private static void addAtIndex(int[] arr, int index, int newValue) {
         for (int i = arr.length - 1; i > index; i--) {
             arr[i] = arr[i - 1];
         }
         arr[index] = newValue;
     }
~~~

**배열의 한계**
* 배열의 크기를 배열을 생성하는 시점에 미리 정해야 된다.(동적으로 변경할 수 없음)
* 데이터를 추가하기 불편하다.


## List 자료구조

* `배열` : 순서가 있고 중복을 허용하지만 크기가 정적
* `리스트` : 순서가 있고 중복을 허용하지만 크기가 동적

~~~java
private static final int DEFAULT_CAPACITY = 5;
     private Object[] elementData;
     private int size = 0;
     public MyArrayListV2() {
         elementData = new Object[DEFAULT_CAPACITY];
}
     public MyArrayListV2(int initialCapacity) {
         elementData = new Object[initialCapacity];
}
     public int size() {
         return size;
}
public void add(Object e) { //코드 추가
         if (size == elementData.length) {
             grow();}
        elementData[size] = e;
        size++;
}
//코드 추가
private void grow() {
        int oldCapacity = elementData.length;
        int newCapacity = oldCapacity * 2;
        elementData = Arrays.copyOf(elementData, newCapacity);
}
    public Object get(int index) {
        return elementData[index];
}
    public Object set(int index, Object element) {
        Object oldValue = get(index);
        elementData[index] = element;
        return oldValue;
}
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (o.equals(elementData[i])) {
                return i;
} }
return -1; }
public void add(int index, Object e) {
        if (size == elementData.length) {
            grow();
        }
        shiftRightFrom(index);
        elementData[index] = e;
        size++;
}
//코드 추가, 요소의 마지막부터 index까지 오른쪽으로 밀기
private void shiftRightFrom(int index) {
for (int i = size; i > index; i--) {
        elementData[i] = elementData[i - 1];
    }
}

/코드 추가
public Object remove(int index) {
    Object oldValue = get(index);
    shiftLeftFrom(index);
    size--;
    elementData[size] = null;
    return oldValue;
}
//코드 추가, 요소의 index부터 마지막까지 왼쪽으로 밀기 private void shiftLeftFrom(int index) {
    for (int i = index; i < size - 1; i++) {
        elementData[i] = elementData[i + 1];
}

~~~

* 추가된 부분은 `grow()`메서드와 `add()`이다.
* `add()`는 size가 배열의 크기에 도달했다면 더는 데이터를 추가할 수 있다.
* `grow()`를 호출해서 기존 배열에 복사한 새로운 배열을 만들고 배열의 크기도 2배로 늘린다.
* `Arrays.copyOf(기존배열, 새로운길이)` : 새로운 길이로 배열을 생성하고, 기존 배열의 값을 새로운 배 열에 복사한다.

**정리**
* 배열 리스트는 순서대로 마지막에 데이터를 추가할 떄 성능이 좋지만, 앞이나 중간에 데이터를 추가하거나 삭제할 때는 성능이 좋지 않다.

* 마지막 추가,삭제 O(1)
* 인덱스 조회 : O(1)
* 데이터 검색 : O(N)
* 앞,중간 추가,삭제 : O(N)