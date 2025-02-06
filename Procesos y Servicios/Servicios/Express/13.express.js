const express = require('express')
const app = express()
const PORT = process.env.PORT ?? 1234

let pokelist = []
fs.readdir('./pokemon/', (err, files) => {
    if(err){
        console.error(err)
        return
    }
    pokelist = files
})

//ENDPOINT GET
const get_root = (req, res) => {
    res.status(200).send('<h1>Bienvenido a mi página web</h1>')
}
app.get('/', get_root)

//ENDPOINT GET POKEMON
const get_pokemon = (req, res) => {
    const { name } = req.params
    name += '.json'
    if(pokelist.includes(name)){
        const poke = './pokemon' + name
        const pokejson = require(poke)
        res.json(pokejson)
    }else {
        res.status(404).send('<h1>Pokemon no encontrado</h1>')
    }
}

app.get('/pokemon/:name', get_pokemon)

//ENDPOINT POST POKEMON
app.post('/pokemon', (req, res) => {
    let body = ''

    req.on('data', chunk => {
        body += chunk.toString()
    })

    req.on('end', () => {
        const data = JSON.parse(body)
        data.tiempo = Date.now()
        res.status(201).json(data)
    })
})

//CASO POR DEFECTO
app.use((req, res) => {
    res.status(404).send('<h1>404 : Página no encontrada</h1>')
})

//INICIALIZACIÓN
app.listen(PORT, () => {
    console.log('server started on http://localhost:${PORT}')
})