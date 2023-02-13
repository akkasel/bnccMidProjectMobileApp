package raymondAkkaselMidProjectLNT;

public class Car extends Vehicle{
    
    private int entertainmentSystem;

    public Car(String brand, String name, String license, int topSpeed, int gasCapacity, int wheels, String type, int entertainmentSystem){
        super(brand, name, license, type, topSpeed, gasCapacity, wheels);
        this.entertainmentSystem = entertainmentSystem;
    }

    @Override
    public void runFeature(){

        String message;

        if(getType().equals("Supercar")){
            message = "Turning on entertainment system...\n...\n...\n...\nBoosting!";
        } else {
            message = "Turning on entertainment system...";
        }

        for(int i = 0; i < message.length(); i++){

            try{
                Thread.sleep(100);
                System.out.print(message.charAt(i));
            } catch (InterruptedException e){
                e.printStackTrace();
            }
            
        }
    }

    public int getEntertainmentSystem(){
        return entertainmentSystem;
    }

}
