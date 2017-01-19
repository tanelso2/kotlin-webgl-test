package com.tanelso2.glmatrix

import org.khronos.webgl.Float32Array

typealias Vec4JS = Float32Array

external open class vec4 {
    companion object {
        fun create(): Vec4JS = noImpl
        fun clone(a: Vec4JS): Vec4JS = noImpl
        fun fromValues(x: Number, y: Number, z: Number, w: Number): Vec4JS = noImpl
        fun copy(out: Vec4JS, a: Vec4JS): Vec4JS = noImpl
        fun set(out: Vec4JS, x: Number, y: Number, z: Number, w: Number): Vec4JS = noImpl
        fun add(out: Vec4JS, a: Vec4JS, b: Vec4JS): Vec4JS = noImpl
        fun subtract(out: Vec4JS, a: Vec4JS, b: Vec4JS): Vec4JS = noImpl
        fun sub(): Nothing = noImpl
        fun multiply(out: Vec4JS, a: Vec4JS, b: Vec4JS): Vec4JS = noImpl
        fun mul(): Nothing = noImpl
        fun divide(out: Vec4JS, a: Vec4JS, b: Vec4JS): Vec4JS = noImpl
        fun div(): Nothing = noImpl
        fun ceil(out: Vec4JS, a: Vec4JS): Vec4JS = noImpl
        fun floor(out: Vec4JS, a: Vec4JS): Vec4JS = noImpl
        fun min(out: Vec4JS, a: Vec4JS, b: Vec4JS): Vec4JS = noImpl
        fun max(out: Vec4JS, a: Vec4JS, b: Vec4JS): Vec4JS = noImpl
        fun round(out: Vec4JS, a: Vec4JS): Vec4JS = noImpl
        fun scale(out: Vec4JS, a: Vec4JS, b: Number): Vec4JS = noImpl
        fun scaleAndAdd(out: Vec4JS, a: Vec4JS, b: Vec4JS, scale: Number): Vec4JS = noImpl
        fun distance(a: Vec4JS, b: Vec4JS): Number = noImpl
        fun dist(): Nothing = noImpl
        fun squaredDistance(a: Vec4JS, b: Vec4JS): Number = noImpl
        fun sqrDist(): Nothing = noImpl
        fun length(a: Vec4JS): Number = noImpl
        fun len(): Nothing = noImpl
        fun squaredLength(a: Vec4JS): Number = noImpl
        fun sqrLen(): Nothing = noImpl
        fun negate(out: Vec4JS, a: Vec4JS): Vec4JS = noImpl
        fun inverse(out: Vec4JS, a: Vec4JS): Vec4JS = noImpl
        fun normalize(out: Vec4JS, a: Vec4JS): Vec4JS = noImpl
        fun dot(a: Vec4JS, b: Vec4JS): Number = noImpl
        fun lerp(out: Vec4JS, a: Vec4JS, b: Vec4JS, t: Number): Vec4JS = noImpl
        fun random(out: Vec4JS, scale: Number): Vec4JS = noImpl
        fun transformMat4(out: Vec4JS, a: Vec4JS, m: Mat4JS): Vec4JS = noImpl
        fun transformQuat(out: Vec4JS, a: Vec4JS, q: QuatJS): Vec4JS = noImpl
        fun forEach(a: dynamic, stride: Number, offset: Number, count: Number, fn: dynamic, arg: dynamic): dynamic = noImpl
        fun str(a: Vec4JS): String = noImpl
        fun exactEquals(a: Vec4JS, b: Vec4JS): Boolean = noImpl
        fun equals(a: Vec4JS, b: Vec4JS): Boolean = noImpl
    }
}
