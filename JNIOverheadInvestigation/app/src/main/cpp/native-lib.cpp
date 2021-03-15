#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring JNICALL
Java_com_palam_jnioverheadinvestigation_MainActivity_stringFromJNI(
        JNIEnv* env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}

extern "C"
JNIEXPORT jstring JNICALL
Java_com_palam_jnioverheadinvestigation_MainActivity_nop_1jni(JNIEnv *env, jobject thiz) {
    std::string hello = "nop jni function";
    return env->NewStringUTF(hello.c_str());
}

static double collectiveTestValue;

extern "C"
JNIEXPORT jstring JNICALL
Java_com_palam_jnioverheadinvestigation_MainActivity_someCalculations_1jni(JNIEnv *env,
                                                                           jobject thiz) {
    std::string hello = "some calculations jni function";
    double testValue = 0.0;
    int testSize = 100;
    for (int i = 0; i < testSize; ++i) {
        for (int j = 0; j < testSize; ++j) {
            testValue += i*j;
        }
    }
    collectiveTestValue += testValue / (testSize * testSize);
    return env->NewStringUTF(hello.c_str());
}