# ContextualCards
 
 This package contains a plug-and-play component (Contextual-Cards) and it's usage in application
 
 ## Idea
 
 - Developed a standalone custom view, that displays a list of `Contextual Cards`
    - A `Contextual Card` is used to refer to a view that is rendered using json from an API
    - These views are dynamic and their properties like images, color, texts, buttons (CTAs) etc. can be changed from backend at anytime.

## Screenshots
<table>
    <tr>
      <td><img src="ss1.jpg" height="400px"</img> </td>
      <td><img src="ss2.jpg" height="400px"</img> </td>
      <td><img src="ss3.jpg" height="400px"</img> </td>
    </tr>
  </table>

## Architecture

The architecture of app follows MVVM Pattern where viewModel deal with accessing repository and loading data based on
which it notifies UI Layer (Activity) about the UI state to be shown and passes the state to Jetpack Compose Components which 
displays the corresponding data on screen.

Language : Kotlin
Architecture: Model View ViewModel (MVVM)
Libraries: Retrofit, StateFlow, ViewModel, Jetpack Compose, Coil, SwiprRefresh, SharedPreference

## Setup Instructions

1. Make sure you've installed Android Studio.

2. Clone this repository in the folder of your preference, and then enter that folder:

```bash
$ git clone https://github.com/adityarajpt/ContextualCards.git
$ cd ContextualCards
```
3. Go to Tools → AVD Manager and create an emulated device.

4. Run.
