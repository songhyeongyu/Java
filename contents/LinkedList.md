# Node 

**배열 리스트의 단점**
1. 배열의 사용하지 않는 공간 낭비
2. 배열의 중간에 데이터 추가

**노드와 연결**
~~~java
public class Node {
    Object item;
    Node next; 
    
    public Node(Object item) {
        this.item = item;
    }   
}
~~~
저장할 데이터인 item, 연결할 노드의 참조 next를 가진다.

* 필드의 점근 제어자는 private으로 선언하는 것이 좋다.

~~~java
//노드 생성하고 연결하기: A -> B -> C
Node first = new Node("A"); first.next = new Node("B"); first.next.next = new Node("C");
System.out.println("모든 노트 탐색하기");
 Node x = first;
while (x != null) {
            System.out.println(x.item);
x = x.next;
}
~~~
* Node first를 통해 첫 번째 노드를 구할 수 있다.
* 첫 번째 노드의 node.next를 호출하면 두 번째 노드를 구할 수 있다.
* 두 번쨰 노드의 node.next를 호출하면 세번째 노드를 구할 수 있다.
* 첫 번쨰 노드의 node.next.next를 호출하면 세 번째 노드를 구할 수 있다.


~~~java
private static Node getLastNode(Node node) {
         Node x = node;
         while (x.next != null) {
             x = x.next;
}
return x; 
}

 private static Node getNode(Node node, int index) {
         Node x = node;
         for (int i = 0; i < index; i++) {
             x = x.next;
}
return x; 
}
private static void add(Node node, String param) {
         Node lastNode = getLastNode(node);
         lastNode.next = new Node(param);
}
public int indexOf(Object o) {
    int index = 0;
    for (Node x = first; x != null; x = x.next) {
        if (o.equals(x.item))
            return index;
        index++;
}
return -1; 
}

public Object get(int index) {
    Node node = getNode(index);
    return node.item;
}
 public Object set(int index, Object element) {
         Node x = getNode(index);
         Object oldValue = x.item;
         x.item = element;
return oldValue;
 }
~~~
1. 특정 노드를 조회하는 코드
2. 노드 데이터 추가하기
3. 마지막 노드 조회하는 코드
4. 특정 위치에 있는 데이터를 찾아 변경 + 반환(set)
5. 특정 위치에 있는 데이터 반환(get)
6. 데이터를 검색하고, 검색된 위치 반환 (indexOf)

## LinkedList

ArrayList : 배열을 통해서 리스트를 만듬
LinkedList : 노드와 연결 구조를 통해서 만듬
| 기능          | 배열 리스트 | 연결 리스트 |
|-------------|------------|------------|
| 인덱스 조회 | O(1)       | O(n)       |
| 검색        | O(n)       | O(n)       |
| 앞에 추가(삭제) | O(n)   | O(1)       |
| 뒤에 추가(삭제) | O(1)   | O(n)       |
| 평균 추가(삭제) | O(n)   | O(n)       |

**정리**
* 연결 리스트를 통해 데이터를 추가하는 방식은 꼭 필요한 메모리만 사용
* 연결을 유지하기 위한 추가 메모리를 사용하는 단점도 존재한다.

특정 위치에 있는 노드 추가,삭제는 어떻게 할까?
~~~java
public void add(int index, Object e) {
         Node newNode = new Node(e);
         if (index == 0) {
newNode.next = first;
first = newNode;
    } else {
        Node prev = getNode(index - 1);
        newNode.next = prev.next;
        prev.next = newNode;
}
size++; }
public Object remove(int index) {
    Node removeNode = getNode(index);
    Object removedItem = removeNode.item;
    if (index == 0) {
        first = removeNode.next;
    } else {
        Node prev = getNode(index - 1);
        prev.next = removeNode.next;
    }
    removeNode.item = null;
    removeNode.next = null;
    size--;
    return removedItem;
}
~~~