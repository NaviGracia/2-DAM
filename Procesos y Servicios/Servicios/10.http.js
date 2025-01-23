const http = require('node:http')
console.log(process.env)

const desiredPort = process.env.PORT ?? 3000

// Creamos el servidor, e indicamos qu� responder cuando se reciba una petici�n

const server = http.createServer((req, res) => {
  console.log('request received')
  res.end('Hello, World!')
})


// Iniciamos el servidor, escuchando en el puerto indicado

server.listen(desiredPort, () => {
  console.log(`server started on: http://localhost:${desiredPort}`)
})