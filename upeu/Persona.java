package upeu;

/**
 * Persona
 */
public class Persona {

    public String nombre="David";
    public int edad;
    public String dni;
    //previo para Encampsulamiento
    public String getDni(){return dni;}
    public void setDni(String dni){this.dni=dni;}
    
    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}
    
    public int getEdad() {return edad;}
    public void setEdad(int edad) {this.edad = edad;}    
    //Fin para Encampsulamiento
    public void saludo() {
        System.out.println("Hola, soy "+nombre+" y mi edad es:"+edad);
    }
    
}