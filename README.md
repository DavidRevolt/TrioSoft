# TriSoft App - Firebase integrated
Example of Android app illustrating Firebase authentication, ROOM DB, Retrofit and Modularization strategy. 
built entirely with Kotlin and Jetpack Compose, the app follows the [official architecture guidance](https://developer.android.com/jetpack/guide) as closely as possible.



### Features
*   User authentication via Firebase.
*   Users can add new points.
*   Points being displayed on a graph.
*   Points data saved on both local and remote DB .
*   Get the current weather for Rehovot, IL.

### Highlights
* **MVVM** architecture.
* **ROOM** local database.
* **Firebase** this app integrated with Firebase Authentication, Cloud Firestore.
* **Retrofit** for fetching weather data from the internet.
* **Hilt** dependency injection.
* **Vico** chart library for Android.



  
## Libraries & Dependencies
- [Hilt](https://developer.android.com/training/dependency-injection/hilt-jetpack) - Dependency injection library.
- [Kotlin Coroutines](https://developer.android.com/kotlin/coroutines) - Asynchronous programming.
- [Room](https://developer.android.com/training/data-storage/room) - Saving data in a local database.
- [Navigation](https://developer.android.com/guide/navigation) - Navigation component.
- [Firebase](https://firebase.google.com) - Authentication and cloud components.
- [RetroFit](https://square.github.io/retrofit/) - Type-safe REST client.
- [Vico](https://github.com/patrykandpatrick/vico) - Chart library for Android.


## Setup Requirements
To install this app, follow these steps:
1. Clone or download the project code from the repository.
2. Create Firebase project, download and place google-services.json file in App module.
3. In Firebase console, enable: Authentication and Cloud Firestore.
4. Get [MetaSource](https://www.meteosource.com/) api key and insert it in [_RetrofitAppNetwork.kt_](https://github.com/DavidRevolt/TrioSoft/blob/master/core/network/src/main/java/com/davidrevolt/core/network/retrofit/RetrofitAppNetwork.kt).
5. Build and run the app on an Android emulator or device.


## Modularization
App modularization approach is very similar to [Now in Android](https://github.com/android/nowinandroid/) App which developed by google.

To learn more about this approach check out [modularization learning journey](https://github.com/android/nowinandroid/blob/main/docs/ModularizationLearningJourney.md).


## Vico chart drawing limitations
Vico chart library Drawing limitations:
1. Must have at least 2 points to draw the graph.
2. In some cases If the first point ever created is _Temperature:0°_, _Humidity:0%_, adding more points afterwards might not trigger graph drawing, in that case relaunching the app will fix this issue. 


## Demo
https://github.com/DavidRevolt/TrioSoft/assets/59780440/789f1321-11c9-4060-8098-ee49354e0b04

