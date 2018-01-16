// Compiled by ClojureScript 1.9.542 {}
goog.provide('cljs.spec.gen.alpha');
goog.require('cljs.core');
goog.require('cljs.core');

/**
* @constructor
 * @implements {cljs.core.IDeref}
*/
cljs.spec.gen.alpha.LazyVar = (function (f,cached){
this.f = f;
this.cached = cached;
this.cljs$lang$protocol_mask$partition0$ = 32768;
this.cljs$lang$protocol_mask$partition1$ = 0;
})
cljs.spec.gen.alpha.LazyVar.prototype.cljs$core$IDeref$_deref$arity$1 = (function (this$){
var self__ = this;
var this$__$1 = this;
if(!((self__.cached == null))){
return self__.cached;
} else {
var x = self__.f.call(null);
if((x == null)){
} else {
self__.cached = x;
}

return x;
}
});

cljs.spec.gen.alpha.LazyVar.getBasis = (function (){
return new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Symbol(null,"f","f",43394975,null),cljs.core.with_meta(new cljs.core.Symbol(null,"cached","cached",-1216707864,null),new cljs.core.PersistentArrayMap(null, 1, [new cljs.core.Keyword(null,"mutable","mutable",875778266),true], null))], null);
});

cljs.spec.gen.alpha.LazyVar.cljs$lang$type = true;

cljs.spec.gen.alpha.LazyVar.cljs$lang$ctorStr = "cljs.spec.gen.alpha/LazyVar";

cljs.spec.gen.alpha.LazyVar.cljs$lang$ctorPrWriter = (function (this__8154__auto__,writer__8155__auto__,opt__8156__auto__){
return cljs.core._write.call(null,writer__8155__auto__,"cljs.spec.gen.alpha/LazyVar");
});

cljs.spec.gen.alpha.__GT_LazyVar = (function cljs$spec$gen$alpha$__GT_LazyVar(f,cached){
return (new cljs.spec.gen.alpha.LazyVar(f,cached));
});

cljs.spec.gen.alpha.quick_check_ref = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check.quick_check !== 'undefined')){
return clojure.test.check.quick_check;
} else {
throw (new Error([cljs.core.str.cljs$core$IFn$_invoke$arity$1("Var "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check","quick-check","clojure.test.check/quick-check",-810344251,null)),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" does not exist, "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check","quick-check","clojure.test.check/quick-check",-810344251,null))),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" never required")].join('')));
}
}),null));
cljs.spec.gen.alpha.quick_check = (function cljs$spec$gen$alpha$quick_check(var_args){
var args__8663__auto__ = [];
var len__8656__auto___13276 = arguments.length;
var i__8657__auto___13277 = (0);
while(true){
if((i__8657__auto___13277 < len__8656__auto___13276)){
args__8663__auto__.push((arguments[i__8657__auto___13277]));

var G__13278 = (i__8657__auto___13277 + (1));
i__8657__auto___13277 = G__13278;
continue;
} else {
}
break;
}

var argseq__8664__auto__ = ((((0) < args__8663__auto__.length))?(new cljs.core.IndexedSeq(args__8663__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.quick_check.cljs$core$IFn$_invoke$arity$variadic(argseq__8664__auto__);
});

cljs.spec.gen.alpha.quick_check.cljs$core$IFn$_invoke$arity$variadic = (function (args){
return cljs.core.apply.call(null,cljs.core.deref.call(null,cljs.spec.gen.alpha.quick_check_ref),args);
});

cljs.spec.gen.alpha.quick_check.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.quick_check.cljs$lang$applyTo = (function (seq13275){
return cljs.spec.gen.alpha.quick_check.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq13275));
});

cljs.spec.gen.alpha.for_all_STAR__ref = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.properties.for_all_STAR_ !== 'undefined')){
return clojure.test.check.properties.for_all_STAR_;
} else {
throw (new Error([cljs.core.str.cljs$core$IFn$_invoke$arity$1("Var "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.properties","for-all*","clojure.test.check.properties/for-all*",67088845,null)),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" does not exist, "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.properties","for-all*","clojure.test.check.properties/for-all*",67088845,null))),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" never required")].join('')));
}
}),null));
/**
 * Dynamically loaded clojure.test.check.properties/for-all*.
 */
cljs.spec.gen.alpha.for_all_STAR_ = (function cljs$spec$gen$alpha$for_all_STAR_(var_args){
var args__8663__auto__ = [];
var len__8656__auto___13280 = arguments.length;
var i__8657__auto___13281 = (0);
while(true){
if((i__8657__auto___13281 < len__8656__auto___13280)){
args__8663__auto__.push((arguments[i__8657__auto___13281]));

var G__13282 = (i__8657__auto___13281 + (1));
i__8657__auto___13281 = G__13282;
continue;
} else {
}
break;
}

var argseq__8664__auto__ = ((((0) < args__8663__auto__.length))?(new cljs.core.IndexedSeq(args__8663__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.for_all_STAR_.cljs$core$IFn$_invoke$arity$variadic(argseq__8664__auto__);
});

cljs.spec.gen.alpha.for_all_STAR_.cljs$core$IFn$_invoke$arity$variadic = (function (args){
return cljs.core.apply.call(null,cljs.core.deref.call(null,cljs.spec.gen.alpha.for_all_STAR__ref),args);
});

cljs.spec.gen.alpha.for_all_STAR_.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.for_all_STAR_.cljs$lang$applyTo = (function (seq13279){
return cljs.spec.gen.alpha.for_all_STAR_.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq13279));
});

var g_QMARK__13283 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.generator_QMARK_ !== 'undefined')){
return clojure.test.check.generators.generator_QMARK_;
} else {
throw (new Error([cljs.core.str.cljs$core$IFn$_invoke$arity$1("Var "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","generator?","clojure.test.check.generators/generator?",-1378210460,null)),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" does not exist, "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","generator?","clojure.test.check.generators/generator?",-1378210460,null))),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" never required")].join('')));
}
}),null));
var g_13284 = (new cljs.spec.gen.alpha.LazyVar(((function (g_QMARK__13283){
return (function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.generate !== 'undefined')){
return clojure.test.check.generators.generate;
} else {
throw (new Error([cljs.core.str.cljs$core$IFn$_invoke$arity$1("Var "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","generate","clojure.test.check.generators/generate",-690390711,null)),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" does not exist, "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","generate","clojure.test.check.generators/generate",-690390711,null))),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" never required")].join('')));
}
});})(g_QMARK__13283))
,null));
var mkg_13285 = (new cljs.spec.gen.alpha.LazyVar(((function (g_QMARK__13283,g_13284){
return (function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.__GT_Generator !== 'undefined')){
return clojure.test.check.generators.__GT_Generator;
} else {
throw (new Error([cljs.core.str.cljs$core$IFn$_invoke$arity$1("Var "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","->Generator","clojure.test.check.generators/->Generator",-1179475051,null)),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" does not exist, "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","->Generator","clojure.test.check.generators/->Generator",-1179475051,null))),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" never required")].join('')));
}
});})(g_QMARK__13283,g_13284))
,null));
cljs.spec.gen.alpha.generator_QMARK_ = ((function (g_QMARK__13283,g_13284,mkg_13285){
return (function cljs$spec$gen$alpha$generator_QMARK_(x){
return cljs.core.deref.call(null,g_QMARK__13283).call(null,x);
});})(g_QMARK__13283,g_13284,mkg_13285))
;

cljs.spec.gen.alpha.generator = ((function (g_QMARK__13283,g_13284,mkg_13285){
return (function cljs$spec$gen$alpha$generator(gfn){
return cljs.core.deref.call(null,mkg_13285).call(null,gfn);
});})(g_QMARK__13283,g_13284,mkg_13285))
;

/**
 * Generate a single value using generator.
 */
cljs.spec.gen.alpha.generate = ((function (g_QMARK__13283,g_13284,mkg_13285){
return (function cljs$spec$gen$alpha$generate(generator){
return cljs.core.deref.call(null,g_13284).call(null,generator);
});})(g_QMARK__13283,g_13284,mkg_13285))
;
cljs.spec.gen.alpha.delay_impl = (function cljs$spec$gen$alpha$delay_impl(gfnd){
return cljs.spec.gen.alpha.generator.call(null,(function (rnd,size){
return new cljs.core.Keyword(null,"gen","gen",142575302).cljs$core$IFn$_invoke$arity$1(cljs.core.deref.call(null,gfnd)).call(null,rnd,size);
}));
});
var g__13247__auto___13305 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.hash_map !== 'undefined')){
return clojure.test.check.generators.hash_map;
} else {
throw (new Error([cljs.core.str.cljs$core$IFn$_invoke$arity$1("Var "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","hash-map","clojure.test.check.generators/hash-map",1961346626,null)),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" does not exist, "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","hash-map","clojure.test.check.generators/hash-map",1961346626,null))),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" never required")].join('')));
}
}),null));
/**
 * Lazy loaded version of clojure.test.check.generators/hash-map
 */
cljs.spec.gen.alpha.hash_map = ((function (g__13247__auto___13305){
return (function cljs$spec$gen$alpha$hash_map(var_args){
var args__8663__auto__ = [];
var len__8656__auto___13306 = arguments.length;
var i__8657__auto___13307 = (0);
while(true){
if((i__8657__auto___13307 < len__8656__auto___13306)){
args__8663__auto__.push((arguments[i__8657__auto___13307]));

var G__13308 = (i__8657__auto___13307 + (1));
i__8657__auto___13307 = G__13308;
continue;
} else {
}
break;
}

var argseq__8664__auto__ = ((((0) < args__8663__auto__.length))?(new cljs.core.IndexedSeq(args__8663__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.hash_map.cljs$core$IFn$_invoke$arity$variadic(argseq__8664__auto__);
});})(g__13247__auto___13305))
;

cljs.spec.gen.alpha.hash_map.cljs$core$IFn$_invoke$arity$variadic = ((function (g__13247__auto___13305){
return (function (args){
return cljs.core.apply.call(null,cljs.core.deref.call(null,g__13247__auto___13305),args);
});})(g__13247__auto___13305))
;

cljs.spec.gen.alpha.hash_map.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.hash_map.cljs$lang$applyTo = ((function (g__13247__auto___13305){
return (function (seq13286){
return cljs.spec.gen.alpha.hash_map.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq13286));
});})(g__13247__auto___13305))
;


var g__13247__auto___13309 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.list !== 'undefined')){
return clojure.test.check.generators.list;
} else {
throw (new Error([cljs.core.str.cljs$core$IFn$_invoke$arity$1("Var "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","list","clojure.test.check.generators/list",506971058,null)),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" does not exist, "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","list","clojure.test.check.generators/list",506971058,null))),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" never required")].join('')));
}
}),null));
/**
 * Lazy loaded version of clojure.test.check.generators/list
 */
