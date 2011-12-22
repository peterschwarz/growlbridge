package com.blastedmachine.growlbridge

case class AppDescriptor(val applicationName: String, val appImage: String = "")

case class NotificationDescriptor(val name: String, val appImage: String = "")


object Growl {
  val GROWL_APPLICATION = "GrowlHelperApp"
  
  implicit def stringToAppDescriptor(name: String) = AppDescriptor(name)
  
  implicit def stringToNotificationDesc(name: String) = NotificationDescriptor(name)  
}

class Growl(val application: AppDescriptor, availableNotifications: List[String], enabledNotifications: List[String]) {

  private val growlEngine: GrowlEngine = AppleScriptGrowlEngine(application, availableNotifications, enabledNotifications)

  def registerApplication() = {
    this.growlEngine.registerApplication()
  }

  def notify(notification: NotificationDescriptor, title: String, message: String) {
    this.growlEngine.notify(notification, title, message)
  }

}