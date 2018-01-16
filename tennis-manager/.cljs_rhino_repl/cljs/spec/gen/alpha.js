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

cljs.spec.gen.alpha.LazyVar.cljs$lang$ctorPrWriter = (function (this__8057__auto__,writer__8058__auto__,opt__8059__auto__){
return cljs.core._write.call(null,writer__8058__auto__,"cljs.spec.gen.alpha/LazyVar");
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
var args__8566__auto__ = [];
var len__8559__auto___13425 = arguments.length;
var i__8560__auto___13426 = (0);
while(true){
if((i__8560__auto___13426 < len__8559__auto___13425)){
args__8566__auto__.push((arguments[i__8560__auto___13426]));

var G__13427 = (i__8560__auto___13426 + (1));
i__8560__auto___13426 = G__13427;
continue;
} else {
}
break;
}

var argseq__8567__auto__ = ((((0) < args__8566__auto__.length))?(new cljs.core.IndexedSeq(args__8566__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.quick_check.cljs$core$IFn$_invoke$arity$variadic(argseq__8567__auto__);
});

cljs.spec.gen.alpha.quick_check.cljs$core$IFn$_invoke$arity$variadic = (function (args){
return cljs.core.apply.call(null,cljs.core.deref.call(null,cljs.spec.gen.alpha.quick_check_ref),args);
});

cljs.spec.gen.alpha.quick_check.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.quick_check.cljs$lang$applyTo = (function (seq13424){
return cljs.spec.gen.alpha.quick_check.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq13424));
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
var args__8566__auto__ = [];
var len__8559__auto___13429 = arguments.length;
var i__8560__auto___13430 = (0);
while(true){
if((i__8560__auto___13430 < len__8559__auto___13429)){
args__8566__auto__.push((arguments[i__8560__auto___13430]));

var G__13431 = (i__8560__auto___13430 + (1));
i__8560__auto___13430 = G__13431;
continue;
} else {
}
break;
}

var argseq__8567__auto__ = ((((0) < args__8566__auto__.length))?(new cljs.core.IndexedSeq(args__8566__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.for_all_STAR_.cljs$core$IFn$_invoke$arity$variadic(argseq__8567__auto__);
});

cljs.spec.gen.alpha.for_all_STAR_.cljs$core$IFn$_invoke$arity$variadic = (function (args){
return cljs.core.apply.call(null,cljs.core.deref.call(null,cljs.spec.gen.alpha.for_all_STAR__ref),args);
});

cljs.spec.gen.alpha.for_all_STAR_.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.for_all_STAR_.cljs$lang$applyTo = (function (seq13428){
return cljs.spec.gen.alpha.for_all_STAR_.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq13428));
});

var g_QMARK__13432 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.generator_QMARK_ !== 'undefined')){
return clojure.test.check.generators.generator_QMARK_;
} else {
throw (new Error([cljs.core.str.cljs$core$IFn$_invoke$arity$1("Var "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","generator?","clojure.test.check.generators/generator?",-1378210460,null)),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" does not exist, "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","generator?","clojure.test.check.generators/generator?",-1378210460,null))),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" never required")].join('')));
}
}),null));
var g_13433 = (new cljs.spec.gen.alpha.LazyVar(((function (g_QMARK__13432){
return (function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.generate !== 'undefined')){
return clojure.test.check.generators.generate;
} else {
throw (new Error([cljs.core.str.cljs$core$IFn$_invoke$arity$1("Var "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","generate","clojure.test.check.generators/generate",-690390711,null)),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" does not exist, "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","generate","clojure.test.check.generators/generate",-690390711,null))),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" never required")].join('')));
}
});})(g_QMARK__13432))
,null));
var mkg_13434 = (new cljs.spec.gen.alpha.LazyVar(((function (g_QMARK__13432,g_13433){
return (function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.__GT_Generator !== 'undefined')){
return clojure.test.check.generators.__GT_Generator;
} else {
throw (new Error([cljs.core.str.cljs$core$IFn$_invoke$arity$1("Var "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","->Generator","clojure.test.check.generators/->Generator",-1179475051,null)),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" does not exist, "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","->Generator","clojure.test.check.generators/->Generator",-1179475051,null))),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" never required")].join('')));
}
});})(g_QMARK__13432,g_13433))
,null));
cljs.spec.gen.alpha.generator_QMARK_ = ((function (g_QMARK__13432,g_13433,mkg_13434){
return (function cljs$spec$gen$alpha$generator_QMARK_(x){
return cljs.core.deref.call(null,g_QMARK__13432).call(null,x);
});})(g_QMARK__13432,g_13433,mkg_13434))
;

cljs.spec.gen.alpha.generator = ((function (g_QMARK__13432,g_13433,mkg_13434){
return (function cljs$spec$gen$alpha$generator(gfn){
return cljs.core.deref.call(null,mkg_13434).call(null,gfn);
});})(g_QMARK__13432,g_13433,mkg_13434))
;

/**
 * Generate a single value using generator.
 */
cljs.spec.gen.alpha.generate = ((function (g_QMARK__13432,g_13433,mkg_13434){
return (function cljs$spec$gen$alpha$generate(generator){
return cljs.core.deref.call(null,g_13433).call(null,generator);
});})(g_QMARK__13432,g_13433,mkg_13434))
;
cljs.spec.gen.alpha.delay_impl = (function cljs$spec$gen$alpha$delay_impl(gfnd){
return cljs.spec.gen.alpha.generator.call(null,(function (rnd,size){
return new cljs.core.Keyword(null,"gen","gen",142575302).cljs$core$IFn$_invoke$arity$1(cljs.core.deref.call(null,gfnd)).call(null,rnd,size);
}));
});
var g__8652__auto___13454 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.hash_map !== 'undefined')){
return clojure.test.check.generators.hash_map;
} else {
throw (new Error([cljs.core.str.cljs$core$IFn$_invoke$arity$1("Var "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","hash-map","clojure.test.check.generators/hash-map",1961346626,null)),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" does not exist, "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","hash-map","clojure.test.check.generators/hash-map",1961346626,null))),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" never required")].join('')));
}
}),null));
/**
 * Lazy loaded version of clojure.test.check.generators/hash-map
 */
cljs.spec.gen.alpha.hash_map = ((function (g__8652__auto___13454){
return (function cljs$spec$gen$alpha$hash_map(var_args){
var args__8566__auto__ = [];
var len__8559__auto___13455 = arguments.length;
var i__8560__auto___13456 = (0);
while(true){
if((i__8560__auto___13456 < len__8559__auto___13455)){
args__8566__auto__.push((arguments[i__8560__auto___13456]));

var G__13457 = (i__8560__auto___13456 + (1));
i__8560__auto___13456 = G__13457;
continue;
} else {
}
break;
}

var argseq__8567__auto__ = ((((0) < args__8566__auto__.length))?(new cljs.core.IndexedSeq(args__8566__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.hash_map.cljs$core$IFn$_invoke$arity$variadic(argseq__8567__auto__);
});})(g__8652__auto___13454))
;

cljs.spec.gen.alpha.hash_map.cljs$core$IFn$_invoke$arity$variadic = ((function (g__8652__auto___13454){
return (function (args){
return cljs.core.apply.call(null,cljs.core.deref.call(null,g__8652__auto___13454),args);
});})(g__8652__auto___13454))
;

cljs.spec.gen.alpha.hash_map.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.hash_map.cljs$lang$applyTo = ((function (g__8652__auto___13454){
return (function (seq13435){
return cljs.spec.gen.alpha.hash_map.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq13435));
});})(g__8652__auto___13454))
;


var g__8652__auto___13458 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.list !== 'undefined')){
return clojure.test.check.generators.list;
} else {
throw (new Error([cljs.core.str.cljs$core$IFn$_invoke$arity$1("Var "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","list","clojure.test.check.generators/list",506971058,null)),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" does not exist, "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","list","clojure.test.check.generators/list",506971058,null))),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" never required")].join('')));
}
}),null));
/**
 * Lazy loaded version of clojure.test.check.generators/list
 */
cljs.spec.gen.alpha.list = ((function (g__8652__auto___13458){
return (function cljs$spec$gen$alpha$list(var_args){
var args__8566__auto__ = [];
var len__8559__auto___13459 = arguments.length;
var i__8560__auto___13460 = (0);
while(true){
if((i__8560__auto___13460 < len__8559__auto___13459)){
args__8566__auto__.push((arguments[i__8560__auto___13460]));

var G__13461 = (i__8560__auto___13460 + (1));
i__8560__auto___13460 = G__13461;
continue;
} else {
}
break;
}

var argseq__8567__auto__ = ((((0) < args__8566__auto__.length))?(new cljs.core.IndexedSeq(args__8566__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.list.cljs$core$IFn$_invoke$arity$variadic(argseq__8567__auto__);
});})(g__8652__auto___13458))
;

cljs.spec.gen.alpha.list.cljs$core$IFn$_invoke$arity$variadic = ((function (g__8652__auto___13458){
return (function (args){
return cljs.core.apply.call(null,cljs.core.deref.call(null,g__8652__auto___13458),args);
});})(g__8652__auto___13458))
;

cljs.spec.gen.alpha.list.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.list.cljs$lang$applyTo = ((function (g__8652__auto___13458){
return (function (seq13436){
return cljs.spec.gen.alpha.list.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq13436));
});})(g__8652__auto___13458))
;


var g__8652__auto___13462 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.map !== 'undefined')){
return clojure.test.check.generators.map;
} else {
throw (new Error([cljs.core.str.cljs$core$IFn$_invoke$arity$1("Var "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","map","clojure.test.check.generators/map",45738796,null)),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" does not exist, "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","map","clojure.test.check.generators/map",45738796,null))),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" never required")].join('')));
}
}),null));
/**
 * Lazy loaded version of clojure.test.check.generators/map
 */
