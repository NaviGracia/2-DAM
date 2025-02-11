const http = require('http');
const fs = require('fs');
const path = require('path');

const desiredPort = process.env.PORT ?? 3000

// Crear servidor HTTP
const server = http.createServer((req, res) => {
    const url = req.url;

    // Si la ruta es la principal ("/")
    if (url === '/') {
        // Leer los archivos de la carpeta 'pokemon'
        fs.readdir('/pokemon', (err, files) => {
            if (err) {
                res.statusCode = 500;
                res.end('Error al leer los archivos.');
                return;
            }

            // Filtrar solo los archivos .json
            const pokemons = files.filter(file => file.endsWith('.json'));
            
            // Generar HTML con los enlaces a cada Pokémon
            let htmlResponse = '<html><body><h1>Lista de Pokémons</h1><ul>';
            pokemons.forEach(file => {
                const pokemonName = path.basename(file, '.json');
                htmlResponse += `<li><a href="/pokemon?name=${pokemonName}">${pokemonName}</a></li>`;
            });
            htmlResponse += '</ul></body></html>';

            // Enviar respuesta con la lista de Pokémon
            res.statusCode = 200;
            res.setHeader('Content-Type', 'text/html');
            res.end(htmlResponse);
        });
    } 
    // Si la ruta es /pokemon con el parámetro 'name'
    else if (url.startsWith('/pokemon')) {
        const urlParams = new URLSearchParams(url.split('?')[1]);
        const pokemonName = urlParams.get('name');
        
        if (pokemonName) {
            // Construir la ruta del archivo JSON del Pokémon
            const filePath = path.join(__dirname, 'pokemon', `${pokemonName}.json`);
            
            fs.readFile(filePath, 'utf8', (err, data) => {
                if (err) {
                    res.statusCode = 404;
                    res.setHeader('Content-Type', 'application/json');
                    res.end(JSON.stringify({ error: 'Pokémon no encontrado' }));
                    return;
                }

                // Enviar el JSON como respuesta
                res.statusCode = 200;
                res.setHeader('Content-Type', 'application/json');
                res.end(data);
            });
        } else {
            res.statusCode = 400;
            res.setHeader('Content-Type', 'application/json');
            res.end(JSON.stringify({ error: 'Falta el parámetro "name"' }));
        }
    } 
    // Si la ruta no es válida
    else {
        res.statusCode = 404;
        res.end('Ruta no encontrada');
    }
});

// Iniciar el servidor en el puerto 8000
server.listen(desiredPort, () => {
    console.log(`server started on: http://localhost:${desiredPort}`);
});
