package com.blastedmachine.growlbridge

/**
 * @author ${user.name}
 */
object App {

  def main(args: Array[String]) {
    val growl = new Growl("Growl Demo",
      List("system", "boss", "spam"),
      List("system", "boss"))
    growl.registerApplication();
    growl.notify("system", "System message", "This seem to be working");
    growl.notify("boss", "From: Big brother", "Get back to work!");
    growl.notify("spam", "Get a diploma", "By going to university");
  }

}
