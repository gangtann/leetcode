# LeetCode

LeetCode的`Java`代码实现

## 常用数据结构

### Stack

以下是 `java.util.Stack<E>` 最常用的方法（它继承自 Vector，并提供如下一些核心栈操作）：

####  核心栈操作（新增/移除/查看/判断/搜索）

* **`push(E item)`**

  将元素压入栈顶，返回该元素。

* **`pop()`**

  弹出并返回栈顶元素；若栈空则抛出 `EmptyStackException`。

+ **`peek()`**

  查看栈顶元素但不移除；若栈空则抛出 `EmptyStackException`。

+ **`empty()`**

  判断栈是否为空，返回 `true`/`false`。

+ **`search(Object o)`**

  返回元素 `o` 距离栈顶的 1 based 位置；若未找到返回 -1。

---

#### 其他常用方法（来自于 Vector / Collection 接口）

虽然 `Stack` 并未新增这些方法，但它继承自 `Vector`，可以继续使用如下一些集合操作：

+ `size()`, `isEmpty()`（与 `empty()` 类似）

+ `contains()`, `indexOf()`, `lastIndexOf()`

+ `clear()`, `clone()`

+ `toArray()`, `iterator()`, `stream()` 等

---

#### 示例代码

```java
import java.util.Stack;

class StackCommonMethod {
  public static void main(String[] args) {
    Stack<Integer> stack = new Stack<>();
    stack.push(10);                    // 入栈
    stack.push(20);
    System.out.println(stack.peek());  // 查看：20
    System.out.println(stack.pop());   // 出栈并返回：20
    System.out.println(stack.empty()); // false
    System.out.println(stack.search(10)); // 1（栈顶为 1）
  }    
}
```

####  建议

虽然 `Stack` 可用，但从 Java 1.6 起，更推荐使用 `Deque` 接口（如 `ArrayDeque`）实现栈结构，性能更好且线程不安全版本也更高效。

---

#### 总结列表

| 方法               | 功能                                                      |
| ------------------ | --------------------------------------------------------- |
| `push(E)`          | 进栈                                                      |
| `pop()`            | 出栈并返回元素                                            |
| `peek()`           | 查看栈顶元素                                              |
| `empty()`          | 判断是否为空                                              |
| `search(Object)`   | 查找元素相对栈顶位置                                      |
| 继承的 Vector 方法 | 支持集合通用操作，如 `size()`、`contains()`、`clear()` 等 |

---

### Deque

下面是 `java.util.Deque<E>` 接口中最常用的方法，涵盖双端队列在两端的增删查操作，同时支持队列和栈的使用方式：

------

#### 🛠 添加元素

| 方法                                                         | 端点       | 异常行为                            |
| ------------------------------------------------------------ | ---------- | ----------------------------------- |
| `addFirst(E e)`                                              | 前端（头） | 失败时抛 `IllegalStateException`    |
| `offerFirst(E e)`                                            | 前端（头） | 失败时返回 `false`                  |
| `addLast(E e)` / `add(E e)` / `offer(E e)` / `offerLast(E e)` | 后端（尾） | `add` 抛异常，`offer` 返回 `false`) |

此外，作为栈操作：

- `push(E e)` 等价于 `addFirst(e)` 。

------

#### ✅ 查看元素（不删除）

- `getFirst()`, `getLast()`：获取首/尾元素，若为空抛异常
- `peekFirst()`, `peekLast()`: 类似上述，但为空返回 `null`
- `peek()`: 等价于 `peekFirst()`，可用于栈（LIFO）模式查看顶部元素

------

#### 🗑 删除元素

| 方法                         | 端点                          | 异常行为                     |
| ---------------------------- | ----------------------------- | ---------------------------- |
| `removeFirst()` / `remove()` | 前端                          | 失败抛异常                   |
| `removeLast()`               | 后端                          | 异常                         |
| `pollFirst()`                | 前端                          | 失败返回 `null`              |
| `pollLast()` / `poll()`      | 后端（`poll` == `pollFirst`） | 返回 `null`                  |
| `pop()`                      | 前端                          | 抛异常，等价 `removeFirst()` |

------

#### 🎯 定位与删除特定元素

- `removeFirstOccurrence(Object o)`：删除首个匹配 o
- `removeLastOccurrence(Object o)`：删除最后一个匹配 o

------

#### 🔄 遍历与状态检查

- `iterator()`：从前端到后端遍历
- `descendingIterator()`：从后端到前端遍历 
- `contains(Object o)`：是否包含元素
- `size()`, `isEmpty()`：常规集合属性

------

#### 📚 方法分类小结

- **插入**：`addFirst`/`offerFirst`, `addLast`/`offerLast`, `push`
- **查看**：`getFirst`/`peekFirst`, `getLast`/`peekLast`, `peek`
- **删除**：`removeFirst`/`pollFirst`, `removeLast`/`pollLast`, `pop`, `removeFirstOccurrence`/`removeLastOccurrence`
- **遍历 & 状态**：`iterator`, `descendingIterator`, `contains`, `size`, `isEmpty`

------

#### 🔁 使用场景

- 当实现栈（LIFO）时，推荐用：`push()`、`pop()`、`peek()`，对应 `addFirst/removeFirst/peekFirst`
- 当作为队列（FIFO）时，使用：`offer()`、`poll()`、`peek()`，对应 `addLast/pollFirst/peekFirst`
- 双端灵活操作适合双向任务管理、回文检测、浏览器前进/后退缓存等场景

------

#### ✅ 推荐实现

常用实现类包括：

- `ArrayDeque<E>`：高性能、动态数组，非线程安全
- `LinkedList<E>`：双链表实现，同样支持 `List` 操作
- 线程安全场景下可用：`ConcurrentLinkedDeque`, `LinkedBlockingDeque`

------

#### 🔎 示例

```java
import java.util.Deque;

class DequeCommonMethod {
  public static void main(String[] args) {
    Deque<Integer> dq = new ArrayDeque<>();
    dq.push(1);              // 栈操作，头入
    dq.addLast(2);           // 队列尾入
    int top = dq.peek();     // 查看头部元素
    int first = dq.pollFirst(); // 移除并取出头部
    dq.removeLastOccurrence(2); // 删除尾部匹配
    for (int x : dq)         // 正序遍历
      System.out.print(x);
  }
}
```

------



