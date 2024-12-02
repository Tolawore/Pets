import java.util.*;
import java.util.zip.ZipFile;

// Enum for Pet Color
enum Color {
    RED, BLUE, GREEN, YELLOW
}

// Interface for Pet
interface Pet {
    void play();
    void feed();
    void makeSound();
    Attribute getAttribute1();
    Attribute getAttribute2();
    Color getColor();
}

// Interface for Attribute
interface Attribute {
    void incrementValue();
    void decrementValue();
    String getValue(Integer key);
    Integer getCurrentValue();
}

// Concrete implementation of Fish
class Fish implements Pet {
    public Map<Integer, String> hungerLevel;
    public Map<Integer, String> happinessLevel;
    private Color color;
    private HungerAttribute hungerAttribute;
    private HappinessAttribute happinessAttribute;

    public Fish() {
        hungerLevel = new HashMap<>();
        hungerLevel.put(0, "Starving");
        hungerLevel.put(1, "Hungry");
        hungerLevel.put(2, "Full");
        happinessLevel = new HashMap<>();
        happinessLevel.put(0, "Sad");
        happinessLevel.put(1, "Happy");
        happinessLevel.put(2, "Very Happy");
        color = Color.BLUE;
        hungerAttribute = new HungerAttribute(this);
        happinessAttribute = new HappinessAttribute(this);
    }
    @Override
    public void play() {
        System.out.println("The fish is swimming playfully");
        incrementHappiness();
        incrementHunger();
    }

    @Override
    public void feed() {
        System.out.println("The fish is eating");
        decrementHunger();
        incrementHappiness();
    }

    @Override
    public void makeSound() {
        System.out.println("The fish is making a bubbling sound");
    }

    @Override
    public Attribute getAttribute1() {
        return new HungerAttribute(this);
    }

    @Override
    public Attribute getAttribute2() {
        return new HappinessAttribute(this);
    }

    @Override
    public Color getColor() {
        return color;
    }

    private void incrementHappiness() {
        HappinessAttribute happinessAttribute = (HappinessAttribute) getAttribute2();
        happinessAttribute.incrementValue();
    }

    private void decrementHunger() {
        HungerAttribute hungerAttribute = (HungerAttribute) getAttribute1();
        hungerAttribute.decrementValue();
    }

    private void incrementHunger() {
        HungerAttribute hungerAttribute = (HungerAttribute) getAttribute1();
        hungerAttribute.incrementValue();
    }
}

// Concrete implementation of Bird
class Bird implements Pet {
    public Map<Integer, String> energyLevel;
    public Map<Integer, String> cheerfulnessLevel;
    private Color color;
    private EnergyAttribute energyAttribute;
    private CheerfulnessAttribute cheerfulnessAttribute;

    public Bird() {
        energyLevel = new HashMap<>();
        energyLevel.put(0, "Exhausted");
        energyLevel.put(1, "Tired");
        energyLevel.put(2, "Energetic");
        cheerfulnessLevel = new HashMap<>();
        cheerfulnessLevel.put(0, "Gloomy");
        cheerfulnessLevel.put(1, "Cheerful");
        cheerfulnessLevel.put(2, "Ecstatic");
        color = Color.YELLOW;
        energyAttribute = new EnergyAttribute(this);
        cheerfulnessAttribute = new CheerfulnessAttribute(this);
    }
    @Override
    public void play() {
        System.out.println("The bird is flapping its wings playfully");
        incrementCheerfulness();
        decrementEnergy();
    }

    @Override
    public void feed() {
        System.out.println("The bird is eating some seeds");
        incrementEnergy();
        incrementCheerfulness();
    }

    @Override
    public void makeSound() {
        System.out.println("The bird is chirping loudly");
    }

    @Override
    public Attribute getAttribute1() {
        return new EnergyAttribute(this);
    }

    @Override
    public Attribute getAttribute2() {
        return new CheerfulnessAttribute(this);
    }

    @Override
    public Color getColor() {
        return color;
    }

    private void incrementCheerfulness() {
        cheerfulnessAttribute.incrementValue();
    }

    private void decrementEnergy() {
        energyAttribute.decrementValue();
    }

