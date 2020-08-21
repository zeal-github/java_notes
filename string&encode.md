# 字符串和编码

## String
+ 在java中，String是一个引用类型。并且，字符串是不可变的。
+ 实际上，String内部通过一个char[]数组表示
+ 字符串比较：我们是要比较字符串内容是否相等，所以要用.equals()方法而不能用==
+ 忽略大小写的比较：.equalsIgnoreCase()
+ 搜索字串
	+ "Hello".contains("ll"); // true
	+ "Hello".indexOf("l"); // 2
	+ "Hello".lastIndexOf("l"); // 3
	+ "Hello".startsWith("He"); // true
	+ "Hello".endsWith("lo"); // true
+ 提取字串
	+ "Hello".substring(2); // "llo"
	+ "Hello".substring(2, 4); "ll"
+ 去除首位空白字符（注意，这里是返回一个新的字符串，并没有改变原字符串内容）
	+ "  \tHello\r\n ".trim(); // "Hello"（空白字符包括空格，\t，\r，\n）
	+ "\u3000Hello\u3000".strip(); // "Hello" （strip会去除类似中文的空格字符\u3000)
	+ " Hello ".stripLeading(); // "Hello "
	+ " Hello ".stripTrailing(); // " Hello"
+ 判断是否为空或为空白字符
	+ "".isEmpty(); // true，因为字符串长度为0
	+ "  ".isEmpty(); // false，因为字符串长度不为0
	+ "  \n".isBlank(); // true，因为只包含空白字符
	+ " Hello ".isBlank(); // false，因为包含非空白字符
+ 替换子串 String s = "hello";
	+ s.replace('l', 'w'); // "hewwo"，所有字符'l'被替换为'w'
	+ s.replace("ll", "~~"); // "he~~o"，所有子串"ll"被替换为"~~"
+ 分割字符串 String s = "A,B,C,D"
	+ String[] ss = s.split("\\,"); // {"A", "B", "C", "D"}
+ 拼接字符串：使用静态方法String.join(),它用指定的字符串连接字符串数组；String[] arr = {"A", "B", "C"};
	+ String s = String.join("***", arr); // "A***B***C"
+ 类型转换：把任意类型或引用类型转换为字符串；使用静态方法：String.valueOf()
	+ String.valueOf(123); // "123"
	+ String.valueOf(45.67); // "45.67"
	+ String.valueOf(true); // "true"
	+ String.valueOf(new Object()); // 类似java.lang.Object@636be97c
+ 把字符串转成其他类型
	+ int n1 = Integer.parseInt("123"); // 123
	+ int n2 = Integer.parseInt("ff", 16); // 按十六进制转换，255
	+ boolean b1 = Boolean.parseBoolean("true"); // true
	+ boolean b2 = Boolean.parseBoolean("FALSE"); // false
+ 转换为char[]，String 和char[]类型可以互相转换
	+ char[] cs = "Hello".toCharArray(); // String -> char[]
	+ String s = new String(cs); // char[] -> String
+ 在java中，char类型实际上是两个字节的Unicode编码。可以手动把字符串转换成其他编码
	+ byte[] b1 = "Hello".getBytes(); // 按系统默认编码转换，不推荐
	+ byte[] b2 = "Hello".getBytes("UTF-8"); // 按UTF-8编码转换
	+ byte[] b2 = "Hello".getBytes("GBK"); // 按GBK编码转换
	+ byte[] b3 = "Hello".getBytes(StandardCharsets.UTF_8); // 按UTF-8编码转换
+ 把已知编码的byte[]转换成String
	+ byte[] b = ...
	+ String s1 = new String(b, "GBK"); // 按GBK转换
	+ String s2 = new String(b, StandardCharsets.UTF_8); // 按UTF-8转换
+ 始终牢记，java的String和char在内存中总是以Unicode编码表示

## StringBuilder
String 可以直接使用“+”进行字符串连接，但是每次连接会创建一个临时变量，印象执行速度。StringBuilder可以开辟一个缓冲区
于是可以动态地往StringBuilder里新增字符。
```
StringBuilder sb = new StringBuilder(1024);
for (int i = 0; i < 1000; i++) {
    sb.append(',');
    sb.append(i);
}
String s = sb.toString();
//append可以进行链式操作
sb.append("Mr ")
          .append("Bob")
          .append("!")
          .insert(0, "Hello, ");
```
append方法可以进行链式操作是因为方法内部除了执行方法内容之外，还返回了一个this，使得可以不断调用自身的方法。

## StringJoiner
StringJoiner 可以用来方便地拼接字符串数组，连接符号不会添加到最后。并且可以指定开头与结尾。
```
import java.util.StringJoiner;
public class Main {
    public static void main(String[] args) {
        String[] names = {"Bob", "Alice", "Grace"};
        var sj = new StringJoiner(", ", "Hello ", "!");
        for (String name : names) {
            sj.add(name);
        }
        System.out.println(sj.toString());
    }
}
```

