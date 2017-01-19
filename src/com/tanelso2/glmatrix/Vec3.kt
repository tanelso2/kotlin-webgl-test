package com.tanelso2.glmatrix

import org.khronos.webgl.Float32Array

typealias Vec3JS = Float32Array

class Vec3(val array: Vec3JS) {
    constructor(x: Number, y: Number, z: Number):
            this(vec3.fromValues(x.toFloat(), y.toFloat(), z.toFloat()))
}

external open class vec3 {
    companion object {
        fun create(): Vec3JS = noImpl
        fun clone(a: Vec3JS): Vec3JS = noImpl
        fun fromValues(x: Number, y: Number, z: Number): Vec3JS = noImpl
        fun copy(out: Vec3JS, a: Vec3JS): Vec3JS = noImpl
        fun set(out: Vec3JS, x: Number, y: Number, z: Number): Vec3JS = noImpl
        fun add(out: Vec3JS, a: Vec3JS, b: Vec3JS): Vec3JS = noImpl
        fun subtract(out: Vec3JS, a: Vec3JS, b: Vec3JS): Vec3JS = noImpl
        fun sub(): Nothing = noImpl
        fun multiply(out: Vec3JS, a: Vec3JS, b: Vec3JS): Vec3JS = noImpl
        fun mul(): Nothing = noImpl
        fun divide(out: Vec3JS, a: Vec3JS, b: Vec3JS): Vec3JS = noImpl
        fun div(): Nothing = noImpl
        fun ceil(out: Vec3JS, a: Vec3JS): Vec3JS = noImpl
        fun floor(out: Vec3JS, a: Vec3JS): Vec3JS = noImpl
        fun min(out: Vec3JS, a: Vec3JS, b: Vec3JS): Vec3JS = noImpl
        fun max(out: Vec3JS, a: Vec3JS, b: Vec3JS): Vec3JS = noImpl
        fun round(out: Vec3JS, a: Vec3JS): Vec3JS = noImpl
        fun scale(out: Vec3JS, a: Vec3JS, b: Number): Vec3JS = noImpl
        fun scaleAndAdd(out: Vec3JS, a: Vec3JS, b: Vec3JS, scale: Number): Vec3JS = noImpl
        fun distance(a: Vec3JS, b: Vec3JS): Number = noImpl
        fun dist(): Nothing = noImpl
        fun squaredDistance(a: Vec3JS, b: Vec3JS): Number = noImpl
        fun sqrDist(): Nothing = noImpl
        fun length(a: Vec3JS): Number = noImpl
        fun len(): Nothing = noImpl
        fun squaredLength(a: Vec3JS): Number = noImpl
        fun sqrLen(): Nothing = noImpl
        fun negate(out: Vec3JS, a: Vec3JS): Vec3JS = noImpl
        fun inverse(out: Vec3JS, a: Vec3JS): Vec3JS = noImpl
        fun normalize(out: Vec3JS, a: Vec3JS): Vec3JS = noImpl
        fun dot(a: Vec3JS, b: Vec3JS): Number = noImpl
        fun cross(out: Vec3JS, a: Vec3JS, b: Vec3JS): Vec3JS = noImpl
        fun lerp(out: Vec3JS, a: Vec3JS, b: Vec3JS, t: Number): Vec3JS = noImpl
        fun hermite(out: Vec3JS, a: Vec3JS, b: Vec3JS, c: Vec3JS, d: Vec3JS, t: Number): Vec3JS = noImpl
        fun bezier(out: Vec3JS, a: Vec3JS, b: Vec3JS, c: Vec3JS, d: Vec3JS, t: Number): Vec3JS = noImpl
        fun random(out: Vec3JS, scale: Number): Vec3JS = noImpl
        fun transformMat4(out: Vec3JS, a: Vec3JS, m: Mat4JS): Vec3JS = noImpl
        fun transformMat3(out: Vec3JS, a: Vec3JS, m: Mat4JS): Vec3JS = noImpl
        fun transformQuat(out: Vec3JS, a: Vec3JS, q: QuatJS): Vec3JS = noImpl
        fun rotateX(out: Vec3JS, a: Vec3JS, b: Vec3JS, c: Number): Vec3JS = noImpl
        fun rotateY(out: Vec3JS, a: Vec3JS, b: Vec3JS, c: Number): Vec3JS = noImpl
        fun rotateZ(out: Vec3JS, a: Vec3JS, b: Vec3JS, c: Number): Vec3JS = noImpl
        fun forEach(a: dynamic, stride: Number, offset: Number, count: Number, fn: dynamic, arg: dynamic): dynamic = noImpl
        fun angle(a: Vec3JS, b: Vec3JS): Number = noImpl
        fun str(a: Vec3JS): String = noImpl
        fun exactEquals(a: Vec3JS, b: Vec3JS): Boolean = noImpl
        fun equals(a: Vec3JS, b: Vec3JS): Boolean = noImpl
    }
}