cljs.spec.gen.alpha.list = ((function (g__13247__auto___13309){
return (function cljs$spec$gen$alpha$list(var_args){
var args__8663__auto__ = [];
var len__8656__auto___13310 = arguments.length;
var i__8657__auto___13311 = (0);
while(true){
if((i__8657__auto___13311 < len__8656__auto___13310)){
args__8663__auto__.push((arguments[i__8657__auto___13311]));

var G__13312 = (i__8657__auto___13311 + (1));
i__8657__auto___13311 = G__13312;
continue;
} else {
}
break;
}

var argseq__8664__auto__ = ((((0) < args__8663__auto__.length))?(new cljs.core.IndexedSeq(args__8663__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.list.cljs$core$IFn$_invoke$arity$variadic(argseq__8664__auto__);
});})(g__13247__auto___13309))
;

cljs.spec.gen.alpha.list.cljs$core$IFn$_invoke$arity$variadic = ((function (g__13247__auto___13309){
return (function (args){
return cljs.core.apply.call(null,cljs.core.deref.call(null,g__13247__auto___13309),args);
});})(g__13247__auto___13309))
;

cljs.spec.gen.alpha.list.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.list.cljs$lang$applyTo = ((function (g__13247__auto___13309){
return (function (seq13287){
return cljs.spec.gen.alpha.list.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq13287));
});})(g__13247__auto___13309))
;


var g__13247__auto___13313 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.map !== 'undefined')){
return clojure.test.check.generators.map;
} else {
throw (new Error([cljs.core.str.cljs$core$IFn$_invoke$arity$1("Var "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","map","clojure.test.check.generators/map",45738796,null)),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" does not exist, "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","map","clojure.test.check.generators/map",45738796,null))),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" never required")].join('')));
}
}),null));
/**
 * Lazy loaded version of clojure.test.check.generators/map
 */
cljs.spec.gen.alpha.map = ((function (g__13247__auto___13313){
return (function cljs$spec$gen$alpha$map(var_args){
var args__8663__auto__ = [];
var len__8656__auto___13314 = arguments.length;
var i__8657__auto___13315 = (0);
while(true){
if((i__8657__auto___13315 < len__8656__auto___13314)){
args__8663__auto__.push((arguments[i__8657__auto___13315]));

var G__13316 = (i__8657__auto___13315 + (1));
i__8657__auto___13315 = G__13316;
continue;
} else {
}
break;
}

var argseq__8664__auto__ = ((((0) < args__8663__auto__.length))?(new cljs.core.IndexedSeq(args__8663__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.map.cljs$core$IFn$_invoke$arity$variadic(argseq__8664__auto__);
});})(g__13247__auto___13313))
;

cljs.spec.gen.alpha.map.cljs$core$IFn$_invoke$arity$variadic = ((function (g__13247__auto___13313){
return (function (args){
return cljs.core.apply.call(null,cljs.core.deref.call(null,g__13247__auto___13313),args);
});})(g__13247__auto___13313))
;

cljs.spec.gen.alpha.map.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.map.cljs$lang$applyTo = ((function (g__13247__auto___13313){
return (function (seq13288){
return cljs.spec.gen.alpha.map.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq13288));
});})(g__13247__auto___13313))
;


var g__13247__auto___13317 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.not_empty !== 'undefined')){
return clojure.test.check.generators.not_empty;
} else {
throw (new Error([cljs.core.str.cljs$core$IFn$_invoke$arity$1("Var "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","not-empty","clojure.test.check.generators/not-empty",-876211682,null)),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" does not exist, "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","not-empty","clojure.test.check.generators/not-empty",-876211682,null))),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" never required")].join('')));
}
}),null));
/**
 * Lazy loaded version of clojure.test.check.generators/not-empty
 */
cljs.spec.gen.alpha.not_empty = ((function (g__13247__auto___13317){
return (function cljs$spec$gen$alpha$not_empty(var_args){
var args__8663__auto__ = [];
var len__8656__auto___13318 = arguments.length;
var i__8657__auto___13319 = (0);
while(true){
if((i__8657__auto___13319 < len__8656__auto___13318)){
args__8663__auto__.push((arguments[i__8657__auto___13319]));

var G__13320 = (i__8657__auto___13319 + (1));
i__8657__auto___13319 = G__13320;
continue;
} else {
}
break;
}

var argseq__8664__auto__ = ((((0) < args__8663__auto__.length))?(new cljs.core.IndexedSeq(args__8663__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.not_empty.cljs$core$IFn$_invoke$arity$variadic(argseq__8664__auto__);
});})(g__13247__auto___13317))
;

cljs.spec.gen.alpha.not_empty.cljs$core$IFn$_invoke$arity$variadic = ((function (g__13247__auto___13317){
return (function (args){
return cljs.core.apply.call(null,cljs.core.deref.call(null,g__13247__auto___13317),args);
});})(g__13247__auto___13317))
;

cljs.spec.gen.alpha.not_empty.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.not_empty.cljs$lang$applyTo = ((function (g__13247__auto___13317){
return (function (seq13289){
return cljs.spec.gen.alpha.not_empty.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq13289));
});})(g__13247__auto___13317))
;


var g__13247__auto___13321 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.set !== 'undefined')){
return clojure.test.check.generators.set;
} else {
throw (new Error([cljs.core.str.cljs$core$IFn$_invoke$arity$1("Var "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","set","clojure.test.check.generators/set",-1027639543,null)),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" does not exist, "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","set","clojure.test.check.generators/set",-1027639543,null))),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" never required")].join('')));
}
}),null));
/**
 * Lazy loaded version of clojure.test.check.generators/set
 */
cljs.spec.gen.alpha.set = ((function (g__13247__auto___13321){
return (function cljs$spec$gen$alpha$set(var_args){
var args__8663__auto__ = [];
var len__8656__auto___13322 = arguments.length;
var i__8657__auto___13323 = (0);
while(true){
if((i__8657__auto___13323 < len__8656__auto___13322)){
args__8663__auto__.push((arguments[i__8657__auto___13323]));

var G__13324 = (i__8657__auto___13323 + (1));
i__8657__auto___13323 = G__13324;
continue;
} else {
}
break;
}

var argseq__8664__auto__ = ((((0) < args__8663__auto__.length))?(new cljs.core.IndexedSeq(args__8663__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.set.cljs$core$IFn$_invoke$arity$variadic(argseq__8664__auto__);
});})(g__13247__auto___13321))
;

cljs.spec.gen.alpha.set.cljs$core$IFn$_invoke$arity$variadic = ((function (g__13247__auto___13321){
return (function (args){
return cljs.core.apply.call(null,cljs.core.deref.call(null,g__13247__auto___13321),args);
});})(g__13247__auto___13321))
;

cljs.spec.gen.alpha.set.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.set.cljs$lang$applyTo = ((function (g__13247__auto___13321){
return (function (seq13290){
return cljs.spec.gen.alpha.set.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq13290));
});})(g__13247__auto___13321))
;


var g__13247__auto___13325 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.vector !== 'undefined')){
return clojure.test.check.generators.vector;
} else {
throw (new Error([cljs.core.str.cljs$core$IFn$_invoke$arity$1("Var "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","vector","clojure.test.check.generators/vector",1081775325,null)),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" does not exist, "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","vector","clojure.test.check.generators/vector",1081775325,null))),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" never required")].join('')));
}
}),null));
/**
 * Lazy loaded version of clojure.test.check.generators/vector
 */
cljs.spec.gen.alpha.vector = ((function (g__13247__auto___13325){
return (function cljs$spec$gen$alpha$vector(var_args){
var args__8663__auto__ = [];
var len__8656__auto___13326 = arguments.length;
var i__8657__auto___13327 = (0);
while(true){
if((i__8657__auto___13327 < len__8656__auto___13326)){
args__8663__auto__.push((arguments[i__8657__auto___13327]));

var G__13328 = (i__8657__auto___13327 + (1));
i__8657__auto___13327 = G__13328;
continue;
} else {
}
break;
}

var argseq__8664__auto__ = ((((0) < args__8663__auto__.length))?(new cljs.core.IndexedSeq(args__8663__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.vector.cljs$core$IFn$_invoke$arity$variadic(argseq__8664__auto__);
});})(g__13247__auto___13325))
;

cljs.spec.gen.alpha.vector.cljs$core$IFn$_invoke$arity$variadic = ((function (g__13247__auto___13325){
return (function (args){
return cljs.core.apply.call(null,cljs.core.deref.call(null,g__13247__auto___13325),args);
});})(g__13247__auto___13325))
;

cljs.spec.gen.alpha.vector.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.vector.cljs$lang$applyTo = ((function (g__13247__auto___13325){
return (function (seq13291){
return cljs.spec.gen.alpha.vector.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq13291));
});})(g__13247__auto___13325))
;


var g__13247__auto___13329 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.vector_distinct !== 'undefined')){
return clojure.test.check.generators.vector_distinct;
} else {
throw (new Error([cljs.core.str.cljs$core$IFn$_invoke$arity$1("Var "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","vector-distinct","clojure.test.check.generators/vector-distinct",1656877834,null)),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" does not exist, "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","vector-distinct","clojure.test.check.generators/vector-distinct",1656877834,null))),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" never required")].join('')));
}
}),null));
/**
 * Lazy loaded version of clojure.test.check.generators/vector-distinct
 */
cljs.spec.gen.alpha.vector_distinct = ((function (g__13247__auto___13329){
return (function cljs$spec$gen$alpha$vector_distinct(var_args){
var args__8663__auto__ = [];
var len__8656__auto___13330 = arguments.length;
var i__8657__auto___13331 = (0);
while(true){
if((i__8657__auto___13331 < len__8656__auto___13330)){
args__8663__auto__.push((arguments[i__8657__auto___13331]));

var G__13332 = (i__8657__auto___13331 + (1));
i__8657__auto___13331 = G__13332;
continue;
} else {
}
break;
}

var argseq__8664__auto__ = ((((0) < args__8663__auto__.length))?(new cljs.core.IndexedSeq(args__8663__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.vector_distinct.cljs$core$IFn$_invoke$arity$variadic(argseq__8664__auto__);
});})(g__13247__auto___13329))
;

cljs.spec.gen.alpha.vector_distinct.cljs$core$IFn$_invoke$arity$variadic = ((function (g__13247__auto___13329){
return (function (args){
return cljs.core.apply.call(null,cljs.core.deref.call(null,g__13247__auto___13329),args);
});})(g__13247__auto___13329))
;

cljs.spec.gen.alpha.vector_distinct.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.vector_distinct.cljs$lang$applyTo = ((function (g__13247__auto___13329){
return (function (seq13292){
return cljs.spec.gen.alpha.vector_distinct.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq13292));
});})(g__13247__auto___13329))
;


var g__13247__auto___13333 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.fmap !== 'undefined')){
return clojure.test.check.generators.fmap;
} else {
throw (new Error([cljs.core.str.cljs$core$IFn$_invoke$arity$1("Var "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","fmap","clojure.test.check.generators/fmap",1957997092,null)),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" does not exist, "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","fmap","clojure.test.check.generators/fmap",1957997092,null))),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" never required")].join('')));
}
}),null));
/**
 * Lazy loaded version of clojure.test.check.generators/fmap
 */
cljs.spec.gen.alpha.fmap = ((function (g__13247__auto___13333){
return (function cljs$spec$gen$alpha$fmap(var_args){
var args__8663__auto__ = [];
var len__8656__auto___13334 = arguments.length;
var i__8657__auto___13335 = (0);
while(true){
if((i__8657__auto___13335 < len__8656__auto___13334)){
args__8663__auto__.push((arguments[i__8657__auto___13335]));

var G__13336 = (i__8657__auto___13335 + (1));
i__8657__auto___13335 = G__13336;
continue;
} else {
}
break;
}

var argseq__8664__auto__ = ((((0) < args__8663__auto__.length))?(new cljs.core.IndexedSeq(args__8663__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.fmap.cljs$core$IFn$_invoke$arity$variadic(argseq__8664__auto__);
});})(g__13247__auto___13333))
;

cljs.spec.gen.alpha.fmap.cljs$core$IFn$_invoke$arity$variadic = ((function (g__13247__auto___13333){
return (function (args){
return cljs.core.apply.call(null,cljs.core.deref.call(null,g__13247__auto___13333),args);
});})(g__13247__auto___13333))
;

cljs.spec.gen.alpha.fmap.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.fmap.cljs$lang$applyTo = ((function (g__13247__auto___13333){
return (function (seq13293){
return cljs.spec.gen.alpha.fmap.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq13293));
});})(g__13247__auto___13333))
;


var g__13247__auto___13337 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.elements !== 'undefined')){
return clojure.test.check.generators.elements;
} else {
throw (new Error([cljs.core.str.cljs$core$IFn$_invoke$arity$1("Var "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","elements","clojure.test.check.generators/elements",438991326,null)),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" does not exist, "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","elements","clojure.test.check.generators/elements",438991326,null))),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" never required")].join('')));
}
}),null));
/**
 * Lazy loaded version of clojure.test.check.generators/elements
 */
