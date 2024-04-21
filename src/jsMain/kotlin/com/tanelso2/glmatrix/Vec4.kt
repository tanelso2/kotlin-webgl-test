package com.tanelso2.glmatrix

import org.khronos.webgl.Float32Array

typealias Vec4JS = Float32Array

external open class vec4 {
    companion object {
        fun create(): Vec4JS = definedExternally
        fun clone(a: Vec4JS): Vec4JS = definedExternally
        fun fromValues(x: Number, y: Number, z: Number, w: Number): Vec4JS = definedExternally
        fun copy(out: Vec4JS, a: Vec4JS): Vec4JS = definedExternally
        fun set(out: Vec4JS, x: Number, y: Number, z: Number, w: Number): Vec4JS = definedExternally
        fun add(out: Vec4JS, a: Vec4JS, b: Vec4JS): Vec4JS = definedExternally
        fun subtract(out: Vec4JS, a: Vec4JS, b: Vec4JS): Vec4JS = definedExternally
        fun sub(): Nothing = definedExternally
        fun multiply(out: Vec4JS, a: Vec4JS, b: Vec4JS): Vec4JS = definedExternally
        fun mul(): Nothing = definedExternally
        fun divide(out: Vec4JS, a: Vec4JS, b: Vec4JS): Vec4JS = definedExternally
        fun div(): Nothing = definedExternally
        fun ceil(out: Vec4JS, a: Vec4JS): Vec4JS = definedExternally
        fun floor(out: Vec4JS, a: Vec4JS): Vec4JS = definedExternally
        fun min(out: Vec4JS, a: Vec4JS, b: Vec4JS): Vec4JS = definedExternally
        fun max(out: Vec4JS, a: Vec4JS, b: Vec4JS): Vec4JS = definedExternally
        fun round(out: Vec4JS, a: Vec4JS): Vec4JS = definedExternally
        fun scale(out: Vec4JS, a: Vec4JS, b: Number): Vec4JS = definedExternally
        fun scaleAndAdd(out: Vec4JS, a: Vec4JS, b: Vec4JS, scale: Number): Vec4JS = definedExternally
        fun distance(a: Vec4JS, b: Vec4JS): Number = definedExternally
        fun dist(): Nothing = definedExternally
        fun squaredDistance(a: Vec4JS, b: Vec4JS): Number = definedExternally
        fun sqrDist(): Nothing = definedExternally
        fun length(a: Vec4JS): Number = definedExternally
        fun len(): Nothing = definedExternally
        fun squaredLength(a: Vec4JS): Number = definedExternally
        fun sqrLen(): Nothing = definedExternally
        fun negate(out: Vec4JS, a: Vec4JS): Vec4JS = definedExternally
        fun inverse(out: Vec4JS, a: Vec4JS): Vec4JS = definedExternally
        fun normalize(out: Vec4JS, a: Vec4JS): Vec4JS = definedExternally
        fun dot(a: Vec4JS, b: Vec4JS): Number = definedExternally
        fun lerp(out: Vec4JS, a: Vec4JS, b: Vec4JS, t: Number): Vec4JS = definedExternally
        fun random(out: Vec4JS, scale: Number): Vec4JS = definedExternally
        fun transformMat4(out: Vec4JS, a: Vec4JS, m: Mat4JS): Vec4JS = definedExternally
        fun transformQuat(out: Vec4JS, a: Vec4JS, q: QuatJS): Vec4JS = definedExternally
        fun forEach(a: dynamic, stride: Number, offset: Number, count: Number, fn: dynamic, arg: dynamic): dynamic = definedExternally
        fun str(a: Vec4JS): String = definedExternally
        fun exactEquals(a: Vec4JS, b: Vec4JS): Boolean = definedExternally
        fun equals(a: Vec4JS, b: Vec4JS): Boolean = definedExternally
    }
}
