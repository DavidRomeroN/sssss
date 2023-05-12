package usomemoria;

public class UsoMemoria {
   static Persona p1;
   static int cantidad=30;

    public static void main(String[] args) {
        p1=new Persona();
        p1.nombre="Dario";
        p1.dni="34631917";
        System.out.println(p1.nombre);
    }
}

class Persona {
    public String nombre, dni; 
}