cljs.spec.gen.alpha.elements = ((function (g__13247__auto___13337){
return (function cljs$spec$gen$alpha$elements(var_args){
var args__8663__auto__ = [];
var len__8656__auto___13338 = arguments.length;
var i__8657__auto___13339 = (0);
while(true){
if((i__8657__auto___13339 < len__8656__auto___13338)){
args__8663__auto__.push((arguments[i__8657__auto___13339]));

var G__13340 = (i__8657__auto___13339 + (1));
i__8657__auto___13339 = G__13340;
continue;
} else {
}
break;
}

var argseq__8664__auto__ = ((((0) < args__8663__auto__.length))?(new cljs.core.IndexedSeq(args__8663__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.elements.cljs$core$IFn$_invoke$arity$variadic(argseq__8664__auto__);
});})(g__13247__auto___13337))
;

cljs.spec.gen.alpha.elements.cljs$core$IFn$_invoke$arity$variadic = ((function (g__13247__auto___13337){
return (function (args){
return cljs.core.apply.call(null,cljs.core.deref.call(null,g__13247__auto___13337),args);
});})(g__13247__auto___13337))
;

cljs.spec.gen.alpha.elements.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.elements.cljs$lang$applyTo = ((function (g__13247__auto___13337){
return (function (seq13294){
return cljs.spec.gen.alpha.elements.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq13294));
});})(g__13247__auto___13337))
;


var g__13247__auto___13341 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.bind !== 'undefined')){
return clojure.test.check.generators.bind;
} else {
throw (new Error([cljs.core.str.cljs$core$IFn$_invoke$arity$1("Var "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","bind","clojure.test.check.generators/bind",-361313906,null)),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" does not exist, "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","bind","clojure.test.check.generators/bind",-361313906,null))),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" never required")].join('')));
}
}),null));
/**
 * Lazy loaded version of clojure.test.check.generators/bind
 */
cljs.spec.gen.alpha.bind = ((function (g__13247__auto___13341){
return (function cljs$spec$gen$alpha$bind(var_args){
var args__8663__auto__ = [];
var len__8656__auto___13342 = arguments.length;
var i__8657__auto___13343 = (0);
while(true){
if((i__8657__auto___13343 < len__8656__auto___13342)){
args__8663__auto__.push((arguments[i__8657__auto___13343]));

var G__13344 = (i__8657__auto___13343 + (1));
i__8657__auto___13343 = G__13344;
continue;
} else {
}
break;
}

var argseq__8664__auto__ = ((((0) < args__8663__auto__.length))?(new cljs.core.IndexedSeq(args__8663__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.bind.cljs$core$IFn$_invoke$arity$variadic(argseq__8664__auto__);
});})(g__13247__auto___13341))
;

cljs.spec.gen.alpha.bind.cljs$core$IFn$_invoke$arity$variadic = ((function (g__13247__auto___13341){
return (function (args){
return cljs.core.apply.call(null,cljs.core.deref.call(null,g__13247__auto___13341),args);
});})(g__13247__auto___13341))
;

cljs.spec.gen.alpha.bind.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.bind.cljs$lang$applyTo = ((function (g__13247__auto___13341){
return (function (seq13295){
return cljs.spec.gen.alpha.bind.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq13295));
});})(g__13247__auto___13341))
;


var g__13247__auto___13345 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.choose !== 'undefined')){
return clojure.test.check.generators.choose;
} else {
throw (new Error([cljs.core.str.cljs$core$IFn$_invoke$arity$1("Var "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","choose","clojure.test.check.generators/choose",909997832,null)),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" does not exist, "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","choose","clojure.test.check.generators/choose",909997832,null))),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" never required")].join('')));
}
}),null));
/**
 * Lazy loaded version of clojure.test.check.generators/choose
 */
cljs.spec.gen.alpha.choose = ((function (g__13247__auto___13345){
return (function cljs$spec$gen$alpha$choose(var_args){
var args__8663__auto__ = [];
var len__8656__auto___13346 = arguments.length;
var i__8657__auto___13347 = (0);
while(true){
if((i__8657__auto___13347 < len__8656__auto___13346)){
args__8663__auto__.push((arguments[i__8657__auto___13347]));

var G__13348 = (i__8657__auto___13347 + (1));
i__8657__auto___13347 = G__13348;
continue;
} else {
}
break;
}

var argseq__8664__auto__ = ((((0) < args__8663__auto__.length))?(new cljs.core.IndexedSeq(args__8663__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.choose.cljs$core$IFn$_invoke$arity$variadic(argseq__8664__auto__);
});})(g__13247__auto___13345))
;

cljs.spec.gen.alpha.choose.cljs$core$IFn$_invoke$arity$variadic = ((function (g__13247__auto___13345){
return (function (args){
return cljs.core.apply.call(null,cljs.core.deref.call(null,g__13247__auto___13345),args);
});})(g__13247__auto___13345))
;

cljs.spec.gen.alpha.choose.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.choose.cljs$lang$applyTo = ((function (g__13247__auto___13345){
return (function (seq13296){
return cljs.spec.gen.alpha.choose.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq13296));
});})(g__13247__auto___13345))
;


var g__13247__auto___13349 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.one_of !== 'undefined')){
return clojure.test.check.generators.one_of;
} else {
throw (new Error([cljs.core.str.cljs$core$IFn$_invoke$arity$1("Var "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","one-of","clojure.test.check.generators/one-of",-183339191,null)),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" does not exist, "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","one-of","clojure.test.check.generators/one-of",-183339191,null))),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" never required")].join('')));
}
}),null));
/**
 * Lazy loaded version of clojure.test.check.generators/one-of
 */
cljs.spec.gen.alpha.one_of = ((function (g__13247__auto___13349){
return (function cljs$spec$gen$alpha$one_of(var_args){
var args__8663__auto__ = [];
var len__8656__auto___13350 = arguments.length;
var i__8657__auto___13351 = (0);
while(true){
if((i__8657__auto___13351 < len__8656__auto___13350)){
args__8663__auto__.push((arguments[i__8657__auto___13351]));

var G__13352 = (i__8657__auto___13351 + (1));
i__8657__auto___13351 = G__13352;
continue;
} else {
}
break;
}

var argseq__8664__auto__ = ((((0) < args__8663__auto__.length))?(new cljs.core.IndexedSeq(args__8663__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.one_of.cljs$core$IFn$_invoke$arity$variadic(argseq__8664__auto__);
});})(g__13247__auto___13349))
;

cljs.spec.gen.alpha.one_of.cljs$core$IFn$_invoke$arity$variadic = ((function (g__13247__auto___13349){
return (function (args){
return cljs.core.apply.call(null,cljs.core.deref.call(null,g__13247__auto___13349),args);
});})(g__13247__auto___13349))
;

cljs.spec.gen.alpha.one_of.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.one_of.cljs$lang$applyTo = ((function (g__13247__auto___13349){
return (function (seq13297){
return cljs.spec.gen.alpha.one_of.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq13297));
});})(g__13247__auto___13349))
;


var g__13247__auto___13353 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.such_that !== 'undefined')){
return clojure.test.check.generators.such_that;
} else {
throw (new Error([cljs.core.str.cljs$core$IFn$_invoke$arity$1("Var "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","such-that","clojure.test.check.generators/such-that",-1754178732,null)),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" does not exist, "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","such-that","clojure.test.check.generators/such-that",-1754178732,null))),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" never required")].join('')));
}
}),null));
/**
 * Lazy loaded version of clojure.test.check.generators/such-that
 */
cljs.spec.gen.alpha.such_that = ((function (g__13247__auto___13353){
return (function cljs$spec$gen$alpha$such_that(var_args){
var args__8663__auto__ = [];
var len__8656__auto___13354 = arguments.length;
var i__8657__auto___13355 = (0);
while(true){
if((i__8657__auto___13355 < len__8656__auto___13354)){
args__8663__auto__.push((arguments[i__8657__auto___13355]));

var G__13356 = (i__8657__auto___13355 + (1));
i__8657__auto___13355 = G__13356;
continue;
} else {
}
break;
}

var argseq__8664__auto__ = ((((0) < args__8663__auto__.length))?(new cljs.core.IndexedSeq(args__8663__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.such_that.cljs$core$IFn$_invoke$arity$variadic(argseq__8664__auto__);
});})(g__13247__auto___13353))
;

cljs.spec.gen.alpha.such_that.cljs$core$IFn$_invoke$arity$variadic = ((function (g__13247__auto___13353){
return (function (args){
return cljs.core.apply.call(null,cljs.core.deref.call(null,g__13247__auto___13353),args);
});})(g__13247__auto___13353))
;

cljs.spec.gen.alpha.such_that.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.such_that.cljs$lang$applyTo = ((function (g__13247__auto___13353){
return (function (seq13298){
return cljs.spec.gen.alpha.such_that.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq13298));
});})(g__13247__auto___13353))
;


var g__13247__auto___13357 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.tuple !== 'undefined')){
return clojure.test.check.generators.tuple;
} else {
throw (new Error([cljs.core.str.cljs$core$IFn$_invoke$arity$1("Var "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","tuple","clojure.test.check.generators/tuple",-143711557,null)),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" does not exist, "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","tuple","clojure.test.check.generators/tuple",-143711557,null))),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" never required")].join('')));
}
}),null));
/**
 * Lazy loaded version of clojure.test.check.generators/tuple
 */
cljs.spec.gen.alpha.tuple = ((function (g__13247__auto___13357){
return (function cljs$spec$gen$alpha$tuple(var_args){
var args__8663__auto__ = [];
var len__8656__auto___13358 = arguments.length;
var i__8657__auto___13359 = (0);
while(true){
if((i__8657__auto___13359 < len__8656__auto___13358)){
args__8663__auto__.push((arguments[i__8657__auto___13359]));

var G__13360 = (i__8657__auto___13359 + (1));
i__8657__auto___13359 = G__13360;
continue;
} else {
}
break;
}

var argseq__8664__auto__ = ((((0) < args__8663__auto__.length))?(new cljs.core.IndexedSeq(args__8663__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.tuple.cljs$core$IFn$_invoke$arity$variadic(argseq__8664__auto__);
});})(g__13247__auto___13357))
;

cljs.spec.gen.alpha.tuple.cljs$core$IFn$_invoke$arity$variadic = ((function (g__13247__auto___13357){
return (function (args){
return cljs.core.apply.call(null,cljs.core.deref.call(null,g__13247__auto___13357),args);
});})(g__13247__auto___13357))
;

cljs.spec.gen.alpha.tuple.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.tuple.cljs$lang$applyTo = ((function (g__13247__auto___13357){
return (function (seq13299){
return cljs.spec.gen.alpha.tuple.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq13299));
});})(g__13247__auto___13357))
;


var g__13247__auto___13361 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.sample !== 'undefined')){
return clojure.test.check.generators.sample;
} else {
throw (new Error([cljs.core.str.cljs$core$IFn$_invoke$arity$1("Var "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","sample","clojure.test.check.generators/sample",-382944992,null)),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" does not exist, "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","sample","clojure.test.check.generators/sample",-382944992,null))),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" never required")].join('')));
}
}),null));
/**
 * Lazy loaded version of clojure.test.check.generators/sample
 */
