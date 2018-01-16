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
var len__8656__auto___13596 = arguments.length;
var i__8657__auto___13597 = (0);
while(true){
if((i__8657__auto___13597 < len__8656__auto___13596)){
args__8663__auto__.push((arguments[i__8657__auto___13597]));

var G__13598 = (i__8657__auto___13597 + (1));
i__8657__auto___13597 = G__13598;
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

cljs.spec.gen.alpha.quick_check.cljs$lang$applyTo = (function (seq13595){
return cljs.spec.gen.alpha.quick_check.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq13595));
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
var len__8656__auto___13600 = arguments.length;
var i__8657__auto___13601 = (0);
while(true){
if((i__8657__auto___13601 < len__8656__auto___13600)){
args__8663__auto__.push((arguments[i__8657__auto___13601]));

var G__13602 = (i__8657__auto___13601 + (1));
i__8657__auto___13601 = G__13602;
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

cljs.spec.gen.alpha.for_all_STAR_.cljs$lang$applyTo = (function (seq13599){
return cljs.spec.gen.alpha.for_all_STAR_.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq13599));
});

var g_QMARK__13603 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.generator_QMARK_ !== 'undefined')){
return clojure.test.check.generators.generator_QMARK_;
} else {
throw (new Error([cljs.core.str.cljs$core$IFn$_invoke$arity$1("Var "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","generator?","clojure.test.check.generators/generator?",-1378210460,null)),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" does not exist, "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","generator?","clojure.test.check.generators/generator?",-1378210460,null))),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" never required")].join('')));
}
}),null));
var g_13604 = (new cljs.spec.gen.alpha.LazyVar(((function (g_QMARK__13603){
return (function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.generate !== 'undefined')){
return clojure.test.check.generators.generate;
} else {
throw (new Error([cljs.core.str.cljs$core$IFn$_invoke$arity$1("Var "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","generate","clojure.test.check.generators/generate",-690390711,null)),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" does not exist, "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","generate","clojure.test.check.generators/generate",-690390711,null))),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" never required")].join('')));
}
});})(g_QMARK__13603))
,null));
var mkg_13605 = (new cljs.spec.gen.alpha.LazyVar(((function (g_QMARK__13603,g_13604){
return (function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.__GT_Generator !== 'undefined')){
return clojure.test.check.generators.__GT_Generator;
} else {
throw (new Error([cljs.core.str.cljs$core$IFn$_invoke$arity$1("Var "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","->Generator","clojure.test.check.generators/->Generator",-1179475051,null)),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" does not exist, "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","->Generator","clojure.test.check.generators/->Generator",-1179475051,null))),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" never required")].join('')));
}
});})(g_QMARK__13603,g_13604))
,null));
cljs.spec.gen.alpha.generator_QMARK_ = ((function (g_QMARK__13603,g_13604,mkg_13605){
return (function cljs$spec$gen$alpha$generator_QMARK_(x){
return cljs.core.deref.call(null,g_QMARK__13603).call(null,x);
});})(g_QMARK__13603,g_13604,mkg_13605))
;

cljs.spec.gen.alpha.generator = ((function (g_QMARK__13603,g_13604,mkg_13605){
return (function cljs$spec$gen$alpha$generator(gfn){
return cljs.core.deref.call(null,mkg_13605).call(null,gfn);
});})(g_QMARK__13603,g_13604,mkg_13605))
;

/**
 * Generate a single value using generator.
 */
cljs.spec.gen.alpha.generate = ((function (g_QMARK__13603,g_13604,mkg_13605){
return (function cljs$spec$gen$alpha$generate(generator){
return cljs.core.deref.call(null,g_13604).call(null,generator);
});})(g_QMARK__13603,g_13604,mkg_13605))
;
cljs.spec.gen.alpha.delay_impl = (function cljs$spec$gen$alpha$delay_impl(gfnd){
return cljs.spec.gen.alpha.generator.call(null,(function (rnd,size){
return new cljs.core.Keyword(null,"gen","gen",142575302).cljs$core$IFn$_invoke$arity$1(cljs.core.deref.call(null,gfnd)).call(null,rnd,size);
}));
});
var g__8749__auto___13625 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.hash_map !== 'undefined')){
return clojure.test.check.generators.hash_map;
} else {
throw (new Error([cljs.core.str.cljs$core$IFn$_invoke$arity$1("Var "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","hash-map","clojure.test.check.generators/hash-map",1961346626,null)),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" does not exist, "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","hash-map","clojure.test.check.generators/hash-map",1961346626,null))),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" never required")].join('')));
}
}),null));
/**
 * Lazy loaded version of clojure.test.check.generators/hash-map
 */
cljs.spec.gen.alpha.hash_map = ((function (g__8749__auto___13625){
return (function cljs$spec$gen$alpha$hash_map(var_args){
var args__8663__auto__ = [];
var len__8656__auto___13626 = arguments.length;
var i__8657__auto___13627 = (0);
while(true){
if((i__8657__auto___13627 < len__8656__auto___13626)){
args__8663__auto__.push((arguments[i__8657__auto___13627]));

var G__13628 = (i__8657__auto___13627 + (1));
i__8657__auto___13627 = G__13628;
continue;
} else {
}
break;
}

var argseq__8664__auto__ = ((((0) < args__8663__auto__.length))?(new cljs.core.IndexedSeq(args__8663__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.hash_map.cljs$core$IFn$_invoke$arity$variadic(argseq__8664__auto__);
});})(g__8749__auto___13625))
;

cljs.spec.gen.alpha.hash_map.cljs$core$IFn$_invoke$arity$variadic = ((function (g__8749__auto___13625){
return (function (args){
return cljs.core.apply.call(null,cljs.core.deref.call(null,g__8749__auto___13625),args);
});})(g__8749__auto___13625))
;

cljs.spec.gen.alpha.hash_map.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.hash_map.cljs$lang$applyTo = ((function (g__8749__auto___13625){
return (function (seq13606){
return cljs.spec.gen.alpha.hash_map.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq13606));
});})(g__8749__auto___13625))
;


var g__8749__auto___13629 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.list !== 'undefined')){
return clojure.test.check.generators.list;
} else {
throw (new Error([cljs.core.str.cljs$core$IFn$_invoke$arity$1("Var "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","list","clojure.test.check.generators/list",506971058,null)),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" does not exist, "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","list","clojure.test.check.generators/list",506971058,null))),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" never required")].join('')));
}
}),null));
/**
 * Lazy loaded version of clojure.test.check.generators/list
 */
cljs.spec.gen.alpha.list = ((function (g__8749__auto___13629){
return (function cljs$spec$gen$alpha$list(var_args){
var args__8663__auto__ = [];
var len__8656__auto___13630 = arguments.length;
var i__8657__auto___13631 = (0);
while(true){
if((i__8657__auto___13631 < len__8656__auto___13630)){
args__8663__auto__.push((arguments[i__8657__auto___13631]));

var G__13632 = (i__8657__auto___13631 + (1));
i__8657__auto___13631 = G__13632;
continue;
} else {
}
break;
}

var argseq__8664__auto__ = ((((0) < args__8663__auto__.length))?(new cljs.core.IndexedSeq(args__8663__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.list.cljs$core$IFn$_invoke$arity$variadic(argseq__8664__auto__);
});})(g__8749__auto___13629))
;

cljs.spec.gen.alpha.list.cljs$core$IFn$_invoke$arity$variadic = ((function (g__8749__auto___13629){
return (function (args){
return cljs.core.apply.call(null,cljs.core.deref.call(null,g__8749__auto___13629),args);
});})(g__8749__auto___13629))
;

cljs.spec.gen.alpha.list.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.list.cljs$lang$applyTo = ((function (g__8749__auto___13629){
return (function (seq13607){
return cljs.spec.gen.alpha.list.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq13607));
});})(g__8749__auto___13629))
;


var g__8749__auto___13633 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.map !== 'undefined')){
return clojure.test.check.generators.map;
} else {
throw (new Error([cljs.core.str.cljs$core$IFn$_invoke$arity$1("Var "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","map","clojure.test.check.generators/map",45738796,null)),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" does not exist, "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","map","clojure.test.check.generators/map",45738796,null))),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" never required")].join('')));
}
}),null));
/**
 * Lazy loaded version of clojure.test.check.generators/map
 */
cljs.spec.gen.alpha.map = ((function (g__8749__auto___13633){
return (function cljs$spec$gen$alpha$map(var_args){
var args__8663__auto__ = [];
var len__8656__auto___13634 = arguments.length;
var i__8657__auto___13635 = (0);
while(true){
if((i__8657__auto___13635 < len__8656__auto___13634)){
args__8663__auto__.push((arguments[i__8657__auto___13635]));

var G__13636 = (i__8657__auto___13635 + (1));
i__8657__auto___13635 = G__13636;
continue;
} else {
}
break;
}

var argseq__8664__auto__ = ((((0) < args__8663__auto__.length))?(new cljs.core.IndexedSeq(args__8663__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.map.cljs$core$IFn$_invoke$arity$variadic(argseq__8664__auto__);
});})(g__8749__auto___13633))
;

cljs.spec.gen.alpha.map.cljs$core$IFn$_invoke$arity$variadic = ((function (g__8749__auto___13633){
return (function (args){
return cljs.core.apply.call(null,cljs.core.deref.call(null,g__8749__auto___13633),args);
});})(g__8749__auto___13633))
;

cljs.spec.gen.alpha.map.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.map.cljs$lang$applyTo = ((function (g__8749__auto___13633){
return (function (seq13608){
return cljs.spec.gen.alpha.map.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq13608));
});})(g__8749__auto___13633))
;


var g__8749__auto___13637 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.not_empty !== 'undefined')){
return clojure.test.check.generators.not_empty;
} else {
throw (new Error([cljs.core.str.cljs$core$IFn$_invoke$arity$1("Var "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","not-empty","clojure.test.check.generators/not-empty",-876211682,null)),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" does not exist, "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","not-empty","clojure.test.check.generators/not-empty",-876211682,null))),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" never required")].join('')));
}
}),null));
/**
 * Lazy loaded version of clojure.test.check.generators/not-empty
 */
