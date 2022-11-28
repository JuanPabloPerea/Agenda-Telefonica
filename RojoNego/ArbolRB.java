package agendatelefonicarb;

/**
 *
 * @author jpper
 */
public class ArbolRB {

    private NodoRB root;
    private NodoRB TNULL;
    // Search the tree
    
    public ArbolRB() {
        TNULL = new NodoRB();
        TNULL.color = 0;
        TNULL.izq = null;
        TNULL.der = null;
        root = TNULL;
    }

    private NodoRB buscarNodo(NodoRB nodoActual, int key) {
        if (nodoActual == TNULL || key == nodoActual.numTelefono) {
            return nodoActual;
        }

        if (key < nodoActual.numTelefono) {
            return buscarNodo(nodoActual.izq, key);
        }
        return buscarNodo(nodoActual.der, key);
    }

    // Balance the tree after deletion of a node
    private void balanceoArbol(NodoRB nodoActual) {
        NodoRB nodo;
        while (nodoActual != root && nodoActual.color == 0) {
            if (nodoActual == nodoActual.padre.izq) {
                nodo = nodoActual.padre.der;
                if (nodo.color == 1) {
                    nodo.color = 0;
                    nodoActual.padre.color = 1;
                    rotacionIzq(nodoActual.padre);
                    nodo = nodoActual.padre.der;
                }

                if (nodo.izq.color == 0 && nodo.der.color == 0) {
                    nodo.color = 1;
                    nodoActual = nodoActual.padre;
                } else {
                    if (nodo.der.color == 0) {
                        nodo.izq.color = 0;
                        nodo.color = 1;
                        rotacionDer(nodo);
                        nodo = nodoActual.padre.der;
                    }

                    nodo.color = nodoActual.padre.color;
                    nodoActual.padre.color = 0;
                    nodo.der.color = 0;
                    rotacionIzq(nodoActual.padre);
                    nodoActual = root;
                }
            } else {
                nodo = nodoActual.padre.izq;
                if (nodo.color == 1) {
                    nodo.color = 0;
                    nodoActual.padre.color = 1;
                    rotacionDer(nodoActual.padre);
                    nodo = nodoActual.padre.izq;
                }

                if (nodo.der.color == 0 && nodo.der.color == 0) {
                    nodo.color = 1;
                    nodoActual = nodoActual.padre;
                } else {
                    if (nodo.izq.color == 0) {
                        nodo.der.color = 0;
                        nodo.color = 1;
                        rotacionIzq(nodo);
                        nodo = nodoActual.padre.izq;
                    }

                    nodo.color = nodoActual.padre.color;
                    nodoActual.padre.color = 0;
                    nodo.izq.color = 0;
                    rotacionDer(nodoActual.padre);
                    nodoActual = root;
                }
            }
        }
        nodoActual.color = 0;
    }

    private void correcionArbol(NodoRB u, NodoRB v) {
        if (u.padre == null) {
            root = v;
        } else if (u == u.padre.izq) {
            u.padre.izq = v;
        } else {
            u.padre.der = v;
        }
        v.padre = u.padre;
    }

    private void eliminarNodo(NodoRB nodoActual, int numTelefono) {
        NodoRB z = TNULL;
        NodoRB temp;
        NodoRB aux;
        while (nodoActual != TNULL) {
            if (nodoActual.numTelefono == numTelefono) {
                z = nodoActual;
            }

            if (nodoActual.numTelefono <= numTelefono) {
                nodoActual = nodoActual.der;
            } else {
                nodoActual = nodoActual.izq;
            }
        }

        if (z == TNULL) {
            System.out.println("Couldn't find key in the tree");
            return;
        }

        aux = z;
        int yOriginalColor = aux.color;
        if (z.izq == TNULL) {
            temp = z.der;
            correcionArbol(z, z.der);
        } else if (z.der == TNULL) {
            temp = z.izq;
            correcionArbol(z, z.izq);
        } else {
            aux = minimo(z.der);
            yOriginalColor = aux.color;
            temp = aux.der;
            if (aux.padre == z) {
                temp.padre = aux;
            } else {
                correcionArbol(aux, aux.der);
                aux.der = z.der;
                aux.der.padre = aux;
            }

            correcionArbol(z, aux);
            aux.izq = z.izq;
            aux.izq.padre = aux;
            aux.color = z.color;
        }
        if (yOriginalColor == 0) {
            balanceoArbol(temp);
        }
    }

