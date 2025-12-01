GreedyGame - Quotes Browser App

A modern Android quotes app built with Jetpack Compose, featuring quotes display, banner slider, favorite quotes management, and persistent storage using DataStore Preferences.

📱 Features

Home Screen:

Displays a list of quotes with gradient cards.

Banner slider at the top to showcase featured content.

Long press on a quote navigates to Detail Screen.

Detail Screen:

Shows quote in detail with author.

Toggle favorite using a heart icon.

Favorites Screen:

Displays all quotes marked as favorite.

Updates automatically when favorites change.

Floating Navigation Bar:

A movable, slidable bottom navigation bar.

Can be dragged to different positions on the screen.

Provides quick access to Home and Favorites screens.

Data Persistence:

Favorite quotes are stored using DataStore Preferences (modern replacement for SharedPreferences).

UI reacts automatically to changes in favorites.

Navigation:

Clean navigation using Navigation Component in Compose.

Image Loading:

Quote images loaded asynchronously using Coil.

🏗️ Project Structure
com.example.greedygamequoteappassignment
│
├─ data/
│   ├─ QuoteModel.kt        # Quote data class
│   ├─ BannerModel.kt       # Banner data class
│   └─ datastore.kt         # DataStore implementation for favorites
│
├─ repository/
│   └─ QuoteRepository.kt   # Handles quotes & favorite IDs with DataStore
│
├─ viewModel/
│   └─ QuoteViewModel.kt    # Provides quotes & favorites to UI
│
├─ presentation/
│   ├─ HomeScreen.kt        # Displays quotes + banner slider
│   ├─ DetailScreen.kt      # Shows quote details + favorite toggle
│   ├─ FavouriteScreen.kt   # Displays favorite quotes
│   ├─ BannerPager.kt       # Pager for banners
│   └─ Nav/
│       ├─ AppNavGraph.kt   # Navigation graph
│       └─ QuoteScreen.kt   # Navigation routes
│
├─ ui/theme/                # Fonts, colors, typography
└─ build.gradle

🛠️ Technologies Used

Kotlin – Primary language

Jetpack Compose – Modern UI toolkit

DataStore Preferences – Persistent storage for favorite quotes

Coil – Image loading library

Navigation Compose – For screen navigation

Material 3 – Modern Material Design components

⚡ Dependencies
implementation("androidx.compose.material3:material3:1.1.0")
implementation("androidx.navigation:navigation-compose:2.9.6")
implementation("io.coil-kt:coil-compose:3.3.0")
implementation("androidx.datastore:datastore-preferences:1.1.1")
implementation("androidx.compose.material:material-icons-extended:1.7.8")


Full dependencies are managed via Version Catalog in libs.versions.toml.

🔄 How It Works

UI Layer: Composable screens observe Flow from the ViewModel.

ViewModel: Fetches quotes and favorite IDs from the repository.

Repository: Reads/writes favorite quote IDs to DataStore Preferences.

DataStore: Persistently stores favorites; UI updates automatically on change.

Floating Navigation Bar: Movable and slidable, providing quick navigation without blocking content.



