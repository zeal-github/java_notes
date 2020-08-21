# record 类
从Java 14开始，引入了新的Record类。我们定义Record类时，使用关键字record。把上述Point类改写为Record类，代码如下
```
public record Point(int x, int y) {}
```
编译器自动把上述代码编译为
```
public final class Point extends Record {
    private final int x;
    private final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int x() {
        return this.x;
    }

    public int y() {
        return this.y;
    }

    public String toString() {
        return String.format("Point[x=%s, y=%s]", x, y);
    }

    public boolean equals(Object o) {
        ...
    }
    public int hashCode() {
        ...
    }
}
```
可以看到，编译器自动创建了构造方法，并自动覆写了toString()\equals()等方法

## 为构造方法添加检查逻辑
```
public record Point(int x, int y) {
    public Point {
        if (x < 0 || y < 0) {
            throw new IllegalArgumentException();
        }
    }
}
```