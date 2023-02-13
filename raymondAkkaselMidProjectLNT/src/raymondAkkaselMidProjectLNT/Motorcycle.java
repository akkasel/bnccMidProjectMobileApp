package raymondAkkaselMidProjectLNT;

public class Motorcycle extends Vehicle {

    private int helmetCount;

    public Motorcycle(String brand, String name, String license, int topSpeed, int gasCapacity, int wheels, String type, int helmetCount){
        super(brand, name, license, type, topSpeed, gasCapacity, wheels);
        this.helmetCount = helmetCount;
    }

    @Override
    public void runFeature(){
        String message = getName() + " is standing!";

        for(int i = 0; i < message.length(); i++){

            try{
                Thread.sleep(100);
                System.out.print(message.charAt(i));
            } catch (InterruptedException e){
                e.printStackTrace();
            }
            
        }
        
    }

    public int getHelmetCount(){
        return helmetCount;
    }
}
