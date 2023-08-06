laibrary = {
    books: [
        {
            title: "Harry Potter 1",
            author: "Jk"
        },
        {
            title: "Harry Potter 2",
            author: "Jk"
        },
        {
            title: "Harry Potter 3",
            author: "Jk"
        },
    ]
}

console.log(laibrary)


const reservations = {
    bob: { claimed: false },
    ted: { claimed: true }
}

const name = prompt('Please enter the name for your reservation');
const lowerCaseName = name.toLowerCase()
if (reservations[lowerCaseName] == undefined)
    alert("Hello '" + lowerCaseName + "' There Is Nothing Under You Name!");
else if (!reservations[lowerCaseName].claimed)
    alert("Hello '" + lowerCaseName + "' Welcome To The Candy Shop!");
else if (reservations[lowerCaseName].claimed)
    alert("Hello '" + lowerCaseName + "' You Already Claimed!");
