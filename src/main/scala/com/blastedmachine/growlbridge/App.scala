package com.blastedmachine.growlbridge
import java.io.File

import Growl._

/**
 * @author ${user.name}
 */
object App {

  def main(args: Array[String]) {
    val growl = new Growl(AppDescriptor("Growl Demo", toUrl("src/main/resources/scala-icon.png")),
      List("system", "boss", "spam"),
      List("system", "boss"))
    growl.registerApplication();
    growl.notify(NotificationDescriptor("system", toUrl("src/main/resources/system-icon.png")), "System message", "This seem to be working");
    growl.notify("boss", "From: Big brother", "Get back to work!");
    growl.notify("spam", "Get a diploma", "By going to university");
  }
  
  private def toUrl(extractedLocalValue: java.lang.String): String = {
    new File(extractedLocalValue).toURL.toString
  }

}