cljs.spec.gen.alpha.sample = ((function (g__13247__auto___13361){
return (function cljs$spec$gen$alpha$sample(var_args){
var args__8663__auto__ = [];
var len__8656__auto___13362 = arguments.length;
var i__8657__auto___13363 = (0);
while(true){
if((i__8657__auto___13363 < len__8656__auto___13362)){
args__8663__auto__.push((arguments[i__8657__auto___13363]));

var G__13364 = (i__8657__auto___13363 + (1));
i__8657__auto___13363 = G__13364;
continue;
} else {
}
break;
}

var argseq__8664__auto__ = ((((0) < args__8663__auto__.length))?(new cljs.core.IndexedSeq(args__8663__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.sample.cljs$core$IFn$_invoke$arity$variadic(argseq__8664__auto__);
});})(g__13247__auto___13361))
;

cljs.spec.gen.alpha.sample.cljs$core$IFn$_invoke$arity$variadic = ((function (g__13247__auto___13361){
return (function (args){
return cljs.core.apply.call(null,cljs.core.deref.call(null,g__13247__auto___13361),args);
});})(g__13247__auto___13361))
;

cljs.spec.gen.alpha.sample.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.sample.cljs$lang$applyTo = ((function (g__13247__auto___13361){
return (function (seq13300){
return cljs.spec.gen.alpha.sample.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq13300));
});})(g__13247__auto___13361))
;


var g__13247__auto___13365 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.return$ !== 'undefined')){
return clojure.test.check.generators.return$;
} else {
throw (new Error([cljs.core.str.cljs$core$IFn$_invoke$arity$1("Var "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","return","clojure.test.check.generators/return",1744522038,null)),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" does not exist, "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","return","clojure.test.check.generators/return",1744522038,null))),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" never required")].join('')));
}
}),null));
/**
 * Lazy loaded version of clojure.test.check.generators/return
 */
cljs.spec.gen.alpha.return$ = ((function (g__13247__auto___13365){
return (function cljs$spec$gen$alpha$return(var_args){
var args__8663__auto__ = [];
var len__8656__auto___13366 = arguments.length;
var i__8657__auto___13367 = (0);
while(true){
if((i__8657__auto___13367 < len__8656__auto___13366)){
args__8663__auto__.push((arguments[i__8657__auto___13367]));

var G__13368 = (i__8657__auto___13367 + (1));
i__8657__auto___13367 = G__13368;
continue;
} else {
}
break;
}

var argseq__8664__auto__ = ((((0) < args__8663__auto__.length))?(new cljs.core.IndexedSeq(args__8663__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.return$.cljs$core$IFn$_invoke$arity$variadic(argseq__8664__auto__);
});})(g__13247__auto___13365))
;

cljs.spec.gen.alpha.return$.cljs$core$IFn$_invoke$arity$variadic = ((function (g__13247__auto___13365){
return (function (args){
return cljs.core.apply.call(null,cljs.core.deref.call(null,g__13247__auto___13365),args);
});})(g__13247__auto___13365))
;

cljs.spec.gen.alpha.return$.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.return$.cljs$lang$applyTo = ((function (g__13247__auto___13365){
return (function (seq13301){
return cljs.spec.gen.alpha.return$.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq13301));
});})(g__13247__auto___13365))
;


var g__13247__auto___13369 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.large_integer_STAR_ !== 'undefined')){
return clojure.test.check.generators.large_integer_STAR_;
} else {
throw (new Error([cljs.core.str.cljs$core$IFn$_invoke$arity$1("Var "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","large-integer*","clojure.test.check.generators/large-integer*",-437830670,null)),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" does not exist, "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","large-integer*","clojure.test.check.generators/large-integer*",-437830670,null))),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" never required")].join('')));
}
}),null));
/**
 * Lazy loaded version of clojure.test.check.generators/large-integer*
 */
cljs.spec.gen.alpha.large_integer_STAR_ = ((function (g__13247__auto___13369){
return (function cljs$spec$gen$alpha$large_integer_STAR_(var_args){
var args__8663__auto__ = [];
var len__8656__auto___13370 = arguments.length;
var i__8657__auto___13371 = (0);
while(true){
if((i__8657__auto___13371 < len__8656__auto___13370)){
args__8663__auto__.push((arguments[i__8657__auto___13371]));

var G__13372 = (i__8657__auto___13371 + (1));
i__8657__auto___13371 = G__13372;
continue;
} else {
}
break;
}

var argseq__8664__auto__ = ((((0) < args__8663__auto__.length))?(new cljs.core.IndexedSeq(args__8663__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.large_integer_STAR_.cljs$core$IFn$_invoke$arity$variadic(argseq__8664__auto__);
});})(g__13247__auto___13369))
;

cljs.spec.gen.alpha.large_integer_STAR_.cljs$core$IFn$_invoke$arity$variadic = ((function (g__13247__auto___13369){
return (function (args){
return cljs.core.apply.call(null,cljs.core.deref.call(null,g__13247__auto___13369),args);
});})(g__13247__auto___13369))
;

cljs.spec.gen.alpha.large_integer_STAR_.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.large_integer_STAR_.cljs$lang$applyTo = ((function (g__13247__auto___13369){
return (function (seq13302){
return cljs.spec.gen.alpha.large_integer_STAR_.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq13302));
});})(g__13247__auto___13369))
;


var g__13247__auto___13373 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.double_STAR_ !== 'undefined')){
return clojure.test.check.generators.double_STAR_;
} else {
throw (new Error([cljs.core.str.cljs$core$IFn$_invoke$arity$1("Var "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","double*","clojure.test.check.generators/double*",841542265,null)),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" does not exist, "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","double*","clojure.test.check.generators/double*",841542265,null))),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" never required")].join('')));
}
}),null));
/**
 * Lazy loaded version of clojure.test.check.generators/double*
 */
cljs.spec.gen.alpha.double_STAR_ = ((function (g__13247__auto___13373){
return (function cljs$spec$gen$alpha$double_STAR_(var_args){
var args__8663__auto__ = [];
var len__8656__auto___13374 = arguments.length;
var i__8657__auto___13375 = (0);
while(true){
if((i__8657__auto___13375 < len__8656__auto___13374)){
args__8663__auto__.push((arguments[i__8657__auto___13375]));

var G__13376 = (i__8657__auto___13375 + (1));
i__8657__auto___13375 = G__13376;
continue;
} else {
}
break;
}

var argseq__8664__auto__ = ((((0) < args__8663__auto__.length))?(new cljs.core.IndexedSeq(args__8663__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.double_STAR_.cljs$core$IFn$_invoke$arity$variadic(argseq__8664__auto__);
});})(g__13247__auto___13373))
;

cljs.spec.gen.alpha.double_STAR_.cljs$core$IFn$_invoke$arity$variadic = ((function (g__13247__auto___13373){
return (function (args){
return cljs.core.apply.call(null,cljs.core.deref.call(null,g__13247__auto___13373),args);
});})(g__13247__auto___13373))
;

cljs.spec.gen.alpha.double_STAR_.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.double_STAR_.cljs$lang$applyTo = ((function (g__13247__auto___13373){
return (function (seq13303){
return cljs.spec.gen.alpha.double_STAR_.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq13303));
});})(g__13247__auto___13373))
;


var g__13247__auto___13377 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.frequency !== 'undefined')){
return clojure.test.check.generators.frequency;
} else {
throw (new Error([cljs.core.str.cljs$core$IFn$_invoke$arity$1("Var "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","frequency","clojure.test.check.generators/frequency",2090703177,null)),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" does not exist, "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","frequency","clojure.test.check.generators/frequency",2090703177,null))),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" never required")].join('')));
}
}),null));
/**
 * Lazy loaded version of clojure.test.check.generators/frequency
 */
cljs.spec.gen.alpha.frequency = ((function (g__13247__auto___13377){
return (function cljs$spec$gen$alpha$frequency(var_args){
var args__8663__auto__ = [];
var len__8656__auto___13378 = arguments.length;
var i__8657__auto___13379 = (0);
while(true){
if((i__8657__auto___13379 < len__8656__auto___13378)){
args__8663__auto__.push((arguments[i__8657__auto___13379]));

var G__13380 = (i__8657__auto___13379 + (1));
i__8657__auto___13379 = G__13380;
continue;
} else {
}
break;
}

var argseq__8664__auto__ = ((((0) < args__8663__auto__.length))?(new cljs.core.IndexedSeq(args__8663__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.frequency.cljs$core$IFn$_invoke$arity$variadic(argseq__8664__auto__);
});})(g__13247__auto___13377))
;

cljs.spec.gen.alpha.frequency.cljs$core$IFn$_invoke$arity$variadic = ((function (g__13247__auto___13377){
return (function (args){
return cljs.core.apply.call(null,cljs.core.deref.call(null,g__13247__auto___13377),args);
});})(g__13247__auto___13377))
;

cljs.spec.gen.alpha.frequency.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.frequency.cljs$lang$applyTo = ((function (g__13247__auto___13377){
return (function (seq13304){
return cljs.spec.gen.alpha.frequency.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq13304));
});})(g__13247__auto___13377))
;

var g__13260__auto___13402 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.any !== 'undefined')){
return clojure.test.check.generators.any;
} else {
throw (new Error([cljs.core.str.cljs$core$IFn$_invoke$arity$1("Var "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","any","clojure.test.check.generators/any",1883743710,null)),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" does not exist, "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","any","clojure.test.check.generators/any",1883743710,null))),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" never required")].join('')));
}
}),null));
/**
 * Fn returning clojure.test.check.generators/any
 */
cljs.spec.gen.alpha.any = ((function (g__13260__auto___13402){
return (function cljs$spec$gen$alpha$any(var_args){
var args__8663__auto__ = [];
var len__8656__auto___13403 = arguments.length;
var i__8657__auto___13404 = (0);
while(true){
if((i__8657__auto___13404 < len__8656__auto___13403)){
args__8663__auto__.push((arguments[i__8657__auto___13404]));

var G__13405 = (i__8657__auto___13404 + (1));
i__8657__auto___13404 = G__13405;
continue;
} else {
}
break;
}

var argseq__8664__auto__ = ((((0) < args__8663__auto__.length))?(new cljs.core.IndexedSeq(args__8663__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.any.cljs$core$IFn$_invoke$arity$variadic(argseq__8664__auto__);
});})(g__13260__auto___13402))
;

cljs.spec.gen.alpha.any.cljs$core$IFn$_invoke$arity$variadic = ((function (g__13260__auto___13402){
return (function (args){
return cljs.core.deref.call(null,g__13260__auto___13402);
});})(g__13260__auto___13402))
;

cljs.spec.gen.alpha.any.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.any.cljs$lang$applyTo = ((function (g__13260__auto___13402){
return (function (seq13381){
return cljs.spec.gen.alpha.any.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq13381));
});})(g__13260__auto___13402))
;


var g__13260__auto___13406 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.any_printable !== 'undefined')){
return clojure.test.check.generators.any_printable;
} else {
throw (new Error([cljs.core.str.cljs$core$IFn$_invoke$arity$1("Var "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","any-printable","clojure.test.check.generators/any-printable",-1570493991,null)),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" does not exist, "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","any-printable","clojure.test.check.generators/any-printable",-1570493991,null))),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" never required")].join('')));
}
}),null));
/**
 * Fn returning clojure.test.check.generators/any-printable
 */