cljs.spec.gen.alpha.not_empty = ((function (g__8749__auto___13637){
return (function cljs$spec$gen$alpha$not_empty(var_args){
var args__8663__auto__ = [];
var len__8656__auto___13638 = arguments.length;
var i__8657__auto___13639 = (0);
while(true){
if((i__8657__auto___13639 < len__8656__auto___13638)){
args__8663__auto__.push((arguments[i__8657__auto___13639]));

var G__13640 = (i__8657__auto___13639 + (1));
i__8657__auto___13639 = G__13640;
continue;
} else {
}
break;
}

var argseq__8664__auto__ = ((((0) < args__8663__auto__.length))?(new cljs.core.IndexedSeq(args__8663__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.not_empty.cljs$core$IFn$_invoke$arity$variadic(argseq__8664__auto__);
});})(g__8749__auto___13637))
;

cljs.spec.gen.alpha.not_empty.cljs$core$IFn$_invoke$arity$variadic = ((function (g__8749__auto___13637){
return (function (args){
return cljs.core.apply.call(null,cljs.core.deref.call(null,g__8749__auto___13637),args);
});})(g__8749__auto___13637))
;

cljs.spec.gen.alpha.not_empty.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.not_empty.cljs$lang$applyTo = ((function (g__8749__auto___13637){
return (function (seq13609){
return cljs.spec.gen.alpha.not_empty.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq13609));
});})(g__8749__auto___13637))
;


var g__8749__auto___13641 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.set !== 'undefined')){
return clojure.test.check.generators.set;
} else {
throw (new Error([cljs.core.str.cljs$core$IFn$_invoke$arity$1("Var "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","set","clojure.test.check.generators/set",-1027639543,null)),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" does not exist, "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","set","clojure.test.check.generators/set",-1027639543,null))),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" never required")].join('')));
}
}),null));
/**
 * Lazy loaded version of clojure.test.check.generators/set
 */
cljs.spec.gen.alpha.set = ((function (g__8749__auto___13641){
return (function cljs$spec$gen$alpha$set(var_args){
var args__8663__auto__ = [];
var len__8656__auto___13642 = arguments.length;
var i__8657__auto___13643 = (0);
while(true){
if((i__8657__auto___13643 < len__8656__auto___13642)){
args__8663__auto__.push((arguments[i__8657__auto___13643]));

var G__13644 = (i__8657__auto___13643 + (1));
i__8657__auto___13643 = G__13644;
continue;
} else {
}
break;
}

var argseq__8664__auto__ = ((((0) < args__8663__auto__.length))?(new cljs.core.IndexedSeq(args__8663__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.set.cljs$core$IFn$_invoke$arity$variadic(argseq__8664__auto__);
});})(g__8749__auto___13641))
;

cljs.spec.gen.alpha.set.cljs$core$IFn$_invoke$arity$variadic = ((function (g__8749__auto___13641){
return (function (args){
return cljs.core.apply.call(null,cljs.core.deref.call(null,g__8749__auto___13641),args);
});})(g__8749__auto___13641))
;

cljs.spec.gen.alpha.set.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.set.cljs$lang$applyTo = ((function (g__8749__auto___13641){
return (function (seq13610){
return cljs.spec.gen.alpha.set.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq13610));
});})(g__8749__auto___13641))
;


var g__8749__auto___13645 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.vector !== 'undefined')){
return clojure.test.check.generators.vector;
} else {
throw (new Error([cljs.core.str.cljs$core$IFn$_invoke$arity$1("Var "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","vector","clojure.test.check.generators/vector",1081775325,null)),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" does not exist, "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","vector","clojure.test.check.generators/vector",1081775325,null))),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" never required")].join('')));
}
}),null));
/**
 * Lazy loaded version of clojure.test.check.generators/vector
 */
cljs.spec.gen.alpha.vector = ((function (g__8749__auto___13645){
return (function cljs$spec$gen$alpha$vector(var_args){
var args__8663__auto__ = [];
var len__8656__auto___13646 = arguments.length;
var i__8657__auto___13647 = (0);
while(true){
if((i__8657__auto___13647 < len__8656__auto___13646)){
args__8663__auto__.push((arguments[i__8657__auto___13647]));

var G__13648 = (i__8657__auto___13647 + (1));
i__8657__auto___13647 = G__13648;
continue;
} else {
}
break;
}

var argseq__8664__auto__ = ((((0) < args__8663__auto__.length))?(new cljs.core.IndexedSeq(args__8663__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.vector.cljs$core$IFn$_invoke$arity$variadic(argseq__8664__auto__);
});})(g__8749__auto___13645))
;

cljs.spec.gen.alpha.vector.cljs$core$IFn$_invoke$arity$variadic = ((function (g__8749__auto___13645){
return (function (args){
return cljs.core.apply.call(null,cljs.core.deref.call(null,g__8749__auto___13645),args);
});})(g__8749__auto___13645))
;

cljs.spec.gen.alpha.vector.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.vector.cljs$lang$applyTo = ((function (g__8749__auto___13645){
return (function (seq13611){
return cljs.spec.gen.alpha.vector.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq13611));
});})(g__8749__auto___13645))
;


var g__8749__auto___13649 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.vector_distinct !== 'undefined')){
return clojure.test.check.generators.vector_distinct;
} else {
throw (new Error([cljs.core.str.cljs$core$IFn$_invoke$arity$1("Var "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","vector-distinct","clojure.test.check.generators/vector-distinct",1656877834,null)),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" does not exist, "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","vector-distinct","clojure.test.check.generators/vector-distinct",1656877834,null))),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" never required")].join('')));
}
}),null));
/**
 * Lazy loaded version of clojure.test.check.generators/vector-distinct
 */
cljs.spec.gen.alpha.vector_distinct = ((function (g__8749__auto___13649){
return (function cljs$spec$gen$alpha$vector_distinct(var_args){
var args__8663__auto__ = [];
var len__8656__auto___13650 = arguments.length;
var i__8657__auto___13651 = (0);
while(true){
if((i__8657__auto___13651 < len__8656__auto___13650)){
args__8663__auto__.push((arguments[i__8657__auto___13651]));

var G__13652 = (i__8657__auto___13651 + (1));
i__8657__auto___13651 = G__13652;
continue;
} else {
}
break;
}

var argseq__8664__auto__ = ((((0) < args__8663__auto__.length))?(new cljs.core.IndexedSeq(args__8663__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.vector_distinct.cljs$core$IFn$_invoke$arity$variadic(argseq__8664__auto__);
});})(g__8749__auto___13649))
;

cljs.spec.gen.alpha.vector_distinct.cljs$core$IFn$_invoke$arity$variadic = ((function (g__8749__auto___13649){
return (function (args){
return cljs.core.apply.call(null,cljs.core.deref.call(null,g__8749__auto___13649),args);
});})(g__8749__auto___13649))
;

cljs.spec.gen.alpha.vector_distinct.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.vector_distinct.cljs$lang$applyTo = ((function (g__8749__auto___13649){
return (function (seq13612){
return cljs.spec.gen.alpha.vector_distinct.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq13612));
});})(g__8749__auto___13649))
;


var g__8749__auto___13653 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.fmap !== 'undefined')){
return clojure.test.check.generators.fmap;
} else {
throw (new Error([cljs.core.str.cljs$core$IFn$_invoke$arity$1("Var "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","fmap","clojure.test.check.generators/fmap",1957997092,null)),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" does not exist, "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","fmap","clojure.test.check.generators/fmap",1957997092,null))),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" never required")].join('')));
}
}),null));
/**
 * Lazy loaded version of clojure.test.check.generators/fmap
 */
cljs.spec.gen.alpha.fmap = ((function (g__8749__auto___13653){
return (function cljs$spec$gen$alpha$fmap(var_args){
var args__8663__auto__ = [];
var len__8656__auto___13654 = arguments.length;
var i__8657__auto___13655 = (0);
while(true){
if((i__8657__auto___13655 < len__8656__auto___13654)){
args__8663__auto__.push((arguments[i__8657__auto___13655]));

var G__13656 = (i__8657__auto___13655 + (1));
i__8657__auto___13655 = G__13656;
continue;
} else {
}
break;
}

var argseq__8664__auto__ = ((((0) < args__8663__auto__.length))?(new cljs.core.IndexedSeq(args__8663__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.fmap.cljs$core$IFn$_invoke$arity$variadic(argseq__8664__auto__);
});})(g__8749__auto___13653))
;

cljs.spec.gen.alpha.fmap.cljs$core$IFn$_invoke$arity$variadic = ((function (g__8749__auto___13653){
return (function (args){
return cljs.core.apply.call(null,cljs.core.deref.call(null,g__8749__auto___13653),args);
});})(g__8749__auto___13653))
;

cljs.spec.gen.alpha.fmap.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.fmap.cljs$lang$applyTo = ((function (g__8749__auto___13653){
return (function (seq13613){
return cljs.spec.gen.alpha.fmap.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq13613));
});})(g__8749__auto___13653))
;


var g__8749__auto___13657 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.elements !== 'undefined')){
return clojure.test.check.generators.elements;
} else {
throw (new Error([cljs.core.str.cljs$core$IFn$_invoke$arity$1("Var "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","elements","clojure.test.check.generators/elements",438991326,null)),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" does not exist, "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","elements","clojure.test.check.generators/elements",438991326,null))),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" never required")].join('')));
}
}),null));
/**
 * Lazy loaded version of clojure.test.check.generators/elements
 */
cljs.spec.gen.alpha.elements = ((function (g__8749__auto___13657){
return (function cljs$spec$gen$alpha$elements(var_args){
var args__8663__auto__ = [];
var len__8656__auto___13658 = arguments.length;
var i__8657__auto___13659 = (0);
while(true){
if((i__8657__auto___13659 < len__8656__auto___13658)){
args__8663__auto__.push((arguments[i__8657__auto___13659]));

var G__13660 = (i__8657__auto___13659 + (1));
i__8657__auto___13659 = G__13660;
continue;
} else {
}
break;
}

var argseq__8664__auto__ = ((((0) < args__8663__auto__.length))?(new cljs.core.IndexedSeq(args__8663__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.elements.cljs$core$IFn$_invoke$arity$variadic(argseq__8664__auto__);
});})(g__8749__auto___13657))
;

cljs.spec.gen.alpha.elements.cljs$core$IFn$_invoke$arity$variadic = ((function (g__8749__auto___13657){
return (function (args){
return cljs.core.apply.call(null,cljs.core.deref.call(null,g__8749__auto___13657),args);
});})(g__8749__auto___13657))
;

cljs.spec.gen.alpha.elements.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.elements.cljs$lang$applyTo = ((function (g__8749__auto___13657){
return (function (seq13614){
return cljs.spec.gen.alpha.elements.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq13614));
});})(g__8749__auto___13657))
;


var g__8749__auto___13661 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.bind !== 'undefined')){
return clojure.test.check.generators.bind;
} else {
throw (new Error([cljs.core.str.cljs$core$IFn$_invoke$arity$1("Var "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","bind","clojure.test.check.generators/bind",-361313906,null)),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" does not exist, "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","bind","clojure.test.check.generators/bind",-361313906,null))),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" never required")].join('')));
}
}),null));
/**
 * Lazy loaded version of clojure.test.check.generators/bind
 */
