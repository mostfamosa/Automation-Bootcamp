const horizontalRule = document.createElement("hr")

document.body.appendChild(horizontalRule)

//creating headers
const header = document.createElement("h1")
header.innerHTML = "This H1 created dynamically"
header.style.color = "#c0392b"
header.style.fontFamily = "Helvetica"
header.setAttribute("class", "my-header")
document.body.appendChild(header)

const subHeader = document.createElement("h2")
subHeader.innerHTML = "~ Mostafa Was Here :D ~"
subHeader.style.fontFamily = "Helvetica"
subHeader.setAttribute("class", "my-subHeader")
document.body.appendChild(subHeader)



//box onhover and onclick changing color
const box = document.getElementById("box")

const enterColor = function () {
    box.style.backgroundColor = "#c0392b"
    box.innerHTML = "AHH GO AWAY"
}

const leaveColor = function () {
    box.style.backgroundColor = "#1abc9c"
    box.innerHTML = "Hover over me!"
}

const clickedColor = function () {
    box.style.backgroundColor = "#8e44ad"
}

//Creating list and onclick method to add new item
const list = document.createElement("ul")
list.id = "myList"
list.onclick = function addItem() {
    const item = document.createElement("li")
    item.innerHTML = "A new item!"
    list.appendChild(item)
}

const item1 = document.createElement("li")
item1.innerHTML = "Starter Item"
const item2 = document.createElement("li")
item2.innerHTML = "Click us!"
list.appendChild(item1)
list.appendChild(item2)
document.body.appendChild(list)

document.body.appendChild(horizontalRule)
