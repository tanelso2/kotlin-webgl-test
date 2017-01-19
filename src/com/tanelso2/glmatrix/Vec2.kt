package com.tanelso2.glmatrix

import org.khronos.webgl.Float32Array

typealias Vec2JS = Float32Array

external open class vec2 {
    companion object {
        fun create(): Vec2JS = noImpl
        fun clone(a: Vec2JS): Vec2JS = noImpl
        fun fromValues(x: Number, y: Number): Vec2JS = noImpl
        fun copy(out: Vec2JS, a: Vec2JS): Vec2JS = noImpl
        fun set(out: Vec2JS, x: Number, y: Number): Vec2JS = noImpl
        fun add(out: Vec2JS, a: Vec2JS, b: Vec2JS): Vec2JS = noImpl
        fun subtract(out: Vec2JS, a: Vec2JS, b: Vec2JS): Vec2JS = noImpl
        fun sub(): Nothing = noImpl
        fun multiply(out: Vec2JS, a: Vec2JS, b: Vec2JS): Vec2JS = noImpl
        fun mul(): Nothing = noImpl
        fun divide(out: Vec2JS, a: Vec2JS, b: Vec2JS): Vec2JS = noImpl
        fun div(): Nothing = noImpl
        fun ceil(out: Vec2JS, a: Vec2JS): Vec2JS = noImpl
        fun floor(out: Vec2JS, a: Vec2JS): Vec2JS = noImpl
        fun min(out: Vec2JS, a: Vec2JS, b: Vec2JS): Vec2JS = noImpl
        fun max(out: Vec2JS, a: Vec2JS, b: Vec2JS): Vec2JS = noImpl
        fun round(out: Vec2JS, a: Vec2JS): Vec2JS = noImpl
        fun scale(out: Vec2JS, a: Vec2JS, b: Number): Vec2JS = noImpl
        fun scaleAndAdd(out: Vec2JS, a: Vec2JS, b: Vec2JS, scale: Number): Vec2JS = noImpl
        fun distance(a: Vec2JS, b: Vec2JS): Number = noImpl
        fun dist(): Nothing = noImpl
        fun squaredDistance(a: Vec2JS, b: Vec2JS): Number = noImpl
        fun sqrDist(): Nothing = noImpl
        fun length(a: Vec2JS): Number = noImpl
        fun len(): Nothing = noImpl
        fun squaredLength(a: Vec2JS): Number = noImpl
        fun sqrLen(): Nothing = noImpl
        fun negate(out: Vec2JS, a: Vec2JS): Vec2JS = noImpl
        fun inverse(out: Vec2JS, a: Vec2JS): Vec2JS = noImpl
        fun normalize(out: Vec2JS, a: Vec2JS): Vec2JS = noImpl
        fun dot(a: Vec2JS, b: Vec2JS): Number = noImpl
        fun cross(out: Vec3JS, a: Vec2JS, b: Vec2JS): Vec3JS = noImpl
        fun lerp(out: Vec2JS, a: Vec2JS, b: Vec2JS, t: Number): Vec2JS = noImpl
        fun random(out: Vec2JS, scale: Number): Vec2JS = noImpl
        fun transformMat2(out: Vec2JS, a: Vec2JS, m: Mat2JS): Vec2JS = noImpl
        fun transformMat2d(out: Vec2JS, a: Vec2JS, m: Mat2dJS): Vec2JS = noImpl
        fun transformMat3(out: Vec2JS, a: Vec2JS, m: Mat3JS): Vec2JS = noImpl
        fun transformMat4(out: Vec2JS, a: Vec2JS, m: Mat4JS): Vec2JS = noImpl
        fun forEach(a: dynamic, stride: Number, offset: Number, count: Number, fn: dynamic, arg: dynamic): dynamic = noImpl
        fun str(a: Vec2JS): String = noImpl
        fun exactEquals(a: Vec2JS, b: Vec2JS): Boolean = noImpl
        fun equals(a: Vec2JS, b: Vec2JS): Boolean = noImpl
    }
}
