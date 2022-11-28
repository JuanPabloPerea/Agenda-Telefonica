package agendatelefonica;
/**
 *
 * @author jpper
 */
public class AgendaTelefonica {

    /**
     * @param args the command line arguments
     */
    /*Integrantes: 20162020074 Juan Pablo Perea Hernandez
                    20182020151 Kevin Nicolas Sierra Gonzales
                    20202020003 Ludwind Rico Rodriguez*/
    public static void main(String[] args) throws InterruptedException {
        
        long inicio = System.currentTimeMillis();
        Thread.sleep(2000);
        ArbolAvl arbol = new ArbolAvl();
        /*
        Ejemplo de insercion de datos en el arbol
        al mostrar el arbol en vez de mostrar de arriba a abajo
        muestra el arbol de derecha a izquierda*/
        System.out.println("Ejemplo 1");
        arbol.insert(3114, "suyo");
        arbol.insert(3152, "suyo");
        arbol.insert(3134, "mios");
        arbol.insert(3167, "suyos");
        arbol.insert(3189, "nuestros");
        arbol.insert(3102, "vuestros");
        arbol.insert(3204, "tuyo");
        arbol.mostrarArbolAvl();
        arbol.eliminar(3134);
        arbol.mostrarArbolAvl();
        
        long fin = System.currentTimeMillis();
        double tiempoEje = (double)((fin - inicio)/1000);
        System.out.println("el timepo de ejecucion fue: "+tiempoEje+"segundos");
    }
    
}
