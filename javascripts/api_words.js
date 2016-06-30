
function clear(R, delSym) {
	for (var i = 0; i < R.length; i++) {
		if (R[i] === delSym){
			R.splice(i,1);
			i--;
		}
	}
	return R;
}

function getQtyRows(R) {
	return R.length;
}

function getAarray() {
	var a = document.getElementById('A');
	var tA = a.value;
	var aA = tA.split('\n');

	return clear(aA, "");
}

function getBarray() {
	var b = document.getElementById('B');
	var tB = b.value;	
	var aB = tB.split('\n');
	return clear(aB, "");
}

function getCarray() {
	var c = document.getElementById('C');
	var tC = c.value;	
	var aC = tC.split('\n');
	return clear(aC, "");
}

function getDarray() {
	var d = document.getElementById('D');
	var tD = d.value;	
	var aD = tD.split('\n');
	return clear(aD, "");
}

function getEarray() {
	var e = document.getElementById('E');
	var tE = e.value;	
	var aE = tE.split('\n');
	return clear(aE, "");
}

function setAToPage(A) {
	var in0 = document.getElementById('A');
	in0.value = '';
	for (var i = 0; i < A.length; i++) {
		in0.value = in0.value + A[i] + '\n';
	}
}

function setBToPage(B) {
	var in1 = document.getElementById('B');
	in1.value = '';
	for (var i = 0; i < B.length; i++) {
		in1.value = in1.value + B[i] + '\n';
	}
}

function setCToPage(C) {
	var out = document.getElementById('C');
	out.value = '';
	for (var i = 0; i < C.length; i++) {
		out.value = out.value + C[i] + '\n';
	}
}

function setEToPage(E) {
	var out2 = document.getElementById('E');
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

function doSortA() {
	var A = getAarray();
	A.sort();
	setAToPage(A);
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

function rmDoublesA() {
	var A = getAarray();
	A = rmDoubles(A);
	setAToPage(A);
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
	var in0 = document.getElementById('A');
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
	var out = document.getElementById('C');
	var out1 = document.getElementById('D');
	out1.scrollTop = out.scrollTop;
}
function scrolingDC() {
	var out = document.getElementById('C');
	var out1 = document.getElementById('D');
	out.scrollTop = out1.scrollTop;
}

function getQtyRowsA() {
	var A = getAarray();
	var sp = document.getElementsByTagName('span')[0];
	sp.innerText = A.length;
}
function getQtyRowsB() {
	var B = getBarray();
	var sp = document.getElementsByTagName('span')[1];
	sp.innerText = B.length;
}
function getQtyRowsC() {
	var C = getCarray();
	var sp = document.getElementsByTagName('span')[2];
	sp.innerText = C.length;
}
function getQtyRowsD() {
	var D = getDarray();
	var sp = document.getElementsByTagName('span')[3];
	sp.innerText = D.length;
}
function getQtyRowsE() {
	var E = getEarray();
	var sp = document.getElementsByTagName('span')[4];
	sp.innerText = E.length;
}

function updateFields() {
	getQtyRowsA();
	getQtyRowsB();
	getQtyRowsC();
	getQtyRowsD();
	getQtyRowsE();
}

window.addEventListener("click", updateFields);
window.onload = function() {
	var butSub = document.getElementById('substract');
	var butMerge = document.getElementById('merge');
	var butSortA = document.getElementById('sortA');
	var butSortB = document.getElementById('sortB');
	var butSortC = document.getElementById('sortC');
	var butRmDoublesA = document.getElementById('rmDoublesA');	
	var butRmDoublesB = document.getElementById('rmDoublesB');
	var butRmDoublesC = document.getElementById('rmDoublesC');
	var butParseSubs = document.getElementById('parseSubs');
	var butMakeDeck = document.getElementById('makeDeck');
	var A = document.getElementById('A');
	var B = document.getElementById('B');
	var C = document.getElementById('C');
	var D = document.getElementById('D');
	var E = document.getElementById('E');
	A.addEventListener('keypress', getQtyRowsA);
	B.addEventListener('keypress', getQtyRowsB);
	C.addEventListener('keypress', getQtyRowsC);
	D.addEventListener('keypress', getQtyRowsD);
	E.addEventListener('keypress', getQtyRowsE);




	var out = document.getElementById('C');
	out.addEventListener('scroll', scrolingCD);
	var out1 = document.getElementById('D');
	out1.addEventListener('scroll', scrolingDC);

	butSub.addEventListener('click', doSubstract);
	butMerge.addEventListener('click', doMerge);
	butSortA.addEventListener('click', doSortA);
	butSortB.addEventListener('click', doSortB);
	butSortC.addEventListener('click', doSortC);
	butRmDoublesA.addEventListener('click', rmDoublesA);	
	butRmDoublesB.addEventListener('click', rmDoublesB);
	butRmDoublesC.addEventListener('click', rmDoublesC);
	butParseSubs.addEventListener('click', parseSubs);
	butMakeDeck.addEventListener('click', makeDeck);
}