cljs.spec.gen.alpha.any_printable = ((function (g__13260__auto___13406){
return (function cljs$spec$gen$alpha$any_printable(var_args){
var args__8663__auto__ = [];
var len__8656__auto___13407 = arguments.length;
var i__8657__auto___13408 = (0);
while(true){
if((i__8657__auto___13408 < len__8656__auto___13407)){
args__8663__auto__.push((arguments[i__8657__auto___13408]));

var G__13409 = (i__8657__auto___13408 + (1));
i__8657__auto___13408 = G__13409;
continue;
} else {
}
break;
}

var argseq__8664__auto__ = ((((0) < args__8663__auto__.length))?(new cljs.core.IndexedSeq(args__8663__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.any_printable.cljs$core$IFn$_invoke$arity$variadic(argseq__8664__auto__);
});})(g__13260__auto___13406))
;

cljs.spec.gen.alpha.any_printable.cljs$core$IFn$_invoke$arity$variadic = ((function (g__13260__auto___13406){
return (function (args){
return cljs.core.deref.call(null,g__13260__auto___13406);
});})(g__13260__auto___13406))
;

cljs.spec.gen.alpha.any_printable.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.any_printable.cljs$lang$applyTo = ((function (g__13260__auto___13406){
return (function (seq13382){
return cljs.spec.gen.alpha.any_printable.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq13382));
});})(g__13260__auto___13406))
;


var g__13260__auto___13410 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.boolean$ !== 'undefined')){
return clojure.test.check.generators.boolean$;
} else {
throw (new Error([cljs.core.str.cljs$core$IFn$_invoke$arity$1("Var "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","boolean","clojure.test.check.generators/boolean",1586992347,null)),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" does not exist, "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","boolean","clojure.test.check.generators/boolean",1586992347,null))),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" never required")].join('')));
}
}),null));
/**
 * Fn returning clojure.test.check.generators/boolean
 */
cljs.spec.gen.alpha.boolean$ = ((function (g__13260__auto___13410){
return (function cljs$spec$gen$alpha$boolean(var_args){
var args__8663__auto__ = [];
var len__8656__auto___13411 = arguments.length;
var i__8657__auto___13412 = (0);
while(true){
if((i__8657__auto___13412 < len__8656__auto___13411)){
args__8663__auto__.push((arguments[i__8657__auto___13412]));

var G__13413 = (i__8657__auto___13412 + (1));
i__8657__auto___13412 = G__13413;
continue;
} else {
}
break;
}

var argseq__8664__auto__ = ((((0) < args__8663__auto__.length))?(new cljs.core.IndexedSeq(args__8663__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.boolean$.cljs$core$IFn$_invoke$arity$variadic(argseq__8664__auto__);
});})(g__13260__auto___13410))
;

cljs.spec.gen.alpha.boolean$.cljs$core$IFn$_invoke$arity$variadic = ((function (g__13260__auto___13410){
return (function (args){
return cljs.core.deref.call(null,g__13260__auto___13410);
});})(g__13260__auto___13410))
;

cljs.spec.gen.alpha.boolean$.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.boolean$.cljs$lang$applyTo = ((function (g__13260__auto___13410){
return (function (seq13383){
return cljs.spec.gen.alpha.boolean$.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq13383));
});})(g__13260__auto___13410))
;


var g__13260__auto___13414 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.char$ !== 'undefined')){
return clojure.test.check.generators.char$;
} else {
throw (new Error([cljs.core.str.cljs$core$IFn$_invoke$arity$1("Var "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","char","clojure.test.check.generators/char",-1426343459,null)),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" does not exist, "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","char","clojure.test.check.generators/char",-1426343459,null))),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" never required")].join('')));
}
}),null));
/**
 * Fn returning clojure.test.check.generators/char
 */
cljs.spec.gen.alpha.char$ = ((function (g__13260__auto___13414){
return (function cljs$spec$gen$alpha$char(var_args){
var args__8663__auto__ = [];
var len__8656__auto___13415 = arguments.length;
var i__8657__auto___13416 = (0);
while(true){
if((i__8657__auto___13416 < len__8656__auto___13415)){
args__8663__auto__.push((arguments[i__8657__auto___13416]));

var G__13417 = (i__8657__auto___13416 + (1));
i__8657__auto___13416 = G__13417;
continue;
} else {
}
break;
}

var argseq__8664__auto__ = ((((0) < args__8663__auto__.length))?(new cljs.core.IndexedSeq(args__8663__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.char$.cljs$core$IFn$_invoke$arity$variadic(argseq__8664__auto__);
});})(g__13260__auto___13414))
;

cljs.spec.gen.alpha.char$.cljs$core$IFn$_invoke$arity$variadic = ((function (g__13260__auto___13414){
return (function (args){
return cljs.core.deref.call(null,g__13260__auto___13414);
});})(g__13260__auto___13414))
;

cljs.spec.gen.alpha.char$.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.char$.cljs$lang$applyTo = ((function (g__13260__auto___13414){
return (function (seq13384){
return cljs.spec.gen.alpha.char$.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq13384));
});})(g__13260__auto___13414))
;


var g__13260__auto___13418 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.char_alpha !== 'undefined')){
return clojure.test.check.generators.char_alpha;
} else {
throw (new Error([cljs.core.str.cljs$core$IFn$_invoke$arity$1("Var "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","char-alpha","clojure.test.check.generators/char-alpha",615785796,null)),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" does not exist, "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","char-alpha","clojure.test.check.generators/char-alpha",615785796,null))),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" never required")].join('')));
}
}),null));
/**
 * Fn returning clojure.test.check.generators/char-alpha
 */
cljs.spec.gen.alpha.char_alpha = ((function (g__13260__auto___13418){
return (function cljs$spec$gen$alpha$char_alpha(var_args){
var args__8663__auto__ = [];
var len__8656__auto___13419 = arguments.length;
var i__8657__auto___13420 = (0);
while(true){
if((i__8657__auto___13420 < len__8656__auto___13419)){
args__8663__auto__.push((arguments[i__8657__auto___13420]));

var G__13421 = (i__8657__auto___13420 + (1));
i__8657__auto___13420 = G__13421;
continue;
} else {
}
break;
}

var argseq__8664__auto__ = ((((0) < args__8663__auto__.length))?(new cljs.core.IndexedSeq(args__8663__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.char_alpha.cljs$core$IFn$_invoke$arity$variadic(argseq__8664__auto__);
});})(g__13260__auto___13418))
;

cljs.spec.gen.alpha.char_alpha.cljs$core$IFn$_invoke$arity$variadic = ((function (g__13260__auto___13418){
return (function (args){
return cljs.core.deref.call(null,g__13260__auto___13418);
});})(g__13260__auto___13418))
;

cljs.spec.gen.alpha.char_alpha.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.char_alpha.cljs$lang$applyTo = ((function (g__13260__auto___13418){
return (function (seq13385){
return cljs.spec.gen.alpha.char_alpha.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq13385));
});})(g__13260__auto___13418))
;


var g__13260__auto___13422 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.char_alphanumeric !== 'undefined')){
return clojure.test.check.generators.char_alphanumeric;
} else {
throw (new Error([cljs.core.str.cljs$core$IFn$_invoke$arity$1("Var "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","char-alphanumeric","clojure.test.check.generators/char-alphanumeric",1383091431,null)),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" does not exist, "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","char-alphanumeric","clojure.test.check.generators/char-alphanumeric",1383091431,null))),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" never required")].join('')));
}
}),null));
/**
 * Fn returning clojure.test.check.generators/char-alphanumeric
 */
cljs.spec.gen.alpha.char_alphanumeric = ((function (g__13260__auto___13422){
return (function cljs$spec$gen$alpha$char_alphanumeric(var_args){
var args__8663__auto__ = [];
var len__8656__auto___13423 = arguments.length;
var i__8657__auto___13424 = (0);
while(true){
if((i__8657__auto___13424 < len__8656__auto___13423)){
args__8663__auto__.push((arguments[i__8657__auto___13424]));

var G__13425 = (i__8657__auto___13424 + (1));
i__8657__auto___13424 = G__13425;
continue;
} else {
}
break;
}

var argseq__8664__auto__ = ((((0) < args__8663__auto__.length))?(new cljs.core.IndexedSeq(args__8663__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.char_alphanumeric.cljs$core$IFn$_invoke$arity$variadic(argseq__8664__auto__);
});})(g__13260__auto___13422))
;

cljs.spec.gen.alpha.char_alphanumeric.cljs$core$IFn$_invoke$arity$variadic = ((function (g__13260__auto___13422){
return (function (args){
return cljs.core.deref.call(null,g__13260__auto___13422);
});})(g__13260__auto___13422))
;

cljs.spec.gen.alpha.char_alphanumeric.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.char_alphanumeric.cljs$lang$applyTo = ((function (g__13260__auto___13422){
return (function (seq13386){
return cljs.spec.gen.alpha.char_alphanumeric.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq13386));
});})(g__13260__auto___13422))
;


var g__13260__auto___13426 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.char_ascii !== 'undefined')){
return clojure.test.check.generators.char_ascii;
} else {
throw (new Error([cljs.core.str.cljs$core$IFn$_invoke$arity$1("Var "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","char-ascii","clojure.test.check.generators/char-ascii",-899908538,null)),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" does not exist, "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","char-ascii","clojure.test.check.generators/char-ascii",-899908538,null))),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" never required")].join('')));
}
}),null));
/**
 * Fn returning clojure.test.check.generators/char-ascii
 */
cljs.spec.gen.alpha.char_ascii = ((function (g__13260__auto___13426){
return (function cljs$spec$gen$alpha$char_ascii(var_args){
var args__8663__auto__ = [];
var len__8656__auto___13427 = arguments.length;
var i__8657__auto___13428 = (0);
while(true){
if((i__8657__auto___13428 < len__8656__auto___13427)){
args__8663__auto__.push((arguments[i__8657__auto___13428]));

var G__13429 = (i__8657__auto___13428 + (1));
i__8657__auto___13428 = G__13429;
continue;
} else {
}
break;
}

var argseq__8664__auto__ = ((((0) < args__8663__auto__.length))?(new cljs.core.IndexedSeq(args__8663__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.char_ascii.cljs$core$IFn$_invoke$arity$variadic(argseq__8664__auto__);
});})(g__13260__auto___13426))
;

cljs.spec.gen.alpha.char_ascii.cljs$core$IFn$_invoke$arity$variadic = ((function (g__13260__auto___13426){
return (function (args){
return cljs.core.deref.call(null,g__13260__auto___13426);
});})(g__13260__auto___13426))
;

cljs.spec.gen.alpha.char_ascii.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.char_ascii.cljs$lang$applyTo = ((function (g__13260__auto___13426){
return (function (seq13387){
return cljs.spec.gen.alpha.char_ascii.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq13387));
});})(g__13260__auto___13426))
;


var g__13260__auto___13430 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.double$ !== 'undefined')){
return clojure.test.check.generators.double$;
} else {
throw (new Error([cljs.core.str.cljs$core$IFn$_invoke$arity$1("Var "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","double","clojure.test.check.generators/double",668331090,null)),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" does not exist, "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","double","clojure.test.check.generators/double",668331090,null))),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" never required")].join('')));
}
}),null));
/**
 * Fn returning clojure.test.check.generators/double
 */
