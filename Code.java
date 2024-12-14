package com.nexora.prefsutils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Utility class for simplified and flexible access to SharedPreferences in Android.
 * 
 * @author Nexora Technologies
 * @version 2.0
 * @since 2024-12-14
 */
public class PrefsUtils {

    private static PrefsUtils instance;
    private static SharedPreferences sharedPreferences;
    private static final Gson GSON = new Gson();

    // Private constructor to enforce Singleton pattern
    private PrefsUtils() {
        // Prevent instantiation
    }

    /**
     * Initializes the PrefsUtils library. This method must be called in the Application class before use.
     *
     * @param context The application context.
     */
    public static void initialize(Context context) {
        if (sharedPreferences == null) {
            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        }
    }

    /**
     * Retrieves the singleton instance of PrefsUtils.
     *
     * @return PrefsUtils instance.
     */
    public static PrefsUtils getInstance() {
        if (instance == null) {
            validateInitialization();
            synchronized (PrefsUtils.class) {
                if (instance == null) {
                    instance = new PrefsUtils();
                }
            }
        }
        return instance;
    }

    /**
     * Checks if the library is properly initialized.
     *
     * @throws PrefsUtilsException if the library is not initialized.
     */
    private static void validateInitialization() {
        if (sharedPreferences == null) {
            throw new PrefsUtilsException("PrefsUtils must be initialized using PrefsUtils.initialize(context) before usage.");
        }
    }

    /**
     * Saves a boolean value in SharedPreferences.
     *
     * @param key   The preference key.
     * @param value The boolean value to save.
     */
    public void putBoolean(String key, boolean value) {
        sharedPreferences.edit().putBoolean(key, value).apply();
    }

    /**
     * Retrieves a boolean value from SharedPreferences.
     *
     * @param key          The preference key.
     * @param defaultValue The default value if the key does not exist.
     * @return The retrieved boolean value.
     */
    public boolean getBoolean(String key, boolean defaultValue) {
        return sharedPreferences.getBoolean(key, defaultValue);
    }

    /**
     * Saves an integer value in SharedPreferences.
     *
     * @param key   The preference key.
     * @param value The integer value to save.
     */
    public void putInt(String key, int value) {
        sharedPreferences.edit().putInt(key, value).apply();
    }

    /**
     * Retrieves an integer value from SharedPreferences.
     *
     * @param key          The preference key.
     * @param defaultValue The default value if the key does not exist.
     * @return The retrieved integer value.
     */
    public int getInt(String key, int defaultValue) {
        return sharedPreferences.getInt(key, defaultValue);
    }

    /**
     * Saves a float value in SharedPreferences.
     *
     * @param key   The preference key.
     * @param value The float value to save.
     */
    public void putFloat(String key, float value) {
        sharedPreferences.edit().putFloat(key, value).apply();
    }

    /**
     * Retrieves a float value from SharedPreferences.
     *
     * @param key          The preference key.
     * @param defaultValue The default value if the key does not exist.
     * @return The retrieved float value.
     */
    public float getFloat(String key, float defaultValue) {
        return sharedPreferences.getFloat(key, defaultValue);
    }

    /**
     * Saves a long value in SharedPreferences.
     *
     * @param key   The preference key.
     * @param value The long value to save.
     */
    public void putLong(String key, long value) {
        sharedPreferences.edit().putLong(key, value).apply();
    }

    /**
     * Retrieves a long value from SharedPreferences.
     *
     * @param key          The preference key.
     * @param defaultValue The default value if the key does not exist.
     * @return The retrieved long value.
     */
    public long getLong(String key, long defaultValue) {
        return sharedPreferences.getLong(key, defaultValue);
    }

    /**
     * Saves a String value in SharedPreferences.
     *
     * @param key   The preference key.
     * @param value The String value to save.
     */
    public void putString(String key, String value) {
        sharedPreferences.edit().putString(key, value).apply();
    }

    /**
     * Retrieves a String value from SharedPreferences.
     *
     * @param key          The preference key.
     * @param defaultValue The default value if the key does not exist.
     * @return The retrieved String value.
     */
    public String getString(String key, String defaultValue) {
        return sharedPreferences.getString(key, defaultValue);
    }

    /**
     * Saves an object in SharedPreferences as a JSON String.
     *
     * @param key    The preference key.
     * @param object The object to save.
     * @param <T>    The object type.
     */
    public <T> void putObject(String key, T object) {
        String jsonString = GSON.toJson(object);
        sharedPreferences.edit().putString(key, jsonString).apply();
    }

    /**
     * Retrieves an object from SharedPreferences.
     *
     * @param key       The preference key.
     * @param classType The class type of the object.
     * @param <T>       The object type.
     * @return The retrieved object, or null if the key does not exist or the value cannot be parsed.
     */
    public <T> T getObject(String key, Class<T> classType) {
        String jsonString = sharedPreferences.getString(key, null);
        return jsonString != null ? GSON.fromJson(jsonString, classType) : null;
    }

    /**
     * Saves a list of objects in SharedPreferences as a JSON String.
     *
     * @param key        The preference key.
     * @param objectList The list of objects to save.
     * @param <T>        The object type.
     */
    public <T> void putObjectList(String key, List<T> objectList) {
        String jsonString = GSON.toJson(objectList);
        sharedPreferences.edit().putString(key, jsonString).apply();
    }

    /**
     * Retrieves a list of objects from SharedPreferences.
     *
     * @param key       The preference key.
     * @param classType The class type of the objects.
     * @param <T>       The object type.
     * @return The retrieved list of objects, or an empty list if the key does not exist or the value cannot be parsed.
     */
    public <T> List<T> getObjectList(String key, Class<T> classType) {
        String jsonString = sharedPreferences.getString(key, null);
        if (jsonString != null) {
            Type listType = TypeToken.getParameterized(List.class, classType).getType();
            return GSON.fromJson(jsonString, listType);
        }
        return new ArrayList<>();
    }

    /**
     * Removes a specific key-value pair from SharedPreferences.
     *
     * @param key The preference key to remove.
     * @return True if the key existed and was removed, false otherwise.
     */
    public boolean remove(String key) {
        if (sharedPreferences.contains(key)) {
            sharedPreferences.edit().remove(key).apply();
            return true;
        }
        return false;
    }

    /**
     * Clears all key-value pairs from SharedPreferences.
     */
    public void clearAll() {
        sharedPreferences.edit().clear().apply();
    }

    /**
     * Checks if a specific key exists in SharedPreferences.
     *
     * @param key The preference key to check.
     * @return True if the key exists, false otherwise.
     */
    public boolean contains(String key) {
        return sharedPreferences.contains(key);
    }
}

/**
 * Custom exception for PrefsUtils errors.
 */
class PrefsUtilsException extends RuntimeException {
    public PrefsUtilsException(String message) {
        super(message);
    }
}
