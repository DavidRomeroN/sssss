package herencia;

public class Carro extends Vehiculo{//Clase hijo
    public  String modelo="Mustang";
    public int anho_fab=2020;
    public long vigencia_soat=1;

    public char vigencoa_soat_r=1;
    public short vigencia_soat_m=11;

    public static void main(String[] args) {
        Carro objCar=new Carro();
        
        System.out.println("Mi carro es de marca "+objCar.marca+ " y su modelo es "+objCar.modelo+
        " y el sonido que emite es: "+ objCar.sonido());
    }
}
