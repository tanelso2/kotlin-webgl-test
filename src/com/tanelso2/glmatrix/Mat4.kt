package com.tanelso2.glmatrix

import org.khronos.webgl.Float32Array

class Mat4(val array: Float32Array) {
    constructor() : this(Float32Array(mat4.create()))
    constructor(a: Array<Float>) : this(Float32Array(a))

    fun clone(): Mat4 = Mat4(array)
    fun identity() = mat4.identity(array)
    fun transpose() = mat4.transpose(array, array)
    fun invert() = mat4.invert(array, array)
    fun adjoint() = mat4.adjoint(array, array)
    fun determinant(): Float = mat4.determinant(array)
    fun translate(v: Vec3) = mat4.translate(array, array, v.array)
    fun rotateX(rad: Float) = mat4.rotateX(array, array, rad)
    fun rotateY(rad: Float) = mat4.rotateY(array, array, rad)
    fun rotateZ(rad: Float) = mat4.rotateZ(array, array, rad)
    fun scale(v: Vec3) = mat4.scale(array, array, v.array)
    fun lookAt(eye: Vec3, center: Vec3, up: Vec3) = mat4.lookAt(array, eye.array, center.array, up.array)
    fun perspective(fovy: Float, aspect: Float, near: Float, far: Float) = mat4.perspective(array, fovy, aspect, near, far)
    fun multiply(other: Mat4): Mat4 {
        val ret = Mat4()
        mat4.multiply(ret.array, array, other.array)
        return ret
    }

}


external open class mat4 {
    companion object {
        fun create(): Float32Array = noImpl
        fun clone(a: Float32Array): Float32Array = noImpl
        fun copy(out: Float32Array, a: Float32Array): Float32Array = noImpl
        fun fromValues(m00: Float, m01: Float, m02: Float, m03: Float,
                       m10: Float, m11: Float, m12: Float, m13: Float,
                       m20: Float, m21: Float, m22: Float, m23: Float,
                       m30: Float, m31: Float, m32: Float, m33: Float): Float32Array = noImpl

        fun set(out: Float32Array,
                m00: Float, m01: Float, m02: Float, m03: Float,
                m10: Float, m11: Float, m12: Float, m13: Float,
                m20: Float, m21: Float, m22: Float, m23: Float,
                m30: Float, m31: Float, m32: Float, m33: Float): Float32Array = noImpl

        fun identity(out: Float32Array): Float32Array = noImpl
        fun transpose(out: Float32Array, a: Float32Array): Float32Array = noImpl
        fun invert(out: Float32Array, a: Float32Array): Float32Array = noImpl
        fun adjoint(out: Float32Array, a: Float32Array): Float32Array = noImpl
        fun determinant(a: Float32Array): Float = noImpl
        fun multiply(out: Float32Array, a: Float32Array, b: Float32Array): Float32Array = noImpl
        fun mul(out: Float32Array, a: Float32Array, b: Float32Array): Float32Array = noImpl
        fun translate(out: Float32Array, a: Float32Array, v: Float32Array): Float32Array = noImpl
        fun scale(out: Float32Array, a: Float32Array, v: Float32Array): Float32Array = noImpl
        fun rotate(out: Float32Array, a: Float32Array, rad: Float, axis: Float32Array): Float32Array = noImpl
        fun rotateX(out: Float32Array, a: Float32Array, rad: Float): Float32Array = noImpl
        fun rotateY(out: Float32Array, a: Float32Array, rad: Float): Float32Array = noImpl
        fun rotateZ(out: Float32Array, a: Float32Array, rad: Float): Float32Array = noImpl
        fun fromScaling(out: Float32Array, v: Float32Array): Float32Array = noImpl
        fun perspective(out: Float32Array, fovy: Float, aspect: Float, near: Float, far: Float): Float32Array = noImpl
        fun ortho(out: Float32Array, left: Float, right: Float, bottom: Float, top: Float, near: Float, far: Float): Float32Array = noImpl
        fun lookAt(out: Float32Array, eye: Float32Array, center: Float32Array, up: Float32Array): Float32Array = noImpl
    }
}


