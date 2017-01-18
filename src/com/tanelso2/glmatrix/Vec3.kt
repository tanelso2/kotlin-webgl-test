package com.tanelso2.glmatrix

import org.khronos.webgl.Float32Array

class Vec3(val array: Float32Array) {
    constructor(x: Float, y: Float, z: Float) : this(vec3.fromValues(x, y, z))
}

external open class vec3 {
    companion object {
        fun fromValues(x: Float, y: Float, z: Float): Float32Array = noImpl
    }
}

