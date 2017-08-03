#include <jni.h>
#include "android/log.h"
#include <stdio.h>
#include <string>

#ifndef LOG_TAG
#define LOG_TAG "JNI_LOG" //Log 的 tag 名字
//定义各种类型 Log 的函数别名
#define LOGD(...) __android_log_print(ANDROID_LOG_DEBUG,LOG_TAG ,__VA_ARGS__)
#define LOGI(...) __android_log_print(ANDROID_LOG_INFO,LOG_TAG ,__VA_ARGS__)
#define LOGW(...) __android_log_print(ANDROID_LOG_WARN,LOG_TAG ,__VA_ARGS__)
#define LOGE(...) __android_log_print(ANDROID_LOG_ERROR,LOG_TAG ,__VA_ARGS__)
#define LOGF(...) __android_log_print(ANDROID_LOG_FATAL,LOG_TAG ,__VA_ARGS__)
#endif

#ifdef __cplusplus
extern "C" {
#endif
//定义类名
static const char *className = "cn/cfanr/jnisample/DynamicRegisterJni";

//定义对应Java native方法的 C++ 函数，函数名可以随意命名
static jstring sayHello(JNIEnv *env, jobject) {
    LOGI("hello, this is native log.");
    const char* hello = "Hello from C++.";
    return env->NewStringUTF(hello);
}

/*
 * 定义函数映射表（是一个数组，可以同时定义多个函数的映射）
 * 参数1：Java 方法名
 * 参数2：方法描述符，也就是签名
 * 参数3：C++定义对应 Java native方法的函数名，注意是空指针类型
 */
static JNINativeMethod jni_Methods_table[] = {
        {"getStringFromCpp", "()Ljava/lang/String;", (void *) sayHello},
};

//根据函数映射表注册函数
static int registerNativeMethods(JNIEnv *env, const char *className,
                                    const JNINativeMethod *gMethods, int numMethods) {
    jclass clazz;
    LOGI("Registering %s natives\n", className);
    clazz = (env)->FindClass(className);
    if (clazz == NULL) {
        LOGE("Native registration unable to find class '%s'\n", className);
        return JNI_ERR;
    }

    if ((env)->RegisterNatives(clazz, gMethods, numMethods) < 0) {
        LOGE("Register natives failed for '%s'\n", className);
        return JNI_ERR;
    }
    //删除本地引用
    (env)->DeleteLocalRef(clazz);
    return JNI_OK;
}

jint JNI_OnLoad(JavaVM *vm, void *reserved) {
    LOGI("call JNI_OnLoad");

    JNIEnv *env = NULL;

    if (vm->GetEnv((void **) &env, JNI_VERSION_1_4) != JNI_OK) {  //判断 JNI 版本是否为JNI_VERSION_1_4
        return JNI_EVERSION;
    }

    registerNativeMethods(env, className, jni_Methods_table, sizeof(jni_Methods_table) / sizeof(JNINativeMethod));

    return JNI_VERSION_1_4;
}
#ifdef __cplusplus
}
#endif