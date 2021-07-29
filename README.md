# FootBallMatchApp
This Application is build on a Multi modules using MVVM, Clean Architecture, MVI design pattrens

The Approach to write this software is pretty much in an isolated way. Below are the brief description of the modules in the software

## Core Libs
1. Koin for DI
2. Jetpack Architecture components
3. Kotlin DSL for Gradle dependencies

## App 
This is the Gateway to the whole application containing DI Initialization and in our case it contain MainActivity to host the application.
On top of that this module have all the dependency which are present in our application
###### Purposes
1. Initializing Dependency Injection
2. Hosting the Application Root 



## Business
This module kept all the use cases belong to the application, on top of that this is UI agnostic module.Having all the business information of the app.
This is utilizing repository module in order to get the business and populate their respective DTOS
###### Purposes
1. To keep the business in a separate module agnostic of UI


## Common
In this module common classes are kept to provide support to the other modules 
###### Purposes
1. Base classes for Fragments & Activities
2. Application Common classes like (Styles,colors,Extensions)
3.Base Mvi CLasses
4. This module don't have any dependency from the project

## Dtos
This is providing the binding capabilities to the UI layer in order to show the data. This is kind of the bridge between Repositories and business module with out having cyclic dependency.This module dont have any dependency from the application
###### Purpose
1. Displaying the Actual data to the UI layer using Mapper


## Network
This module is on the bottom layer of the project. Dealing with repository $ DataSource to get the data from the Network API client. And it can access the DTOS & common module
###### Purpose
1. Dealing with the Retrofit client to get the data



## footballMatch (Main Functionality of app Resides Here)
In this module we are presenting the applications main functionality where we are showcasing the list of football match results data . This module have dependency on business, common, dtos
###### Purpose
1. Display football match  list data




## footballMatchRepository
This module contains footballMatch Repo layer to fetch data from any source either remote or local dat source based on network availability
###### Purpose
1. populating business module


## Gradle's Kotlin DSL
Gradle’s Kotlin DSL is an alternative syntax to the Groovy DSL with an enhanced editing experience.

## buildSrc 
A special module within the project to manage dependencies and whatnot.


##Helping sources 
https://proandroiddev.com/multi-flavoured-kotlin-dsl-build-script-for-android-app-2d51577e76fb
