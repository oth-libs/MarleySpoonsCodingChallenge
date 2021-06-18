# MarleySpoonsCodingChallenge

## Intro

This project is written in Kotlin and following MVVM Clean Architecture principles, with Room/StateFlow/LiveData/Coroutines/DataBinding.


## Build and run

Simply clone the project, import into Android Studio and run.

## Modules

It contains 4 modules in total: 



* **app**: Contains the views fragment/activity and the adapters, as well as custom views, view helper classes, extensions and data binding adapters.
* **presentation**: Contains the ViewModel declaration 
* **domain**: Contains the Repository interface and UseCases 
* **data**: Contains the Repository implementation, the retrofit api calls and room database

## Testing

Each module contains a set of unit tests



## Libraries

* Coroutines
* Koin
* Jetpack (Navigation, Room, Data Binding, Lifecycle, LiveData, ViewModel)
* Kotlinx Serialization
* Jakewharton retrofit2-kotlinx-serialization-converter
* Retrofit
* Picasso
* JUnit
* Mockk


## Code
### Base Fragment 

[**BaseFragment**](https://github.com/oth-libs/MarleySpoonsCodingChallenge/blob/master/app/src/main/java/io/marleyspoonscodingchallenge/BaseFragment.kt)

this BaseFragment takes ```<BINDING : ViewDataBinding, VIEW_MODEL : BaseViewModel>``` and injects the ViewModel automatically and binds it with the view, this way the Fragments won't need to worry about injecting ViewModels.

### Data Source helpers 

[**SourceResultHolder**](https://github.com/oth-libs/MarleySpoonsCodingChallenge/blob/master/domain/src/main/java/io/marleyspoonscodingchallenge/domain/datasource/DataSourceResultHolder.kt)

A generic class that holds a value with its loading status.


[**DataSourceFlow**](https://github.com/oth-libs/MarleySpoonsCodingChallenge/blob/master/data/src/main/java/io/marleyspoonscodingchallenge/data/datasource/DataSourceFlow.kt)

This file contains one important functions:

**```resultFlow()```** will execute the given ```selectQuery``` and return the cache data, ```networkCall``` to call the api, and save into room using ```insertResultQuery``` all while mapping between appropriate models, returning a ```Flow``` to observe in the view model. 


[**RemoteDataSource**](https://github.com/oth-libs/MarleySpoonsCodingChallenge/blob/master/data/src/main/java/io/marleyspoonscodingchallenge/data/remote/datasource/RemoteDataSource.kt)

**```getRemoteResult```** will execute the ```call``` function, generally coming from a retrofit service, and map it using the ```transform``` function. This method automatically checks on network state and handles errors, and returns the right ```SourceResultHolder``` wrapper.

[**RoomDataSource**](https://github.com/oth-libs/MarleySpoonsCodingChallenge/blob/master/data/src/main/java/io/marleyspoonscodingchallenge/data/room/datasource/RoomDataSource.kt)

**```getRoomResult```** will execute the ```call``` function, generally a database call, and map it using the ```transform``` function.


All of these files can be put together with Retrofit services and have a seamless Repository implementation for use cases.


## Demo

<https://www.dropbox.com/s/ip4rkw02j7z2cix/marleyspoons.webm?dl=0> 



