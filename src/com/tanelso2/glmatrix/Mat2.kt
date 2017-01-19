package com.tanelso2.glmatrix

import org.khronos.webgl.Float32Array

typealias Mat2JS = Float32Array

external open class mat2 {
    companion object {
        fun create(): Mat2JS = noImpl
        fun clone(a: Mat2JS): Mat2JS = noImpl
        fun copy(out: Mat2JS, a: Mat2JS): Mat2JS = noImpl
        fun identity(out: Mat2JS): Mat2JS = noImpl
        fun fromValues(m00: Number, m01: Number, m10: Number, m11: Number): Mat2JS = noImpl
        fun set(out: Mat2JS, m00: Number, m01: Number, m10: Number, m11: Number): Mat2JS = noImpl
        fun transpose(out: Mat2JS, a: Mat2JS): Mat2JS = noImpl
        fun invert(out: Mat2JS, a: Mat2JS): Mat2JS = noImpl
        fun adjoint(out: Mat2JS, a: Mat2JS): Mat2JS = noImpl
        fun determinant(a: Mat2JS): Number = noImpl
        fun multiply(out: Mat2JS, a: Mat2JS, b: Mat2JS): Mat2JS = noImpl
        fun mul(): Nothing = noImpl
        fun rotate(out: Mat2JS, a: Mat2JS, rad: Number): Mat2JS = noImpl
        fun scale(out: Mat2JS, a: Mat2JS, v: Vec2JS): Mat2JS = noImpl
        fun fromRotation(out: Mat2JS, rad: Number): Mat2JS = noImpl
        fun fromScaling(out: Mat2JS, v: Vec2JS): Mat2JS = noImpl
        fun str(a: Mat2JS): String = noImpl
        fun frob(a: Mat2JS): Number = noImpl
        fun LDU(L: Mat2JS, D: Mat2JS, U: Mat2JS, a: Mat2JS): Nothing = noImpl
        fun add(out: Mat2JS, a: Mat2JS, b: Mat2JS): Mat2JS = noImpl
        fun subtract(out: Mat2JS, a: Mat2JS, b: Mat2JS): Mat2JS = noImpl
        fun sub(): Nothing = noImpl
        fun exactEquals(a: Mat2JS, b: Mat2JS): Boolean = noImpl
        fun equals(a: Mat2JS, b: Mat2JS): Boolean = noImpl
        fun multiplyScalar(out: Mat2JS, a: Mat2JS, b: Number): Mat2JS = noImpl
        fun multiplyScalarAndAdd(out: Mat2JS, a: Mat2JS, b: Mat2JS, scale: Number): Mat2JS = noImpl
    }
}
