package com.tanelso2.glmatrix

import org.khronos.webgl.Float32Array

typealias Mat2dJS = Float32Array

external open class mat2d {
    companion object {
        fun create(): Mat2dJS = definedExternally
        fun clone(a: Mat2dJS): Mat2dJS = definedExternally
        fun copy(out: Mat2dJS, a: Mat2dJS): Mat2dJS = definedExternally
        fun identity(out: Mat2dJS): Mat2dJS = definedExternally
        fun fromValues(a: Number, b: Number, c: Number, d: Number, tx: Number, ty: Number): Mat2dJS = definedExternally
        fun set(out: Mat2dJS, a: Number, b: Number, c: Number, d: Number, tx: Number, ty: Number): Mat2dJS = definedExternally
        fun invert(out: Mat2dJS, a: Mat2dJS): Mat2dJS = definedExternally
        fun determinant(a: Mat2dJS): Number = definedExternally
        fun multiply(out: Mat2dJS, a: Mat2dJS, b: Mat2dJS): Mat2dJS = definedExternally
        fun mul(): Nothing = definedExternally
        fun rotate(out: Mat2dJS, a: Mat2dJS, rad: Number): Mat2dJS = definedExternally
        fun scale(out: Mat2dJS, a: Mat2dJS, v: Vec2JS): Mat2dJS = definedExternally
        fun translate(out: Mat2dJS, a: Mat2dJS, v: Vec2JS): Mat2dJS = definedExternally
        fun fromRotation(out: Mat2dJS, rad: Number): Mat2dJS = definedExternally
        fun fromScaling(out: Mat2dJS, v: Vec2JS): Mat2dJS = definedExternally
        fun fromTranslation(out: Mat2dJS, v: Vec2JS): Mat2dJS = definedExternally
        fun str(a: Mat2dJS): String = definedExternally
        fun frob(a: Mat2dJS): Number = definedExternally
        fun add(out: Mat2dJS, a: Mat2dJS, b: Mat2dJS): Mat2dJS = definedExternally
        fun subtract(out: Mat2dJS, a: Mat2dJS, b: Mat2dJS): Mat2dJS = definedExternally
        fun sub(): Nothing = definedExternally
        fun multiplyScalar(out: Mat2dJS, a: Mat2dJS, b: Number): Mat2dJS = definedExternally
        fun multiplyScalarAndAdd(out: Mat2dJS, a: Mat2dJS, b: Mat2dJS, scale: Number): Mat2dJS = definedExternally
        fun exactEquals(a: Mat2dJS, b: Mat2dJS): Boolean = definedExternally
        fun equals(a: Mat2dJS, b: Mat2dJS): Boolean = definedExternally
    }
}