cljs.spec.gen.alpha.double$ = ((function (g__13260__auto___13430){
return (function cljs$spec$gen$alpha$double(var_args){
var args__8663__auto__ = [];
var len__8656__auto___13431 = arguments.length;
var i__8657__auto___13432 = (0);
while(true){
if((i__8657__auto___13432 < len__8656__auto___13431)){
args__8663__auto__.push((arguments[i__8657__auto___13432]));

var G__13433 = (i__8657__auto___13432 + (1));
i__8657__auto___13432 = G__13433;
continue;
} else {
}
break;
}

var argseq__8664__auto__ = ((((0) < args__8663__auto__.length))?(new cljs.core.IndexedSeq(args__8663__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.double$.cljs$core$IFn$_invoke$arity$variadic(argseq__8664__auto__);
});})(g__13260__auto___13430))
;

cljs.spec.gen.alpha.double$.cljs$core$IFn$_invoke$arity$variadic = ((function (g__13260__auto___13430){
return (function (args){
return cljs.core.deref.call(null,g__13260__auto___13430);
});})(g__13260__auto___13430))
;

cljs.spec.gen.alpha.double$.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.double$.cljs$lang$applyTo = ((function (g__13260__auto___13430){
return (function (seq13388){
return cljs.spec.gen.alpha.double$.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq13388));
});})(g__13260__auto___13430))
;


var g__13260__auto___13434 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.int$ !== 'undefined')){
return clojure.test.check.generators.int$;
} else {
throw (new Error([cljs.core.str.cljs$core$IFn$_invoke$arity$1("Var "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","int","clojure.test.check.generators/int",1756228469,null)),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" does not exist, "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","int","clojure.test.check.generators/int",1756228469,null))),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" never required")].join('')));
}
}),null));
/**
 * Fn returning clojure.test.check.generators/int
 */
cljs.spec.gen.alpha.int$ = ((function (g__13260__auto___13434){
return (function cljs$spec$gen$alpha$int(var_args){
var args__8663__auto__ = [];
var len__8656__auto___13435 = arguments.length;
var i__8657__auto___13436 = (0);
while(true){
if((i__8657__auto___13436 < len__8656__auto___13435)){
args__8663__auto__.push((arguments[i__8657__auto___13436]));

var G__13437 = (i__8657__auto___13436 + (1));
i__8657__auto___13436 = G__13437;
continue;
} else {
}
break;
}

var argseq__8664__auto__ = ((((0) < args__8663__auto__.length))?(new cljs.core.IndexedSeq(args__8663__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.int$.cljs$core$IFn$_invoke$arity$variadic(argseq__8664__auto__);
});})(g__13260__auto___13434))
;

cljs.spec.gen.alpha.int$.cljs$core$IFn$_invoke$arity$variadic = ((function (g__13260__auto___13434){
return (function (args){
return cljs.core.deref.call(null,g__13260__auto___13434);
});})(g__13260__auto___13434))
;

cljs.spec.gen.alpha.int$.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.int$.cljs$lang$applyTo = ((function (g__13260__auto___13434){
return (function (seq13389){
return cljs.spec.gen.alpha.int$.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq13389));
});})(g__13260__auto___13434))
;


var g__13260__auto___13438 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.keyword !== 'undefined')){
return clojure.test.check.generators.keyword;
} else {
throw (new Error([cljs.core.str.cljs$core$IFn$_invoke$arity$1("Var "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","keyword","clojure.test.check.generators/keyword",24530530,null)),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" does not exist, "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","keyword","clojure.test.check.generators/keyword",24530530,null))),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" never required")].join('')));
}
}),null));
/**
 * Fn returning clojure.test.check.generators/keyword
 */
cljs.spec.gen.alpha.keyword = ((function (g__13260__auto___13438){
return (function cljs$spec$gen$alpha$keyword(var_args){
var args__8663__auto__ = [];
var len__8656__auto___13439 = arguments.length;
var i__8657__auto___13440 = (0);
while(true){
if((i__8657__auto___13440 < len__8656__auto___13439)){
args__8663__auto__.push((arguments[i__8657__auto___13440]));

var G__13441 = (i__8657__auto___13440 + (1));
i__8657__auto___13440 = G__13441;
continue;
} else {
}
break;
}

var argseq__8664__auto__ = ((((0) < args__8663__auto__.length))?(new cljs.core.IndexedSeq(args__8663__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.keyword.cljs$core$IFn$_invoke$arity$variadic(argseq__8664__auto__);
});})(g__13260__auto___13438))
;

cljs.spec.gen.alpha.keyword.cljs$core$IFn$_invoke$arity$variadic = ((function (g__13260__auto___13438){
return (function (args){
return cljs.core.deref.call(null,g__13260__auto___13438);
});})(g__13260__auto___13438))
;

cljs.spec.gen.alpha.keyword.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.keyword.cljs$lang$applyTo = ((function (g__13260__auto___13438){
return (function (seq13390){
return cljs.spec.gen.alpha.keyword.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq13390));
});})(g__13260__auto___13438))
;


var g__13260__auto___13442 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.keyword_ns !== 'undefined')){
return clojure.test.check.generators.keyword_ns;
} else {
throw (new Error([cljs.core.str.cljs$core$IFn$_invoke$arity$1("Var "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","keyword-ns","clojure.test.check.generators/keyword-ns",-1492628482,null)),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" does not exist, "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","keyword-ns","clojure.test.check.generators/keyword-ns",-1492628482,null))),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" never required")].join('')));
}
}),null));
/**
 * Fn returning clojure.test.check.generators/keyword-ns
 */
cljs.spec.gen.alpha.keyword_ns = ((function (g__13260__auto___13442){
return (function cljs$spec$gen$alpha$keyword_ns(var_args){
var args__8663__auto__ = [];
var len__8656__auto___13443 = arguments.length;
var i__8657__auto___13444 = (0);
while(true){
if((i__8657__auto___13444 < len__8656__auto___13443)){
args__8663__auto__.push((arguments[i__8657__auto___13444]));

var G__13445 = (i__8657__auto___13444 + (1));
i__8657__auto___13444 = G__13445;
continue;
} else {
}
break;
}

var argseq__8664__auto__ = ((((0) < args__8663__auto__.length))?(new cljs.core.IndexedSeq(args__8663__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.keyword_ns.cljs$core$IFn$_invoke$arity$variadic(argseq__8664__auto__);
});})(g__13260__auto___13442))
;

cljs.spec.gen.alpha.keyword_ns.cljs$core$IFn$_invoke$arity$variadic = ((function (g__13260__auto___13442){
return (function (args){
return cljs.core.deref.call(null,g__13260__auto___13442);
});})(g__13260__auto___13442))
;

cljs.spec.gen.alpha.keyword_ns.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.keyword_ns.cljs$lang$applyTo = ((function (g__13260__auto___13442){
return (function (seq13391){
return cljs.spec.gen.alpha.keyword_ns.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq13391));
});})(g__13260__auto___13442))
;


var g__13260__auto___13446 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.large_integer !== 'undefined')){
return clojure.test.check.generators.large_integer;
} else {
throw (new Error([cljs.core.str.cljs$core$IFn$_invoke$arity$1("Var "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","large-integer","clojure.test.check.generators/large-integer",-865967138,null)),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" does not exist, "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","large-integer","clojure.test.check.generators/large-integer",-865967138,null))),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" never required")].join('')));
}
}),null));
/**
 * Fn returning clojure.test.check.generators/large-integer
 */
cljs.spec.gen.alpha.large_integer = ((function (g__13260__auto___13446){
return (function cljs$spec$gen$alpha$large_integer(var_args){
var args__8663__auto__ = [];
var len__8656__auto___13447 = arguments.length;
var i__8657__auto___13448 = (0);
while(true){
if((i__8657__auto___13448 < len__8656__auto___13447)){
args__8663__auto__.push((arguments[i__8657__auto___13448]));

var G__13449 = (i__8657__auto___13448 + (1));
i__8657__auto___13448 = G__13449;
continue;
} else {
}
break;
}

var argseq__8664__auto__ = ((((0) < args__8663__auto__.length))?(new cljs.core.IndexedSeq(args__8663__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.large_integer.cljs$core$IFn$_invoke$arity$variadic(argseq__8664__auto__);
});})(g__13260__auto___13446))
;

cljs.spec.gen.alpha.large_integer.cljs$core$IFn$_invoke$arity$variadic = ((function (g__13260__auto___13446){
return (function (args){
return cljs.core.deref.call(null,g__13260__auto___13446);
});})(g__13260__auto___13446))
;

cljs.spec.gen.alpha.large_integer.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.large_integer.cljs$lang$applyTo = ((function (g__13260__auto___13446){
return (function (seq13392){
return cljs.spec.gen.alpha.large_integer.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq13392));
});})(g__13260__auto___13446))
;


var g__13260__auto___13450 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.ratio !== 'undefined')){
return clojure.test.check.generators.ratio;
} else {
throw (new Error([cljs.core.str.cljs$core$IFn$_invoke$arity$1("Var "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","ratio","clojure.test.check.generators/ratio",1540966915,null)),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" does not exist, "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","ratio","clojure.test.check.generators/ratio",1540966915,null))),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" never required")].join('')));
}
}),null));
/**
 * Fn returning clojure.test.check.generators/ratio
 */
cljs.spec.gen.alpha.ratio = ((function (g__13260__auto___13450){
return (function cljs$spec$gen$alpha$ratio(var_args){
var args__8663__auto__ = [];
var len__8656__auto___13451 = arguments.length;
var i__8657__auto___13452 = (0);
while(true){
if((i__8657__auto___13452 < len__8656__auto___13451)){
args__8663__auto__.push((arguments[i__8657__auto___13452]));

var G__13453 = (i__8657__auto___13452 + (1));
i__8657__auto___13452 = G__13453;
continue;
} else {
}
break;
}

var argseq__8664__auto__ = ((((0) < args__8663__auto__.length))?(new cljs.core.IndexedSeq(args__8663__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.ratio.cljs$core$IFn$_invoke$arity$variadic(argseq__8664__auto__);
});})(g__13260__auto___13450))
;

cljs.spec.gen.alpha.ratio.cljs$core$IFn$_invoke$arity$variadic = ((function (g__13260__auto___13450){
return (function (args){
return cljs.core.deref.call(null,g__13260__auto___13450);
});})(g__13260__auto___13450))
;

cljs.spec.gen.alpha.ratio.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.ratio.cljs$lang$applyTo = ((function (g__13260__auto___13450){
return (function (seq13393){
return cljs.spec.gen.alpha.ratio.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq13393));
});})(g__13260__auto___13450))
;


var g__13260__auto___13454 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.simple_type !== 'undefined')){
return clojure.test.check.generators.simple_type;
} else {
throw (new Error([cljs.core.str.cljs$core$IFn$_invoke$arity$1("Var "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","simple-type","clojure.test.check.generators/simple-type",892572284,null)),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" does not exist, "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","simple-type","clojure.test.check.generators/simple-type",892572284,null))),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" never required")].join('')));
}
}),null));
/**
 * Fn returning clojure.test.check.generators/simple-type
 */