cljs.spec.gen.alpha.map = ((function (g__8652__auto___13462){
return (function cljs$spec$gen$alpha$map(var_args){
var args__8566__auto__ = [];
var len__8559__auto___13463 = arguments.length;
var i__8560__auto___13464 = (0);
while(true){
if((i__8560__auto___13464 < len__8559__auto___13463)){
args__8566__auto__.push((arguments[i__8560__auto___13464]));

var G__13465 = (i__8560__auto___13464 + (1));
i__8560__auto___13464 = G__13465;
continue;
} else {
}
break;
}

var argseq__8567__auto__ = ((((0) < args__8566__auto__.length))?(new cljs.core.IndexedSeq(args__8566__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.map.cljs$core$IFn$_invoke$arity$variadic(argseq__8567__auto__);
});})(g__8652__auto___13462))
;

cljs.spec.gen.alpha.map.cljs$core$IFn$_invoke$arity$variadic = ((function (g__8652__auto___13462){
return (function (args){
return cljs.core.apply.call(null,cljs.core.deref.call(null,g__8652__auto___13462),args);
});})(g__8652__auto___13462))
;

cljs.spec.gen.alpha.map.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.map.cljs$lang$applyTo = ((function (g__8652__auto___13462){
return (function (seq13437){
return cljs.spec.gen.alpha.map.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq13437));
});})(g__8652__auto___13462))
;


var g__8652__auto___13466 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.not_empty !== 'undefined')){
return clojure.test.check.generators.not_empty;
} else {
throw (new Error([cljs.core.str.cljs$core$IFn$_invoke$arity$1("Var "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","not-empty","clojure.test.check.generators/not-empty",-876211682,null)),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" does not exist, "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","not-empty","clojure.test.check.generators/not-empty",-876211682,null))),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" never required")].join('')));
}
}),null));
/**
 * Lazy loaded version of clojure.test.check.generators/not-empty
 */
cljs.spec.gen.alpha.not_empty = ((function (g__8652__auto___13466){
return (function cljs$spec$gen$alpha$not_empty(var_args){
var args__8566__auto__ = [];
var len__8559__auto___13467 = arguments.length;
var i__8560__auto___13468 = (0);
while(true){
if((i__8560__auto___13468 < len__8559__auto___13467)){
args__8566__auto__.push((arguments[i__8560__auto___13468]));

var G__13469 = (i__8560__auto___13468 + (1));
i__8560__auto___13468 = G__13469;
continue;
} else {
}
break;
}

var argseq__8567__auto__ = ((((0) < args__8566__auto__.length))?(new cljs.core.IndexedSeq(args__8566__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.not_empty.cljs$core$IFn$_invoke$arity$variadic(argseq__8567__auto__);
});})(g__8652__auto___13466))
;

cljs.spec.gen.alpha.not_empty.cljs$core$IFn$_invoke$arity$variadic = ((function (g__8652__auto___13466){
return (function (args){
return cljs.core.apply.call(null,cljs.core.deref.call(null,g__8652__auto___13466),args);
});})(g__8652__auto___13466))
;

cljs.spec.gen.alpha.not_empty.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.not_empty.cljs$lang$applyTo = ((function (g__8652__auto___13466){
return (function (seq13438){
return cljs.spec.gen.alpha.not_empty.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq13438));
});})(g__8652__auto___13466))
;


var g__8652__auto___13470 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.set !== 'undefined')){
return clojure.test.check.generators.set;
} else {
throw (new Error([cljs.core.str.cljs$core$IFn$_invoke$arity$1("Var "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","set","clojure.test.check.generators/set",-1027639543,null)),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" does not exist, "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","set","clojure.test.check.generators/set",-1027639543,null))),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" never required")].join('')));
}
}),null));
/**
 * Lazy loaded version of clojure.test.check.generators/set
 */
cljs.spec.gen.alpha.set = ((function (g__8652__auto___13470){
return (function cljs$spec$gen$alpha$set(var_args){
var args__8566__auto__ = [];
var len__8559__auto___13471 = arguments.length;
var i__8560__auto___13472 = (0);
while(true){
if((i__8560__auto___13472 < len__8559__auto___13471)){
args__8566__auto__.push((arguments[i__8560__auto___13472]));

var G__13473 = (i__8560__auto___13472 + (1));
i__8560__auto___13472 = G__13473;
continue;
} else {
}
break;
}

var argseq__8567__auto__ = ((((0) < args__8566__auto__.length))?(new cljs.core.IndexedSeq(args__8566__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.set.cljs$core$IFn$_invoke$arity$variadic(argseq__8567__auto__);
});})(g__8652__auto___13470))
;

cljs.spec.gen.alpha.set.cljs$core$IFn$_invoke$arity$variadic = ((function (g__8652__auto___13470){
return (function (args){
return cljs.core.apply.call(null,cljs.core.deref.call(null,g__8652__auto___13470),args);
});})(g__8652__auto___13470))
;

cljs.spec.gen.alpha.set.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.set.cljs$lang$applyTo = ((function (g__8652__auto___13470){
return (function (seq13439){
return cljs.spec.gen.alpha.set.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq13439));
});})(g__8652__auto___13470))
;


var g__8652__auto___13474 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.vector !== 'undefined')){
return clojure.test.check.generators.vector;
} else {
throw (new Error([cljs.core.str.cljs$core$IFn$_invoke$arity$1("Var "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","vector","clojure.test.check.generators/vector",1081775325,null)),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" does not exist, "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","vector","clojure.test.check.generators/vector",1081775325,null))),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" never required")].join('')));
}
}),null));
/**
 * Lazy loaded version of clojure.test.check.generators/vector
 */
cljs.spec.gen.alpha.vector = ((function (g__8652__auto___13474){
return (function cljs$spec$gen$alpha$vector(var_args){
var args__8566__auto__ = [];
var len__8559__auto___13475 = arguments.length;
var i__8560__auto___13476 = (0);
while(true){
if((i__8560__auto___13476 < len__8559__auto___13475)){
args__8566__auto__.push((arguments[i__8560__auto___13476]));

var G__13477 = (i__8560__auto___13476 + (1));
i__8560__auto___13476 = G__13477;
continue;
} else {
}
break;
}

var argseq__8567__auto__ = ((((0) < args__8566__auto__.length))?(new cljs.core.IndexedSeq(args__8566__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.vector.cljs$core$IFn$_invoke$arity$variadic(argseq__8567__auto__);
});})(g__8652__auto___13474))
;

cljs.spec.gen.alpha.vector.cljs$core$IFn$_invoke$arity$variadic = ((function (g__8652__auto___13474){
return (function (args){
return cljs.core.apply.call(null,cljs.core.deref.call(null,g__8652__auto___13474),args);
});})(g__8652__auto___13474))
;

cljs.spec.gen.alpha.vector.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.vector.cljs$lang$applyTo = ((function (g__8652__auto___13474){
return (function (seq13440){
return cljs.spec.gen.alpha.vector.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq13440));
});})(g__8652__auto___13474))
;


var g__8652__auto___13478 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.vector_distinct !== 'undefined')){
return clojure.test.check.generators.vector_distinct;
} else {
throw (new Error([cljs.core.str.cljs$core$IFn$_invoke$arity$1("Var "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","vector-distinct","clojure.test.check.generators/vector-distinct",1656877834,null)),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" does not exist, "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","vector-distinct","clojure.test.check.generators/vector-distinct",1656877834,null))),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" never required")].join('')));
}
}),null));
/**
 * Lazy loaded version of clojure.test.check.generators/vector-distinct
 */
cljs.spec.gen.alpha.vector_distinct = ((function (g__8652__auto___13478){
return (function cljs$spec$gen$alpha$vector_distinct(var_args){
var args__8566__auto__ = [];
var len__8559__auto___13479 = arguments.length;
var i__8560__auto___13480 = (0);
while(true){
if((i__8560__auto___13480 < len__8559__auto___13479)){
args__8566__auto__.push((arguments[i__8560__auto___13480]));

var G__13481 = (i__8560__auto___13480 + (1));
i__8560__auto___13480 = G__13481;
continue;
} else {
}
break;
}

var argseq__8567__auto__ = ((((0) < args__8566__auto__.length))?(new cljs.core.IndexedSeq(args__8566__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.vector_distinct.cljs$core$IFn$_invoke$arity$variadic(argseq__8567__auto__);
});})(g__8652__auto___13478))
;

cljs.spec.gen.alpha.vector_distinct.cljs$core$IFn$_invoke$arity$variadic = ((function (g__8652__auto___13478){
return (function (args){
return cljs.core.apply.call(null,cljs.core.deref.call(null,g__8652__auto___13478),args);
});})(g__8652__auto___13478))
;

cljs.spec.gen.alpha.vector_distinct.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.vector_distinct.cljs$lang$applyTo = ((function (g__8652__auto___13478){
return (function (seq13441){
return cljs.spec.gen.alpha.vector_distinct.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq13441));
});})(g__8652__auto___13478))
;


var g__8652__auto___13482 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.fmap !== 'undefined')){
return clojure.test.check.generators.fmap;
} else {
throw (new Error([cljs.core.str.cljs$core$IFn$_invoke$arity$1("Var "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","fmap","clojure.test.check.generators/fmap",1957997092,null)),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" does not exist, "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","fmap","clojure.test.check.generators/fmap",1957997092,null))),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" never required")].join('')));
}
}),null));
/**
 * Lazy loaded version of clojure.test.check.generators/fmap
 */
cljs.spec.gen.alpha.fmap = ((function (g__8652__auto___13482){
return (function cljs$spec$gen$alpha$fmap(var_args){
var args__8566__auto__ = [];
var len__8559__auto___13483 = arguments.length;
var i__8560__auto___13484 = (0);
while(true){
if((i__8560__auto___13484 < len__8559__auto___13483)){
args__8566__auto__.push((arguments[i__8560__auto___13484]));

var G__13485 = (i__8560__auto___13484 + (1));
i__8560__auto___13484 = G__13485;
continue;
} else {
}
break;
}

var argseq__8567__auto__ = ((((0) < args__8566__auto__.length))?(new cljs.core.IndexedSeq(args__8566__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.fmap.cljs$core$IFn$_invoke$arity$variadic(argseq__8567__auto__);
});})(g__8652__auto___13482))
;

cljs.spec.gen.alpha.fmap.cljs$core$IFn$_invoke$arity$variadic = ((function (g__8652__auto___13482){
return (function (args){
return cljs.core.apply.call(null,cljs.core.deref.call(null,g__8652__auto___13482),args);
});})(g__8652__auto___13482))
;

cljs.spec.gen.alpha.fmap.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.fmap.cljs$lang$applyTo = ((function (g__8652__auto___13482){
return (function (seq13442){
return cljs.spec.gen.alpha.fmap.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq13442));
});})(g__8652__auto___13482))
;


var g__8652__auto___13486 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.elements !== 'undefined')){
return clojure.test.check.generators.elements;
} else {
throw (new Error([cljs.core.str.cljs$core$IFn$_invoke$arity$1("Var "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","elements","clojure.test.check.generators/elements",438991326,null)),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" does not exist, "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","elements","clojure.test.check.generators/elements",438991326,null))),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" never required")].join('')));
}
}),null));
/**
 * Lazy loaded version of clojure.test.check.generators/elements
 */
