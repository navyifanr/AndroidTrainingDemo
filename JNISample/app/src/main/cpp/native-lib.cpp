#include <jni.h>
#include <string>


extern "C"
JNIEXPORT jstring JNICALL
Java_cn_cfanr_jnisample_MainActivity_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "Hello from C++, see the log for other test.";
    return env->NewStringUTF(hello.c_str());
}
