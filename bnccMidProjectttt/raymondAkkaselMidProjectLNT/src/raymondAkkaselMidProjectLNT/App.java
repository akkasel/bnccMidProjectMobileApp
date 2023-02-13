package raymondAkkaselMidProjectLNT;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class App {

    public static void main(String[] args) throws Exception {
        ArrayList<Car> carList = new ArrayList<Car>();
        ArrayList<Motorcycle> motorList = new ArrayList<Motorcycle>();

        showMenu(carList, motorList);
    }

    public static void showMenu(ArrayList<Car> carList, ArrayList<Motorcycle> motorList){

        System.out.print("\033[H\033[2J");
        System.out.flush();

        Scanner scanner = new Scanner(System.in);

        System.out.println("1. Add Vehicle");
        System.out.println("2. View Vehicle");
        System.out.println("3. Exit");
        System.out.print(">> ");

        int choice = 0; 

        try{
            choice = scanner.nextInt();
            scanner.nextLine();
        } catch(InputMismatchException e){
            System.out.print("Input must be an integer! [1 - 3]");
            scanner.nextLine();scanner.nextLine();
            showMenu(carList, motorList);
        }

        switch(choice){
            case 1:
            addVehicle(carList, motorList);
                break;
            case 2:
            viewVehicle(carList, motorList);
                break;
            case 3:
            System.exit(0);
                break;
            default:
            showMenu(carList, motorList);
                break;
        }
        scanner.close();
    };

    public static void addVehicle(ArrayList<Car> carList, ArrayList<Motorcycle> motorList){
        System.out.print("\033[H\033[2J");
        System.out.flush();

        Scanner scanner = new Scanner(System.in);

        String category;

        do{

            System.out.print("Input type [Car | Motorcycle]: ");
            category = scanner.nextLine();

        }while(!category.equals("Car") && !category.equals("Motorcycle"));

        String brand;

        do{

            System.out.print("Input brand [>= 5]: ");
            brand = scanner.nextLine();

        }while(brand.length() < 5);


        String name;

        do{

            System.out.print("Input name [>= 5]: ");
            name = scanner.nextLine();

        }while(name.length() < 5);

        String license;

        do{
            System.out.print("Input license: ");
            license = scanner.nextLine();
        }while(verifyLicense(license) == 1);


        int topSpeed = 0;
        int flag = 0;

        do{
            flag = 0; 
            try{
                System.out.print("Input top speed [100 <= topSpeed <= 250]: ");
                topSpeed = scanner.nextInt();
                scanner.nextLine();
            }catch(InputMismatchException e){
                System.out.println("Input must be an integer!");
                scanner.nextLine();
                flag = 1;
            }

        }while(topSpeed < 100 || topSpeed > 250 || flag == 1);

        int gasCapacity = 0;

        do{
            flag = 0;
            try{
                System.out.print("Input gas capacity [30 <= gasCap <= 60]: ");
                gasCapacity = scanner.nextInt();
                scanner.nextLine();
            }catch(InputMismatchException e){
                System.out.println("Input must be an integer!");
                scanner.nextLine();
                flag = 1;
            }
        }while(gasCapacity < 30 || gasCapacity > 60 || flag == 1);

        int wheels = 0;

        if(category.equals("Car")){

            do{
                flag = 0;
                try{
                    System.out.print("Input wheel [4 <= wheel <= 6]: ");
                    wheels = scanner.nextInt();
                    scanner.nextLine();
                }catch(InputMismatchException e){
                    System.out.println("Input must be an integer!");
                    scanner.nextLine();
                    flag = 1;
                };
            }while(wheels < 4 || wheels > 6 || flag == 1);

        } else {

            do{
                flag = 0;
                try{
                    System.out.print("Input wheel [2 <= wheel <= 3]: ");
                    wheels = scanner.nextInt();
                    scanner.nextLine();
                }catch(InputMismatchException e){
                    System.out.println("Input must be an integer!");
                    scanner.nextLine();
                    flag = 1;
                }
            }while(wheels < 2 || wheels > 3 || flag == 1);

        }

        String type;

        if(category.equals("Car")){

            do{
                System.out.print("Input type [SUV || Supercar || Minivan]: ");
                type = scanner.nextLine();
            }while(!type.equals("SUV") && !type.equals("Supercar") && !type.equals("Minivan"));

        } else {

            do{
                System.out.print("Input type [Automatic || Manual]: ");
                type = scanner.nextLine();
            }while(!type.equals("Automatic") && !type.equals("Manual"));

        }

        int count = 0;

        if(category.equals("Car")){

            do{

                flag = 0;

                try{
                    System.out.print("Input entertainment system amount [>= 1]: ");
                    count = scanner.nextInt();   
                    scanner.nextLine();
                }catch(InputMismatchException e){
                    System.out.println("Input must be an integer!");
                    scanner.nextLine();
                    flag = 1;
                }

            }while(count < 1 || flag == 1);

        } else {

            do{
                flag = 0;

                try{
                    System.out.print("Input helmet amount [>= 1]: ");
                    count = scanner.nextInt();
                    scanner.nextLine();
                }catch(InputMismatchException e){
                    System.out.println("Input must be an integer!");
                    scanner.nextLine();
                    flag = 1;
                }

            }while(count < 1 || flag == 1);

        }

        if(category.equals("Car")){
            Car newCar = new Car(brand, name, license, topSpeed, gasCapacity, wheels, type, count);
            carList.add(newCar);
        } else {
            Motorcycle newRide = new Motorcycle(brand, name, license, topSpeed, gasCapacity, wheels, type, count);
            motorList.add(newRide);
        }

        System.out.print("ENTER to return");
        scanner.nextLine();

        showMenu(carList, motorList);

        scanner.close();
        
    }

    public static int verifyLicense(String license){

        int licenseLength = license.length();

        int flag = 0;

        if(licenseLength > 7){
            if(!Character.isUpperCase(license.charAt(0))){
                System.out.println("Invalid Character! Capital Letter Only!\n");
                flag = 1;
            } 
    
            if(license.charAt(1) != ' '){
                System.out.println("Space Missing!\n");
                flag = 1;
            } 
            
            for(int i = 2; i < 6; i++){
    
                int digit = license.charAt(i) - '0';
    
                if(digit > 10){
                    System.out.println("Invalid Numbering! [4 Digits]\n");
                    flag = 1;
                    break;
                }
            }
    
            if(license.charAt(6) != ' '){
                System.out.println("Space Missing!\n");
                flag = 1;
            }
    
            int firstLength = 7;

            if(licenseLength - firstLength < 4){
                for(int i = firstLength; i < licenseLength; i++){
    
                    if(!Character.isUpperCase(license.charAt(i))){
                        System.out.println("Invalid Character! Capital Letters Only! [1 <= n <= 3] ");
                        flag = 1;
                        break;
                    }
                }
            } else {
                System.out.println("[1 <= n <= 3] Characters Only!");
                flag = 1;
            }
    
            
        } else {
            System.out.println("Incomplete License Plate!");
            flag = 1;
        }

        return flag;

    }

    public static void viewVehicle(ArrayList<Car> carList, ArrayList<Motorcycle> motorList){
        System.out.print("\033[H\033[2J");
        System.out.flush();

        Scanner scanner = new Scanner(System.in);

        System.out.println("1. View All");
        System.out.println("2. View Car");
        System.out.println("3. View Motorcycle");
        System.out.println("4. Back to Main Menu");
        System.out.print(">> ");

        int choice = 0;

        try{
            choice = scanner.nextInt();
            scanner.nextLine();
        }catch(InputMismatchException e){
            System.out.println("Input must be an integer! [1 - 3]");
            scanner.nextLine();scanner.nextLine();
            viewVehicle(carList, motorList);
        }

        switch(choice) {
            case 1:
            viewAll(carList, motorList);
                break;
            case 2:
            viewCar(carList, motorList);
                break;
            case 3:
            viewMotorcycle(carList, motorList);
                break;
            case 4:
            showMenu(carList, motorList);
                break;
            default:
            viewVehicle(carList, motorList);
                break;
        }


        scanner.close();
    }

    public static void viewCar(ArrayList<Car> carList, ArrayList<Motorcycle> motorList){
        System.out.print("\033[H\033[2J");
        System.out.flush();

        Scanner scanner = new Scanner(System.in);

        System.out.println("|-----|---------------|---------------|");
        System.out.println("|No   |Type           |Name           |");
        System.out.println("|-----|---------------|---------------|");
        if(carList.size() != 0){

            for(int i = 0; i < carList.size(); i++){
                System.out.printf("|%-5d|", i + 1);
                System.out.printf("%-15s|", "Car");
                System.out.printf("%-15s|", carList.get(i).getName());
                System.out.println();
            }

        } else {
            System.out.println("|-----|NO DATA--------|NO DATA--------|");
        }

        System.out.println("|-----|---------------|---------------|");
        System.out.println("|-----|---------------|---------------|");
        
        System.out.print("Pick a vehicle number to test drive[Enter '0' to exit]: ");

        Integer choice = 0;

        try{
            choice = scanner.nextInt();
            scanner.nextLine();
        } catch (InputMismatchException e){
            viewCar(carList, motorList);
        }

        if(choice.toString().equals("0")){
            showMenu(carList, motorList);
        }

        if((choice) > carList.size()){
            viewCar(carList, motorList);
        }

        choice -= 1;

        showCar(carList, motorList, choice);

        scanner.close();
    }

    public static void viewMotorcycle(ArrayList<Car> carList, ArrayList<Motorcycle> motorList){
        System.out.print("\033[H\033[2J");
        System.out.flush();

        Scanner scanner = new Scanner(System.in);

        System.out.println("|-----|---------------|---------------|");
        System.out.println("|No   |Type           |Name           |");
        System.out.println("|-----|---------------|---------------|");
        if(motorList.size() != 0){

            for(int i = 0; i < motorList.size(); i++){
                System.out.printf("|%-5d|", i + 1);
                System.out.printf("%-15s|", "Motorcycle");
                System.out.printf("%-15s|", motorList.get(i).getName());
                System.out.println();
            }

        } else {
            System.out.println("|-----|NO DATA--------|NO DATA--------|");
        }

        System.out.println("|-----|---------------|---------------|");
        System.out.println("|-----|---------------|---------------|");

        System.out.print("Pick a vehicle number to test drive[Enter '0' to exit]: ");

        Integer choice = 0;

        try{
            choice = scanner.nextInt();
            scanner.nextLine();
        } catch (InputMismatchException e){
            viewMotorcycle(carList, motorList);
        }

        if(choice.toString().equals("0")){
            showMenu(carList, motorList);
        }

        if((choice) > motorList.size()){
            viewMotorcycle(carList, motorList);
        }

        choice -= 1;

        showMotorcycle(carList, motorList, choice);

        scanner.close();
    }

    public static void viewAll(ArrayList<Car> carList, ArrayList<Motorcycle> motorList){
        System.out.print("\033[H\033[2J");
        System.out.flush();

        Scanner scanner = new Scanner(System.in);

        System.out.println("|-----|---------------|---------------|");
        System.out.println("|No   |Type           |Name           |");
        System.out.println("|-----|---------------|---------------|");

        int index = 0;
        
        if(motorList.size() != 0 || carList.size() != 0){

            for(int i = 0; i < motorList.size(); i++){
                System.out.printf("|%-5d|", index + 1);
                System.out.printf("%-15s|", "Motorcycle");
                System.out.printf("%-15s|", motorList.get(i).getName());
                System.out.println();
                index++;
            }

            for(int i = 0; i < carList.size(); i++){
                System.out.printf("|%-5d|", index + 1);
                System.out.printf("%-15s|", "Car");
                System.out.printf("%-15s|", carList.get(i).getName());
                System.out.println();
                index++;
            }

        } else {
            System.out.println("|-----|NO DATA--------|NO DATA--------|");
        }

        System.out.println("|-----|---------------|---------------|");
        System.out.println("|-----|---------------|---------------|");

        System.out.print("Pick a vehicle number to test drive[Enter '0' to exit]: ");

        Integer choice = 0;

        try{
            choice = scanner.nextInt();
            scanner.nextLine();
        } catch (InputMismatchException e){
            viewAll(carList, motorList);
        }

        if(choice.toString().equals("0")){
            showMenu(carList, motorList);
        }

        int total = carList.size() + motorList.size();
        if(choice > total){
            viewAll(carList, motorList);
        }

        if((choice) > motorList.size()){
            
            choice = choice - 1 - motorList.size();

            System.out.println(choice);

            showCar(carList, motorList, choice);
        } else {
            choice -= 1;
            System.out.println(choice);

            showMotorcycle(carList, motorList, choice);
        }

        scanner.close();

    }

    

    public static void showMotorcycle(ArrayList<Car> carList, ArrayList<Motorcycle> motorList, int index){
        System.out.print("\033[H\033[2J");
        System.out.flush();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Brand: " + motorList.get(index).getBrand());
        System.out.println("Name: " + motorList.get(index).getName());
        System.out.println("License Plate: " + motorList.get(index).getLicense());
        System.out.println("Type: " + motorList.get(index).getType());
        System.out.println("Gas Capacity: " + motorList.get(index).getGasCapacity());
        System.out.println("Top Speed: " + motorList.get(index).getTopSpeed());
        System.out.println("Wheel(s): " + motorList.get(index).getWheels());
        System.out.println("Helmet: " + motorList.get(index).getHelmetCount());

        System.out.print("Press Enter To Start " + motorList.get(index).getName() + "!");
        scanner.nextLine();

        revEngine();

        motorList.get(index).runFeature();

        scanner.nextLine();

        int price = 0;
        int flag = 0;

        do{ 
            flag = 0;
            try{
                System.out.print("Input helmet price: ");
                price = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e){
                System.out.println("Input digits only!");
                scanner.nextLine();
                flag = 1;
            }

        }while(flag == 1);

        System.out.println("Total helmet is: " + motorList.get(index).getHelmetCount());
        System.out.println("Price/helmet is: " + price);
        System.out.println("Total price is: " + motorList.get(index).getHelmetCount() * price);

        System.out.print("Press Enter To Return To Menu...");

        scanner.nextLine();

        showMenu(carList, motorList);

        scanner.close();
    }

    public static void showCar(ArrayList<Car> carList, ArrayList<Motorcycle> motorList, int index){
        System.out.print("\033[H\033[2J");
        System.out.flush();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Brand: " + carList.get(index).getBrand());
        System.out.println("Name: " + carList.get(index).getName());
        System.out.println("License Plate: " + carList.get(index).getLicense());
        System.out.println("Type: " + carList.get(index).getType());
        System.out.println("Gas Capacity: " + carList.get(index).getGasCapacity());
        System.out.println("Top Speed: " + carList.get(index).getTopSpeed());
        System.out.println("Wheel(s): " + carList.get(index).getWheels());
        System.out.println("Entertainment System: " + carList.get(index).getEntertainmentSystem());

        System.out.print("Press Enter To Start " + carList.get(index).getName() + "!");
        scanner.nextLine();

        revEngine();

        carList.get(index).runFeature();

        scanner.nextLine();

        System.out.print("Press Enter To Return To Menu...");

        scanner.nextLine();

        showMenu(carList, motorList);

        scanner.close();
    }

    public static void revEngine(){
        String[] engine = {"1...", "2...", "3..."};

        for(int i = 0; i < engine.length; i++){
            for(int j = 0; j < 4; j++){

                try{
                    Thread.sleep(100);
                    System.out.print(engine[i].charAt(j));
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
            System.out.println();
        }
    }

}