    // Balance the node after insertion
    private void correccionBalanceo(NodoRB nodoActual) {
        NodoRB temp;
        while (nodoActual.padre.color == 1) {
            if (nodoActual.padre == nodoActual.padre.padre.der) {
                temp = nodoActual.padre.padre.izq;
                if (temp.color == 1) {
                    temp.color = 0;
                    nodoActual.padre.color = 0;
                    nodoActual.padre.padre.color = 1;
                    nodoActual = nodoActual.padre.padre;
                } else {
                    if (nodoActual == nodoActual.padre.izq) {
                        nodoActual = nodoActual.padre;
                        rotacionDer(nodoActual);
                    }
                    nodoActual.padre.color = 0;
                    nodoActual.padre.padre.color = 1;
                    rotacionIzq(nodoActual.padre.padre);
                }
            } else {
                temp = nodoActual.padre.padre.der;

                if (temp.color == 1) {
                    temp.color = 0;
                    nodoActual.padre.color = 0;
                    nodoActual.padre.padre.color = 1;
                    nodoActual = nodoActual.padre.padre;
                } else {
                    if (nodoActual == nodoActual.padre.der) {
                        nodoActual = nodoActual.padre;
                        rotacionIzq(nodoActual);
                    }
                    nodoActual.padre.color = 0;
                    nodoActual.padre.padre.color = 1;
                    rotacionDer(nodoActual.padre.padre);
                }
            }
            if (nodoActual == root) {
                break;
            }
        }
        root.color = 0;
    }

    private void mostarArbol(NodoRB root, String indent, boolean last) {
        if (root != TNULL) {
            System.out.print(indent);
            if (last) {
                System.out.print("R----");
                indent += "   ";
            } else {
                System.out.print("L----");
                indent += "|  ";
            }

            String sColor = root.color == 1 ? "RED" : "BLACK";
            System.out.println(root.numTelefono +" "+ root.nombreUsu +"(" + sColor + ")");
            mostarArbol(root.izq, indent, false);
            mostarArbol(root.der, indent, true);
        }
    }

    public NodoRB buscar(int numTelefono) {
        return buscarNodo(this.root, numTelefono);
    }

    public NodoRB minimo(NodoRB nodoActual) {
        while (nodoActual.izq != TNULL) {
            nodoActual = nodoActual.izq;
        }
        return nodoActual;
    }

    public NodoRB maximo(NodoRB nosoActual) {
        while (nosoActual.der != TNULL) {
            nosoActual = nosoActual.der;
        }
        return nosoActual;
    }

    public NodoRB successor(NodoRB nodoActual) {
        if (nodoActual.der != TNULL) {
            return minimo(nodoActual.der);
        }

        NodoRB temp = nodoActual.padre;
        while (temp != TNULL && nodoActual == temp.der) {
            nodoActual = temp;
            temp = temp.padre;
        }
        return temp;
    }

    public NodoRB predecessor(NodoRB nodoActual) {
        if (nodoActual.izq != TNULL) {
            return maximo(nodoActual.izq);
        }

        NodoRB temp = nodoActual.padre;
        while (temp != TNULL && nodoActual == temp.izq) {
            nodoActual = temp;
            temp = temp.padre;
        }

        return temp;
    }

    public void rotacionIzq(NodoRB nodoActual) {
        NodoRB temp = nodoActual.der;
        nodoActual.der = temp.izq;
        if (temp.izq != TNULL) {
            temp.izq.padre = nodoActual;
        }
        temp.padre = nodoActual.padre;
        if (nodoActual.padre == null) {
            this.root = temp;
        } else if (nodoActual == nodoActual.padre.izq) {
            nodoActual.padre.izq = temp;
        } else {
            nodoActual.padre.der = temp;
        }
        temp.izq = nodoActual;
        nodoActual.padre = temp;
    }

    public void rotacionDer(NodoRB nodoActual) {
        NodoRB temp = nodoActual.izq;
        nodoActual.izq = temp.der;
        if (temp.der != TNULL) {
            temp.der.padre = nodoActual;
        }
        temp.padre = nodoActual.padre;
        if (nodoActual.padre == null) {
            this.root = temp;
        } else if (nodoActual == nodoActual.padre.der) {
            nodoActual.padre.der = temp;
        } else {
            nodoActual.padre.izq = temp;
        }
        temp.der = nodoActual;
        nodoActual.padre = temp;
    }

    public void insertar(int numTelefono, String nombre) {
        NodoRB nodoActual = new NodoRB();
        nodoActual.padre = null;
        nodoActual.numTelefono = numTelefono;
        nodoActual.nombreUsu = nombre;
        nodoActual.izq = TNULL;
        nodoActual.der = TNULL;
        nodoActual.color = 1;

        NodoRB aux = null;
        NodoRB temp = this.root;

        while (temp != TNULL) {
            aux = temp;
            if (nodoActual.numTelefono < temp.numTelefono) {
                temp = temp.izq;
            } else {
                temp = temp.der;
            }
        }

        nodoActual.padre = aux;
        if (aux == null) {
            root = nodoActual;
        } else if (nodoActual.numTelefono < aux.numTelefono) {
            aux.izq = nodoActual;
        } else {
            aux.der = nodoActual;
        }

        if (nodoActual.padre == null) {
            nodoActual.color = 0;
            return;
        }

        if (nodoActual.padre.padre == null) {
            return;
        }

        correccionBalanceo(nodoActual);
    }

    public NodoRB getRoot() {
        return this.root;
    }

    public void eliminar(int data) {
        eliminarNodo(this.root, data);
    }

    public void mostrar() {
        mostarArbol(this.root, "", true);
    }
}
