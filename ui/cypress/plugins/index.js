//descarga de archivo
const downloadFile = require('cypress-downloadfile/lib/addPlugin')
module.exports = (on, config) => {
    downloadFile(on, config)
    return config
}

//verificación de descarga de archivo
const { isFileExist, findFiles } = require('cy-verify-downloads');
module.exports = (on, config) => {
   on('task', { isFileExist, findFiles });
}  