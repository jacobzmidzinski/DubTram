# 🚋🚋🚋 DubTram 🚋🚋🚋

## Screenshot
<p align="center">
<img src="https://i.ibb.co/tX0sr6v/Screenshot-1596553973.png" width="32%"/>
</p>

## Tech stack

### Architecture
  - Clean architecture - I've only used one module in the app, but in bigger projects, I would definietly divide it into multiple modules
    - Domain (Kotlin, Domain specific code - models, interfaces, constants)
    - Data (Kotlin + some libraries like Retrofit - implementations of data fetching, mappers)
    - Presentation (Android - presentation layer with Activities, Fragments, ViewModels)
  - MVVM (Model-View-ViewModel)
  - [Koin](https://insert-koin.io/) for dependency injection

### Libraries
- [Jetpack](https://developer.android.com/jetpack)
  - ViewModel - stores and manages UI-releated data
  - Lifecycle - adjusting behaviour based on the current lifecycle state of Fragment or Activity
  - DataBinding - bind UI components to data sources
  - LiveData - lifecycle-aware observable data holder class
- [Retrofit2](https://github.com/square/retrofit) - for HTTP calls
- [SimpleXML Converter](https://github.com/square/retrofit/tree/master/retrofit-converters/simplexml) - XML response serializer
- Testing
  - [Espresso](https://developer.android.com/training/testing/espresso#additional-resources) - for Android UI Tests
  - [Koin-Test](https://start.insert-koin.io/#/getting-started/testing) - helper library for loading and overriding Koin modules
  - [Mockito](https://github.com/mockito/mockito) - mocking library
  
