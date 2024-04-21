package com.tanelso2.glmatrix

import org.khronos.webgl.Float32Array

typealias Mat2JS = Float32Array

external open class mat2 {
    companion object {
        fun create(): Mat2JS = definedExternally
        fun clone(a: Mat2JS): Mat2JS = definedExternally
        fun copy(out: Mat2JS, a: Mat2JS): Mat2JS = definedExternally
        fun identity(out: Mat2JS): Mat2JS = definedExternally
        fun fromValues(m00: Number, m01: Number, m10: Number, m11: Number): Mat2JS = definedExternally
        fun set(out: Mat2JS, m00: Number, m01: Number, m10: Number, m11: Number): Mat2JS = definedExternally
        fun transpose(out: Mat2JS, a: Mat2JS): Mat2JS = definedExternally
        fun invert(out: Mat2JS, a: Mat2JS): Mat2JS = definedExternally
        fun adjoint(out: Mat2JS, a: Mat2JS): Mat2JS = definedExternally
        fun determinant(a: Mat2JS): Number = definedExternally
        fun multiply(out: Mat2JS, a: Mat2JS, b: Mat2JS): Mat2JS = definedExternally
        fun mul(): Nothing = definedExternally
        fun rotate(out: Mat2JS, a: Mat2JS, rad: Number): Mat2JS = definedExternally
        fun scale(out: Mat2JS, a: Mat2JS, v: Vec2JS): Mat2JS = definedExternally
        fun fromRotation(out: Mat2JS, rad: Number): Mat2JS = definedExternally
        fun fromScaling(out: Mat2JS, v: Vec2JS): Mat2JS = definedExternally
        fun str(a: Mat2JS): String = definedExternally
        fun frob(a: Mat2JS): Number = definedExternally
        fun LDU(L: Mat2JS, D: Mat2JS, U: Mat2JS, a: Mat2JS): Nothing = definedExternally
        fun add(out: Mat2JS, a: Mat2JS, b: Mat2JS): Mat2JS = definedExternally
        fun subtract(out: Mat2JS, a: Mat2JS, b: Mat2JS): Mat2JS = definedExternally
        fun sub(): Nothing = definedExternally
        fun exactEquals(a: Mat2JS, b: Mat2JS): Boolean = definedExternally
        fun equals(a: Mat2JS, b: Mat2JS): Boolean = definedExternally
        fun multiplyScalar(out: Mat2JS, a: Mat2JS, b: Number): Mat2JS = definedExternally
        fun multiplyScalarAndAdd(out: Mat2JS, a: Mat2JS, b: Mat2JS, scale: Number): Mat2JS = definedExternally
    }
}
