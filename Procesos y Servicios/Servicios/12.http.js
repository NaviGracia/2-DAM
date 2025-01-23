const http = require('node:http')
console.log(process.env)

const desiredPort = process.env.PORT ?? 3000

// Creamos el servidor, e indicamos qu� responder cuando se reciba una petici�n

const processRequest = (req, res) => {
    const { method, url } = req
    switch (method) {
        case 'GET':
            switch (url) {
                case '/':
                    res.statusCode = 200
                    res.setHeader('Content-Type', 'text/html; charset=utf-8')
                    return res.end(
                        '<h1>Pokemones</h1>' + 
                        '<h3><a href="./pokemon/smoliv">Smoliv</a></h3>' +
                        '<h3><a href="./pokemon/fuecoco">Fuecoco</a></h3>'
                    );

                case '/pokemon/smoliv':
                    res.statusCode = 200
                    res.setHeader('Content-Type', 'application/json; charset=utf-8')
                    const smolivJSON = require('./pokemon/smoliv.json')
                    return res.end(JSON.stringify(smolivJSON));
                
                case '/pokemon/fuecoco':
                    res.statusCode = 200
                    res.setHeader('Content-Type', 'application/json; charset=utf-8')
                    const fuecocoJSON = require('./pokemon/fuecoco.json')
                    return res.end(JSON.stringify(fuecocoJSON));
                
                default:
                    res.statusCode = 404
                    res.setHeader('Content-Type', 'text/html; charset=utf-8')
                    res.end('<h1>Error 404</h1>')
            }
    }
}

const server = http.createServer(processRequest)

// Iniciamos el servidor, escuchando en el puerto indicado

server.listen(desiredPort, () => {
  console.log(`server started on: http://localhost:${desiredPort}`)
})


