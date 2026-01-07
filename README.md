
# Dashboard Widget System
**Built with Jetpack Compose**

## Overview

This project implements a **dynamic, widget-based dashboard** using **Jetpack Compose**, where the UI is composed of multiple independent widgets such as **Banner widgets** and **List widgets**.


## Architecture Overview

The widget system is built on a **layered architecture** that ensures separation of concerns and scalability:

```
┌─────────────────────────────────────────────────────┐
│                    UI Layer                         │
│  (HomeScreen + Individual Widget Composables)       │
└──────────────────┬──────────────────────────────────┘
                   │
┌──────────────────▼──────────────────────────────────┐
│                ViewModel Layer                      │
│  (HomeViewModel + Per-Widget ViewModels)            │
└──────────────────┬──────────────────────────────────┘
                   │
┌──────────────────▼──────────────────────────────────┐
│                Domain Layer                         │
│            (UseCases + DataState)                   │
└──────────────────┬──────────────────────────────────┘
                   │
┌──────────────────▼──────────────────────────────────┐
│                 Data Layer                          │
│               (Repositories)                        │
└─────────────────────────────────────────────────────┘
                   │
                   |
┌──────────────────▼──────────────────────────────────┐
│                 Network Layer                       │
│                (DataSources)                        │
└─────────────────────────────────────────────────────┘
```

### Component Relationships

```
Dashboard (HomeScreen)
    ├── HomeViewModel
    │   └── GetDashboardWidgetsUseCase
    │       └── DashboardRepository
    │           └── DashboardDataSource
    │
    ├── BannerWidget (instanceId: "banner_1")
    │   └── BannerWidgetViewModel
    │       └── GetBannerDataUseCase
    │           └── BannerRepository
    │               └── BannerDataSource
    │
    ├── BannerWidget (instanceId: "banner_2")
    │   └── BannerWidgetViewModel (separate instance)
    │
    └── ListWidget (instanceId: "list_1")
        └── ListWidgetViewModel
            └── GetListDataUseCase
                └── ListRepository
                    └── ListDataSource
```


---

### Key Features

Each widget in the system:

- **Is independently identifiable** using a unique `instanceId`
- **Manages its own state** through dedicated ViewModels
- **Loads data asynchronously** without blocking other widgets
- **Fault-tolerant** - failures are isolated per widget
- **Scalable** - new widget types can be added without modifying existing code

The architecture follows **MVVM + Unidirectional Data Flow (UDF)** principles, making it production-ready, testable, and maintainable.

---

## Widget Identity Handling

###  How Widget Identity Is Handled

Each widget is uniquely identified using an **`instanceId`**.

```kotlin
sealed class WidgetMeta {
    data class Banner(val instanceId: String) : WidgetMeta()
    data class List(val instanceId: String) : WidgetMeta()
}
```

### Benefits of Using `instanceId`

The `instanceId` enables:

- **Unique Identification** - Distinguishes multiple widgets of the same type
- **Stable Keys** - Provides reliable keys for Compose's `LazyColumn` recomposition
- **ViewModel Isolation** - Creates one ViewModel instance per widget
- **State Preservation** - Ensures correct state retention during configuration changes
- **Independent Lifecycle** - Each widget manages its own lifecycle independently

---

### Stable Keys in LazyColumn

```kotlin
LazyColumn {
    items(
        items = uiState.widgets,
        key = { widget -> widget.instanceId }
    ) { widget ->
        when (widget) {
            is WidgetMeta.Banner ->
                BannerWidget(instanceId = widget.instanceId)
            is WidgetMeta.List ->
                ListWidget(instanceId = widget.instanceId)
        }
    }
}
```

This ensures:

- No unwanted recompositions
- No state mix-up between widgets
- Correct lifecycle handling

---

## How Banner & List Widgets Scale

### Banner Widget Scaling

Rendered using **`LazyRow`**

Each banner widget:

- Has its own ViewModel
- Loads data independently
- Uses `instanceId` during ViewModel creation

```kotlin
val viewModel: BannerWidgetViewModel = koinViewModel(
    parameters = { parametersOf(instanceId) }
)
```

This allows:

- Multiple banner widgets on the same screen
- Different banner data sources (cars, bikes, movies, etc.)
- Independent loading, error, and empty states

### List Widget Scaling

Rendered vertically using **`LazyColumn`**

Each list widget is:

- Fully isolated
- Stateful
- Independent of other widgets

List widgets can scale to support:

- Pagination
- Filtering
- Sorting
- Retry mechanisms
- Server-driven configuration

### Adding a New Widget Type

To add a new widget:

1. Add a new `WidgetMeta` type
2. Create a ViewModel for the widget
3. Create the Composable UI
4. Handle it in the `when(widget)` block

 **No existing widgets need modification**, making the system highly extensible.

---

## List Widget State Management

Each widget manages its own state flow using a dedicated ViewModel.

### Domain State

```kotlin
sealed class DataState<out T> {
    object Loading : DataState<Nothing>()
    data class Success<T>(val data: T) : DataState<T>()
    data class Error(val message: String) : DataState<Nothing>()
    object Empty : DataState<Nothing>()
}
```

### UI State

```kotlin
sealed class BannerUiState {
    object Loading : BannerUiState()
    data class Success(val widgets: List<BannerItem>) : BannerUiState()
    data class Error(val message: String) : BannerUiState()
    object Empty : BannerUiState()
}
```

### State Flow Pipeline

```
Repository → DataState
UseCase    → DataState
ViewModel  → UI State
UI         → Composable Rendering
```
**Step-by-step flow:**

1. **Repository** fetches data and wraps it in `DataState`
2. **UseCase** processes the `DataState` and applies business logic
3. **ViewModel** transforms `DataState` to widget-specific `UiState`
4. **UI** observes `UiState` and renders accordingly


### Why This Works Well

- Each widget loads independently
- Failures are isolated per widget
- Dashboard remains usable under partial failure
- State is predictable and testable

---

## Why ViewModel Constructor Injection Is Used

Each widget ViewModel receives `instanceId` via **constructor injection**, not via `LaunchedEffect`.

```kotlin
internal class BannerWidgetViewModel(
    instanceId: String,
    getBannerData: GetBannerDataUseCase
) : ViewModel() {
    ...
}
```

### Benefits

- ViewModel fully initialized at creation
- Business logic stays out of UI layer
- No duplicate recomposition
- Lifecycle-aware loading via `viewModelScope`

This aligns with **best practices** for scalable Compose apps.

---

## What Can Be Improved Next

### 1 Backend-Driven Widgets

- Fetch widget configuration from server
- Control widget order, visibility, and type dynamically

### 2 Caching & Performance

- Cache widget data (memory / database)
- Avoid unnecessary reloads
- Improve startup performance

### 3 Pagination & Partial Loading

- Load large lists incrementally
- Reduce memory usage for heavy dashboards

### 4 Animations & UX

- Animate Loading → Success transitions
- Add shimmer placeholders
- Improve perceived performance

### 5 Per-Widget Retry

- Retry only failed widgets
- Keep dashboard usable under partial failures

---

## Key Takeaways

This widget system demonstrates **production-ready Android development** with:

### Architecture

- **Clean MVVM** with clear separation of concerns
- **Unidirectional Data Flow** for predictable state management
- **Dependency Injection** using Koin for testability
- **Repository Pattern** for data abstraction

### Scalability

- **Widget Identity** system enabling multiple instances
- **Easy Extensibility** - add new widgets without modifying existing code
- **Independent Lifecycles** - each widget manages its own state
- **Server-Driven UI** ready - designed for backend configuration

### Best Practices

- **Constructor Injection** for ViewModels
- **Stable Keys** in lazy lists for proper recomposition
- **Isolated State** preventing cascading failures
- **Type-Safe State** with sealed classes
- **Lifecycle-Aware** coroutines with `viewModelScope`

### User Experience

- **Fault Tolerance** - partial failures don't break the dashboard
- **Loading States** - clear feedback during data fetches
- **Error Handling** - graceful degradation with retry options
- **Empty States** - informative messaging when no data available

---

## Summary

This Dashboard Widget System showcases a **modern, scalable architecture** for building dynamic, widget-based UIs in Jetpack Compose. The design supports large dashboards with multiple widget instances, handles failures gracefully, and provides an excellent foundation for future enhancements like server-driven UI, caching, and pagination.

<img src = "https://github.com/user-attachments/assets/b1356285-0226-47d5-a5c5-1b437a404c4b" height = "750" width = "350" />
<img src = "https://github.com/user-attachments/assets/6369598c-8ecc-4872-acb7-d4a0bfea6556" height = "750" width = "350" />
<img src = "https://github.com/user-attachments/assets/69e41320-7ae0-44ac-8c45-efa3080da434" height = "750" width = "350" />


The implementation follows Android best practices and demonstrates expertise in:

- **Clean Architecture** principles
- **Jetpack Compose** declarative UI
- **State Management** with Kotlin Flow
- **Testability** through dependency injection
- **Scalability** through modular design

Perfect for production applications requiring flexible, maintainable, and performant dashboard experiences.
