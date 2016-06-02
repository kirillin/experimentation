		// default parameters
		var ATTACK = 0;
		var DECAY = 0; 
		var DECAY_CONST = 0; // just need :)


		var context = new AudioContext();
		var can, can2, c, c2;		
		var formSignalSet = null;
		var sp = context.createScriptProcessor(1024, 1, 1);
		var an = context.createAnalyser();
		var ks; //for notes dispay
		
		var audioLybrary = new Object();
		audioLybrary.getFrequency = function(key, octave) {
			return 27.5 * Math.pow(2, (key + octave *12.0) / 12.0);
		}
		audioLybrary.FORM_SIGNAL = 'sine';
		audioLybrary.createChainOfNodes = function() {
		}
		sp.onaudioprocess = function() {
			var fFrequencyData = new Float32Array(an.frequencyBinCount);
			var bFrequencyData = new Uint8Array(an.frequencyBinCount); 
			var bTimeData = new Uint8Array(an.frequencyBinCount);
			an.getFloatFrequencyData(fFrequencyData); 
			an.getByteFrequencyData(bFrequencyData); 
			an.getByteTimeDomainData(bTimeData);
			c.clearRect(0, 0, width, height);				
			c.strokeStyle = 'white';
			c.fillStyle = 'black';
			c.fillRect(0, 0, width2, height2);
			c.beginPath();		
			c.moveTo(0,height/2);
			c.lineWidth = 1;
			for (var i = 0; i < bTimeData.length; i = i + 1) {
				var t = bTimeData[i];
				var a = bFrequencyData[i];
				var ba = bFrequencyData[i];
				var scaleX = width / bTimeData.length;
				var scaleY = (height/2 - t);				
				c.lineTo(scaleX * i, t + 25);
			}
			c.stroke();
			//*************
			c2.clearRect(0, 0, width2, height2);
			c2.strokeStyle = 'white';
			c2.fillStyle = 'black';
			c2.fillRect(0, 0, width2, height2);
			c2.fillStyle = 'white';
			c2.beginPath();		
			c2.moveTo(0,height2/2);
			c2.lineWidth = 1;
			for (var i = 0; i < bTimeData.length; i = i + 1) {
				var scaleX = width / bTimeData.length;
				var scaleY = height / height;
				var t = bTimeData[i];
				var a = bFrequencyData[i];
				var ba = bFrequencyData[i];
				c2.fillRect(i, height-a, i, height);
			}
			c2.stroke();
		};			
		
		audioLybrary.play = function(e) {
			var attack = ATTACK;
			var decay = DECAY;
			//audioLybrary.createChainOfNodes();
			var key = (e.target.id[2] == '#') ? piano.NOTES.indexOf(e.target.id[0] + e.target.id[2]) : piano.NOTES.indexOf(e.target.id[0]);
			var octave = e.target.id[1];		
			var note = document.getElementById("note");
			note.innerHTML = e.target.id;
			this.osc = context.createOscillator();		
			this.gain = context.createGain();
			sp.connect(context.destination);
			an.connect(sp);
			this.osc.connect(this.gain);
			this.gain.connect(an);
			this.gain.connect(context.destination);						
			an.fftSize = 1024;
			an.smoothingTimeConstant = 0.0;
			this.osc.frequency.value = audioLybrary.getFrequency(key, octave);
			this.osc.type = (formSignalSet != null) ? formSignalSet : audioLybrary.FORM_SIGNAL;			
			this.gain.gain.setValueAtTime(0, context.currentTime);
			this.gain.gain.linearRampToValueAtTime(1, context.currentTime + attack / 1000);
			this.gain.gain.linearRampToValueAtTime(0, context.currentTime + decay / 1000);
			this.osc.start(0);
			setTimeout(
				function(e) {
					this.osc.stop(0);
					this.osc.disconnect(this.gain);
					this.gain.disconnect(context.destination);
				}, decay
			);		
		}

		var intIdEx;
		var intInner;
		function startSequenceEx() {
			//var seq = document.getElementById('notes_sequence').value;
			var seq = "C3:1/1,C3:1/4,D3:1/4,E3:1/4,F3:1/4";
			DECAY = 4000;
			DECAY_CONST = 4000;
			var i = 0;				
			if (seq.length != 0) {
				var arrForPlay = seq.split(',');
				var sequanceEx = new Array();
				for (var j = 0; j < arrForPlay.length; j++) {
					var note_duration = arrForPlay[j].split(':');
					sequanceEx[j] = new Array();
					sequanceEx[j][0] = note_duration[0];
					sequanceEx[j][1] = new Array();
					sequanceEx[j][1] = note_duration[1].split('/');
				}
				/*
				TODO
				поделить весь массив по длительностям, 
				чтобы в интервале запускать по подпоследовательностям нот 
				сумма продолжительностей который равна целой (DECAY_CONST)

				т.е
				
				*** 1/1 - играет 
						целую ноту DECAY_CONST* 1/1 
				*** 1/4 - играет 
						играет четверть ноты DECAY_CONST * 1/4
						пауза DECAY_CONST * 3/4
				*** 3/4,1/4 - играет
						играет три четверные ноты DECAY_CONST * 3/4
						играет четверть ноты DECAY_CONST * 1/4

				*/
				intIdEx = setInterval(
					function() {
						var conditionOfExit = DECAY_CONST;
						intInner = setInterval(
							function() {
								DECAY = DECAY_CONST * sequanceEx[i][1][0] / sequanceEx[i][1][1];								
								console.log(DECAY);
								console.log(conditionOfExit);
								if (sequanceEx[i][0] != '-') {
									document.getElementById(sequanceEx[i][0]).click();
								}
								if (DECAY < DECAY_CONST) {
									conditionOfExit = conditionOfExit - DECAY;
									i++;
								}
								if (conditionOfExit >= DECAY_CONST) {
									clearInterval(intInner);
								}
							}, DECAY_CONST
						);
						i++;
						if (i >= sequanceEx.length) i = 0;
					}, DECAY_CONST
				);
			} else alert('сотворите мелодию или скопируйте ниже сотверенные');
		}

		function stopSequenceEx() {
			clearInterval(intInner);
			clearInterval(intIdEx);
		}

		var intId;
		function startSequence() {
			var seq = document.getElementById('notes_sequence').value;
			var i = 0;				
			if (seq.length != 0) {
				var arrForPlay = seq.split(',');
				intId = setInterval(
					function() {
						if (arrForPlay[i] != '-') document.getElementById(arrForPlay[i]).click();				
						i++;
						if (i >= arrForPlay.length) i = 0;
					}, 350
				);
			} else alert('сотворите мелодию или скопируйте ниже сотверенные');
		}
		
		function stopSequence() {
			clearInterval(intId);
		}
		
		function addCurrentNote() {
			var note = document.getElementById('note').textContent;
			var seq = document.getElementById('notes_sequence').value;
			if (note != "") {
				if (seq == "") {
					document.getElementById('notes_sequence').value =
					document.getElementById('notes_sequence').value + 
					note;
				} else {
					document.getElementById('notes_sequence').value =
					document.getElementById('notes_sequence').value + 
					"," + note;
				}
			}
		}

		function addPause() {
			var seq = document.getElementById('notes_sequence').value;
			if (seq == "") {
				document.getElementById('notes_sequence').value =
				document.getElementById('notes_sequence').value + 
				"-";
			} else {
				document.getElementById('notes_sequence').value =
				document.getElementById('notes_sequence').value + 
				",-" ;
			}
		}

		function clr() {
			document.getElementById('notes_sequence').value = "";
		}

		var piano = new Object();
		piano.NOTES = ['A', 'A#', 'B', 'C', 'C#', 'D', 'D#', 'E', 'F', 'F#', 'G', 'G#'];
		piano.KEYS_TYPE = ['white','black'];
		piano.createKeyboard = function() {
			/*
				'keysType' must contains only values of piano.KEYS_TYPE
			*/
			function makeKey(octaveCnt, noteCnt, keysType) {
				var key = document.createElement('div');
				key.setAttribute('class', keysType);
				key.id = piano.NOTES[noteCnt][0] + octaveCnt;
				if(keysType == piano.KEYS_TYPE[1]) key.id += '#';
				//key.innerHTML = key.id;
				return key;
			}
			function makeKeyboard() {
				var keyboard = document.createElement('div');
				var offset = 50;
				keyboard.setAttribute('id', 'keys');
				var keyNumber = 0;
				var key;
				var whiteKeyCnt = 0;
				for(var octaveCnt = 0; octaveCnt < 8; octaveCnt++) {
					for (var noteCnt = 0; noteCnt < 12; noteCnt++) {
						keyNumber = (octaveCnt * 12) + noteCnt;
						if (keyNumber > 87) break;
						switch (noteCnt % 12) {
							//  ['A', 'A#', 'B', 'C', 'C#', 'D', 'D#', 'E', 'F', 'F#', 'G', 'G#'];					
							case 1: case 4: case 6: case 9: case 11:
								key = makeKey(octaveCnt, noteCnt, piano.KEYS_TYPE[1]);
								key.style.left = whiteKeyCnt * 21 - 8 + offset; // width of white key minus half of black key 
							break;
							default:
								key = makeKey(octaveCnt, noteCnt, piano.KEYS_TYPE[0]);	
								key.style.left = whiteKeyCnt * 21 + offset;
								whiteKeyCnt++;
						}
						keyboard.appendChild(key);
					}
				}
				return keyboard;
			}
			return makeKeyboard();
		}	

		function setParameters() {
			var d = document.getElementById('decay').value;
			var a = document.getElementById('attack').value; 
			DECAY = parseInt(d);
			ATTACK = parseInt(a);
			DECAY_CONST = DECAY;
		}

		window.onload = function() {
			document.body.addEventListener('click', audioLybrary.play);
//			document.body.addEventListener('click', getNote);
			document.body.appendChild(piano.createKeyboard());			
			can = document.getElementById('area');
			width = can.width;
			height = can.height;
			c = can.getContext('2d');
			c.clearRect(0, 0, width, height);
			can2 = document.getElementById('area2');			
			width2 = can2.width;
			height2 = can2.height;
			c2 = can2.getContext('2d');
			c2.clearRect(0, 0, width2, height2);
			document.getElementById('setparameters').click();
		}
