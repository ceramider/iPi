#include <jni.h>
#include <string>
#include <jni.h>
#include <math.h>
#include "getPi.h"
extern "C"
JNIEXPORT jdouble JNICALL
Java_com_example_ipi_MainActivity_getPi(JNIEnv *env, jobject thiz, jint formula_type, jint accuracy) {
    // TODO: implement getPi()
    double myPi = 0;
    if (formula_type == 0) { // Leibniz
        int pn_value = 1;
        for (int i = 0; i < accuracy; ++i) {
            myPi += pn_value * (1 / (1.0 * 2 * i + 1));;
            pn_value *= -1;
        }
        myPi = 4 * myPi;
    } else if (formula_type == 1) { // Ramanujan
        for (int i = 0; i < accuracy; i++) {
            myPi += calculateFactorial(4 * i) * (26390 * i + 1103) / (pow(calculateFactorial(i), 4) * pow(396, 4 * i));
        }
        myPi = 99 * 99 / (2 * sqrt(2) * myPi);
    } else if (formula_type == 2) { // Chudnovsky
        int pn_value = 1;
        for (int i = 0; i < accuracy; i++) {
            myPi += pn_value * calculateFactorial(6 * i) * (13591409 + 545140134 * i) /
                    (pow(calculateFactorial(i), 3) * calculateFactorial(3 * i) *
                     pow(640320, 3 * i));
            pn_value *= -1;
        }
        myPi = 53360 * sqrt(640320) / myPi;
    } else if (formula_type == 3) { // Wallis
        myPi = 1;
        int numerator = 2;
        int denominator = 1;
        for (int i = 0; i < accuracy; i++) {
            myPi *= numerator / (1.0 * denominator);
            int j = numerator;
            numerator = denominator + 1;
            denominator = j + 1;
        }
        myPi *= 2;
    }
    return myPi;
}
