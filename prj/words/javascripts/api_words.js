
function clear(R, delSym) {
	for (var i = 0; i < R.length; i++) {
		if (R[i] === delSym){
			R.splice(i,1);
			i--;
		}
	}
	return R;
}

function getAarray() {
	var in0 = document.getElementById('input0');
	var tA = in0.value;
	var aA = tA.split('\n');

	return clear(aA, "");
}

function getBarray() {
	var in1 = document.getElementById('input1');
	var tB = in1.value;	
	var aB = tB.split('\n');
	return clear(aB, "");
}

function getCarray() {
	var out = document.getElementById('output');
	var tC = out.value;	
	var aC = tC.split('\n');
	return clear(aC, "");
}

function getDarray() {
	var out1 = document.getElementById('output1');
	var tD = out1.value;	
	var aD = tD.split('\n');
	return clear(aD, "");
}


function setCToPage(C) {
	var out = document.getElementById('output');
	out.value = '';
	for (var i = 0; i < C.length; i++) {
		out.value = out.value + C[i] + '\n';
	}
}

function setBToPage(B) {
	var in1 = document.getElementById('input1');
	in1.value = '';
	for (var i = 0; i < B.length; i++) {
		in1.value = in1.value + B[i] + '\n';
	}
}

function setEToPage(E) {
	var out2 = document.getElementById('output2');
	out2.value = '';
	for (var i = 0; i < E.length; i++) {
		out2.value = out2.value + E[i] + '\n';
	}
}


/*
 *	!!!whitout doubles
 */

function merge(A, B) {
	var C = new Array();
	var N = A.length;
	var M = B.length;
	C = A;
	var count = N;
	for (var i = 0; i < M; i++) {
		var flag = false;
		for (var j = 0; j < N; j++) {
			if (C[j] == B[i]) {
				flag = true;
				break;
			}
		}
		if (flag == false) {
			C[count] = B[i];
			count++;
		}
	}
	return C;
}

function substract(A, B) {
	var C = new Array();
	var N = A.length;
	var M = B.length;
	var count = 0;
	for (var i = 0; i < N; i++) {
		var flag = false;
		for (var j = 0; j < M; j++) {
			if (A[i] == B[j]) {
				flag = true;
				break;
			}
		}
		if (flag == false) {
			C[count] = A[i];
			count++;
		}
	}
	return C;
}

function rmDoubles(C) {
	for (var i = 0; i < C.length; i++) {
		for (var j = i + 1; j < C.length;) {
			if (C[i] == C[j]) {
				C.splice(j, 1);
			} else {
				j++;
			}
		}
	}
	return C;
}

/*
	Below functions implements listeners
*/

function doSubstract() {
	console.log("doSubstract - working...");
	var A = getAarray();
	var B = getBarray();
	// B substract A
	var C = substract(B, A);
	setCToPage(C);
	console.log("doSubstract - complete!");
}

function doMerge() {
	console.log("doMerge - working...");
	var A = getAarray();
	var B = getBarray();
	// B substract A
	var C = merge(A, B);
	setCToPage(C);
	console.log("doMerge - complete!");
}

function doSortB() {
	var B = getBarray();
	B.sort();
	setBToPage(B);
}

function doSortC() {
	var C = getCarray();
	C.sort();
	setCToPage(C);
}

function rmDoublesB() {
	var B = getBarray();
	B = rmDoubles(B);
	setBToPage(B);
}

function rmDoublesC() {
	var C = getCarray();
	C = rmDoubles(C);
	setCToPage(C);
}

function parseSubs() {
	var in0 = document.getElementById('input0');
	var tA = in0.value;
	//.test(tA)
	var re = new RegExp("[a-z']+","gim");
	var array;
	if (re.test(tA)) {
		array = tA.match(re);
	}
	for (var i = 0; i < array.length; i++) {
		array[i] = array[i].toLowerCase();
	}
	setBToPage(array);
}

function makeDeck() {
	var C = getCarray();
	var D = getDarray();
	var E = new Array();
	if (C.length == D.length) {
		for (var i = 0; i < C.length; i++) {
			E[i] = C[i].trim() + ";" +D[i].trim();
		}
	} else {
		alert("problem whith C and D. Dfferent length!");
	}
	setEToPage(E);
}


function scrolingCD() {
	var out = document.getElementById('output');
	var out1 = document.getElementById('output1');
	out1.scrollTop = out.scrollTop;
}
function scrolingDC() {
	var out = document.getElementById('output');
	var out1 = document.getElementById('output1');
	out.scrollTop = out1.scrollTop;
}

window.onload = function() {
	var butSub = document.getElementById('substract');
	var butMerge = document.getElementById('merge');
	var butSortB = document.getElementById('sortB');
	var butSortC = document.getElementById('sortC');
	var butRmDoublesB = document.getElementById('rmDoublesB');
	var butRmDoublesC = document.getElementById('rmDoublesC');
	var butParseSubs = document.getElementById('parseSubs');
	var butMakeDeck = document.getElementById('makeDeck');

	var out = document.getElementById('output');
	out.addEventListener('scroll', scrolingCD);
	var out1 = document.getElementById('output1');
	out1.addEventListener('scroll', scrolingDC);

	butSub.addEventListener('click', doSubstract);
	butMerge.addEventListener('click', doMerge);
	butSortB.addEventListener('click', doSortB);
	butSortC.addEventListener('click', doSortC);
	butRmDoublesB.addEventListener('click', rmDoublesB);
	butRmDoublesC.addEventListener('click', rmDoublesC);
	butParseSubs.addEventListener('click', parseSubs);
	butMakeDeck.addEventListener('click', makeDeck);
}
