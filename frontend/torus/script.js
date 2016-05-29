
	function getAngle(xi,yi,x,y) {
		var alpha = Math.acos( ((xi-x)*(xi-x) + (y-y)*(yi-y))    / 
			( Math.sqrt((xi-x)*(xi-x) + (y-y)*(y-y)) * Math.sqrt((xi-x)*(xi-x) + (yi-y)*(yi-y)) ) )*180/3.14;

//		var alpha = Math.acos( ((xi-x)*(xi-x) + (0-y)*(yi-y))    / 
//			( Math.sqrt((xi-x)*(xi-x) + (0-y)*(0-y)) * Math.sqrt((xi-x)*(xi-x) + (yi-y)*(yi-y)) ) )*180/3.14;

		if (x > xi) return alpha
		else if ((x <= xi) || (n <4)) return 180 - alpha 
			else return 180 + alpha;
	}

	var example = document.getElementById("example"),
	ctx     = example.getContext('2d');

	var x_center = 300, y_center = 300;
	var R = 200, n = 8, f = 30;
	var id_side;
	var side;
	var angle = 0;
	var x = x_center + R * Math.cos(f + (2 * Math.PI * 7/n));
	var y = y_center + R * Math.sin(f + (2 * Math.PI * 7/n));
	
	//f = Math.acos((x - x_center)/(y - y_center))*180;
	angle =	getAngle(x_center + R * Math.cos(f + (2 * Math.PI * 0/n)), y_center + R * Math.sin(f + (2 * Math.PI * 0/n)),
		x_center + R * Math.cos(f + (2 * Math.PI * 1/n)), y_center + R * Math.sin(f + (2 * Math.PI * 1/n)));
	var a;
	ctx.beginPath();
	ctx.moveTo(x, y);
	for(var i = 0; i < n; i++) {
		
		xi = x_center + R * Math.cos(f + (2 * Math.PI * i/n));
		yi = y_center + R * Math.sin(f + (2 * Math.PI * i/n));

		id_side = 'side' + i;
		side = document.getElementById(id_side);
		side.style.transformOrigin = '0 0';
		side.style.top = yi+'px';
		side.style.left = xi+'px';

		
		var ang = angle + (45 * i);

//		side.style.transform = 'rotateY('+90+'deg)';	
//		side.style.transform = 'rotateZ('+ang+'deg)';	
		side.style.transform =	'rotateX(-90deg) rotateY('+(-1)*ang+'deg)';



		a = Math.sqrt((xi-x)*(xi-x)+(yi-y)*(yi-y));

		ctx.lineTo(xi, yi);
		console.log(xi + '; ' + yi);
		console.log(ang);
//		side.style.transform = 'translate3d('+ xi +'px, '+ yi +'px, 0)';
		x = xi;
		y = yi;
	}
	ctx.stroke();
	for(var i = 0; i < n; i++) {
		id_side = 'side' + i;
		side = document.getElementById(id_side);
		side.style.width = a+'px';
	}
	

