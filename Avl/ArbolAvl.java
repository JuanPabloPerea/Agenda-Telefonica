package agendatelefonica;

/**
 *
 * @author jpper
 */
public class ArbolAvl {
    NodoAvl root;
    
    public void limpiarNodo(){
        root = null;
    }
    
/* Insercion de datos */
    
    public void insert(int numTelefono, String nombre) {
        root = insertarAvl(root, numTelefono, nombre);
    }
    
    public NodoAvl insertarAvl(NodoAvl nodoActual, int numTelefono, String nombre){
        if (nodoActual == null){
            return (new NodoAvl(numTelefono, nombre));
        }
        if (numTelefono < nodoActual.numTelefono) {
            nodoActual.izq = insertarAvl(nodoActual.izq, numTelefono, nombre);
        }else if(numTelefono > nodoActual.numTelefono){
            nodoActual.der = insertarAvl(nodoActual.der, numTelefono, nombre);
        }else{// si la clave esta duplicada retorna el mismo nodo
            return nodoActual;
        }
        //Actualizacion de altura
        nodoActual.altura = 1 + max(getAltura(nodoActual.izq), getAltura(nodoActual.der));
        // se obtiene el factor de equilibrio
        int fe = getFactorEquilibrio(nodoActual);
        //rotacion simple derecha
        if (fe > 1 && numTelefono < nodoActual.izq.numTelefono){
            return rotacionDer (nodoActual);
        }
        //rotacion simple izquierda
        if (fe < -1 && numTelefono > nodoActual.der.numTelefono){
            return rotacionIzq (nodoActual);
        }
        //rotacion doble izquierda derecha
        if (fe > 1 && numTelefono > nodoActual.izq.numTelefono){
            nodoActual.izq = rotacionIzq(nodoActual.izq);
            return rotacionDer(nodoActual);
        }
        //rotacion doble derecha izquierda
        if (fe < -1 && numTelefono < nodoActual.der.numTelefono){
            nodoActual.der = rotacionDer(nodoActual.der);
            return rotacionIzq(nodoActual);
        }
        return nodoActual;
    }
    
/* Eliminacion de un nodo */
    public void eliminar(int numTelefono){
        root = eliminarAvl(root, numTelefono);
    }
    
    public NodoAvl eliminarAvl(NodoAvl nodoActual, int numTelefono){
        if (nodoActual==null){
            return nodoActual;
        }
        if (numTelefono < nodoActual.numTelefono){
            nodoActual.izq = eliminarAvl(nodoActual.izq, numTelefono);
        }else if (numTelefono > nodoActual.numTelefono){
            nodoActual.der = eliminarAvl(nodoActual.der, numTelefono);
        }else{
        // El nodo es igual a la clave, se eliminca
        // nodo con un hijo o es hoja
            if ((nodoActual.izq == null) || (nodoActual.der == null)){
                NodoAvl temp = null;
                if (temp == nodoActual.izq){
                    temp = nodoActual.der;
                }else{
                    temp = nodoActual.izq;
                }
                // Caso no tiene hijos
                if (temp == null){
                    nodoActual = null;// se elimina dejandolo null
                }else{
                    nodoActual = temp;
                }
        // tiene dos o mas hijos
            }else{
            NodoAvl temp = getNodoconValorMaximo(nodoActual.izq);
            
            nodoActual.numTelefono = temp.numTelefono;
            
            nodoActual.izq = eliminarAvl(nodoActual.izq, temp.numTelefono);
            }      
        }
        if (nodoActual == null){
            return nodoActual;
        }
        // Actualizar altura
        nodoActual.altura = max(getAltura(nodoActual.izq), getAltura(nodoActual.der))+1;        
        // Calcular factor de equilibrio
        int fe = getFactorEquilibrio(nodoActual);
        // Rotaciones segun el resultado del factor de equilibrio
        // rotacion sencilla derecha
        if (fe > 1 && getFactorEquilibrio(nodoActual.izq) >= 0){
            return rotacionDer(nodoActual);
        }
        // rotacion sencilla izquierda
        if (fe < -1 && getFactorEquilibrio(nodoActual.der) <= 0){
            return rotacionIzq(nodoActual);
        }
        // rotacion doble derecha izquierda
        if(fe < -1 && getFactorEquilibrio(nodoActual.der) > 0){
            nodoActual.der = rotacionDer(nodoActual.der);
            return rotacionIzq(nodoActual);
        }
        // rotacion doble izquierda derecha 
        if(fe > 1 && getFactorEquilibrio(nodoActual.izq) < 0){
            nodoActual.izq = rotacionIzq(nodoActual.izq);
            return rotacionDer(nodoActual);
        }
        return nodoActual;
    }
    
    // Mostrar los datos guardados en el arbol
    public void mostrarArbolAvl(){
        System.out.println("Arbol Avl");
        mostrarArbol(root,0);
    }
    
    private void mostrarArbol(NodoAvl nodo, int depth){
        if (nodo.der != null){
            mostrarArbol(nodo.der, depth + 1);
        }
        for (int i = 0; i < depth; i++) {
            System.out.print("  ");
        }
        System.out.println("(" + nodo.numTelefono +" "+ nodo.nombreUsu +")");
        if (nodo.izq != null){
            mostrarArbol(nodo.izq, depth + 1);
        }
    }
    // Metodos auxiliares
    
    private int getAltura(NodoAvl nodoActual){
        if (nodoActual == null){
            return 0;
        }
        return nodoActual.altura;
    }
    
    private int max(int a, int b){
        return (a > b) ? a : b;
    }
    
    private int getFactorEquilibrio(NodoAvl nodoActual){
        if (nodoActual == null){
            return 0;
        }
        return getAltura(nodoActual.izq) - getAltura(nodoActual.der);
    }
    
    private NodoAvl getNodoconValorMaximo(NodoAvl nodo){
        NodoAvl actual = nodo;
        while (actual.der != null){
            actual = actual.der;
        }
        return actual;
    }
     
    private NodoAvl rotacionDer(NodoAvl nodoActual){
        NodoAvl nuevaRaiz = nodoActual.izq;
        NodoAvl temp = nuevaRaiz.der;
    
        //se hace la rotacion
        nuevaRaiz.der = nodoActual;
        nodoActual.izq = temp;
    
        //actualizar alturaz
        nodoActual.altura = max(getAltura(nodoActual.izq),getAltura(nodoActual.der))+1;
        nuevaRaiz.altura = max(getAltura(nodoActual.izq),getAltura(nodoActual.der))+1;
    
        return nuevaRaiz;
    }
    
    private NodoAvl rotacionIzq(NodoAvl nodoActual){
        NodoAvl nuevaRaiz = nodoActual.der;
        NodoAvl temp = nuevaRaiz.izq;
        
        //se hace la rotacion
        nuevaRaiz.izq = nodoActual;
        nodoActual.der = temp;
        
        //actualizar alturaz
        nodoActual.altura = max(getAltura(nodoActual.izq),getAltura(nodoActual.der))+1;
        nuevaRaiz.altura = max(getAltura(nodoActual.izq),getAltura(nodoActual.der))+1;
        return nuevaRaiz;
    }
    
    
    
}