    private void incrementEnergy() {
        energyAttribute.incrementValue();
    }
}

// Concrete implementation of HungerAttribute
class HungerAttribute implements Attribute {
    private Fish fish;
    private int value;

    public HungerAttribute(Fish fish) {
        this.fish = fish;
        value = 1;
    }

    @Override
    public void incrementValue() {
        value++;
        if (value > 2) {
            value = 2;
        }
    }


    @Override
    public void decrementValue() {
        value--;
        if (value < 0) {
            value = 0;
        }
    }

    @Override
    public String getValue(Integer key) {
        return fish.hungerLevel.get(key);
    }

    @Override
    public Integer getCurrentValue() {
        return value;
    }
}

// Concrete implementation of HappinessAttribute
class HappinessAttribute implements Attribute {
    private Fish fish;
    private int value;

    public HappinessAttribute(Fish fish) {
        this.fish = fish;
        value = 1;
    }

    @Override
    public void incrementValue() {
        value++;
        if (value > 2) {
            value = 2;
        }
    }

    @Override
    public void decrementValue() {
        value--;
        if (value < 0) {
            value = 0;
        }
    }

    @Override
    public String getValue(Integer key) {
        return fish.happinessLevel.get(key);
    }

    @Override
    public Integer getCurrentValue() {
        return value;
    }
}

// Concrete implementation of EnergyAttribute
class EnergyAttribute implements Attribute {
    private Bird bird;
    private int value;

    public EnergyAttribute(Bird bird) {
        this.bird = bird;
        value = 1;
    }

    @Override
    public void incrementValue() {
        value++;
        if (value > 2) {
            value = 2;
        }
    }

    @Override
    public void decrementValue() {
        value--;
        if (value < 0) {
            value = 0;
        }
    }

    @Override
    public String getValue(Integer key) {
        return bird.energyLevel.get(key);
    }

    @Override
    public Integer getCurrentValue() {
        return value;
    }
}

// Concrete implementation of CheerfulnessAttribute
class CheerfulnessAttribute implements Attribute {
    private Bird bird;
    private int value;

    public CheerfulnessAttribute(Bird bird) {
        this.bird = bird;
        value = 1;
    }


    @Override
    public void incrementValue() {
        value++;
        if (value > 2) {
            value = 2;
        }
    }

    @Override
    public void decrementValue() {
        value--;
        if (value < 0) {
            value = 0;
        }
    }

    @Override
    public String getValue(Integer key) {
        return bird.cheerfulnessLevel.get(key);
    }

    @Override
    public Integer getCurrentValue() {
        return value;
    }
}

// Owner class
class Owner {
    public List<Pet> pets;

    public Owner() {
        pets = new ArrayList<>();
        pets.add(new Fish());
        pets.add(new Bird());
    }

    public void interact(int petIndex, int choice) {
        Pet pet = (Pet) pets.get(Integer.parseInt(String.valueOf(petIndex)));
        switch (choice) {
            case 1:
                pet.play();
                break;
            case 2:
                pet.feed();
                break;
            case 3:
                pet.makeSound();
                break;
            case 4:
                System.out.println("Pet color: " + pet.getColor());
                break;
            case 5:
                System.out.println("Hunger level: " + pet.getAttribute1().getValue(pet.getAttribute1().getCurrentValue()));
                break;
            case 6:
                System.out.println("Happiness level: " + pet.getAttribute2().getValue(pet.getAttribute2().getCurrentValue()));
                break;
        }
    }
}

// Main class
public class Main {
    public static void main(String[] args) throws InterruptedException {
        Owner owner = new Owner();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Choose a Pet to interact with:");
            for (int i = 0; i < owner.pets.size(); i++) {
                System.out.println((i + 1) + "- " + owner.pets.get(i).getClass().getSimpleName());
            }

            int petIndex = scanner.nextInt() - 1;

            System.out.println("Choose from the following:");
            System.out.println("1- play");
            System.out.println("2- feed");
            System.out.println("3- make sound");
            System.out.println("4- view color");
            System.out.println("5- view hunger value");
            System.out.println("6- view happiness value");

            int choice = scanner.nextInt();
            owner.interact(petIndex, choice);
        }
    }
}
