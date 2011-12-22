package com.blastedmachine.growlbridge

private[growlbridge] trait GrowlEngine {

  def isGrowlEnabled(): Boolean

  def registerApplication()

  def notify(notification: NotificationDescriptor, title: String, message: String)
}

private[growlbridge] class UnavailableGrowlEngine extends GrowlEngine {
  override def isGrowlEnabled(): Boolean = false

  override def registerApplication() = {
    // TODO: Log it?
    println("Growl is unavailable")
  }

  override def notify(notifcation: NotificationDescriptor, title: String, message: String) = {
    // TODO: Log it?
    println("Growl is unavailable")
  }
}
