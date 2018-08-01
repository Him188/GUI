# GUI - FormWindow tools

这是为开发者制作的插件
通过使用 **GUI**, 你可以炒鸡容易地创建表单窗口, 并通过内置的事件方法处理点击和关闭事件!

## Features
* 这个插件提供:
  * `ResponsibleFormWindow`s
  * `ResponseListener`s
  * `ResponsibleElement`(s)

* 函数式方法(lambda) 支持  
  这将会大幅缩短创建一些表单的代码, 比如提示窗口  
  你可以仅使用一行代码就对玩家发送一个窗口并处理点击按钮事件  
  例子:  
  `
  player.showFormWindow(new ResponsibleFormWindowModal("Tips", "Hello", "Yes", "No")
.onClicked(confirmation -> player.sendMessage("Your choice:"+confirmation)));
  `

* 每个表单都可以有 **内部**事件检测器
  这将会比实现nk Listener, 监听 PlayerFormRespondedEvent, 调用窗口的等不知道方便到哪里去!!
  例子:
  ```java
  class A extends ResponsibleFormWindowSimple implements SimpleResponseListener {
      A() {
          super("Title", "Content");
          addButton(new ElementButton("Say Hi"));
          addButton(new ElementButton("Say Hello"));
      }
      public void onClicked(int id, Player player) {
          switch (id){
              case 0:
                  player.sendMessage("Hi");
                  break;
              case 1:
                  player.sendMessage("Hello");
                  break;
          }
      }
      public void onClosed(Player player) {
          player.sendMessage("You Closed");
      }
  }
  ```

  有没有觉得使用 **ResponseListener** 比使用 nukkit EventListener 方便得多?

* 对 ResponsibleFormWindow 的更多便捷实现!  
  比上面所说的 ResponsibleFormWindow 还要方便一万倍的便捷实现!  
  例子:

  * 提供给 ResponsibleFormWindowSimple 的 **ResponsibleButton**  
    不仅仅是窗口, 甚至按钮都可以监听(就像 Android 那样)!  
    是否觉得上面的写法还是很麻烦? --那么这个实现将会是很好的替代!  
    例子(可以和上面的相比较):
    ```java
    class A extends ResponsibleFormWindowSimple implements SimpleResponseListener {
        A() {
            super("Title", "Content");
            addButton(new ResponsibleElementButton("Say Hi", player->player.sendMessage("Hi")));
            addButton(new ResponsibleElementButton("Say Hello", player->player.sendMessage("Hello")));
        }
        public void onClicked(int id, Player player) {
        }
        public void onClosed(Player player) {
            player.sendMessage("You Closed");
        }
    }
    ```
    Yeah! 你的代码正在一步步缩小, 不是嘛?!
  * ResponsibleFormWindowSimple**Advanced**  
    这是一个非常棒的实现, 为了方便(其实是懒)这里贴上代码里面的 javadoc  
    这里简称 ResponsibleFormWindowSimpleAdvanced 为高级版(2333)  
    简称 ResponsibleFormWindowSimple 为普通版
    ```text
    高级版无需通过 id 自找数据, 一切的一切都由 GUI 帮你完成. 你只需要实现后续处理即可!
    普通版里面, 点击事件函数参数1是 int, 是按钮的 ID.
    并且需要使用 Simple 类型表单的情况, 一般是有一组同一类型的数据需要依次显示. 比如传送世界列表, 任务列表
    在普通版构造器中, 你需要手动对数据遍历并添加按钮, 在普通版click事件中, 你又需要手动从数据(List,Map等)中取出第 n 项, 再处理.
    如果再来一个窗口, 你又要枯燥地重复一切. 那么是时候使用高级版了!

    高级版里面, 由java泛型支持, 你在构造器中需要传入一组数据和构造按钮的函数, 然后在点击事件中直接拿到按钮对应的数据进行处理
    ```
    请花上一分钟时间读一读 [源代码](src/main/java/moe/him188/gui/window/ResponsibleFormWindowSimpleAdvanced.java), 你就会懂了

## How to use
### Maven repository

1. Add `repository` in `repositories`
    ```xml
    <repository>
        <id>him188-gui</id>
        <url>http://repo.him188.moe:8081/repository/gui/</url>
    </repository>
    ```
2. Add `dependency` in `build.dependencies`
    ```xml
    <dependency>
        <groupId>moe.him188</groupId>
        <artifactId>gui</artifactId>
        <version>1.0</version>
    </dependency>
    ```
3. Don't forget to add `depend` into `plugin.yml`
    ```yaml
    depend:
    - GUI
    ```

### Package JAR file

1. 在项目根目录中运行 `mvn clean package`

2. 在 `target/` 中找到构建完成的 jar
