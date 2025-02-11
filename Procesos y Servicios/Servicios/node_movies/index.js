const express = require('express') // require -> commonJS
const cors = require('cors')
const movies = require('./movies.json')

const app = express()
app.use(express.json())
app.use(cors())

//ENDPOINT: RECUPERAR TODAS LAS PELÍCULAS
// Y OPCIÓN DE FILTRAR POR GÉNERO
const getAllMovies = (req, res) => {
    const { genre } = req.query
    if (genre) {
        const filteredMovies = movies.filter(
            movie => movie.genre.includes(genre)
            //movie => movie.genre.some(
            //    g => g.toLowerCase() === genre.toLowerCase()
            //)
        )
        res.json(filteredMovies)
    } else {
        res.json(movies)
    }
}
app.get('/movies', getAllMovies)

// ENDPOINT: RECUPERAR PELÍCULA POR ID
const getMovieById = (req, res) => {
    const { id } = req.params
    const movie = movies.find(movie => movie.id === id)
    if (movie) res.send(`La peli es: ${movie.title}`)
    else res.status(404).send("No se ha encontrado la película")
}
app.get('/movies/:id', getMovieById)

const PORT = process.env.PORT ?? 1234
app.listen(PORT, () => {
console.log(`server listening on port http://localhost:${PORT}`)
})