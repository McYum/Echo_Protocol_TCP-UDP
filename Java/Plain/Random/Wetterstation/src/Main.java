import java.util.*;
import java.lang.Math;
import java.util.Dictionary;
import java.util.Hashtable;

public class Main {

    static double ReturnMedian(List<Integer> Days, int[] Temperatures) {
        double Median = 0;

        for (int i = 0; i < Days.toArray().length; i++) {
            Median += Temperatures[i];
        }

        Median = Median / Temperatures.length;

        return Median;
    }

    static Dictionary GetMinMax(int[] Temperatures) {
        Dictionary MinMax = new Hashtable();

        int StoredValue = 0;
        boolean Maxxed = false;

        for (int i = 1; i < Temperatures.length; i++) {
            if (!Maxxed) {
                if (Temperatures[i] > StoredValue) {
                    StoredValue = Temperatures[i];
                }

                if (i == Temperatures.length - 1) {
                    i = 0;
                    MinMax.put("Max ", " " + StoredValue);
                    Maxxed = true;
                    StoredValue = 420691337;
                }
            } else {
                if (Temperatures[i] < StoredValue) {
                    StoredValue = Temperatures[i];
                }

                if (i == Temperatures.length - 1) {
                    MinMax.put("Min ", " " + StoredValue);
                }
            }
        }

        return MinMax;
    }

    static Dictionary HighDiff(List<Integer> Days, int[] Temperatures) {
        Dictionary HigDiff = new Hashtable();
        int Heatpoint = 0, StoredVal = 0;

        for (int i = 0; i < Days.toArray().length; i++) {
            int TempVal, EndNumber = 1;

            if (i == Days.toArray().length - 1) {
                EndNumber = 0;
            }
            TempVal = Math.abs(Temperatures[i] - Temperatures[i + EndNumber]);

            if (TempVal > StoredVal) {
                StoredVal = TempVal;
                Heatpoint = i;
            }
        }

        HigDiff.put("Day 1 ", " " + Days.get(Heatpoint));
        HigDiff.put("Day 2 ", " " + Days.get(Heatpoint + 1));

        return HigDiff;
    }

    static LinkedHashMap FancyTable(List<Integer> Days, int[] Temperatures) {
        LinkedHashMap<String, String> FancyTable = new LinkedHashMap<>(); // Bloody L just spit out the normal Hashmap sorted

        for (int i = 0; i < Days.size(); i++) {
            FancyTable.put("Tag " + Days.get(i) + " ", " " + Temperatures[i]);
        }

        return FancyTable;
    }

    public static void main(String[] args) {
        List<Integer> Days = new ArrayList<>();
        int[] Temperatures = new int[]{12, 14, 9, 12, 15, 16, 15, 15, 11, 8, 13, 13, 15, 12};

        for (int i = 1; i <= 14; i++) {
            Days.add(i);
        }

        System.out.println("Median: " + (Math.round(ReturnMedian(Days, Temperatures) * 100.) / 100.)) ;
        System.out.println(GetMinMax(Temperatures));
        System.out.println(HighDiff(Days, Temperatures));
        System.out.println(FancyTable(Days, Temperatures));
    }
}