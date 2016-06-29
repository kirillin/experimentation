var w = document.getElementsByClassName("link_11ewmfw");
var text = '';
for (var i = 0; i < w.length; i++) {
	text += w[i].getElementsByTagName("span")[2].innerHTML;
}

var re = / |\.|,|\?|\n/;
var e = text.split(re);

for (var i = 0; i < e.length; i++) {
	e[i] = e[i].toLowerCase();
}

for (var i = 0; i < e.length; i++) {
	for (var j = 0; j < e.length; j++) {
		if ((e[i] == e[j]) && (i != j)) {
			e[j] = '666';
		}
	}
}
e.sort();

var arr = new Array();
var i = e.length;
var j = 0;
var rr = /[0-9]*/
while(i >= 0) {
	if (rr in e[i] ) {
		arr[j] = e[i]; 
		j++;
	}
	i--;
}

arr.sort();
arr.forEach(
	function(el,i,a) {
		console.log(el);
	}
);
