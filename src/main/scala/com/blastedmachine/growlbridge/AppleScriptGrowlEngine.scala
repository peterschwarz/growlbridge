package com.blastedmachine.growlbridge

import javax.script.ScriptEngine
import javax.script.ScriptEngineManager

private[growlbridge] object AppleScriptGrowlEngine {
  def apply(application: AppDescriptor,
    availableNotifications: List[String], enabledNotifications: List[String]) = {
    new ScriptEngineManager().getEngineByName("AppleScript") match {
      case scriptEngine: ScriptEngine => {
        val growlEngine = new AppleScriptGrowlEngine(scriptEngine, application, availableNotifications, enabledNotifications)
        if (growlEngine.isGrowlEnabled()) {
          growlEngine
        } else {
          new UnavailableGrowlEngine
        }
      }
      case _ => new UnavailableGrowlEngine
    }

  }

}

private[growlbridge] class AppleScriptGrowlEngine(appleScriptEngine: ScriptEngine, val application: AppDescriptor,
  availableNotifications: List[String], enabledNotifications: List[String]) extends GrowlEngine {

  override def registerApplication() = {
    val script = ScriptBuilder().add("tell application ")
      .quote(Growl.GROWL_APPLICATION)
      .nextRow("set the availableList to ")
      .array(availableNotifications)
      .nextRow("set the enabledList to ")
      .array(enabledNotifications)
      .nextRow("register as application ")
      .quote(application.applicationName)
      .add(" all notifications availableList default notifications enabledList")
      .nextRow("end tell").get()
    executeVoidScript(script)
  }

  override def notify(notification: NotificationDescriptor, title: String, message: String) {
    val script = ScriptBuilder().add("tell application ")
      .quote(Growl.GROWL_APPLICATION)
      .nextRow("notify with name ").quote(notification.name)
      .add(" title ").quote(title)
      .add(" description ").quote(message)
      .add(" application name ").quote(application.applicationName)

    if (!notification.appImage.isEmpty()) {
      script.add("image from location ").filename(notification.appImage)
    } else if (!application.appImage.isEmpty()) {
      script.add("image from location ").filename(application.appImage)
    }
    script.nextRow("end tell");

    executeVoidScript(script.get());
  }

  override def isGrowlEnabled(): Boolean = {
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
