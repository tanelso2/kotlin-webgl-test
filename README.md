# kotlin-webgl-test
Trying to relearn WebGL while also learning Kotlin.js

Live demo of the current code can be found at https://tanelso2.github.io/kotlin-webgl-test

# Building

```bash
./gradlew build
```

# Viewing the Built Code

Use any web server to serve `build/dist/js/productionExecutable`. Here's an example using Python's built-in http server:
```bash
python3 -m http.server --directory build/dist/js/productionExecutable
```
