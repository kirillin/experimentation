'use strict';

function Challenge() {
	this.checkSetup();


  this.photosets = document.getElementById('purpose-id-1');
  this.photosets.cnt = this.photosets.getElementsByClassName('cnt')[0];
  this.photosets.title = this.photosets.getElementsByClassName('title')[0];
  this.photosets.buttor = this.photosets.getElementsByClassName('submit')[0];
  

  this.books = document.getElementById('purpose-id-2');
  this.books.cnt = this.books.getElementsByClassName('cnt')[0];
  this.books.title = this.books.getElementsByClassName('title')[0];
  this.books.buttor = this.books.getElementsByClassName('submit')[0];

	this.userPic = document.getElementById('user-pic');
	this.userName = document.getElementById('user-name');
	this.signInButton = document.getElementById('sign-in');
	this.signOutButton = document.getElementById('sign-out');

	this.signOutButton.addEventListener('click', this.signOut.bind(this));
	this.signInButton.addEventListener('click', this.signIn.bind(this));

	this.initFirebase();
};


Challenge.prototype.initFirebase = function() {
	this.auth = firebase.auth();
	this.database = firebase.database();
	this.storage = firebase.storage();
  this.loadData();
  this.auth.onAuthStateChanged(this.onAuthStateChanged.bind(this));
};

Challenge.prototype.signIn = function() {
  var provider = new firebase.auth.GoogleAuthProvider();
  this.auth.signInWithPopup(provider);
  console.log("singin");
};

Challenge.prototype.signOut = function() {
  this.auth.signOut();
  console.log("singout");
};

Challenge.prototype.loadData = function() {
  this.messagesRef = this.database.ref('purposes');
  this.messagesRef.off();

  var setData = function(data) {
    var val = data.val();
    this.displayData(data.key, val.cnt, val.title, val.items);
  }.bind(this);
  this.messagesRef.limitToLast(10).on('child_added', setData);
  //this.messagesRef.limitToLast(10).on('child_changed', setData);
};

Challenge.prototype.onAuthStateChanged = function(user) {
   if (user) {
    var profilePicUrl = user.photoURL;
    var userName = user.displayName;

    this.userPic.style.backgroundImage = 'url(' + profilePicUrl + ')';
    this.userName.textContent = userName;

    this.userName.removeAttribute('hidden');
    this.userPic.removeAttribute('hidden');
    this.signOutButton.removeAttribute('hidden');

    this.signInButton.setAttribute('hidden', 'true');

   } else {
    this.userName.setAttribute('hidden', 'true');
    this.userPic.setAttribute('hidden', 'true');
    this.signOutButton.setAttribute('hidden', 'true');

    this.signInButton.removeAttribute('hidden');
  }
};

Challenge.prototype.checkSignedInWithMessage = function() {
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


Challenge.prototype.displayData = function(key, cnt, title, items) {
  var div = document.getElementById(key);
  var count;
  var tit;
  var count = div.getElementsByClassName('cnt')[0];
  var tit = div.getElementsByClassName('title')[0];
  var page_items = div.getElementsByClassName('items')[0];
  if (cnt && title) {
    count.textContent = cnt;
    tit.textContent = title;
    if (items) {
      for (key in items) {
        var item = document.createElement('div');
        item.innerHTML = Challenge.ITEM_TEMPLATE;
        var n = item.getElementsByClassName('name')[0];
        var d = item.getElementsByClassName('date')[0];
        n.textContent = items[key].name;
        d.textContent = items[key].date;
        page_items.appendChild(item);        
      }      
    }
  } else {
    console.log('la problema data display function');
  }
  console.log(items);
};

Challenge.prototype.checkSetup = function() {
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

window.onload = function() {
  window.challenge = new Challenge();
};