cljs.spec.gen.alpha.bind = ((function (g__8749__auto___13661){
return (function cljs$spec$gen$alpha$bind(var_args){
var args__8663__auto__ = [];
var len__8656__auto___13662 = arguments.length;
var i__8657__auto___13663 = (0);
while(true){
if((i__8657__auto___13663 < len__8656__auto___13662)){
args__8663__auto__.push((arguments[i__8657__auto___13663]));

var G__13664 = (i__8657__auto___13663 + (1));
i__8657__auto___13663 = G__13664;
continue;
} else {
}
break;
}

var argseq__8664__auto__ = ((((0) < args__8663__auto__.length))?(new cljs.core.IndexedSeq(args__8663__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.bind.cljs$core$IFn$_invoke$arity$variadic(argseq__8664__auto__);
});})(g__8749__auto___13661))
;

cljs.spec.gen.alpha.bind.cljs$core$IFn$_invoke$arity$variadic = ((function (g__8749__auto___13661){
return (function (args){
return cljs.core.apply.call(null,cljs.core.deref.call(null,g__8749__auto___13661),args);
});})(g__8749__auto___13661))
;

cljs.spec.gen.alpha.bind.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.bind.cljs$lang$applyTo = ((function (g__8749__auto___13661){
return (function (seq13615){
return cljs.spec.gen.alpha.bind.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq13615));
});})(g__8749__auto___13661))
;


var g__8749__auto___13665 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.choose !== 'undefined')){
return clojure.test.check.generators.choose;
} else {
throw (new Error([cljs.core.str.cljs$core$IFn$_invoke$arity$1("Var "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","choose","clojure.test.check.generators/choose",909997832,null)),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" does not exist, "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","choose","clojure.test.check.generators/choose",909997832,null))),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" never required")].join('')));
}
}),null));
/**
 * Lazy loaded version of clojure.test.check.generators/choose
 */
cljs.spec.gen.alpha.choose = ((function (g__8749__auto___13665){
return (function cljs$spec$gen$alpha$choose(var_args){
var args__8663__auto__ = [];
var len__8656__auto___13666 = arguments.length;
var i__8657__auto___13667 = (0);
while(true){
if((i__8657__auto___13667 < len__8656__auto___13666)){
args__8663__auto__.push((arguments[i__8657__auto___13667]));

var G__13668 = (i__8657__auto___13667 + (1));
i__8657__auto___13667 = G__13668;
continue;
} else {
}
break;
}

var argseq__8664__auto__ = ((((0) < args__8663__auto__.length))?(new cljs.core.IndexedSeq(args__8663__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.choose.cljs$core$IFn$_invoke$arity$variadic(argseq__8664__auto__);
});})(g__8749__auto___13665))
;

cljs.spec.gen.alpha.choose.cljs$core$IFn$_invoke$arity$variadic = ((function (g__8749__auto___13665){
return (function (args){
return cljs.core.apply.call(null,cljs.core.deref.call(null,g__8749__auto___13665),args);
});})(g__8749__auto___13665))
;

cljs.spec.gen.alpha.choose.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.choose.cljs$lang$applyTo = ((function (g__8749__auto___13665){
return (function (seq13616){
return cljs.spec.gen.alpha.choose.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq13616));
});})(g__8749__auto___13665))
;


var g__8749__auto___13669 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.one_of !== 'undefined')){
return clojure.test.check.generators.one_of;
} else {
throw (new Error([cljs.core.str.cljs$core$IFn$_invoke$arity$1("Var "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","one-of","clojure.test.check.generators/one-of",-183339191,null)),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" does not exist, "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","one-of","clojure.test.check.generators/one-of",-183339191,null))),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" never required")].join('')));
}
}),null));
/**
 * Lazy loaded version of clojure.test.check.generators/one-of
 */
cljs.spec.gen.alpha.one_of = ((function (g__8749__auto___13669){
return (function cljs$spec$gen$alpha$one_of(var_args){
var args__8663__auto__ = [];
var len__8656__auto___13670 = arguments.length;
var i__8657__auto___13671 = (0);
while(true){
if((i__8657__auto___13671 < len__8656__auto___13670)){
args__8663__auto__.push((arguments[i__8657__auto___13671]));

var G__13672 = (i__8657__auto___13671 + (1));
i__8657__auto___13671 = G__13672;
continue;
} else {
}
break;
}

var argseq__8664__auto__ = ((((0) < args__8663__auto__.length))?(new cljs.core.IndexedSeq(args__8663__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.one_of.cljs$core$IFn$_invoke$arity$variadic(argseq__8664__auto__);
});})(g__8749__auto___13669))
;

cljs.spec.gen.alpha.one_of.cljs$core$IFn$_invoke$arity$variadic = ((function (g__8749__auto___13669){
return (function (args){
return cljs.core.apply.call(null,cljs.core.deref.call(null,g__8749__auto___13669),args);
});})(g__8749__auto___13669))
;

cljs.spec.gen.alpha.one_of.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.one_of.cljs$lang$applyTo = ((function (g__8749__auto___13669){
return (function (seq13617){
return cljs.spec.gen.alpha.one_of.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq13617));
});})(g__8749__auto___13669))
;


var g__8749__auto___13673 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.such_that !== 'undefined')){
return clojure.test.check.generators.such_that;
} else {
throw (new Error([cljs.core.str.cljs$core$IFn$_invoke$arity$1("Var "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","such-that","clojure.test.check.generators/such-that",-1754178732,null)),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" does not exist, "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","such-that","clojure.test.check.generators/such-that",-1754178732,null))),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" never required")].join('')));
}
}),null));
/**
 * Lazy loaded version of clojure.test.check.generators/such-that
 */
cljs.spec.gen.alpha.such_that = ((function (g__8749__auto___13673){
return (function cljs$spec$gen$alpha$such_that(var_args){
var args__8663__auto__ = [];
var len__8656__auto___13674 = arguments.length;
var i__8657__auto___13675 = (0);
while(true){
if((i__8657__auto___13675 < len__8656__auto___13674)){
args__8663__auto__.push((arguments[i__8657__auto___13675]));

var G__13676 = (i__8657__auto___13675 + (1));
i__8657__auto___13675 = G__13676;
continue;
} else {
}
break;
}

var argseq__8664__auto__ = ((((0) < args__8663__auto__.length))?(new cljs.core.IndexedSeq(args__8663__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.such_that.cljs$core$IFn$_invoke$arity$variadic(argseq__8664__auto__);
});})(g__8749__auto___13673))
;

cljs.spec.gen.alpha.such_that.cljs$core$IFn$_invoke$arity$variadic = ((function (g__8749__auto___13673){
return (function (args){
return cljs.core.apply.call(null,cljs.core.deref.call(null,g__8749__auto___13673),args);
});})(g__8749__auto___13673))
;

cljs.spec.gen.alpha.such_that.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.such_that.cljs$lang$applyTo = ((function (g__8749__auto___13673){
return (function (seq13618){
return cljs.spec.gen.alpha.such_that.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq13618));
});})(g__8749__auto___13673))
;


var g__8749__auto___13677 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.tuple !== 'undefined')){
return clojure.test.check.generators.tuple;
} else {
throw (new Error([cljs.core.str.cljs$core$IFn$_invoke$arity$1("Var "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","tuple","clojure.test.check.generators/tuple",-143711557,null)),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" does not exist, "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","tuple","clojure.test.check.generators/tuple",-143711557,null))),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" never required")].join('')));
}
}),null));
/**
 * Lazy loaded version of clojure.test.check.generators/tuple
 */
cljs.spec.gen.alpha.tuple = ((function (g__8749__auto___13677){
return (function cljs$spec$gen$alpha$tuple(var_args){
var args__8663__auto__ = [];
var len__8656__auto___13678 = arguments.length;
var i__8657__auto___13679 = (0);
while(true){
if((i__8657__auto___13679 < len__8656__auto___13678)){
args__8663__auto__.push((arguments[i__8657__auto___13679]));

var G__13680 = (i__8657__auto___13679 + (1));
i__8657__auto___13679 = G__13680;
continue;
} else {
}
break;
}

var argseq__8664__auto__ = ((((0) < args__8663__auto__.length))?(new cljs.core.IndexedSeq(args__8663__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.tuple.cljs$core$IFn$_invoke$arity$variadic(argseq__8664__auto__);
});})(g__8749__auto___13677))
;

cljs.spec.gen.alpha.tuple.cljs$core$IFn$_invoke$arity$variadic = ((function (g__8749__auto___13677){
return (function (args){
return cljs.core.apply.call(null,cljs.core.deref.call(null,g__8749__auto___13677),args);
});})(g__8749__auto___13677))
;

cljs.spec.gen.alpha.tuple.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.tuple.cljs$lang$applyTo = ((function (g__8749__auto___13677){
return (function (seq13619){
return cljs.spec.gen.alpha.tuple.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq13619));
});})(g__8749__auto___13677))
;


var g__8749__auto___13681 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.sample !== 'undefined')){
return clojure.test.check.generators.sample;
} else {
throw (new Error([cljs.core.str.cljs$core$IFn$_invoke$arity$1("Var "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","sample","clojure.test.check.generators/sample",-382944992,null)),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" does not exist, "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","sample","clojure.test.check.generators/sample",-382944992,null))),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" never required")].join('')));
}
}),null));
/**
 * Lazy loaded version of clojure.test.check.generators/sample
 */
