// Compiled by ClojureScript 1.9.542 {}
goog.provide('cljs.repl');
goog.require('cljs.core');
goog.require('cljs.spec.alpha');
cljs.repl.print_doc = (function cljs$repl$print_doc(p__14618){
var map__14643 = p__14618;
var map__14643__$1 = ((((!((map__14643 == null)))?((((map__14643.cljs$lang$protocol_mask$partition0$ & (64))) || ((cljs.core.PROTOCOL_SENTINEL === map__14643.cljs$core$ISeq$)))?true:false):false))?cljs.core.apply.call(null,cljs.core.hash_map,map__14643):map__14643);
var m = map__14643__$1;
var n = cljs.core.get.call(null,map__14643__$1,new cljs.core.Keyword(null,"ns","ns",441598760));
var nm = cljs.core.get.call(null,map__14643__$1,new cljs.core.Keyword(null,"name","name",1843675177));
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
var seq__14645_14667 = cljs.core.seq.call(null,new cljs.core.Keyword(null,"forms","forms",2045992350).cljs$core$IFn$_invoke$arity$1(m));
var chunk__14646_14668 = null;
var count__14647_14669 = (0);
var i__14648_14670 = (0);
while(true){
if((i__14648_14670 < count__14647_14669)){
var f_14671 = cljs.core._nth.call(null,chunk__14646_14668,i__14648_14670);
cljs.core.println.call(null,"  ",f_14671);

var G__14672 = seq__14645_14667;
var G__14673 = chunk__14646_14668;
var G__14674 = count__14647_14669;
var G__14675 = (i__14648_14670 + (1));
seq__14645_14667 = G__14672;
chunk__14646_14668 = G__14673;
count__14647_14669 = G__14674;
i__14648_14670 = G__14675;
continue;
} else {
var temp__4657__auto___14676 = cljs.core.seq.call(null,seq__14645_14667);
if(temp__4657__auto___14676){
var seq__14645_14677__$1 = temp__4657__auto___14676;
if(cljs.core.chunked_seq_QMARK_.call(null,seq__14645_14677__$1)){
var c__8362__auto___14678 = cljs.core.chunk_first.call(null,seq__14645_14677__$1);
var G__14679 = cljs.core.chunk_rest.call(null,seq__14645_14677__$1);
var G__14680 = c__8362__auto___14678;
var G__14681 = cljs.core.count.call(null,c__8362__auto___14678);
var G__14682 = (0);
seq__14645_14667 = G__14679;
chunk__14646_14668 = G__14680;
count__14647_14669 = G__14681;
i__14648_14670 = G__14682;
continue;
} else {
var f_14683 = cljs.core.first.call(null,seq__14645_14677__$1);
cljs.core.println.call(null,"  ",f_14683);

var G__14684 = cljs.core.next.call(null,seq__14645_14677__$1);
var G__14685 = null;
var G__14686 = (0);
var G__14687 = (0);
seq__14645_14667 = G__14684;
chunk__14646_14668 = G__14685;
count__14647_14669 = G__14686;
i__14648_14670 = G__14687;
continue;
}
} else {
}
}
break;
}
} else {
if(cljs.core.truth_(new cljs.core.Keyword(null,"arglists","arglists",1661989754).cljs$core$IFn$_invoke$arity$1(m))){
var arglists_14688 = new cljs.core.Keyword(null,"arglists","arglists",1661989754).cljs$core$IFn$_invoke$arity$1(m);
if(cljs.core.truth_((function (){var or__7543__auto__ = new cljs.core.Keyword(null,"macro","macro",-867863404).cljs$core$IFn$_invoke$arity$1(m);
if(cljs.core.truth_(or__7543__auto__)){
return or__7543__auto__;
} else {
return new cljs.core.Keyword(null,"repl-special-function","repl-special-function",1262603725).cljs$core$IFn$_invoke$arity$1(m);
}
})())){
cljs.core.prn.call(null,arglists_14688);
} else {
cljs.core.prn.call(null,((cljs.core._EQ_.call(null,new cljs.core.Symbol(null,"quote","quote",1377916282,null),cljs.core.first.call(null,arglists_14688)))?cljs.core.second.call(null,arglists_14688):arglists_14688));
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
var seq__14649_14689 = cljs.core.seq.call(null,new cljs.core.Keyword(null,"methods","methods",453930866).cljs$core$IFn$_invoke$arity$1(m));
var chunk__14650_14690 = null;
var count__14651_14691 = (0);
var i__14652_14692 = (0);
while(true){
if((i__14652_14692 < count__14651_14691)){
var vec__14653_14693 = cljs.core._nth.call(null,chunk__14650_14690,i__14652_14692);
var name_14694 = cljs.core.nth.call(null,vec__14653_14693,(0),null);
var map__14656_14695 = cljs.core.nth.call(null,vec__14653_14693,(1),null);
var map__14656_14696__$1 = ((((!((map__14656_14695 == null)))?((((map__14656_14695.cljs$lang$protocol_mask$partition0$ & (64))) || ((cljs.core.PROTOCOL_SENTINEL === map__14656_14695.cljs$core$ISeq$)))?true:false):false))?cljs.core.apply.call(null,cljs.core.hash_map,map__14656_14695):map__14656_14695);
var doc_14697 = cljs.core.get.call(null,map__14656_14696__$1,new cljs.core.Keyword(null,"doc","doc",1913296891));
var arglists_14698 = cljs.core.get.call(null,map__14656_14696__$1,new cljs.core.Keyword(null,"arglists","arglists",1661989754));
cljs.core.println.call(null);

cljs.core.println.call(null," ",name_14694);

cljs.core.println.call(null," ",arglists_14698);

if(cljs.core.truth_(doc_14697)){
cljs.core.println.call(null," ",doc_14697);
} else {
}

var G__14699 = seq__14649_14689;
var G__14700 = chunk__14650_14690;
var G__14701 = count__14651_14691;
var G__14702 = (i__14652_14692 + (1));
seq__14649_14689 = G__14699;
chunk__14650_14690 = G__14700;
count__14651_14691 = G__14701;
i__14652_14692 = G__14702;
continue;
} else {
var temp__4657__auto___14703 = cljs.core.seq.call(null,seq__14649_14689);
if(temp__4657__auto___14703){
var seq__14649_14704__$1 = temp__4657__auto___14703;
if(cljs.core.chunked_seq_QMARK_.call(null,seq__14649_14704__$1)){
var c__8362__auto___14705 = cljs.core.chunk_first.call(null,seq__14649_14704__$1);
var G__14706 = cljs.core.chunk_rest.call(null,seq__14649_14704__$1);
var G__14707 = c__8362__auto___14705;
var G__14708 = cljs.core.count.call(null,c__8362__auto___14705);
var G__14709 = (0);
seq__14649_14689 = G__14706;
chunk__14650_14690 = G__14707;
count__14651_14691 = G__14708;
i__14652_14692 = G__14709;
continue;
} else {
var vec__14658_14710 = cljs.core.first.call(null,seq__14649_14704__$1);
var name_14711 = cljs.core.nth.call(null,vec__14658_14710,(0),null);
var map__14661_14712 = cljs.core.nth.call(null,vec__14658_14710,(1),null);
var map__14661_14713__$1 = ((((!((map__14661_14712 == null)))?((((map__14661_14712.cljs$lang$protocol_mask$partition0$ & (64))) || ((cljs.core.PROTOCOL_SENTINEL === map__14661_14712.cljs$core$ISeq$)))?true:false):false))?cljs.core.apply.call(null,cljs.core.hash_map,map__14661_14712):map__14661_14712);
var doc_14714 = cljs.core.get.call(null,map__14661_14713__$1,new cljs.core.Keyword(null,"doc","doc",1913296891));
var arglists_14715 = cljs.core.get.call(null,map__14661_14713__$1,new cljs.core.Keyword(null,"arglists","arglists",1661989754));
cljs.core.println.call(null);

cljs.core.println.call(null," ",name_14711);

cljs.core.println.call(null," ",arglists_14715);

if(cljs.core.truth_(doc_14714)){
cljs.core.println.call(null," ",doc_14714);
} else {
}

var G__14716 = cljs.core.next.call(null,seq__14649_14704__$1);
var G__14717 = null;
var G__14718 = (0);
var G__14719 = (0);
seq__14649_14689 = G__14716;
chunk__14650_14690 = G__14717;
count__14651_14691 = G__14718;
i__14652_14692 = G__14719;
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

var seq__14663 = cljs.core.seq.call(null,new cljs.core.PersistentVector(null, 3, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"args","args",1315556576),new cljs.core.Keyword(null,"ret","ret",-468222814),new cljs.core.Keyword(null,"fn","fn",-1175266204)], null));
var chunk__14664 = null;
var count__14665 = (0);
var i__14666 = (0);
while(true){
if((i__14666 < count__14665)){
var role = cljs.core._nth.call(null,chunk__14664,i__14666);
var temp__4657__auto___14720__$1 = cljs.core.get.call(null,fnspec,role);
if(cljs.core.truth_(temp__4657__auto___14720__$1)){
var spec_14721 = temp__4657__auto___14720__$1;
cljs.core.print.call(null,[cljs.core.str.cljs$core$IFn$_invoke$arity$1("\n "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.name.call(null,role)),cljs.core.str.cljs$core$IFn$_invoke$arity$1(":")].join(''),cljs.spec.alpha.describe.call(null,spec_14721));
} else {
}

var G__14722 = seq__14663;
var G__14723 = chunk__14664;
var G__14724 = count__14665;
var G__14725 = (i__14666 + (1));
seq__14663 = G__14722;
chunk__14664 = G__14723;
count__14665 = G__14724;
i__14666 = G__14725;
continue;
} else {
var temp__4657__auto____$1 = cljs.core.seq.call(null,seq__14663);
if(temp__4657__auto____$1){
var seq__14663__$1 = temp__4657__auto____$1;
if(cljs.core.chunked_seq_QMARK_.call(null,seq__14663__$1)){
var c__8362__auto__ = cljs.core.chunk_first.call(null,seq__14663__$1);
var G__14726 = cljs.core.chunk_rest.call(null,seq__14663__$1);
var G__14727 = c__8362__auto__;
var G__14728 = cljs.core.count.call(null,c__8362__auto__);
var G__14729 = (0);
seq__14663 = G__14726;
chunk__14664 = G__14727;
count__14665 = G__14728;
i__14666 = G__14729;
continue;
} else {
var role = cljs.core.first.call(null,seq__14663__$1);
var temp__4657__auto___14730__$2 = cljs.core.get.call(null,fnspec,role);
if(cljs.core.truth_(temp__4657__auto___14730__$2)){
var spec_14731 = temp__4657__auto___14730__$2;
cljs.core.print.call(null,[cljs.core.str.cljs$core$IFn$_invoke$arity$1("\n "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.name.call(null,role)),cljs.core.str.cljs$core$IFn$_invoke$arity$1(":")].join(''),cljs.spec.alpha.describe.call(null,spec_14731));
} else {
}

var G__14732 = cljs.core.next.call(null,seq__14663__$1);
var G__14733 = null;
var G__14734 = (0);
var G__14735 = (0);
seq__14663 = G__14732;
chunk__14664 = G__14733;
count__14665 = G__14734;
i__14666 = G__14735;
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
