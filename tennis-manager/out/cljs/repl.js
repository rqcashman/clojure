// Compiled by ClojureScript 1.9.542 {}
goog.provide('cljs.repl');
goog.require('cljs.core');
goog.require('cljs.spec.alpha');
cljs.repl.print_doc = (function cljs$repl$print_doc(p__12610){
var map__12635 = p__12610;
var map__12635__$1 = ((((!((map__12635 == null)))?((((map__12635.cljs$lang$protocol_mask$partition0$ & (64))) || ((cljs.core.PROTOCOL_SENTINEL === map__12635.cljs$core$ISeq$)))?true:false):false))?cljs.core.apply.call(null,cljs.core.hash_map,map__12635):map__12635);
var m = map__12635__$1;
var n = cljs.core.get.call(null,map__12635__$1,new cljs.core.Keyword(null,"ns","ns",441598760));
var nm = cljs.core.get.call(null,map__12635__$1,new cljs.core.Keyword(null,"name","name",1843675177));
cljs.core.println.call(null,"-------------------------");

cljs.core.println.call(null,[cljs.core.str.cljs$core$IFn$_invoke$arity$1((function (){var temp__4657__auto__ = new cljs.core.Keyword(null,"ns","ns",441598760).cljs$core$IFn$_invoke$arity$1(m);
if(cljs.core.truth_(temp__4657__auto__)){
var ns = temp__4657__auto__;
return [cljs.core.str.cljs$core$IFn$_invoke$arity$1(ns),cljs.core.str.cljs$core$IFn$_invoke$arity$1("/")].join('');
} else {
return null;
}
})()),cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Keyword(null,"name","name",1843675177).cljs$core$IFn$_invoke$arity$1(m))].join(''));

if(cljs.core.truth_(new cljs.core.Keyword(null,"protocol","protocol",652470118).cljs$core$IFn$_invoke$arity$1(m))){
cljs.core.println.call(null,"Protocol");
} else {
}

if(cljs.core.truth_(new cljs.core.Keyword(null,"forms","forms",2045992350).cljs$core$IFn$_invoke$arity$1(m))){
var seq__12637_12659 = cljs.core.seq.call(null,new cljs.core.Keyword(null,"forms","forms",2045992350).cljs$core$IFn$_invoke$arity$1(m));
var chunk__12638_12660 = null;
var count__12639_12661 = (0);
var i__12640_12662 = (0);
while(true){
if((i__12640_12662 < count__12639_12661)){
var f_12663 = cljs.core._nth.call(null,chunk__12638_12660,i__12640_12662);
cljs.core.println.call(null,"  ",f_12663);

var G__12664 = seq__12637_12659;
var G__12665 = chunk__12638_12660;
var G__12666 = count__12639_12661;
var G__12667 = (i__12640_12662 + (1));
seq__12637_12659 = G__12664;
chunk__12638_12660 = G__12665;
count__12639_12661 = G__12666;
i__12640_12662 = G__12667;
continue;
} else {
var temp__4657__auto___12668 = cljs.core.seq.call(null,seq__12637_12659);
if(temp__4657__auto___12668){
var seq__12637_12669__$1 = temp__4657__auto___12668;
if(cljs.core.chunked_seq_QMARK_.call(null,seq__12637_12669__$1)){
var c__8362__auto___12670 = cljs.core.chunk_first.call(null,seq__12637_12669__$1);
var G__12671 = cljs.core.chunk_rest.call(null,seq__12637_12669__$1);
var G__12672 = c__8362__auto___12670;
var G__12673 = cljs.core.count.call(null,c__8362__auto___12670);
var G__12674 = (0);
seq__12637_12659 = G__12671;
chunk__12638_12660 = G__12672;
count__12639_12661 = G__12673;
i__12640_12662 = G__12674;
continue;
} else {
var f_12675 = cljs.core.first.call(null,seq__12637_12669__$1);
cljs.core.println.call(null,"  ",f_12675);

var G__12676 = cljs.core.next.call(null,seq__12637_12669__$1);
var G__12677 = null;
var G__12678 = (0);
var G__12679 = (0);
seq__12637_12659 = G__12676;
chunk__12638_12660 = G__12677;
count__12639_12661 = G__12678;
i__12640_12662 = G__12679;
continue;
}
} else {
}
}
break;
}
} else {
if(cljs.core.truth_(new cljs.core.Keyword(null,"arglists","arglists",1661989754).cljs$core$IFn$_invoke$arity$1(m))){
var arglists_12680 = new cljs.core.Keyword(null,"arglists","arglists",1661989754).cljs$core$IFn$_invoke$arity$1(m);
if(cljs.core.truth_((function (){var or__7543__auto__ = new cljs.core.Keyword(null,"macro","macro",-867863404).cljs$core$IFn$_invoke$arity$1(m);
if(cljs.core.truth_(or__7543__auto__)){
return or__7543__auto__;
} else {
return new cljs.core.Keyword(null,"repl-special-function","repl-special-function",1262603725).cljs$core$IFn$_invoke$arity$1(m);
}
})())){
cljs.core.prn.call(null,arglists_12680);
} else {
cljs.core.prn.call(null,((cljs.core._EQ_.call(null,new cljs.core.Symbol(null,"quote","quote",1377916282,null),cljs.core.first.call(null,arglists_12680)))?cljs.core.second.call(null,arglists_12680):arglists_12680));
}
} else {
}
}

if(cljs.core.truth_(new cljs.core.Keyword(null,"special-form","special-form",-1326536374).cljs$core$IFn$_invoke$arity$1(m))){
cljs.core.println.call(null,"Special Form");

cljs.core.println.call(null," ",new cljs.core.Keyword(null,"doc","doc",1913296891).cljs$core$IFn$_invoke$arity$1(m));

if(cljs.core.contains_QMARK_.call(null,m,new cljs.core.Keyword(null,"url","url",276297046))){
if(cljs.core.truth_(new cljs.core.Keyword(null,"url","url",276297046).cljs$core$IFn$_invoke$arity$1(m))){
return cljs.core.println.call(null,[cljs.core.str.cljs$core$IFn$_invoke$arity$1("\n  Please see http://clojure.org/"),cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Keyword(null,"url","url",276297046).cljs$core$IFn$_invoke$arity$1(m))].join(''));
} else {
return null;
}
} else {
return cljs.core.println.call(null,[cljs.core.str.cljs$core$IFn$_invoke$arity$1("\n  Please see http://clojure.org/special_forms#"),cljs.core.str.cljs$core$IFn$_invoke$arity$1(new cljs.core.Keyword(null,"name","name",1843675177).cljs$core$IFn$_invoke$arity$1(m))].join(''));
}
} else {
if(cljs.core.truth_(new cljs.core.Keyword(null,"macro","macro",-867863404).cljs$core$IFn$_invoke$arity$1(m))){
cljs.core.println.call(null,"Macro");
} else {
}

if(cljs.core.truth_(new cljs.core.Keyword(null,"repl-special-function","repl-special-function",1262603725).cljs$core$IFn$_invoke$arity$1(m))){
cljs.core.println.call(null,"REPL Special Function");
} else {
}

cljs.core.println.call(null," ",new cljs.core.Keyword(null,"doc","doc",1913296891).cljs$core$IFn$_invoke$arity$1(m));

if(cljs.core.truth_(new cljs.core.Keyword(null,"protocol","protocol",652470118).cljs$core$IFn$_invoke$arity$1(m))){
var seq__12641_12681 = cljs.core.seq.call(null,new cljs.core.Keyword(null,"methods","methods",453930866).cljs$core$IFn$_invoke$arity$1(m));
var chunk__12642_12682 = null;
var count__12643_12683 = (0);
var i__12644_12684 = (0);
while(true){
if((i__12644_12684 < count__12643_12683)){
var vec__12645_12685 = cljs.core._nth.call(null,chunk__12642_12682,i__12644_12684);
var name_12686 = cljs.core.nth.call(null,vec__12645_12685,(0),null);
var map__12648_12687 = cljs.core.nth.call(null,vec__12645_12685,(1),null);
var map__12648_12688__$1 = ((((!((map__12648_12687 == null)))?((((map__12648_12687.cljs$lang$protocol_mask$partition0$ & (64))) || ((cljs.core.PROTOCOL_SENTINEL === map__12648_12687.cljs$core$ISeq$)))?true:false):false))?cljs.core.apply.call(null,cljs.core.hash_map,map__12648_12687):map__12648_12687);
var doc_12689 = cljs.core.get.call(null,map__12648_12688__$1,new cljs.core.Keyword(null,"doc","doc",1913296891));
var arglists_12690 = cljs.core.get.call(null,map__12648_12688__$1,new cljs.core.Keyword(null,"arglists","arglists",1661989754));
cljs.core.println.call(null);

cljs.core.println.call(null," ",name_12686);

cljs.core.println.call(null," ",arglists_12690);

if(cljs.core.truth_(doc_12689)){
cljs.core.println.call(null," ",doc_12689);
} else {
}

var G__12691 = seq__12641_12681;
var G__12692 = chunk__12642_12682;
var G__12693 = count__12643_12683;
var G__12694 = (i__12644_12684 + (1));
seq__12641_12681 = G__12691;
chunk__12642_12682 = G__12692;
count__12643_12683 = G__12693;
i__12644_12684 = G__12694;
continue;
} else {
var temp__4657__auto___12695 = cljs.core.seq.call(null,seq__12641_12681);
if(temp__4657__auto___12695){
var seq__12641_12696__$1 = temp__4657__auto___12695;
if(cljs.core.chunked_seq_QMARK_.call(null,seq__12641_12696__$1)){
var c__8362__auto___12697 = cljs.core.chunk_first.call(null,seq__12641_12696__$1);
var G__12698 = cljs.core.chunk_rest.call(null,seq__12641_12696__$1);
var G__12699 = c__8362__auto___12697;
var G__12700 = cljs.core.count.call(null,c__8362__auto___12697);
var G__12701 = (0);
seq__12641_12681 = G__12698;
chunk__12642_12682 = G__12699;
count__12643_12683 = G__12700;
i__12644_12684 = G__12701;
continue;
} else {
var vec__12650_12702 = cljs.core.first.call(null,seq__12641_12696__$1);
var name_12703 = cljs.core.nth.call(null,vec__12650_12702,(0),null);
var map__12653_12704 = cljs.core.nth.call(null,vec__12650_12702,(1),null);
var map__12653_12705__$1 = ((((!((map__12653_12704 == null)))?((((map__12653_12704.cljs$lang$protocol_mask$partition0$ & (64))) || ((cljs.core.PROTOCOL_SENTINEL === map__12653_12704.cljs$core$ISeq$)))?true:false):false))?cljs.core.apply.call(null,cljs.core.hash_map,map__12653_12704):map__12653_12704);
var doc_12706 = cljs.core.get.call(null,map__12653_12705__$1,new cljs.core.Keyword(null,"doc","doc",1913296891));
var arglists_12707 = cljs.core.get.call(null,map__12653_12705__$1,new cljs.core.Keyword(null,"arglists","arglists",1661989754));
cljs.core.println.call(null);

cljs.core.println.call(null," ",name_12703);

cljs.core.println.call(null," ",arglists_12707);

if(cljs.core.truth_(doc_12706)){
cljs.core.println.call(null," ",doc_12706);
} else {
}

var G__12708 = cljs.core.next.call(null,seq__12641_12696__$1);
var G__12709 = null;
var G__12710 = (0);
var G__12711 = (0);
seq__12641_12681 = G__12708;
chunk__12642_12682 = G__12709;
count__12643_12683 = G__12710;
i__12644_12684 = G__12711;
continue;
}
} else {
}
}
break;
}
} else {
}

if(cljs.core.truth_(n)){
var temp__4657__auto__ = cljs.spec.alpha.get_spec.call(null,cljs.core.symbol.call(null,[cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.ns_name.call(null,n))].join(''),cljs.core.name.call(null,nm)));
if(cljs.core.truth_(temp__4657__auto__)){
var fnspec = temp__4657__auto__;
cljs.core.print.call(null,"Spec");

var seq__12655 = cljs.core.seq.call(null,new cljs.core.PersistentVector(null, 3, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"args","args",1315556576),new cljs.core.Keyword(null,"ret","ret",-468222814),new cljs.core.Keyword(null,"fn","fn",-1175266204)], null));
var chunk__12656 = null;
var count__12657 = (0);
var i__12658 = (0);
while(true){
if((i__12658 < count__12657)){
var role = cljs.core._nth.call(null,chunk__12656,i__12658);
var temp__4657__auto___12712__$1 = cljs.core.get.call(null,fnspec,role);
if(cljs.core.truth_(temp__4657__auto___12712__$1)){
var spec_12713 = temp__4657__auto___12712__$1;
cljs.core.print.call(null,[cljs.core.str.cljs$core$IFn$_invoke$arity$1("\n "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.name.call(null,role)),cljs.core.str.cljs$core$IFn$_invoke$arity$1(":")].join(''),cljs.spec.alpha.describe.call(null,spec_12713));
} else {
}

var G__12714 = seq__12655;
var G__12715 = chunk__12656;
var G__12716 = count__12657;
var G__12717 = (i__12658 + (1));
seq__12655 = G__12714;
chunk__12656 = G__12715;
count__12657 = G__12716;
i__12658 = G__12717;
continue;
} else {
var temp__4657__auto____$1 = cljs.core.seq.call(null,seq__12655);
if(temp__4657__auto____$1){
var seq__12655__$1 = temp__4657__auto____$1;
if(cljs.core.chunked_seq_QMARK_.call(null,seq__12655__$1)){
var c__8362__auto__ = cljs.core.chunk_first.call(null,seq__12655__$1);
var G__12718 = cljs.core.chunk_rest.call(null,seq__12655__$1);
var G__12719 = c__8362__auto__;
var G__12720 = cljs.core.count.call(null,c__8362__auto__);
var G__12721 = (0);
seq__12655 = G__12718;
chunk__12656 = G__12719;
count__12657 = G__12720;
i__12658 = G__12721;
continue;
} else {
var role = cljs.core.first.call(null,seq__12655__$1);
var temp__4657__auto___12722__$2 = cljs.core.get.call(null,fnspec,role);
if(cljs.core.truth_(temp__4657__auto___12722__$2)){
var spec_12723 = temp__4657__auto___12722__$2;
cljs.core.print.call(null,[cljs.core.str.cljs$core$IFn$_invoke$arity$1("\n "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.name.call(null,role)),cljs.core.str.cljs$core$IFn$_invoke$arity$1(":")].join(''),cljs.spec.alpha.describe.call(null,spec_12723));
} else {
}

var G__12724 = cljs.core.next.call(null,seq__12655__$1);
var G__12725 = null;
var G__12726 = (0);
var G__12727 = (0);
seq__12655 = G__12724;
chunk__12656 = G__12725;
count__12657 = G__12726;
i__12658 = G__12727;
continue;
}
} else {
return null;
}
}
break;
}
} else {
return null;
}
} else {
return null;
}
}
});

//# sourceMappingURL=repl.js.map