cljs.spec.gen.alpha.elements = ((function (g__8652__auto___13486){
return (function cljs$spec$gen$alpha$elements(var_args){
var args__8566__auto__ = [];
var len__8559__auto___13487 = arguments.length;
var i__8560__auto___13488 = (0);
while(true){
if((i__8560__auto___13488 < len__8559__auto___13487)){
args__8566__auto__.push((arguments[i__8560__auto___13488]));

var G__13489 = (i__8560__auto___13488 + (1));
i__8560__auto___13488 = G__13489;
continue;
} else {
}
break;
}

var argseq__8567__auto__ = ((((0) < args__8566__auto__.length))?(new cljs.core.IndexedSeq(args__8566__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.elements.cljs$core$IFn$_invoke$arity$variadic(argseq__8567__auto__);
});})(g__8652__auto___13486))
;

cljs.spec.gen.alpha.elements.cljs$core$IFn$_invoke$arity$variadic = ((function (g__8652__auto___13486){
return (function (args){
return cljs.core.apply.call(null,cljs.core.deref.call(null,g__8652__auto___13486),args);
});})(g__8652__auto___13486))
;

cljs.spec.gen.alpha.elements.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.elements.cljs$lang$applyTo = ((function (g__8652__auto___13486){
return (function (seq13443){
return cljs.spec.gen.alpha.elements.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq13443));
});})(g__8652__auto___13486))
;


var g__8652__auto___13490 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.bind !== 'undefined')){
return clojure.test.check.generators.bind;
} else {
throw (new Error([cljs.core.str.cljs$core$IFn$_invoke$arity$1("Var "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","bind","clojure.test.check.generators/bind",-361313906,null)),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" does not exist, "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","bind","clojure.test.check.generators/bind",-361313906,null))),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" never required")].join('')));
}
}),null));
/**
 * Lazy loaded version of clojure.test.check.generators/bind
 */
cljs.spec.gen.alpha.bind = ((function (g__8652__auto___13490){
return (function cljs$spec$gen$alpha$bind(var_args){
var args__8566__auto__ = [];
var len__8559__auto___13491 = arguments.length;
var i__8560__auto___13492 = (0);
while(true){
if((i__8560__auto___13492 < len__8559__auto___13491)){
args__8566__auto__.push((arguments[i__8560__auto___13492]));

var G__13493 = (i__8560__auto___13492 + (1));
i__8560__auto___13492 = G__13493;
continue;
} else {
}
break;
}

var argseq__8567__auto__ = ((((0) < args__8566__auto__.length))?(new cljs.core.IndexedSeq(args__8566__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.bind.cljs$core$IFn$_invoke$arity$variadic(argseq__8567__auto__);
});})(g__8652__auto___13490))
;

cljs.spec.gen.alpha.bind.cljs$core$IFn$_invoke$arity$variadic = ((function (g__8652__auto___13490){
return (function (args){
return cljs.core.apply.call(null,cljs.core.deref.call(null,g__8652__auto___13490),args);
});})(g__8652__auto___13490))
;

cljs.spec.gen.alpha.bind.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.bind.cljs$lang$applyTo = ((function (g__8652__auto___13490){
return (function (seq13444){
return cljs.spec.gen.alpha.bind.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq13444));
});})(g__8652__auto___13490))
;


var g__8652__auto___13494 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.choose !== 'undefined')){
return clojure.test.check.generators.choose;
} else {
throw (new Error([cljs.core.str.cljs$core$IFn$_invoke$arity$1("Var "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","choose","clojure.test.check.generators/choose",909997832,null)),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" does not exist, "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","choose","clojure.test.check.generators/choose",909997832,null))),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" never required")].join('')));
}
}),null));
/**
 * Lazy loaded version of clojure.test.check.generators/choose
 */
cljs.spec.gen.alpha.choose = ((function (g__8652__auto___13494){
return (function cljs$spec$gen$alpha$choose(var_args){
var args__8566__auto__ = [];
var len__8559__auto___13495 = arguments.length;
var i__8560__auto___13496 = (0);
while(true){
if((i__8560__auto___13496 < len__8559__auto___13495)){
args__8566__auto__.push((arguments[i__8560__auto___13496]));

var G__13497 = (i__8560__auto___13496 + (1));
i__8560__auto___13496 = G__13497;
continue;
} else {
}
break;
}

var argseq__8567__auto__ = ((((0) < args__8566__auto__.length))?(new cljs.core.IndexedSeq(args__8566__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.choose.cljs$core$IFn$_invoke$arity$variadic(argseq__8567__auto__);
});})(g__8652__auto___13494))
;

cljs.spec.gen.alpha.choose.cljs$core$IFn$_invoke$arity$variadic = ((function (g__8652__auto___13494){
return (function (args){
return cljs.core.apply.call(null,cljs.core.deref.call(null,g__8652__auto___13494),args);
});})(g__8652__auto___13494))
;

cljs.spec.gen.alpha.choose.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.choose.cljs$lang$applyTo = ((function (g__8652__auto___13494){
return (function (seq13445){
return cljs.spec.gen.alpha.choose.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq13445));
});})(g__8652__auto___13494))
;


var g__8652__auto___13498 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.one_of !== 'undefined')){
return clojure.test.check.generators.one_of;
} else {
throw (new Error([cljs.core.str.cljs$core$IFn$_invoke$arity$1("Var "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","one-of","clojure.test.check.generators/one-of",-183339191,null)),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" does not exist, "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","one-of","clojure.test.check.generators/one-of",-183339191,null))),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" never required")].join('')));
}
}),null));
/**
 * Lazy loaded version of clojure.test.check.generators/one-of
 */
cljs.spec.gen.alpha.one_of = ((function (g__8652__auto___13498){
return (function cljs$spec$gen$alpha$one_of(var_args){
var args__8566__auto__ = [];
var len__8559__auto___13499 = arguments.length;
var i__8560__auto___13500 = (0);
while(true){
if((i__8560__auto___13500 < len__8559__auto___13499)){
args__8566__auto__.push((arguments[i__8560__auto___13500]));

var G__13501 = (i__8560__auto___13500 + (1));
i__8560__auto___13500 = G__13501;
continue;
} else {
}
break;
}

var argseq__8567__auto__ = ((((0) < args__8566__auto__.length))?(new cljs.core.IndexedSeq(args__8566__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.one_of.cljs$core$IFn$_invoke$arity$variadic(argseq__8567__auto__);
});})(g__8652__auto___13498))
;

cljs.spec.gen.alpha.one_of.cljs$core$IFn$_invoke$arity$variadic = ((function (g__8652__auto___13498){
return (function (args){
return cljs.core.apply.call(null,cljs.core.deref.call(null,g__8652__auto___13498),args);
});})(g__8652__auto___13498))
;

cljs.spec.gen.alpha.one_of.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.one_of.cljs$lang$applyTo = ((function (g__8652__auto___13498){
return (function (seq13446){
return cljs.spec.gen.alpha.one_of.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq13446));
});})(g__8652__auto___13498))
;


var g__8652__auto___13502 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.such_that !== 'undefined')){
return clojure.test.check.generators.such_that;
} else {
throw (new Error([cljs.core.str.cljs$core$IFn$_invoke$arity$1("Var "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","such-that","clojure.test.check.generators/such-that",-1754178732,null)),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" does not exist, "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","such-that","clojure.test.check.generators/such-that",-1754178732,null))),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" never required")].join('')));
}
}),null));
/**
 * Lazy loaded version of clojure.test.check.generators/such-that
 */
cljs.spec.gen.alpha.such_that = ((function (g__8652__auto___13502){
return (function cljs$spec$gen$alpha$such_that(var_args){
var args__8566__auto__ = [];
var len__8559__auto___13503 = arguments.length;
var i__8560__auto___13504 = (0);
while(true){
if((i__8560__auto___13504 < len__8559__auto___13503)){
args__8566__auto__.push((arguments[i__8560__auto___13504]));

var G__13505 = (i__8560__auto___13504 + (1));
i__8560__auto___13504 = G__13505;
continue;
} else {
}
break;
}

var argseq__8567__auto__ = ((((0) < args__8566__auto__.length))?(new cljs.core.IndexedSeq(args__8566__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.such_that.cljs$core$IFn$_invoke$arity$variadic(argseq__8567__auto__);
});})(g__8652__auto___13502))
;

cljs.spec.gen.alpha.such_that.cljs$core$IFn$_invoke$arity$variadic = ((function (g__8652__auto___13502){
return (function (args){
return cljs.core.apply.call(null,cljs.core.deref.call(null,g__8652__auto___13502),args);
});})(g__8652__auto___13502))
;

cljs.spec.gen.alpha.such_that.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.such_that.cljs$lang$applyTo = ((function (g__8652__auto___13502){
return (function (seq13447){
return cljs.spec.gen.alpha.such_that.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq13447));
});})(g__8652__auto___13502))
;


var g__8652__auto___13506 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.tuple !== 'undefined')){
return clojure.test.check.generators.tuple;
} else {
throw (new Error([cljs.core.str.cljs$core$IFn$_invoke$arity$1("Var "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","tuple","clojure.test.check.generators/tuple",-143711557,null)),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" does not exist, "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","tuple","clojure.test.check.generators/tuple",-143711557,null))),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" never required")].join('')));
}
}),null));
/**
 * Lazy loaded version of clojure.test.check.generators/tuple
 */
cljs.spec.gen.alpha.tuple = ((function (g__8652__auto___13506){
return (function cljs$spec$gen$alpha$tuple(var_args){
var args__8566__auto__ = [];
var len__8559__auto___13507 = arguments.length;
var i__8560__auto___13508 = (0);
while(true){
if((i__8560__auto___13508 < len__8559__auto___13507)){
args__8566__auto__.push((arguments[i__8560__auto___13508]));

var G__13509 = (i__8560__auto___13508 + (1));
i__8560__auto___13508 = G__13509;
continue;
} else {
}
break;
}

var argseq__8567__auto__ = ((((0) < args__8566__auto__.length))?(new cljs.core.IndexedSeq(args__8566__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.tuple.cljs$core$IFn$_invoke$arity$variadic(argseq__8567__auto__);
});})(g__8652__auto___13506))
;

cljs.spec.gen.alpha.tuple.cljs$core$IFn$_invoke$arity$variadic = ((function (g__8652__auto___13506){
return (function (args){
return cljs.core.apply.call(null,cljs.core.deref.call(null,g__8652__auto___13506),args);
});})(g__8652__auto___13506))
;

cljs.spec.gen.alpha.tuple.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.tuple.cljs$lang$applyTo = ((function (g__8652__auto___13506){
return (function (seq13448){
return cljs.spec.gen.alpha.tuple.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq13448));
});})(g__8652__auto___13506))
;


var g__8652__auto___13510 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.sample !== 'undefined')){
return clojure.test.check.generators.sample;
} else {
throw (new Error([cljs.core.str.cljs$core$IFn$_invoke$arity$1("Var "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","sample","clojure.test.check.generators/sample",-382944992,null)),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" does not exist, "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","sample","clojure.test.check.generators/sample",-382944992,null))),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" never required")].join('')));
}
}),null));
/**
 * Lazy loaded version of clojure.test.check.generators/sample
 */
