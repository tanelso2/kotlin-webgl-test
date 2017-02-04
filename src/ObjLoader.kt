import com.tanelso2.glmatrix.Vec3
import org.khronos.webgl.Float32Array
import org.khronos.webgl.Uint16Array

class ObjLoader(source: String) {
    private val points = ArrayList<Point>()
    private val faces = ArrayList<Face>()
    private val normals = ArrayList<Vec3>()
    init {
        val lines = source.split('\n')
        lines.forEach { line ->
            val values = line.split(' ').filter { it != "" }
            when(values.getOrNull(0)) {
                "v" -> points.add(Point(
                        safeParseDouble(values[1])!!,
                        safeParseDouble(values[2])!!,
                        safeParseDouble(values[3])!!))
                "f" -> faces.add(Face(
                        safeParseInt(values[1])!! - 1,
                        safeParseInt(values[2])!! - 1,
                        safeParseInt(values[3])!! - 1))
                "vn" -> normals.add(Vec3(
                        safeParseDouble(values[1])!!,
                        safeParseDouble(values[2])!!,
                        safeParseDouble(values[3])!!))
            }
        }
        if (normals.isEmpty()) {
            computeNormals()
        }
    }

    fun computeNormals() {
        faces.forEach { face ->
            val p1 = points[face.p1.toInt()]
            val p2 = points[face.p2.toInt()]
            val p3 = points[face.p3.toInt()]
            val w = Vec3(
                    p2.x - p1.x,
                    p2.y - p1.y,
                    p2.z - p1.z
            )
            val v = Vec3(
                    p3.x - p1.x,
                    p3.y - p1.y,
                    p3.z - p1.z
            )
            val normal = v.cross(w)
            normal.normalize()
            normals.add(normal)
        }
    }

    fun getVertices(): Float32Array {
        val floatList = points.flatMap { it.list() }
        return Float32Array(floatList.toTypedArray())
    }

    fun getVertexNormals(): Float32Array {
        val floatList = normals.flatMap { listOf(it.array[0], it.array[1], it.array[2]) }
        return Float32Array(floatList.toTypedArray())
    }

    fun getFaces(): Uint16Array {
        val intList = faces.flatMap { it.list() }
        return Uint16Array(intList.toTypedArray())
    }

    fun getColors(): Float32Array {
        val colorsList = points.flatMap { it.color.list() }
        return Float32Array(colorsList.toTypedArray())
    }

    fun getNumFaces(): Int = faces.size

    data class Face(val p1: Short, val p2: Short, val p3: Short) {
        constructor(p1: Number, p2: Number, p3: Number):
                this(p1.toShort(), p2.toShort(), p3.toShort())

        fun list() = listOf(p1, p2, p3)
    }

    data class Point(val x: Float, val y: Float, val z: Float, val color: Color) {
        constructor(x: Number, y: Number, z: Number):
                this(x.toFloat(), y.toFloat(), z.toFloat(), Color.randomColor())

        fun list() = listOf(x, y, z)

        data class Color(val r: Float, val g: Float, val b: Float, val a: Float) {
            constructor(r: Number, g: Number, b: Number, a:Number):
                    this(r.toFloat(), g.toFloat(), b.toFloat(), a.toFloat())

            companion object {
                fun randomColor() = Color(
                        Math.random(),
                        Math.random(),
                        Math.random(),
                        1.0
                )
            }

            fun list() = listOf(r, g, b, a)
        }
    }

}