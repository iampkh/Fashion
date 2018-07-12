# Task:Demo Fashion app 
B. A base for an app that will be developed and maintained for a long time 


## Description

A mini Fashion application for user with basic functionalities such as.
1. Login Screen (User login with valid email name and any dummy password)
2. Fashion item list categorized as follows with basic information of the item.
    * All
    * Men
    * Women

As this is a initial version, the application is created by thinking of future enhancement.
 * Design followed : MVP

## Getting Started

### Dependencies

* Android Studio 3.1.2 or above.
* Libraries (used)
  * RxAndroid
  * ButterKnife
  * Retrofit
  * Picasso 
  * CardView
  * RecyclerView
  * Mockito.

* Note
  The app developed environement is Ubuntu-32 bit version + Android studio (3.1.2)
  Due to this , android studio's latest libraries are not used for the development.
  (eg)
  * Dagger2
  * Java 8 (Functional Programming[lambda])

### Installing

* step 1: https://github.com/iampkh/Fashion.git
* step 2: File -> open -> "Select the Fashion project" from Android studio.
* step 3: select the "Sync" to download the required libraries.
* step 4: build the project.
* or Open -> Output-> adb install Fashion.apk #(run this command in command line)

## Note :
- Need to update the implementation with Dagger2.


## Authors

Contributors names and contact info

 * Prakash P [ pkh.lucid@gmail.com ]

## Version History
* 0.5
    * Added a sample Junit using Mockito library
    * Submited the binaries (screen shot, apk , release notes)
* 0.4
    * Updated the state of each fragment.
    * (maintaing the state, so that even though after fragment instance is destroyed in the memory.
      when the pager is scrolled to old position new  fragment instance with users's last seen item will be loaded)
* 0.3
    * FAB button implementation 
* 0.2
    * Added Fashion category and list screen using RxAndroid.
* 0.1
    * Initial release - login  screen with MVP model.

##Output Screen shot
![Alt text](OutPut/1.LoginScreen.png?raw=true "Login Screen")
![Alt text](OutPut/2.ItemListScreen.png?raw=true "ItemList UI as expected")

## License
This project is licensed under open source GPLV2.


