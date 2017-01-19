package com.tanelso2.glmatrix

import org.khronos.webgl.Float32Array

typealias Mat2dJS = Float32Array

external open class mat2d {
    companion object {
        fun create(): Mat2dJS = noImpl
        fun clone(a: Mat2dJS): Mat2dJS = noImpl
        fun copy(out: Mat2dJS, a: Mat2dJS): Mat2dJS = noImpl
        fun identity(out: Mat2dJS): Mat2dJS = noImpl
        fun fromValues(a: Number, b: Number, c: Number, d: Number, tx: Number, ty: Number): Mat2dJS = noImpl
        fun set(out: Mat2dJS, a: Number, b: Number, c: Number, d: Number, tx: Number, ty: Number): Mat2dJS = noImpl
        fun invert(out: Mat2dJS, a: Mat2dJS): Mat2dJS = noImpl
        fun determinant(a: Mat2dJS): Number = noImpl
        fun multiply(out: Mat2dJS, a: Mat2dJS, b: Mat2dJS): Mat2dJS = noImpl
        fun mul(): Nothing = noImpl
        fun rotate(out: Mat2dJS, a: Mat2dJS, rad: Number): Mat2dJS = noImpl
        fun scale(out: Mat2dJS, a: Mat2dJS, v: Vec2JS): Mat2dJS = noImpl
        fun translate(out: Mat2dJS, a: Mat2dJS, v: Vec2JS): Mat2dJS = noImpl
        fun fromRotation(out: Mat2dJS, rad: Number): Mat2dJS = noImpl
        fun fromScaling(out: Mat2dJS, v: Vec2JS): Mat2dJS = noImpl
        fun fromTranslation(out: Mat2dJS, v: Vec2JS): Mat2dJS = noImpl
        fun str(a: Mat2dJS): String = noImpl
        fun frob(a: Mat2dJS): Number = noImpl
        fun add(out: Mat2dJS, a: Mat2dJS, b: Mat2dJS): Mat2dJS = noImpl
        fun subtract(out: Mat2dJS, a: Mat2dJS, b: Mat2dJS): Mat2dJS = noImpl
        fun sub(): Nothing = noImpl
        fun multiplyScalar(out: Mat2dJS, a: Mat2dJS, b: Number): Mat2dJS = noImpl
        fun multiplyScalarAndAdd(out: Mat2dJS, a: Mat2dJS, b: Mat2dJS, scale: Number): Mat2dJS = noImpl
        fun exactEquals(a: Mat2dJS, b: Mat2dJS): Boolean = noImpl
        fun equals(a: Mat2dJS, b: Mat2dJS): Boolean = noImpl
    }
}
