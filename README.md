# PrefsUtils Library Documentation

## Overview
**PrefsUtils** is a utility library for simplified and flexible access to Android's SharedPreferences. It provides an easy-to-use API to save, retrieve, and manage key-value pairs, including primitive types, objects, and lists.

## Features
- Singleton pattern for global access.
- Supports all basic types: boolean, int, float, long, and String.
- Save and retrieve custom objects and lists using Gson serialization.
- Key existence checks, deletion, and clearing of preferences.
- Lightweight and fast.

## Setup

1. **Add PrefsUtils to your project:**
   Include the `PrefsUtils` class in your project directory.

2. **Initialize in Application class:**
   ```java
   import android.app.Application;
   import com.nexora.prefsutils.PrefsUtils;

   public class MyApplication extends Application {
       @Override
       public void onCreate() {
           super.onCreate();
           PrefsUtils.initialize(this);
       }
   }
   ```

## Usage

### 1. Access the Singleton Instance
```java
PrefsUtils prefs = PrefsUtils.getInstance();
```

### 2. Save and Retrieve Primitives
#### Boolean
```java
// Save a boolean value
prefs.putBoolean("isLoggedIn", true);

// Retrieve a boolean value
boolean isLoggedIn = prefs.getBoolean("isLoggedIn", false);
```

#### Integer
```java
// Save an integer value
prefs.putInt("userAge", 25);

// Retrieve an integer value
int userAge = prefs.getInt("userAge", 0);
```

#### Float
```java
// Save a float value
prefs.putFloat("userScore", 85.5f);

// Retrieve a float value
float userScore = prefs.getFloat("userScore", 0.0f);
```

#### Long
```java
// Save a long value
prefs.putLong("lastLogin", System.currentTimeMillis());

// Retrieve a long value
long lastLogin = prefs.getLong("lastLogin", 0L);
```

#### String
```java
// Save a string value
prefs.putString("username", "JohnDoe");

// Retrieve a string value
String username = prefs.getString("username", "");
```

### 3. Save and Retrieve Objects
#### Saving an Object
```java
User user = new User("John", 25);
prefs.putObject("userProfile", user);
```

#### Retrieving an Object
```java
User user = prefs.getObject("userProfile", User.class);
```

### 4. Save and Retrieve Lists
#### Saving a List of Objects
```java
List<User> userList = Arrays.asList(new User("John", 25), new User("Jane", 30));
prefs.putObjectList("userList", userList);
```

#### Retrieving a List of Objects
```java
List<User> userList = prefs.getObjectList("userList", User.class);
```

### 5. Manage Preferences
#### Check Key Existence
```java
boolean exists = prefs.contains("username");
```

#### Remove a Key
```java
boolean removed = prefs.remove("username");
```

#### Clear All Preferences
```java
prefs.clearAll();
```

## Error Handling
If PrefsUtils is not initialized, the following exception will be thrown:
```java
throw new PrefsUtilsException("PrefsUtils must be initialized using PrefsUtils.initialize(context) before usage.");
```
Ensure you call `PrefsUtils.initialize(context)` in the `Application` class before using the library.

## Example Application
```java
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.nexora.prefsutils.PrefsUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PrefsUtils prefs = PrefsUtils.getInstance();

        // Save data
        prefs.putBoolean("isLoggedIn", true);
        prefs.putString("username", "JohnDoe");

        // Retrieve data
        boolean isLoggedIn = prefs.getBoolean("isLoggedIn", false);
        String username = prefs.getString("username", "");

        // Logging values
        System.out.println("Logged In: " + isLoggedIn);
        System.out.println("Username: " + username);
    }
}
```

## License
This library is developed by **Nexora Technologies** and is free to use and distribute under the MIT License.

