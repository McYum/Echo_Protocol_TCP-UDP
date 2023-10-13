package classes;
import java.lang.reflect.Field;

public class Worker implements Comparable<Worker>{
    private String name;
    private int id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Worker(String Name, int ID) {
        this.name = Name;
        this.id = ID;
    }

    /*
    public void set(String ToReplace, Object Replacement) throws NoSuchFieldException, IllegalAccessException {
        if (!ToReplace.equals("name") && !ToReplace.equals("id")) {
            return;
        }

        Field field = getClass().getDeclaredField(ToReplace);
        field.setAccessible(true);

        // why doesn't getType() and getClass() spit out the same shit >:(
        Class<?> fieldType = field.getType();

        if (fieldType.isPrimitive()) {
            if (fieldType == int.class) {
                if (Replacement.getClass() == Integer.class) {
                    field.setInt(this, (int) Replacement);
                } else {
                    System.out.println("Input type invalid!");
                }
            } else if (fieldType == double.class) {
                if (Replacement.getClass() == Double.class) {
                    field.setDouble(this, (double) Replacement);
                } else {
                    System.out.println("Input type invalid!");
                }
            }
            else {
                System.out.println("Oh jard ;(");
            }
        } else if (fieldType.isAssignableFrom(Replacement.getClass())) {
            field.set(this, Replacement);
        } else {
            System.out.println("Input type invalid!");
        }
    }


    public Object get(String attributeName) throws NoSuchFieldException, IllegalAccessException {
        if (!attributeName.equals("name") && !attributeName.equals("id")) {
            return "attribute N/A";
        }

        Field field = getClass().getDeclaredField(attributeName);
        field.setAccessible(true);
        return field.get(this);
    }
    */

    public String toString() {
        String Temp;
        Temp = "Name: " + this.name + " Id: " + this.id;

        return Temp;
    }

    public int compareTo(Worker Worker) {
        return this.name.compareTo(Worker.name);
    }

    public boolean IsAlphabeticallyHigher(Worker Comparison) {
        char firstChar1 = Character.toLowerCase(this.name.charAt(0));
        char firstChar2 = Character.toLowerCase(Comparison.name.charAt(0));

        return firstChar1 < firstChar2;
    }
}
