/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package agendatelefonica;

public class NodoAvl {
    int altura;
    int numTelefono;
    String nombreUsu;
    NodoAvl izq;
    NodoAvl der;
    
    NodoAvl(int a, String nombre){
        numTelefono = a;
        altura = 1;
        nombreUsu = nombre; 
    }
}
