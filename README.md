growlbridge
=============

*Sending Growl Messages via Scala*

About
-------

This library allows an application to simply send notifications to the [Growl][1] system.

The core of this library is based off of the Java code created by Tobias 
Södergren in this [blog entry][2] 

License: [Apache License 2.0][3]

Usage Notes
-----------

This library will currently only work with a Java 6 or greater VM 
(though, it still needs to be tested with Java 7).

More importantly, this will only operate on Mac OS X, with Growl installed. 

TODO
-------

- Convert to using the [Growl Notification Transport Protocol (GNTP)][4]; this will allow the bridge to run on windows machines (with Growl for Windows), as well as remove the need for AppleScript.

[1]: http://growl.info
[2]: http://blog.jayway.com/2011/04/12/send-growl-notifications-on-os-x-using-a-java-6-script-engine-and-applescript/
[3]: http://www.apache.org/licenses/LICENSE-2.0.html
[4]: http://growl.info/documentation/developer/gntp.php
