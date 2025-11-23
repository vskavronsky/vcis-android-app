#include <jni.h>

extern "C"
JNIEXPORT jstring JNICALL
Java_com_automotive_bootcamp_launcher_presentation_MainActivity_getHelloMessage(JNIEnv *env, jobject thiz) {
    return env->NewStringUTF("Hello from MainActivity");
}
