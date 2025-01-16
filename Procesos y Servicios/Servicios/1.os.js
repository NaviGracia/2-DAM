const os = require('node:os');

console.log('Info OS:')
console.log('---------')
console.log('Nombre OS:', os.platform())
console.log('Versión OS:', os.release())
console.log('Arquitectura:', os.arch())
console.log('CPUs:', os.cpus())
console.log('Memory:', os.totalmem()/1024/1024)
console.log('Uptime:', os.uptime/60)