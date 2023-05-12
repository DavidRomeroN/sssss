package upeu;

public class ClaseGeneral {
    public static void main(String[] args) {
        Persona person=new Persona();       
        person.edad=19;
        person.nombre="Jose";
        person.saludo();

        Persona person1=new Persona();
        person1.setNombre("Raul");//Encampsulamiento
        person1.setEdad(20);//Encampsulamiento
        person1.saludo();     
        
    }
}
