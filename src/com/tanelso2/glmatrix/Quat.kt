package com.tanelso2.glmatrix

import org.khronos.webgl.Float32Array

typealias QuatJS = Float32Array

external open class quat {
    companion object {
        fun create(): QuatJS = noImpl
        fun clone(a: QuatJS): QuatJS = noImpl
        fun fromValues(x: Number, y: Number, z: Number, w: Number): QuatJS = noImpl
        fun copy(out: QuatJS, a: QuatJS): QuatJS = noImpl
        fun set(out: QuatJS, x: Number, y: Number, z: Number, w: Number): QuatJS = noImpl
        fun identity(out: QuatJS): QuatJS = noImpl
        fun setAxisAngle(out: QuatJS, axis: Vec3JS, rad: Number): QuatJS = noImpl
        fun getAxisAngle(out_axis: Vec3JS, q: QuatJS): Number = noImpl
        fun add(out: QuatJS, a: QuatJS, b: QuatJS): QuatJS = noImpl
        fun multiply(out: QuatJS, a: QuatJS, b: QuatJS): QuatJS = noImpl
        fun mul(): Nothing = noImpl
        fun scale(out: QuatJS, a: QuatJS, b: Number): QuatJS = noImpl
        fun rotateX(out: QuatJS, a: QuatJS, rad: Number): QuatJS = noImpl
        fun rotateY(out: QuatJS, a: QuatJS, rad: Number): QuatJS = noImpl
        fun rotateZ(out: QuatJS, a: QuatJS, rad: Number): QuatJS = noImpl
        fun calculateW(out: QuatJS, a: QuatJS): QuatJS = noImpl
        fun dot(a: QuatJS, b: QuatJS): Number = noImpl
        fun lerp(out: QuatJS, a: QuatJS, b: QuatJS, t: Number): QuatJS = noImpl
        fun slerp(out: QuatJS, a: QuatJS, b: QuatJS, t: Number): QuatJS = noImpl
        fun invert(out: QuatJS, a: QuatJS): QuatJS = noImpl
        fun conjugate(out: QuatJS, a: QuatJS): QuatJS = noImpl
        fun length(a: QuatJS): Number = noImpl
        fun len(): Nothing = noImpl
        fun squaredLength(a: QuatJS): Number = noImpl
        fun sqrLen(): Nothing = noImpl
        fun normalize(out: QuatJS, a: QuatJS): QuatJS = noImpl
        fun fromMat3(out: QuatJS, m: Mat3JS): QuatJS = noImpl
        fun str(a: QuatJS): String = noImpl
    }
}
