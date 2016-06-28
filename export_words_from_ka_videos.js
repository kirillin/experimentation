var w = document.getElementsByClassName("link_11ewmfw");
var text = '';
for (var i = 0; i < w.length; i++) {
	text += w[i].getElementsByTagName("span")[2].innerHTML;
}

var re = / |\.|,|\?/;
var e = text.split(re);

e.sort();
for (var i = 0; i < e.length; i++) {
	for (var j = i+1; j < e.length; j++) {
		if (e[i] == e[j]) {
			e[j] = '';
		}
	}
}

var arr = new Array();
var i = 0
e.forEach(
	function(el,i,a) {
		if (el != "") {
			arr[i] = el; 
			i++;
		}
	}
);

arr.forEach(
	function(el,i,a) {
		console.log(el);
	}
);
