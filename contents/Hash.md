# Hash

**List vs Set**

* List
    * 순서유지
    * 중복허용
    * 인덱스 접근

**용도** 순서 중요하거나 중복된 요소를 허용

* Set
    * 유일성
    * 순서 미보장
    * 빠른 검색

**용도** 중복을 허용하지 않고, 요소의 유무만 중요한 경우에 사용한다.

**`Set`** 의 구현
~~~java
 public boolean add(int value) {
         if (contains(value)) {
             return false;
         }
         elementData[size] = value;
         size++;
         return true;
}
// O(n)
     public boolean contains(int value) {
         for (int data : elementData) {
             if (data == value) {
                 return true;
} }
         return false;
     }
     public int getSize() {
         return size;
}
~~~

**정리**
데이터의 값 자체를 배열의 인덱스로 사용
O(N) -> O(1)로 개선했다.
but 큰 배열을 사용해야 되서 배열에 낭비되는 공간이 많이 발생된다.

### 나머지 연산

**해시 인덱스**
나머지 연산을 통해 인섹스에다가 저장할 수 있다

~~~java
private static void add(Integer[] inputArray, int value) {
         int hashIndex = hashIndex(value);
         inputArray[hashIndex] = value;
}
     static int hashIndex(int value) {
         return value % CAPACITY;
}
~~~

**❗️하지만 충돌이 일어난다**
ex) 88 % 10 = 8 8 % 10 = 8

하지만 통계적으로 입력한 데이터의 수가 배열의 크기를 75% 넘지 않으면 해시 인덱스는 자주 충돌하지 않는다.

**정리**
해시 인덱스를 사용하는 경우
* 데이터 저장
    * 평균: O(1)
    * 최악: O(n) 
* 데이터 조회
    * 평균: O(1) 
    * 최악: O(n)

## 개선

~~~java
 public MyHashSetV1(int capacity) {
         this.capacity = capacity;
         initBuckets();
}
     private void initBuckets() {
         buckets = new LinkedList[capacity];
         for (int i = 0; i < capacity; i++) {
             buckets[i] = new LinkedList<>();
         }
}
     public boolean add(int value) {
         int hashIndex = hashIndex(value);
         LinkedList<Integer> bucket = buckets[hashIndex];
         if (bucket.contains(value)) {
             return false;
         }
         bucket.add(value);
         size++;
         return true;}
    public boolean contains(int searchValue) {
        int hashIndex = hashIndex(searchValue);
        LinkedList<Integer> bucket = buckets[hashIndex];
        return bucket.contains(searchValue);
}
    public boolean remove(int value) {
        int hashIndex = hashIndex(value);
        LinkedList<Integer> bucket = buckets[hashIndex];
        boolean result = bucket.remove(Integer.valueOf(value));
        if (result) {
size--;
            return true;
        } else {
            return false;
        }
}
    private int hashIndex(int value) {
        return value % capacity;
}
    public int getSize() {
        return size;
}
~~~
* `buckets` : 연결 리스트를 배열로 사용한다.
    * 배열안에 연결 리스트가 들어있고, 연결 리스트 안에 데이터가 저장된다.
    * 해시 인덱스가 충돌이 발생하면 같은 연결 리스트 안에 여러 데이터가 저장된다. 
* `initBuckets()`
    * 연결 리스트를 생성해서 배열을 채운다. 배열의 모든 인덱스 위치에는 연결 리스트가 들어있다.
 
* `add()` : 해시 인덱스를 사용해서 데이터를 보관한다. 
* `contains()` : 해시 인덱스를 사용해서 데이터를 확인한다. 
* `remove()` : 해시 인덱스를 사용해서 데이터를 제거한다.

### 문자열 해시 코드
지금까지 해시 인덱스는 숫자로 이루어져 있었다. 문자는 어떻게 할까?

**문자는 아스키 코드를 기준으로 정렬한다**
~~~java
char charA = 'A';
System.out.println(charA + " = " + (int)charA);

System.out.println("hashCode(A) = " + hashCode("A"));

System.out.println("hashIndex(A) = " + hashIndex(hashCode("A")));


     static int hashCode(String str) {
         char[] charArray = str.toCharArray();
         int sum = 0;
         for (char c : charArray) {
sum += c; }
return sum; }
     static int hashIndex(int value) {
         return value % CAPACITY;
}
~~~
결과
~~~
A = 65
hash(A) = 65
hashIndex(A) = 5
~~~

**정리**
*   문자 데이터를 사용할 때도, 해시 함수를 사용해서 정수 기반의 해시 코드로 변환한 덕분에, 해시 인덱스를 사용할 수 있 게 되었다. 따라서 문자의 경우에도 해시 인덱스를 통해 빠르게 저장하고 조회할 수 있다.

#### equals, hashCode의 중요성

~~~java
//검색 실패
MemberNoHashNoEq searchValue = new MemberNoHashNoEq("A"); System.out.println("searchValue.hashCode() = " +
 searchValue.hashCode());
         boolean contains = set.contains(searchValue);
         System.out.println("contains = " + contains);
~~~
equals와 hashCode를 오버라이딩을 안해 원하는 값이 안나왔다.

❗️`hashCode()` 를 항상 재정의해야 하는 것은 아니다. 하지만 해시 자료 구조를 사용하는 경우 `hashCode()` 와 `equals()` 를 반드시 함께 재정의해야 한다.
