# JavaBean

+ 如果类里面定义了若干private实例字段，并且通过public方法来都读写这些字段，并且读写方法符合以下命名规范
	+ 读方法： public Type s=getXyz（）
	+ 写方法： public void setXyz（Type value）
	+ boolean 的读方法命名为：isXyz（）
	+ 则这种class被称为JavaBean
+ 属性（property）：一组对应的读方法（getter）和写方法（setter）称为属性
+ 属性是一种通用的叫法，并非java语法规定
+ 只有getter的属性称为只读属性，只有setter的属性称为只写属性

+ 枚举JavaBean的属性
```
import java.beans.*;
public class Main {
    public static void main(String[] args) throws Exception {
        BeanInfo info = Introspector.getBeanInfo(Person.class);
        for (PropertyDescriptor pd : info.getPropertyDescriptors()) {
            System.out.println(pd.getName());
            System.out.println("  " + pd.getReadMethod());
            System.out.println("  " + pd.getWriteMethod());
        }
    }
}
```

