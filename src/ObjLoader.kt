import org.khronos.webgl.Float32Array

class ObjLoader(source: String) {
    private val points = ArrayList<Point>()
    private val orderedPoints = ArrayList<Point>()
    init {
        val lines = source.split('\n')
        for(line in lines) {
            if(line.startsWith('v')) {
                val values = line.split(' ').filter { it != "" }
                points.add(Point(
                    safeParseDouble(values[1])!!,
                    safeParseDouble(values[2])!!,
                    safeParseDouble(values[3])!!
                ))
            }
            if(line.startsWith('f')) {
                val values = line.split(' ').filter { it != "" }
                val p1idx = safeParseInt(values[1])!! - 1
                val p2idx = safeParseInt(values[2])!! - 1
                val p3idx = safeParseInt(values[3])!! - 1
                val p1 = points[p1idx]
                val p2 = points[p2idx]
                val p3 = points[p3idx]
                orderedPoints.add(p1)
                orderedPoints.add(p2)
                orderedPoints.add(p3)
            }
        }
    }

    fun getVertices(): Float32Array {
        val floatList = orderedPoints.flatMap { it.list() }
        return Float32Array(floatList.toTypedArray())
    }

    fun getColors(): Float32Array {
        val colorsList = orderedPoints.flatMap { it.color.list() }
        return Float32Array(colorsList.toTypedArray())
    }

    fun getNumPoints(): Int = orderedPoints.size

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

    data class Face(val p1: Point, val p2: Point, val p3: Point)
}