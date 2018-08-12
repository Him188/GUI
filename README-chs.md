# GUI - FormWindow tools

这是为开发者制作的插件
通过使用 **GUI**, 你可以炒鸡容易地创建表单窗口, 并通过内置的事件方法处理点击和关闭事件!

## Features



### Functional listeners

  **函数式方法(lambda) 支持**

  这将会大幅缩短创建一些表单的代码, 比如提示窗口  
  你可以仅使用一行代码就对玩家发送一个窗口并处理点击按钮事件  
  例子:  
  `
  player.showFormWindow(new ResponsibleFormWindowModal("Tips", "Hello", "Yes", "No")
.onResponded(confirmation -> player.sendMessage("Your choice:"+confirmation)));
  `
  [ResponsibleFormWindowSimpleAdvanced.java](src/main/java/moe/him188/gui/window/ResponsibleFormWindowSimpleAdvanced.java):
  ```java
  public class ResponsibleFormWindowModal extends FormWindowModal implements Backable, ResponseListenerModal {
      public final ResponsibleFormWindowModal onClicked(BiConsumer<Boolean, Player> listener) {
          this.buttonClickedListener = listener;
          return this;
      }
      public final ResponsibleFormWindowModal onClosed(Consumer<Player> listener) {
          this.windowClosedListener = listener;
          return this;
      }
  }
  ```

### ResponseListener

  每个表单都可以有 **内部**事件检测器([ResponseListener](https://github.com/Him188/GUI/blob/master/src/main/java/moe/him188/gui/window/listener/response/ResponseListener.java))
  这将会比实现nk Listener, 监听 PlayerFormRespondedEvent, 调用窗口的等不知道方便到哪里去!!
  例子:
  ```java
  class A extends ResponsibleFormWindowSimple {
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

### ResponseListener & Functional listeners

  ResponseListener 与 函数式监听器可以同时使用  
  注意: **ResponseListener 将会被优先调用**

### ResponsibleButton
  提供给 ResponsibleFormWindowSimple 的 **ResponsibleButton**
  不仅仅是窗口, 甚至按钮都可以监听(就像 Android 那样)!
  是否觉得上面的写法还是很麻烦? --那么这个实现将会是很好的替代!
  例子(可以和上面的相比较):
  ```java
  class A extends ResponsibleFormWindowSimple {
      A() {
          super("Title", "Content");
          addButton("Say Hi", player->player.sendMessage("Hi"));//快速添加
          addButton(new ResponsibleElementButton("Say Hello", player->player.sendMessage("Hello")));//通常添加
      }
      public void onClosed(Player player) {
          player.sendMessage("You Closed");
      }
  }
  ```
  Yeah! 你的代码正在一步步缩小, 不是嘛?!

### ResponsibleFormWindowSimpleAdvanced
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

### ResponsibleFormWindowTemplated
  这是 ResponsibleFormWindow 的实现.
  通过使用它, 你可以通过你自己的 **key** 比如 `enum`, `String` 来获取表单数据.
  模板的 `ElementInput` 拥有内容检查你, 它可以自动检查玩家表单输入是否正确(符合预期格式).
  ```
  比如, 你设置ID为"test"的 Input 只能输入整数, 你在收到返回数据时, 可以通过ID "test" 直接获取玩家的输入内容. 而原本NK表单只有整数ID, 对于项目多达十几个的表单很不方便.
  当玩家提交表单, 模板会将表单数据按照你预期希望的数据类型转换, 因此你可以直接取到整数型, 日期型, 甚至`Level`, `Player`, `Item`类型的数据!(不然, 试想10个输入框, 你需要花几十行判断和转换类型?)
  如果玩家输入数据有误, 你可以选择性地处理错误. 我们提供了 `ExceptionConsumer` 来捕获错误. 也提供了默认的处理方法: 只处理一个错误, 处理每个错误, 集中处理全部错误.
  当你想让玩家重新填写这一项, 你可以连已经填写的其他正确数据, 一起发送表格. 这样能大幅提高表格输入体验.(不然, 试想10个输入框, 填错了一个就全没了?)
  ```
  请查看源码 [`ResponsibleFormWindowTemplated`](https://github.com/Him188/GUI/blob/master/src/main/java/moe/him188/gui/window/ResponsibleFormWindowTemplated.java)
  **或者查看模板示例, 快速了解如何使用**: [template example](https://github.com/Him188/GUI/blob/master/TemplateExample.md)

### Backable
  所有类型的 `ResponsibleFormWindow` 都支持直接返回上一级窗口.  
  并且你只需使用一行代码:
  ```
  window.goBack(player);
  ```
  你也可以获取玩家的上一级窗口, 详细请查看源码 [Backable.java](https://github.com/Him188/GUI/blob/master/src/main/java/moe/him188/gui/utils/Backable.java)

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
        <version>LATEST</version>
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
