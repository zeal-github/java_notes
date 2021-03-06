## 第一个java程序
+ 一个Java源码只能定义一个public类型的class，并且class名称和文件名要完全一致
+ javac 编译java源码成字节码.class
+ java运行已编译的java程序，参数是类名，虚拟机自动查找对应的class文件并执行
+ java 规定，类里面的main函数是程序入口
+ java用；和{}区分语句块，是否有缩进对程序没有影响

## Java 程序的基本结构

+ Java入口程序规定的方法必须是静态方法，方法名必须为main，括号内的参数必须是String数组。

## 数据类型

+ 长整型（Long）末尾要加L：100000000000000L
+ float末尾加f：3.14f
+ 定义变量前加 final 修饰符则变成定义常量：final double PI = 3.14；通常，常量名大写
+ var 关键字可在定义变量时省略变量类型： var sb = new StringBuilder（）

## 四则运算
### 整数
+ 注意++写在前面和后面计算结果是不同的，++n表示先加1再引用n，n++表示先引用n再加1
+ 移位操作：int a = n << 1; n左移1位
+ 右移>>时，不会循环部位，对负数右移时，最高位的1不动，结果任然是负数
+ 不带符号的右移操作：>>>
+ 异或运算符：^
+ 对两个整数进行位运算，实际上就是按位对齐，然后依次对每一位进行运算

