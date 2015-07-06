/**
 * Created by ugly-man on 2015/6/29.
 */

var name;
var password;
name=document.getElementById("name").value;
password=document.getElementById("psd").value;
function loginhandler() {
    name=document.getElementById("name").value;
    password=document.getElementById("psd").value;
    console.log(localStorage.length);

    for (var i = 0; i < localStorage.length; i++) {
        if (name == localStorage.key(i) && password == localStorage.getItem(localStorage.key(i)))
        {
       window.location="game.html";
        }
        else if(name == localStorage.key(i) && password != localStorage.getItem(localStorage.key(i))){
        	alert("用户名或者密码错误！");
        }
    }
}
function forgetHandler(){
    alert("那就算了吧！");

}



function remember() {
    name=document.getElementById("name").value;
    password=document.getElementById("psd").value;
    localStorage.setItem(name,password);
}
function check() {
    name=document.getElementById("name").value;
    password=document.getElementById("psd").value;
    for (var i = 0; i < localStorage.length; i++) {
        if(name==localStorage.key(i)) {
            document.getElementById("psd").value = localStorage.getItem(localStorage.key(i));
        }
    }
}