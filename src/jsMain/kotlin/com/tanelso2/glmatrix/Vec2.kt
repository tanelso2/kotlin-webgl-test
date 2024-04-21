package com.tanelso2.glmatrix

import org.khronos.webgl.Float32Array

typealias Vec2JS = Float32Array

external open class vec2 {
    companion object {
        fun create(): Vec2JS = definedExternally
        fun clone(a: Vec2JS): Vec2JS = definedExternally
        fun fromValues(x: Number, y: Number): Vec2JS = definedExternally
        fun copy(out: Vec2JS, a: Vec2JS): Vec2JS = definedExternally
        fun set(out: Vec2JS, x: Number, y: Number): Vec2JS = definedExternally
        fun add(out: Vec2JS, a: Vec2JS, b: Vec2JS): Vec2JS = definedExternally
        fun subtract(out: Vec2JS, a: Vec2JS, b: Vec2JS): Vec2JS = definedExternally
        fun sub(): Nothing = definedExternally
        fun multiply(out: Vec2JS, a: Vec2JS, b: Vec2JS): Vec2JS = definedExternally
        fun mul(): Nothing = definedExternally
        fun divide(out: Vec2JS, a: Vec2JS, b: Vec2JS): Vec2JS = definedExternally
        fun div(): Nothing = definedExternally
        fun ceil(out: Vec2JS, a: Vec2JS): Vec2JS = definedExternally
        fun floor(out: Vec2JS, a: Vec2JS): Vec2JS = definedExternally
        fun min(out: Vec2JS, a: Vec2JS, b: Vec2JS): Vec2JS = definedExternally
        fun max(out: Vec2JS, a: Vec2JS, b: Vec2JS): Vec2JS = definedExternally
        fun round(out: Vec2JS, a: Vec2JS): Vec2JS = definedExternally
        fun scale(out: Vec2JS, a: Vec2JS, b: Number): Vec2JS = definedExternally
        fun scaleAndAdd(out: Vec2JS, a: Vec2JS, b: Vec2JS, scale: Number): Vec2JS = definedExternally
        fun distance(a: Vec2JS, b: Vec2JS): Number = definedExternally
        fun dist(): Nothing = definedExternally
        fun squaredDistance(a: Vec2JS, b: Vec2JS): Number = definedExternally
        fun sqrDist(): Nothing = definedExternally
        fun length(a: Vec2JS): Number = definedExternally
        fun len(): Nothing = definedExternally
        fun squaredLength(a: Vec2JS): Number = definedExternally
        fun sqrLen(): Nothing = definedExternally
        fun negate(out: Vec2JS, a: Vec2JS): Vec2JS = definedExternally
        fun inverse(out: Vec2JS, a: Vec2JS): Vec2JS = definedExternally
        fun normalize(out: Vec2JS, a: Vec2JS): Vec2JS = definedExternally
        fun dot(a: Vec2JS, b: Vec2JS): Number = definedExternally
        fun cross(out: Vec3JS, a: Vec2JS, b: Vec2JS): Vec3JS = definedExternally
        fun lerp(out: Vec2JS, a: Vec2JS, b: Vec2JS, t: Number): Vec2JS = definedExternally
        fun random(out: Vec2JS, scale: Number): Vec2JS = definedExternally
        fun transformMat2(out: Vec2JS, a: Vec2JS, m: Mat2JS): Vec2JS = definedExternally
        fun transformMat2d(out: Vec2JS, a: Vec2JS, m: Mat2dJS): Vec2JS = definedExternally
        fun transformMat3(out: Vec2JS, a: Vec2JS, m: Mat3JS): Vec2JS = definedExternally
        fun transformMat4(out: Vec2JS, a: Vec2JS, m: Mat4JS): Vec2JS = definedExternally
        fun forEach(a: dynamic, stride: Number, offset: Number, count: Number, fn: dynamic, arg: dynamic): dynamic = definedExternally
        fun str(a: Vec2JS): String = definedExternally
        fun exactEquals(a: Vec2JS, b: Vec2JS): Boolean = definedExternally
        fun equals(a: Vec2JS, b: Vec2JS): Boolean = definedExternally
    }
}
