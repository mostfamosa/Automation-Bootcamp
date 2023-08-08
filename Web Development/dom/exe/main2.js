

//Moving the ball game
const moveRight = function () {
    const ball = document.getElementById("item")
    let left = parseInt(ball.style.marginLeft) || 0
    left += 15
    ball.style.marginLeft = left + "px"
}

const moveLeft = function () {
    const ball = document.getElementById("item")
    let right = parseInt(ball.style.marginRight) || 0
    right += 15
    ball.style.marginRight = right + "px"
}

const moveUp = function () {
    const ball = document.getElementById("item")
    let down = parseInt(ball.style.marginTop) || 0
    down -= 15
    ball.style.marginTop = down + "px"
}


const moveDown = function () {
    const ball = document.getElementById("item")
    let up = parseInt(ball.style.marginTop) || 0
    up += 15
    ball.style.marginTop = up + "px"
}



/***********************************************/


//Reservations
const checkReservation = function () {
    const reservations = {
        bob: { claimed: false },
        ted: { claimed: true }
    }


    const nameInput = document.getElementById("reserveName").value
    const nameText = nameInput.toLowerCase()
    console.log(reservations);
    if (reservations[nameText] == undefined) {
        const btnReservation = document.getElementById("btnReservation")
        btnReservation.style.backgroundColor = "red";
        alert("Hello '" + nameText + "' There Is Nothing Under You Name!")
    }
    else if (!reservations[nameText].claimed) {
        const btnReservation = document.getElementById("btnReservation")
        btnReservation.style.backgroundColor = "green";
        alert("Hello '" + nameText + "' Welcome To The Candy Shop!")
    }
    else if (reservations[nameText].claimed) {
        const btnReservation = document.getElementById("btnReservation")
        btnReservation.style.backgroundColor = "yellow";
        alert("Hello '" + nameText + "' You Already Claimed!")
    }
}


/***********************************************/


//Visual Overload
function changeColor() {
    const box = document.getElementById(this.id)
    let r = Math.random() * 250;
    let g = Math.random() * 250;
    let b = Math.random() * 250;
    box.style.backgroundColor = "rgb(" + r + "," + g + "," + b + ")"
}


const container = document.getElementById("container")
container.style.textAlign = "center"
for (let i = 0; i < 4; i++) {
    const box = document.createElement("div")
    box.style.width = "150px"
    box.style.height = "150px"
    box.style.margin = "10px"
    box.style.border = "9px solid black"
    box.style.display = "inline-block"
    box.id = "box" + i
    box.onmouseenter = changeColor
    container.appendChild(box)
}


/***********************************************/


//Form
function validate() {
    const form = document.getElementById("form")
    const fname = document.getElementById("fname").value
    const salary = document.getElementById("salary").value
    const birthday = document.getElementById("birthday").value
    const tel = document.getElementById("tel").value
    let err = document.getElementById("errorMsg")
    err.style.color = "red"
    if (fname.length <= 2) {
        err.innerHTML = "Name must be longer than 2 characters!"
    }
    else if (salary < 10000 || salary > 16000) {
        err.innerHTML = "Salary must be greater than 10,000 but less than 16,000!"
    }
    else if (birthday == "") {
        err.innerHTML = "Birthday must not be empty!"
    }
    else if (tel.length != 10) {
        err.innerHTML = "Phone must be 10 digits long!"
    }
    else {
        err.style.color = "green"
        err.innerHTML = "Connecting ..."
        // sleep(2000)
        form.style.display = "none"
        const formContainer = document.getElementById("formContainer")
        const h1 = document.createElement("h1")
        h1.style.color = "green"
        h1.style.textAlign = "center"
        h1.innerHTML = "Hi "+fname+", Welcome!"
        formContainer.appendChild(h1)
    }


}