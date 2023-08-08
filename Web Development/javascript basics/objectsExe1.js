console.log("*****************\nObject Exe1:");

let p1 = {
    name: "Jill",
    age: 26,
    city: "Tel Aviv"
}

let p2 = {
    name: "Robert",
    age: 26,
    city: "Haifa"
}

if(p1.age == p2.age){
    if(p1.city==p2.city){
        console.log(p1.name+" wanted to date "+p2.name)
    }
    else{
        console.log(p1.name+" wanted to date "+p2.name+", but couldn't")
    }
}