package com.tanelso2.glmatrix

import org.khronos.webgl.Float32Array

typealias Mat3JS = Float32Array

class Mat3(val array: Mat3JS) {
    constructor():
            this(mat3.create())

    companion object {
        fun fromMat4(source: Mat4): Mat3 {
            val ret = Mat3()
            mat3.fromMat4(ret.array, source.array)
            return ret
        }
    }

    fun invert() {
        mat3.invert(array, array)
    }

    fun transpose() {
        mat3.transpose(array, array)
    }
}

external open class mat3 {
    companion object {
        fun create(): Mat3JS = definedExternally
        fun fromMat4(out: Mat3JS, a: Mat4JS): Mat3JS = definedExternally
        fun clone(a: Mat3JS): Mat3JS = definedExternally
        fun copy(out: Mat3JS, a: Mat3JS): Mat3JS = definedExternally
        fun fromValues(m00: Number, m01: Number, m02: Number, m10: Number, m11: Number, m12: Number, m20: Number, m21: Number, m22: Number): Mat3JS = definedExternally
        fun set(out: Mat3JS, m00: Number, m01: Number, m02: Number, m10: Number, m11: Number, m12: Number, m20: Number, m21: Number, m22: Number): Mat3JS = definedExternally
        fun identity(out: Mat3JS): Mat3JS = definedExternally
        fun transpose(out: Mat3JS, a: Mat3JS): Mat3JS = definedExternally
        fun invert(out: Mat3JS, a: Mat3JS): Mat3JS = definedExternally
        fun adjoint(out: Mat3JS, a: Mat3JS): Mat3JS = definedExternally
        fun determinant(a: Mat3JS): Number = definedExternally
        fun multiply(out: Mat3JS, a: Mat3JS, b: Mat3JS): Mat3JS = definedExternally
        fun mul(): Nothing = definedExternally
        fun translate(out: Mat3JS, a: Mat3JS, v: Vec2JS): Mat3JS = definedExternally
        fun rotate(out: Mat3JS, a: Mat3JS, rad: Number): Mat3JS = definedExternally
        fun scale(out: Mat3JS, a: Mat3JS, v: Vec2JS): Mat3JS = definedExternally
        fun fromTranslation(out: Mat3JS, v: Vec2JS): Mat3JS = definedExternally
        fun fromRotation(out: Mat3JS, rad: Number): Mat3JS = definedExternally
        fun fromScaling(out: Mat3JS, v: Vec2JS): Mat3JS = definedExternally
        fun fromMat2d(out: Mat3JS, a: Mat2dJS): Mat3JS = definedExternally
        fun fromQuat(out: Mat3JS, q: QuatJS): Mat3JS = definedExternally
        fun normalFromMat4(out: Mat3JS, a: Mat4JS): Mat3JS = definedExternally
        fun str(a: Mat3JS): String = definedExternally
        fun frob(a: Mat3JS): Number = definedExternally
        fun add(out: Mat3JS, a: Mat3JS, b: Mat3JS): Mat3JS = definedExternally
        fun subtract(out: Mat3JS, a: Mat3JS, b: Mat3JS): Mat3JS = definedExternally
        fun sub(): Nothing = definedExternally
        fun multiplyScalar(out: Mat3JS, a: Mat3JS, b: Number): Mat3JS = definedExternally
        fun multiplyScalarAndAdd(out: Mat3JS, a: Mat3JS, b: Mat3JS, scale: Number): Mat3JS = definedExternally
        fun exactEquals(a: Mat3JS, b: Mat3JS): Boolean = definedExternally
        fun equals(a: Mat3JS, b: Mat3JS): Boolean = definedExternally
    }
}
