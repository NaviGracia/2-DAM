const http = require('node:http')
const fs = require('node:fs')
console.log(process.env)

const desiredPort = process.env.PORT ?? 3001

// Creamos el servidor, e indicamos qu� responder cuando se reciba una petici�n


const server = http.createServer((req, res) => {
    if (req.url === '/') {
        res.statusCode = 200 // OK
        res.setHeader('Content-Type', 'text/html; charset=utf-8')
        res.end('<h1>Bienvenido a mi página web</h1>')
    } else if (req.url === '/imagen.png') {
        res.statusCode = 200 // OK
        res.setHeader('Content-Type', 'image/png')
        fs.readFile('./image.png', (err, data) => {
            if (err) {
                res.statusCode = 500 // Internal Server Error
                res.end('<h1>500 : Internal Server Error</h1>')
            } else {
                res.end(data)
            }
        })
    } else if (req.url === '/contacto') {
        //Web donde uncliyamos un email
        res.statusCode = 200 // OK
        res.setHeader('Content-Type', 'text/html')
        res.end("<label for='email'> Email: <input type='email'name='email' id='email'></label>")
     }else {
        //web indicando 404 Error
        res.statusCode = 404 // Error
        res.setHeader('Content-Type', 'text/html; charset=utf-8')
        res.end('<h1>Error 404</h1>')
     }
    })




// Iniciamos el servidor, escuchando en el puerto indicado

server.listen(desiredPort, () => {
  console.log(`server started on: http://localhost:${desiredPort}`)
})