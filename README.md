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
  `player.showFormWindow(new ResponsibleFormWindowModal("Tips", "Hello", "Yes", "No")
   .onClicked(confirmation -> player.sendMessage("Your choice:"+confirmation)));`

* **Inner** Listeners for each window
  This is very useful!  
  Sample:  
  <br />
      <xmp>class A extends ResponsibleFormWindowSimple implements SimpleResponseListener {</xmp>  
      <xmp>    A() {</xmp>  
      <xmp>        super("Title", "Content");</xmp>  
      <xmp>        addButton(new ElementButton("Say Hi"));</xmp>  
      <xmp>        addButton(new ElementButton("Say Hello"));</xmp>  
      <xmp>    }</xmp>  
      <xmp>    public void onClicked(int id, Player player) {</xmp>  
      <xmp>        switch (id){</xmp>  
      <xmp>            case 0:</xmp>  
      <xmp>                player.sendMessage("Hi");</xmp>  
      <xmp>                break;</xmp>  
      <xmp>            case 1:</xmp>  
      <xmp>                player.sendMessage("Hello");</xmp>  
      <xmp>                break;</xmp>  
      <xmp>        }</xmp>  
      <xmp>    }</xmp>  
      <xmp>    public void onClosed(Player player) {</xmp>  
      <xmp>        player.sendMessage("You Closed");</xmp>  
      <xmp>    }</xmp>  
      <xmp>}</xmp>
  Don't you think it is much easier to create FormWindow by using interface **ResponseListener** instead of another nukkit EventListener?

* Useful implements of ResponsibleFormWindow  
  More useful than ResponsibleFormWindow!
  Sample:
  * **ResponsibleButton** for ResponsibleFormWindowSimple
    You think ResponsibleFormWindowSimple is also too trouble to use? --This would be great choice!  
    Sample(comparing with the sample above):  
    <xmp>    </xmp>addButton(new ElementButton("Say Hi", **player->player.sendMessage("Hi")**));  
    <xmp>    </xmp>addButton(new ElementButton("Say Hello", **player->player.sendMessage("Hello")**));  
    Yeah! Your codes is more and more conciser, isn't it?
  * ResponsibleFormWindowSimple**Advanced**
    This is a wonderful implement of ResponsibleFormWindowSimple!
    Please use a minute to check the [source code](src/main/java/moe/him188/gui/window/ResponsibleFormWindowSimpleAdvanced.java)

## How to use
### Maven repository

1. Add `repository` in `repositories`

    <xmp>    <repository></xmp>  
    <xmp>        <id>him188-gui</id></xmp>  
    <xmp>        <url>http://repo.him188.moe:8081/repository/gui</url></xmp>  
    <xmp>    </repository></xmp>

2. Add `dependency` in `build.dependencies`

    <xmp><dependency></xmp>  
    <xmp>    <groupId>moe.him188</groupId></xmp>  
    <xmp>    <artifactId>gui</artifactId></xmp>  
    <xmp>    <version>1.0</version></xmp>  
    <xmp></dependency></xmp>

3. Don't forget add `depend` into `plugin.yml`