package com.blastedmachine.growlbridge
import java.io.File

/**
 * @author ${user.name}
 */
object App {

  def main(args: Array[String]) {
    val growl = new Growl(AppDescriptor("Growl Demo", new File("src/main/resources/scala-icon.png").toURL().toString),
      List("system", "boss", "spam"),
      List("system", "boss"))
    growl.registerApplication();
    growl.notify("system", "System message", "This seem to be working");
    growl.notify("boss", "From: Big brother", "Get back to work!");
    growl.notify("spam", "Get a diploma", "By going to university");
  }

}
