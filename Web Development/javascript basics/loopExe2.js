console.log("*****************\nLoop Exe2:");

const posts2 = [
  {
    id: 1,
    text: "Love this product",
    comments: []
  },
  {
    id: 2,
    text: "This is the worst. DON'T BUY!",
    comments: [
      { id: 1, text: "Idiot has no idea" },
      { id: 2, text: "Fool!" },
      { id: 3, text: "I agree!" }
    ]
  },
  {
    id: 3,
    text: "So glad I found this. Bought four already!",
    comments: []
  }
]


for (const post in posts2) {
  if (posts2[post].id == 2) {

    for (const c in posts2[post].comments) {
      if (posts2[post].comments[c].id == 3) {
        posts2[post].comments.splice(c, 1)
      }
    }

  }
}

console.log(posts2);