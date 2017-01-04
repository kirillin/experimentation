'use strict';

function Challenge() {
    this.checkSetup();


    this.photosets = document.getElementById('purpose-id-1');
    this.photosets.cnt = this.photosets.getElementsByClassName('cnt')[0];
    this.photosets.title = this.photosets.getElementsByClassName('title')[0];
    this.photosets.button = this.photosets.getElementsByClassName('date')[0];
    this.photosets.input = this.photosets.getElementsByClassName('name')[0];

    this.books = document.getElementById('purpose-id-2');
    this.books.cnt = this.books.getElementsByClassName('cnt')[0];
    this.books.title = this.books.getElementsByClassName('title')[0];
    this.books.button = this.books.getElementsByClassName('date')[0];
    this.books.input = this.books.getElementsByClassName('name')[0];

    this.userPic = document.getElementById('user-pic');
    this.userName = document.getElementById('user-name');
    this.signInButton = document.getElementById('sign-in');
    this.signOutButton = document.getElementById('sign-out');


    this.photosets.cnt.addEventListener('click', this.visibleOn.bind(this));
    this.books.cnt.addEventListener('click', this.visibleOn.bind(this));

    this.inputfeild = document.getElementById('inputfeild');
    this.txtarea = document.getElementById('txtarea');
    this.txtbut = document.getElementById('txtbut');
    this.txtbutclose = document.getElementById('txtbut-close');

    this.txtbut.addEventListener('click', this.saveData.bind(this));
    this.txtarea.addEventListener('click', this.clear.bind(this));
    this.txtbutclose.addEventListener('click', this.close.bind(this));

    this.signOutButton.addEventListener('click', this.signOut.bind(this));
    this.signInButton.addEventListener('click', this.signIn.bind(this));

    this.initFirebase();
};

Challenge.prototype.clear = function (e) {
    e.toElement.value = "";
}

Challenge.prototype.close = function (e) {
    this.visibleOff();
}

Challenge.prototype.visibleOn = function (e) {
    var f = document.getElementById('inputfeild');
    f.hidden = false;
    var g = document.getElementById('fog');
    g.hidden = false;
    this.involv = e.srcElement.parentNode;
}

Challenge.prototype.visibleOff = function (e) {
    var f = document.getElementById('inputfeild');
    f.hidden = true;
    var g = document.getElementById('fog');
    g.hidden = true;
}

Challenge.prototype.initFirebase = function () {
    this.auth = firebase.auth();
    this.database = firebase.database();
    this.storage = firebase.storage();

    this.auth.onAuthStateChanged(this.onAuthStateChanged.bind(this));
};

Challenge.prototype.signIn = function () {
    var provider = new firebase.auth.GoogleAuthProvider();
    this.auth.signInWithPopup(provider);
    console.log("singin");
};

Challenge.prototype.signOut = function () {
    this.auth.signOut();
    console.log("singout");
};

Challenge.prototype.loadData = function () {
    this.dataRef = this.database.ref('purposes');
    this.dataRef.off();

    var setData = function (data) {
        var val = data.val();
        if (val.items) {
          this.displayData(data.key, val.cnt, val.title, val.items);
        } else {
          this.displayData(data.key, val.cnt, val.title, 0);
        }
    }.bind(this);
    this.dataRef.limitToLast(10).on('child_added', setData);
};

Challenge.prototype.saveCnt = function (id, cnt) {
    var c = cnt - 1;
    var postData = {
        cnt: c
    }
    var x = this.database.ref('purposes/' + id).push().key;
    console.log(id);
    //  var updates = {};
    // updates[id + '/' + postkey] = postData;
    // this.database.ref().update(updates);
}
Challenge.buf = '';

Challenge.prototype.saveData = function (e) {
    this.visibleOff(e);
    e.preventDefault();
    var d = new Date();
    var dat = d.toUTCString().substr(5, 20);
    var postData = {
        date: dat,
        name: this.txtarea.value
    };
    if (this.txtarea.value.trim() && this.checkSignedInWithMessage()) {
        var currentUser = this.auth.currentUser;
        var getCnt = function (snapshot) {
            //this.saveCnt(this.involv.id, snapshot);
            var v = snapshot.val();
            var c = v.cnt;
            c = c - 1;
            var t = v.title;
            var i = v.items;
            var updateData = {
                cnt: c,
                items: i,
                title: t
            }
            this.database.ref('purposes/' + this.involv.id).update(updateData);
        }.bind(this);
        this.database.ref('/purposes/' + this.involv.id + '/items').push(postData);
        this.database.ref('/purposes/' + this.involv.id).once('value').then(getCnt);
    } else {
      console.log('no data in text area!')
      var src = document.getElementById('msg');
      this.buf = src.textContent;
      src.textContent = 'ничего не сделал?? -_-';
      src.style.color = 'red';
      setTimeout(
        function(e) {
          var src = document.getElementById('msg');
          src.textContent = this.buf;  
          src.style.color = 'black';  
      }.bind(this), 2000);
    }
    this.loadData();
};


