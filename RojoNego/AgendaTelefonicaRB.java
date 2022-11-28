package agendatelefonicarb;

/**
 *
 * @author jpper
 */
public class AgendaTelefonicaRB {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        
        long inicio = System.currentTimeMillis();
        Thread.sleep(2000);
        
        ArbolRB arbol = new ArbolRB();
        arbol.insertar(55,"mio");
        arbol.insertar(40,"suyo");
        arbol.insertar(65,"tuyo");
        arbol.insertar(60,"vuestro");
        arbol.insertar(75,"mios");
        arbol.insertar(57,"suyos");
        arbol.mostrar();
        arbol.eliminar(75);
        arbol.mostrar();
        
        long fin = System.currentTimeMillis();
        double tiempoEje = (double)((fin - inicio)/1000);
        System.out.println("el timepo de ejecucion fue: "+tiempoEje+"segundos");
    }

}
