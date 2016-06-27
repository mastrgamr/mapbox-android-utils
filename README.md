# Mapbox Android SDK Utility Library (Unofficial)
[![](https://jitpack.io/v/mastrgamr/mapbox-android-utils.svg)](https://jitpack.io/#mastrgamr/mapbox-android-utils)

Utility library for the Mapbox Android SDK. Inspired by [Google Maps Utility library](https://github.com/googlemaps/android-maps-utils)
Consider this the "pre-alpha" release. Google maps SDK and Mapbox have slight differences, so mapping the source is a Work in Progress.
Please submit issues and suggestions to the repo's [issues](https://github.com/mastrgamr/mapbox-android-utils/issues) tab.

View features to come: [HERE](https://github.com/mastrgamr/mapbox-android-utils/blob/master/TODO.md)

##To Use
See [jitpack](https://jitpack.io/#mastrgamr/mapbox-android-utils/v0.3) for details.

tl;dr
*Step 1* - Add this to the root build.gradle at the end of your repository list:
```
allprojects {
		repositories {
			...
			maven { url "https://jitpack.io" }
		}
	}
```
	
*Step 2* - Add the dependency to your app's gradle
```
dependencies {
	        compile 'com.github.mastrgamr:mapbox-android-utils:v0.3'
	}
```

###Thanks
-Google for open sourcing the android utils map library
