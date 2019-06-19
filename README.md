**GUI - FormWindow tools**  
**(中文版说明)**[**Chinese README**](https://github.com/Him188/GUI/blob/master/README-chs.md)

# Introduction

This plugin is made for developers.  
By using **GUI**, you can easily create forms.  
**Contribution is welcomed**

# Download [![Build Status](https://travis-ci.org/Him188/GUI.svg?branch=master)](https://travis-ci.org/Him188/GUI)
- [TeamCity](http://repo.him188.moe:2333/viewType.html?buildTypeId=gui_Build)

# Features

## ResponseListener

  ([ResponseListener.java](https://github.com/Him188/GUI/blob/master/src/main/java/moe/him188/gui/window/listener/response/ResponseListener.java))  
  This is very useful!  
    Sample:
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

  Don't you think it is much easier to create FormWindow by using interface **ResponseListener** instead of another nukkit EventListener?

## Functional listeners

  **Functional method(lambda) support**

  This will reduce much codes in send TipWindows  
  You can send FormWindow and process only in one line!  
  Sample:  
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
### Using Listeners

  ResponseListener and Functional listeners can be used at the same time  
  Notice that: **ResponseListener will be called before Functional listeners**

## **ResponsibleButton**

  **ResponsibleButton** for ResponsibleFormWindowSimple  
  Not only windows but also buttons can have listeners!  
  You think ResponsibleFormWindowSimple is also too trouble to use? --This would be great choice!  
  Sample(comparing with the sample above):

  ```java
  class A extends ResponsibleFormWindowSimple {
      A() {
          super("Title", "Content");
          addButton("Say Hi", player->player.sendMessage("Hi"));//fast addButton
          addButton(new ResponsibleElementButton("Say Hello", player->player.sendMessage("Hello")));//common addButton
      }
      public void onClosed(Player player) {
          player.sendMessage("You Closed");
      }
  }
  ```

## ResponsibleFormWindowSimpleAdvanced

  ResponsibleFormWindowSimple**Advanced**  
  This is a wonderful implement of ResponsibleFormWindowSimple!  
   (I can't translate this paragraph  into English)
  ```text
  高级版无需通过 id 自找数据, 一切的一切都由 GUI 帮你完成. 你只需要实现后续处理即可!
  普通版里面, 点击事件函数参数1是 int, 是按钮的 ID.
  并且需要使用 Simple 类型表单的情况, 一般是有一组同一类型的数据需要依次显示. 比如传送世界列表, 任务列表
  在普通版构造器中, 你需要手动对数据遍历并添加按钮, 在普通版click事件中, 你又需要手动从数据(List,Map等)中取出第 n 项, 再处理.
  如果再来一个窗口, 你又要枯燥地重复一切. 那么是时候使用高级版了!

  高级版里面, 由java泛型支持, 你在构造器中需要传入一组数据和构造按钮的函数, 然后在点击事件中直接拿到按钮对应的数据进行处理
  ```
  Please use a minute to check the [source code](src/main/java/moe/him188/gui/window/ResponsibleFormWindowSimpleAdvanced.java)

## ResponsibleFormWindowTemplated

  extends `ResponsibleFormWindow`  
  By using it, you can get responses from your custom **key** such as `enum`, `String`  
  The template has checkers(parsers) to `TemplateElementInput`, It can automatically check whether the input is correct.  
  (I can't translate this paragraph  into English)
  ```
  比如, 你设置ID为"test"的 Input 只能输入整数, 你在收到返回数据时, 可以通过ID "test" 直接获取玩家的输入内容. 而原本NK表单只有整数ID, 对于项目多达十几个的表单很不方便.
  当玩家提交表单, 模板会将表单数据按照你预期希望的数据类型转换, 因此你可以直接取到整数型, 日期型, 甚至`Level`, `Player`, `Item`类型的数据!(不然, 试想10个输入框, 你需要花几十行判断和转换类型?)
  如果玩家输入数据有误, 你可以选择性地处理错误. 我们提供了 `ExceptionConsumer` 来捕获错误. 也提供了默认的处理方法: 只处理一个错误, 处理每个错误, 集中处理全部https://github.com/Him188/GUI/blob/master/src/main/java/moe/him188/gui/window/ResponsibleFormWindowSimpleAdvanced.java错了一个就全没了?)
  ```
  Please check the [source code](src/main/java/moe/him188/gui/window/ResponsibleFormWindowTemplated.java)  
  Or fast know hot wo use: [template example](TemplateExample.md)

## Backable

  Each `ResponsibleFormWindow` supports directly back to the window which has been sent before.  
  And you just need to code one line:
  ```
  window.goBack(player);
  ```
  You can also get players' latest windows, please view [Backable.java](https://github.com/Him188/GUI/blob/master/src/main/java/moe/him188/gui/utils/Backable.java)

## How to use

### Maven repository

1. Add `repository` in `repositories`
    ```xml
    <repository>
        <id>him188-gui</id>
        <url>http://repo.him188.moe:8081/repository/public/</url>
    </repository>
    ```
    This repo may permanently work.
2. Add `dependency` in `build.dependencies`
    ```xml
    <dependency>
        <groupId>moe.him188</groupId>
        <artifactId>gui</artifactId>
        <version>LATEST</version>
    </dependency>
    ```
    Sometimes the LATEST version sign does not work, in this case, you need to use the latest version, such as `1.13.4` instead.
    And, you can get the latest version in `pom.xml`
3. Don't forget to add `depend` into `plugin.yml`
    ```yaml
    depend:
    - GUI
    ```

### Package JAR file

1. Run command `mvn clean package` in project root path

2. Find JAR in `target/`