### 浮点数
+ 在一个(复杂的四则运算中，两个整数的运算不会出现自动提升的情况

### 字符串
+ 字符串是引用类型。操作的是指针

### 数组
+ 数组是引用类型，且数组大小不可变
+ 这里的引用类型跟指针的类型还是有区别。当赋值给引用类型变量时，实际上是把地址赋给变量。

## 流程

### 输入和输出
+ 三种输出函数：System.out.print/println/printf, printf可以格式化输出

### if判断
+ 如果if语句块只有一行语句时，可以省略花括号{}
+ 计算机系统对浮点数的表示是不准确的。比如0.1表示成二进制是无限循环的。故进行等值判断浮点数时，应该用绝对值小于某个程度为等值。不能直接使用==
+ ==表示“引用是否相等”，或者说，是否指向同一个对象。要判断引用类型的变量内容是否相等，要用.equals()函数。
+ 执行语句s1.equals(s2)时，如果变量s1为null，会报NullPointerException

### switch
+ 每个case块后必须加break；如果不加break则后面的case语句也会被执行
+ switch 匹配字符串时是匹配“内容相等”
+ 新语法使用->{dosomething()}执行学则的case部分。并且不需要加break
+ yield可以在switch分支里返回值

### for each循环
+ for （int i ：ns）{}

## 数组
### 遍历数组
+ import java.util.Arrays Arrays.toString(ns);

### 多维数组
+ Arrays.deepToString(ns[][]);可以直接打印多维数组。

### 命令行参数
+ 命令行参数由JVM接收用户输入并传给main方法。命令行参数是String[]数组。（类似于python里的参数解析模块：argparse）

## 面向对象编程
### 方法
+ 类中数据存储的变量和常量称为字段（field）
+ 在类中，this变量始终指向此类。（类似python中的self）。如果没有命名冲突，可以省略this。如果有局部变量和字段重名，那么局部变量优先级更高。
+ 可变参数：用【类型... 变量名】传入。相当于传入一个数组。但是比使用数组作为参数更方便。
+ 基本类型参数的传递，是调用方对传入变量值得复制。
+ 但是对引用类型得传递，调用方的变量和接收方的参数变量，指向的是同一个对象。

### 构造方法
+ 也就是python里面的__init__()函数。构造方法的名称就是类名
+ 如果没有显示定义构造方法，则编译器会自动生成一个空的构造方法
+ 当显式地定义构造方法之后，编译器则不会再生成空的构造方法。但是可以显示地定义两个构造方法，一个带参数，一个不带参数。同理，可以定义多个构造方法
+ 可以用一个构造方法调用另一个构造方法
+ 构造方法没有返回值，定义的时候也不能加void。所以构造方法只能是public Classname（type args）{}

### 方法重载
+ 同样的方法名但是有不同的参数

### 继承
+ class NewClassName extends ParentsName{...}
+ 在没有写extends的时候，默认继承Object类
+ 子类不能访问父类的private 字段。但是可以访问父类的protected字段。protected关键字把字段和方法的访问权限控制在继承树内。
+ 在子类的构造方法内部，编译器会自动先调用父类的构造方法。如果父类的构造方法是带参数的（没有默认构造方法），则需要显式调用：super（）
+ 也就是说，子类不会继承任何父类的构造方法。子类默认的构造方法是编译器自动生成的，不是继承的。
+ 向上转型：把一个子类类型安全地变为父类类型地赋值：Person p = new Student();

### 多态
+ 在继承关系中，子类如果定义了一个与父类方法签名（方法名相同，方法参数相同，方法返回值相同）
完全相同的方法，被称为覆写（override）
+ @override关键字可以让编译器帮助检查是否进行了正确的覆写
+ 多态的特性就是，运行期才能动态决定调用的子类方法
+ 多态具有一个非常强大的功能，就是允许添加更多类型的子类实现功能扩展，却不需要修改基于父类的代码
+ final 标记的方法不能被子类override。同理，如果一个类不希望任何其他类继承它，可以把这个类标记为final

### 抽象类
+ 如果父类的方法本身不需要实现任何功能，仅仅是为了定义方法签名，目的是让子类去覆写它，那么，可以把父类的方法声明为抽象方法
+ public abstract void run（）{。。。}
+ 拥有抽象方法的类必须声明为抽象类：abstract class Person{}， 不然编译器无法正常编译。
+ 因为抽象方法是无法执行的，所以拥有抽象方法的类无法实例化，所以需要声明为抽象类
+ 面向抽象编程：尽量引用高层类型，避免引用实际子类型
	+ 上层代码只定义规范（例如：abstract class Person）；
	+ 不需要子类就可以实现业务逻辑（正常编译）；
	+ 具体的业务逻辑由不同的子类实现，调用者并不关心。

### 接口
+ 没有字段，并且所有方法都是抽象方法， 这样的抽象类可以改写为接口。接口用来定义高层的接口，具体实现由子类实现。
+ interface Classname{}可以声明一个接口。
+ 因为接口的方法都是抽象方法，所以可以省略public abstract
+ 当一个具体的类实现一个interface的时候，用implements关键字：class Classname implements Interface_name {}
+ 一个类可以实现多个interface：class Classname implements Interface1, Interface2 {}
+ 一个interface可以继承自另一个interface

### 静态字段和静态方法
+ 在一个class中定义的字段，称之为实例字段。每个实例字段在每个实例中有独立的空间。与之对应的是静态字段，所有实例会共享该字段
+ 静态字段并不属于实例，而是属于类（class）的字段，所以，所有实例共享一个静态字段。所以，最好不要通过实例访问静态字段，而是通过类名来访问
+ 静态方法类似其他编程语言的【函数】。在python中类似 @staticmethod 修饰器。
+ 由于静态方法属于`class`但不属于实例，所以静态方法内部不能使用`this`变量，也无法访问实例字段，只能访问静态字段。
+ 虽然静态字段和静态方法可以通过实例访问，但其实只是编译器自动把实例改写成类名
+ 接口interface是一个纯抽象类，所以它不能定义实例字段。但是可以定义静态final字段：public static final int MALE =1；
   实际上，因为interface的字段只能是public static final类型，所以可以省略这3个修饰符

### 包
+ 在java中，我们使用package来解决名字冲突。一个类总是属于某个包。类名只是一个简写，真正完整的类名是`包名.类名`
+ 包没有父子关系。java.util和java.util.zip是不同的包，两者没有任何继承关系。import package.*会导入此包下的所有类，但不会导入子包的类
+ 包作用域
	位于同一个包的类，可以访问包内所有其他的类。事实上，java编译器最终编译出的.class文件只使用完整类名。因此，
在代码中当编译器遇到一个简单类名时，按照下面顺序依次查找
	1. 查找当前package是否存在这个class
	2. 查找import的包是否包含这个class
	3. 查找java.lang包是否包含这个class
+ 包名推荐使用倒置的域名，例如：org.apache;oop.apache.commons.log

### 作用域
+ final与访问权限不冲突。用final修饰class可以阻止被继承；用final修饰method可以阻止被子类覆写；用final修饰field可以阻止被重新赋值；用final修饰局部变量可以阻止被重新赋值。
+ 一个.java文件只能包含一个public类，但可以包含多个非public类

### classpath和jar
+ 类似于python里面的sys.path，搜索包位置的路径
+ java在启动时可以传入 -classpath或-cp关键字指明包搜索路径，否则默认为“."也就是当前目录
+ 使用jar包可以把所有.class也就是字节码所有文件打包，在windows可以直接生成zip文件然后改后缀
+ jar包可以包含一个特殊的/META-INF/MANIFEST.MF文件，MANIFEST.MF是纯文本文件，可以指定Main-class和其他信息
+ 如果没有指定的话，则需要在启动编译器的时候手工指定：java -jar xxx.jar xx.xx.xx.main_class_name 现在的IDE应该可以自己生成manifest文件然后打包（maven）

### 模块module
+ java的标准库为rt.jar，注意，不要将其添加进classpath里面，加了反而会影响java执行
+ jar文件只是把class打包，而模块是为了解决依赖关系。从java9开始，标准版rt.jar被分拆为几十个文件，位于$JAVA_HOME/jmods文件夹里，模块的后缀为.jmod
+ 目录下的module-info.java规定了此模块需要引用的其他模块：requires，并声明模块的导出类：exports