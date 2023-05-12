package polimorfismoabstracto;

public class Loro extends Animal{

    @Override
    public void soundAnimal() {//Polimorfismo
      System.out.println("Estoy durmiendo...no molestar!!");
    }

    public void dormir() {//Polimorfismo
        System.out.println("Voy a dormir ...no molestar!!");
    }

    public void cantar() {
        System.out.println("Laralalalala!!!");
    }
    
}