cljs.spec.gen.alpha.sample = ((function (g__8749__auto___13681){
return (function cljs$spec$gen$alpha$sample(var_args){
var args__8663__auto__ = [];
var len__8656__auto___13682 = arguments.length;
var i__8657__auto___13683 = (0);
while(true){
if((i__8657__auto___13683 < len__8656__auto___13682)){
args__8663__auto__.push((arguments[i__8657__auto___13683]));

var G__13684 = (i__8657__auto___13683 + (1));
i__8657__auto___13683 = G__13684;
continue;
} else {
}
break;
}

var argseq__8664__auto__ = ((((0) < args__8663__auto__.length))?(new cljs.core.IndexedSeq(args__8663__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.sample.cljs$core$IFn$_invoke$arity$variadic(argseq__8664__auto__);
});})(g__8749__auto___13681))
;

cljs.spec.gen.alpha.sample.cljs$core$IFn$_invoke$arity$variadic = ((function (g__8749__auto___13681){
return (function (args){
return cljs.core.apply.call(null,cljs.core.deref.call(null,g__8749__auto___13681),args);
});})(g__8749__auto___13681))
;

cljs.spec.gen.alpha.sample.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.sample.cljs$lang$applyTo = ((function (g__8749__auto___13681){
return (function (seq13620){
return cljs.spec.gen.alpha.sample.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq13620));
});})(g__8749__auto___13681))
;


var g__8749__auto___13685 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.return$ !== 'undefined')){
return clojure.test.check.generators.return$;
} else {
throw (new Error([cljs.core.str.cljs$core$IFn$_invoke$arity$1("Var "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","return","clojure.test.check.generators/return",1744522038,null)),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" does not exist, "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","return","clojure.test.check.generators/return",1744522038,null))),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" never required")].join('')));
}
}),null));
/**
 * Lazy loaded version of clojure.test.check.generators/return
 */
cljs.spec.gen.alpha.return$ = ((function (g__8749__auto___13685){
return (function cljs$spec$gen$alpha$return(var_args){
var args__8663__auto__ = [];
var len__8656__auto___13686 = arguments.length;
var i__8657__auto___13687 = (0);
while(true){
if((i__8657__auto___13687 < len__8656__auto___13686)){
args__8663__auto__.push((arguments[i__8657__auto___13687]));

var G__13688 = (i__8657__auto___13687 + (1));
i__8657__auto___13687 = G__13688;
continue;
} else {
}
break;
}

var argseq__8664__auto__ = ((((0) < args__8663__auto__.length))?(new cljs.core.IndexedSeq(args__8663__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.return$.cljs$core$IFn$_invoke$arity$variadic(argseq__8664__auto__);
});})(g__8749__auto___13685))
;

cljs.spec.gen.alpha.return$.cljs$core$IFn$_invoke$arity$variadic = ((function (g__8749__auto___13685){
return (function (args){
return cljs.core.apply.call(null,cljs.core.deref.call(null,g__8749__auto___13685),args);
});})(g__8749__auto___13685))
;

cljs.spec.gen.alpha.return$.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.return$.cljs$lang$applyTo = ((function (g__8749__auto___13685){
return (function (seq13621){
return cljs.spec.gen.alpha.return$.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq13621));
});})(g__8749__auto___13685))
;


var g__8749__auto___13689 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.large_integer_STAR_ !== 'undefined')){
return clojure.test.check.generators.large_integer_STAR_;
} else {
throw (new Error([cljs.core.str.cljs$core$IFn$_invoke$arity$1("Var "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","large-integer*","clojure.test.check.generators/large-integer*",-437830670,null)),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" does not exist, "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","large-integer*","clojure.test.check.generators/large-integer*",-437830670,null))),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" never required")].join('')));
}
}),null));
/**
 * Lazy loaded version of clojure.test.check.generators/large-integer*
 */
cljs.spec.gen.alpha.large_integer_STAR_ = ((function (g__8749__auto___13689){
return (function cljs$spec$gen$alpha$large_integer_STAR_(var_args){
var args__8663__auto__ = [];
var len__8656__auto___13690 = arguments.length;
var i__8657__auto___13691 = (0);
while(true){
if((i__8657__auto___13691 < len__8656__auto___13690)){
args__8663__auto__.push((arguments[i__8657__auto___13691]));

var G__13692 = (i__8657__auto___13691 + (1));
i__8657__auto___13691 = G__13692;
continue;
} else {
}
break;
}

var argseq__8664__auto__ = ((((0) < args__8663__auto__.length))?(new cljs.core.IndexedSeq(args__8663__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.large_integer_STAR_.cljs$core$IFn$_invoke$arity$variadic(argseq__8664__auto__);
});})(g__8749__auto___13689))
;

cljs.spec.gen.alpha.large_integer_STAR_.cljs$core$IFn$_invoke$arity$variadic = ((function (g__8749__auto___13689){
return (function (args){
return cljs.core.apply.call(null,cljs.core.deref.call(null,g__8749__auto___13689),args);
});})(g__8749__auto___13689))
;

cljs.spec.gen.alpha.large_integer_STAR_.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.large_integer_STAR_.cljs$lang$applyTo = ((function (g__8749__auto___13689){
return (function (seq13622){
return cljs.spec.gen.alpha.large_integer_STAR_.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq13622));
});})(g__8749__auto___13689))
;


var g__8749__auto___13693 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.double_STAR_ !== 'undefined')){
return clojure.test.check.generators.double_STAR_;
} else {
throw (new Error([cljs.core.str.cljs$core$IFn$_invoke$arity$1("Var "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","double*","clojure.test.check.generators/double*",841542265,null)),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" does not exist, "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","double*","clojure.test.check.generators/double*",841542265,null))),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" never required")].join('')));
}
}),null));
/**
 * Lazy loaded version of clojure.test.check.generators/double*
 */
cljs.spec.gen.alpha.double_STAR_ = ((function (g__8749__auto___13693){
return (function cljs$spec$gen$alpha$double_STAR_(var_args){
var args__8663__auto__ = [];
var len__8656__auto___13694 = arguments.length;
var i__8657__auto___13695 = (0);
while(true){
if((i__8657__auto___13695 < len__8656__auto___13694)){
args__8663__auto__.push((arguments[i__8657__auto___13695]));

var G__13696 = (i__8657__auto___13695 + (1));
i__8657__auto___13695 = G__13696;
continue;
} else {
}
break;
}

var argseq__8664__auto__ = ((((0) < args__8663__auto__.length))?(new cljs.core.IndexedSeq(args__8663__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.double_STAR_.cljs$core$IFn$_invoke$arity$variadic(argseq__8664__auto__);
});})(g__8749__auto___13693))
;

cljs.spec.gen.alpha.double_STAR_.cljs$core$IFn$_invoke$arity$variadic = ((function (g__8749__auto___13693){
return (function (args){
return cljs.core.apply.call(null,cljs.core.deref.call(null,g__8749__auto___13693),args);
});})(g__8749__auto___13693))
;

cljs.spec.gen.alpha.double_STAR_.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.double_STAR_.cljs$lang$applyTo = ((function (g__8749__auto___13693){
return (function (seq13623){
return cljs.spec.gen.alpha.double_STAR_.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq13623));
});})(g__8749__auto___13693))
;


var g__8749__auto___13697 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.frequency !== 'undefined')){
return clojure.test.check.generators.frequency;
} else {
throw (new Error([cljs.core.str.cljs$core$IFn$_invoke$arity$1("Var "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","frequency","clojure.test.check.generators/frequency",2090703177,null)),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" does not exist, "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","frequency","clojure.test.check.generators/frequency",2090703177,null))),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" never required")].join('')));
}
}),null));
/**
 * Lazy loaded version of clojure.test.check.generators/frequency
 */
cljs.spec.gen.alpha.frequency = ((function (g__8749__auto___13697){
return (function cljs$spec$gen$alpha$frequency(var_args){
var args__8663__auto__ = [];
var len__8656__auto___13698 = arguments.length;
var i__8657__auto___13699 = (0);
while(true){
if((i__8657__auto___13699 < len__8656__auto___13698)){
args__8663__auto__.push((arguments[i__8657__auto___13699]));

var G__13700 = (i__8657__auto___13699 + (1));
i__8657__auto___13699 = G__13700;
continue;
} else {
}
break;
}

var argseq__8664__auto__ = ((((0) < args__8663__auto__.length))?(new cljs.core.IndexedSeq(args__8663__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.frequency.cljs$core$IFn$_invoke$arity$variadic(argseq__8664__auto__);
});})(g__8749__auto___13697))
;

cljs.spec.gen.alpha.frequency.cljs$core$IFn$_invoke$arity$variadic = ((function (g__8749__auto___13697){
return (function (args){
return cljs.core.apply.call(null,cljs.core.deref.call(null,g__8749__auto___13697),args);
});})(g__8749__auto___13697))
;

cljs.spec.gen.alpha.frequency.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.frequency.cljs$lang$applyTo = ((function (g__8749__auto___13697){
return (function (seq13624){
return cljs.spec.gen.alpha.frequency.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq13624));
});})(g__8749__auto___13697))
;

var g__8762__auto___13722 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.any !== 'undefined')){
return clojure.test.check.generators.any;
} else {
throw (new Error([cljs.core.str.cljs$core$IFn$_invoke$arity$1("Var "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","any","clojure.test.check.generators/any",1883743710,null)),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" does not exist, "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","any","clojure.test.check.generators/any",1883743710,null))),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" never required")].join('')));
}
}),null));
/**
 * Fn returning clojure.test.check.generators/any
 */
cljs.spec.gen.alpha.any = ((function (g__8762__auto___13722){
return (function cljs$spec$gen$alpha$any(var_args){
var args__8663__auto__ = [];
var len__8656__auto___13723 = arguments.length;
var i__8657__auto___13724 = (0);
while(true){
if((i__8657__auto___13724 < len__8656__auto___13723)){
args__8663__auto__.push((arguments[i__8657__auto___13724]));

var G__13725 = (i__8657__auto___13724 + (1));
i__8657__auto___13724 = G__13725;
continue;
} else {
}
break;
}

var argseq__8664__auto__ = ((((0) < args__8663__auto__.length))?(new cljs.core.IndexedSeq(args__8663__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.any.cljs$core$IFn$_invoke$arity$variadic(argseq__8664__auto__);
});})(g__8762__auto___13722))
;

cljs.spec.gen.alpha.any.cljs$core$IFn$_invoke$arity$variadic = ((function (g__8762__auto___13722){
return (function (args){
return cljs.core.deref.call(null,g__8762__auto___13722);
});})(g__8762__auto___13722))
;

cljs.spec.gen.alpha.any.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.any.cljs$lang$applyTo = ((function (g__8762__auto___13722){
return (function (seq13701){
return cljs.spec.gen.alpha.any.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq13701));
});})(g__8762__auto___13722))
;


var g__8762__auto___13726 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.any_printable !== 'undefined')){
return clojure.test.check.generators.any_printable;
} else {
throw (new Error([cljs.core.str.cljs$core$IFn$_invoke$arity$1("Var "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","any-printable","clojure.test.check.generators/any-printable",-1570493991,null)),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" does not exist, "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","any-printable","clojure.test.check.generators/any-printable",-1570493991,null))),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" never required")].join('')));
}
}),null));
/**
 * Fn returning clojure.test.check.generators/any-printable
 */
