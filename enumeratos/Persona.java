package enumeratos;

public class Persona {

    enum GENERO{Masculino, Femenino}  //Enumerators
    enum ESTADO_CIVIL{Soltero, Casado, Viudo, Divorciado} //Enumerators

    public static void main(String[] args) {
        for (GENERO valx : GENERO.values()) {
            System.out.println(valx);
            }
            
        System.out.println("\n");
         for (ESTADO_CIVIL  ec: ESTADO_CIVIL.values()) {
            System.out.println(ec);
         }  
         
         System.out.println("\n"+ESTADO_CIVIL.Soltero);
    }
    
}
