package com.tanelso2.glmatrix

import org.khronos.webgl.Float32Array

typealias Mat4JS = Float32Array

class Mat4(val array: Mat4JS) {
    constructor() : this(Float32Array(mat4.create()))
    constructor(a: Array<Float>) : this(Float32Array(a))

    fun clone(): Mat4 = Mat4(array)
    fun identity() {
        mat4.identity(array)
    }

    fun transpose() {
        mat4.transpose(array, array)
    }

    fun invert() {
        mat4.invert(array, array)
    }

    fun adjoint() {
        mat4.adjoint(array, array)
    }

    fun determinant(): Number = mat4.determinant(array)
    fun translate(v: Vec3) {
        mat4.translate(array, array, v.array)
    }

    fun rotateX(rad: Number) {
        mat4.rotateX(array, array, rad.toFloat())
    }

    fun rotateY(rad: Number) {
        mat4.rotateY(array, array, rad.toFloat())
    }

    fun rotateZ(rad: Number) {
        mat4.rotateZ(array, array, rad.toFloat())
    }

    fun scale(v: Vec3) {
        mat4.scale(array, array, v.array)
    }

    fun lookAt(eye: Vec3, center: Vec3, up: Vec3) {
        mat4.lookAt(array, eye.array, center.array, up.array)
    }

    fun perspective(fovy: Number, aspect: Number, near: Number, far: Number) {
        mat4.perspective(array, fovy.toFloat(), aspect.toFloat(), near.toFloat(), far.toFloat())
    }

    fun multiply(other: Mat4): Mat4 {
        val ret = Mat4()
        mat4.multiply(ret.array, array, other.array)
        return ret
    }

}

external open class mat4 {
    companion object {
        fun create(): Mat4JS = noImpl
        fun clone(a: Mat4JS): Mat4JS = noImpl
        fun copy(out: Mat4JS, a: Mat4JS): Mat4JS = noImpl
        fun fromValues(m00: Number, m01: Number, m02: Number, m03: Number, m10: Number, m11: Number, m12: Number, m13: Number, m20: Number, m21: Number, m22: Number, m23: Number, m30: Number, m31: Number, m32: Number, m33: Number): Mat4JS = noImpl
        fun set(out: Mat4JS, m00: Number, m01: Number, m02: Number, m03: Number, m10: Number, m11: Number, m12: Number, m13: Number, m20: Number, m21: Number, m22: Number, m23: Number, m30: Number, m31: Number, m32: Number, m33: Number): Mat4JS = noImpl
        fun identity(out: Mat4JS): Mat4JS = noImpl
        fun transpose(out: Mat4JS, a: Mat4JS): Mat4JS = noImpl
        fun invert(out: Mat4JS, a: Mat4JS): Mat4JS = noImpl
        fun adjoint(out: Mat4JS, a: Mat4JS): Mat4JS = noImpl
        fun determinant(a: Mat4JS): Number = noImpl
        fun multiply(out: Mat4JS, a: Mat4JS, b: Mat4JS): Mat4JS = noImpl
        fun translate(out: Mat4JS, a: Mat4JS, v: Vec3JS): Mat4JS = noImpl
        fun scale(out: Mat4JS, a: Mat4JS, v: Vec3JS): Mat4JS = noImpl
        fun rotate(out: Mat4JS, a: Mat4JS, rad: Number, axis: Vec3JS): Mat4JS = noImpl
        fun rotateX(out: Mat4JS, a: Mat4JS, rad: Number): Mat4JS = noImpl
        fun rotateY(out: Mat4JS, a: Mat4JS, rad: Number): Mat4JS = noImpl
        fun rotateZ(out: Mat4JS, a: Mat4JS, rad: Number): Mat4JS = noImpl
        fun fromTranslation(out: Mat4JS, v: Vec3JS): Mat4JS = noImpl
        fun fromScaling(out: Mat4JS, v: Vec3JS): Mat4JS = noImpl
        fun fromRotation(out: Mat4JS, rad: Number, axis: Vec3JS): Mat4JS = noImpl
        fun fromXRotation(out: Mat4JS, rad: Number): Mat4JS = noImpl
        fun fromYRotation(out: Mat4JS, rad: Number): Mat4JS = noImpl
        fun fromZRotation(out: Mat4JS, rad: Number): Mat4JS = noImpl
        fun fromRotationTranslation(out: Mat4JS, q: QuatJS, v: Vec3JS): Mat4JS = noImpl
        fun getTranslation(out: Vec3JS, mat: Mat4JS): Vec3JS = noImpl
        fun getScaling(out: Vec3JS, mat: Mat4JS): Vec3JS = noImpl
        fun getRotation(out: QuatJS, mat: Mat4JS): QuatJS = noImpl
        fun fromRotationTranslationScale(out: Mat4JS, q: QuatJS, v: Vec3JS, s: Vec3JS): Mat4JS = noImpl
        fun fromRotationTranslationScaleOrigin(out: Mat4JS, q: QuatJS, v: Vec3JS, s: Vec3JS, o: Vec3JS): Mat4JS = noImpl
        fun fromQuat(out: Mat4JS, q: QuatJS): Mat4JS = noImpl
        fun frustum(out: Mat4JS, left: Number, right: Number, bottom: Number, top: Number, near: Number, far: Number): Mat4JS = noImpl
        fun perspective(out: Mat4JS, fovy: Number, aspect: Number, near: Number, far: Number): Mat4JS = noImpl
        fun perspectiveFromFieldOfView(out: Mat4JS, fov: dynamic, near: Number, far: Number): Mat4JS = noImpl
        fun ortho(out: Mat4JS, left: Number, right: Number, bottom: Number, top: Number, near: Number, far: Number): Mat4JS = noImpl
        fun lookAt(out: Mat4JS, eye: Vec3JS, center: Vec3JS, up: Vec3JS): Mat4JS = noImpl
        fun str(a: Mat4JS): String = noImpl
        fun frob(a: Mat4JS): Number = noImpl
        fun add(out: Mat4JS, a: Mat4JS, b: Mat4JS): Mat4JS = noImpl
        fun subtract(out: Mat4JS, a: Mat4JS, b: Mat4JS): Mat4JS = noImpl
        fun sub(): Nothing = noImpl
        fun multiplyScalar(out: Mat4JS, a: Mat4JS, b: Number): Mat4JS = noImpl
        fun multiplyScalarAndAdd(out: Mat4JS, a: Mat4JS, b: Mat4JS, scale: Number): Mat4JS = noImpl
        fun exactEquals(a: Mat4JS, b: Mat4JS): Boolean = noImpl
        fun equals(a: Mat4JS, b: Mat4JS): Boolean = noImpl
    }
}

