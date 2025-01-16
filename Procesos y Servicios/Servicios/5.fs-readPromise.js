console.log('Leyendo primer archivo...')
fs.readFile('./archivo.txt', 'utf-8')
    .then(text => {
        console.log('primer texto: ', text)
    }
)

console.log('Leyendo segundo archivo...')
fs.readFile('./archivo2.txt', 'utf-8')
    .then(text => {
        console.log('primer texto: ', text)
    }
)

console.log('Fin Lecturas')