Challenge.resetMaterialTextfield = function (element) {
    element.value = '';
    element.parentNode.MaterialTextfield.boundUpdateClassesHandler();
};

Challenge.prototype.onAuthStateChanged = function (user) {

    if (user) {
        var profilePicUrl = user.photoURL;
        var userName = user.displayName;

        this.userPic.style.backgroundImage = 'url(' + profilePicUrl + ')';
        this.userName.textContent = userName;

        this.userName.removeAttribute('hidden');
        this.userPic.removeAttribute('hidden');
        this.signOutButton.removeAttribute('hidden');

        this.signInButton.setAttribute('hidden', 'true');
        this.loadData();
    } else {
        this.userName.setAttribute('hidden', 'true');
        this.userPic.setAttribute('hidden', 'true');
        this.signOutButton.setAttribute('hidden', 'true');

        this.signInButton.removeAttribute('hidden');
    }
};

Challenge.prototype.checkSignedInWithMessage = function () {
    if (this.auth.currentUser) {
        if (this.auth.currentUser.displayName != 'Rustam Mukin') {
            console.log('You are not Rustam, sorry you can only see on this!')
        }
        return true;
    }
    var data = {
        message: 'You must sign-in first',
        timeout: 2000
    };
    this.signInSnackbar.MaterialSnackbar.showSnackbar(data);
    return false;
};

Challenge.PURPOSE_TEMPLATE =
    '<div class="counter">' +
    '<div id="cnt"></div>' +
    '<div class="what">' +
    '<div id="title"></div>' +
    '</div>' +
    '<form >' +
    '<input class="iwhat" id="input" type="text" name="" value="<photosets title>"/>' +
    '<input class="iwhen" id="submit" type="button" name="" value="I did it" />' +
    '</form>' +
    '</div>';

Challenge.ITEM_TEMPLATE =
    '<div class="status"></div>' +
    '<div class="name"></div>' +
    '<div class="date"></div>';

Challenge.ROW_TEMPLATE =
    '<td class="dsc"></td>' +
    '<td class="num"></td>' +
    '<td class="dat"></td>';

Challenge.prototype.displayData = function (key, cnt, title, items) {
    var div = document.getElementById(key);
    var count;
    var tit;
    var count = div.getElementsByClassName('cnt')[0];
    var tit = div.getElementsByClassName('title')[0];
    var page_items = div.getElementsByClassName('items')[0];
    page_items.innerHTML = '';
    if (cnt && title) {
        count.textContent = cnt;
        tit.textContent = title;
        if (items) {
            for (key in items) {
                var item = document.createElement('tr');
                item.innerHTML = Challenge.ROW_TEMPLATE;
                var n = item.getElementsByClassName('dsc')[0];
                var d = item.getElementsByClassName('dat')[0];
                var num = item.getElementsByClassName('num')[0];
                n.textContent = items[key].name;
                d.textContent = items[key].date;
                num.innerHTML = '&#10004;';
                page_items.appendChild(item);
            }
        }
    } else {
        console.log('la problema data display function');
    }
};

Challenge.prototype.checkSetup = function () {
    if (!window.firebase || !(firebase.app instanceof Function) || !window.config) {
        window.alert('You have not configured and imported the Firebase SDK. ' +
            'Make sure you go through the codelab setup instructions.');
    } else if (config.storageBucket === '') {
        window.alert('Your Firebase Storage bucket has not been enabled. Sorry about that. This is ' +
            'actually a Firebase bug that occurs rarely. ' +
            'Please go and re-generate the Firebase initialisation snippet (step 4 of the codelab) ' +
            'and make sure the storageBucket attribute is not empty. ' +
            'You may also need to visit the Storage tab and paste the name of your bucket which is ' +
            'displayed there.');
    }
    console.log("SDK is OK!");
};

window.onload = function () {
    window.challenge = new Challenge();
};
