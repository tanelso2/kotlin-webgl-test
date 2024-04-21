package com.tanelso2.glmatrix

import org.khronos.webgl.Float32Array

typealias Vec3JS = Float32Array

class Vec3(val array: Vec3JS) {
    constructor(x: Number, y: Number, z: Number):
            this(vec3.fromValues(x.toFloat(), y.toFloat(), z.toFloat()))
    constructor():
            this(vec3.create())

    fun cross(other: Vec3): Vec3 {
        val ret = Vec3()
        vec3.cross(ret.array, this.array, other.array)
        return ret
    }

    fun normalize() {
        vec3.normalize(array, array)
    }

    operator fun plus(other: Vec3) = add(other)

    fun add(other: Vec3): Vec3 {
        val ret = Vec3()
        vec3.add(ret.array, this.array, other.array)
        return ret
    }
}

external open class vec3 {
    companion object {
        fun create(): Vec3JS = definedExternally
        fun clone(a: Vec3JS): Vec3JS = definedExternally
        fun fromValues(x: Number, y: Number, z: Number): Vec3JS = definedExternally
        fun copy(out: Vec3JS, a: Vec3JS): Vec3JS = definedExternally
        fun set(out: Vec3JS, x: Number, y: Number, z: Number): Vec3JS = definedExternally
        fun add(out: Vec3JS, a: Vec3JS, b: Vec3JS): Vec3JS = definedExternally
        fun subtract(out: Vec3JS, a: Vec3JS, b: Vec3JS): Vec3JS = definedExternally
        fun sub(): Nothing = definedExternally
        fun multiply(out: Vec3JS, a: Vec3JS, b: Vec3JS): Vec3JS = definedExternally
        fun mul(): Nothing = definedExternally
        fun divide(out: Vec3JS, a: Vec3JS, b: Vec3JS): Vec3JS = definedExternally
        fun div(): Nothing = definedExternally
        fun ceil(out: Vec3JS, a: Vec3JS): Vec3JS = definedExternally
        fun floor(out: Vec3JS, a: Vec3JS): Vec3JS = definedExternally
        fun min(out: Vec3JS, a: Vec3JS, b: Vec3JS): Vec3JS = definedExternally
        fun max(out: Vec3JS, a: Vec3JS, b: Vec3JS): Vec3JS = definedExternally
        fun round(out: Vec3JS, a: Vec3JS): Vec3JS = definedExternally
        fun scale(out: Vec3JS, a: Vec3JS, b: Number): Vec3JS = definedExternally
        fun scaleAndAdd(out: Vec3JS, a: Vec3JS, b: Vec3JS, scale: Number): Vec3JS = definedExternally
        fun distance(a: Vec3JS, b: Vec3JS): Number = definedExternally
        fun dist(): Nothing = definedExternally
        fun squaredDistance(a: Vec3JS, b: Vec3JS): Number = definedExternally
        fun sqrDist(): Nothing = definedExternally
        fun length(a: Vec3JS): Number = definedExternally
        fun len(): Nothing = definedExternally
        fun squaredLength(a: Vec3JS): Number = definedExternally
        fun sqrLen(): Nothing = definedExternally
        fun negate(out: Vec3JS, a: Vec3JS): Vec3JS = definedExternally
        fun inverse(out: Vec3JS, a: Vec3JS): Vec3JS = definedExternally
        fun normalize(out: Vec3JS, a: Vec3JS): Vec3JS = definedExternally
        fun dot(a: Vec3JS, b: Vec3JS): Number = definedExternally
        fun cross(out: Vec3JS, a: Vec3JS, b: Vec3JS): Vec3JS = definedExternally
        fun lerp(out: Vec3JS, a: Vec3JS, b: Vec3JS, t: Number): Vec3JS = definedExternally
        fun hermite(out: Vec3JS, a: Vec3JS, b: Vec3JS, c: Vec3JS, d: Vec3JS, t: Number): Vec3JS = definedExternally
        fun bezier(out: Vec3JS, a: Vec3JS, b: Vec3JS, c: Vec3JS, d: Vec3JS, t: Number): Vec3JS = definedExternally
        fun random(out: Vec3JS, scale: Number): Vec3JS = definedExternally
        fun transformMat4(out: Vec3JS, a: Vec3JS, m: Mat4JS): Vec3JS = definedExternally
        fun transformMat3(out: Vec3JS, a: Vec3JS, m: Mat4JS): Vec3JS = definedExternally
        fun transformQuat(out: Vec3JS, a: Vec3JS, q: QuatJS): Vec3JS = definedExternally
        fun rotateX(out: Vec3JS, a: Vec3JS, b: Vec3JS, c: Number): Vec3JS = definedExternally
        fun rotateY(out: Vec3JS, a: Vec3JS, b: Vec3JS, c: Number): Vec3JS = definedExternally
        fun rotateZ(out: Vec3JS, a: Vec3JS, b: Vec3JS, c: Number): Vec3JS = definedExternally
        fun forEach(a: dynamic, stride: Number, offset: Number, count: Number, fn: dynamic, arg: dynamic): dynamic = definedExternally
        fun angle(a: Vec3JS, b: Vec3JS): Number = definedExternally
        fun str(a: Vec3JS): String = definedExternally
        fun exactEquals(a: Vec3JS, b: Vec3JS): Boolean = definedExternally
        fun equals(a: Vec3JS, b: Vec3JS): Boolean = definedExternally
    }
}
