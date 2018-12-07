const {app, BrowserWindow, Menu} = require('electron');
const url = require('url');
const path = require('path');

if(process.eventNames.NODE_ENV !== 'production'){
    require('electron-reload')(__dirname, {
        electron: path.join(__dirname, '../node_modules', '.bin'
        ,'electron')
    })
}


let mainWindow;
let newProductWindow;


app.on('ready', ()=> {
    mainWindow= new BrowserWindow({})
    mainWindow.loadURL(url.format({
        pathname: path.join(__dirname, 'views/index.html'),
        protocol: 'file',
        slashes: true
    }))

    const mainMenu= Menu.buildFromTemplate(templateMenu);
    Menu.setApplicationMenu(mainMenu)
});

function createNewProductWindow(){
    newProductWindow = new BrowserWindow({
        width:400,
        height:500,
        title:'Add a new Product'
    });
    newProductWindow.setMenu(false);
    newProductWindow.loadURL(url.format({
        pathname: path.join(__dirname, 'views/newProduct.html'),
        protocol: 'file',
        slashes: true
    }))

}

const templateMenu = [
    {
        label:'File',
        submenu:[
            {
                label: 'New Product',
                accelerator:'ctrl+N',
                click(){
                    createNewProductWindow()
                }
            }
        ]
        
    }
]
   