cljs.spec.gen.alpha.sample = ((function (g__8652__auto___13510){
return (function cljs$spec$gen$alpha$sample(var_args){
var args__8566__auto__ = [];
var len__8559__auto___13511 = arguments.length;
var i__8560__auto___13512 = (0);
while(true){
if((i__8560__auto___13512 < len__8559__auto___13511)){
args__8566__auto__.push((arguments[i__8560__auto___13512]));

var G__13513 = (i__8560__auto___13512 + (1));
i__8560__auto___13512 = G__13513;
continue;
} else {
}
break;
}

var argseq__8567__auto__ = ((((0) < args__8566__auto__.length))?(new cljs.core.IndexedSeq(args__8566__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.sample.cljs$core$IFn$_invoke$arity$variadic(argseq__8567__auto__);
});})(g__8652__auto___13510))
;

cljs.spec.gen.alpha.sample.cljs$core$IFn$_invoke$arity$variadic = ((function (g__8652__auto___13510){
return (function (args){
return cljs.core.apply.call(null,cljs.core.deref.call(null,g__8652__auto___13510),args);
});})(g__8652__auto___13510))
;

cljs.spec.gen.alpha.sample.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.sample.cljs$lang$applyTo = ((function (g__8652__auto___13510){
return (function (seq13449){
return cljs.spec.gen.alpha.sample.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq13449));
});})(g__8652__auto___13510))
;


var g__8652__auto___13514 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.return$ !== 'undefined')){
return clojure.test.check.generators.return$;
} else {
throw (new Error([cljs.core.str.cljs$core$IFn$_invoke$arity$1("Var "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","return","clojure.test.check.generators/return",1744522038,null)),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" does not exist, "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","return","clojure.test.check.generators/return",1744522038,null))),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" never required")].join('')));
}
}),null));
/**
 * Lazy loaded version of clojure.test.check.generators/return
 */
cljs.spec.gen.alpha.return$ = ((function (g__8652__auto___13514){
return (function cljs$spec$gen$alpha$return(var_args){
var args__8566__auto__ = [];
var len__8559__auto___13515 = arguments.length;
var i__8560__auto___13516 = (0);
while(true){
if((i__8560__auto___13516 < len__8559__auto___13515)){
args__8566__auto__.push((arguments[i__8560__auto___13516]));

var G__13517 = (i__8560__auto___13516 + (1));
i__8560__auto___13516 = G__13517;
continue;
} else {
}
break;
}

var argseq__8567__auto__ = ((((0) < args__8566__auto__.length))?(new cljs.core.IndexedSeq(args__8566__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.return$.cljs$core$IFn$_invoke$arity$variadic(argseq__8567__auto__);
});})(g__8652__auto___13514))
;

cljs.spec.gen.alpha.return$.cljs$core$IFn$_invoke$arity$variadic = ((function (g__8652__auto___13514){
return (function (args){
return cljs.core.apply.call(null,cljs.core.deref.call(null,g__8652__auto___13514),args);
});})(g__8652__auto___13514))
;

cljs.spec.gen.alpha.return$.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.return$.cljs$lang$applyTo = ((function (g__8652__auto___13514){
return (function (seq13450){
return cljs.spec.gen.alpha.return$.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq13450));
});})(g__8652__auto___13514))
;


var g__8652__auto___13518 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.large_integer_STAR_ !== 'undefined')){
return clojure.test.check.generators.large_integer_STAR_;
} else {
throw (new Error([cljs.core.str.cljs$core$IFn$_invoke$arity$1("Var "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","large-integer*","clojure.test.check.generators/large-integer*",-437830670,null)),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" does not exist, "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","large-integer*","clojure.test.check.generators/large-integer*",-437830670,null))),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" never required")].join('')));
}
}),null));
/**
 * Lazy loaded version of clojure.test.check.generators/large-integer*
 */
cljs.spec.gen.alpha.large_integer_STAR_ = ((function (g__8652__auto___13518){
return (function cljs$spec$gen$alpha$large_integer_STAR_(var_args){
var args__8566__auto__ = [];
var len__8559__auto___13519 = arguments.length;
var i__8560__auto___13520 = (0);
while(true){
if((i__8560__auto___13520 < len__8559__auto___13519)){
args__8566__auto__.push((arguments[i__8560__auto___13520]));

var G__13521 = (i__8560__auto___13520 + (1));
i__8560__auto___13520 = G__13521;
continue;
} else {
}
break;
}

var argseq__8567__auto__ = ((((0) < args__8566__auto__.length))?(new cljs.core.IndexedSeq(args__8566__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.large_integer_STAR_.cljs$core$IFn$_invoke$arity$variadic(argseq__8567__auto__);
});})(g__8652__auto___13518))
;

cljs.spec.gen.alpha.large_integer_STAR_.cljs$core$IFn$_invoke$arity$variadic = ((function (g__8652__auto___13518){
return (function (args){
return cljs.core.apply.call(null,cljs.core.deref.call(null,g__8652__auto___13518),args);
});})(g__8652__auto___13518))
;

cljs.spec.gen.alpha.large_integer_STAR_.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.large_integer_STAR_.cljs$lang$applyTo = ((function (g__8652__auto___13518){
return (function (seq13451){
return cljs.spec.gen.alpha.large_integer_STAR_.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq13451));
});})(g__8652__auto___13518))
;


var g__8652__auto___13522 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.double_STAR_ !== 'undefined')){
return clojure.test.check.generators.double_STAR_;
} else {
throw (new Error([cljs.core.str.cljs$core$IFn$_invoke$arity$1("Var "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","double*","clojure.test.check.generators/double*",841542265,null)),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" does not exist, "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","double*","clojure.test.check.generators/double*",841542265,null))),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" never required")].join('')));
}
}),null));
/**
 * Lazy loaded version of clojure.test.check.generators/double*
 */
cljs.spec.gen.alpha.double_STAR_ = ((function (g__8652__auto___13522){
return (function cljs$spec$gen$alpha$double_STAR_(var_args){
var args__8566__auto__ = [];
var len__8559__auto___13523 = arguments.length;
var i__8560__auto___13524 = (0);
while(true){
if((i__8560__auto___13524 < len__8559__auto___13523)){
args__8566__auto__.push((arguments[i__8560__auto___13524]));

var G__13525 = (i__8560__auto___13524 + (1));
i__8560__auto___13524 = G__13525;
continue;
} else {
}
break;
}

var argseq__8567__auto__ = ((((0) < args__8566__auto__.length))?(new cljs.core.IndexedSeq(args__8566__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.double_STAR_.cljs$core$IFn$_invoke$arity$variadic(argseq__8567__auto__);
});})(g__8652__auto___13522))
;

cljs.spec.gen.alpha.double_STAR_.cljs$core$IFn$_invoke$arity$variadic = ((function (g__8652__auto___13522){
return (function (args){
return cljs.core.apply.call(null,cljs.core.deref.call(null,g__8652__auto___13522),args);
});})(g__8652__auto___13522))
;

cljs.spec.gen.alpha.double_STAR_.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.double_STAR_.cljs$lang$applyTo = ((function (g__8652__auto___13522){
return (function (seq13452){
return cljs.spec.gen.alpha.double_STAR_.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq13452));
});})(g__8652__auto___13522))
;


var g__8652__auto___13526 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.frequency !== 'undefined')){
return clojure.test.check.generators.frequency;
} else {
throw (new Error([cljs.core.str.cljs$core$IFn$_invoke$arity$1("Var "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","frequency","clojure.test.check.generators/frequency",2090703177,null)),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" does not exist, "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","frequency","clojure.test.check.generators/frequency",2090703177,null))),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" never required")].join('')));
}
}),null));
/**
 * Lazy loaded version of clojure.test.check.generators/frequency
 */
cljs.spec.gen.alpha.frequency = ((function (g__8652__auto___13526){
return (function cljs$spec$gen$alpha$frequency(var_args){
var args__8566__auto__ = [];
var len__8559__auto___13527 = arguments.length;
var i__8560__auto___13528 = (0);
while(true){
if((i__8560__auto___13528 < len__8559__auto___13527)){
args__8566__auto__.push((arguments[i__8560__auto___13528]));

var G__13529 = (i__8560__auto___13528 + (1));
i__8560__auto___13528 = G__13529;
continue;
} else {
}
break;
}

var argseq__8567__auto__ = ((((0) < args__8566__auto__.length))?(new cljs.core.IndexedSeq(args__8566__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.frequency.cljs$core$IFn$_invoke$arity$variadic(argseq__8567__auto__);
});})(g__8652__auto___13526))
;

cljs.spec.gen.alpha.frequency.cljs$core$IFn$_invoke$arity$variadic = ((function (g__8652__auto___13526){
return (function (args){
return cljs.core.apply.call(null,cljs.core.deref.call(null,g__8652__auto___13526),args);
});})(g__8652__auto___13526))
;

cljs.spec.gen.alpha.frequency.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.frequency.cljs$lang$applyTo = ((function (g__8652__auto___13526){
return (function (seq13453){
return cljs.spec.gen.alpha.frequency.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq13453));
});})(g__8652__auto___13526))
;

var g__8665__auto___13551 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.any !== 'undefined')){
return clojure.test.check.generators.any;
} else {
throw (new Error([cljs.core.str.cljs$core$IFn$_invoke$arity$1("Var "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","any","clojure.test.check.generators/any",1883743710,null)),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" does not exist, "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","any","clojure.test.check.generators/any",1883743710,null))),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" never required")].join('')));
}
}),null));
/**
 * Fn returning clojure.test.check.generators/any
 */