cljs.spec.gen.alpha.any_printable = ((function (g__8762__auto___13726){
return (function cljs$spec$gen$alpha$any_printable(var_args){
var args__8663__auto__ = [];
var len__8656__auto___13727 = arguments.length;
var i__8657__auto___13728 = (0);
while(true){
if((i__8657__auto___13728 < len__8656__auto___13727)){
args__8663__auto__.push((arguments[i__8657__auto___13728]));

var G__13729 = (i__8657__auto___13728 + (1));
i__8657__auto___13728 = G__13729;
continue;
} else {
}
break;
}

var argseq__8664__auto__ = ((((0) < args__8663__auto__.length))?(new cljs.core.IndexedSeq(args__8663__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.any_printable.cljs$core$IFn$_invoke$arity$variadic(argseq__8664__auto__);
});})(g__8762__auto___13726))
;

cljs.spec.gen.alpha.any_printable.cljs$core$IFn$_invoke$arity$variadic = ((function (g__8762__auto___13726){
return (function (args){
return cljs.core.deref.call(null,g__8762__auto___13726);
});})(g__8762__auto___13726))
;

cljs.spec.gen.alpha.any_printable.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.any_printable.cljs$lang$applyTo = ((function (g__8762__auto___13726){
return (function (seq13702){
return cljs.spec.gen.alpha.any_printable.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq13702));
});})(g__8762__auto___13726))
;


var g__8762__auto___13730 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.boolean$ !== 'undefined')){
return clojure.test.check.generators.boolean$;
} else {
throw (new Error([cljs.core.str.cljs$core$IFn$_invoke$arity$1("Var "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","boolean","clojure.test.check.generators/boolean",1586992347,null)),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" does not exist, "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","boolean","clojure.test.check.generators/boolean",1586992347,null))),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" never required")].join('')));
}
}),null));
/**
 * Fn returning clojure.test.check.generators/boolean
 */
cljs.spec.gen.alpha.boolean$ = ((function (g__8762__auto___13730){
return (function cljs$spec$gen$alpha$boolean(var_args){
var args__8663__auto__ = [];
var len__8656__auto___13731 = arguments.length;
var i__8657__auto___13732 = (0);
while(true){
if((i__8657__auto___13732 < len__8656__auto___13731)){
args__8663__auto__.push((arguments[i__8657__auto___13732]));

var G__13733 = (i__8657__auto___13732 + (1));
i__8657__auto___13732 = G__13733;
continue;
} else {
}
break;
}

var argseq__8664__auto__ = ((((0) < args__8663__auto__.length))?(new cljs.core.IndexedSeq(args__8663__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.boolean$.cljs$core$IFn$_invoke$arity$variadic(argseq__8664__auto__);
});})(g__8762__auto___13730))
;

cljs.spec.gen.alpha.boolean$.cljs$core$IFn$_invoke$arity$variadic = ((function (g__8762__auto___13730){
return (function (args){
return cljs.core.deref.call(null,g__8762__auto___13730);
});})(g__8762__auto___13730))
;

cljs.spec.gen.alpha.boolean$.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.boolean$.cljs$lang$applyTo = ((function (g__8762__auto___13730){
return (function (seq13703){
return cljs.spec.gen.alpha.boolean$.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq13703));
});})(g__8762__auto___13730))
;


var g__8762__auto___13734 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.char$ !== 'undefined')){
return clojure.test.check.generators.char$;
} else {
throw (new Error([cljs.core.str.cljs$core$IFn$_invoke$arity$1("Var "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","char","clojure.test.check.generators/char",-1426343459,null)),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" does not exist, "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","char","clojure.test.check.generators/char",-1426343459,null))),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" never required")].join('')));
}
}),null));
/**
 * Fn returning clojure.test.check.generators/char
 */
cljs.spec.gen.alpha.char$ = ((function (g__8762__auto___13734){
return (function cljs$spec$gen$alpha$char(var_args){
var args__8663__auto__ = [];
var len__8656__auto___13735 = arguments.length;
var i__8657__auto___13736 = (0);
while(true){
if((i__8657__auto___13736 < len__8656__auto___13735)){
args__8663__auto__.push((arguments[i__8657__auto___13736]));

var G__13737 = (i__8657__auto___13736 + (1));
i__8657__auto___13736 = G__13737;
continue;
} else {
}
break;
}

var argseq__8664__auto__ = ((((0) < args__8663__auto__.length))?(new cljs.core.IndexedSeq(args__8663__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.char$.cljs$core$IFn$_invoke$arity$variadic(argseq__8664__auto__);
});})(g__8762__auto___13734))
;

cljs.spec.gen.alpha.char$.cljs$core$IFn$_invoke$arity$variadic = ((function (g__8762__auto___13734){
return (function (args){
return cljs.core.deref.call(null,g__8762__auto___13734);
});})(g__8762__auto___13734))
;

cljs.spec.gen.alpha.char$.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.char$.cljs$lang$applyTo = ((function (g__8762__auto___13734){
return (function (seq13704){
return cljs.spec.gen.alpha.char$.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq13704));
});})(g__8762__auto___13734))
;


var g__8762__auto___13738 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.char_alpha !== 'undefined')){
return clojure.test.check.generators.char_alpha;
} else {
throw (new Error([cljs.core.str.cljs$core$IFn$_invoke$arity$1("Var "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","char-alpha","clojure.test.check.generators/char-alpha",615785796,null)),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" does not exist, "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","char-alpha","clojure.test.check.generators/char-alpha",615785796,null))),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" never required")].join('')));
}
}),null));
/**
 * Fn returning clojure.test.check.generators/char-alpha
 */
cljs.spec.gen.alpha.char_alpha = ((function (g__8762__auto___13738){
return (function cljs$spec$gen$alpha$char_alpha(var_args){
var args__8663__auto__ = [];
var len__8656__auto___13739 = arguments.length;
var i__8657__auto___13740 = (0);
while(true){
if((i__8657__auto___13740 < len__8656__auto___13739)){
args__8663__auto__.push((arguments[i__8657__auto___13740]));

var G__13741 = (i__8657__auto___13740 + (1));
i__8657__auto___13740 = G__13741;
continue;
} else {
}
break;
}

var argseq__8664__auto__ = ((((0) < args__8663__auto__.length))?(new cljs.core.IndexedSeq(args__8663__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.char_alpha.cljs$core$IFn$_invoke$arity$variadic(argseq__8664__auto__);
});})(g__8762__auto___13738))
;

cljs.spec.gen.alpha.char_alpha.cljs$core$IFn$_invoke$arity$variadic = ((function (g__8762__auto___13738){
return (function (args){
return cljs.core.deref.call(null,g__8762__auto___13738);
});})(g__8762__auto___13738))
;

cljs.spec.gen.alpha.char_alpha.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.char_alpha.cljs$lang$applyTo = ((function (g__8762__auto___13738){
return (function (seq13705){
return cljs.spec.gen.alpha.char_alpha.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq13705));
});})(g__8762__auto___13738))
;


var g__8762__auto___13742 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.char_alphanumeric !== 'undefined')){
return clojure.test.check.generators.char_alphanumeric;
} else {
throw (new Error([cljs.core.str.cljs$core$IFn$_invoke$arity$1("Var "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","char-alphanumeric","clojure.test.check.generators/char-alphanumeric",1383091431,null)),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" does not exist, "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","char-alphanumeric","clojure.test.check.generators/char-alphanumeric",1383091431,null))),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" never required")].join('')));
}
}),null));
/**
 * Fn returning clojure.test.check.generators/char-alphanumeric
 */
cljs.spec.gen.alpha.char_alphanumeric = ((function (g__8762__auto___13742){
return (function cljs$spec$gen$alpha$char_alphanumeric(var_args){
var args__8663__auto__ = [];
var len__8656__auto___13743 = arguments.length;
var i__8657__auto___13744 = (0);
while(true){
if((i__8657__auto___13744 < len__8656__auto___13743)){
args__8663__auto__.push((arguments[i__8657__auto___13744]));

var G__13745 = (i__8657__auto___13744 + (1));
i__8657__auto___13744 = G__13745;
continue;
} else {
}
break;
}

var argseq__8664__auto__ = ((((0) < args__8663__auto__.length))?(new cljs.core.IndexedSeq(args__8663__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.char_alphanumeric.cljs$core$IFn$_invoke$arity$variadic(argseq__8664__auto__);
});})(g__8762__auto___13742))
;

cljs.spec.gen.alpha.char_alphanumeric.cljs$core$IFn$_invoke$arity$variadic = ((function (g__8762__auto___13742){
return (function (args){
return cljs.core.deref.call(null,g__8762__auto___13742);
});})(g__8762__auto___13742))
;

cljs.spec.gen.alpha.char_alphanumeric.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.char_alphanumeric.cljs$lang$applyTo = ((function (g__8762__auto___13742){
return (function (seq13706){
return cljs.spec.gen.alpha.char_alphanumeric.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq13706));
});})(g__8762__auto___13742))
;


var g__8762__auto___13746 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.char_ascii !== 'undefined')){
return clojure.test.check.generators.char_ascii;
} else {
throw (new Error([cljs.core.str.cljs$core$IFn$_invoke$arity$1("Var "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","char-ascii","clojure.test.check.generators/char-ascii",-899908538,null)),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" does not exist, "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","char-ascii","clojure.test.check.generators/char-ascii",-899908538,null))),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" never required")].join('')));
}
}),null));
/**
 * Fn returning clojure.test.check.generators/char-ascii
 */
