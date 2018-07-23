### 中文版说明
[**Chinese README**](README-chs.md)

# GUI - FormWindow tools

This plugin is made for developers.  
By using **GUI**, you can easily create forms.

## Features
* This plugin provides:
  * `ResponsibleFormWindow`s
  * `ResponseListener`s
  * `ResponsibleElement`(s)

* Functional Methods(lambda) support  
  This will reduce much codes in send TipWindows  
  You can send FormWindow and process only in one line!  
  Sample:  
  `
  player.showFormWindow(new ResponsibleFormWindowModal("Tips", "Hello", "Yes", "No")
.onClicked(confirmation -> player.sendMessage("Your choice:"+confirmation)));
  `

* **Inner** Listeners for each window
  This is very useful!  
  Sample:  
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
  Don't you think it is much easier to create FormWindow by using interface **ResponseListener** instead of another nukkit EventListener?

* Useful implements of ResponsibleFormWindow  
  More useful than ResponsibleFormWindow!
  Sample:
  * **ResponsibleButton** for ResponsibleFormWindowSimple  
    Not only windows but also buttons can have listeners!  
    You think ResponsibleFormWindowSimple is also too trouble to use? --This would be great choice!  
    Sample(comparing with the sample above):

    ```java
    class A extends ResponsibleFormWindowSimple implements SimpleResponseListener {
        A() {
            super("Title", "Content");
            addButton(new ElementButton("Say Hi", player->player.sendMessage("Hi")));
            addButton(new ElementButton("Say Hello", player->player.sendMessage("Hello")));
        }
        public void onClicked(int id, Player player) {
        }
        public void onClosed(Player player) {
            player.sendMessage("You Closed");
        }
    }
    ```
    Yeah! Your codes is more and more conciser, isn't it?

  * ResponsibleFormWindowSimple**Advanced**  
    This is a wonderful implement of ResponsibleFormWindowSimple!
    Please use a minute to check the [source code](src/main/java/moe/him188/gui/window/ResponsibleFormWindowSimpleAdvanced.java)

## How to use
### Maven repository

1. Add `repository` in `repositories`
    ```xml
    <repository>
        <id>him188-gui</id>
        <url>http://repo.him188.moe:8081/repository/gui</url>
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
3. Don't forget add `depend` into `plugin.yml`
    ```yaml
    depend:
    - GUI
    ```