cljs.spec.gen.alpha.any = ((function (g__8665__auto___13551){
return (function cljs$spec$gen$alpha$any(var_args){
var args__8566__auto__ = [];
var len__8559__auto___13552 = arguments.length;
var i__8560__auto___13553 = (0);
while(true){
if((i__8560__auto___13553 < len__8559__auto___13552)){
args__8566__auto__.push((arguments[i__8560__auto___13553]));

var G__13554 = (i__8560__auto___13553 + (1));
i__8560__auto___13553 = G__13554;
continue;
} else {
}
break;
}

var argseq__8567__auto__ = ((((0) < args__8566__auto__.length))?(new cljs.core.IndexedSeq(args__8566__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.any.cljs$core$IFn$_invoke$arity$variadic(argseq__8567__auto__);
});})(g__8665__auto___13551))
;

cljs.spec.gen.alpha.any.cljs$core$IFn$_invoke$arity$variadic = ((function (g__8665__auto___13551){
return (function (args){
return cljs.core.deref.call(null,g__8665__auto___13551);
});})(g__8665__auto___13551))
;

cljs.spec.gen.alpha.any.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.any.cljs$lang$applyTo = ((function (g__8665__auto___13551){
return (function (seq13530){
return cljs.spec.gen.alpha.any.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq13530));
});})(g__8665__auto___13551))
;


var g__8665__auto___13555 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.any_printable !== 'undefined')){
return clojure.test.check.generators.any_printable;
} else {
throw (new Error([cljs.core.str.cljs$core$IFn$_invoke$arity$1("Var "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","any-printable","clojure.test.check.generators/any-printable",-1570493991,null)),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" does not exist, "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","any-printable","clojure.test.check.generators/any-printable",-1570493991,null))),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" never required")].join('')));
}
}),null));
/**
 * Fn returning clojure.test.check.generators/any-printable
 */
cljs.spec.gen.alpha.any_printable = ((function (g__8665__auto___13555){
return (function cljs$spec$gen$alpha$any_printable(var_args){
var args__8566__auto__ = [];
var len__8559__auto___13556 = arguments.length;
var i__8560__auto___13557 = (0);
while(true){
if((i__8560__auto___13557 < len__8559__auto___13556)){
args__8566__auto__.push((arguments[i__8560__auto___13557]));

var G__13558 = (i__8560__auto___13557 + (1));
i__8560__auto___13557 = G__13558;
continue;
} else {
}
break;
}

var argseq__8567__auto__ = ((((0) < args__8566__auto__.length))?(new cljs.core.IndexedSeq(args__8566__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.any_printable.cljs$core$IFn$_invoke$arity$variadic(argseq__8567__auto__);
});})(g__8665__auto___13555))
;

cljs.spec.gen.alpha.any_printable.cljs$core$IFn$_invoke$arity$variadic = ((function (g__8665__auto___13555){
return (function (args){
return cljs.core.deref.call(null,g__8665__auto___13555);
});})(g__8665__auto___13555))
;

cljs.spec.gen.alpha.any_printable.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.any_printable.cljs$lang$applyTo = ((function (g__8665__auto___13555){
return (function (seq13531){
return cljs.spec.gen.alpha.any_printable.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq13531));
});})(g__8665__auto___13555))
;


var g__8665__auto___13559 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.boolean$ !== 'undefined')){
return clojure.test.check.generators.boolean$;
} else {
throw (new Error([cljs.core.str.cljs$core$IFn$_invoke$arity$1("Var "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","boolean","clojure.test.check.generators/boolean",1586992347,null)),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" does not exist, "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","boolean","clojure.test.check.generators/boolean",1586992347,null))),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" never required")].join('')));
}
}),null));
/**
 * Fn returning clojure.test.check.generators/boolean
 */
cljs.spec.gen.alpha.boolean$ = ((function (g__8665__auto___13559){
return (function cljs$spec$gen$alpha$boolean(var_args){
var args__8566__auto__ = [];
var len__8559__auto___13560 = arguments.length;
var i__8560__auto___13561 = (0);
while(true){
if((i__8560__auto___13561 < len__8559__auto___13560)){
args__8566__auto__.push((arguments[i__8560__auto___13561]));

var G__13562 = (i__8560__auto___13561 + (1));
i__8560__auto___13561 = G__13562;
continue;
} else {
}
break;
}

var argseq__8567__auto__ = ((((0) < args__8566__auto__.length))?(new cljs.core.IndexedSeq(args__8566__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.boolean$.cljs$core$IFn$_invoke$arity$variadic(argseq__8567__auto__);
});})(g__8665__auto___13559))
;

cljs.spec.gen.alpha.boolean$.cljs$core$IFn$_invoke$arity$variadic = ((function (g__8665__auto___13559){
return (function (args){
return cljs.core.deref.call(null,g__8665__auto___13559);
});})(g__8665__auto___13559))
;

cljs.spec.gen.alpha.boolean$.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.boolean$.cljs$lang$applyTo = ((function (g__8665__auto___13559){
return (function (seq13532){
return cljs.spec.gen.alpha.boolean$.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq13532));
});})(g__8665__auto___13559))
;


var g__8665__auto___13563 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.char$ !== 'undefined')){
return clojure.test.check.generators.char$;
} else {
throw (new Error([cljs.core.str.cljs$core$IFn$_invoke$arity$1("Var "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","char","clojure.test.check.generators/char",-1426343459,null)),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" does not exist, "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","char","clojure.test.check.generators/char",-1426343459,null))),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" never required")].join('')));
}
}),null));
/**
 * Fn returning clojure.test.check.generators/char
 */
cljs.spec.gen.alpha.char$ = ((function (g__8665__auto___13563){
return (function cljs$spec$gen$alpha$char(var_args){
var args__8566__auto__ = [];
var len__8559__auto___13564 = arguments.length;
var i__8560__auto___13565 = (0);
while(true){
if((i__8560__auto___13565 < len__8559__auto___13564)){
args__8566__auto__.push((arguments[i__8560__auto___13565]));

var G__13566 = (i__8560__auto___13565 + (1));
i__8560__auto___13565 = G__13566;
continue;
} else {
}
break;
}

var argseq__8567__auto__ = ((((0) < args__8566__auto__.length))?(new cljs.core.IndexedSeq(args__8566__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.char$.cljs$core$IFn$_invoke$arity$variadic(argseq__8567__auto__);
});})(g__8665__auto___13563))
;

cljs.spec.gen.alpha.char$.cljs$core$IFn$_invoke$arity$variadic = ((function (g__8665__auto___13563){
return (function (args){
return cljs.core.deref.call(null,g__8665__auto___13563);
});})(g__8665__auto___13563))
;

cljs.spec.gen.alpha.char$.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.char$.cljs$lang$applyTo = ((function (g__8665__auto___13563){
return (function (seq13533){
return cljs.spec.gen.alpha.char$.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq13533));
});})(g__8665__auto___13563))
;


var g__8665__auto___13567 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.char_alpha !== 'undefined')){
return clojure.test.check.generators.char_alpha;
} else {
throw (new Error([cljs.core.str.cljs$core$IFn$_invoke$arity$1("Var "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","char-alpha","clojure.test.check.generators/char-alpha",615785796,null)),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" does not exist, "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","char-alpha","clojure.test.check.generators/char-alpha",615785796,null))),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" never required")].join('')));
}
}),null));
/**
 * Fn returning clojure.test.check.generators/char-alpha
 */
cljs.spec.gen.alpha.char_alpha = ((function (g__8665__auto___13567){
return (function cljs$spec$gen$alpha$char_alpha(var_args){
var args__8566__auto__ = [];
var len__8559__auto___13568 = arguments.length;
var i__8560__auto___13569 = (0);
while(true){
if((i__8560__auto___13569 < len__8559__auto___13568)){
args__8566__auto__.push((arguments[i__8560__auto___13569]));

var G__13570 = (i__8560__auto___13569 + (1));
i__8560__auto___13569 = G__13570;
continue;
} else {
}
break;
}

var argseq__8567__auto__ = ((((0) < args__8566__auto__.length))?(new cljs.core.IndexedSeq(args__8566__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.char_alpha.cljs$core$IFn$_invoke$arity$variadic(argseq__8567__auto__);
});})(g__8665__auto___13567))
;

cljs.spec.gen.alpha.char_alpha.cljs$core$IFn$_invoke$arity$variadic = ((function (g__8665__auto___13567){
return (function (args){
return cljs.core.deref.call(null,g__8665__auto___13567);
});})(g__8665__auto___13567))
;

cljs.spec.gen.alpha.char_alpha.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.char_alpha.cljs$lang$applyTo = ((function (g__8665__auto___13567){
return (function (seq13534){
return cljs.spec.gen.alpha.char_alpha.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq13534));
});})(g__8665__auto___13567))
;


var g__8665__auto___13571 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.char_alphanumeric !== 'undefined')){
return clojure.test.check.generators.char_alphanumeric;
} else {
throw (new Error([cljs.core.str.cljs$core$IFn$_invoke$arity$1("Var "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","char-alphanumeric","clojure.test.check.generators/char-alphanumeric",1383091431,null)),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" does not exist, "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","char-alphanumeric","clojure.test.check.generators/char-alphanumeric",1383091431,null))),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" never required")].join('')));
}
}),null));
/**
 * Fn returning clojure.test.check.generators/char-alphanumeric
 */
cljs.spec.gen.alpha.char_alphanumeric = ((function (g__8665__auto___13571){
return (function cljs$spec$gen$alpha$char_alphanumeric(var_args){
var args__8566__auto__ = [];
var len__8559__auto___13572 = arguments.length;
var i__8560__auto___13573 = (0);
while(true){
if((i__8560__auto___13573 < len__8559__auto___13572)){
args__8566__auto__.push((arguments[i__8560__auto___13573]));

var G__13574 = (i__8560__auto___13573 + (1));
i__8560__auto___13573 = G__13574;
continue;
} else {
}
break;
}

var argseq__8567__auto__ = ((((0) < args__8566__auto__.length))?(new cljs.core.IndexedSeq(args__8566__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.char_alphanumeric.cljs$core$IFn$_invoke$arity$variadic(argseq__8567__auto__);
});})(g__8665__auto___13571))
;

cljs.spec.gen.alpha.char_alphanumeric.cljs$core$IFn$_invoke$arity$variadic = ((function (g__8665__auto___13571){
return (function (args){
return cljs.core.deref.call(null,g__8665__auto___13571);
});})(g__8665__auto___13571))
;

cljs.spec.gen.alpha.char_alphanumeric.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.char_alphanumeric.cljs$lang$applyTo = ((function (g__8665__auto___13571){
return (function (seq13535){
return cljs.spec.gen.alpha.char_alphanumeric.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq13535));
});})(g__8665__auto___13571))
;


var g__8665__auto___13575 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.char_ascii !== 'undefined')){
return clojure.test.check.generators.char_ascii;
} else {
throw (new Error([cljs.core.str.cljs$core$IFn$_invoke$arity$1("Var "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","char-ascii","clojure.test.check.generators/char-ascii",-899908538,null)),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" does not exist, "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","char-ascii","clojure.test.check.generators/char-ascii",-899908538,null))),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" never required")].join('')));
}
}),null));
/**
 * Fn returning clojure.test.check.generators/char-ascii
 */
