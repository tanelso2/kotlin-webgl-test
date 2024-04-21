package com.tanelso2.glmatrix

import org.khronos.webgl.Float32Array

typealias QuatJS = Float32Array

external open class quat {
    companion object {
        fun create(): QuatJS = definedExternally
        fun clone(a: QuatJS): QuatJS = definedExternally
        fun fromValues(x: Number, y: Number, z: Number, w: Number): QuatJS = definedExternally
        fun copy(out: QuatJS, a: QuatJS): QuatJS = definedExternally
        fun set(out: QuatJS, x: Number, y: Number, z: Number, w: Number): QuatJS = definedExternally
        fun identity(out: QuatJS): QuatJS = definedExternally
        fun setAxisAngle(out: QuatJS, axis: Vec3JS, rad: Number): QuatJS = definedExternally
        fun getAxisAngle(out_axis: Vec3JS, q: QuatJS): Number = definedExternally
        fun add(out: QuatJS, a: QuatJS, b: QuatJS): QuatJS = definedExternally
        fun multiply(out: QuatJS, a: QuatJS, b: QuatJS): QuatJS = definedExternally
        fun mul(): Nothing = definedExternally
        fun scale(out: QuatJS, a: QuatJS, b: Number): QuatJS = definedExternally
        fun rotateX(out: QuatJS, a: QuatJS, rad: Number): QuatJS = definedExternally
        fun rotateY(out: QuatJS, a: QuatJS, rad: Number): QuatJS = definedExternally
        fun rotateZ(out: QuatJS, a: QuatJS, rad: Number): QuatJS = definedExternally
        fun calculateW(out: QuatJS, a: QuatJS): QuatJS = definedExternally
        fun dot(a: QuatJS, b: QuatJS): Number = definedExternally
        fun lerp(out: QuatJS, a: QuatJS, b: QuatJS, t: Number): QuatJS = definedExternally
        fun slerp(out: QuatJS, a: QuatJS, b: QuatJS, t: Number): QuatJS = definedExternally
        fun invert(out: QuatJS, a: QuatJS): QuatJS = definedExternally
        fun conjugate(out: QuatJS, a: QuatJS): QuatJS = definedExternally
        fun length(a: QuatJS): Number = definedExternally
        fun len(): Nothing = definedExternally
        fun squaredLength(a: QuatJS): Number = definedExternally
        fun sqrLen(): Nothing = definedExternally
        fun normalize(out: QuatJS, a: QuatJS): QuatJS = definedExternally
        fun fromMat3(out: QuatJS, m: Mat3JS): QuatJS = definedExternally
        fun str(a: QuatJS): String = definedExternally
    }
}
