var demo = new Vue({
el:'#main',
data:{
services:[
{name: 'Test Suite 1', active:true},
{name: 'Test Suite 2', active:false}
]
}
methods:{
toggleActive:function(s){
s.active=!s.active;}}
});