cljs.spec.gen.alpha.char_ascii = ((function (g__8665__auto___13575){
return (function cljs$spec$gen$alpha$char_ascii(var_args){
var args__8566__auto__ = [];
var len__8559__auto___13576 = arguments.length;
var i__8560__auto___13577 = (0);
while(true){
if((i__8560__auto___13577 < len__8559__auto___13576)){
args__8566__auto__.push((arguments[i__8560__auto___13577]));

var G__13578 = (i__8560__auto___13577 + (1));
i__8560__auto___13577 = G__13578;
continue;
} else {
}
break;
}

var argseq__8567__auto__ = ((((0) < args__8566__auto__.length))?(new cljs.core.IndexedSeq(args__8566__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.char_ascii.cljs$core$IFn$_invoke$arity$variadic(argseq__8567__auto__);
});})(g__8665__auto___13575))
;

cljs.spec.gen.alpha.char_ascii.cljs$core$IFn$_invoke$arity$variadic = ((function (g__8665__auto___13575){
return (function (args){
return cljs.core.deref.call(null,g__8665__auto___13575);
});})(g__8665__auto___13575))
;

cljs.spec.gen.alpha.char_ascii.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.char_ascii.cljs$lang$applyTo = ((function (g__8665__auto___13575){
return (function (seq13536){
return cljs.spec.gen.alpha.char_ascii.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq13536));
});})(g__8665__auto___13575))
;


var g__8665__auto___13579 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.double$ !== 'undefined')){
return clojure.test.check.generators.double$;
} else {
throw (new Error([cljs.core.str.cljs$core$IFn$_invoke$arity$1("Var "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","double","clojure.test.check.generators/double",668331090,null)),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" does not exist, "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","double","clojure.test.check.generators/double",668331090,null))),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" never required")].join('')));
}
}),null));
/**
 * Fn returning clojure.test.check.generators/double
 */
cljs.spec.gen.alpha.double$ = ((function (g__8665__auto___13579){
return (function cljs$spec$gen$alpha$double(var_args){
var args__8566__auto__ = [];
var len__8559__auto___13580 = arguments.length;
var i__8560__auto___13581 = (0);
while(true){
if((i__8560__auto___13581 < len__8559__auto___13580)){
args__8566__auto__.push((arguments[i__8560__auto___13581]));

var G__13582 = (i__8560__auto___13581 + (1));
i__8560__auto___13581 = G__13582;
continue;
} else {
}
break;
}

var argseq__8567__auto__ = ((((0) < args__8566__auto__.length))?(new cljs.core.IndexedSeq(args__8566__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.double$.cljs$core$IFn$_invoke$arity$variadic(argseq__8567__auto__);
});})(g__8665__auto___13579))
;

cljs.spec.gen.alpha.double$.cljs$core$IFn$_invoke$arity$variadic = ((function (g__8665__auto___13579){
return (function (args){
return cljs.core.deref.call(null,g__8665__auto___13579);
});})(g__8665__auto___13579))
;

cljs.spec.gen.alpha.double$.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.double$.cljs$lang$applyTo = ((function (g__8665__auto___13579){
return (function (seq13537){
return cljs.spec.gen.alpha.double$.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq13537));
});})(g__8665__auto___13579))
;


var g__8665__auto___13583 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.int$ !== 'undefined')){
return clojure.test.check.generators.int$;
} else {
throw (new Error([cljs.core.str.cljs$core$IFn$_invoke$arity$1("Var "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","int","clojure.test.check.generators/int",1756228469,null)),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" does not exist, "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","int","clojure.test.check.generators/int",1756228469,null))),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" never required")].join('')));
}
}),null));
/**
 * Fn returning clojure.test.check.generators/int
 */
cljs.spec.gen.alpha.int$ = ((function (g__8665__auto___13583){
return (function cljs$spec$gen$alpha$int(var_args){
var args__8566__auto__ = [];
var len__8559__auto___13584 = arguments.length;
var i__8560__auto___13585 = (0);
while(true){
if((i__8560__auto___13585 < len__8559__auto___13584)){
args__8566__auto__.push((arguments[i__8560__auto___13585]));

var G__13586 = (i__8560__auto___13585 + (1));
i__8560__auto___13585 = G__13586;
continue;
} else {
}
break;
}

var argseq__8567__auto__ = ((((0) < args__8566__auto__.length))?(new cljs.core.IndexedSeq(args__8566__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.int$.cljs$core$IFn$_invoke$arity$variadic(argseq__8567__auto__);
});})(g__8665__auto___13583))
;

cljs.spec.gen.alpha.int$.cljs$core$IFn$_invoke$arity$variadic = ((function (g__8665__auto___13583){
return (function (args){
return cljs.core.deref.call(null,g__8665__auto___13583);
});})(g__8665__auto___13583))
;

cljs.spec.gen.alpha.int$.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.int$.cljs$lang$applyTo = ((function (g__8665__auto___13583){
return (function (seq13538){
return cljs.spec.gen.alpha.int$.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq13538));
});})(g__8665__auto___13583))
;


var g__8665__auto___13587 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.keyword !== 'undefined')){
return clojure.test.check.generators.keyword;
} else {
throw (new Error([cljs.core.str.cljs$core$IFn$_invoke$arity$1("Var "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","keyword","clojure.test.check.generators/keyword",24530530,null)),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" does not exist, "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","keyword","clojure.test.check.generators/keyword",24530530,null))),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" never required")].join('')));
}
}),null));
/**
 * Fn returning clojure.test.check.generators/keyword
 */
cljs.spec.gen.alpha.keyword = ((function (g__8665__auto___13587){
return (function cljs$spec$gen$alpha$keyword(var_args){
var args__8566__auto__ = [];
var len__8559__auto___13588 = arguments.length;
var i__8560__auto___13589 = (0);
while(true){
if((i__8560__auto___13589 < len__8559__auto___13588)){
args__8566__auto__.push((arguments[i__8560__auto___13589]));

var G__13590 = (i__8560__auto___13589 + (1));
i__8560__auto___13589 = G__13590;
continue;
} else {
}
break;
}

var argseq__8567__auto__ = ((((0) < args__8566__auto__.length))?(new cljs.core.IndexedSeq(args__8566__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.keyword.cljs$core$IFn$_invoke$arity$variadic(argseq__8567__auto__);
});})(g__8665__auto___13587))
;

cljs.spec.gen.alpha.keyword.cljs$core$IFn$_invoke$arity$variadic = ((function (g__8665__auto___13587){
return (function (args){
return cljs.core.deref.call(null,g__8665__auto___13587);
});})(g__8665__auto___13587))
;

cljs.spec.gen.alpha.keyword.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.keyword.cljs$lang$applyTo = ((function (g__8665__auto___13587){
return (function (seq13539){
return cljs.spec.gen.alpha.keyword.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq13539));
});})(g__8665__auto___13587))
;


var g__8665__auto___13591 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.keyword_ns !== 'undefined')){
return clojure.test.check.generators.keyword_ns;
} else {
throw (new Error([cljs.core.str.cljs$core$IFn$_invoke$arity$1("Var "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","keyword-ns","clojure.test.check.generators/keyword-ns",-1492628482,null)),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" does not exist, "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","keyword-ns","clojure.test.check.generators/keyword-ns",-1492628482,null))),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" never required")].join('')));
}
}),null));
/**
 * Fn returning clojure.test.check.generators/keyword-ns
 */
cljs.spec.gen.alpha.keyword_ns = ((function (g__8665__auto___13591){
return (function cljs$spec$gen$alpha$keyword_ns(var_args){
var args__8566__auto__ = [];
var len__8559__auto___13592 = arguments.length;
var i__8560__auto___13593 = (0);
while(true){
if((i__8560__auto___13593 < len__8559__auto___13592)){
args__8566__auto__.push((arguments[i__8560__auto___13593]));

var G__13594 = (i__8560__auto___13593 + (1));
i__8560__auto___13593 = G__13594;
continue;
} else {
}
break;
}

var argseq__8567__auto__ = ((((0) < args__8566__auto__.length))?(new cljs.core.IndexedSeq(args__8566__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.keyword_ns.cljs$core$IFn$_invoke$arity$variadic(argseq__8567__auto__);
});})(g__8665__auto___13591))
;

cljs.spec.gen.alpha.keyword_ns.cljs$core$IFn$_invoke$arity$variadic = ((function (g__8665__auto___13591){
return (function (args){
return cljs.core.deref.call(null,g__8665__auto___13591);
});})(g__8665__auto___13591))
;

cljs.spec.gen.alpha.keyword_ns.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.keyword_ns.cljs$lang$applyTo = ((function (g__8665__auto___13591){
return (function (seq13540){
return cljs.spec.gen.alpha.keyword_ns.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq13540));
});})(g__8665__auto___13591))
;


var g__8665__auto___13595 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.large_integer !== 'undefined')){
return clojure.test.check.generators.large_integer;
} else {
throw (new Error([cljs.core.str.cljs$core$IFn$_invoke$arity$1("Var "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","large-integer","clojure.test.check.generators/large-integer",-865967138,null)),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" does not exist, "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","large-integer","clojure.test.check.generators/large-integer",-865967138,null))),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" never required")].join('')));
}
}),null));
/**
 * Fn returning clojure.test.check.generators/large-integer
 */
cljs.spec.gen.alpha.large_integer = ((function (g__8665__auto___13595){
return (function cljs$spec$gen$alpha$large_integer(var_args){
var args__8566__auto__ = [];
var len__8559__auto___13596 = arguments.length;
var i__8560__auto___13597 = (0);
while(true){
if((i__8560__auto___13597 < len__8559__auto___13596)){
args__8566__auto__.push((arguments[i__8560__auto___13597]));

var G__13598 = (i__8560__auto___13597 + (1));
i__8560__auto___13597 = G__13598;
continue;
} else {
}
break;
}

var argseq__8567__auto__ = ((((0) < args__8566__auto__.length))?(new cljs.core.IndexedSeq(args__8566__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.large_integer.cljs$core$IFn$_invoke$arity$variadic(argseq__8567__auto__);
});})(g__8665__auto___13595))
;

cljs.spec.gen.alpha.large_integer.cljs$core$IFn$_invoke$arity$variadic = ((function (g__8665__auto___13595){
return (function (args){
return cljs.core.deref.call(null,g__8665__auto___13595);
});})(g__8665__auto___13595))
;

cljs.spec.gen.alpha.large_integer.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.large_integer.cljs$lang$applyTo = ((function (g__8665__auto___13595){
return (function (seq13541){
return cljs.spec.gen.alpha.large_integer.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq13541));
});})(g__8665__auto___13595))
;


var g__8665__auto___13599 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.ratio !== 'undefined')){
return clojure.test.check.generators.ratio;
} else {
throw (new Error([cljs.core.str.cljs$core$IFn$_invoke$arity$1("Var "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","ratio","clojure.test.check.generators/ratio",1540966915,null)),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" does not exist, "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","ratio","clojure.test.check.generators/ratio",1540966915,null))),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" never required")].join('')));
}
}),null));
/**
 * Fn returning clojure.test.check.generators/ratio
 */
