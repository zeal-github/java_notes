# Input/Output

## 同步和异步

同步IO是指，读写IO时代码必须等待数据返回后才继续执行后续代码，它的优点是代码编写简单，缺点是CPU执行效率低。

而异步IO是指，读写IO时仅发出请求，然后立刻执行后续代码，它的优点是CPU执行效率高，缺点是代码编写复杂。

Java标准库的包java.io提供了同步IO，而java.nio则是异步IO。上面我们讨论的InputStream、OutputStream、Reader和Writer都是同步IO的抽象类，对应的具体实现类，以文件为例，有FileInputStream、FileOutputStream、FileReader和FileWriter。


## File对象
Java的标准库java.io提供了File对象来操作文件和目录

```
File f = new File("C:\\Windows\\notepad.exe");
```
`注意Windows平台使用\作为路径分隔符，在Java字符串中需要用\\表示一个\。Linux平台使用/作为路径分隔符`

### 规范路径
绝对路径可以表示成C:\Windows\System32\..\notepad.exe，而规范路径就是把.和..转换成标准的绝对路径后的路径：C:\Windows\notepad.exe

+ 因为Windows和Linux的路径分隔符不同，File对象有一个静态变量用于表示当前平台的系统分隔符
```
System.out.println(File.separator); // 根据当前平台打印"\"或"/"
```

### 文件和目录

`File`对象既可以表示文件，也可以表示目录。特别要注意的是，构造一个`File`对象，即使传入的文件或目录不存在，代码也不会出错，因为构造一个`File`对象，并不会导致任何磁盘操作。只有当我们调用`File`对象的某些`方法`的时候，才真正进行磁盘操作:`isFile()`判断对象是否是一个存在的文件;`isDirectory()`判断对象是否是一个已存在的目录。

对于文件，有以下方法判断文件的权限和大小
+ `boolean canRead()`：是否可读；
+ `boolean canWrite()`：是否可写；
+ `boolean canExecute()`：是否可执行；
+ `long length()`：文件字节大小。

### 创建和删除文件
```
File file = new File("/path/to/file");
if (file.createNewFile()) {
    // 文件创建成功:
    // TODO:
    if (file.delete()) {
        // 删除文件成功:
    }
}
```

`File`对象提供了`createTempFile()`来创建一个临时文件，以及`deleteOnExit()`在JVM退出时自动删除该文件
```
import java.io*
public class Main {
    public static void main(String[] args) throws IOException {
        File f = File.createTempFile("tmp-", ".txt"); // 提供临时文件的前缀和后缀
        f.deleteOnExit(); // JVM退出时自动删除
        System.out.println(f.isFile());
        System.out.println(f.getAbsolutePath()); //C:\Users\11564\AppData\Local\Temp\tmp-4222507028819438281.txt
        // 创建的临时文件会有一个随机的编号
    }
}
```


### 遍历文件和目录
当`File`对象表示目录时，可以使用`list()`和`listFiles()`列出目录下的文件和子目录名

+ `listFiles()`提供一系列重载方法，可以过滤不想要的文件和目录
```
File f = new File("C:\\Windows");
File[] fs1 = f.listFiles(); // 列出所有文件和子目录
File[] fs2 = f.listFiles(new FilenameFilter() { // 仅列出.exe文件
            public boolean accept(File dir, String name) {
                return name.endsWith(".exe"); // 返回true表示接受该文件
            }
        });
```

### 创建和删除目录
```
+ boolean mkdir()：创建当前File对象表示的目录；
+ boolean mkdirs()：创建当前File对象表示的目录，并在必要时将不存在的父目录也创建出来；
+ boolean delete()：删除当前File对象表示的目录，当前目录必须为空才能删除成功。
```

### `Path`对象

Java标准库还提供了一个Path对象，它位于java.nio.file包。Path对象和File对象类似。
```
import java.io.*;
import java.nio.file.*;
……
Path p1 = Paths.get(".", "project", "study"); // 构造一个Path对象
System.out.println(p1); // C:\Users\11564\AppData\Local\Temp\00000171819150aa\.\project\study

Path p2 = p1.toAbsolutePath(); // 转换为绝对路径
// C:\Users\11564\AppData\Local\Temp\00000171819150aa\project\study


Path p3 = p2.normalize(); // 转换为规范路径
// C:\Users\11564\AppData\Local\Temp\00000171819150aa\project\study


for (Path p : Paths.get("..").toAbsolutePath()) { // 可以直接遍历Path
```


## InputStream

+ try(source)
```
public void readFile() throws IOException {
    try (InputStream input = new FileInputStream("src/readme.txt")) {
        int n;
        while ((n = input.read()) != -1) {
            System.out.println(n);
        }
    } // 编译器在此自动为我们写入finally并调用close()
}
```
编译器并不会特别地为InputStream加上自动关闭。编译器只看try(resource = ...)中的对象是否实现了java.lang.AutoCloseable接口，如果实现了，就自动加上finally语句并调用close()方法.

### 缓冲
在读取流的时候，一次读取一个字节并不是最高效的方法。很多流支持一次性读取多个字节到缓冲区，对于文件和网络流来说，利用缓冲区一次性读取多个字节效率往往要高很多
+ int read(byte[] b)：读取若干字节并填充到byte[]数组，返回读取的字节数
+ int read(byte[] b, int off, int len)：指定byte[]数组的偏移量和最大填充数

```
public void readFile() throws IOException {
    try (InputStream input = new FileInputStream("src/readme.txt")) {
        // 定义1000个字节大小的缓冲区:
        byte[] buffer = new byte[1000];
        int n;
        while ((n = input.read(buffer)) != -1) { // 读取到缓冲区
            System.out.println("read " + n + " bytes.");
        }
    }
}
```

### 阻塞
在调用InputStream的read()方法读取数据时，我们说read()方法是阻塞（Blocking）的。它的意思是，对于下面的代码：
```
int n;
n = input.read(); // 必须等待read()方法返回才能执行下一行代码
int m = n;
```























