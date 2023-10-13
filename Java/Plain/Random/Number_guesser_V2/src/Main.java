// Number guesser
// Guess the number war
import java.lang.reflect.Array;
import java.util.*;  // 😭 RIP REAL JAVA CONSOLE
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Main {
    public static double CurrentCount = 0;
    public static boolean DebugMode = true, Repeat = true, TrollMode, WordGame;
    public static int Picked, Max, Roll, Plays, Tries;
    public static String Input = "";

    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String WHITE = "\u001B[0m";

    static String FetchWord(String Length) {
        String Chosen = null;

        try {
            URL url = new URL("https://random-word-api.herokuapp.com/word?length=" + Length);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = reader.readLine()) != null) {
                response.append(inputLine);
            }
            reader.close();
            System.out.println(response.toString() + " afadfgdagadgadgda");
            Chosen = response.toString().replaceAll("[^a-zA-Z]", ""); // Array :( ? Fuck up the array :)

            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Chosen;
    }

    static void NumberGuesserMain(Scanner numberScanner) {
        // Sick gameplay
        Tries = Plays - 1;
        Roll = (int)(Math.random() * (Max + 1));

        if (DebugMode) {
            System.out.println(YELLOW + "Rolled number: " + Roll);
        }

        while (Roll != Picked && 0 <= Tries) {
            System.out.print(WHITE + "Choose a number 0-" + Max + ": ");
            Input = numberScanner.nextLine();
            Input = Input.replaceAll("[^0-9]", "");

            if (Input.isEmpty()) {
                System.out.println(YELLOW + "Enter a number buffoon!");
            } else {
                Picked = Integer.parseInt(Input);
            }

            if (Picked > Max) {
                System.out.println(YELLOW + "The number isn't in range!");
                Input = "";
            } else {
                Tries -= 1;

                if (TrollMode && Roll != Picked) {
                    Shrekify(Plays);
                }
            }

            if (Roll != Picked && !Input.isEmpty()) {
                if (Tries >= 0) {
                    System.out.println(RED + "This ain't it!");
                    System.out.println(RED + "Current try: " + (Plays - Tries));
                    System.out.print(WHITE + "Try again: ");
                }
            }
        }

        //System.out.println("Roll: " + Roll + " Picked: " + Picked);
        if (Roll == Picked) {
           if (true) {
               System.out.println(GREEN + "\n" +
                       "░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░▒\n" +
                       "░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░▒\n" +
                       "░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░▒\n" +
                       "░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░▒\n" +
                       "░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░▒\n" +
                       "░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░▒\n" +
                       "░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░▒▓▓▒▒▒▒▒▒▒░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░▒\n" +
                       "░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░▒▓▒▒▒▒▒▒░░░▒▓░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░▒\n" +
                       "░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░▓▒▒▒▒▒▒▒▒▒▒▒▒▓░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░▒\n" +
                       "░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▒░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░▒\n" +
                       "░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░▓▒▒▒▒▒░░░▒▒▒▒▒▓▒░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░▒\n" +
                       "░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░▒▓▒▒▒▒▒▒▒▒▒▒▒▒▒▓▒░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░▒\n" +
                       "░░░░░░░░░░░░░░░░░░░░░▒▒▒▓▓▓▓▓▓▓▓▓▓▓▒▒▒▒▒▒▒▒▒▒▒▓▓░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░▒\n" +
                       "░░░░░░░░░░░░░░░░░░▒▓▓▓▒▒▒▒▒▒▒▒▒▒▒▒▒▓▓▓▒▒▒▒▒▒▒▓▒░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░▒\n" +
                       "░░░░░░░░░░░░░░░░▒▓▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▓▓▓▓▓▒░░░▒▒▒▒▒▒▒▒▒▒▒▒▒░░░░░░░░░░░░░░░░░░░░░░░░░░░░▒\n" +
                       "░░░░░░░░░░░░░░▒▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▓▒▒▒▓▒▒▒▒▓▓███▓▓▒▒▒▒▓▒▒░░░░░░░░░░░░░░░░░░░░░░░░▒\n" +
                       "░░░░░░░░░░░░░▓▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▓▒▒▒▒▒▓█▓▒▒░▒▒▒▒▒▒▒▒▒▒▓▒▒░░░░░░░░░░░░░░░░░░░░▒\n" +
                       "░░░░░░░░░░░░▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▒░░░░▒░░░░░░░░░░░░▒▒▒▓███▓░░░░░░░░░░░░░░░░░░▒\n" +
                       "░░░░░░░░░░░▓▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒░░░░░░░░░░░░░░░░░░░▒▒▒▒▓█▓▒░░░░░░░░░░░░░░░░▒\n" +
                       "░░░░░░░░░░▒▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▒░░░░▒▒▒▒▒░░░░░░░░░▒▒▒▒▒▓▓▒▓▒░░░░░░░░░░░░░░▒\n" +
                       "░░░░░░░░░░▓▓▒▒▒▒▒▒▒▒▒▒▒▒▒░░░░░░▒▒▒▒▒▒▓▒▒▒▒▒▒▒▓▒░░░▒░░░░░░░░░░░░░░▒▒▒▒▒▒▒▒▒▓▒░░░░░░░░░░░░░▒\n" +
                       "░░░░░░░░░░▓▓▒▒▒▒▒▒▒▒▒▒▒▒░░░░░░░░░▒▒▒▒▒▒▓▒▒▒▒▒▓▒░░▒░░░▒▒░░░░░░░░▒▒▒▒▒▒▒▒▒▒▒▒▓▓░░░░░░░░░░░░▒\n" +
                       "░░░░░░░░░░▓▓▒▒▒▒▒▒▒▒▒▒▒▒▒░░░░░░▒▒▒▒░░▒▒▒▒▓▒▒▒▒░░▒░░▒░▓█▓░░░░░░▒░▒▒▒░░░▒▒▒▒▒▓▓▒░░░░░░░░░░░▒\n" +
                       "░░░░░░░░░░▓▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒░░▒▒▒▒▒▒▒▒░░▒▒▒▓▒▒▒▒▒▒▒▒▓███▓▒░░░░░▒▒▒▓▓▓░░░▒▒▒▒▓▓▓░░░░░░░░░░░▒\n" +
                       "░░░░░░░░░░░▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▒▒▒▒▒▒▒▒▓▓▒▓▓░░░░░░▒▓▓██▓▒░░▒▒▒▒▓▓▓▓░░░░░░░░░░▒\n" +
                       "░░░░░░░░░░░▒▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▒▒▒▒▒▒▒░░▒▒░░░░░░░▒▒▓▓▒▓▓░░░▒▒▒▒▓▓▓▓░░░░░░░░░░▒\n" +
                       "░░░░░░░░░░░░▒▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▒▒▒▒▒▒▒▓▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒░░░░▒▒▒▒▓▓▓▓▓░░░░░░░░░░▒\n" +
                       "░░░░░░░░░░░░░░▓▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▒▒▒▒▓▓▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▓▓▓▓▓▓▓▓░░░░░░░░░░▒\n" +
                       "░░░░░░░░░░░░░░░▒▓▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▓▓▓▓▒▒▒▒▒▒▒▒▒█▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▓▓▓▓░░░░░░░░░░▒\n" +
                       "░░░░░░░░░░░░░░░░░░▒▓▓▒▒▒▒▒▒▒▒▒▒▒▒▒▓▓▓▓▓▓▒▒▒▒▒▒▒▒▒▓█▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▓▓▓▓▒▓▓▓▓▓░░░░░░░░░░▒\n" +
                       "░░░░░░░░░░░░░░░░░░░░░▒▒▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▒▒▒▒▒▒▒▒▒▒██▓░░░░░▒▒▒▒▒▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓░░░░░░░░░░░▒\n" +
                       "░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░▓▓▓▓▓▒▒▒▒▒▒▒▒▒▒▒▒▒▓███▓▒░░░░░░▒██▓▒▓▓▓▓▓▓▓▓▓▓▒░░░░░░░░░░░▒\n" +
                       "░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░▓▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▒▒████▓▓▓███▓▓▓▓▓▓▓▓▓▓▓▓▓░░░░░░░░░░░░▒\n" +
                       "░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▓▓████████▓▓▓▓▓▓▓▓▓▓▓▓▒░░░░░░░░░░░░░▒\n" +
                       "░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓████████▓▓▓▓▓▓▓▓▓▓▓▓▒░░░░░░░░░░░░░░▒\n" +
                       "░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░▒▒▒▒░░░░░▒▒▒▒▒▒▒▒▓▓▓███▓▓▓▓▓▓▓▓▓▓▓▓▒░░░░░░░░░░░░░░░░▒\n" +
                       "░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░▒▒░░░░░░▒▒▒▒▒▒▒▒▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▒░░░░░░░░░░░░░░░░░░▒\n" +
                       "░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░▒▒▒░░▒▒▒▒▒▒▒▒▒▓▒▒▓▓▓▓▓▓▓▓▓▒▒░░░░░░░░░░░░░░░░░░░░▒\n" +
                       "░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░▒▒▒▒▒▒▒▒▒▓▓▓▓▓▓▓▓▓▒▒░░░░░░░░░░░░░░░░░░░░░░░░▒\n" +
                       "░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░▒▒▒▒▒▒▒▒░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░▒\n" +
                       "░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░▒\n" +
                       "░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░▒\n" +
                       "░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░▒\n" +
                       "░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░▒\n" +
                       "░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░▒\n");
           }

            System.out.println(GREEN + "You chose the correct number of: " + Picked);
        } else {
            System.out.println(RED + " _                     \n" +
                    "| |                    \n" +
                    "| | ___  ___  ___ _ __ \n" +
                    "| |/ _ \\/ __|/ _ \\ '__|\n" +
                    "| | (_) \\__ \\  __/ |   \n" +
                    "|_|\\___/|___/\\___|_|   ");
            System.out.println("""
                    ⣿⣿⣿⣿⣿⣿⣿⣿⣿⠿⠛⠋⣉⣉⣉⣉⣉⣉⠙⠛⠿⣿⣿⣿⣿⣿⣿⣿⣿⣿
                    ⣿⣿⣿⣿⣿⡿⠟⢁⣤⣶⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣶⣤⡈⠻⢿⣿⣿⣿⣿⣿
                    ⣿⣿⣿⡿⠋⣠⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣄⠙⢿⣿⣿⣿
                    ⣿⣿⡟⢀⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⡀⢻⣿⣿
                    ⣿⡟⢠⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡄⢻⣿
                    ⣿⢀⣿⣿⣿⠟⠁⣠⣴⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣦⣄⠈⠻⣿⣿⣿⡀⣿
                    ⡇⢸⣿⣿⠋⣠⡾⠿⠛⠛⠛⠿⣿⣿⣿⣿⣿⣿⠿⠛⠛⠛⠻⢷⣄⠙⣿⣿⡇⢸
                    ⡇⢸⣿⣿⣾⣿⢀⣠⣤⣤⣤⣤⣀⣿⣿⣿⣿⣀⣤⣤⣤⣤⣄⡀⣿⣷⣾⣿⡇⢸
                    ⡇⠸⠟⣫⣥⣶⣧⠹⠿⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠿⠏⣼⣶⣬⣍⠻⠇⢸
                    ⡧⣰⣿⣿⣿⣿⣿⢰⣦⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣤⣴⡆⣿⣿⣿⣿⣿⣆⢼
                    ⡇⣿⣿⣿⣿⣿⡟⠈⠙⠛⠻⠿⠿⠿⠿⠿⠿⠿⠿⠟⠛⠋⠁⢻⣿⣿⣿⣿⣿⢸
                    ⣿⣌⡻⠿⠿⢋⣴⣦⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⡀⢀⣴⣦⡙⠿⠿⢟⣡⣾
                    ⣿⣿⣿⣷⣄⠙⢿⣿⣿⣶⣤⣀⡀⡀⡀⡀⡀⡀⣀⣤⣶⣿⣿⡿⠋⣠⣾⣿⣿⣿
                    ⣿⣿⣿⣿⣿⣷⣦⣉⠛⠿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠿⠛⣉⣴⣾⣿⣿⣿⣿⣿
                    ⣿⣿⣿⣿⣿⣿⣿⣿⣿⣶⣤⣌⣉⣉⣉⣉⣉⣉⣡⣤⣶⣿⣿⣿⣿⣿⣿⣿⣿⣿""");
        }
    }

    static void HangManMain(Scanner numberScanner) {
        // Sick gameplay #2
        Tries = Plays;
        String ChosenWord = "";
        String Length = "0";

        while (ChosenWord.isEmpty()) {
            System.out.print(WHITE + "Enter length of word: ");
            Input = "";

            while (Input.isEmpty()) {
                Input = numberScanner.nextLine();
                Input = Input.replaceAll("[^0-9]", "");

                if (Input.isEmpty()) {
                    System.out.println(YELLOW + "Enter a number buffoon!");
                } else {
                    Length = Input;
                    System.out.println(GREEN + "Length of word is: " + Input);
                }
            }
            Input = "";

            ChosenWord = FetchWord(Length);

            if (ChosenWord.isEmpty()) {
                System.out.println(RED + "Couldn't fetch a word with the length of " + Length);
            }
        }

       if (DebugMode) {
           System.out.println(YELLOW + ChosenWord);
       }

        char Guesses[] = new char[ChosenWord.length()];
        List<String> UsedUp = new ArrayList<>();

        for(int i = 0; i < Guesses.length; i++) {
            Guesses[i] =  '_';
        }

        while (0 <= Tries) {
            boolean matched, Success = false;

            do{
                System.out.println(WHITE + "Enter ONE letter from the latin alphabet no [Ö, Ä, Ü]: ");
                matched = false;
                Input = numberScanner.nextLine();
                Input = Input.toLowerCase();
            } while (Input.length() != 1);

            for(int i = 0; i < ChosenWord.length(); i++) {
                String TempSec = Character.toString(ChosenWord.charAt(i));

                if (Input.equals(TempSec)) {
                    Guesses[i] = ChosenWord.charAt(i);
                    Success = true;
                } else {

                    if (UsedUp.contains(Input)) {
                        matched = true;
                    } else {
                        UsedUp.add(Input);
                    }
                }
            }

            if (!Success) {
                Tries -= 1;
                Shrekify(Plays);
            } else {
                System.out.println(GREEN + "The letter fits :D");
            }

            if (matched) {
                System.out.println(YELLOW + "Used up letters:");
                for (int i = 0; i < UsedUp.size(); i++) {
                    System.out.print(WHITE + UsedUp.get(i));
                }

                System.out.println();
            }

            for(int i = 0; i < Guesses.length; i++) {
                System.out.print(WHITE + Guesses[i]);
            }

            System.out.println();
            System.out.println(RED + "Guesses left: " + (Tries));

            // Lazy
            if (!(new String(Guesses).contains("_"))) {
                if (true) {

                    System.out.println(GREEN + "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW\n" +
                            "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW\n" +
                            "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW\n" +
                            "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW\n" +
                            "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW\n" +
                            "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW\n" +
                            "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW\n" +
                            "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW\n" +
                            "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW\n" +
                            "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW\n" +
                            "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW\n" +
                            "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW\n" +
                            "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW\n" +
                            "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW\n" +
                            "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW\n" +
                            "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW\n" +
                            "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW\n" +
                            "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW\n" +
                            "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW\n" +
                            "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWMMMMMMMWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW\n" +
                            "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWNNNNNNNNNNNNXXXXXXXXXXXXXXNNNNNNNNNNNWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW\n" +
                            "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWMMMWWWWWNNNNXXXXKKKKKK0000000000OOOOOOOOO0OOOOOOOOOOOOOOO00OO0000000000KKKKKXXXXNNNNWWWWWWMWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW\n" +
                            "WWWWWWWWWWWWWWWWWWWWMWWWWWWNNXXXKKK00000O000000000KKKKKKKKKKXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXKKKKKKKKK00000000OOOO000KKKXXXNNWWWWWWWWWWWWWWWWWWWWWWWWWWW\n" +
                            "WWWWWWWWWWWWWWWWWWWNNXXKK00OOO0000KKKKKXXXXXXXNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNXXXXXXKKKKKK0000OO000KKXXNNWWWWMWWWWWWWWWWWWWW\n" +
                            "WWWWWWWWWWWWWNXXK000O000KKKKKXXXXNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNXXNNNNNNNNNNXXXXXKKK000OO000KXNNWWWWWWWWWWWWW\n" +
                            "WMWWWWWWNXXK0OO00KKKXXXXXNNXNNXNNNNXXNNNNNNNNNNNNNNNXXNNNNNNNNNNNNNNNNNNNNNNNXXXNNNNNNNNNXXNNNNNNNNNNNNNNNXNNNNNXXNNNNXXNNNNNXXXXXKK000O00KXNNWWWWWWWW\n" +
                            "WWWWWNXK0OO0KKXXXXXXXXXXXNXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXNNXXXXXXXXNXXXNXXXXXXXXXXXXXXXXXXXXNXNXXXXXNNXXXXNXXXXXXXXXNNXXXKK0OO0KXNWWMWW\n" +
                            "WWNXKOO0KKXXXXNXo,'lKNXXXNk;':ONXXXXXXXXXXXXXXNXd,,lKNXXXXXXXXXXXXXXXXXXXNKkl,,,,oKNXNKo,,,,lKNXXXXXXXXXXXXXXN0:,;kNXXXXXXXXXXXXXXXXXXNXXXXXKK0O0KXNWW\n" +
                            "WX0O0KKXXXXXXXNK:  '0NXXXNo  .xNXXXXXK000KXNXXNX:  ;KXKKKXXXXXXKKKXXXXXXXN0d'    .dXXXd.    ,0NXXNXXK00KXXNXXNk.  dXKKKXXXXXXKKKKXXXXXXXXXXXXXXKK0O0XW\n" +
                            "KOOKXXXXXXXXXXXK:  .dOkOkOc  .xNXXkc,.''..;lOXNX:  ;K0:..oKXXXo'.:0NXXXXXN0d'  '. 'ONO' .'  ,0NX0d:'.''.':xKXNk.  dXd'.;ONXNO;.,dXXXXXXXXXXXXXXXXXKOOK\n" +
                            "OOKXXXXXXXXXXXXK:   .......  .xNKl. .lxkxc. .dXX:  ,0Nx. .oXXd. .xXXXXXXXN0d'  ll  c0c  lc  ,0Nk'  ,dkkd'  ;ONk. .oNK:  ;0N0;  :KXXXXXXXXXXXXXXXXXX0OO\n" +
                            "OOKXXXXXXXXXXXXK:  .dOOOOOc  .xNk.  lXXXXK;  ,KX:  ;0NXx. .xk. .dXXXXXXXXN0d'  l0, .,. ;0l  ,0Xc  .ONXXNx. .oNk. .dXX0:  :Oc  :0XXXXXXXXXXXXXXXXXXX0OO\n" +
                            "KOO0KXXXXXXXXXXK:  'OXXXXXo  .xN0:  'd00Oo. .lXX:  ,0XXXd. .. .dXXXXXXXXXN0d'  lXd.   .xXc  ,0Nx.  :k00k;  ,kNk. .dXXX0;  .. ;0XXXXXXXXXXXXXXXXXXXKOOK\n" +
                            "WX0O00KXXXXXXXXKc..;0XXXXXd..'xXX0d;...'...:xKXXl..:KXXXXd.  .oXXXXXXXXXXX0d;..oXKc...lKXo..:0XXkc'..''..,l0XXk,..dXXXX0,   ,OXXXXXXXXXXXXXXXXXKK0O0XW\n" +
                            "WWNX0OO00KKXXXXX0OO0KXXXXXKOOOKXXXXX0OkxkOKXXXXX0OO0XX0OOl. .lKXXXXXXXXXXXXK0OOKXXKOOOKXX0OO0KXXXXKOkkxk0KXXXXKOOOKXKOOx,  ,OXXXXXXXXXXXXXXKK00OO0XNWW\n" +
                            "WWWWWNXK0OO00KKKXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXKd,...,dKXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXO:'..'cOXXXXXXXXXXKKK00OO0KXNWWWWW\n" +
                            "WWWWWWWWNNXK00OO000KKKKXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXKOOO0KXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXK0OO0KXXXXXXKKK0000OO0KXXNWWWWWWWW\n" +
                            "WWWWWWWWWMWWWNXXKK00OOO000KKKKKKXXXXXXXXXXXXXXXXXXKXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXKK0000OO00KKXXNWWWWWWWWWWWWW\n" +
                            "WWWWWWWWWWWWWWWWWWWNNXXKK00OOOOO00000KKKKKKKKKXXXXXKKXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXKKKKKXXXXXXXXXKXXXXK0OO0KXXNNWWWMMWWWWWWWWWWWWWW\n" +
                            "WWWWWWWWWWWWWWWWWWWWWWWWWWWNNXXXKKK0000OOOOO0000000000KKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKK0000000000000KXXXXXKKXXKXK0OOKWWMMWWWWWMWWWWWWWWWWWWWWW\n" +
                            "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWNNNNXXXXXKKKKK00000000OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO000000000KKKKKKK0OOKXXXXXXXXXK0OOXWMWWMWWWWWWWWWWWWWWWWWWWWW\n" +
                            "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWMMWWWWWWWWWWNNNNNNNNNNNNXXXXXXXXXXXXXXXXNNNNNNNNNNWWWWWWWWWWWWNKO0KXKXXKXXK0O0XWWWWWWWWWWWWWWWWWWWWWWWWWWW\n" +
                            "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWXOOKKXXXXK0OOKNWWWWWWWWWWWWWWWWWWWWWWWWWWWW\n" +
                            "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWMWMWKOOKXXXK00O0XWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW\n" +
                            "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWMMWWWWWWWWWWWWN0O0KKK00O0XNWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW\n" +
                            "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWMWWWWNXK0000KXWWWWWWWWWWWWMWWNK00000KXNWWWWWWWKOO000OO0XNWMWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW\n" +
                            "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWXOxolc::;;::codxO000000000kdlc:;;;::codk0NWWXOOOO00XNWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW\n" +
                            "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWOc;;;::cccccc:::cxkOOOOOOOko:::ccccc::;;;:dXX00KKXNWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW\n" +
                            "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWNKxccloddxxkkkkkkkOKKXXXXXXXK0kkkkkkxxxdolcco0XXWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW\n" +
                            "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWNKOkxdxxkkOOOOOOOO0KXXXXXXNNXNXXXKK0OOOOOOOOkxxdxxO0XWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW\n" +
                            "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWNKOxddxxk0KKXXXXXXK0OO0KKXXXXNNXNXXXKOO0KKXXXXXXK0Okxddxk0XWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW\n" +
                            "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWMWWMWWX0xdddxk0KNNNNNNNNNNNNXKOOOKXXXXXXXXX0O0XNNNNNNNNNNNNX0OxxddxOKWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW\n" +
                            "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWMWWWXOxddxxk0XX0OkkkkkkkkO0KNNXK0OKXXXXXXK0OKXNXKOkkkkkkkkO0KXKOxxdddkKWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW\n" +
                            "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWN0xdddxxOK0kdoodxkkkkxxdodx0XNX0OKKKKKK0OXXKkdoodxkkkkxxdoox0XKkxddddOXWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW\n" +
                            "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWXkddddxkKXkoloxxkkkxdddxxxdolxOKX00KKKK0OKXkoloxxxxddxxkkkxdllxKKOxxdddx0WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW\n" +
                            "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWKxdddddx0Xkllodxxo:,''',;;:looccdKKO0K0K00XOllooc;;,'''';ldxdolcdKXkxxddddONWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW\n" +
                            "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW0dddddxxOXXdcloooooc,..;llc'':lcco0KO0000O0Kxclc,.;ll:'.':loooolco0N0xxdddddONWWWWWWWWWWWWWWWWWWWWWWWWWWWWW\n" +
                            "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWKxodddxxxOXNklllooooc,..';;,'':lccdKKO0000O0XOllc,.,;;,'.':oooollcdKN0xxxddddd0WWWWWWWWWWWWWWWWWWWWWWWWWWWWW\n" +
                            "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWNkoddddxxxxOXXklclllc;''..''';:clxOKX0OOOOOOOKXOocc;,'...'',:llllldKXKkxxxddddodKWWWWWWWWWWWWWWWWWWWWWWWWWWWW\n" +
                            "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWKdodddddxxxxO0XKkdlcccc:::::clox0XNX0OOOOOOOO0XXKkdlcc::::cccllox0XKOkxxxxdddddoOWWWWWWWWWWWWWWWWWWWWWWWWWWWW\n" +
                            "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWMWOoddddddxxxxxxk0KK0OkxxxdxxkO0KXXK00OkOOOOOOkkO0XNXKOkxxxdxxkO0KK0OkxxxxxxdddddoxXWWWWWWWWWWWWWWWWWWWWWWWWWWW\n" +
                            "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWMNkoddddddxxxxxxxxkkO00KKKKXXXKK00OkkkkkkkkkkkkkkkO00KKXXXKKKK00OkkkxxxxxxxxdddddoxXWWWWWWWWWWWWWWWWWWWWWWWWWWW\n" +
                            "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWMNkoddddddxxxxxxxxxkkxxkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkxxxxxxxxddddddoxXWWWWWWWWWWWWWWWWWWWWWWWWWWW\n" +
                            "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWMWOooddddddxxxxxxxxxxxxkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkxkkxxxxxxxxdddddddoxNMWWWWWWWWWWWWWWWWWWWWWWWWWW\n" +
                            "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWKdodddddddxxxxxxxxxxxkkkkkkkkkkkkkxdolc::::ccldxkkkkkkkkkkkxxxxxxxxxxxxdddddddooOWMWWWWWWWWWWWWWWWWWWWWWWWWWW\n" +
                            "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWNkoodddddddxxxxxxxxxxxxxkkkkkkxdc;,'..........',:oxkxkkkkxxxxxxxxxxxxxddddddddoxXWWWWWWWWWWWWWWWWWWWWWWWWWWWW\n" +
                            "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWXxodddddddddxxxxxxxxxxxxxkkxd:'..................;oxkxxxxxxxxxxxxxxdddddddddod0WWWWWWWWWWWWWWWWWWWWWWWWWWWWW\n" +
                            "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWKxoodddddddxxxxxxxxxxxxxxxo;.....................'lxxxxxxxxxxxxxxdddddddddod0WWWWWWWWWWWWWWWWWWWWWWWWWWWWWW\n" +
                            "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWXxooddddddddddxxxxxxxxxxx:.......................,dxxxxxxxxxxddddddddddood0WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW\n" +
                            "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWNOdoddddddddddddxxxxxxxd;.......................,oxxxxxxxddddddddddddooxXWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW\n" +
                            "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWKxooodddddddddddxxdxxxc'.''''''''''''''''''''':dxxxxdddddddddddddood0NWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW\n" +
                            "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWN0xoodddddddddddddddxdc,'''''''''''''''''''';oxdddddddddddddddooxOXWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW\n" +
                            "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWNKkdoodddddddddddddddl:,''''''''''''''',;ldddddddddddddddooox0NWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW\n" +
                            "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWX0xdooddddddddddddddolc:;;,,,,,,;;:clddddddddddddddoooxOKWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW\n" +
                            "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWX0kxooooddddddddddddddoollllooddddddddddddddooodkOKNWMWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW\n" +
                            "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWNNNXXKOkxdooooooodddddddddddddddddddddooooooodxxO0XXNNNWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW\n" +
                            "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWNXXXKKK0000OOkkxddddoooooooooooooooooooooodddxxkOO000KKKKXXNNWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW\n" +
                            "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWNNXXXKKK0000OOOOkkkxxxxxxxdddddxxxxxxkkkkOOO00000KKKXXNNNWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW\n" +
                            "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWNNNNNNXXXXXXXKKKKKKKKKKKKKKKKKXXXXXXXNNNNNWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW\n" +
                            "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWMWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW\n" +
                            "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW\n" +
                            "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW\n" +
                            "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW\n" +
                            "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW\n" +
                            "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW\n" +
                            "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW\n" +
                            "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW\n" +
                            "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW\n" +
                            "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW\n" +
                            "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW\n" +
                            "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW\n" +
                            "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW\n" +
                            "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW\n" +
                            "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW\n" +
                            "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW\n" +
                            "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW\n" +
                            "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW\n" +
                            "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW\n" +
                            "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW\n" +
                            "WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW");
                }
                Tries = -1;
                System.out.println(GREEN + "Holey moley you did it.");
            } else if (0 >= Tries) {
                if (true) {
                    System.out.println(RED + "⠀⠀⠀⠀⠀⠀⠀⠀⣠⣤⠴⠶⠖⠒⠒⠛⠛⠛⠛⠛⠓⠒⠒⠒⠒⠒⠒⠒⠒⠒⠲⠶⠶⠶⠦⢤⣄⣀⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                            "⠀⠀⠀⠀⠀⠀⢠⠟⠁⠀⠀⠀⠀⢀⣠⣴⠶⠟⠀⣀⣉⣉⣉⡉⠉⠉⠉⠙⠛⠓⠒⠲⠶⠀⠀⠀⠀⠀⠉⠉⠛⠲⠦⣄⠀⠀⠀⠀⠀\n" +
                            "⠀⠀⠀⠀⠀⢠⡏⠀⠀⠀⢀⣤⡾⢛⣩⠴⠒⢋⣉⣭⣭⡍⠉⠉⠉⠉⠉⠉⠉⠉⠉⠛⠂⠀⠩⣙⣁⠀⠀⠀⠀⠀⠀⠈⢳⡄⠀⠀⠀\n" +
                            "⠀⠀⠀⠀⢠⡞⠀⠀⠀⠀⣘⣡⠖⢋⡤⠶⠚⠉⠀⠀⠀⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠉⠉⠁⠀⠀⠀⠀⠀⠀⣷⠀⠀⠀\n" +
                            "⠀⠀⠀⣠⠋⠀⠀⠀⠀⠀⠟⠁⠀⠈⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢹⡀⠀⠀\n" +
                            "⠀⢀⡾⠁⠀⠀⠀⠀⠀⠀⠀⠀⢠⣶⣶⡶⠶⠿⠿⢿⣿⣿⣿⣶⣤⣀⠀⠀⠀⢀⣀⣀⣤⣶⡾⢿⣿⡿⢿⣶⣦⠀⠀⠀⠀⠈⢷⠀⠀\n" +
                            "⢠⡟⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⠻⠿⢿⣶⣶⣶⣿⣿⣿⣿⡿⠿⠛⠀⠀⠀⠻⢿⣿⣿⣷⣶⣿⣿⣿⣶⠿⠛⠀⠀⠀⠀⠀⠈⢧⠀\n" +
                            "⣾⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠉⠉⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⣿⡟⠀⠈⠉⠉⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⢳\n" +
                            "⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣴⣾⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣷⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸\n" +
                            "⢹⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⣿⣿⡟⠀⠀⢀⡄⠀⢠⣶⣿⠇⠀⠀⠀⠀⠀⠙⢿⣿⣷⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸\n" +
                            "⠈⣷⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣴⣿⡿⠋⠀⠀⣰⠏⠀⠀⢸⣿⣇⣴⣶⣶⠀⠀⠀⠀⣸⣿⣿⣿⠀⠀⠀⠀⠀⣶⣄⠀⠀⠀⠀⢀⡟\n" +
                            "⠀⠈⢷⡀⠀⠀⠀⣠⣴⣶⣾⣿⡿⠛⠀⠀⠀⠀⠁⠀⠀⠀⠈⠛⠟⠉⠛⠉⠀⣾⣿⣿⣿⡿⠛⠁⠀⠀⠀⠀⠀⠘⣿⣷⡄⠀⠀⣼⠀\n" +
                            "⠀⠀⠈⢷⡆⠀⢰⣿⡿⣿⣿⣷⣶⣶⣶⣶⣶⣿⣷⣶⣶⣶⣶⣶⣶⣶⣤⣤⣤⣬⣟⣛⣋⡀⠀⠀⠀⠀⠀⠀⠀⠀⢈⣿⣿⡄⣰⠃⠀\n" +
                            "⠀⠀⠀⠈⢷⡀⠸⣿⣧⣿⡿⠟⠛⠛⠛⠛⠋⠉⠉⠉⠉⠉⠙⠛⠛⠛⠛⠛⠛⠿⠿⣿⣿⣿⣿⣿⣿⣶⣶⣶⣾⣿⣿⠿⣿⣷⡏⠀⠀\n" +
                            "⠀⠀⠀⠀⠘⣧⠀⢻⣿⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠉⠉⠉⠉⠉⠉⠁⠀⠀⠀⠉⡿⠀⠀⠀\n" +
                            "⠀⠀⠀⠀⠀⠘⢧⡀⢿⣿⡆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠐⠒⠶⠤⢤⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢰⠇⠀⠀⠀\n" +
                            "⠀⠀⠀⠀⠀⠀⠈⠻⢦⣈⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠐⠓⠲⠶⠤⢤⣤⣤⣀⣀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⠀⠀⠀⠀\n" +
                            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠛⠶⣄⡀⠀⠀⠀⠀⠀⠀⠀⠀⠓⠶⠤⣤⣀⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⠀⠀⠀⠀\n" +
                            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⠳⢦⣄⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠉⠉⠉⠙⠛⠒⠒⠚⠋⠀⠀⠀⠀⠀⠀⠀⡿⠀⠀⠀⠀\n" +
                            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠉⠓⠲⠤⣤⣀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡼⠃⠀⠀⠀⠀\n" +
                            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠉⠉⠛⠒⠲⠶⠦⠤⠤⢤⣤⣤⣤⣤⣤⣤⠤⠞⠋⠀⠀⠀⠀⠀⠀");
                }
                Tries = -1;
                System.out.println(RED + "You lost ;( the word was: " + ChosenWord);
            }
        }
    }

    static void Shrekify(int Plays) {
        CurrentCount += 1;
        double Percentage = CurrentCount /Plays;
        Percentage = Math.round(Percentage * 100.0)/100.0;

        if (Percentage < .065) {
            System.out.println(GREEN + "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿");
        } else if (Percentage < .067) {
            System.out.println(GREEN +
                            "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\n" +
                            "⣿⠟⠫⢻⣿⣿⣿⣿⢟⣩⡍⣙⠛⢛⣿⣿⣿⠛⠛⠛⠛⠻⣿⣿⣿⣿⣿⡿⢿⣿"
            );
        } else if (Percentage < .067 * 2) {
            System.out.println(GREEN +
                    "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\n" +
                            "⣿⠟⠫⢻⣿⣿⣿⣿⢟⣩⡍⣙⠛⢛⣿⣿⣿⠛⠛⠛⠛⠻⣿⣿⣿⣿⣿⡿⢿⣿\n" +
                            "⣿⠤⠄⠄⠙⢿⣿⣿⣿⡿⠿⠛⠛⢛⣧⣿⠇⠄⠂⠄⠄⠄⠘⣿⣿⣿⣿⠁⠄⢻"
            );
        } else if (Percentage < .067 * 3) {
            System.out.println(GREEN + "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\n" +
                    "⣿⠟⠫⢻⣿⣿⣿⣿⢟⣩⡍⣙⠛⢛⣿⣿⣿⠛⠛⠛⠛⠻⣿⣿⣿⣿⣿⡿⢿⣿\n" +
                    "⣿⠤⠄⠄⠙⢿⣿⣿⣿⡿⠿⠛⠛⢛⣧⣿⠇⠄⠂⠄⠄⠄⠘⣿⣿⣿⣿⠁⠄⢻\n" +
                    "⣿⣿⣿⣿⣶⣄⣾⣿⢟⣼⠒⢲⡔⣺⣿⣧⠄⠄⣠⠤⢤⡀⠄⠟⠉⣠⣤⣤⣤⣾");
        }
        else if (Percentage < .067 * 4) {
            System.out.println(GREEN + "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\n" +
                    "⣿⠟⠫⢻⣿⣿⣿⣿⢟⣩⡍⣙⠛⢛⣿⣿⣿⠛⠛⠛⠛⠻⣿⣿⣿⣿⣿⡿⢿⣿\n" +
                    "⣿⠤⠄⠄⠙⢿⣿⣿⣿⡿⠿⠛⠛⢛⣧⣿⠇⠄⠂⠄⠄⠄⠘⣿⣿⣿⣿⠁⠄⢻\n" +
                    "⣿⣿⣿⣿⣶⣄⣾⣿⢟⣼⠒⢲⡔⣺⣿⣧⠄⠄⣠⠤⢤⡀⠄⠟⠉⣠⣤⣤⣤⣾\n" +
                    "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣟⣀⣬⣵⣿⣿⣿⣶⡤⠙⠄⠘⠃⠄⣴⣾⣿⣿⣿⣿⣿");
        }
        else if (Percentage < .067 * 5) {
            System.out.println(GREEN + "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\n" +
                    "⣿⠟⠫⢻⣿⣿⣿⣿⢟⣩⡍⣙⠛⢛⣿⣿⣿⠛⠛⠛⠛⠻⣿⣿⣿⣿⣿⡿⢿⣿\n" +
                    "⣿⠤⠄⠄⠙⢿⣿⣿⣿⡿⠿⠛⠛⢛⣧⣿⠇⠄⠂⠄⠄⠄⠘⣿⣿⣿⣿⠁⠄⢻\n" +
                    "⣿⣿⣿⣿⣶⣄⣾⣿⢟⣼⠒⢲⡔⣺⣿⣧⠄⠄⣠⠤⢤⡀⠄⠟⠉⣠⣤⣤⣤⣾\n" +
                    "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣟⣀⣬⣵⣿⣿⣿⣶⡤⠙⠄⠘⠃⠄⣴⣾⣿⣿⣿⣿⣿\n" +
                    "⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⢻⠿⢿⣿⣿⠿⠋⠁⠄⠂⠉⠒⢘⣿⣿⣿⣿⣿⣿⣿");
        }
        else if (Percentage < .067 * 6) {
            System.out.println(GREEN + "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\n" +
                    "⣿⠟⠫⢻⣿⣿⣿⣿⢟⣩⡍⣙⠛⢛⣿⣿⣿⠛⠛⠛⠛⠻⣿⣿⣿⣿⣿⡿⢿⣿\n" +
                    "⣿⠤⠄⠄⠙⢿⣿⣿⣿⡿⠿⠛⠛⢛⣧⣿⠇⠄⠂⠄⠄⠄⠘⣿⣿⣿⣿⠁⠄⢻\n" +
                    "⣿⣿⣿⣿⣶⣄⣾⣿⢟⣼⠒⢲⡔⣺⣿⣧⠄⠄⣠⠤⢤⡀⠄⠟⠉⣠⣤⣤⣤⣾\n" +
                    "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣟⣀⣬⣵⣿⣿⣿⣶⡤⠙⠄⠘⠃⠄⣴⣾⣿⣿⣿⣿⣿\n" +
                    "⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⢻⠿⢿⣿⣿⠿⠋⠁⠄⠂⠉⠒⢘⣿⣿⣿⣿⣿⣿⣿\n" +
                    "⣿⣿⣿⣿⣿⣿⣿⣿⡿⣡⣷⣶⣤⣤⣀⡀⠄⠄⠄⠄⠄⠄⠄⣾⣿⣿⣿⣿⣿⣿");
        }
        else if (Percentage < .067 * 7) {
            System.out.println(GREEN + "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\n" +
                    "⣿⠟⠫⢻⣿⣿⣿⣿⢟⣩⡍⣙⠛⢛⣿⣿⣿⠛⠛⠛⠛⠻⣿⣿⣿⣿⣿⡿⢿⣿\n" +
                    "⣿⠤⠄⠄⠙⢿⣿⣿⣿⡿⠿⠛⠛⢛⣧⣿⠇⠄⠂⠄⠄⠄⠘⣿⣿⣿⣿⠁⠄⢻\n" +
                    "⣿⣿⣿⣿⣶⣄⣾⣿⢟⣼⠒⢲⡔⣺⣿⣧⠄⠄⣠⠤⢤⡀⠄⠟⠉⣠⣤⣤⣤⣾\n" +
                    "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣟⣀⣬⣵⣿⣿⣿⣶⡤⠙⠄⠘⠃⠄⣴⣾⣿⣿⣿⣿⣿\n" +
                    "⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⢻⠿⢿⣿⣿⠿⠋⠁⠄⠂⠉⠒⢘⣿⣿⣿⣿⣿⣿⣿\n" +
                    "⣿⣿⣿⣿⣿⣿⣿⣿⡿⣡⣷⣶⣤⣤⣀⡀⠄⠄⠄⠄⠄⠄⠄⣾⣿⣿⣿⣿⣿⣿\n" +
                    "⣿⣿⣿⣿⣿⣿⣿⡿⣸⣿⣿⣿⣿⣿⣿⣿⣷⣦⣰⠄⠄⠄⠄⢾⠿⢿⣿⣿⣿⣿");
        }
        else if (Percentage < .067 * 8) {
            System.out.println(YELLOW + "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\n" +
                    "⣿⠟⠫⢻⣿⣿⣿⣿⢟⣩⡍⣙⠛⢛⣿⣿⣿⠛⠛⠛⠛⠻⣿⣿⣿⣿⣿⡿⢿⣿\n" +
                    "⣿⠤⠄⠄⠙⢿⣿⣿⣿⡿⠿⠛⠛⢛⣧⣿⠇⠄⠂⠄⠄⠄⠘⣿⣿⣿⣿⠁⠄⢻\n" +
                    "⣿⣿⣿⣿⣶⣄⣾⣿⢟⣼⠒⢲⡔⣺⣿⣧⠄⠄⣠⠤⢤⡀⠄⠟⠉⣠⣤⣤⣤⣾\n" +
                    "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣟⣀⣬⣵⣿⣿⣿⣶⡤⠙⠄⠘⠃⠄⣴⣾⣿⣿⣿⣿⣿\n" +
                    "⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⢻⠿⢿⣿⣿⠿⠋⠁⠄⠂⠉⠒⢘⣿⣿⣿⣿⣿⣿⣿\n" +
                    "⣿⣿⣿⣿⣿⣿⣿⣿⡿⣡⣷⣶⣤⣤⣀⡀⠄⠄⠄⠄⠄⠄⠄⣾⣿⣿⣿⣿⣿⣿\n" +
                    "⣿⣿⣿⣿⣿⣿⣿⡿⣸⣿⣿⣿⣿⣿⣿⣿⣷⣦⣰⠄⠄⠄⠄⢾⠿⢿⣿⣿⣿⣿\n" +
                    "⣿⡿⠋⣡⣾⣿⣿⣿⡟⠉⠉⠈⠉⠉⠉⠉⠉⠄⠄⠄⠑⠄⠄⠐⡇⠄⠈⠙⠛⠋");
        }
        else if (Percentage < .067 * 9) {
            System.out.println(YELLOW + "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\n" +
                    "⣿⠟⠫⢻⣿⣿⣿⣿⢟⣩⡍⣙⠛⢛⣿⣿⣿⠛⠛⠛⠛⠻⣿⣿⣿⣿⣿⡿⢿⣿\n" +
                    "⣿⠤⠄⠄⠙⢿⣿⣿⣿⡿⠿⠛⠛⢛⣧⣿⠇⠄⠂⠄⠄⠄⠘⣿⣿⣿⣿⠁⠄⢻\n" +
                    "⣿⣿⣿⣿⣶⣄⣾⣿⢟⣼⠒⢲⡔⣺⣿⣧⠄⠄⣠⠤⢤⡀⠄⠟⠉⣠⣤⣤⣤⣾\n" +
                    "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣟⣀⣬⣵⣿⣿⣿⣶⡤⠙⠄⠘⠃⠄⣴⣾⣿⣿⣿⣿⣿\n" +
                    "⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⢻⠿⢿⣿⣿⠿⠋⠁⠄⠂⠉⠒⢘⣿⣿⣿⣿⣿⣿⣿\n" +
                    "⣿⣿⣿⣿⣿⣿⣿⣿⡿⣡⣷⣶⣤⣤⣀⡀⠄⠄⠄⠄⠄⠄⠄⣾⣿⣿⣿⣿⣿⣿\n" +
                    "⣿⣿⣿⣿⣿⣿⣿⡿⣸⣿⣿⣿⣿⣿⣿⣿⣷⣦⣰⠄⠄⠄⠄⢾⠿⢿⣿⣿⣿⣿\n" +
                    "⣿⡿⠋⣡⣾⣿⣿⣿⡟⠉⠉⠈⠉⠉⠉⠉⠉⠄⠄⠄⠑⠄⠄⠐⡇⠄⠈⠙⠛⠋\n" +
                    "⠋⠄⣾⣿⣿⣿⣿⡿⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⢠⡇⠄⠄⠄⠄⠄");
        }
        else if (Percentage < .067 * 10) {
            System.out.println(YELLOW + "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\n" +
                    "⣿⠟⠫⢻⣿⣿⣿⣿⢟⣩⡍⣙⠛⢛⣿⣿⣿⠛⠛⠛⠛⠻⣿⣿⣿⣿⣿⡿⢿⣿\n" +
                    "⣿⠤⠄⠄⠙⢿⣿⣿⣿⡿⠿⠛⠛⢛⣧⣿⠇⠄⠂⠄⠄⠄⠘⣿⣿⣿⣿⠁⠄⢻\n" +
                    "⣿⣿⣿⣿⣶⣄⣾⣿⢟⣼⠒⢲⡔⣺⣿⣧⠄⠄⣠⠤⢤⡀⠄⠟⠉⣠⣤⣤⣤⣾\n" +
                    "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣟⣀⣬⣵⣿⣿⣿⣶⡤⠙⠄⠘⠃⠄⣴⣾⣿⣿⣿⣿⣿\n" +
                    "⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⢻⠿⢿⣿⣿⠿⠋⠁⠄⠂⠉⠒⢘⣿⣿⣿⣿⣿⣿⣿\n" +
                    "⣿⣿⣿⣿⣿⣿⣿⣿⡿⣡⣷⣶⣤⣤⣀⡀⠄⠄⠄⠄⠄⠄⠄⣾⣿⣿⣿⣿⣿⣿\n" +
                    "⣿⣿⣿⣿⣿⣿⣿⡿⣸⣿⣿⣿⣿⣿⣿⣿⣷⣦⣰⠄⠄⠄⠄⢾⠿⢿⣿⣿⣿⣿\n" +
                    "⣿⡿⠋⣡⣾⣿⣿⣿⡟⠉⠉⠈⠉⠉⠉⠉⠉⠄⠄⠄⠑⠄⠄⠐⡇⠄⠈⠙⠛⠋\n" +
                    "⠋⠄⣾⣿⣿⣿⣿⡿⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⢠⡇⠄⠄⠄⠄⠄\n" +
                    "⠄⢸⣿⣿⣿⣿⣿⣯⠄⢠⡀⠄⠄⠄⠄⠄⠄⠄⠄⣀⠄⠄⠄⠄⠁⠄⠄⠄⠄⠄");
        }
        else if (Percentage < .067 * 11) {
            System.out.println(YELLOW + "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\n" +
                    "⣿⠟⠫⢻⣿⣿⣿⣿⢟⣩⡍⣙⠛⢛⣿⣿⣿⠛⠛⠛⠛⠻⣿⣿⣿⣿⣿⡿⢿⣿\n" +
                    "⣿⠤⠄⠄⠙⢿⣿⣿⣿⡿⠿⠛⠛⢛⣧⣿⠇⠄⠂⠄⠄⠄⠘⣿⣿⣿⣿⠁⠄⢻\n" +
                    "⣿⣿⣿⣿⣶⣄⣾⣿⢟⣼⠒⢲⡔⣺⣿⣧⠄⠄⣠⠤⢤⡀⠄⠟⠉⣠⣤⣤⣤⣾\n" +
                    "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣟⣀⣬⣵⣿⣿⣿⣶⡤⠙⠄⠘⠃⠄⣴⣾⣿⣿⣿⣿⣿\n" +
                    "⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⢻⠿⢿⣿⣿⠿⠋⠁⠄⠂⠉⠒⢘⣿⣿⣿⣿⣿⣿⣿\n" +
                    "⣿⣿⣿⣿⣿⣿⣿⣿⡿⣡⣷⣶⣤⣤⣀⡀⠄⠄⠄⠄⠄⠄⠄⣾⣿⣿⣿⣿⣿⣿\n" +
                    "⣿⣿⣿⣿⣿⣿⣿⡿⣸⣿⣿⣿⣿⣿⣿⣿⣷⣦⣰⠄⠄⠄⠄⢾⠿⢿⣿⣿⣿⣿\n" +
                    "⣿⡿⠋⣡⣾⣿⣿⣿⡟⠉⠉⠈⠉⠉⠉⠉⠉⠄⠄⠄⠑⠄⠄⠐⡇⠄⠈⠙⠛⠋\n" +
                    "⠋⠄⣾⣿⣿⣿⣿⡿⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⢠⡇⠄⠄⠄⠄⠄\n" +
                    "⠄⢸⣿⣿⣿⣿⣿⣯⠄⢠⡀⠄⠄⠄⠄⠄⠄⠄⠄⣀⠄⠄⠄⠄⠁⠄⠄⠄⠄⠄\n" +
                    "⠁⢸⣿⣿⣿⣿⣿⣯⣧⣬⣿⣤⣐⣂⣄⣀⣠⡴⠖⠈⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄");
        }
        else if (Percentage < .067 * 12) {
            System.out.println(YELLOW + "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\n" +
                    "⣿⠟⠫⢻⣿⣿⣿⣿⢟⣩⡍⣙⠛⢛⣿⣿⣿⠛⠛⠛⠛⠻⣿⣿⣿⣿⣿⡿⢿⣿\n" +
                    "⣿⠤⠄⠄⠙⢿⣿⣿⣿⡿⠿⠛⠛⢛⣧⣿⠇⠄⠂⠄⠄⠄⠘⣿⣿⣿⣿⠁⠄⢻\n" +
                    "⣿⣿⣿⣿⣶⣄⣾⣿⢟⣼⠒⢲⡔⣺⣿⣧⠄⠄⣠⠤⢤⡀⠄⠟⠉⣠⣤⣤⣤⣾\n" +
                    "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣟⣀⣬⣵⣿⣿⣿⣶⡤⠙⠄⠘⠃⠄⣴⣾⣿⣿⣿⣿⣿\n" +
                    "⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⢻⠿⢿⣿⣿⠿⠋⠁⠄⠂⠉⠒⢘⣿⣿⣿⣿⣿⣿⣿\n" +
                    "⣿⣿⣿⣿⣿⣿⣿⣿⡿⣡⣷⣶⣤⣤⣀⡀⠄⠄⠄⠄⠄⠄⠄⣾⣿⣿⣿⣿⣿⣿\n" +
                    "⣿⣿⣿⣿⣿⣿⣿⡿⣸⣿⣿⣿⣿⣿⣿⣿⣷⣦⣰⠄⠄⠄⠄⢾⠿⢿⣿⣿⣿⣿\n" +
                    "⣿⡿⠋⣡⣾⣿⣿⣿⡟⠉⠉⠈⠉⠉⠉⠉⠉⠄⠄⠄⠑⠄⠄⠐⡇⠄⠈⠙⠛⠋\n" +
                    "⠋⠄⣾⣿⣿⣿⣿⡿⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⢠⡇⠄⠄⠄⠄⠄\n" +
                    "⠄⢸⣿⣿⣿⣿⣿⣯⠄⢠⡀⠄⠄⠄⠄⠄⠄⠄⠄⣀⠄⠄⠄⠄⠁⠄⠄⠄⠄⠄\n" +
                    "⠁⢸⣿⣿⣿⣿⣿⣯⣧⣬⣿⣤⣐⣂⣄⣀⣠⡴⠖⠈⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄\n" +
                    "⠈⠈⣿⣟⣿⣿⣿⣿⣿⣿⣿⣿⣽⣉⡉⠉⠈⠁⠄⠁⠄⠄⠄⠄⡂⠄⠄⠄⠄⠄");
        }
        else if (Percentage < .067 * 13) {
            System.out.println(YELLOW + "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\n" +
                    "⣿⠟⠫⢻⣿⣿⣿⣿⢟⣩⡍⣙⠛⢛⣿⣿⣿⠛⠛⠛⠛⠻⣿⣿⣿⣿⣿⡿⢿⣿\n" +
                    "⣿⠤⠄⠄⠙⢿⣿⣿⣿⡿⠿⠛⠛⢛⣧⣿⠇⠄⠂⠄⠄⠄⠘⣿⣿⣿⣿⠁⠄⢻\n" +
                    "⣿⣿⣿⣿⣶⣄⣾⣿⢟⣼⠒⢲⡔⣺⣿⣧⠄⠄⣠⠤⢤⡀⠄⠟⠉⣠⣤⣤⣤⣾\n" +
                    "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣟⣀⣬⣵⣿⣿⣿⣶⡤⠙⠄⠘⠃⠄⣴⣾⣿⣿⣿⣿⣿\n" +
                    "⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⢻⠿⢿⣿⣿⠿⠋⠁⠄⠂⠉⠒⢘⣿⣿⣿⣿⣿⣿⣿\n" +
                    "⣿⣿⣿⣿⣿⣿⣿⣿⡿⣡⣷⣶⣤⣤⣀⡀⠄⠄⠄⠄⠄⠄⠄⣾⣿⣿⣿⣿⣿⣿\n" +
                    "⣿⣿⣿⣿⣿⣿⣿⡿⣸⣿⣿⣿⣿⣿⣿⣿⣷⣦⣰⠄⠄⠄⠄⢾⠿⢿⣿⣿⣿⣿\n" +
                    "⣿⡿⠋⣡⣾⣿⣿⣿⡟⠉⠉⠈⠉⠉⠉⠉⠉⠄⠄⠄⠑⠄⠄⠐⡇⠄⠈⠙⠛⠋\n" +
                    "⠋⠄⣾⣿⣿⣿⣿⡿⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⢠⡇⠄⠄⠄⠄⠄\n" +
                    "⠄⢸⣿⣿⣿⣿⣿⣯⠄⢠⡀⠄⠄⠄⠄⠄⠄⠄⠄⣀⠄⠄⠄⠄⠁⠄⠄⠄⠄⠄\n" +
                    "⠁⢸⣿⣿⣿⣿⣿⣯⣧⣬⣿⣤⣐⣂⣄⣀⣠⡴⠖⠈⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄\n" +
                    "⠈⠈⣿⣟⣿⣿⣿⣿⣿⣿⣿⣿⣽⣉⡉⠉⠈⠁⠄⠁⠄⠄⠄⠄⡂⠄⠄⠄⠄⠄\n" +
                    "⠄⠄⠙⣿⣿⠿⣿⣿⣿⣿⣷⡤⠈⠉⠉⠁⠄⠄⠄⠄⠄⠄⠄⠠⠔⠄⠄⠄⠄⠄");
        }
        else if (Percentage < .067 * 14) {
            System.out.println(YELLOW + "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\n" +
                    "⣿⠟⠫⢻⣿⣿⣿⣿⢟⣩⡍⣙⠛⢛⣿⣿⣿⠛⠛⠛⠛⠻⣿⣿⣿⣿⣿⡿⢿⣿\n" +
                    "⣿⠤⠄⠄⠙⢿⣿⣿⣿⡿⠿⠛⠛⢛⣧⣿⠇⠄⠂⠄⠄⠄⠘⣿⣿⣿⣿⠁⠄⢻\n" +
                    "⣿⣿⣿⣿⣶⣄⣾⣿⢟⣼⠒⢲⡔⣺⣿⣧⠄⠄⣠⠤⢤⡀⠄⠟⠉⣠⣤⣤⣤⣾\n" +
                    "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣟⣀⣬⣵⣿⣿⣿⣶⡤⠙⠄⠘⠃⠄⣴⣾⣿⣿⣿⣿⣿\n" +
                    "⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⢻⠿⢿⣿⣿⠿⠋⠁⠄⠂⠉⠒⢘⣿⣿⣿⣿⣿⣿⣿\n" +
                    "⣿⣿⣿⣿⣿⣿⣿⣿⡿⣡⣷⣶⣤⣤⣀⡀⠄⠄⠄⠄⠄⠄⠄⣾⣿⣿⣿⣿⣿⣿\n" +
                    "⣿⣿⣿⣿⣿⣿⣿⡿⣸⣿⣿⣿⣿⣿⣿⣿⣷⣦⣰⠄⠄⠄⠄⢾⠿⢿⣿⣿⣿⣿\n" +
                    "⣿⡿⠋⣡⣾⣿⣿⣿⡟⠉⠉⠈⠉⠉⠉⠉⠉⠄⠄⠄⠑⠄⠄⠐⡇⠄⠈⠙⠛⠋\n" +
                    "⠋⠄⣾⣿⣿⣿⣿⡿⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⢠⡇⠄⠄⠄⠄⠄\n" +
                    "⠄⢸⣿⣿⣿⣿⣿⣯⠄⢠⡀⠄⠄⠄⠄⠄⠄⠄⠄⣀⠄⠄⠄⠄⠁⠄⠄⠄⠄⠄\n" +
                    "⠁⢸⣿⣿⣿⣿⣿⣯⣧⣬⣿⣤⣐⣂⣄⣀⣠⡴⠖⠈⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄\n" +
                    "⠈⠈⣿⣟⣿⣿⣿⣿⣿⣿⣿⣿⣽⣉⡉⠉⠈⠁⠄⠁⠄⠄⠄⠄⡂⠄⠄⠄⠄⠄\n" +
                    "⠄⠄⠙⣿⣿⠿⣿⣿⣿⣿⣷⡤⠈⠉⠉⠁⠄⠄⠄⠄⠄⠄⠄⠠⠔⠄⠄⠄⠄⠄\n" +
                    "⠄⠄⠄⡈⢿⣷⣿⣿⢿⣿⣿⣷⡦⢤⡀⠄⠄⠄⠄⠄⠄⢐⣠⡿⠁⠄⠄⠄⠄⠄");
        }
        else if (Percentage >= 1) {
            System.out.println(RED + "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\n" +
                    "⣿⠟⠫⢻⣿⣿⣿⣿⢟⣩⡍⣙⠛⢛⣿⣿⣿⠛⠛⠛⠛⠻⣿⣿⣿⣿⣿⡿⢿⣿\n" +
                    "⣿⠤⠄⠄⠙⢿⣿⣿⣿⡿⠿⠛⠛⢛⣧⣿⠇⠄⠂⠄⠄⠄⠘⣿⣿⣿⣿⠁⠄⢻\n" +
                    "⣿⣿⣿⣿⣶⣄⣾⣿⢟⣼⠒⢲⡔⣺⣿⣧⠄⠄⣠⠤⢤⡀⠄⠟⠉⣠⣤⣤⣤⣾\n" +
                    "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣟⣀⣬⣵⣿⣿⣿⣶⡤⠙⠄⠘⠃⠄⣴⣾⣿⣿⣿⣿⣿\n" +
                    "⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⢻⠿⢿⣿⣿⠿⠋⠁⠄⠂⠉⠒⢘⣿⣿⣿⣿⣿⣿⣿\n" +
                    "⣿⣿⣿⣿⣿⣿⣿⣿⡿⣡⣷⣶⣤⣤⣀⡀⠄⠄⠄⠄⠄⠄⠄⣾⣿⣿⣿⣿⣿⣿\n" +
                    "⣿⣿⣿⣿⣿⣿⣿⡿⣸⣿⣿⣿⣿⣿⣿⣿⣷⣦⣰⠄⠄⠄⠄⢾⠿⢿⣿⣿⣿⣿\n" +
                    "⣿⡿⠋⣡⣾⣿⣿⣿⡟⠉⠉⠈⠉⠉⠉⠉⠉⠄⠄⠄⠑⠄⠄⠐⡇⠄⠈⠙⠛⠋\n" +
                    "⠋⠄⣾⣿⣿⣿⣿⡿⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⢠⡇⠄⠄⠄⠄⠄\n" +
                    "⠄⢸⣿⣿⣿⣿⣿⣯⠄⢠⡀⠄⠄⠄⠄⠄⠄⠄⠄⣀⠄⠄⠄⠄⠁⠄⠄⠄⠄⠄\n" +
                    "⠁⢸⣿⣿⣿⣿⣿⣯⣧⣬⣿⣤⣐⣂⣄⣀⣠⡴⠖⠈⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄\n" +
                    "⠈⠈⣿⣟⣿⣿⣿⣿⣿⣿⣿⣿⣽⣉⡉⠉⠈⠁⠄⠁⠄⠄⠄⠄⡂⠄⠄⠄⠄⠄\n" +
                    "⠄⠄⠙⣿⣿⠿⣿⣿⣿⣿⣷⡤⠈⠉⠉⠁⠄⠄⠄⠄⠄⠄⠄⠠⠔⠄⠄⠄⠄⠄\n" +
                    "⠄⠄⠄⡈⢿⣷⣿⣿⢿⣿⣿⣷⡦⢤⡀⠄⠄⠄⠄⠄⠄⢐⣠⡿⠁⠄⠄⠄⠄⠄");
        }

        System.out.println();
    }

    public static void main(String[] args) {
        Picked = Max = Roll = Plays = -1; // If it were zero you would get a free win.
        Tries = 0;
        Input = "";
        Scanner numberScanner = new Scanner(System.in);
        Scanner PlayLoopScanner = new Scanner(System.in);

        while (Repeat) {

            System.out.print(WHITE + "Type Yes for fun mode, \";)\" : "); // Are we shreking?
            Input = numberScanner.nextLine();

            TrollMode = false;
            if (Input.equalsIgnoreCase("yes")) {
                TrollMode = true;
                System.out.println(RED +
                        "  ___   _  _   ___   ___   _  __  __  __    ___    ___    ___      ___    _  _ \n" +
                        " / __| | || | | _ \\ | __| | |/ / |  \\/  |  / _ \\  |   \\  | __|    / _ \\  | \\| |\n" +
                        " \\__ \\ | __ | |   / | _|  | ' <  | |\\/| | | (_) | | |) | | _|    | (_) | | .` |\n" +
                        " |___/ |_||_| |_|_\\ |___| |_|\\_\\ |_|  |_|  \\___/  |___/  |___|    \\___/  |_|\\_|\n");
            }
            Input = "";

            System.out.print(WHITE + "Type Yes for hangman: ");
            Input = numberScanner.nextLine();

            if (!Input.equalsIgnoreCase("yes")) {
                WordGame = false;
                Input = "";
                System.out.println(GREEN + "Game mode: Number guesser");

                System.out.print("Choose the highest number: ");
                while (Max == -1 && Input.isEmpty()) {
                    Input = numberScanner.nextLine();
                    Input = Input.replaceAll("[^0-9]", "");

                    if (Input.isEmpty()) {
                        System.out.println(YELLOW + "Enter a number buffoon!");
                    } else {
                        Max = Integer.parseInt(Input);
                        System.out.println(GREEN + "Picked highest number of: " + Input);
                    }
                }
            } else {
                WordGame = true;
                System.out.println(GREEN + "Game mode: Hangman");
            }

            Input = "";

            System.out.print(WHITE + "Enter amount of tries: ");
            while (Plays == -1 && Input.isEmpty()) {
                Input = numberScanner.nextLine();
                Input = Input.replaceAll("[^0-9]", "");

                if (Input.isEmpty()) {
                    System.out.println(YELLOW + "Enter a number buffoon!");
                } else {
                    Plays = Integer.parseInt(Input);
                    System.out.println(GREEN + "Total amount of tries: " + Input);
                    Input = "";
                }
            }

            if (!WordGame) {
                NumberGuesserMain(PlayLoopScanner);
            } else {
                HangManMain(PlayLoopScanner);
            }

            System.out.print(WHITE + "Type Yes to play again: ");
            Input = numberScanner.nextLine();

            if (!Input.equalsIgnoreCase("yes")) {
                Repeat = false;
                System.out.println(RED + "It's Joever, your'e bidone :(");
            } else {
                // Cleanup
                for (int i = 0; i < 50; i++) {
                    System.out.println();
                }
                Picked = Max = Roll = Plays = -1;
                Input = "";
                CurrentCount = 0;
            }
        }
    }
}