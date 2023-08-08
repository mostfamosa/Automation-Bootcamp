console.log("*****************\nIf Exe:");

let boughtTesla = true
const yearOfTeslaPurchase = 2014
let isUSCitizen = true
let currentYear = 2018


let yearsDifference = currentYear - yearOfTeslaPurchase;

if (isUSCitizen) {
    if (boughtTesla) {
        console.log("Wow you have the Tesla for " + yearsDifference + " years.")
        if (yearsDifference >= 4)
            console.log("Would you like to upgrade?");

        else
            console.log("Are you satisfied with the car?");
    }
    else
        console.log("Are you interested in buying a Tesla.")
}
else {
    if (boughtTesla)
        console.log("Would you like to move to the US?")

    else
        console.log("Are you interested in buying a Tesla.")

}

