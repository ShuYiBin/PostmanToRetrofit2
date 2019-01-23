# PostmanToRetrofit2
This is a Android Studio plugin can generate Retrofit2's java code from postman's collection.

##Install
- Clone or download **Retrofit2Plugin.jar**

- Open Android Studio
	- <kbd>Preferences</kbd>  > <kbd>Plugins</kbd> > <kbd>Install plugin from disk..</kbd> > import **Retrofit2Plugin.jar**

- Restart Android Studio 

##Usage
###Import Retrofit2
gradle

	implementation 'com.squareup.retrofit2:retrofit:2.5.0'

###Import RxJava2
gradle

	implementation "io.reactivex.rxjava2:rxjava:2.0.1"
	
###Create ApiService.java

<kbd>Right click</kbd>  > <kbd>Generate</kbd> > <kbd>Retrofit2Generator</kbd> > Paste your postman collection > <kbd>OK</kbd>

