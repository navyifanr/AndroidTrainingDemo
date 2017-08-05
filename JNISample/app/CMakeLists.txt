# For more information about using CMake with Android Studio, read the
# documentation: https://d.android.com/studio/projects/add-native-code.html

# Sets the minimum version of CMake required to build the native library.
# 1.指定cmake版本
cmake_minimum_required(VERSION 3.4.1)

# Creates and names a library, sets it as either STATIC
# or SHARED, and provides the relative paths to its source code.
# You can define multiple libraries, and CMake builds them for you.
# Gradle automatically packages shared libraries with your APK.

add_library( # Sets the name of the library. ——>2.生成函数库的名字，需要写到程序中的 so 库的名字
             native-lib

             # Sets the library as a shared library. 生成动态函数
             SHARED

             # Provides a relative path to your source file(s). ——> 依赖的cpp文件，每添加一个 C/C++文件都要添加到这里，不然不会被编译
             src/main/cpp/native-lib.cpp
             src/main/cpp/native-field.cpp
             src/main/cpp/native-method.cpp
             src/main/cpp/native-params.cpp
             src/main/cpp/native-string.cpp
             src/main/cpp/dynamic-register-jni.cpp
             )

# Searches for a specified prebuilt library and stores the path as a
# variable. Because CMake includes system libraries in the search path by
# default, you only need to specify the name of the public NDK library
# you want to add. CMake verifies that the library exists before
# completing its build.

find_library( # Sets the name of the path variable. 设置path变量的名称
              log-lib

              # Specifies the name of the NDK library that
              # you want CMake to locate.  #指定要查询库的名字
              log )

# Specifies libraries CMake should link to your target library. You
# can link multiple libraries, such as libraries you define in this
# build script, prebuilt third-party libraries, or system libraries.

target_link_libraries( # Specifies the target library. 目标库, 和上面生成的函数库名字一致
                       native-lib

                       # Links the target library to the log library
                       # included in the NDK. 连接的库，根据log-lib变量对应liblog.so函数库
                       ${log-lib} )