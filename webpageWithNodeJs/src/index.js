const express = require ('express');
const path = require('path')
const app =express();

//setting
app.set('port', 3000)

//middlewares

//routes
app.get('/', (req, res) => {
    res.sendFile(path.join(__dirname, 'views/index.html'));
})

//static files

app.listen(app.get('port'));