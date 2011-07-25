package com.blastedmachine.growlbridge
import javax.script.ScriptEngine
import javax.script.ScriptEngineManager

object Growl {
  val GROWL_APPLICATION = "GrowlHelperApp"
}

class Growl(val applicationName: String, availableNotifications: List[String], enabledNotifications: List[String]) {

  private var appleScriptEngine: ScriptEngine = null

  def init() = {
    val scriptEngineManager = new ScriptEngineManager();
    appleScriptEngine = scriptEngineManager.getEngineByName("AppleScript");
    if (appleScriptEngine == null) {
      throw new RuntimeException("No AppleScriptEngine available");
    }

    if (!isGrowlEnabled()) {
      throw new RuntimeException("No Growl process was found.");
    }
  }

  def registerApplication() = {
    val script = ScriptBuilder().add("tell application ")
      .quote(Growl.GROWL_APPLICATION)
      .nextRow("set the availableList to ")
      .array(availableNotifications)
      .nextRow("set the enabledList to ")
      .array(enabledNotifications)
      .nextRow("register as application ")
      .quote(applicationName)
      .add(" all notifications availableList default notifications enabledList")
      .nextRow("end tell").get()
    executeVoidScript(script)
  }

  def notify(notificationName: String, title: String, message: String) {
    val script = ScriptBuilder().add("tell application ")
      .quote(Growl.GROWL_APPLICATION)
      .nextRow("notify with name ").quote(notificationName)
      .add(" title ").quote(title)
      .add(" description ").quote(message)
      .add(" application name ").quote(applicationName)
      .nextRow("end tell").get();
    executeVoidScript(script);
  }

  def isGrowlEnabled(): Boolean = {
    val script = ScriptBuilder().add("tell application ")
      .quote("System Events")
      .nextRow("return count of (every process whose name is ")
      .quote(Growl.GROWL_APPLICATION).add(") > 0")
      .nextRow("end tell")
      .get();
    val count = executeScript(script, 0L);
    return count > 0;
  }

  private def executeScript[T](script: String, defaultValue: T): T = {
    try {
      appleScriptEngine.eval(script, appleScriptEngine.getContext()).asInstanceOf[T];
    } catch {
      case _ => defaultValue;
    }
  }

  private def executeVoidScript(script: String): Unit = {
    try {
      appleScriptEngine.eval(script, appleScriptEngine.getContext());
    } catch {
      case _ =>
    }
  }
}