cljs.spec.gen.alpha.simple_type = ((function (g__13260__auto___13454){
return (function cljs$spec$gen$alpha$simple_type(var_args){
var args__8663__auto__ = [];
var len__8656__auto___13455 = arguments.length;
var i__8657__auto___13456 = (0);
while(true){
if((i__8657__auto___13456 < len__8656__auto___13455)){
args__8663__auto__.push((arguments[i__8657__auto___13456]));

var G__13457 = (i__8657__auto___13456 + (1));
i__8657__auto___13456 = G__13457;
continue;
} else {
}
break;
}

var argseq__8664__auto__ = ((((0) < args__8663__auto__.length))?(new cljs.core.IndexedSeq(args__8663__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.simple_type.cljs$core$IFn$_invoke$arity$variadic(argseq__8664__auto__);
});})(g__13260__auto___13454))
;

cljs.spec.gen.alpha.simple_type.cljs$core$IFn$_invoke$arity$variadic = ((function (g__13260__auto___13454){
return (function (args){
return cljs.core.deref.call(null,g__13260__auto___13454);
});})(g__13260__auto___13454))
;

cljs.spec.gen.alpha.simple_type.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.simple_type.cljs$lang$applyTo = ((function (g__13260__auto___13454){
return (function (seq13394){
return cljs.spec.gen.alpha.simple_type.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq13394));
});})(g__13260__auto___13454))
;


var g__13260__auto___13458 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.simple_type_printable !== 'undefined')){
return clojure.test.check.generators.simple_type_printable;
} else {
throw (new Error([cljs.core.str.cljs$core$IFn$_invoke$arity$1("Var "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","simple-type-printable","clojure.test.check.generators/simple-type-printable",-58489962,null)),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" does not exist, "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","simple-type-printable","clojure.test.check.generators/simple-type-printable",-58489962,null))),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" never required")].join('')));
}
}),null));
/**
 * Fn returning clojure.test.check.generators/simple-type-printable
 */
cljs.spec.gen.alpha.simple_type_printable = ((function (g__13260__auto___13458){
return (function cljs$spec$gen$alpha$simple_type_printable(var_args){
var args__8663__auto__ = [];
var len__8656__auto___13459 = arguments.length;
var i__8657__auto___13460 = (0);
while(true){
if((i__8657__auto___13460 < len__8656__auto___13459)){
args__8663__auto__.push((arguments[i__8657__auto___13460]));

var G__13461 = (i__8657__auto___13460 + (1));
i__8657__auto___13460 = G__13461;
continue;
} else {
}
break;
}

var argseq__8664__auto__ = ((((0) < args__8663__auto__.length))?(new cljs.core.IndexedSeq(args__8663__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.simple_type_printable.cljs$core$IFn$_invoke$arity$variadic(argseq__8664__auto__);
});})(g__13260__auto___13458))
;

cljs.spec.gen.alpha.simple_type_printable.cljs$core$IFn$_invoke$arity$variadic = ((function (g__13260__auto___13458){
return (function (args){
return cljs.core.deref.call(null,g__13260__auto___13458);
});})(g__13260__auto___13458))
;

cljs.spec.gen.alpha.simple_type_printable.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.simple_type_printable.cljs$lang$applyTo = ((function (g__13260__auto___13458){
return (function (seq13395){
return cljs.spec.gen.alpha.simple_type_printable.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq13395));
});})(g__13260__auto___13458))
;


var g__13260__auto___13462 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.string !== 'undefined')){
return clojure.test.check.generators.string;
} else {
throw (new Error([cljs.core.str.cljs$core$IFn$_invoke$arity$1("Var "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","string","clojure.test.check.generators/string",-1704750979,null)),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" does not exist, "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","string","clojure.test.check.generators/string",-1704750979,null))),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" never required")].join('')));
}
}),null));
/**
 * Fn returning clojure.test.check.generators/string
 */
cljs.spec.gen.alpha.string = ((function (g__13260__auto___13462){
return (function cljs$spec$gen$alpha$string(var_args){
var args__8663__auto__ = [];
var len__8656__auto___13463 = arguments.length;
var i__8657__auto___13464 = (0);
while(true){
if((i__8657__auto___13464 < len__8656__auto___13463)){
args__8663__auto__.push((arguments[i__8657__auto___13464]));

var G__13465 = (i__8657__auto___13464 + (1));
i__8657__auto___13464 = G__13465;
continue;
} else {
}
break;
}

var argseq__8664__auto__ = ((((0) < args__8663__auto__.length))?(new cljs.core.IndexedSeq(args__8663__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.string.cljs$core$IFn$_invoke$arity$variadic(argseq__8664__auto__);
});})(g__13260__auto___13462))
;

cljs.spec.gen.alpha.string.cljs$core$IFn$_invoke$arity$variadic = ((function (g__13260__auto___13462){
return (function (args){
return cljs.core.deref.call(null,g__13260__auto___13462);
});})(g__13260__auto___13462))
;

cljs.spec.gen.alpha.string.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.string.cljs$lang$applyTo = ((function (g__13260__auto___13462){
return (function (seq13396){
return cljs.spec.gen.alpha.string.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq13396));
});})(g__13260__auto___13462))
;


var g__13260__auto___13466 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.string_ascii !== 'undefined')){
return clojure.test.check.generators.string_ascii;
} else {
throw (new Error([cljs.core.str.cljs$core$IFn$_invoke$arity$1("Var "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","string-ascii","clojure.test.check.generators/string-ascii",-2009877640,null)),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" does not exist, "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","string-ascii","clojure.test.check.generators/string-ascii",-2009877640,null))),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" never required")].join('')));
}
}),null));
/**
 * Fn returning clojure.test.check.generators/string-ascii
 */
cljs.spec.gen.alpha.string_ascii = ((function (g__13260__auto___13466){
return (function cljs$spec$gen$alpha$string_ascii(var_args){
var args__8663__auto__ = [];
var len__8656__auto___13467 = arguments.length;
var i__8657__auto___13468 = (0);
while(true){
if((i__8657__auto___13468 < len__8656__auto___13467)){
args__8663__auto__.push((arguments[i__8657__auto___13468]));

var G__13469 = (i__8657__auto___13468 + (1));
i__8657__auto___13468 = G__13469;
continue;
} else {
}
break;
}

var argseq__8664__auto__ = ((((0) < args__8663__auto__.length))?(new cljs.core.IndexedSeq(args__8663__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.string_ascii.cljs$core$IFn$_invoke$arity$variadic(argseq__8664__auto__);
});})(g__13260__auto___13466))
;

cljs.spec.gen.alpha.string_ascii.cljs$core$IFn$_invoke$arity$variadic = ((function (g__13260__auto___13466){
return (function (args){
return cljs.core.deref.call(null,g__13260__auto___13466);
});})(g__13260__auto___13466))
;

cljs.spec.gen.alpha.string_ascii.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.string_ascii.cljs$lang$applyTo = ((function (g__13260__auto___13466){
return (function (seq13397){
return cljs.spec.gen.alpha.string_ascii.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq13397));
});})(g__13260__auto___13466))
;


var g__13260__auto___13470 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.string_alphanumeric !== 'undefined')){
return clojure.test.check.generators.string_alphanumeric;
} else {
throw (new Error([cljs.core.str.cljs$core$IFn$_invoke$arity$1("Var "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","string-alphanumeric","clojure.test.check.generators/string-alphanumeric",836374939,null)),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" does not exist, "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","string-alphanumeric","clojure.test.check.generators/string-alphanumeric",836374939,null))),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" never required")].join('')));
}
}),null));
/**
 * Fn returning clojure.test.check.generators/string-alphanumeric
 */
cljs.spec.gen.alpha.string_alphanumeric = ((function (g__13260__auto___13470){
return (function cljs$spec$gen$alpha$string_alphanumeric(var_args){
var args__8663__auto__ = [];
var len__8656__auto___13471 = arguments.length;
var i__8657__auto___13472 = (0);
while(true){
if((i__8657__auto___13472 < len__8656__auto___13471)){
args__8663__auto__.push((arguments[i__8657__auto___13472]));

var G__13473 = (i__8657__auto___13472 + (1));
i__8657__auto___13472 = G__13473;
continue;
} else {
}
break;
}

var argseq__8664__auto__ = ((((0) < args__8663__auto__.length))?(new cljs.core.IndexedSeq(args__8663__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.string_alphanumeric.cljs$core$IFn$_invoke$arity$variadic(argseq__8664__auto__);
});})(g__13260__auto___13470))
;

cljs.spec.gen.alpha.string_alphanumeric.cljs$core$IFn$_invoke$arity$variadic = ((function (g__13260__auto___13470){
return (function (args){
return cljs.core.deref.call(null,g__13260__auto___13470);
});})(g__13260__auto___13470))
;

cljs.spec.gen.alpha.string_alphanumeric.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.string_alphanumeric.cljs$lang$applyTo = ((function (g__13260__auto___13470){
return (function (seq13398){
return cljs.spec.gen.alpha.string_alphanumeric.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq13398));
});})(g__13260__auto___13470))
;


var g__13260__auto___13474 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.symbol !== 'undefined')){
return clojure.test.check.generators.symbol;
} else {
throw (new Error([cljs.core.str.cljs$core$IFn$_invoke$arity$1("Var "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","symbol","clojure.test.check.generators/symbol",-1305461065,null)),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" does not exist, "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","symbol","clojure.test.check.generators/symbol",-1305461065,null))),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" never required")].join('')));
}
}),null));
/**
 * Fn returning clojure.test.check.generators/symbol
 */
cljs.spec.gen.alpha.symbol = ((function (g__13260__auto___13474){
return (function cljs$spec$gen$alpha$symbol(var_args){
var args__8663__auto__ = [];
var len__8656__auto___13475 = arguments.length;
var i__8657__auto___13476 = (0);
while(true){
if((i__8657__auto___13476 < len__8656__auto___13475)){
args__8663__auto__.push((arguments[i__8657__auto___13476]));

var G__13477 = (i__8657__auto___13476 + (1));
i__8657__auto___13476 = G__13477;
continue;
} else {
}
break;
}

var argseq__8664__auto__ = ((((0) < args__8663__auto__.length))?(new cljs.core.IndexedSeq(args__8663__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.symbol.cljs$core$IFn$_invoke$arity$variadic(argseq__8664__auto__);
});})(g__13260__auto___13474))
;

cljs.spec.gen.alpha.symbol.cljs$core$IFn$_invoke$arity$variadic = ((function (g__13260__auto___13474){
return (function (args){
return cljs.core.deref.call(null,g__13260__auto___13474);
});})(g__13260__auto___13474))
;

cljs.spec.gen.alpha.symbol.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.symbol.cljs$lang$applyTo = ((function (g__13260__auto___13474){
return (function (seq13399){
return cljs.spec.gen.alpha.symbol.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq13399));
});})(g__13260__auto___13474))
;


var g__13260__auto___13478 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.symbol_ns !== 'undefined')){
return clojure.test.check.generators.symbol_ns;
} else {
throw (new Error([cljs.core.str.cljs$core$IFn$_invoke$arity$1("Var "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","symbol-ns","clojure.test.check.generators/symbol-ns",-862629490,null)),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" does not exist, "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","symbol-ns","clojure.test.check.generators/symbol-ns",-862629490,null))),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" never required")].join('')));
}
}),null));
/**
 * Fn returning clojure.test.check.generators/symbol-ns
 */
