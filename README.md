# Gallery_app

## Introduction

Gallery App is an Android application that allows users to search for the top images of the week from
the Imgur gallery and displays them in a list.

## Installation

To compile and run the application, follow these steps:

1. Clone the repository to your local machine:
   ```shell
   git clone https://github.com/Sadhu54/Gallery_app.git

2. Open the project in Android Studio.
3. Connect your Android device or use an emulator.
4. Build and run the application.

## Dependencies 
Gallery App utilizes several third-party libraries to enhance its functionality. Here are some of the key libraries used:

1. Coroutines: Asynchronous programming for managing background tasks.
2. Dagger - Hilt: Dependency injection framework for managing app components.
3. Retrofit: Networking library for making API requests.
4. Picasso: Image loading library for displaying images efficiently.
5. Toggle: A library for toggle buttons.
6. Lottie: A library for rendering After Effects animations.

## Design Decisions

### Clean Architecture

- The application follows the principles of Clean Architecture to ensure a clear separation of concerns and maintainability.
- The architecture is divided into distinct layers:
  - **Data Layer**: Responsible for data retrieval and data source management, such as API calls and database interactions.
  - **Domain Layer**: Contains the business logic and use cases of the application, providing a domain-centric approach.
  - **Presentation Layer**: Handles UI components, user interactions, and the presentation of data to the user.

- This architecture promotes testability, scalability, and flexibility by reducing dependencies between layers.

## Screenshots

### Feature 1: Search result List Page
![WhatsApp Image 2023-10-01 at 23 47 12](https://github.com/Sadhu54/Gallery_app/assets/41671404/a11413d3-8320-49c9-a96e-3c9564f8b78d)

### Feature 2: Search result Grid Page
![WhatsApp Image 2023-10-01 at 23 47 12 (1)](https://github.com/Sadhu54/Gallery_app/assets/41671404/41d6c483-a253-4593-b037-4f52e4c04ed3)

### Feature 3: Search result Empty Page
![WhatsApp Image 2023-10-01 at 23 47 12 (2)](https://github.com/Sadhu54/Gallery_app/assets/41671404/f909ff87-d5b0-4670-b799-437a9dd898a7)


 
