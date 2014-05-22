-optimizationpasses 5
-dontusemixedcaseclassnames
-dontskipnonpubliclibraryclasses
-dontpreverify
-verbose
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*

-ignorewarnings

-libraryjars ./libs/commons-httpclient-3.1.jar
-libraryjars ./libs/chardet-1.0.jar
-libraryjars ./libs/httpmime-4.1.3.jar
-libraryjars ./libs/commons-logging-1.1.jar
-libraryjars ./libs/commons-codec.jar 
-libraryjars ./libs/cpdetector_1.0.10.jar
-libraryjars ./libs/jpush-sdk-release1.5.0.jar
-libraryjars ./libs/android-support-v4.jar
-libraryjars ./libs/gson-2.2.2.jar
-libraryjars ./libs/guomob_android_sdk.jar

-keep public class * extends cn.jpush.android.** {*;}
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class com.android.vending.licensing.ILicensingService
-keep public class * extends android.os.Handler
-keep class org.jsoup.** {*;}
-keep class com.tencent.weibo.** {*;}
-keep class com.nostra13.universalimageloader.** {*;}
-keep class android.support.v4.** {*;}
-keep class it.sephiroth.android.library.** {*;}
-keep class net.simonvt.menudrawer.** {*;}


-keepclasseswithmembernames class * {
    native <methods>;
}

-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
}

-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet, int);
}

-keepclassmembers class * extends android.app.Activity {
   public void *(android.view.View);
}

-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}

-keepnames class * implements java.io.Serializable


-keepclassmembers class * implements java.io.Serializable {
   static final long serialVersionUID;
   private static final java.io.ObjectStreamField[] serialPersistentFields;
   !static !transient <fields>;
   private void writeObject(java.io.ObjectOutputStream);
   private void readObject(java.io.ObjectInputStream);
   java.lang.Object writeReplace();
   java.lang.Object readResolve();
}
-keepclassmembers class **.R$* {
    public static <fields>;
}