cljs.spec.gen.alpha.char_ascii = ((function (g__8762__auto___13746){
return (function cljs$spec$gen$alpha$char_ascii(var_args){
var args__8663__auto__ = [];
var len__8656__auto___13747 = arguments.length;
var i__8657__auto___13748 = (0);
while(true){
if((i__8657__auto___13748 < len__8656__auto___13747)){
args__8663__auto__.push((arguments[i__8657__auto___13748]));

var G__13749 = (i__8657__auto___13748 + (1));
i__8657__auto___13748 = G__13749;
continue;
} else {
}
break;
}

var argseq__8664__auto__ = ((((0) < args__8663__auto__.length))?(new cljs.core.IndexedSeq(args__8663__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.char_ascii.cljs$core$IFn$_invoke$arity$variadic(argseq__8664__auto__);
});})(g__8762__auto___13746))
;

cljs.spec.gen.alpha.char_ascii.cljs$core$IFn$_invoke$arity$variadic = ((function (g__8762__auto___13746){
return (function (args){
return cljs.core.deref.call(null,g__8762__auto___13746);
});})(g__8762__auto___13746))
;

cljs.spec.gen.alpha.char_ascii.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.char_ascii.cljs$lang$applyTo = ((function (g__8762__auto___13746){
return (function (seq13707){
return cljs.spec.gen.alpha.char_ascii.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq13707));
});})(g__8762__auto___13746))
;


var g__8762__auto___13750 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.double$ !== 'undefined')){
return clojure.test.check.generators.double$;
} else {
throw (new Error([cljs.core.str.cljs$core$IFn$_invoke$arity$1("Var "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","double","clojure.test.check.generators/double",668331090,null)),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" does not exist, "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","double","clojure.test.check.generators/double",668331090,null))),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" never required")].join('')));
}
}),null));
/**
 * Fn returning clojure.test.check.generators/double
 */
cljs.spec.gen.alpha.double$ = ((function (g__8762__auto___13750){
return (function cljs$spec$gen$alpha$double(var_args){
var args__8663__auto__ = [];
var len__8656__auto___13751 = arguments.length;
var i__8657__auto___13752 = (0);
while(true){
if((i__8657__auto___13752 < len__8656__auto___13751)){
args__8663__auto__.push((arguments[i__8657__auto___13752]));

var G__13753 = (i__8657__auto___13752 + (1));
i__8657__auto___13752 = G__13753;
continue;
} else {
}
break;
}

var argseq__8664__auto__ = ((((0) < args__8663__auto__.length))?(new cljs.core.IndexedSeq(args__8663__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.double$.cljs$core$IFn$_invoke$arity$variadic(argseq__8664__auto__);
});})(g__8762__auto___13750))
;

cljs.spec.gen.alpha.double$.cljs$core$IFn$_invoke$arity$variadic = ((function (g__8762__auto___13750){
return (function (args){
return cljs.core.deref.call(null,g__8762__auto___13750);
});})(g__8762__auto___13750))
;

cljs.spec.gen.alpha.double$.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.double$.cljs$lang$applyTo = ((function (g__8762__auto___13750){
return (function (seq13708){
return cljs.spec.gen.alpha.double$.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq13708));
});})(g__8762__auto___13750))
;


var g__8762__auto___13754 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.int$ !== 'undefined')){
return clojure.test.check.generators.int$;
} else {
throw (new Error([cljs.core.str.cljs$core$IFn$_invoke$arity$1("Var "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","int","clojure.test.check.generators/int",1756228469,null)),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" does not exist, "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","int","clojure.test.check.generators/int",1756228469,null))),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" never required")].join('')));
}
}),null));
/**
 * Fn returning clojure.test.check.generators/int
 */
cljs.spec.gen.alpha.int$ = ((function (g__8762__auto___13754){
return (function cljs$spec$gen$alpha$int(var_args){
var args__8663__auto__ = [];
var len__8656__auto___13755 = arguments.length;
var i__8657__auto___13756 = (0);
while(true){
if((i__8657__auto___13756 < len__8656__auto___13755)){
args__8663__auto__.push((arguments[i__8657__auto___13756]));

var G__13757 = (i__8657__auto___13756 + (1));
i__8657__auto___13756 = G__13757;
continue;
} else {
}
break;
}

var argseq__8664__auto__ = ((((0) < args__8663__auto__.length))?(new cljs.core.IndexedSeq(args__8663__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.int$.cljs$core$IFn$_invoke$arity$variadic(argseq__8664__auto__);
});})(g__8762__auto___13754))
;

cljs.spec.gen.alpha.int$.cljs$core$IFn$_invoke$arity$variadic = ((function (g__8762__auto___13754){
return (function (args){
return cljs.core.deref.call(null,g__8762__auto___13754);
});})(g__8762__auto___13754))
;

cljs.spec.gen.alpha.int$.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.int$.cljs$lang$applyTo = ((function (g__8762__auto___13754){
return (function (seq13709){
return cljs.spec.gen.alpha.int$.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq13709));
});})(g__8762__auto___13754))
;


var g__8762__auto___13758 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.keyword !== 'undefined')){
return clojure.test.check.generators.keyword;
} else {
throw (new Error([cljs.core.str.cljs$core$IFn$_invoke$arity$1("Var "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","keyword","clojure.test.check.generators/keyword",24530530,null)),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" does not exist, "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","keyword","clojure.test.check.generators/keyword",24530530,null))),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" never required")].join('')));
}
}),null));
/**
 * Fn returning clojure.test.check.generators/keyword
 */
cljs.spec.gen.alpha.keyword = ((function (g__8762__auto___13758){
return (function cljs$spec$gen$alpha$keyword(var_args){
var args__8663__auto__ = [];
var len__8656__auto___13759 = arguments.length;
var i__8657__auto___13760 = (0);
while(true){
if((i__8657__auto___13760 < len__8656__auto___13759)){
args__8663__auto__.push((arguments[i__8657__auto___13760]));

var G__13761 = (i__8657__auto___13760 + (1));
i__8657__auto___13760 = G__13761;
continue;
} else {
}
break;
}

var argseq__8664__auto__ = ((((0) < args__8663__auto__.length))?(new cljs.core.IndexedSeq(args__8663__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.keyword.cljs$core$IFn$_invoke$arity$variadic(argseq__8664__auto__);
});})(g__8762__auto___13758))
;

cljs.spec.gen.alpha.keyword.cljs$core$IFn$_invoke$arity$variadic = ((function (g__8762__auto___13758){
return (function (args){
return cljs.core.deref.call(null,g__8762__auto___13758);
});})(g__8762__auto___13758))
;

cljs.spec.gen.alpha.keyword.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.keyword.cljs$lang$applyTo = ((function (g__8762__auto___13758){
return (function (seq13710){
return cljs.spec.gen.alpha.keyword.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq13710));
});})(g__8762__auto___13758))
;


var g__8762__auto___13762 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.keyword_ns !== 'undefined')){
return clojure.test.check.generators.keyword_ns;
} else {
throw (new Error([cljs.core.str.cljs$core$IFn$_invoke$arity$1("Var "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","keyword-ns","clojure.test.check.generators/keyword-ns",-1492628482,null)),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" does not exist, "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","keyword-ns","clojure.test.check.generators/keyword-ns",-1492628482,null))),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" never required")].join('')));
}
}),null));
/**
 * Fn returning clojure.test.check.generators/keyword-ns
 */
cljs.spec.gen.alpha.keyword_ns = ((function (g__8762__auto___13762){
return (function cljs$spec$gen$alpha$keyword_ns(var_args){
var args__8663__auto__ = [];
var len__8656__auto___13763 = arguments.length;
var i__8657__auto___13764 = (0);
while(true){
if((i__8657__auto___13764 < len__8656__auto___13763)){
args__8663__auto__.push((arguments[i__8657__auto___13764]));

var G__13765 = (i__8657__auto___13764 + (1));
i__8657__auto___13764 = G__13765;
continue;
} else {
}
break;
}

var argseq__8664__auto__ = ((((0) < args__8663__auto__.length))?(new cljs.core.IndexedSeq(args__8663__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.keyword_ns.cljs$core$IFn$_invoke$arity$variadic(argseq__8664__auto__);
});})(g__8762__auto___13762))
;

cljs.spec.gen.alpha.keyword_ns.cljs$core$IFn$_invoke$arity$variadic = ((function (g__8762__auto___13762){
return (function (args){
return cljs.core.deref.call(null,g__8762__auto___13762);
});})(g__8762__auto___13762))
;

cljs.spec.gen.alpha.keyword_ns.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.keyword_ns.cljs$lang$applyTo = ((function (g__8762__auto___13762){
return (function (seq13711){
return cljs.spec.gen.alpha.keyword_ns.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq13711));
});})(g__8762__auto___13762))
;


var g__8762__auto___13766 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.large_integer !== 'undefined')){
return clojure.test.check.generators.large_integer;
} else {
throw (new Error([cljs.core.str.cljs$core$IFn$_invoke$arity$1("Var "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","large-integer","clojure.test.check.generators/large-integer",-865967138,null)),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" does not exist, "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","large-integer","clojure.test.check.generators/large-integer",-865967138,null))),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" never required")].join('')));
}
}),null));
/**
 * Fn returning clojure.test.check.generators/large-integer
 */
cljs.spec.gen.alpha.large_integer = ((function (g__8762__auto___13766){
return (function cljs$spec$gen$alpha$large_integer(var_args){
var args__8663__auto__ = [];
var len__8656__auto___13767 = arguments.length;
var i__8657__auto___13768 = (0);
while(true){
if((i__8657__auto___13768 < len__8656__auto___13767)){
args__8663__auto__.push((arguments[i__8657__auto___13768]));

var G__13769 = (i__8657__auto___13768 + (1));
i__8657__auto___13768 = G__13769;
continue;
} else {
}
break;
}

var argseq__8664__auto__ = ((((0) < args__8663__auto__.length))?(new cljs.core.IndexedSeq(args__8663__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.large_integer.cljs$core$IFn$_invoke$arity$variadic(argseq__8664__auto__);
});})(g__8762__auto___13766))
;

cljs.spec.gen.alpha.large_integer.cljs$core$IFn$_invoke$arity$variadic = ((function (g__8762__auto___13766){
return (function (args){
return cljs.core.deref.call(null,g__8762__auto___13766);
});})(g__8762__auto___13766))
;

cljs.spec.gen.alpha.large_integer.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.large_integer.cljs$lang$applyTo = ((function (g__8762__auto___13766){
return (function (seq13712){
return cljs.spec.gen.alpha.large_integer.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq13712));
});})(g__8762__auto___13766))
;


var g__8762__auto___13770 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.ratio !== 'undefined')){
return clojure.test.check.generators.ratio;
} else {
throw (new Error([cljs.core.str.cljs$core$IFn$_invoke$arity$1("Var "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","ratio","clojure.test.check.generators/ratio",1540966915,null)),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" does not exist, "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","ratio","clojure.test.check.generators/ratio",1540966915,null))),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" never required")].join('')));
}
}),null));
/**
 * Fn returning clojure.test.check.generators/ratio
 */
