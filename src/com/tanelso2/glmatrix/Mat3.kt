package com.tanelso2.glmatrix

import org.khronos.webgl.Float32Array

typealias Mat3JS = Float32Array

external open class mat3 {
    companion object {
        fun create(): Mat3JS = noImpl
        fun fromMat4(out: Mat3JS, a: Mat4JS): Mat3JS = noImpl
        fun clone(a: Mat3JS): Mat3JS = noImpl
        fun copy(out: Mat3JS, a: Mat3JS): Mat3JS = noImpl
        fun fromValues(m00: Number, m01: Number, m02: Number, m10: Number, m11: Number, m12: Number, m20: Number, m21: Number, m22: Number): Mat3JS = noImpl
        fun set(out: Mat3JS, m00: Number, m01: Number, m02: Number, m10: Number, m11: Number, m12: Number, m20: Number, m21: Number, m22: Number): Mat3JS = noImpl
        fun identity(out: Mat3JS): Mat3JS = noImpl
        fun transpose(out: Mat3JS, a: Mat3JS): Mat3JS = noImpl
        fun invert(out: Mat3JS, a: Mat3JS): Mat3JS = noImpl
        fun adjoint(out: Mat3JS, a: Mat3JS): Mat3JS = noImpl
        fun determinant(a: Mat3JS): Number = noImpl
        fun multiply(out: Mat3JS, a: Mat3JS, b: Mat3JS): Mat3JS = noImpl
        fun mul(): Nothing = noImpl
        fun translate(out: Mat3JS, a: Mat3JS, v: Vec2JS): Mat3JS = noImpl
        fun rotate(out: Mat3JS, a: Mat3JS, rad: Number): Mat3JS = noImpl
        fun scale(out: Mat3JS, a: Mat3JS, v: Vec2JS): Mat3JS = noImpl
        fun fromTranslation(out: Mat3JS, v: Vec2JS): Mat3JS = noImpl
        fun fromRotation(out: Mat3JS, rad: Number): Mat3JS = noImpl
        fun fromScaling(out: Mat3JS, v: Vec2JS): Mat3JS = noImpl
        fun fromMat2d(out: Mat3JS, a: Mat2dJS): Mat3JS = noImpl
        fun fromQuat(out: Mat3JS, q: QuatJS): Mat3JS = noImpl
        fun normalFromMat4(out: Mat3JS, a: Mat4JS): Mat3JS = noImpl
        fun str(a: Mat3JS): String = noImpl
        fun frob(a: Mat3JS): Number = noImpl
        fun add(out: Mat3JS, a: Mat3JS, b: Mat3JS): Mat3JS = noImpl
        fun subtract(out: Mat3JS, a: Mat3JS, b: Mat3JS): Mat3JS = noImpl
        fun sub(): Nothing = noImpl
        fun multiplyScalar(out: Mat3JS, a: Mat3JS, b: Number): Mat3JS = noImpl
        fun multiplyScalarAndAdd(out: Mat3JS, a: Mat3JS, b: Mat3JS, scale: Number): Mat3JS = noImpl
        fun exactEquals(a: Mat3JS, b: Mat3JS): Boolean = noImpl
        fun equals(a: Mat3JS, b: Mat3JS): Boolean = noImpl
    }
}
