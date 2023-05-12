package claseinterna;

import claseinterna.ClaseExterna.ClaseInterna;

public class ClaseExterna { //Clase Interna
    public int a;
    public int b;

    public int suma(int a, int b) {
        return a+b;
    }
    public int suma() {
        return a+b;
    }    

    public class ClaseInterna {
        public int resultado;

        public int multiplicar( int a, int b) {
            return resultado=a*b;
        }
        
    }    
}


class Principal {

    public static void main(String[] args) {
        ClaseExterna obj=new ClaseExterna();
        
        ClaseInterna myCIobj=obj.new ClaseInterna();
        myCIobj.multiplicar(3, 2);
        System.out.println("R: "+myCIobj.resultado);

        obj.a=4;
        obj.b=5;
        System.out.println("Suma:"+obj.suma());
    }
}