cljs.spec.gen.alpha.ratio = ((function (g__8665__auto___13599){
return (function cljs$spec$gen$alpha$ratio(var_args){
var args__8566__auto__ = [];
var len__8559__auto___13600 = arguments.length;
var i__8560__auto___13601 = (0);
while(true){
if((i__8560__auto___13601 < len__8559__auto___13600)){
args__8566__auto__.push((arguments[i__8560__auto___13601]));

var G__13602 = (i__8560__auto___13601 + (1));
i__8560__auto___13601 = G__13602;
continue;
} else {
}
break;
}

var argseq__8567__auto__ = ((((0) < args__8566__auto__.length))?(new cljs.core.IndexedSeq(args__8566__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.ratio.cljs$core$IFn$_invoke$arity$variadic(argseq__8567__auto__);
});})(g__8665__auto___13599))
;

cljs.spec.gen.alpha.ratio.cljs$core$IFn$_invoke$arity$variadic = ((function (g__8665__auto___13599){
return (function (args){
return cljs.core.deref.call(null,g__8665__auto___13599);
});})(g__8665__auto___13599))
;

cljs.spec.gen.alpha.ratio.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.ratio.cljs$lang$applyTo = ((function (g__8665__auto___13599){
return (function (seq13542){
return cljs.spec.gen.alpha.ratio.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq13542));
});})(g__8665__auto___13599))
;


var g__8665__auto___13603 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.simple_type !== 'undefined')){
return clojure.test.check.generators.simple_type;
} else {
throw (new Error([cljs.core.str.cljs$core$IFn$_invoke$arity$1("Var "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","simple-type","clojure.test.check.generators/simple-type",892572284,null)),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" does not exist, "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","simple-type","clojure.test.check.generators/simple-type",892572284,null))),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" never required")].join('')));
}
}),null));
/**
 * Fn returning clojure.test.check.generators/simple-type
 */
cljs.spec.gen.alpha.simple_type = ((function (g__8665__auto___13603){
return (function cljs$spec$gen$alpha$simple_type(var_args){
var args__8566__auto__ = [];
var len__8559__auto___13604 = arguments.length;
var i__8560__auto___13605 = (0);
while(true){
if((i__8560__auto___13605 < len__8559__auto___13604)){
args__8566__auto__.push((arguments[i__8560__auto___13605]));

var G__13606 = (i__8560__auto___13605 + (1));
i__8560__auto___13605 = G__13606;
continue;
} else {
}
break;
}

var argseq__8567__auto__ = ((((0) < args__8566__auto__.length))?(new cljs.core.IndexedSeq(args__8566__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.simple_type.cljs$core$IFn$_invoke$arity$variadic(argseq__8567__auto__);
});})(g__8665__auto___13603))
;

cljs.spec.gen.alpha.simple_type.cljs$core$IFn$_invoke$arity$variadic = ((function (g__8665__auto___13603){
return (function (args){
return cljs.core.deref.call(null,g__8665__auto___13603);
});})(g__8665__auto___13603))
;

cljs.spec.gen.alpha.simple_type.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.simple_type.cljs$lang$applyTo = ((function (g__8665__auto___13603){
return (function (seq13543){
return cljs.spec.gen.alpha.simple_type.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq13543));
});})(g__8665__auto___13603))
;


var g__8665__auto___13607 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.simple_type_printable !== 'undefined')){
return clojure.test.check.generators.simple_type_printable;
} else {
throw (new Error([cljs.core.str.cljs$core$IFn$_invoke$arity$1("Var "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","simple-type-printable","clojure.test.check.generators/simple-type-printable",-58489962,null)),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" does not exist, "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","simple-type-printable","clojure.test.check.generators/simple-type-printable",-58489962,null))),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" never required")].join('')));
}
}),null));
/**
 * Fn returning clojure.test.check.generators/simple-type-printable
 */
cljs.spec.gen.alpha.simple_type_printable = ((function (g__8665__auto___13607){
return (function cljs$spec$gen$alpha$simple_type_printable(var_args){
var args__8566__auto__ = [];
var len__8559__auto___13608 = arguments.length;
var i__8560__auto___13609 = (0);
while(true){
if((i__8560__auto___13609 < len__8559__auto___13608)){
args__8566__auto__.push((arguments[i__8560__auto___13609]));

var G__13610 = (i__8560__auto___13609 + (1));
i__8560__auto___13609 = G__13610;
continue;
} else {
}
break;
}

var argseq__8567__auto__ = ((((0) < args__8566__auto__.length))?(new cljs.core.IndexedSeq(args__8566__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.simple_type_printable.cljs$core$IFn$_invoke$arity$variadic(argseq__8567__auto__);
});})(g__8665__auto___13607))
;

cljs.spec.gen.alpha.simple_type_printable.cljs$core$IFn$_invoke$arity$variadic = ((function (g__8665__auto___13607){
return (function (args){
return cljs.core.deref.call(null,g__8665__auto___13607);
});})(g__8665__auto___13607))
;

cljs.spec.gen.alpha.simple_type_printable.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.simple_type_printable.cljs$lang$applyTo = ((function (g__8665__auto___13607){
return (function (seq13544){
return cljs.spec.gen.alpha.simple_type_printable.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq13544));
});})(g__8665__auto___13607))
;


var g__8665__auto___13611 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.string !== 'undefined')){
return clojure.test.check.generators.string;
} else {
throw (new Error([cljs.core.str.cljs$core$IFn$_invoke$arity$1("Var "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","string","clojure.test.check.generators/string",-1704750979,null)),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" does not exist, "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","string","clojure.test.check.generators/string",-1704750979,null))),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" never required")].join('')));
}
}),null));
/**
 * Fn returning clojure.test.check.generators/string
 */
cljs.spec.gen.alpha.string = ((function (g__8665__auto___13611){
return (function cljs$spec$gen$alpha$string(var_args){
var args__8566__auto__ = [];
var len__8559__auto___13612 = arguments.length;
var i__8560__auto___13613 = (0);
while(true){
if((i__8560__auto___13613 < len__8559__auto___13612)){
args__8566__auto__.push((arguments[i__8560__auto___13613]));

var G__13614 = (i__8560__auto___13613 + (1));
i__8560__auto___13613 = G__13614;
continue;
} else {
}
break;
}

var argseq__8567__auto__ = ((((0) < args__8566__auto__.length))?(new cljs.core.IndexedSeq(args__8566__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.string.cljs$core$IFn$_invoke$arity$variadic(argseq__8567__auto__);
});})(g__8665__auto___13611))
;

cljs.spec.gen.alpha.string.cljs$core$IFn$_invoke$arity$variadic = ((function (g__8665__auto___13611){
return (function (args){
return cljs.core.deref.call(null,g__8665__auto___13611);
});})(g__8665__auto___13611))
;

cljs.spec.gen.alpha.string.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.string.cljs$lang$applyTo = ((function (g__8665__auto___13611){
return (function (seq13545){
return cljs.spec.gen.alpha.string.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq13545));
});})(g__8665__auto___13611))
;


var g__8665__auto___13615 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.string_ascii !== 'undefined')){
return clojure.test.check.generators.string_ascii;
} else {
throw (new Error([cljs.core.str.cljs$core$IFn$_invoke$arity$1("Var "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","string-ascii","clojure.test.check.generators/string-ascii",-2009877640,null)),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" does not exist, "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","string-ascii","clojure.test.check.generators/string-ascii",-2009877640,null))),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" never required")].join('')));
}
}),null));
/**
 * Fn returning clojure.test.check.generators/string-ascii
 */
cljs.spec.gen.alpha.string_ascii = ((function (g__8665__auto___13615){
return (function cljs$spec$gen$alpha$string_ascii(var_args){
var args__8566__auto__ = [];
var len__8559__auto___13616 = arguments.length;
var i__8560__auto___13617 = (0);
while(true){
if((i__8560__auto___13617 < len__8559__auto___13616)){
args__8566__auto__.push((arguments[i__8560__auto___13617]));

var G__13618 = (i__8560__auto___13617 + (1));
i__8560__auto___13617 = G__13618;
continue;
} else {
}
break;
}

var argseq__8567__auto__ = ((((0) < args__8566__auto__.length))?(new cljs.core.IndexedSeq(args__8566__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.string_ascii.cljs$core$IFn$_invoke$arity$variadic(argseq__8567__auto__);
});})(g__8665__auto___13615))
;

cljs.spec.gen.alpha.string_ascii.cljs$core$IFn$_invoke$arity$variadic = ((function (g__8665__auto___13615){
return (function (args){
return cljs.core.deref.call(null,g__8665__auto___13615);
});})(g__8665__auto___13615))
;

cljs.spec.gen.alpha.string_ascii.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.string_ascii.cljs$lang$applyTo = ((function (g__8665__auto___13615){
return (function (seq13546){
return cljs.spec.gen.alpha.string_ascii.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq13546));
});})(g__8665__auto___13615))
;


var g__8665__auto___13619 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.string_alphanumeric !== 'undefined')){
return clojure.test.check.generators.string_alphanumeric;
} else {
throw (new Error([cljs.core.str.cljs$core$IFn$_invoke$arity$1("Var "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","string-alphanumeric","clojure.test.check.generators/string-alphanumeric",836374939,null)),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" does not exist, "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","string-alphanumeric","clojure.test.check.generators/string-alphanumeric",836374939,null))),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" never required")].join('')));
}
}),null));
/**
 * Fn returning clojure.test.check.generators/string-alphanumeric
 */
cljs.spec.gen.alpha.string_alphanumeric = ((function (g__8665__auto___13619){
return (function cljs$spec$gen$alpha$string_alphanumeric(var_args){
var args__8566__auto__ = [];
var len__8559__auto___13620 = arguments.length;
var i__8560__auto___13621 = (0);
while(true){
if((i__8560__auto___13621 < len__8559__auto___13620)){
args__8566__auto__.push((arguments[i__8560__auto___13621]));

var G__13622 = (i__8560__auto___13621 + (1));
i__8560__auto___13621 = G__13622;
continue;
} else {
}
break;
}

var argseq__8567__auto__ = ((((0) < args__8566__auto__.length))?(new cljs.core.IndexedSeq(args__8566__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.string_alphanumeric.cljs$core$IFn$_invoke$arity$variadic(argseq__8567__auto__);
});})(g__8665__auto___13619))
;

cljs.spec.gen.alpha.string_alphanumeric.cljs$core$IFn$_invoke$arity$variadic = ((function (g__8665__auto___13619){
return (function (args){
return cljs.core.deref.call(null,g__8665__auto___13619);
});})(g__8665__auto___13619))
;

cljs.spec.gen.alpha.string_alphanumeric.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.string_alphanumeric.cljs$lang$applyTo = ((function (g__8665__auto___13619){
return (function (seq13547){
return cljs.spec.gen.alpha.string_alphanumeric.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq13547));
});})(g__8665__auto___13619))
;


var g__8665__auto___13623 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.symbol !== 'undefined')){
return clojure.test.check.generators.symbol;
} else {
throw (new Error([cljs.core.str.cljs$core$IFn$_invoke$arity$1("Var "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","symbol","clojure.test.check.generators/symbol",-1305461065,null)),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" does not exist, "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","symbol","clojure.test.check.generators/symbol",-1305461065,null))),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" never required")].join('')));
}
}),null));
/**
 * Fn returning clojure.test.check.generators/symbol
 */
