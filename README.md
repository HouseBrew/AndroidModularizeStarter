# AndroidModularizeStarter

This is a simple news headline reading app with dummy user system to demonstrate how to modularize an Android application

### Why modularizing

1. Reduce build time
2. Reuse common logic/style/etc across applications/modules
3. Easier to port to Instant apps
4. Easier for teams who work on same codebase

### Project Structure

- common
  - Module for storing common part like
    - models
    - repos
    - helpers
    - extensions
    - apis
    - navigations
    - base classes
    - dependency injections
  - Can be a standalone repo
- app
  - The main app module which has module `common`, `account`, etc as dependenies
  - Entry point of the application
- account
  - Module for account related features, i.e. the login flow
- accountapp
  - Application for testing the `accoount` module **ONLY**
  - A minimal layer for dispatching a deep link intent to launch activities in `account` module

### Base flow

1. App module `MainActivity` is launched
2. Before `MainActivity.setContentView()`, check user info exist or not. if not exist, launch `LoginActivity`
3. After user logged in, relaunch `MainActivity` and start fetching news headlines.

### Libraries

1. [Kodein](https://github.com/Kodein-Framework/Kodein-DI) for Dependency injection
2. [Retrofit](http://square.github.io/retrofit/)
3. [Epoxy](https://github.com/airbnb/epoxy) for RecyclerView
4. [DeepLinkDispatch](https://github.com/airbnb/DeepLinkDispatch) for navigating between modules
5. [Fresco](http://frescolib.org)
6. [Stetho](http://facebook.github.io/stetho/)
7. [Timber](https://github.com/JakeWharton/timber)
8. RxJava

### License

```
MIT License

Copyright (c) 2018 HouseBrew

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```

