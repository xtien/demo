# demo
Small demo project. App reads a file issues.csv from /sdcard/, then displays contents in listview. If there's no file on /sdcard/, it copies one from assets to /sdcard/.

It uses MVVM, following this <a "https://proandroiddev.com/mvp-to-mvvm-transformation-611959d5e0ca">article</a>. 
It uses Dagger for dependency injection. I like Toothpick a lot for DI, but Dagger seems to be the standard. 
