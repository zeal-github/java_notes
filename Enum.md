# 枚举 enum 类型

## enum 的定义
```
enum Weekday {
    SUN, MON, TUE, WED, THU, FRI, SAT;
}
```
解读：enum 这里是类型声明；Weekday是类名; SUN等是Weekday的常量实例；因此，在类Weekday里面还可以定义一下其他方法和字段。
+ enum常量本身带有类型信息，即Weekday.SUN类型是Weekday，编译器会自动检查出类型错误
+ 不同类型的枚举不能互相比较或者赋值，因为类型不符

## enum 的比较
使用enum定义的枚举类是一种引用类型。前面我们讲到，引用类型比较，要使用equals()方法，如果使用==比较，它比较的是两个引用类型的变量是否是同一个对象。因此，引用类型比较，要始终使用equals()方法，但enum类型可以例外
+ 可以直接使用"=="做比较，因为enum类型的每个常量在JVM中只有一个唯一实例

## enum类&方法
+ 编译后的enum类和普通class并没有任何区别。但是我们自己无法按定义普通class那样来定义enum，必须使用enum关键字，这是Java语法规定的
+ 定义的enum类型总是继承自java.lang.Enum，且无法被继承
+ 只能定义出enum的实例，而无法通过new操作符创建enum的实例
+ 定义的每个实例都是引用类型的唯一实例
+ 可以将enum类型用于switch语句
+ name()返回常量名：String s = Weekday.SUN.name(); // "SUN"
+ ordinal()返回定义的常量的顺序，从0开始：int n = Weekday.MON.ordinal(); // 1
+ 默认情况下，对枚举常量调用toString()会返回name()一样的字符。直接调用枚举常量就是调用toString()方法。
但是toString()可以被覆写，但是name()不可以。覆写toString()是为了输出时更有可读性。
```
public class Main {
    public static void main(String[] args) {
        Weekday day = Weekday.SUN;
        if (day.dayValue == 6 || day.dayValue == 0) {
            System.out.println("Today is " + day + ". Work at home!");
        } else {
            System.out.println("Today is " + day + ". Work at office!");
        }
    }
}

enum Weekday {
    MON(1, "星期一"), TUE(2, "星期二"), WED(3, "星期三"), THU(4, "星期四"), FRI(5, "星期五"), SAT(6, "星期六"), SUN(0, "星期日");

    public final int dayValue;
    private final String chinese;

    private Weekday(int dayValue, String chinese) {
        this.dayValue = dayValue;
        this.chinese = chinese;
    }

    @Override
    public String toString() {
        return this.chinese;
    }
}
```