cljs.spec.gen.alpha.ratio = ((function (g__8762__auto___13770){
return (function cljs$spec$gen$alpha$ratio(var_args){
var args__8663__auto__ = [];
var len__8656__auto___13771 = arguments.length;
var i__8657__auto___13772 = (0);
while(true){
if((i__8657__auto___13772 < len__8656__auto___13771)){
args__8663__auto__.push((arguments[i__8657__auto___13772]));

var G__13773 = (i__8657__auto___13772 + (1));
i__8657__auto___13772 = G__13773;
continue;
} else {
}
break;
}

var argseq__8664__auto__ = ((((0) < args__8663__auto__.length))?(new cljs.core.IndexedSeq(args__8663__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.ratio.cljs$core$IFn$_invoke$arity$variadic(argseq__8664__auto__);
});})(g__8762__auto___13770))
;

cljs.spec.gen.alpha.ratio.cljs$core$IFn$_invoke$arity$variadic = ((function (g__8762__auto___13770){
return (function (args){
return cljs.core.deref.call(null,g__8762__auto___13770);
});})(g__8762__auto___13770))
;

cljs.spec.gen.alpha.ratio.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.ratio.cljs$lang$applyTo = ((function (g__8762__auto___13770){
return (function (seq13713){
return cljs.spec.gen.alpha.ratio.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq13713));
});})(g__8762__auto___13770))
;


var g__8762__auto___13774 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.simple_type !== 'undefined')){
return clojure.test.check.generators.simple_type;
} else {
throw (new Error([cljs.core.str.cljs$core$IFn$_invoke$arity$1("Var "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","simple-type","clojure.test.check.generators/simple-type",892572284,null)),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" does not exist, "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","simple-type","clojure.test.check.generators/simple-type",892572284,null))),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" never required")].join('')));
}
}),null));
/**
 * Fn returning clojure.test.check.generators/simple-type
 */
cljs.spec.gen.alpha.simple_type = ((function (g__8762__auto___13774){
return (function cljs$spec$gen$alpha$simple_type(var_args){
var args__8663__auto__ = [];
var len__8656__auto___13775 = arguments.length;
var i__8657__auto___13776 = (0);
while(true){
if((i__8657__auto___13776 < len__8656__auto___13775)){
args__8663__auto__.push((arguments[i__8657__auto___13776]));

var G__13777 = (i__8657__auto___13776 + (1));
i__8657__auto___13776 = G__13777;
continue;
} else {
}
break;
}

var argseq__8664__auto__ = ((((0) < args__8663__auto__.length))?(new cljs.core.IndexedSeq(args__8663__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.simple_type.cljs$core$IFn$_invoke$arity$variadic(argseq__8664__auto__);
});})(g__8762__auto___13774))
;

cljs.spec.gen.alpha.simple_type.cljs$core$IFn$_invoke$arity$variadic = ((function (g__8762__auto___13774){
return (function (args){
return cljs.core.deref.call(null,g__8762__auto___13774);
});})(g__8762__auto___13774))
;

cljs.spec.gen.alpha.simple_type.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.simple_type.cljs$lang$applyTo = ((function (g__8762__auto___13774){
return (function (seq13714){
return cljs.spec.gen.alpha.simple_type.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq13714));
});})(g__8762__auto___13774))
;


var g__8762__auto___13778 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.simple_type_printable !== 'undefined')){
return clojure.test.check.generators.simple_type_printable;
} else {
throw (new Error([cljs.core.str.cljs$core$IFn$_invoke$arity$1("Var "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","simple-type-printable","clojure.test.check.generators/simple-type-printable",-58489962,null)),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" does not exist, "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","simple-type-printable","clojure.test.check.generators/simple-type-printable",-58489962,null))),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" never required")].join('')));
}
}),null));
/**
 * Fn returning clojure.test.check.generators/simple-type-printable
 */
cljs.spec.gen.alpha.simple_type_printable = ((function (g__8762__auto___13778){
return (function cljs$spec$gen$alpha$simple_type_printable(var_args){
var args__8663__auto__ = [];
var len__8656__auto___13779 = arguments.length;
var i__8657__auto___13780 = (0);
while(true){
if((i__8657__auto___13780 < len__8656__auto___13779)){
args__8663__auto__.push((arguments[i__8657__auto___13780]));

var G__13781 = (i__8657__auto___13780 + (1));
i__8657__auto___13780 = G__13781;
continue;
} else {
}
break;
}

var argseq__8664__auto__ = ((((0) < args__8663__auto__.length))?(new cljs.core.IndexedSeq(args__8663__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.simple_type_printable.cljs$core$IFn$_invoke$arity$variadic(argseq__8664__auto__);
});})(g__8762__auto___13778))
;

cljs.spec.gen.alpha.simple_type_printable.cljs$core$IFn$_invoke$arity$variadic = ((function (g__8762__auto___13778){
return (function (args){
return cljs.core.deref.call(null,g__8762__auto___13778);
});})(g__8762__auto___13778))
;

cljs.spec.gen.alpha.simple_type_printable.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.simple_type_printable.cljs$lang$applyTo = ((function (g__8762__auto___13778){
return (function (seq13715){
return cljs.spec.gen.alpha.simple_type_printable.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq13715));
});})(g__8762__auto___13778))
;


var g__8762__auto___13782 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.string !== 'undefined')){
return clojure.test.check.generators.string;
} else {
throw (new Error([cljs.core.str.cljs$core$IFn$_invoke$arity$1("Var "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","string","clojure.test.check.generators/string",-1704750979,null)),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" does not exist, "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","string","clojure.test.check.generators/string",-1704750979,null))),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" never required")].join('')));
}
}),null));
/**
 * Fn returning clojure.test.check.generators/string
 */
cljs.spec.gen.alpha.string = ((function (g__8762__auto___13782){
return (function cljs$spec$gen$alpha$string(var_args){
var args__8663__auto__ = [];
var len__8656__auto___13783 = arguments.length;
var i__8657__auto___13784 = (0);
while(true){
if((i__8657__auto___13784 < len__8656__auto___13783)){
args__8663__auto__.push((arguments[i__8657__auto___13784]));

var G__13785 = (i__8657__auto___13784 + (1));
i__8657__auto___13784 = G__13785;
continue;
} else {
}
break;
}

var argseq__8664__auto__ = ((((0) < args__8663__auto__.length))?(new cljs.core.IndexedSeq(args__8663__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.string.cljs$core$IFn$_invoke$arity$variadic(argseq__8664__auto__);
});})(g__8762__auto___13782))
;

cljs.spec.gen.alpha.string.cljs$core$IFn$_invoke$arity$variadic = ((function (g__8762__auto___13782){
return (function (args){
return cljs.core.deref.call(null,g__8762__auto___13782);
});})(g__8762__auto___13782))
;

cljs.spec.gen.alpha.string.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.string.cljs$lang$applyTo = ((function (g__8762__auto___13782){
return (function (seq13716){
return cljs.spec.gen.alpha.string.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq13716));
});})(g__8762__auto___13782))
;


var g__8762__auto___13786 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.string_ascii !== 'undefined')){
return clojure.test.check.generators.string_ascii;
} else {
throw (new Error([cljs.core.str.cljs$core$IFn$_invoke$arity$1("Var "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","string-ascii","clojure.test.check.generators/string-ascii",-2009877640,null)),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" does not exist, "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","string-ascii","clojure.test.check.generators/string-ascii",-2009877640,null))),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" never required")].join('')));
}
}),null));
/**
 * Fn returning clojure.test.check.generators/string-ascii
 */
cljs.spec.gen.alpha.string_ascii = ((function (g__8762__auto___13786){
return (function cljs$spec$gen$alpha$string_ascii(var_args){
var args__8663__auto__ = [];
var len__8656__auto___13787 = arguments.length;
var i__8657__auto___13788 = (0);
while(true){
if((i__8657__auto___13788 < len__8656__auto___13787)){
args__8663__auto__.push((arguments[i__8657__auto___13788]));

var G__13789 = (i__8657__auto___13788 + (1));
i__8657__auto___13788 = G__13789;
continue;
} else {
}
break;
}

var argseq__8664__auto__ = ((((0) < args__8663__auto__.length))?(new cljs.core.IndexedSeq(args__8663__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.string_ascii.cljs$core$IFn$_invoke$arity$variadic(argseq__8664__auto__);
});})(g__8762__auto___13786))
;

cljs.spec.gen.alpha.string_ascii.cljs$core$IFn$_invoke$arity$variadic = ((function (g__8762__auto___13786){
return (function (args){
return cljs.core.deref.call(null,g__8762__auto___13786);
});})(g__8762__auto___13786))
;

cljs.spec.gen.alpha.string_ascii.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.string_ascii.cljs$lang$applyTo = ((function (g__8762__auto___13786){
return (function (seq13717){
return cljs.spec.gen.alpha.string_ascii.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq13717));
});})(g__8762__auto___13786))
;


var g__8762__auto___13790 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.string_alphanumeric !== 'undefined')){
return clojure.test.check.generators.string_alphanumeric;
} else {
throw (new Error([cljs.core.str.cljs$core$IFn$_invoke$arity$1("Var "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","string-alphanumeric","clojure.test.check.generators/string-alphanumeric",836374939,null)),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" does not exist, "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","string-alphanumeric","clojure.test.check.generators/string-alphanumeric",836374939,null))),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" never required")].join('')));
}
}),null));
/**
 * Fn returning clojure.test.check.generators/string-alphanumeric
 */
