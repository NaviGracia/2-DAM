console.log('Leyendo primer archivo...')
const text = fs.readFileSync('./archivo.txt', 'utf-8')
console.log('primer texto: ', text)

console.log('Leyendo segundo archivo...')
const text2 = fs.readFileSync('./archivo2.txt', 'utf-8')
console.log('primer texto: ', text2)

console.log('Fin Lecturas')
