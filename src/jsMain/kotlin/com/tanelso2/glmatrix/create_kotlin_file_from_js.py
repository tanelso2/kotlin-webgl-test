#!/usr/bin/env python3
import json
import subprocess
import sys

assert len(sys.argv) > 1, "Need to provide a filename"

def extract_type_name(d):
    type_names_list = d["type"]["names"]
    assert len(type_names_list) == 1, "multiple type names! Assumptions are failing!"
    return type_names_list[0]

def get_return_type(d):
    if "returns" in d:
        return_list = d["returns"]
        assert len(return_list) <= 1, "Multiple returns! Assumptions are failing!"
        if len(return_list) == 0:
            return None
        return extract_type_name(return_list[0])
    else:
        return None

js_to_kotlin_types = {
    'Number': 'Number',
    'vec2': 'Vec2JS',
    'vec3': 'Vec3JS',
    'vec4': 'Vec4JS',
    'mat2': 'Mat2JS',
    'mat3': 'Mat3JS',
    'mat4': 'Mat4JS',
    'mat2d': 'Mat2dJS',
    'quat': 'QuatJS',
    'quat4': 'QuatJS',
    'String': 'String',
    'Boolean': 'Boolean',
    'number': 'Number'
}

def convert_type(type_name):
    if type_name not in js_to_kotlin_types:
        print(f"Unknown type {type_name}")
        return "dynamic"
    return js_to_kotlin_types[type_name]

def function_str(func_data):
    name = func_data["name"]
    if "params" in func_data:
        params_strs = [f"{d['name']}: {convert_type(d['type'])}" for d in func_data["params"]]
        params_str = ", ".join(params_strs)
    else:
        params_str = ""
    if "ret_type" in func_data:
        ret_type = convert_type(func_data["ret_type"])
    else:
        ret_type = "Nothing"
    return f"fun {name}({params_str}): {ret_type} = noImpl"

for current_file in sys.argv[1:]:
    json_output = subprocess.check_output(["jsdoc2md", "--json", current_file])
    data = json.loads(json_output)

    module_name = current_file.replace(".js", "")
    kotlin_file_name = module_name.capitalize() + ".kt"
    typealias_name = module_name.capitalize() + "JS"



    functions = list()
    for elem in data:
        if elem["kind"] == "function":
            function_info = dict()
            function_info["name"] = elem["name"]
            ret_type = get_return_type(elem)
            if ret_type is not None:
                function_info["ret_type"] = ret_type
            if "params" in elem:
                function_info["params"] = [{"name": d["name"], "type": extract_type_name(d)} for d in elem["params"]]
            functions.append(function_info)


    formated_functions = ('\n' + 8*' ').join([function_str(f) for f in functions])

    file_format = f"""package com.tanelso2.glmatrix

import org.khronos.webgl.Float32Array

typealias {typealias_name} = Float32Array

external open class {module_name} {{
    companion object {{
        {formated_functions}
    }}
}}
"""

    with open(kotlin_file_name, 'w') as fd:
        fd.write(file_format)
