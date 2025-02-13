const express = require('express')
const cors = require('cors')
const books = require('./books.json')

const app = express()
app.use(express.json())
app.use(cors())

//ENDPOINT: RECUPERAR TODOS LOS LIBROS POR AUTOR
const getAllBooksByAutor = (req, res) => {
    const { autor } = req.query
    if (autor) {
        const filteredBooks = books.filter(
            book => book.autor.includes(autor)
        )
        res.json(filteredBooks)
    } else {
        res.json(books)
    }
}
app.get('/books/', getAllBooksByAutor)

// ENDPOINT: RECUPERAR LIBRO POR ISBN Y DEVOLVIENDO JSON
const getBookByISBNJSON = (req, res) => {
    const { isbn } = req.params
    const book = books.find(book => book.ISBN === isbn)
    if (book) res.json(book)
    else res.status(404).send("No se ha encontrado el libro")
}
app.get('/:isbn/json', getBookByISBNJSON)

// ENDPOINT: RECUPERAR LIBRO POR ISBN
const getBookByISBN = (req, res) => {
    const { isbn } = req.params
    const book = books.find(book => book.ISBN === isbn)
    if (book) res.send(`<h4>El libro es: <h2>Título: ${book.titulo}</h2> <h4>Autor: ${book.autor}</h4> <h4>Publicación: ${book.ano_publicacion}</h4> <h4>Géneros: ${book.generos}</h4> <h4>Personajes: ${book.personajes}</h4> <h4>Nº Páginas: ${book.numero_paginas}</h4> <h4>Editoriales: ${book.editoriales}</h4> <h4>ISBN: ${book.ISBN}</h4> <h4>Portada: ${book.imagen_portada}</h4>`)
    else res.status(404).send("No se ha encontrado el libro")
}
app.get('/:isbn', getBookByISBN)

//ENDPOINT: MOSTRAR TODOS LOS LIBROS CON TITULO ENLAZADO A UNA IMAGEN
const getAllBooks = (req, res) => {
    let htmlContent = "<html><head><title>Lista de Libros</title></head><body>";

    books.forEach(book => 
        htmlContent += `<a href="${book.imagen_portada}">${book.titulo}</a> <br>`
    );
    htmlContent += "</body></html>";
    res.send(htmlContent);
}
app.get('/', getAllBooks)

//POST: AÑADIR LIBRO
const newBook = (req, res) => {
    const bookNew = req.body
    books.push(bookNew)
    res.status(201).json(bookNew)
}
app.post('/books', newBook)

const PORT = process.env.PORT ?? 1235
app.listen(PORT, () => {
    console.log(`server listening on port http://localhost:${PORT}`)
})