cljs.spec.gen.alpha.symbol = ((function (g__8665__auto___13623){
return (function cljs$spec$gen$alpha$symbol(var_args){
var args__8566__auto__ = [];
var len__8559__auto___13624 = arguments.length;
var i__8560__auto___13625 = (0);
while(true){
if((i__8560__auto___13625 < len__8559__auto___13624)){
args__8566__auto__.push((arguments[i__8560__auto___13625]));

var G__13626 = (i__8560__auto___13625 + (1));
i__8560__auto___13625 = G__13626;
continue;
} else {
}
break;
}

var argseq__8567__auto__ = ((((0) < args__8566__auto__.length))?(new cljs.core.IndexedSeq(args__8566__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.symbol.cljs$core$IFn$_invoke$arity$variadic(argseq__8567__auto__);
});})(g__8665__auto___13623))
;

cljs.spec.gen.alpha.symbol.cljs$core$IFn$_invoke$arity$variadic = ((function (g__8665__auto___13623){
return (function (args){
return cljs.core.deref.call(null,g__8665__auto___13623);
});})(g__8665__auto___13623))
;

cljs.spec.gen.alpha.symbol.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.symbol.cljs$lang$applyTo = ((function (g__8665__auto___13623){
return (function (seq13548){
return cljs.spec.gen.alpha.symbol.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq13548));
});})(g__8665__auto___13623))
;


var g__8665__auto___13627 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.symbol_ns !== 'undefined')){
return clojure.test.check.generators.symbol_ns;
} else {
throw (new Error([cljs.core.str.cljs$core$IFn$_invoke$arity$1("Var "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","symbol-ns","clojure.test.check.generators/symbol-ns",-862629490,null)),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" does not exist, "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","symbol-ns","clojure.test.check.generators/symbol-ns",-862629490,null))),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" never required")].join('')));
}
}),null));
/**
 * Fn returning clojure.test.check.generators/symbol-ns
 */
cljs.spec.gen.alpha.symbol_ns = ((function (g__8665__auto___13627){
return (function cljs$spec$gen$alpha$symbol_ns(var_args){
var args__8566__auto__ = [];
var len__8559__auto___13628 = arguments.length;
var i__8560__auto___13629 = (0);
while(true){
if((i__8560__auto___13629 < len__8559__auto___13628)){
args__8566__auto__.push((arguments[i__8560__auto___13629]));

var G__13630 = (i__8560__auto___13629 + (1));
i__8560__auto___13629 = G__13630;
continue;
} else {
}
break;
}

var argseq__8567__auto__ = ((((0) < args__8566__auto__.length))?(new cljs.core.IndexedSeq(args__8566__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.symbol_ns.cljs$core$IFn$_invoke$arity$variadic(argseq__8567__auto__);
});})(g__8665__auto___13627))
;

cljs.spec.gen.alpha.symbol_ns.cljs$core$IFn$_invoke$arity$variadic = ((function (g__8665__auto___13627){
return (function (args){
return cljs.core.deref.call(null,g__8665__auto___13627);
});})(g__8665__auto___13627))
;

cljs.spec.gen.alpha.symbol_ns.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.symbol_ns.cljs$lang$applyTo = ((function (g__8665__auto___13627){
return (function (seq13549){
return cljs.spec.gen.alpha.symbol_ns.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq13549));
});})(g__8665__auto___13627))
;


var g__8665__auto___13631 = (new cljs.spec.gen.alpha.LazyVar((function (){
if((typeof clojure.test !== 'undefined') && (typeof clojure.test.check !== 'undefined') && (typeof clojure.test.check.generators.uuid !== 'undefined')){
return clojure.test.check.generators.uuid;
} else {
throw (new Error([cljs.core.str.cljs$core$IFn$_invoke$arity$1("Var "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Symbol("clojure.test.check.generators","uuid","clojure.test.check.generators/uuid",1589373144,null)),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" does not exist, "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.namespace.call(null,new cljs.core.Symbol("clojure.test.check.generators","uuid","clojure.test.check.generators/uuid",1589373144,null))),cljs.core.str.cljs$core$IFn$_invoke$arity$1(" never required")].join('')));
}
}),null));
/**
 * Fn returning clojure.test.check.generators/uuid
 */
cljs.spec.gen.alpha.uuid = ((function (g__8665__auto___13631){
return (function cljs$spec$gen$alpha$uuid(var_args){
var args__8566__auto__ = [];
var len__8559__auto___13632 = arguments.length;
var i__8560__auto___13633 = (0);
while(true){
if((i__8560__auto___13633 < len__8559__auto___13632)){
args__8566__auto__.push((arguments[i__8560__auto___13633]));

var G__13634 = (i__8560__auto___13633 + (1));
i__8560__auto___13633 = G__13634;
continue;
} else {
}
break;
}

var argseq__8567__auto__ = ((((0) < args__8566__auto__.length))?(new cljs.core.IndexedSeq(args__8566__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.uuid.cljs$core$IFn$_invoke$arity$variadic(argseq__8567__auto__);
});})(g__8665__auto___13631))
;

cljs.spec.gen.alpha.uuid.cljs$core$IFn$_invoke$arity$variadic = ((function (g__8665__auto___13631){
return (function (args){
return cljs.core.deref.call(null,g__8665__auto___13631);
});})(g__8665__auto___13631))
;

cljs.spec.gen.alpha.uuid.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.uuid.cljs$lang$applyTo = ((function (g__8665__auto___13631){
return (function (seq13550){
return cljs.spec.gen.alpha.uuid.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq13550));
});})(g__8665__auto___13631))
;

/**
 * Returns a generator of a sequence catenated from results of
 * gens, each of which should generate something sequential.
 */
cljs.spec.gen.alpha.cat = (function cljs$spec$gen$alpha$cat(var_args){
var args__8566__auto__ = [];
var len__8559__auto___13637 = arguments.length;
var i__8560__auto___13638 = (0);
while(true){
if((i__8560__auto___13638 < len__8559__auto___13637)){
args__8566__auto__.push((arguments[i__8560__auto___13638]));

var G__13639 = (i__8560__auto___13638 + (1));
i__8560__auto___13638 = G__13639;
continue;
} else {
}
break;
}

var argseq__8567__auto__ = ((((0) < args__8566__auto__.length))?(new cljs.core.IndexedSeq(args__8566__auto__.slice((0)),(0),null)):null);
return cljs.spec.gen.alpha.cat.cljs$core$IFn$_invoke$arity$variadic(argseq__8567__auto__);
});

cljs.spec.gen.alpha.cat.cljs$core$IFn$_invoke$arity$variadic = (function (gens){
return cljs.spec.gen.alpha.fmap.call(null,(function (p1__13635_SHARP_){
return cljs.core.apply.call(null,cljs.core.concat,p1__13635_SHARP_);
}),cljs.core.apply.call(null,cljs.spec.gen.alpha.tuple,gens));
});

cljs.spec.gen.alpha.cat.cljs$lang$maxFixedArity = (0);

cljs.spec.gen.alpha.cat.cljs$lang$applyTo = (function (seq13636){
return cljs.spec.gen.alpha.cat.cljs$core$IFn$_invoke$arity$variadic(cljs.core.seq.call(null,seq13636));
});

cljs.spec.gen.alpha.qualified_QMARK_ = (function cljs$spec$gen$alpha$qualified_QMARK_(ident){
return !((cljs.core.namespace.call(null,ident) == null));
});
cljs.spec.gen.alpha.gen_builtins = (new cljs.core.Delay((function (){
var simple = cljs.spec.gen.alpha.simple_type_printable.call(null);
return cljs.core.PersistentHashMap.fromArrays([cljs.core.qualified_keyword_QMARK_,cljs.core.seq_QMARK_,cljs.core.vector_QMARK_,cljs.core.any_QMARK_,cljs.core.boolean_QMARK_,cljs.core.char_QMARK_,cljs.core.inst_QMARK_,cljs.core.simple_symbol_QMARK_,cljs.core.sequential_QMARK_,cljs.core.float_QMARK_,cljs.core.set_QMARK_,cljs.core.map_QMARK_,cljs.core.empty_QMARK_,cljs.core.string_QMARK_,cljs.core.double_QMARK_,cljs.core.int_QMARK_,cljs.core.associative_QMARK_,cljs.core.keyword_QMARK_,cljs.core.indexed_QMARK_,cljs.core.zero_QMARK_,cljs.core.simple_keyword_QMARK_,cljs.core.neg_int_QMARK_,cljs.core.nil_QMARK_,cljs.core.ident_QMARK_,cljs.core.qualified_ident_QMARK_,cljs.core.true_QMARK_,cljs.core.integer_QMARK_,cljs.core.nat_int_QMARK_,cljs.core.pos_int_QMARK_,cljs.core.uuid_QMARK_,cljs.core.false_QMARK_,cljs.core.list_QMARK_,cljs.core.simple_ident_QMARK_,cljs.core.number_QMARK_,cljs.core.qualified_symbol_QMARK_,cljs.core.seqable_QMARK_,cljs.core.symbol_QMARK_,cljs.core.coll_QMARK_],[cljs.spec.gen.alpha.such_that.call(null,cljs.spec.gen.alpha.qualified_QMARK_,cljs.spec.gen.alpha.keyword_ns.call(null)),cljs.spec.gen.alpha.list.call(null,simple),cljs.spec.gen.alpha.vector.call(null,simple),cljs.spec.gen.alpha.one_of.call(null,new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [cljs.spec.gen.alpha.return$.call(null,null),cljs.spec.gen.alpha.any_printable.call(null)], null)),cljs.spec.gen.alpha.boolean$.call(null),cljs.spec.gen.alpha.char$.call(null),cljs.spec.gen.alpha.fmap.call(null,((function (simple){
return (function (p1__13640_SHARP_){
return (new Date(p1__13640_SHARP_));
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