cljs.spec.gen.alpha.symbol_ns = ((function (g__13260__auto___13478){
return (function cljs$spec$gen$alpha$symbol_ns(var_args){
var args__8663__auto__ = [];
var len__8656__auto___13479 = arguments.length;
var i__8657__auto___13480 = (0);
while(true){
if((i__8657__auto___13480 < len__8656__auto___13479)){
args__8663__auto__.push((arguments[i__8657__auto___13480]));

var G__13481 = (i__8657__auto___13480 + (1));
i__8657__auto___13480 = G__13481;
continue;
} else {
}
break;
}

var argseq__8664__auto__ = ((((0) < args__8663__auto__.length))?(new cljs.core.IndexedSeq(args__8663__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.symbol_ns.cljs$core$IFn$_invoke$arity$variadic(argseq__8664__auto__);
});})(g__13260__auto___13478))
;

cljs.spec.gen.alpha.symbol_ns.cljs$core$IFn$_invoke$arity$variadic = ((function (g__13260__auto___13478){
return (function (args){
return cljs.core.deref.call(null,g__13260__auto___13478);
});})(g__13260__auto___13478))
;

cljs.spec.gen.alpha.symbol_ns.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.symbol_ns.cljs$lang$applyTo = ((function (g__13260__auto___13478){
return (function (seq13400){
return cljs.spec.gen.alpha.symbol_ns.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq13400));
});})(g__13260__auto___13478))
;


var g__13260__auto___13482 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.uuid !== 'undefined')){
return clojure.test.check.generators.uuid;
} else {
throw (new Error([cljs.core.str.cljs$core$IFn$_invoke$arity$1("Var "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","uuid","clojure.test.check.generators/uuid",1589373144,null)),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" does not exist, "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","uuid","clojure.test.check.generators/uuid",1589373144,null))),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" never required")].join('')));
}
}),null));
/**
 * Fn returning clojure.test.check.generators/uuid
 */
cljs.spec.gen.alpha.uuid = ((function (g__13260__auto___13482){
return (function cljs$spec$gen$alpha$uuid(var_args){
var args__8663__auto__ = [];
var len__8656__auto___13483 = arguments.length;
var i__8657__auto___13484 = (0);
while(true){
if((i__8657__auto___13484 < len__8656__auto___13483)){
args__8663__auto__.push((arguments[i__8657__auto___13484]));

var G__13485 = (i__8657__auto___13484 + (1));
i__8657__auto___13484 = G__13485;
continue;
} else {
}
break;
}

var argseq__8664__auto__ = ((((0) < args__8663__auto__.length))?(new cljs.core.IndexedSeq(args__8663__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.uuid.cljs$core$IFn$_invoke$arity$variadic(argseq__8664__auto__);
});})(g__13260__auto___13482))
;

cljs.spec.gen.alpha.uuid.cljs$core$IFn$_invoke$arity$variadic = ((function (g__13260__auto___13482){
return (function (args){
return cljs.core.deref.call(null,g__13260__auto___13482);
});})(g__13260__auto___13482))
;

cljs.spec.gen.alpha.uuid.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.uuid.cljs$lang$applyTo = ((function (g__13260__auto___13482){
return (function (seq13401){
return cljs.spec.gen.alpha.uuid.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq13401));
});})(g__13260__auto___13482))
;

/**
 * Returns a generator of a sequence catenated from results of
 * gens, each of which should generate something sequential.
 */
cljs.spec.gen.alpha.cat = (function cljs$spec$gen$alpha$cat(var_args){
var args__8663__auto__ = [];
var len__8656__auto___13488 = arguments.length;
var i__8657__auto___13489 = (0);
while(true){
if((i__8657__auto___13489 < len__8656__auto___13488)){
args__8663__auto__.push((arguments[i__8657__auto___13489]));

var G__13490 = (i__8657__auto___13489 + (1));
i__8657__auto___13489 = G__13490;
continue;
} else {
}
break;
}

var argseq__8664__auto__ = ((((0) < args__8663__auto__.length))?(new cljs.core.IndexedSeq(args__8663__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.cat.cljs$core$IFn$_invoke$arity$variadic(argseq__8664__auto__);
});

cljs.spec.gen.alpha.cat.cljs$core$IFn$_invoke$arity$variadic = (function (gens){
return cljs.spec.gen.alpha.fmap.call(null,(function (p1__13486_SHARP_){
return cljs.core.apply.call(null,cljs.core.concat,p1__13486_SHARP_);
}),cljs.core.apply.call(null,cljs.spec.gen.alpha.tuple,gens));
});

cljs.spec.gen.alpha.cat.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.cat.cljs$lang$applyTo = (function (seq13487){
return cljs.spec.gen.alpha.cat.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq13487));
});

cljs.spec.gen.alpha.qualified_QMARK_ = (function cljs$spec$gen$alpha$qualified_QMARK_(ident){
return !((cljs.core.namespace.call(null,ident) == null));
});
cljs.spec.gen.alpha.gen_builtins = (new cljs.core.Delay((function (){
var simple = cljs.spec.gen.alpha.simple_type_printable.call(null);
return cljs.core.PersistentHashMap.fromArrays([cljs.core.qualified_keyword_QMARK_,cljs.core.seq_QMARK_,cljs.core.vector_QMARK_,cljs.core.any_QMARK_,cljs.core.boolean_QMARK_,cljs.core.char_QMARK_,cljs.core.inst_QMARK_,cljs.core.simple_symbol_QMARK_,cljs.core.sequential_QMARK_,cljs.core.float_QMARK_,cljs.core.set_QMARK_,cljs.core.map_QMARK_,cljs.core.empty_QMARK_,cljs.core.string_QMARK_,cljs.core.double_QMARK_,cljs.core.int_QMARK_,cljs.core.associative_QMARK_,cljs.core.keyword_QMARK_,cljs.core.indexed_QMARK_,cljs.core.zero_QMARK_,cljs.core.simple_keyword_QMARK_,cljs.core.neg_int_QMARK_,cljs.core.nil_QMARK_,cljs.core.ident_QMARK_,cljs.core.qualified_ident_QMARK_,cljs.core.true_QMARK_,cljs.core.integer_QMARK_,cljs.core.nat_int_QMARK_,cljs.core.pos_int_QMARK_,cljs.core.uuid_QMARK_,cljs.core.false_QMARK_,cljs.core.list_QMARK_,cljs.core.simple_ident_QMARK_,cljs.core.number_QMARK_,cljs.core.qualified_symbol_QMARK_,cljs.core.seqable_QMARK_,cljs.core.symbol_QMARK_,cljs.core.coll_QMARK_],[cljs.spec.gen.alpha.such_that.call(null,cljs.spec.gen.alpha.qualified_QMARK_,cljs.spec.gen.alpha.keyword_ns.call(null)),cljs.spec.gen.alpha.list.call(null,simple),cljs.spec.gen.alpha.vector.call(null,simple),cljs.spec.gen.alpha.one_of.call(null,new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [cljs.spec.gen.alpha.return$.call(null,null),cljs.spec.gen.alpha.any_printable.call(null)], null)),cljs.spec.gen.alpha.boolean$.call(null),cljs.spec.gen.alpha.char$.call(null),cljs.spec.gen.alpha.fmap.call(null,((function (simple){
return (function (p1__13491_SHARP_){
return (new Date(p1__13491_SHARP_));
});})(simple))
,cljs.spec.gen.alpha.large_integer.call(null)),cljs.spec.gen.alpha.symbol.call(null),cljs.spec.gen.alpha.one_of.call(null,new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [cljs.spec.gen.alpha.list.call(null,simple),cljs.spec.gen.alpha.vector.call(null,simple)], null)),cljs.spec.gen.alpha.double$.call(null),cljs.spec.gen.alpha.set.call(null,simple),cljs.spec.gen.alpha.map.call(null,simple,simple),cljs.spec.gen.alpha.elements.call(null,new cljs.core.PersistentVector(null, 5, 5, cljs.core.PersistentVector.EMPTY_NODE, [null,cljs.core.List.EMPTY,cljs.core.PersistentVector.EMPTY,cljs.core.PersistentArrayMap.EMPTY,cljs.core.PersistentHashSet.EMPTY], null)),cljs.spec.gen.alpha.string_alphanumeric.call(null),cljs.spec.gen.alpha.double$.call(null),cljs.spec.gen.alpha.large_integer.call(null),cljs.spec.gen.alpha.one_of.call(null,new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [cljs.spec.gen.alpha.map.call(null,simple,simple),cljs.spec.gen.alpha.vector.call(null,simple)], null)),cljs.spec.gen.alpha.keyword_ns.call(null),cljs.spec.gen.alpha.vector.call(null,simple),cljs.spec.gen.alpha.return$.call(null,(0)),cljs.spec.gen.alpha.keyword.call(null),cljs.spec.gen.alpha.large_integer_STAR_.call(null,new cljs.core.PersistentArrayMap(null, 1, [new cljs.core.Keyword(null,"max","max",61366548),(-1)], null)),cljs.spec.gen.alpha.return$.call(null,null),cljs.spec.gen.alpha.one_of.call(null,new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [cljs.spec.gen.alpha.keyword_ns.call(null),cljs.spec.gen.alpha.symbol_ns.call(null)], null)),cljs.spec.gen.alpha.such_that.call(null,cljs.spec.gen.alpha.qualified_QMARK_,cljs.spec.gen.alpha.one_of.call(null,new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [cljs.spec.gen.alpha.keyword_ns.call(null),cljs.spec.gen.alpha.symbol_ns.call(null)], null))),cljs.spec.gen.alpha.return$.call(null,true),cljs.spec.gen.alpha.large_integer.call(null),cljs.spec.gen.alpha.large_integer_STAR_.call(null,new cljs.core.PersistentArrayMap(null, 1, [new cljs.core.Keyword(null,"min","min",444991522),(0)], null)),cljs.spec.gen.alpha.large_integer_STAR_.call(null,new cljs.core.PersistentArrayMap(null, 1, [new cljs.core.Keyword(null,"min","min",444991522),(1)], null)),cljs.spec.gen.alpha.uuid.call(null),cljs.spec.gen.alpha.return$.call(null,false),cljs.spec.gen.alpha.list.call(null,simple),cljs.spec.gen.alpha.one_of.call(null,new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [cljs.spec.gen.alpha.keyword.call(null),cljs.spec.gen.alpha.symbol.call(null)], null)),cljs.spec.gen.alpha.one_of.call(null,new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [cljs.spec.gen.alpha.large_integer.call(null),cljs.spec.gen.alpha.double$.call(null)], null)),cljs.spec.gen.alpha.such_that.call(null,cljs.spec.gen.alpha.qualified_QMARK_,cljs.spec.gen.alpha.symbol_ns.call(null)),cljs.spec.gen.alpha.one_of.call(null,new cljs.core.PersistentVector(null, 6, 5, cljs.core.PersistentVector.EMPTY_NODE, [cljs.spec.gen.alpha.return$.call(null,null),cljs.spec.gen.alpha.list.call(null,simple),cljs.spec.gen.alpha.vector.call(null,simple),cljs.spec.gen.alpha.map.call(null,simple,simple),cljs.spec.gen.alpha.set.call(null,simple),cljs.spec.gen.alpha.string_alphanumeric.call(null)], null)),cljs.spec.gen.alpha.symbol_ns.call(null),cljs.spec.gen.alpha.one_of.call(null,new cljs.core.PersistentVector(null, 4, 5, cljs.core.PersistentVector.EMPTY_NODE, [cljs.spec.gen.alpha.map.call(null,simple,simple),cljs.spec.gen.alpha.list.call(null,simple),cljs.spec.gen.alpha.vector.call(null,simple),cljs.spec.gen.alpha.set.call(null,simple)], null))]);
}),null));
/**
 * Given a predicate, returns a built-in generator if one exists.
 */
cljs.spec.gen.alpha.gen_for_pred = (function cljs$spec$gen$alpha$gen_for_pred(pred){
if(cljs.core.set_QMARK_.call(null,pred)){
return cljs.spec.gen.alpha.elements.call(null,pred);
} else {
return cljs.core.get.call(null,cljs.core.deref.call(null,cljs.spec.gen.alpha.gen_builtins),pred);
}
});
