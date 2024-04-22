import kotlinx.browser.document
import org.w3c.dom.HTMLInputElement
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

fun getInputElem(s: String): HTMLInputElement =
    document.getElementById(s) as HTMLInputElement

class HTMLInputProperty(private val input: HTMLInputElement): ReadOnlyProperty<Any?, Double> {
    override fun getValue(thisRef: Any?, property: KProperty<*>): Double = input.valueAsNumber
}

fun HTMLInputProperty(s: String): HTMLInputProperty {
    val elem = getInputElem(s)
    return HTMLInputProperty(elem)
}

class ArrayOfInputsProperty(private val inputs: Array<HTMLInputElement>): ReadOnlyProperty<Any?, Array<Float>> {
    private val cache = inputs.map { it.valueAsNumber.toFloat() }.toTypedArray()
    init {
        inputs.mapIndexed { i,input ->
            input.oninput =  {
                cache[i] = input.valueAsNumber.toFloat()
                null // Function is required to return a js value
            }
        }
    }
    override fun getValue(thisRef: Any?, property: KProperty<*>): Array<Float> = cache
}

fun ArrayOfInputsProperty(vararg inputNames: String): ArrayOfInputsProperty {
    val elems = inputNames.map { getInputElem(it) }.toTypedArray()
    return ArrayOfInputsProperty(elems)
}