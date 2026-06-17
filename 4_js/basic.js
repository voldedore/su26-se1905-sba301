/* BASIC */
console.log("Testing");
//alert("This won't harm you very much");

/* Fn DECLARATION */
// You can declare the function b4 or after, as long as it is in the scope
printMsg(789);
function printMsg(msg) {
    console.log(msg + "!!!");
}
printMsg("Hi everybody");

function addNum(x, y) {
    return x + y;
}

// Since this is declared after, t
// his must be called after the declaration
// var nine = squareNum(3);
// printMsg(nine);

// Another way to declare a function
var squareNum = function (x) {
    return x * x;
}

var sixteen = squareNum(4);
printMsg(sixteen);

/* var, let, const */
sixteen = 60;
printMsg(sixteen);
// you can changed the value of a variable declared 
// with var or let, but not const
sixteen = "This variable became 16 (a string)";
printMsg(sixteen);
sixteen = true;
// String interpolation
printMsg(`Its value is now ${sixteen}`);

let nine = squareNum(3);
printMsg(nine);
nine = 9 + " oh this finally turned into a string";
printMsg(nine);

const tf = squareNum(5);
printMsg(tf);
// tf = 100; // This will throw an error
// printMsg(tf);

/* HOISTING */
console.log(a);
var a = 10;
console.log(a);

// equivalent to
// var a; // declaration is hoisted (moved) to the top
// console.log(a); // undefined -> chưa được gán trị lúc khởi tạo.
// a = 10;
// console.log(a);

// Does hoisting apply to let?
// console.log(b);
// let b = 20;
// console.log(b);
// This will throw an error because let is not hoisted like var. 
// The variable b is in a "temporal dead zone" until it is declared and initialized.
// Khi dùng let, ta buộc phải nhớ gán giá trị trước khi sử dụng.
// Do cơ chế hoisting cua JS chỉ áp dụng cho var.
// **Như vậy, để chặt chẽ, thì nên dùng let thay vì var.**
// Vì nếu biến chưa có giá trị, sẽ thấy lỗi sớm.

// Let's try another way to declare a function
let shoutOutMsg = (flag) => {
    if (!!flag) {
        console.log("Yayyyyyyyyyy");
    } else {
        console.log("Ahhhhhhhhhhh");
    }
}
shoutOutMsg();
shoutOutMsg(true);
shoutOutMsg(1);
shoutOutMsg("true"); 

// Paraemeters with default values
let printHelloDays = (day = "Saturday") => {
    if (day === "Saturday") {
        console.log("Hoooooray.");
    } else {
        console.log("Oh no, it's " + day);
    }
}

printHelloDays();
printHelloDays("Monday");

function printHelloDaysLegacy(day = "Saturday") {
    if (day === "Saturday") {
        console.log("[L]: Hoooooray.");
    } else {
        console.log("[L]: Oh no, it's " + day);
    }
}
printHelloDaysLegacy();
printHelloDaysLegacy("Monday");

/* DATA TYPES */
let n = 5; // primitive
let m = new Number(79);
let name = "Jon";
let email = new String("jon@snow.got");
let isActive = true;
let isAdmin = new Boolean(false);
let abc; // Cái này là chưa gán giá trị 
let nullValue = null; // Cái này cũng là chưa có giá trị gì, nhưng ông dev cố ý để null,
// ổng nói là nó có giá trị (object), nhưng tạm thời null

// toan tu typeof va toan tu instanceof
console.log(`n: value=${n}, typeof=${typeof n}, instanceof Number=${n instanceof Number}`);
console.log(`m: value=${m}, typeof=${typeof m}, instanceof Number=${m instanceof Number}`);

console.log(`name: value=${name}, typeof=${typeof name},
     instanceof String=${name instanceof String}`);
console.log(`email: value=${email}, typeof=${typeof email},
     instanceof String=${email instanceof String}`);

console.log(`isActive: value=${isActive}, typeof=${typeof isActive}, instanceof Boolean=${isActive instanceof Boolean}`);
console.log(`isAdmin: value=${isAdmin}, typeof=${typeof isAdmin}, instanceof Boolean=${isAdmin instanceof Boolean}`);

console.log(`abc: value=${abc}, typeof=${typeof abc}, instanceof Object=${abc instanceof Object}`);
console.log(`nullValue: value=${nullValue}, typeof=${typeof nullValue}, instanceof Object=${nullValue instanceof Object}`);

// object & array
// Declare an empty object
let emptyEmployee = {};
console.log(emptyEmployee);

// Declare an object with some properties
let employee = {
    id: 1,
    name: "Alice",
    tel: null,
    address: []
};

// Access properties with . (dot) op
console.log("Employee details:");
console.log(employee.id);
console.log(employee.tel);
console.log(employee.address);
console.log(employee.email);

// Assign new prop to the obj
employee.email = "the_hound@got.com";
console.log(employee.email);
console.log(employee);



// Some useful array fn
// push() - add an element to the end of the array
employee.address.push(1);
employee.address.push("Le Loi street");
employee.address.push("MT city");
employee.address.push("TGG");

// pop() - remove an element from the end of the array
employee.address.pop();
console.log(employee.address);

// join().
console.log("Address of this employee:");
console.log(employee.address.join(" "));

// split
const splitStr = "This is a long string, it will soon be split".split(" ");
console.log(splitStr);

// indexOf
let anotherSpliStr = "This makes a good array from a string".split(" ");
console.log("The word 'makes' is at the index: " + anotherSpliStr.indexOf("makes"));
console.log("The word 'a' is at the index: " + anotherSpliStr.indexOf("a"));
console.log("The word 'jon' is at the index: " + anotherSpliStr.indexOf("jon"));

// reverse()
anotherSpliStr.reverse();
console.log("Reversed: " + anotherSpliStr.join("_"));

// ARRAY
const nums = [1,2,3,6,7,8];
console.log(nums);
console.log(nums[0]);
console.log(nums[6]);

// Fn browse 
// forEach()
nums.forEach((num) => {
    console.log(num);
});

// transform -> Từ mảng ban đầu, ta áp dụng 1 logic cụ thể 
// vào từng phần tử, tạo ra mảng mới
const doubleNums = nums.map((n) => n * 2);
console.log(doubleNums);

// filter -> Từ mảng ban đầu, ta áp dụng 1 logic cụ thể 
// vào từng phần tử, tạo ra mảng mới chỉ chứa các phần tử theo logic đó
const users = [
    {id: 1, name: "Alice", isActive: true},
    {id: 2, name: "Bob", isActive: false},
    {id: 3, name: "Charlie", isActive: true},
    {id: 4, name: "David", isActive: false},
    {id: 5, name: "Eve", isActive: true}
];

const disabledUsers = users.filter((u) => !u.isActive);
console.log(disabledUsers);