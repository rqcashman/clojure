// Compiled by ClojureScript 1.9.542 {}
goog.provide('cljs.repl');
goog.require('cljs.core');
goog.require('cljs.spec.alpha');
cljs.repl.print_doc = (function cljs$repl$print_doc(p__12439){
var map__12464 = p__12439;
var map__12464__$1 = ((((!((map__12464 == null)))?((((map__12464.cljs$lang$protocol_mask$partition0$ & (64))) || ((cljs.core.PROTOCOL_SENTINEL === map__12464.cljs$core$ISeq$)))?true:false):false))?cljs.core.apply.call(null,cljs.core.hash_map,map__12464):map__12464);
var m = map__12464__$1;
var n = cljs.core.get.call(null,map__12464__$1,new cljs.core.Keyword(null,"ns","ns",441598760));
var nm = cljs.core.get.call(null,map__12464__$1,new cljs.core.Keyword(null,"name","name",1843675177));
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
var seq__12466_12488 = cljs.core.seq.call(null,new cljs.core.Keyword(null,"forms","forms",2045992350).cljs$core$IFn$_invoke$arity$1(m));
var chunk__12467_12489 = null;
var count__12468_12490 = (0);
var i__12469_12491 = (0);
while(true){
if((i__12469_12491 < count__12468_12490)){
var f_12492 = cljs.core._nth.call(null,chunk__12467_12489,i__12469_12491);
cljs.core.println.call(null,"  ",f_12492);

var G__12493 = seq__12466_12488;
var G__12494 = chunk__12467_12489;
var G__12495 = count__12468_12490;
var G__12496 = (i__12469_12491 + (1));
seq__12466_12488 = G__12493;
chunk__12467_12489 = G__12494;
count__12468_12490 = G__12495;
i__12469_12491 = G__12496;
continue;
} else {
var temp__4657__auto___12497 = cljs.core.seq.call(null,seq__12466_12488);
if(temp__4657__auto___12497){
var seq__12466_12498__$1 = temp__4657__auto___12497;
if(cljs.core.chunked_seq_QMARK_.call(null,seq__12466_12498__$1)){
var c__8265__auto___12499 = cljs.core.chunk_first.call(null,seq__12466_12498__$1);
var G__12500 = cljs.core.chunk_rest.call(null,seq__12466_12498__$1);
var G__12501 = c__8265__auto___12499;
var G__12502 = cljs.core.count.call(null,c__8265__auto___12499);
var G__12503 = (0);
seq__12466_12488 = G__12500;
chunk__12467_12489 = G__12501;
count__12468_12490 = G__12502;
i__12469_12491 = G__12503;
continue;
} else {
var f_12504 = cljs.core.first.call(null,seq__12466_12498__$1);
cljs.core.println.call(null,"  ",f_12504);

var G__12505 = cljs.core.next.call(null,seq__12466_12498__$1);
var G__12506 = null;
var G__12507 = (0);
var G__12508 = (0);
seq__12466_12488 = G__12505;
chunk__12467_12489 = G__12506;
count__12468_12490 = G__12507;
i__12469_12491 = G__12508;
continue;
}
} else {
}
}
break;
}
} else {
if(cljs.core.truth_(new cljs.core.Keyword(null,"arglists","arglists",1661989754).cljs$core$IFn$_invoke$arity$1(m))){
var arglists_12509 = new cljs.core.Keyword(null,"arglists","arglists",1661989754).cljs$core$IFn$_invoke$arity$1(m);
if(cljs.core.truth_((function (){var or__7446__auto__ = new cljs.core.Keyword(null,"macro","macro",-867863404).cljs$core$IFn$_invoke$arity$1(m);
if(cljs.core.truth_(or__7446__auto__)){
return or__7446__auto__;
} else {
return new cljs.core.Keyword(null,"repl-special-function","repl-special-function",1262603725).cljs$core$IFn$_invoke$arity$1(m);
}
})())){
cljs.core.prn.call(null,arglists_12509);
} else {
cljs.core.prn.call(null,((cljs.core._EQ_.call(null,new cljs.core.Symbol(null,"quote","quote",1377916282,null),cljs.core.first.call(null,arglists_12509)))?cljs.core.second.call(null,arglists_12509):arglists_12509));
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
var seq__12470_12510 = cljs.core.seq.call(null,new cljs.core.Keyword(null,"methods","methods",453930866).cljs$core$IFn$_invoke$arity$1(m));
var chunk__12471_12511 = null;
var count__12472_12512 = (0);
var i__12473_12513 = (0);
while(true){
if((i__12473_12513 < count__12472_12512)){
var vec__12474_12514 = cljs.core._nth.call(null,chunk__12471_12511,i__12473_12513);
var name_12515 = cljs.core.nth.call(null,vec__12474_12514,(0),null);
var map__12477_12516 = cljs.core.nth.call(null,vec__12474_12514,(1),null);
var map__12477_12517__$1 = ((((!((map__12477_12516 == null)))?((((map__12477_12516.cljs$lang$protocol_mask$partition0$ & (64))) || ((cljs.core.PROTOCOL_SENTINEL === map__12477_12516.cljs$core$ISeq$)))?true:false):false))?cljs.core.apply.call(null,cljs.core.hash_map,map__12477_12516):map__12477_12516);
var doc_12518 = cljs.core.get.call(null,map__12477_12517__$1,new cljs.core.Keyword(null,"doc","doc",1913296891));
var arglists_12519 = cljs.core.get.call(null,map__12477_12517__$1,new cljs.core.Keyword(null,"arglists","arglists",1661989754));
cljs.core.println.call(null);

cljs.core.println.call(null," ",name_12515);

cljs.core.println.call(null," ",arglists_12519);

if(cljs.core.truth_(doc_12518)){
cljs.core.println.call(null," ",doc_12518);
} else {
}

var G__12520 = seq__12470_12510;
var G__12521 = chunk__12471_12511;
var G__12522 = count__12472_12512;
var G__12523 = (i__12473_12513 + (1));
seq__12470_12510 = G__12520;
chunk__12471_12511 = G__12521;
count__12472_12512 = G__12522;
i__12473_12513 = G__12523;
continue;
} else {
var temp__4657__auto___12524 = cljs.core.seq.call(null,seq__12470_12510);
if(temp__4657__auto___12524){
var seq__12470_12525__$1 = temp__4657__auto___12524;
if(cljs.core.chunked_seq_QMARK_.call(null,seq__12470_12525__$1)){
var c__8265__auto___12526 = cljs.core.chunk_first.call(null,seq__12470_12525__$1);
var G__12527 = cljs.core.chunk_rest.call(null,seq__12470_12525__$1);
var G__12528 = c__8265__auto___12526;
var G__12529 = cljs.core.count.call(null,c__8265__auto___12526);
var G__12530 = (0);
seq__12470_12510 = G__12527;
chunk__12471_12511 = G__12528;
count__12472_12512 = G__12529;
i__12473_12513 = G__12530;
continue;
} else {
var vec__12479_12531 = cljs.core.first.call(null,seq__12470_12525__$1);
var name_12532 = cljs.core.nth.call(null,vec__12479_12531,(0),null);
var map__12482_12533 = cljs.core.nth.call(null,vec__12479_12531,(1),null);
var map__12482_12534__$1 = ((((!((map__12482_12533 == null)))?((((map__12482_12533.cljs$lang$protocol_mask$partition0$ & (64))) || ((cljs.core.PROTOCOL_SENTINEL === map__12482_12533.cljs$core$ISeq$)))?true:false):false))?cljs.core.apply.call(null,cljs.core.hash_map,map__12482_12533):map__12482_12533);
var doc_12535 = cljs.core.get.call(null,map__12482_12534__$1,new cljs.core.Keyword(null,"doc","doc",1913296891));
var arglists_12536 = cljs.core.get.call(null,map__12482_12534__$1,new cljs.core.Keyword(null,"arglists","arglists",1661989754));
cljs.core.println.call(null);

cljs.core.println.call(null," ",name_12532);

cljs.core.println.call(null," ",arglists_12536);

if(cljs.core.truth_(doc_12535)){
cljs.core.println.call(null," ",doc_12535);
} else {
}

var G__12537 = cljs.core.next.call(null,seq__12470_12525__$1);
var G__12538 = null;
var G__12539 = (0);
var G__12540 = (0);
seq__12470_12510 = G__12537;
chunk__12471_12511 = G__12538;
count__12472_12512 = G__12539;
i__12473_12513 = G__12540;
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

var seq__12484 = cljs.core.seq.call(null,new cljs.core.PersistentVector(null, 3, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"args","args",1315556576),new cljs.core.Keyword(null,"ret","ret",-468222814),new cljs.core.Keyword(null,"fn","fn",-1175266204)], null));
var chunk__12485 = null;
var count__12486 = (0);
var i__12487 = (0);
while(true){
if((i__12487 < count__12486)){
var role = cljs.core._nth.call(null,chunk__12485,i__12487);
var temp__4657__auto___12541__$1 = cljs.core.get.call(null,fnspec,role);
if(cljs.core.truth_(temp__4657__auto___12541__$1)){
var spec_12542 = temp__4657__auto___12541__$1;
cljs.core.print.call(null,[cljs.core.str.cljs$core$IFn$_invoke$arity$1("\n "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.name.call(null,role)),cljs.core.str.cljs$core$IFn$_invoke$arity$1(":")].join(''),cljs.spec.alpha.describe.call(null,spec_12542));
} else {
}

var G__12543 = seq__12484;
var G__12544 = chunk__12485;
var G__12545 = count__12486;
var G__12546 = (i__12487 + (1));
seq__12484 = G__12543;
chunk__12485 = G__12544;
count__12486 = G__12545;
i__12487 = G__12546;
continue;
} else {
var temp__4657__auto____$1 = cljs.core.seq.call(null,seq__12484);
if(temp__4657__auto____$1){
var seq__12484__$1 = temp__4657__auto____$1;
if(cljs.core.chunked_seq_QMARK_.call(null,seq__12484__$1)){
var c__8265__auto__ = cljs.core.chunk_first.call(null,seq__12484__$1);
var G__12547 = cljs.core.chunk_rest.call(null,seq__12484__$1);
var G__12548 = c__8265__auto__;
var G__12549 = cljs.core.count.call(null,c__8265__auto__);
var G__12550 = (0);
seq__12484 = G__12547;
chunk__12485 = G__12548;
count__12486 = G__12549;
i__12487 = G__12550;
continue;
} else {
var role = cljs.core.first.call(null,seq__12484__$1);
var temp__4657__auto___12551__$2 = cljs.core.get.call(null,fnspec,role);
if(cljs.core.truth_(temp__4657__auto___12551__$2)){
var spec_12552 = temp__4657__auto___12551__$2;
cljs.core.print.call(null,[cljs.core.str.cljs$core$IFn$_invoke$arity$1("\n "),cljs.core.str.cljs$core$IFn$_invoke$arity$1(cljs.core.name.call(null,role)),cljs.core.str.cljs$core$IFn$_invoke$arity$1(":")].join(''),cljs.spec.alpha.describe.call(null,spec_12552));
} else {
}

var G__12553 = cljs.core.next.call(null,seq__12484__$1);
var G__12554 = null;
var G__12555 = (0);
var G__12556 = (0);
seq__12484 = G__12553;
chunk__12485 = G__12554;
count__12486 = G__12555;
i__12487 = G__12556;
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