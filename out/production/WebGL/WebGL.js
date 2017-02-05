if (typeof kotlin === 'undefined') {
  throw new Error("Error loading module 'WebGL'. Its dependency 'kotlin' was not found. Please, check whether 'kotlin' is loaded prior to 'WebGL'.");
}
var WebGL = function (_, Kotlin) {
  'use strict';
  var IllegalStateException = Kotlin.kotlin.IllegalStateException;
  var lazy = Kotlin.kotlin.lazy_un3fny$;
  var listOf = Kotlin.kotlin.collections.listOf_9mqe4v$;
  var ArrayList_init = Kotlin.kotlin.collections.ArrayList_init_za3lpa$;
  var split = Kotlin.kotlin.text.split_rhc0qh$;
  var getOrNull = Kotlin.kotlin.collections.getOrNull_3iu80n$;
  var HashMap_init = Kotlin.kotlin.collections.HashMap_init;
  function WebGLWrapper() {
    var tmp$, tmp$_0, tmp$_1, tmp$_2, tmp$_3, tmp$_4, tmp$_5, tmp$_6, tmp$_7;
    this.canvas = Kotlin.isType(tmp$ = document.getElementById('webglCanvas'), HTMLCanvasElement) ? tmp$ : Kotlin.throwCCE();
    this.webgl = Kotlin.isType(tmp$_0 = this.canvas.getContext('webgl'), WebGLRenderingContext) ? tmp$_0 : Kotlin.throwCCE();
    tmp$_1 = this.webgl.createProgram();
    if (tmp$_1 == null) {
      throw new IllegalStateException('Could not initialize shader program');
    }
    this.shaderProgram = tmp$_1;
    this.scaleInput = Kotlin.isType(tmp$_2 = document.getElementById('scaleInput'), HTMLInputElement) ? tmp$_2 : Kotlin.throwCCE();
    this.scaleFactor = this.scaleInput.valueAsNumber;
    this.lightPosXInput = Kotlin.isType(tmp$_3 = document.getElementById('lightPosX'), HTMLInputElement) ? tmp$_3 : Kotlin.throwCCE();
    this.lightPosYInput = Kotlin.isType(tmp$_4 = document.getElementById('lightPosY'), HTMLInputElement) ? tmp$_4 : Kotlin.throwCCE();
    this.lightPosZInput = Kotlin.isType(tmp$_5 = document.getElementById('lightPosZ'), HTMLInputElement) ? tmp$_5 : Kotlin.throwCCE();
    this.lightPos = [this.lightPosXInput.valueAsNumber, this.lightPosYInput.valueAsNumber, this.lightPosZInput.valueAsNumber];
    this.shininessInput = Kotlin.isType(tmp$_6 = document.getElementById('shininessInput'), HTMLInputElement) ? tmp$_6 : Kotlin.throwCCE();
    this.shininess = this.shininessInput.valueAsNumber;
    this.rotationSpeedInput = Kotlin.isType(tmp$_7 = document.getElementById('rotationSpeedInput'), HTMLInputElement) ? tmp$_7 : Kotlin.throwCCE();
    this.rotationSpeed = this.rotationSpeedInput.valueAsNumber;
    this.webgl.enable(WebGLRenderingContext.DEPTH_TEST);
    this.scaleInput.oninput = WebGLWrapper_init$lambda(this);
    this.lightPosXInput.oninput = WebGLWrapper_init$lambda_0(this);
    this.lightPosYInput.oninput = WebGLWrapper_init$lambda_1(this);
    this.lightPosZInput.oninput = WebGLWrapper_init$lambda_2(this);
    this.shininessInput.oninput = WebGLWrapper_init$lambda_3(this);
    this.rotationSpeedInput.oninput = WebGLWrapper_init$lambda_4(this);
    this.windowWidth = 800;
    this.windowHeight = 600;
    this.vertexShaderLocation_0 = 'vertex-shader.glsl';
    this.fragmentShaderLocation_0 = 'frag-shader.glsl';
    this.objFileLocation_0 = 'teapot.obj';
    this.resourceList = [this.fragmentShaderLocation_0, this.vertexShaderLocation_0, this.objFileLocation_0];
    this.resourceLoader = new ResourceLoader(this.resourceList.slice());
    this.objFileLoader$delegate = lazy(WebGLWrapper$objFileLoader$lambda(this));
    this.rotation = 0.0;
  }
  Object.defineProperty(WebGLWrapper.prototype, 'objFileLoader', {
    get: function () {
      var $receiver = this.objFileLoader$delegate;
      new Kotlin.PropertyMetadata('objFileLoader');
      return $receiver.value;
    }
  });
  function WebGLWrapper$setup$lambda(this$WebGLWrapper) {
    return function (it) {
      this$WebGLWrapper.render();
    };
  }
  function WebGLWrapper$setup$lambda_0(this$WebGLWrapper) {
    return function (it) {
      this$WebGLWrapper.setup();
    };
  }
  WebGLWrapper.prototype.setup = function () {
    if (this.resourceLoader.allLoaded()) {
      this.compileShaderProgram_0();
      this.setupAttribute_0('aVertexPosition', this.objFileLoader.getVertices());
      this.setupAttribute_0('aVertexNormal', this.objFileLoader.getVertexNormals());
      Kotlin.println('Vertex array size = ' + this.objFileLoader.getVertices().length);
      Kotlin.println('Vertex normal array size = ' + this.objFileLoader.getVertexNormals().length);
      var ambientColor = [0.10000000149011612, 0.10000000149011612, 0.10000000149011612];
      var diffuseColor = [0.4000000059604645, 0.4000000059604645, 0.0];
      var specularColor = [1.0, 1.0, 1.0];
      this.setupUniformVec3Float_0(this.lightPos, 'uLightPos');
      this.setupUniformVec3Float_0(ambientColor, 'uAmbientColor');
      this.setupUniformVec3Float_0(diffuseColor, 'uDiffuseColor');
      this.setupUniformVec3Float_0(specularColor, 'uSpecularColor');
      var faceIndexBuffer = this.webgl.createBuffer();
      this.webgl.bindBuffer(WebGLRenderingContext.ELEMENT_ARRAY_BUFFER, faceIndexBuffer);
      this.webgl.bufferData(WebGLRenderingContext.ELEMENT_ARRAY_BUFFER, this.objFileLoader.getFaces(), WebGLRenderingContext.STATIC_DRAW);
      window.requestAnimationFrame(WebGLWrapper$setup$lambda(this));
    }
     else {
      window.requestAnimationFrame(WebGLWrapper$setup$lambda_0(this));
    }
  };
  WebGLWrapper.prototype.setupUniformVec3Float_0 = function (lightPos, name) {
    var lightPositionLoc = this.webgl.getUniformLocation(this.shaderProgram, name);
    this.webgl.uniform3fv(lightPositionLoc, lightPos);
  };
  WebGLWrapper.prototype.setupAttribute_0 = function (attributeName, attributeArray) {
    var vertexPositionAttribute = this.webgl.getAttribLocation(this.shaderProgram, attributeName);
    this.webgl.enableVertexAttribArray(vertexPositionAttribute);
    var vertexPositionBuffer = this.webgl.createBuffer();
    this.webgl.bindBuffer(WebGLRenderingContext.ARRAY_BUFFER, vertexPositionBuffer);
    this.webgl.bufferData(WebGLRenderingContext.ARRAY_BUFFER, attributeArray, WebGLRenderingContext.STATIC_DRAW);
    this.webgl.vertexAttribPointer(vertexPositionAttribute, 3, WebGLRenderingContext.FLOAT, false, 0, 0);
  };
  WebGLWrapper.prototype.compileShaderProgram_0 = function () {
    var tmp$, tmp$_0;
    var vertexShaderSource = (tmp$ = this.resourceLoader.get_61zpoe$(this.vertexShaderLocation_0)) != null ? tmp$ : Kotlin.throwNPE();
    var vertexShader = this.getVertexShader_61zpoe$(vertexShaderSource);
    var fragmentShaderSource = (tmp$_0 = this.resourceLoader.get_61zpoe$(this.fragmentShaderLocation_0)) != null ? tmp$_0 : Kotlin.throwNPE();
    var fragmentShader = this.getFragmentShader_61zpoe$(fragmentShaderSource);
    this.webgl.attachShader(this.shaderProgram, vertexShader);
    this.webgl.attachShader(this.shaderProgram, fragmentShader);
    this.webgl.linkProgram(this.shaderProgram);
    Kotlin.println(this.webgl.getProgramInfoLog(this.shaderProgram));
    this.webgl.useProgram(this.shaderProgram);
  };
  WebGLWrapper.prototype.getFragmentShader_61zpoe$ = function (source) {
    return this.getShader_0(source, WebGLRenderingContext.FRAGMENT_SHADER);
  };
  WebGLWrapper.prototype.getVertexShader_61zpoe$ = function (source) {
    return this.getShader_0(source, WebGLRenderingContext.VERTEX_SHADER);
  };
  WebGLWrapper.prototype.getShader_0 = function (shaderSource, shaderType) {
    var tmp$;
    var shader = this.webgl.createShader(shaderType);
    this.webgl.shaderSource(shader, shaderSource);
    this.webgl.compileShader(shader);
    if (!(typeof (tmp$ = this.webgl.getShaderParameter(shader, WebGLRenderingContext.COMPILE_STATUS)) === 'boolean' ? tmp$ : Kotlin.throwCCE())) {
      Kotlin.println(this.webgl.getShaderInfoLog(shader));
    }
    if (shader == null) {
      throw new IllegalStateException('Shader is null!');
    }
    return shader;
  };
  WebGLWrapper.prototype.draw = function () {
    this.setupUniformVec3Float_0(this.lightPos, 'uLightPos');
    var shininessUniform = this.webgl.getUniformLocation(this.shaderProgram, 'shininess');
    this.webgl.uniform1f(shininessUniform, this.shininess);
    var pMatrix = Mat4_init();
    pMatrix.perspective_1ugm5o$(Math.PI / 3, 16.0 / 9.0, 0.1, 60.0);
    var vMatrix = Mat4_init();
    vMatrix.lookAt_p3layc$(Vec3_init(20, 20, 20), Vec3_init(0, 0, 0), Vec3_init(0, 0, 1));
    var mMatrix = Mat4_init();
    mMatrix.scale_3p81yu$(this.scaleFactor);
    this.rotation += this.rotationSpeed;
    mMatrix.rotateX_3p81yu$(Math.PI / 2);
    mMatrix.rotateY_3p81yu$(this.rotation);
    var nMatrix = Mat3$Companion_getInstance().fromMat4_k68j1l$(vMatrix.times_k68j1l$(mMatrix));
    nMatrix.transpose();
    nMatrix.invert();
    var nMatrixUniform = this.webgl.getUniformLocation(this.shaderProgram, 'uNMatrix');
    this.webgl.uniformMatrix3fv(nMatrixUniform, false, nMatrix.array);
    var pMatrixUniform = this.webgl.getUniformLocation(this.shaderProgram, 'uPMatrix');
    this.webgl.uniformMatrix4fv(pMatrixUniform, false, pMatrix.array);
    var vMatrixUniform = this.webgl.getUniformLocation(this.shaderProgram, 'uVMatrix');
    this.webgl.uniformMatrix4fv(vMatrixUniform, false, vMatrix.array);
    var mMatrixUniform = this.webgl.getUniformLocation(this.shaderProgram, 'uMMatrix');
    this.webgl.uniformMatrix4fv(mMatrixUniform, false, mMatrix.array);
    this.webgl.drawElements(WebGLRenderingContext.TRIANGLES, this.objFileLoader.getNumFaces() * 3, WebGLRenderingContext.UNSIGNED_SHORT, 0);
  };
  function WebGLWrapper$render$lambda(this$WebGLWrapper) {
    return function (it) {
      this$WebGLWrapper.render();
    };
  }
  WebGLWrapper.prototype.render = function () {
    this.webgl.clearColor(0.0, 0.0, 0.0, 1.0);
    this.webgl.clear(WebGLRenderingContext.COLOR_BUFFER_BIT | WebGLRenderingContext.DEPTH_BUFFER_BIT);
    this.draw();
    window.requestAnimationFrame(WebGLWrapper$render$lambda(this));
  };
  function WebGLWrapper_init$lambda(this$WebGLWrapper) {
    return function (it) {
      this$WebGLWrapper.scaleFactor = this$WebGLWrapper.scaleInput.valueAsNumber;
      return null;
    };
  }
  function WebGLWrapper_init$lambda_0(this$WebGLWrapper) {
    return function (it) {
      this$WebGLWrapper.lightPos[0] = this$WebGLWrapper.lightPosXInput.valueAsNumber;
      return null;
    };
  }
  function WebGLWrapper_init$lambda_1(this$WebGLWrapper) {
    return function (it) {
      this$WebGLWrapper.lightPos[1] = this$WebGLWrapper.lightPosYInput.valueAsNumber;
      return null;
    };
  }
  function WebGLWrapper_init$lambda_2(this$WebGLWrapper) {
    return function (it) {
      this$WebGLWrapper.lightPos[2] = this$WebGLWrapper.lightPosZInput.valueAsNumber;
      return null;
    };
  }
  function WebGLWrapper_init$lambda_3(this$WebGLWrapper) {
    return function (it) {
      this$WebGLWrapper.shininess = this$WebGLWrapper.shininessInput.valueAsNumber;
      return null;
    };
  }
  function WebGLWrapper_init$lambda_4(this$WebGLWrapper) {
    return function (it) {
      this$WebGLWrapper.rotationSpeed = this$WebGLWrapper.rotationSpeedInput.valueAsNumber;
      return null;
    };
  }
  function WebGLWrapper$objFileLoader$lambda(this$WebGLWrapper) {
    return function () {
      var tmp$;
      return new ObjLoader((tmp$ = this$WebGLWrapper.resourceLoader.get_61zpoe$(this$WebGLWrapper.objFileLocation_0)) != null ? tmp$ : Kotlin.throwNPE());
    };
  }
  WebGLWrapper.$metadata$ = {
    type: Kotlin.TYPE.CLASS,
    classIndex: Kotlin.newClassIndex(),
    simpleName: 'WebGLWrapper',
    baseClasses: []
  };
  function main$lambda$lambda(closure$html) {
    return function (it) {
      closure$html.setup();
    };
  }
  function main$lambda(it) {
    var html = new WebGLWrapper();
    return window.requestAnimationFrame(main$lambda$lambda(html));
  }
  function main(args) {
    var tmp$;
    (tmp$ = document.body) != null ? (tmp$.onload = main$lambda) : null;
  }
  function ObjLoader(source) {
    this.points_0 = ArrayList_init();
    this.faces_0 = ArrayList_init();
    this.normals_0 = ArrayList_init();
    var lines = split(source, ['\n']);
    var tmp$;
    tmp$ = lines.iterator();
    while (tmp$.hasNext()) {
      var element = tmp$.next();
      var tmp$_0, tmp$_1, tmp$_2, tmp$_3, tmp$_4, tmp$_5, tmp$_6, tmp$_7, tmp$_8, tmp$_9;
      var $receiver_0 = split(element, [' ']);
      var destination = Kotlin.kotlin.collections.ArrayList_init_za3lpa$();
      var tmp$_10;
      tmp$_10 = $receiver_0.iterator();
      while (tmp$_10.hasNext()) {
        var element_0 = tmp$_10.next();
        if (!Kotlin.equals(element_0, '')) {
          destination.add_za3rmp$(element_0);
        }
      }
      var values = destination;
      tmp$_0 = getOrNull(values, 0);
      if (Kotlin.equals(tmp$_0, 'v'))
        this.points_0.add_za3rmp$(ObjLoader$ObjLoader$Point_init((tmp$_1 = Kotlin.safeParseDouble(values.get_za3lpa$(1))) != null ? tmp$_1 : Kotlin.throwNPE(), (tmp$_2 = Kotlin.safeParseDouble(values.get_za3lpa$(2))) != null ? tmp$_2 : Kotlin.throwNPE(), (tmp$_3 = Kotlin.safeParseDouble(values.get_za3lpa$(3))) != null ? tmp$_3 : Kotlin.throwNPE()));
      else if (Kotlin.equals(tmp$_0, 'f'))
        this.faces_0.add_za3rmp$(ObjLoader$ObjLoader$Face_init(((tmp$_4 = Kotlin.safeParseInt(values.get_za3lpa$(1))) != null ? tmp$_4 : Kotlin.throwNPE()) - 1, ((tmp$_5 = Kotlin.safeParseInt(values.get_za3lpa$(2))) != null ? tmp$_5 : Kotlin.throwNPE()) - 1, ((tmp$_6 = Kotlin.safeParseInt(values.get_za3lpa$(3))) != null ? tmp$_6 : Kotlin.throwNPE()) - 1));
      else if (Kotlin.equals(tmp$_0, 'vn'))
        this.normals_0.add_za3rmp$(Vec3_init((tmp$_7 = Kotlin.safeParseDouble(values.get_za3lpa$(1))) != null ? tmp$_7 : Kotlin.throwNPE(), (tmp$_8 = Kotlin.safeParseDouble(values.get_za3lpa$(2))) != null ? tmp$_8 : Kotlin.throwNPE(), (tmp$_9 = Kotlin.safeParseDouble(values.get_za3lpa$(3))) != null ? tmp$_9 : Kotlin.throwNPE()));
    }
    if (this.normals_0.isEmpty()) {
      this.computeNormals();
    }
  }
  function ObjLoader$computeNormals$lambda(it) {
    return Vec3_init_0();
  }
  ObjLoader.prototype.computeNormals = function () {
    var tmpNormals = Kotlin.arrayFromFun(this.points_0.size, ObjLoader$computeNormals$lambda);
    var tmp$;
    tmp$ = this.faces_0.iterator();
    while (tmp$.hasNext()) {
      var element = tmp$.next();
      var idx1 = element.p1;
      var idx2 = element.p2;
      var idx3 = element.p3;
      var p1 = this.points_0.get_za3lpa$(idx1);
      var p2 = this.points_0.get_za3lpa$(idx2);
      var p3 = this.points_0.get_za3lpa$(idx3);
      var w = Vec3_init(p2.x - p1.x, p2.y - p1.y, p2.z - p1.z);
      var v = Vec3_init(p3.x - p1.x, p3.y - p1.y, p3.z - p1.z);
      var normal = v.cross_k6echg$(w);
      normal.normalize();
      tmpNormals[idx1] = tmpNormals[idx1].plus_k6echg$(normal);
      tmpNormals[idx2] = tmpNormals[idx2].plus_k6echg$(normal);
      tmpNormals[idx3] = tmpNormals[idx3].plus_k6echg$(normal);
    }
    var tmp$_0;
    for (tmp$_0 = 0; tmp$_0 !== tmpNormals.length; ++tmp$_0) {
      var element_0 = tmpNormals[tmp$_0];
      element_0.normalize();
      this.normals_0.add_za3rmp$(element_0);
    }
  };
  ObjLoader.prototype.getVertices = function () {
    var $receiver = this.points_0;
    var destination = Kotlin.kotlin.collections.ArrayList_init_za3lpa$();
    var tmp$;
    tmp$ = $receiver.iterator();
    while (tmp$.hasNext()) {
      var element = tmp$.next();
      var list = element.list();
      Kotlin.kotlin.collections.addAll_fwwv5a$(destination, list);
    }
    var floatList = destination;
    return new Float32Array(Kotlin.kotlin.collections.copyToArray(floatList));
  };
  ObjLoader.prototype.getVertexNormals = function () {
    var $receiver = this.normals_0;
    var destination = Kotlin.kotlin.collections.ArrayList_init_za3lpa$();
    var tmp$;
    tmp$ = $receiver.iterator();
    while (tmp$.hasNext()) {
      var element = tmp$.next();
      var list = listOf([element.array[0], element.array[1], element.array[2]]);
      Kotlin.kotlin.collections.addAll_fwwv5a$(destination, list);
    }
    var floatList = destination;
    return new Float32Array(Kotlin.kotlin.collections.copyToArray(floatList));
  };
  ObjLoader.prototype.getFaces = function () {
    var $receiver = this.faces_0;
    var destination = Kotlin.kotlin.collections.ArrayList_init_za3lpa$();
    var tmp$;
    tmp$ = $receiver.iterator();
    while (tmp$.hasNext()) {
      var element = tmp$.next();
      var list = element.list();
      Kotlin.kotlin.collections.addAll_fwwv5a$(destination, list);
    }
    var intList = destination;
    return new Uint16Array(Kotlin.kotlin.collections.copyToArray(intList));
  };
  ObjLoader.prototype.getColors = function () {
    var $receiver = this.points_0;
    var destination = Kotlin.kotlin.collections.ArrayList_init_za3lpa$();
    var tmp$;
    tmp$ = $receiver.iterator();
    while (tmp$.hasNext()) {
      var element = tmp$.next();
      var list = element.color.list();
      Kotlin.kotlin.collections.addAll_fwwv5a$(destination, list);
    }
    var colorsList = destination;
    return new Float32Array(Kotlin.kotlin.collections.copyToArray(colorsList));
  };
  ObjLoader.prototype.getNumFaces = function () {
    return this.faces_0.size;
  };
  function ObjLoader$Face(p1, p2, p3) {
    this.p1 = p1;
    this.p2 = p2;
    this.p3 = p3;
  }
  ObjLoader$Face.prototype.list = function () {
    return listOf([this.p1, this.p2, this.p3]);
  };
  ObjLoader$Face.$metadata$ = {
    type: Kotlin.TYPE.CLASS,
    classIndex: Kotlin.newClassIndex(),
    simpleName: 'Face',
    baseClasses: []
  };
  ObjLoader$Face.prototype.component1 = function () {
    return this.p1;
  };
  ObjLoader$Face.prototype.component2 = function () {
    return this.p2;
  };
  ObjLoader$Face.prototype.component3 = function () {
    return this.p3;
  };
  ObjLoader$Face.prototype.copy_i3nxhr$ = function (p1, p2, p3) {
    return new ObjLoader$ObjLoader$Face_init(p1 === void 0 ? this.p1 : p1, p2 === void 0 ? this.p2 : p2, p3 === void 0 ? this.p3 : p3);
  };
  ObjLoader$Face.prototype.toString = function () {
    return 'Face(p1=' + Kotlin.toString(this.p1) + (', p2=' + Kotlin.toString(this.p2)) + (', p3=' + Kotlin.toString(this.p3)) + ')';
  };
  ObjLoader$Face.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.p1) | 0;
    result = result * 31 + Kotlin.hashCode(this.p2) | 0;
    result = result * 31 + Kotlin.hashCode(this.p3) | 0;
    return result;
  };
  ObjLoader$Face.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && (Kotlin.equals(this.p1, other.p1) && Kotlin.equals(this.p2, other.p2) && Kotlin.equals(this.p3, other.p3)))));
  };
  function ObjLoader$Point(x, y, z, color) {
    this.x = x;
    this.y = y;
    this.z = z;
    this.color = color;
  }
  ObjLoader$Point.prototype.list = function () {
    return listOf([this.x, this.y, this.z]);
  };
  function ObjLoader$Point$Color(r, g, b, a) {
    ObjLoader$Point$Color$Companion_getInstance();
    this.r = r;
    this.g = g;
    this.b = b;
    this.a = a;
  }
  function ObjLoader$Point$Color$Companion() {
    ObjLoader$Point$Color$Companion_instance = this;
  }
  ObjLoader$Point$Color$Companion.prototype.randomColor = function () {
    return ObjLoader$Point$ObjLoader$Point$Color_init(Math.random(), Math.random(), Math.random(), 1.0);
  };
  ObjLoader$Point$Color$Companion.$metadata$ = {
    type: Kotlin.TYPE.OBJECT,
    classIndex: Kotlin.newClassIndex(),
    simpleName: 'Companion',
    baseClasses: []
  };
  var ObjLoader$Point$Color$Companion_instance = null;
  function ObjLoader$Point$Color$Companion_getInstance() {
    if (ObjLoader$Point$Color$Companion_instance === null) {
      ObjLoader$Point$Color$Companion_instance = new ObjLoader$Point$Color$Companion();
    }
    return ObjLoader$Point$Color$Companion_instance;
  }
  ObjLoader$Point$Color.prototype.list = function () {
    return listOf([this.r, this.g, this.b, this.a]);
  };
  ObjLoader$Point$Color.$metadata$ = {
    type: Kotlin.TYPE.CLASS,
    classIndex: Kotlin.newClassIndex(),
    simpleName: 'Color',
    baseClasses: []
  };
  ObjLoader$Point$Color.prototype.component1 = function () {
    return this.r;
  };
  ObjLoader$Point$Color.prototype.component2 = function () {
    return this.g;
  };
  ObjLoader$Point$Color.prototype.component3 = function () {
    return this.b;
  };
  ObjLoader$Point$Color.prototype.component4 = function () {
    return this.a;
  };
  ObjLoader$Point$Color.prototype.copy_7b5o5w$ = function (r, g, b, a) {
    return new ObjLoader$Point$ObjLoader$Point$Color_init(r === void 0 ? this.r : r, g === void 0 ? this.g : g, b === void 0 ? this.b : b, a === void 0 ? this.a : a);
  };
  ObjLoader$Point$Color.prototype.toString = function () {
    return 'Color(r=' + Kotlin.toString(this.r) + (', g=' + Kotlin.toString(this.g)) + (', b=' + Kotlin.toString(this.b)) + (', a=' + Kotlin.toString(this.a)) + ')';
  };
  ObjLoader$Point$Color.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.r) | 0;
    result = result * 31 + Kotlin.hashCode(this.g) | 0;
    result = result * 31 + Kotlin.hashCode(this.b) | 0;
    result = result * 31 + Kotlin.hashCode(this.a) | 0;
    return result;
  };
  ObjLoader$Point$Color.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && (Kotlin.equals(this.r, other.r) && Kotlin.equals(this.g, other.g) && Kotlin.equals(this.b, other.b) && Kotlin.equals(this.a, other.a)))));
  };
  ObjLoader$Point.$metadata$ = {
    type: Kotlin.TYPE.CLASS,
    classIndex: Kotlin.newClassIndex(),
    simpleName: 'Point',
    baseClasses: []
  };
  ObjLoader$Point.prototype.component1 = function () {
    return this.x;
  };
  ObjLoader$Point.prototype.component2 = function () {
    return this.y;
  };
  ObjLoader$Point.prototype.component3 = function () {
    return this.z;
  };
  ObjLoader$Point.prototype.component4 = function () {
    return this.color;
  };
  ObjLoader$Point.prototype.copy_msjxpg$ = function (x, y, z, color) {
    return new ObjLoader$ObjLoader$Point_init(x === void 0 ? this.x : x, y === void 0 ? this.y : y, z === void 0 ? this.z : z, color === void 0 ? this.color : color);
  };
  ObjLoader$Point.prototype.toString = function () {
    return 'Point(x=' + Kotlin.toString(this.x) + (', y=' + Kotlin.toString(this.y)) + (', z=' + Kotlin.toString(this.z)) + (', color=' + Kotlin.toString(this.color)) + ')';
  };
  ObjLoader$Point.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.x) | 0;
    result = result * 31 + Kotlin.hashCode(this.y) | 0;
    result = result * 31 + Kotlin.hashCode(this.z) | 0;
    result = result * 31 + Kotlin.hashCode(this.color) | 0;
    return result;
  };
  ObjLoader$Point.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && (Kotlin.equals(this.x, other.x) && Kotlin.equals(this.y, other.y) && Kotlin.equals(this.z, other.z) && Kotlin.equals(this.color, other.color)))));
  };
  ObjLoader.$metadata$ = {
    type: Kotlin.TYPE.CLASS,
    classIndex: Kotlin.newClassIndex(),
    simpleName: 'ObjLoader',
    baseClasses: []
  };
  function ResourceLoader(resourceLocations) {
    this.resourceMap_0 = HashMap_init();
    this.fetchParams_0 = {};
    var tmp$;
    this.fetchParams_0.method = 'GET';
    this.fetchParams_0.cache = 'no-store';
    this.fetchParams_0.mode = 'same-origin';
    for (tmp$ = 0; tmp$ !== resourceLocations.length; ++tmp$) {
      var loc = resourceLocations[tmp$];
      this.resourceMap_0.put_wn2jw4$(loc, new ResourceLoader$ResourceInfo());
      this.makeRequest_0(loc);
    }
  }
  function ResourceLoader$ResourceInfo() {
    this.loaded = false;
    this.value = null;
  }
  ResourceLoader$ResourceInfo.$metadata$ = {
    type: Kotlin.TYPE.CLASS,
    classIndex: Kotlin.newClassIndex(),
    simpleName: 'ResourceInfo',
    baseClasses: []
  };
  function ResourceLoader$makeRequest$lambda(response) {
    return response.text();
  }
  function ResourceLoader$makeRequest$lambda_0(this$ResourceLoader, closure$uri) {
    return function (text_0) {
      var tmp$, tmp$_0, tmp$_1;
      (tmp$ = this$ResourceLoader.resourceMap_0.get_za3rmp$(closure$uri)) != null ? (tmp$.loaded = true) : null;
      (tmp$_1 = this$ResourceLoader.resourceMap_0.get_za3rmp$(closure$uri)) != null ? (tmp$_1.value = typeof (tmp$_0 = text_0) === 'string' ? tmp$_0 : Kotlin.throwCCE()) : null;
      return null;
    };
  }
  function ResourceLoader$makeRequest$lambda_1(closure$uri, this$ResourceLoader) {
    return function (e) {
      Kotlin.println('Getting ' + closure$uri + ' failed with ' + e + '. Trying again');
      this$ResourceLoader.makeRequest_0(closure$uri);
    };
  }
  ResourceLoader.prototype.makeRequest_0 = function (uri) {
    window.fetch(uri, this.fetchParams_0).then(ResourceLoader$makeRequest$lambda).then(ResourceLoader$makeRequest$lambda_0(this, uri)).catch(ResourceLoader$makeRequest$lambda_1(uri, this));
  };
  ResourceLoader.prototype.get_61zpoe$ = function (name) {
    var tmp$;
    return (tmp$ = this.resourceMap_0.get_za3rmp$(name)) != null ? tmp$.value : null;
  };
  ResourceLoader.prototype.allLoaded = function () {
    var tmp$;
    tmp$ = this.resourceMap_0.entries.iterator();
    while (tmp$.hasNext()) {
      var element = tmp$.next();
      if (!element.value.loaded) {
        return false;
      }
    }
    return true;
  };
  ResourceLoader.$metadata$ = {
    type: Kotlin.TYPE.CLASS,
    classIndex: Kotlin.newClassIndex(),
    simpleName: 'ResourceLoader',
    baseClasses: []
  };
  function Mat3(array) {
    Mat3$Companion_getInstance();
    this.array = array;
  }
  function Mat3$Companion() {
    Mat3$Companion_instance = this;
  }
  Mat3$Companion.prototype.fromMat4_k68j1l$ = function (source) {
    var ret = Mat3_init();
    mat3.fromMat4(ret.array, source.array);
    return ret;
  };
  Mat3$Companion.$metadata$ = {
    type: Kotlin.TYPE.OBJECT,
    classIndex: Kotlin.newClassIndex(),
    simpleName: 'Companion',
    baseClasses: []
  };
  var Mat3$Companion_instance = null;
  function Mat3$Companion_getInstance() {
    if (Mat3$Companion_instance === null) {
      Mat3$Companion_instance = new Mat3$Companion();
    }
    return Mat3$Companion_instance;
  }
  Mat3.prototype.invert = function () {
    mat3.invert(this.array, this.array);
  };
  Mat3.prototype.transpose = function () {
    mat3.transpose(this.array, this.array);
  };
  Mat3.$metadata$ = {
    type: Kotlin.TYPE.CLASS,
    classIndex: Kotlin.newClassIndex(),
    simpleName: 'Mat3',
    baseClasses: []
  };
  function Mat4(array) {
    this.array = array;
  }
  Mat4.prototype.clone = function () {
    return new Mat4(this.array);
  };
  Mat4.prototype.identity = function () {
    mat4.identity(this.array);
  };
  Mat4.prototype.transpose = function () {
    mat4.transpose(this.array, this.array);
  };
  Mat4.prototype.invert = function () {
    mat4.invert(this.array, this.array);
  };
  Mat4.prototype.adjoint = function () {
    mat4.adjoint(this.array, this.array);
  };
  Mat4.prototype.determinant = function () {
    return mat4.determinant(this.array);
  };
  Mat4.prototype.translate_k6echg$ = function (v) {
    mat4.translate(this.array, this.array, v.array);
  };
  Mat4.prototype.rotateX_3p81yu$ = function (rad) {
    mat4.rotateX(this.array, this.array, Kotlin.numberToDouble(rad));
  };
  Mat4.prototype.rotateY_3p81yu$ = function (rad) {
    mat4.rotateY(this.array, this.array, Kotlin.numberToDouble(rad));
  };
  Mat4.prototype.rotateZ_3p81yu$ = function (rad) {
    mat4.rotateZ(this.array, this.array, Kotlin.numberToDouble(rad));
  };
  Mat4.prototype.scale_3p81yu$ = function (amount) {
    this.scale_k6echg$(Vec3_init(amount, amount, amount));
  };
  Mat4.prototype.scale_k6echg$ = function (v) {
    mat4.scale(this.array, this.array, v.array);
  };
  Mat4.prototype.lookAt_p3layc$ = function (eye, center, up) {
    mat4.lookAt(this.array, eye.array, center.array, up.array);
  };
  Mat4.prototype.perspective_1ugm5o$ = function (fovy, aspect, near, far) {
    mat4.perspective(this.array, Kotlin.numberToDouble(fovy), Kotlin.numberToDouble(aspect), Kotlin.numberToDouble(near), Kotlin.numberToDouble(far));
  };
  Mat4.prototype.multiply_k68j1l$ = function (other) {
    var ret = Mat4_init();
    mat4.multiply(ret.array, this.array, other.array);
    return ret;
  };
  Mat4.prototype.times_k68j1l$ = function (other) {
    return this.multiply_k68j1l$(other);
  };
  Mat4.$metadata$ = {
    type: Kotlin.TYPE.CLASS,
    classIndex: Kotlin.newClassIndex(),
    simpleName: 'Mat4',
    baseClasses: []
  };
  function Vec3(array) {
    this.array = array;
  }
  Vec3.prototype.cross_k6echg$ = function (other) {
    var ret = Vec3_init_0();
    vec3.cross(ret.array, this.array, other.array);
    return ret;
  };
  Vec3.prototype.normalize = function () {
    vec3.normalize(this.array, this.array);
  };
  Vec3.prototype.plus_k6echg$ = function (other) {
    return this.add_k6echg$(other);
  };
  Vec3.prototype.add_k6echg$ = function (other) {
    var ret = Vec3_init_0();
    vec3.add(ret.array, this.array, other.array);
    return ret;
  };
  Vec3.$metadata$ = {
    type: Kotlin.TYPE.CLASS,
    classIndex: Kotlin.newClassIndex(),
    simpleName: 'Vec3',
    baseClasses: []
  };
  _.WebGLWrapper = WebGLWrapper;
  _.main_kand9s$ = main;
  ObjLoader.Face_init_a2j3zq$ = ObjLoader$ObjLoader$Face_init;
  ObjLoader.Face = ObjLoader$Face;
  Object.defineProperty(ObjLoader$Point$Color, 'Companion', {
    get: ObjLoader$Point$Color$Companion_getInstance
  });
  ObjLoader$Point.Color_init_1ugm5o$ = ObjLoader$Point$ObjLoader$Point$Color_init;
  ObjLoader$Point.Color = ObjLoader$Point$Color;
  ObjLoader.Point_init_a2j3zq$ = ObjLoader$ObjLoader$Point_init;
  ObjLoader.Point = ObjLoader$Point;
  _.ObjLoader = ObjLoader;
  _.ResourceLoader = ResourceLoader;
  Object.defineProperty(Mat3, 'Companion', {
    get: Mat3$Companion_getInstance
  });
  var package$com = _.com || (_.com = {});
  var package$tanelso2 = package$com.tanelso2 || (package$com.tanelso2 = {});
  var package$glmatrix = package$tanelso2.glmatrix || (package$tanelso2.glmatrix = {});
  package$glmatrix.Mat3_init = Mat3_init;
  package$glmatrix.Mat3 = Mat3;
  package$glmatrix.Mat4_init = Mat4_init;
  package$glmatrix.Mat4_init_o5v4nz$ = Mat4_init_0;
  package$glmatrix.Mat4 = Mat4;
  package$glmatrix.Vec3_init_a2j3zq$ = Vec3_init;
  package$glmatrix.Vec3_init = Vec3_init_0;
  package$glmatrix.Vec3 = Vec3;
  function ObjLoader$ObjLoader$Face_init(p1, p2, p3, $this) {
    $this = $this || Object.create(ObjLoader$Face.prototype);
    ObjLoader$Face.call($this, Kotlin.numberToShort(p1), Kotlin.numberToShort(p2), Kotlin.numberToShort(p3));
    return $this;
  }
  function ObjLoader$Point$ObjLoader$Point$Color_init(r, g, b, a, $this) {
    $this = $this || Object.create(ObjLoader$Point$Color.prototype);
    ObjLoader$Point$Color.call($this, Kotlin.numberToDouble(r), Kotlin.numberToDouble(g), Kotlin.numberToDouble(b), Kotlin.numberToDouble(a));
    return $this;
  }
  function ObjLoader$ObjLoader$Point_init(x, y, z, $this) {
    $this = $this || Object.create(ObjLoader$Point.prototype);
    ObjLoader$Point.call($this, Kotlin.numberToDouble(x), Kotlin.numberToDouble(y), Kotlin.numberToDouble(z), ObjLoader$Point$Color$Companion_getInstance().randomColor());
    return $this;
  }
  function Mat3_init($this) {
    $this = $this || Object.create(Mat3.prototype);
    Mat3.call($this, mat3.create());
    return $this;
  }
  function Mat4_init($this) {
    $this = $this || Object.create(Mat4.prototype);
    Mat4.call($this, new Float32Array(mat4.create()));
    return $this;
  }
  function Mat4_init_0(a, $this) {
    $this = $this || Object.create(Mat4.prototype);
    Mat4.call($this, new Float32Array(a));
    return $this;
  }
  function Vec3_init(x, y, z, $this) {
    $this = $this || Object.create(Vec3.prototype);
    Vec3.call($this, vec3.fromValues(Kotlin.numberToDouble(x), Kotlin.numberToDouble(y), Kotlin.numberToDouble(z)));
    return $this;
  }
  function Vec3_init_0($this) {
    $this = $this || Object.create(Vec3.prototype);
    Vec3.call($this, vec3.create());
    return $this;
  }
  Kotlin.defineModule('WebGL', _);
  main([]);
  return _;
}(typeof WebGL === 'undefined' ? {} : WebGL, kotlin);

//@ sourceMappingURL=WebGL.js.map
