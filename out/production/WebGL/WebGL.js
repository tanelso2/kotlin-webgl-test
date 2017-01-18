if (typeof kotlin === 'undefined') {
  throw new Error("Error loading module 'WebGL'. Its dependency 'kotlin' was not found. Please, check whether 'kotlin' is loaded prior to 'WebGL'.");
}
var WebGL = function (_, Kotlin) {
  'use strict';
  var IllegalStateException = Kotlin.kotlin.IllegalStateException;
  var listOf = Kotlin.kotlin.collections.listOf_9mqe4v$;
  var HashMap_init = Kotlin.kotlin.collections.HashMap_init;
  function insertElem() {
    var tmp$, tmp$_0;
    Kotlin.println('Inside insertElem()');
    tmp$_0 = Kotlin.isType(tmp$ = document.getElementById('kotlinhook'), HTMLElement) ? tmp$ : null;
    if (tmp$_0 == null) {
      throw new IllegalStateException('kotlinhook element not present');
    }
    var hookElem = tmp$_0;
    var node = document.createElement('h1');
    node.textContent = 'Hello again!';
    hookElem.appendChild(node);
  }
  function WebGLWrapper() {
    var tmp$, tmp$_0, tmp$_1;
    this.canvas = Kotlin.isType(tmp$ = document.getElementById('webglCanvas'), HTMLCanvasElement) ? tmp$ : Kotlin.throwCCE();
    this.webgl = Kotlin.isType(tmp$_0 = this.canvas.getContext('webgl'), WebGLRenderingContext) ? tmp$_0 : Kotlin.throwCCE();
    tmp$_1 = this.webgl.createProgram();
    if (tmp$_1 == null) {
      throw new IllegalStateException('Could not initialize shader program');
    }
    this.shaderProgram = tmp$_1;
    this.windowWidth = 800;
    this.windowHeight = 600;
    this.p = listOf([Point_init(-0.8, 0.8, 0.0), Point_init(-0.2, 0.8, 0.0), Point_init(0.2, 0.8, 0.0), Point_init(0.8, 0.8, 0.0), Point_init(-0.8, 0.6, 0.0), Point_init(-0.2, 0.6, 0.0), Point_init(0.2, 0.6, 0.0), Point_init(0.8, 0.6, 0.0), Point_init(-0.8, -0.6, 0.0), Point_init(-0.2, -0.6, 0.0), Point_init(0.2, -0.6, 0.0), Point_init(0.8, -0.6, 0.0), Point_init(-0.8, -0.8, 0.0), Point_init(-0.2, -0.8, 0.0), Point_init(0.2, -0.8, 0.0), Point_init(0.8, -0.8, 0.0)]);
    this.trianglesArray = listOf([this.p.get_za3lpa$(0), this.p.get_za3lpa$(4), this.p.get_za3lpa$(1), this.p.get_za3lpa$(1), this.p.get_za3lpa$(4), this.p.get_za3lpa$(5), this.p.get_za3lpa$(1), this.p.get_za3lpa$(5), this.p.get_za3lpa$(2), this.p.get_za3lpa$(2), this.p.get_za3lpa$(5), this.p.get_za3lpa$(6), this.p.get_za3lpa$(2), this.p.get_za3lpa$(6), this.p.get_za3lpa$(3), this.p.get_za3lpa$(3), this.p.get_za3lpa$(6), this.p.get_za3lpa$(7), this.p.get_za3lpa$(5), this.p.get_za3lpa$(9), this.p.get_za3lpa$(6), this.p.get_za3lpa$(6), this.p.get_za3lpa$(9), this.p.get_za3lpa$(10), this.p.get_za3lpa$(8), this.p.get_za3lpa$(12), this.p.get_za3lpa$(9), this.p.get_za3lpa$(9), this.p.get_za3lpa$(12), this.p.get_za3lpa$(13), this.p.get_za3lpa$(9), this.p.get_za3lpa$(13), this.p.get_za3lpa$(10), this.p.get_za3lpa$(10), this.p.get_za3lpa$(13), this.p.get_za3lpa$(14), this.p.get_za3lpa$(10), this.p.get_za3lpa$(14), this.p.get_za3lpa$(11), this.p.get_za3lpa$(11), this.p.get_za3lpa$(14), this.p.get_za3lpa$(15)]);
    var $receiver = this.trianglesArray;
    var destination = Kotlin.kotlin.collections.ArrayList_init_za3lpa$();
    var tmp$_2;
    tmp$_2 = $receiver.iterator();
    while (tmp$_2.hasNext()) {
      var element = tmp$_2.next();
      var list = listOf([element.x, element.y, element.z]);
      Kotlin.kotlin.collections.addAll_fwwv5a$(destination, list);
    }
    this.vertexList = destination;
    var $receiver_0 = this.trianglesArray;
    var destination_0 = Kotlin.kotlin.collections.ArrayList_init_za3lpa$();
    var tmp$_3;
    tmp$_3 = $receiver_0.iterator();
    while (tmp$_3.hasNext()) {
      var element_0 = tmp$_3.next();
      var list_0 = element_0.color.list();
      Kotlin.kotlin.collections.addAll_fwwv5a$(destination_0, list_0);
    }
    this.colorList = destination_0;
    this.resourceList = ['frag-shader.txt', 'vertex-shader.txt'];
    this.resourceLoader = new ResourceLoader(this.resourceList.slice());
    this.rotZ = 2 * Math.PI;
    this.transZ = 2 * Math.PI;
  }
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
    var tmp$, tmp$_0;
    if (this.resourceLoader.allLoaded()) {
      var vertexShaderSource_0 = (tmp$ = this.resourceLoader.get_61zpoe$('vertex-shader.txt')) != null ? tmp$ : '';
      var vertexShader = this.getVertexShader_61zpoe$(vertexShaderSource_0);
      var fragmentShaderSource_0 = (tmp$_0 = this.resourceLoader.get_61zpoe$('frag-shader.txt')) != null ? tmp$_0 : '';
      var fragmentShader = this.getFragmentShader_61zpoe$(fragmentShaderSource_0);
      this.webgl.attachShader(this.shaderProgram, vertexShader);
      this.webgl.attachShader(this.shaderProgram, fragmentShader);
      this.webgl.linkProgram(this.shaderProgram);
      this.webgl.useProgram(this.shaderProgram);
      var vertexPositionAttribute = this.webgl.getAttribLocation(this.shaderProgram, 'aVertexPosition');
      this.webgl.enableVertexAttribArray(vertexPositionAttribute);
      var vertexPositionBuffer = this.webgl.createBuffer();
      this.webgl.bindBuffer(WebGLRenderingContext.ARRAY_BUFFER, vertexPositionBuffer);
      var $receiver = this.vertexList;
      var vertexArray = Kotlin.kotlin.collections.copyToArray($receiver);
      this.webgl.bufferData(WebGLRenderingContext.ARRAY_BUFFER, new Float32Array(vertexArray), WebGLRenderingContext.STATIC_DRAW);
      this.webgl.vertexAttribPointer(vertexPositionAttribute, 3, WebGLRenderingContext.FLOAT, false, 0, 0);
      var vertexColorAttribute = this.webgl.getAttribLocation(this.shaderProgram, 'aVertexColor');
      this.webgl.enableVertexAttribArray(vertexColorAttribute);
      var vertexColorBuffer = this.webgl.createBuffer();
      this.webgl.bindBuffer(WebGLRenderingContext.ARRAY_BUFFER, vertexColorBuffer);
      var $receiver_0 = this.colorList;
      var colorArray = Kotlin.kotlin.collections.copyToArray($receiver_0);
      this.webgl.bufferData(WebGLRenderingContext.ARRAY_BUFFER, new Float32Array(colorArray), WebGLRenderingContext.STATIC_DRAW);
      this.webgl.vertexAttribPointer(vertexColorAttribute, 4, WebGLRenderingContext.FLOAT, false, 0, 0);
      window.requestAnimationFrame(WebGLWrapper$setup$lambda(this));
    }
     else {
      window.requestAnimationFrame(WebGLWrapper$setup$lambda_0(this));
    }
  };
  WebGLWrapper.prototype.getFragmentShader_61zpoe$ = function (source) {
    return this.getShader_0(source, WebGLRenderingContext.FRAGMENT_SHADER);
  };
  WebGLWrapper.prototype.getVertexShader_61zpoe$ = function (source) {
    return this.getShader_0(source, WebGLRenderingContext.VERTEX_SHADER);
  };
  WebGLWrapper.prototype.getShader_0 = function (shaderSource, shaderType) {
    var shader = this.webgl.createShader(shaderType);
    this.webgl.shaderSource(shader, shaderSource);
    this.webgl.compileShader(shader);
    Kotlin.println(this.webgl.getShaderInfoLog(shader));
    if (shader == null) {
      throw new IllegalStateException('Shader is null!');
    }
    return shader;
  };
  WebGLWrapper.prototype.draw = function () {
    this.rotZ -= 0.02;
    this.transZ += 0.02;
    var transX = 0.0;
    var transY = 0.0;
    var transZ = Math.sin(this.transZ);
    var mvMatrix = Mat4_init();
    mvMatrix.translate_k6echg$(Vec3_init(transX, transY, transZ));
    mvMatrix.scale_k6echg$(Vec3_init(1.0, 1.0, 1.0));
    mvMatrix.rotateX_mx4ult$(0.0);
    mvMatrix.rotateY_mx4ult$(0.0);
    mvMatrix.rotateZ_mx4ult$(this.rotZ);
    var translateMatrix = Mat4_init();
    translateMatrix.translate_k6echg$(Vec3_init(0.0, 0.0, -1.5));
    var perspectiveMatrix = Mat4_init();
    perspectiveMatrix.perspective_7b5o5w$(1.0, Math.PI / 2, 0.5, 100.0);
    var finalMatrix = perspectiveMatrix.multiply_k68j1l$(translateMatrix).multiply_k68j1l$(mvMatrix);
    var mvMatrixUniform = this.webgl.getUniformLocation(this.shaderProgram, 'uMVMatrix');
    this.webgl.uniformMatrix4fv(mvMatrixUniform, false, finalMatrix.array);
    this.webgl.drawArrays(WebGLRenderingContext.TRIANGLES, 0, this.trianglesArray.size);
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
  WebGLWrapper.$metadata$ = {
    type: Kotlin.TYPE.CLASS,
    classIndex: Kotlin.newClassIndex(),
    simpleName: 'WebGLWrapper',
    baseClasses: []
  };
  function Point(x, y, z, color) {
    this.x = x;
    this.y = y;
    this.z = z;
    this.color = color;
  }
  function Point$Color(r, g, b, a) {
    Point$Color$Companion_getInstance();
    this.r = r;
    this.g = g;
    this.b = b;
    this.a = a;
  }
  function Point$Color$Companion() {
    Point$Color$Companion_instance = this;
  }
  Point$Color$Companion.prototype.randomColor = function () {
    return new Point$Color(Math.random(), Math.random(), Math.random(), 1.0);
  };
  Point$Color$Companion.$metadata$ = {
    type: Kotlin.TYPE.OBJECT,
    classIndex: Kotlin.newClassIndex(),
    simpleName: 'Companion',
    baseClasses: []
  };
  var Point$Color$Companion_instance = null;
  function Point$Color$Companion_getInstance() {
    if (Point$Color$Companion_instance === null) {
      Point$Color$Companion_instance = new Point$Color$Companion();
    }
    return Point$Color$Companion_instance;
  }
  Point$Color.prototype.list = function () {
    return listOf([this.r, this.g, this.b, this.a]);
  };
  Point$Color.$metadata$ = {
    type: Kotlin.TYPE.CLASS,
    classIndex: Kotlin.newClassIndex(),
    simpleName: 'Color',
    baseClasses: []
  };
  Point$Color.prototype.component1 = function () {
    return this.r;
  };
  Point$Color.prototype.component2 = function () {
    return this.g;
  };
  Point$Color.prototype.component3 = function () {
    return this.b;
  };
  Point$Color.prototype.component4 = function () {
    return this.a;
  };
  Point$Color.prototype.copy_7b5o5w$ = function (r, g, b, a) {
    return new Point$Color(r === void 0 ? this.r : r, g === void 0 ? this.g : g, b === void 0 ? this.b : b, a === void 0 ? this.a : a);
  };
  Point$Color.prototype.toString = function () {
    return 'Color(r=' + Kotlin.toString(this.r) + (', g=' + Kotlin.toString(this.g)) + (', b=' + Kotlin.toString(this.b)) + (', a=' + Kotlin.toString(this.a)) + ')';
  };
  Point$Color.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.r) | 0;
    result = result * 31 + Kotlin.hashCode(this.g) | 0;
    result = result * 31 + Kotlin.hashCode(this.b) | 0;
    result = result * 31 + Kotlin.hashCode(this.a) | 0;
    return result;
  };
  Point$Color.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && (Kotlin.equals(this.r, other.r) && Kotlin.equals(this.g, other.g) && Kotlin.equals(this.b, other.b) && Kotlin.equals(this.a, other.a)))));
  };
  Point.$metadata$ = {
    type: Kotlin.TYPE.CLASS,
    classIndex: Kotlin.newClassIndex(),
    simpleName: 'Point',
    baseClasses: []
  };
  Point.prototype.component1 = function () {
    return this.x;
  };
  Point.prototype.component2 = function () {
    return this.y;
  };
  Point.prototype.component3 = function () {
    return this.z;
  };
  Point.prototype.component4 = function () {
    return this.color;
  };
  Point.prototype.copy_kb9oe0$ = function (x, y, z, color) {
    return new Point_init_0(x === void 0 ? this.x : x, y === void 0 ? this.y : y, z === void 0 ? this.z : z, color === void 0 ? this.color : color);
  };
  Point.prototype.toString = function () {
    return 'Point(x=' + Kotlin.toString(this.x) + (', y=' + Kotlin.toString(this.y)) + (', z=' + Kotlin.toString(this.z)) + (', color=' + Kotlin.toString(this.color)) + ')';
  };
  Point.prototype.hashCode = function () {
    var result = 0;
    result = result * 31 + Kotlin.hashCode(this.x) | 0;
    result = result * 31 + Kotlin.hashCode(this.y) | 0;
    result = result * 31 + Kotlin.hashCode(this.z) | 0;
    result = result * 31 + Kotlin.hashCode(this.color) | 0;
    return result;
  };
  Point.prototype.equals = function (other) {
    return this === other || (other !== null && (typeof other === 'object' && (Object.getPrototypeOf(this) === Object.getPrototypeOf(other) && (Kotlin.equals(this.x, other.x) && Kotlin.equals(this.y, other.y) && Kotlin.equals(this.z, other.z) && Kotlin.equals(this.color, other.color)))));
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
    return function (text) {
      var tmp$, tmp$_0, tmp$_1;
      (tmp$ = this$ResourceLoader.resourceMap_0.get_za3rmp$(closure$uri)) != null ? (tmp$.loaded = true) : null;
      (tmp$_1 = this$ResourceLoader.resourceMap_0.get_za3rmp$(closure$uri)) != null ? (tmp$_1.value = typeof (tmp$_0 = text) === 'string' ? tmp$_0 : Kotlin.throwCCE()) : null;
      return null;
    };
  }
  ResourceLoader.prototype.makeRequest_0 = function (uri) {
    window.fetch(uri, this.fetchParams_0).then(ResourceLoader$makeRequest$lambda).then(ResourceLoader$makeRequest$lambda_0(this, uri));
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
  var vertexShaderSource;
  var fragmentShaderSource;
  function getFragmentShader(webgl) {
    return getShader(webgl, fragmentShaderSource, WebGLRenderingContext.FRAGMENT_SHADER);
  }
  function getVertexShader(webgl) {
    return getShader(webgl, vertexShaderSource, WebGLRenderingContext.VERTEX_SHADER);
  }
  function getShader(webgl, shaderSource, shaderType) {
    var shader = webgl.createShader(shaderType);
    webgl.shaderSource(shader, shaderSource);
    webgl.compileShader(shader);
    if (shader == null) {
      throw new IllegalStateException('Shader is null!');
    }
    return shader;
  }
  function Mat4(array) {
    this.array = array;
  }
  Mat4.prototype.clone = function () {
    return new Mat4(this.array);
  };
  Mat4.prototype.identity = function () {
    return mat4.identity(this.array);
  };
  Mat4.prototype.transpose = function () {
    return mat4.transpose(this.array, this.array);
  };
  Mat4.prototype.invert = function () {
    return mat4.invert(this.array, this.array);
  };
  Mat4.prototype.adjoint = function () {
    return mat4.adjoint(this.array, this.array);
  };
  Mat4.prototype.determinant = function () {
    return mat4.determinant(this.array);
  };
  Mat4.prototype.translate_k6echg$ = function (v) {
    return mat4.translate(this.array, this.array, v.array);
  };
  Mat4.prototype.rotateX_mx4ult$ = function (rad) {
    return mat4.rotateX(this.array, this.array, rad);
  };
  Mat4.prototype.rotateY_mx4ult$ = function (rad) {
    return mat4.rotateY(this.array, this.array, rad);
  };
  Mat4.prototype.rotateZ_mx4ult$ = function (rad) {
    return mat4.rotateZ(this.array, this.array, rad);
  };
  Mat4.prototype.scale_k6echg$ = function (v) {
    return mat4.scale(this.array, this.array, v.array);
  };
  Mat4.prototype.lookAt_p3layc$ = function (eye, center, up) {
    return mat4.lookAt(this.array, eye.array, center.array, up.array);
  };
  Mat4.prototype.perspective_7b5o5w$ = function (fovy, aspect, near, far) {
    return mat4.perspective(this.array, fovy, aspect, near, far);
  };
  Mat4.prototype.multiply_k68j1l$ = function (other) {
    var ret = Mat4_init();
    mat4.multiply(ret.array, this.array, other.array);
    return ret;
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
  Vec3.$metadata$ = {
    type: Kotlin.TYPE.CLASS,
    classIndex: Kotlin.newClassIndex(),
    simpleName: 'Vec3',
    baseClasses: []
  };
  _.insertElem = insertElem;
  _.WebGLWrapper = WebGLWrapper;
  Object.defineProperty(Point$Color, 'Companion', {
    get: Point$Color$Companion_getInstance
  });
  Point.Color = Point$Color;
  _.Point_init_y2kzbl$ = Point_init_0;
  _.Point_init_qt1dr2$ = Point_init_1;
  _.Point_init_yvo9jy$ = Point_init;
  _.Point = Point;
  _.main_kand9s$ = main;
  _.ResourceLoader = ResourceLoader;
  _.getFragmentShader_tnk6ih$ = getFragmentShader;
  _.getVertexShader_tnk6ih$ = getVertexShader;
  var package$com = _.com || (_.com = {});
  var package$tanelso2 = package$com.tanelso2 || (package$com.tanelso2 = {});
  var package$glmatrix = package$tanelso2.glmatrix || (package$tanelso2.glmatrix = {});
  package$glmatrix.Mat4_init = Mat4_init;
  package$glmatrix.Mat4_init_o5v4nz$ = Mat4_init_0;
  package$glmatrix.Mat4 = Mat4;
  package$glmatrix.Vec3_init_y2kzbl$ = Vec3_init;
  package$glmatrix.Vec3 = Vec3;
  function Point_init_0(x, y, z, $this) {
    $this = $this || Object.create(Point.prototype);
    Point.call($this, x, y, z, Point$Color$Companion_getInstance().randomColor());
    return $this;
  }
  function Point_init_1(x, y, z, $this) {
    $this = $this || Object.create(Point.prototype);
    Point_init_0(x, y, z, $this);
    return $this;
  }
  function Point_init(x, y, z, $this) {
    $this = $this || Object.create(Point.prototype);
    Point_init_0(x, y, z, $this);
    return $this;
  }
  vertexShaderSource = '\n   attribute vec3 aVertexPosition;\n   attribute vec4 aVertexColor;\n\n    uniform mat4 uMVMatrix;\n    varying vec4 vColor;\n\n    void main(void) {\n        gl_Position = uMVMatrix*vec4(aVertexPosition, 1.0);\n        vColor = aVertexColor;\n    }\n';
  fragmentShaderSource = '\n    precision mediump float;\n    varying vec4 vColor;\n    void main(void) {\n            gl_FragColor = vColor;\n     }\n';
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
    Vec3.call($this, vec3.fromValues(x, y, z));
    return $this;
  }
  Kotlin.defineModule('WebGL', _);
  main([]);
  return _;
}(typeof WebGL === 'undefined' ? {} : WebGL, kotlin);
