# JetList
JetList is a sample Jetpack compose app which allows users to add names to a list and dynamically update the list with newly added names.

# Tech Stack
:writing_hand: Jetpack Compose <br>
:link: Hilt <br>
:file_folder: Room <br>
:paintbrush: Material 3 Components <br>
:loop: Kotlin Flow <br>
:building_construction: MVVM


# Features
This app  demonstrates a simple usecase of adding data to room database and showing data from room dynamicall using Kotlin Flow. The app also demonstrates
use of Hilt for dependency injection. Hilt is used to inject Room DAO into a repository constructor which is responsible to bridge the data flow from Room to
the ViewModel.

ViewModel allows Activity to collect the Flow in the Composables using `collectAsState()` to keep the Room DB updates dynamic.

## Using Room ##
We are using Room database to persist all the added names to a local database to demonstrate the dynamic nature of Flows in Jetpack Compose's `LazyColumn`.

## Using Material 3 ##
This project entirely uses Material 3 components such as `SmallTopAppBar`, `OutlinedTextField`,  `TextButton`, etc. The basic idea was to bring out a Material3 look and feel.

## Using Hilt ##
Here's how Hilt works in this app-

1. Create an Application class `JetListApplication` and annotate it with `@HiltAndroidApp` to indiate that this app will be using Hilt for dependency injections <br>
2. Next we mark `MainActivity` with `@AndroidEntryPoint` annotation to let the activity use Hilt. 
3. Create a entity for Room - `Names` and DAO for CRUD operations - `NamesDao` <br>
4. Create a repository class- `NamesRepository` which acts a layer of abstraction  betweek the `MainActivityViewModel` and Room database.
5. `NamesDao` is constructor injected into the `NamesRepository` class with `@Singleton`. We scope it to application level to allow `MainAcitvityViewModel` to use the same room database everywhere.
6. But Room and it's APIs come from outside the scope of the application therefore, we need a way to provide it's dependencies to the `NamesRepository` class. We use `DatabaseModule` to serve this purpose. Annotate `DatabaseModule` with `@InstallsIn(SingletonComponent::class)` to tell Hilt where these DAO bindings will be available and `@Module` to indicate to Hilt that this is a module.
> Modules are used to add bindings to Hilt, or in other words, to tell Hilt how to provide instances of different types.
7. Annotate `provideNamesDao()` with `@Provides`. This indicates that Hilt will now provide the DAO for constructor injection in `NamesRepository`. But `provideNamesDao()` is passed the room database, `AppDatabase` as a argument. We need to tell hilt where to fetch the database from as well.
8. Annotate `provideAppDatabase()` with `@Provides` to fetch/_provide_ the room database. 
9. Next we need to let Hilt know the implementation of the `NamesRepository` class i.e. `NamesRepositoryImpl`. We use `@Bind` to annotate `NamesRepository` to make it's impementation available to the ViewModel class.

## Screenshot ##
<img src="https://github.com/PriyaSindkar/JetList/blob/master/screenshots/Screenshot_20230328-114513.png?raw=true" width="200" height="400"/>