cljs.spec.gen.alpha.string_alphanumeric = ((function (g__8762__auto___13790){
return (function cljs$spec$gen$alpha$string_alphanumeric(var_args){
var args__8663__auto__ = [];
var len__8656__auto___13791 = arguments.length;
var i__8657__auto___13792 = (0);
while(true){
if((i__8657__auto___13792 < len__8656__auto___13791)){
args__8663__auto__.push((arguments[i__8657__auto___13792]));

var G__13793 = (i__8657__auto___13792 + (1));
i__8657__auto___13792 = G__13793;
continue;
} else {
}
break;
}

var argseq__8664__auto__ = ((((0) < args__8663__auto__.length))?(new cljs.core.IndexedSeq(args__8663__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.string_alphanumeric.cljs$core$IFn$_invoke$arity$variadic(argseq__8664__auto__);
});})(g__8762__auto___13790))
;

cljs.spec.gen.alpha.string_alphanumeric.cljs$core$IFn$_invoke$arity$variadic = ((function (g__8762__auto___13790){
return (function (args){
return cljs.core.deref.call(null,g__8762__auto___13790);
});})(g__8762__auto___13790))
;

cljs.spec.gen.alpha.string_alphanumeric.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.string_alphanumeric.cljs$lang$applyTo = ((function (g__8762__auto___13790){
return (function (seq13718){
return cljs.spec.gen.alpha.string_alphanumeric.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq13718));
});})(g__8762__auto___13790))
;


var g__8762__auto___13794 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.symbol !== 'undefined')){
return clojure.test.check.generators.symbol;
} else {
throw (new Error([cljs.core.str.cljs$core$IFn$_invoke$arity$1("Var "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","symbol","clojure.test.check.generators/symbol",-1305461065,null)),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" does not exist, "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","symbol","clojure.test.check.generators/symbol",-1305461065,null))),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" never required")].join('')));
}
}),null));
/**
 * Fn returning clojure.test.check.generators/symbol
 */
cljs.spec.gen.alpha.symbol = ((function (g__8762__auto___13794){
return (function cljs$spec$gen$alpha$symbol(var_args){
var args__8663__auto__ = [];
var len__8656__auto___13795 = arguments.length;
var i__8657__auto___13796 = (0);
while(true){
if((i__8657__auto___13796 < len__8656__auto___13795)){
args__8663__auto__.push((arguments[i__8657__auto___13796]));

var G__13797 = (i__8657__auto___13796 + (1));
i__8657__auto___13796 = G__13797;
continue;
} else {
}
break;
}

var argseq__8664__auto__ = ((((0) < args__8663__auto__.length))?(new cljs.core.IndexedSeq(args__8663__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.symbol.cljs$core$IFn$_invoke$arity$variadic(argseq__8664__auto__);
});})(g__8762__auto___13794))
;

cljs.spec.gen.alpha.symbol.cljs$core$IFn$_invoke$arity$variadic = ((function (g__8762__auto___13794){
return (function (args){
return cljs.core.deref.call(null,g__8762__auto___13794);
});})(g__8762__auto___13794))
;

cljs.spec.gen.alpha.symbol.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.symbol.cljs$lang$applyTo = ((function (g__8762__auto___13794){
return (function (seq13719){
return cljs.spec.gen.alpha.symbol.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq13719));
});})(g__8762__auto___13794))
;


var g__8762__auto___13798 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.symbol_ns !== 'undefined')){
return clojure.test.check.generators.symbol_ns;
} else {
throw (new Error([cljs.core.str.cljs$core$IFn$_invoke$arity$1("Var "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","symbol-ns","clojure.test.check.generators/symbol-ns",-862629490,null)),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" does not exist, "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","symbol-ns","clojure.test.check.generators/symbol-ns",-862629490,null))),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" never required")].join('')));
}
}),null));
/**
 * Fn returning clojure.test.check.generators/symbol-ns
 */
cljs.spec.gen.alpha.symbol_ns = ((function (g__8762__auto___13798){
return (function cljs$spec$gen$alpha$symbol_ns(var_args){
var args__8663__auto__ = [];
var len__8656__auto___13799 = arguments.length;
var i__8657__auto___13800 = (0);
while(true){
if((i__8657__auto___13800 < len__8656__auto___13799)){
args__8663__auto__.push((arguments[i__8657__auto___13800]));

var G__13801 = (i__8657__auto___13800 + (1));
i__8657__auto___13800 = G__13801;
continue;
} else {
}
break;
}

var argseq__8664__auto__ = ((((0) < args__8663__auto__.length))?(new cljs.core.IndexedSeq(args__8663__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.symbol_ns.cljs$core$IFn$_invoke$arity$variadic(argseq__8664__auto__);
});})(g__8762__auto___13798))
;

cljs.spec.gen.alpha.symbol_ns.cljs$core$IFn$_invoke$arity$variadic = ((function (g__8762__auto___13798){
return (function (args){
return cljs.core.deref.call(null,g__8762__auto___13798);
});})(g__8762__auto___13798))
;

cljs.spec.gen.alpha.symbol_ns.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.symbol_ns.cljs$lang$applyTo = ((function (g__8762__auto___13798){
return (function (seq13720){
return cljs.spec.gen.alpha.symbol_ns.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq13720));
});})(g__8762__auto___13798))
;


var g__8762__auto___13802 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.uuid !== 'undefined')){
return clojure.test.check.generators.uuid;
} else {
throw (new Error([cljs.core.str.cljs$core$IFn$_invoke$arity$1("Var "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","uuid","clojure.test.check.generators/uuid",1589373144,null)),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" does not exist, "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","uuid","clojure.test.check.generators/uuid",1589373144,null))),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" never required")].join('')));
}
}),null));
/**
 * Fn returning clojure.test.check.generators/uuid
 */
cljs.spec.gen.alpha.uuid = ((function (g__8762__auto___13802){
return (function cljs$spec$gen$alpha$uuid(var_args){
var args__8663__auto__ = [];
var len__8656__auto___13803 = arguments.length;
var i__8657__auto___13804 = (0);
while(true){
if((i__8657__auto___13804 < len__8656__auto___13803)){
args__8663__auto__.push((arguments[i__8657__auto___13804]));

var G__13805 = (i__8657__auto___13804 + (1));
i__8657__auto___13804 = G__13805;
continue;
} else {
}
break;
}

var argseq__8664__auto__ = ((((0) < args__8663__auto__.length))?(new cljs.core.IndexedSeq(args__8663__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.uuid.cljs$core$IFn$_invoke$arity$variadic(argseq__8664__auto__);
});})(g__8762__auto___13802))
;

cljs.spec.gen.alpha.uuid.cljs$core$IFn$_invoke$arity$variadic = ((function (g__8762__auto___13802){
return (function (args){
return cljs.core.deref.call(null,g__8762__auto___13802);
});})(g__8762__auto___13802))
;

cljs.spec.gen.alpha.uuid.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.uuid.cljs$lang$applyTo = ((function (g__8762__auto___13802){
return (function (seq13721){
return cljs.spec.gen.alpha.uuid.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq13721));
});})(g__8762__auto___13802))
;

/**
 * Returns a generator of a sequence catenated from results of
 * gens, each of which should generate something sequential.
 */
cljs.spec.gen.alpha.cat = (function cljs$spec$gen$alpha$cat(var_args){
var args__8663__auto__ = [];
var len__8656__auto___13808 = arguments.length;
var i__8657__auto___13809 = (0);
while(true){
if((i__8657__auto___13809 < len__8656__auto___13808)){
args__8663__auto__.push((arguments[i__8657__auto___13809]));

var G__13810 = (i__8657__auto___13809 + (1));
i__8657__auto___13809 = G__13810;
continue;
} else {
}
break;
}

var argseq__8664__auto__ = ((((0) < args__8663__auto__.length))?(new cljs.core.IndexedSeq(args__8663__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.cat.cljs$core$IFn$_invoke$arity$variadic(argseq__8664__auto__);
});

cljs.spec.gen.alpha.cat.cljs$core$IFn$_invoke$arity$variadic = (function (gens){
return cljs.spec.gen.alpha.fmap.call(null,(function (p1__13806_SHARP_){
return cljs.core.apply.call(null,cljs.core.concat,p1__13806_SHARP_);
}),cljs.core.apply.call(null,cljs.spec.gen.alpha.tuple,gens));
});

cljs.spec.gen.alpha.cat.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.cat.cljs$lang$applyTo = (function (seq13807){
return cljs.spec.gen.alpha.cat.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq13807));
});

cljs.spec.gen.alpha.qualified_QMARK_ = (function cljs$spec$gen$alpha$qualified_QMARK_(ident){
return !((cljs.core.namespace.call(null,ident) == null));
});
cljs.spec.gen.alpha.gen_builtins = (new cljs.core.Delay((function (){
var simple = cljs.spec.gen.alpha.simple_type_printable.call(null);
return cljs.core.PersistentHashMap.fromArrays([cljs.core.qualified_keyword_QMARK_,cljs.core.seq_QMARK_,cljs.core.vector_QMARK_,cljs.core.any_QMARK_,cljs.core.boolean_QMARK_,cljs.core.char_QMARK_,cljs.core.inst_QMARK_,cljs.core.simple_symbol_QMARK_,cljs.core.sequential_QMARK_,cljs.core.float_QMARK_,cljs.core.set_QMARK_,cljs.core.map_QMARK_,cljs.core.empty_QMARK_,cljs.core.string_QMARK_,cljs.core.double_QMARK_,cljs.core.int_QMARK_,cljs.core.associative_QMARK_,cljs.core.keyword_QMARK_,cljs.core.indexed_QMARK_,cljs.core.zero_QMARK_,cljs.core.simple_keyword_QMARK_,cljs.core.neg_int_QMARK_,cljs.core.nil_QMARK_,cljs.core.ident_QMARK_,cljs.core.qualified_ident_QMARK_,cljs.core.true_QMARK_,cljs.core.integer_QMARK_,cljs.core.nat_int_QMARK_,cljs.core.pos_int_QMARK_,cljs.core.uuid_QMARK_,cljs.core.false_QMARK_,cljs.core.list_QMARK_,cljs.core.simple_ident_QMARK_,cljs.core.number_QMARK_,cljs.core.qualified_symbol_QMARK_,cljs.core.seqable_QMARK_,cljs.core.symbol_QMARK_,cljs.core.coll_QMARK_],[cljs.spec.gen.alpha.such_that.call(null,cljs.spec.gen.alpha.qualified_QMARK_,cljs.spec.gen.alpha.keyword_ns.call(null)),cljs.spec.gen.alpha.list.call(null,simple),cljs.spec.gen.alpha.vector.call(null,simple),cljs.spec.gen.alpha.one_of.call(null,new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [cljs.spec.gen.alpha.return$.call(null,null),cljs.spec.gen.alpha.any_printable.call(null)], null)),cljs.spec.gen.alpha.boolean$.call(null),cljs.spec.gen.alpha.char$.call(null),cljs.spec.gen.alpha.fmap.call(null,((function (simple){
return (function (p1__13811_SHARP_){
return (new Date(p1__13811_SHARP_));
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

//# sourceMappingURL=alpha.js.map