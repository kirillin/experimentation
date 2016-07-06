

var N = 2; // rows
var M = 2;
var matrix;

function getMatrixInitBy(value, N, M) {
	var matrix = new Array();
	for (var i = 0; i < N; i++) {
		matrix[i] = new Array();
		for (var j = 0; j < M; j++) {
			matrix[i][j] = value;
		}
	}
	return matrix;
}

function
