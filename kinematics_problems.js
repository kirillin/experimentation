

window.onload = function() {


    var N = 7;

    var canvas;
    var context;
    var canvasYZ;
    var contextYZ;
    var canvasXZ;
    var contextXZ;
    var x;
    var y;
    var zoom = 1;
    canvas = document.getElementById('myCanvasXY');
    context = canvas.getContext('2d');
    canvasYZ = document.getElementById('myCanvasYZ');
    contextYZ = canvasYZ.getContext('2d');
    canvasXZ = document.getElementById('myCanvasXZ');
    contextXZ = canvasXZ.getContext('2d');
    x = canvas.width;
    y = canvas.height;
    color = ['red', 'blue', 'green', 'pink', 'yellow', 'orange', 'black'];
    context.strokeStyle = color[2];



    // just need, lol
    var aa = new Array();
    var ni = 4;
    var nj = 4;
    res = new Array();
    for (var i = 0; i < ni; i++) {
        res[i] = new Array();
        for (var j = 0; j < nj; j++) {
            res[i][j] = 0;
        }
    }
    for (var i = 0; i < 7; i++) {
        aa[i] = res;
    }
    // end of just need, lol

    var d1 = 50;
    var d4 = 50;
    var d6 = 50;

    var a =     [0,50,0,0,0,0,50];
    var alpha = [90,0,-90,90,-90,0,90];
    var d =     [d1,0,0,d4,0,d6,0];
    for (var i = 0; i < N; i++) {
        alpha[i] *= Math.PI / 180;
    }


    centerX = x / 2;
    centerY = y / 2;
    prevX = centerX;
    prevY = centerY;



    function draw(c, res, ax1, ax2, dek) {
        c.lineWidth = 1;
        c.clearRect(0, 0, x, y);
        c.strokeStyle = 'green';
        c.lineWidth = 1;
        c.beginPath();
        c.moveTo(10,10);
        c.lineTo(30,10);
        c.moveTo(10,10);
        c.lineTo(10,30);
        cx = '';
        switch(ax1) {
            case 0: cx = 'X'; break;
            case 1: cx = 'Y'; break;
            case 2: cx = 'Z'; break;
            default: cx = 'I HAVE SOME PROBLEMS!'
        }
        c.fillText(cx,30,13);
        cy = '';
        switch(ax2) {
            case 0: cy = 'X'; break;
            case 1: cy = 'Y'; break;
            case 2: cy = 'Z'; break;
            default: cy = 'I HAVE SOME PROBLEMS!'
        }
        c.fillText(cy,7,40);
        c.stroke();

        // legenda
        c.lineWidth = 1;
        
        for (var i = 1; i < N+1; i++) {
            c.lineWidth += 1;
            c.beginPath();
            c.strokeStyle = color[i-1];
            c.moveTo(50+40*(i-1),10);
            var str = 'q' + i;
            c.fillText(str,50+40*(i-1),20+i);
            c.lineTo(50+40*i,10);
            c.stroke();
        }

        //center point                
        c.lineWidth = 1;
        c.beginPath();
        c.strokeStyle = 'black';
        c.ellipse(centerX, centerY, 5,5,0,0,7);
        c.stroke();

        
        c.lineWidth = 2;
        prevX = centerX;
        prevY = centerY;
        for (var i = 0; i < dek[0].length; i++) {
            c.strokeStyle = color[i];
            c.beginPath();
            c.moveTo(prevX, prevY);
            var ix = centerX + dek[ax1][i]*zoom;
            var iy = centerY + dek[ax2][i]*zoom;
            if (i == dek[0].length-1) {
                c.lineWidth = 1;
            }
            c.lineTo(ix, iy);
            c.stroke();
            prevX = ix;
            prevY = iy;
            c.lineWidth += 1;
        }
        c.lineWidth = 1;
        c.strokeStyle = color[7];
        c.beginPath();
        //c.rect(0,0,200,200);
        c.stroke();
        // R vector from 0,0,0 to point M
        c.strokeStyle = 'red';
        c.lineWidth = 1;
        c.beginPath();
        c.moveTo(centerX, centerY);
        c.lineTo(centerX + res[ax1][3], centerY + res[ax2][3]);
        c.stroke();
    }

    function mxm(a, b) {
        res = new Array();
        for (var i = 0; i < a.length; i++) {
            res[i] = new Array();
            for (var j = 0; j < b[0].length; j++) {
                res[i][j] = 0;
            }
        }
        for (var i = 0; i < b[0].length; i++) {
            for (var j = 0; j < a.length; j++) {
                for (var k = 0; k < b.length; k++) {
                    res[j][i] += a[j][k] * b[k][i]; 
                }
            }
        }
        return res;
    }

    function mxm1(a, b, m) {
        res = [0, 0, 0];
        for (var i = 0; i < m; i++) {
            for (var k = 0; k < m; k++) {
                res[i] += a[k] * b[k][i]; 
            }
        }
        return res;
    }


    function doMagic() {
        for (var ii = 0; ii < N; ii++) {
            o1 = document.getElementById('o1').value;
            o2 = document.getElementById('o2').value;
            o3 = document.getElementById('o3').value;   
            o4 = document.getElementById('o4').value;
            o5 = document.getElementById('o5').value;
            o6 = document.getElementById('o6').value;
            o7 = 45;
            otta =  [o1,o2,o3,o4,o5,o6,o7];
            for (var i = 0; i < otta.length; i++) {
                otta[i] *= Math.PI / 180;
            }

            Rz = [  [Math.cos(otta[ii]), (-1) * Math.sin(otta[ii]), 0, 0],
                    [Math.sin(otta[ii]), Math.cos(otta[ii]), 0, 0],
                    [0, 0, 1, 0],
                    [0, 0, 0, 1]];

            Tzd = [ [1, 0, 0, 0],
                    [0, 1, 0, 0],
                    [0, 0, 1, d[ii]],
                    [0, 0, 0, 1]];

            Txa = [ [1, 0, 0, a[ii]],
                    [0, 1, 0, 0],
                    [0, 0, 1, 0],
                    [0, 0, 0, 1]];

            Rx = [  [1, 0, 0, 0],
                    [0, Math.cos(alpha[ii]), (-1) * Math.sin(alpha[ii]), 0],
                    [0, Math.sin(alpha[ii]), Math.cos(alpha[ii]), 0],
                    [0, 0, 0, 1]];

            res = mxm(mxm(mxm(Rz, Tzd), Txa), Rx);
            aa[ii] = res;
            //console.log(ii + ' done!');
        }
        var xi = new Array();
        var yi = new Array();
        var zi = new Array();

        var res = aa[0];
        xi.push(res[0][3]);
        yi.push(res[1][3]);
        zi.push(res[2][3]);
        for (var i = 1; i < N; i++) {
            res = mxm(res, aa[i]);
            xi.push(res[0][3]);
            yi.push(res[1][3]);
            zi.push(res[2][3]);
        }

        res = mxm(mxm(mxm(mxm(mxm(aa[0], aa[1]), aa[2]), aa[3]), aa[4]),aa[5]);
        xi.push(res[0][3]);
        yi.push(res[1][3]);
        zi.push(res[2][3]);
        // console.log(xi);
        // console.log(yi);
        // console.log(zi);
        var dek = new Array();
        dek.push(xi);
        dek.push(yi);
        dek.push(zi);

        draw(context, res, 0, 1, dek);
        draw(contextYZ, res, 2, 1, dek);
        draw(contextXZ, res, 0, 2, dek);  


        console.log(res[0]);
        console.log(res[1]);
        console.log(res[2]);
        console.log(res[3]);
        context.strokeStyle = 'red';
        context.lineWidth = 1;
        context.beginPath();
        context.moveTo(centerX, centerY);
       // context.lineTo(centerX + res[0][3], centerY + res[1][3]);             var fpeef = mxm(end_eff[1], Reef);
        context.stroke();
    }

    function update_o1() {
        o1 = document.getElementById('o1').value;
        console.log(o1);
    }

    function update_o2() {
        o2 = document.getElementById('o2').value;
    }

    function update_o3() {
        o3 = document.getElementById('o3').value;
    }

    function update_o4() {
        o4 = document.getElementById('o4').value;
    }

    function update_o5() {
        o5 = document.getElementById('o5').value;
    }

    function update_o6() {
        o6 = document.getElementById('o6').value;
    }

    function updateO1(val) {
       // document.getElementById('o1value').value = val;
       document.querySelector('#o1value').value = val;
    }
    function updateO2(val) {
        document.getElementById('o2value').value = val;
    }
    function updateO3(val) {
        document.getElementById('o3value').value = val;
    }
    function updateO4(val) {
        document.getElementById('o4value').value = val;
    }
    function updateO5(val) {
        document.getElementById('o5value').value = val;
    }
    function updateO6(val) {
        document.getElementById('o6value').value = val;
    }

    
    document.body.addEventListener('mousemove', doMagic);
    
    document.getElementById('o1').addEventListener('mousemove', update_o1); 
    document.getElementById('o2').addEventListener('change', update_o2);
    document.getElementById('o3').addEventListener('change', update_o3);
    document.getElementById('o4').addEventListener('change', update_o4);
    document.getElementById('o5').addEventListener('change', update_o5);
    document.getElementById('o6').addEventListener('change', update_o6);
}