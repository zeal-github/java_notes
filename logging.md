# logging
作用跟python的logging一样。
+ 可以设置输出样式，避免自己每次都写"ERROR: " + var；
+ 可以设置输出级别，禁止某些级别输出。例如，只输出错误日志；
+ 可以被重定向到文件，这样可以在程序运行结束后查看日志；
+ 可以按包名控制日志级别，只输出某些包打的日志；

代码跟python很像：
```
import java.util.logging.Level;
import java.util.logging.Logger;

public class Hello {
    public static void main(String[] args) {
        Logger logger = Logger.getGlobal();
        logger.info("start process...");
        logger.warning("memory is running out...");
        logger.fine("ignored.");
        logger.severe("process will be terminated...");
    }
}
```

## 日志级别
一共七个级别，由严重到普通分别为：
+ SEVERE
+ WARNING
+ INFO
+ CONFIG
+ FINE
+ FINER
+ FINEST
默认级别时INFO，INFO级别以下的日志，不会被打印出来
使用Java标准库内置的Logging有以下局限：

Logging系统在JVM启动时读取配置文件并完成初始化，一旦开始运行main()方法，就无法修改配置；
配置不太方便，需要在JVM启动时传递参数-Djava.util.logging.config.file=<config-file-name>。
因此，Java标准库内置的Logging使用并不是非常广泛。更方便的日志系统我们稍后介绍。

## commons Logging
和Java标准库提供的日志不同，Commons Logging是一个第三方日志库，它是由Apache创建的日志模块。
Commons Logging的特色是，它可以挂接不同的日志系统，并通过配置文件指定挂接的日志系统。默认情况下，Commons Loggin自动搜索并使用Log4j（Log4j是另一个流行的日志系统），如果没有找到Log4j，再使用JDK Logging。

代码：
```
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
public class Main {
    public static void main(String[] args) {
        Log log = LogFactory.getLog(Main.class);
        log.info("start...");
        log.warn("end.");
    }
}
```
注意到实例变量log的获取方式是LogFactory.getLog(getClass())，虽然也可以用LogFactory.getLog(Person.class)

此外，Commons Logging的日志方法，例如info()，除了标准的info(String)外，还提供了一个非常有用的重载方法：info(String, Throwable)，这使得记录异常更加简单：
```
try {
    ...
} catch (Exception e) {
    log.error("got exception!", e);
}
```

## Log4j
+ commons logging 使用的Log4包是通过配置文件来配置的。不是通过调用方法配置的。
+ 使用的时候需要把Log4j和commons logging的包加入classpath
+ 在开发阶段，始终使用Commons Logging接口来写入日志，并且开发阶段无需引入Log4j。如果需要把日志写入文件， 只需要把正确的配置文件和Log4j相关的jar包放入classpath，就可以自动把日志切换成使用Log4j写入，无需修改任何代码。


## SLF4J 和Logback
+ 其实SLF4J类似于Commons Logging，也是一个日志接口，而Logback类似于Log4j，是一个日志的实现
示例：
```
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class Main {
    final Logger logger = LoggerFactory.getLogger(getClass());
}
```
+ 配置文件也是使用xml文件放置在同一个classpath路径
+ 从目前的趋势来看，越来越多的开源项目从Commons Logging加Log4j转向了SLF4J加Logback。
![./images/commons logging&slf4j.png]