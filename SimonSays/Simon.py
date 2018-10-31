
from Tkinter import *
import tkMessageBox
import random
import time
import winsound

class Simon:

    def __init__(self):
        self.ventana = Tk()
        self.arreglo = []
        self.marcador = 0
        self.mayor = 0
        self.contador= 0
        self.juegoI = False
        self.colores = ["Azul", "Amarillo","Verde", "Rojo"]
        self.ventana.title("Simon says")
        self.ventana.geometry("500x500")
        self.iniciarBotones()
        self.ventana.mainloop()
        
    def iniciarBotones(self):
        self.btnAzul = Button(self.ventana, 
        command=lambda: self.presionar("Azul"), height=6, width=13, bg="blue")
        self.btnAzul.place(x=100, y=100)

        self.btnAmarillo = Button(self.ventana, 
        command=lambda: self.presionar("Amarillo"), height=6, width=13, bg="yellow")
        self.btnAmarillo.place(x=200, y=100)

        self.btnVerde = Button(self.ventana, 
        command=lambda: self.presionar("Verde"), height=6, width=13, bg="green")
        self.btnVerde.place(x=100, y=200)

        self.btnRojo = Button(self.ventana, 
        command=lambda: self.presionar("Rojo"), height=6, width=13, bg="red")
        self.btnRojo.place(x=200, y=200)

        self.btnIniciar = Button(self.ventana, command= lambda: self.iniciar(), height = 2, width = 7, bg ="white", text="Iniciar")
        self.btnIniciar.place(x=175, y=30)

        self.etiqueta = Label(self.ventana, text="Marcador: 0 Mayor: 0")
        self.etiqueta.place(x=100, y=80)

    def presionar(self, color):
         if self.juegoI == True:
            if len(self.arreglo) >= self.contador-1:
                if self.arreglo[self.contador] == color:
                    self.contador += 1
                    if color == "Amarillo":
                        self.sonido(600,500)
                    if color == "Azul":
                        self.sonido(500,500)
                    if color == "Rojo":
                        self.sonido(700,500)
                    if color == "Verde":
                        self.sonido(800,500)
                    self.revisarTurno()
                    self.etiqueta.config(text ="Marcador: " + str(self.contador) + " Mayor: " + str(self.mayor))
                else:
                    tkMessageBox.showinfo("Perdiste", "Perdiste Marcador: " + str(self.marcador))
                    
                    if self.marcador > self.mayor:
                        self.mayor = self.marcador
                    self.etiqueta.config(text ="Marcador: " + str(self.contador) + " Mayor: " + str(self.mayor))
                    self.juegoI = False
                    self.contador = 0
                    self.marcador = 0
                    self.arreglo = 0

    def iniciar(self):
        self.contador = 0
        self.marcador = 0
        self.arreglo = []
        self.juegoI = True
        self.crearColor()

    def revisarTurno(self):
        if len(self.arreglo) == self.contador:
            self.contador = 0
            self.marcador += 1
            self.btnIniciar.after(1000, self.crearColor)

    

    def crearColor(self):
        if self.juegoI == True:
            i=0
            while i < len(self.arreglo):
                if self.arreglo[i] == "Azul":
                    self.cambio(self.btnAzul, "#6A5ACD", "blue", 500, 500)
                if self.arreglo[i] == "Amarillo":
                    self.cambio(self.btnAmarillo, "#FFD700", "yellow", 600, 500)
                if self.arreglo[i] == "Rojo":
                    self.cambio(self.btnRojo, "orange", "red", 700, 500)
                if self.arreglo[i] == "Verde":
                    self.cambio(self.btnVerde, "#00FF00", "green", 800, 500)
                i+=1
                time.sleep(1)

            aleatorio = random.randrange(0,4)

            self.arreglo.append(self.colores[aleatorio])
            if self.arreglo[i] == "Azul":
                    self.cambio(self.btnAzul, "#6A5ACD", "blue", 500, 500)
            if self.arreglo[i] == "Amarillo":
                    self.cambio(self.btnAmarillo, "#FFD700", "yellow", 600, 500)
            if self.arreglo[i] == "Rojo":
                    self.cambio(self.btnRojo, "orange", "red", 700, 500)
            if self.arreglo[i] == "Verde":
                    self.cambio(self.btnVerde, "#00FF00", "green", 800, 500)

       
                    

    def cambio(self, btn, colorCambio, colorInicial, f, d):
        btn.configure(bg=colorCambio)
        self.ventana.update()
        self.sonido(f,d)
        btn.configure(bg=colorInicial)
        self.ventana.update()

    def sonido(self, frecuencia, duracion):
        winsound.Beep(frecuencia, duracion)
        
obj = Simon()
