//
// Created by Mahmoud on 20/12/2021.
//

#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring
Java_com_mahmoud_weatherapp_modules_core_data_WeatherKeys_geApiUrl(JNIEnv *env, jobject object) {
    return env->NewStringUTF("https://api.openweathermap.org/data/2.5/");
}

extern "C" JNIEXPORT jstring
Java_com_mahmoud_weatherapp_modules_core_data_WeatherKeys_geAppId(JNIEnv *env, jobject object) {
    return env->NewStringUTF("4e4d7511f83873e7b4c5dae8f6c6354a");
}