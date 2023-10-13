import java.util.*;
import java.lang.Math;
import java.util.concurrent.TimeUnit;

public class Main {
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String WHITE = "\u001B[0m";

    public static class Base {
        final String name;
        HashMap<String, Object> Data;

        public Base(HashMap<String, Object> Data) {
            this.Data = Data;
            this.name = String.valueOf(Data.get("Name"));
        }

        public HashMap<String, Object> ReturnDataAsString() {

            return this.Data;
        }
    }

    public static class RacingSnail extends Base {
        final String race, color;
        final int speed;
        private int coveredDistance;

        public RacingSnail(HashMap<String, Object> Data) {
            super(Data);
            this.race = String.valueOf(Data.get("Race"));
            this.speed = (int) Data.get("MaxSpeed");
            this.color = String.valueOf(Data.get("Color"));

            this.coveredDistance = (int) Data.get("DistanceTraveled");
        }

        public void move() {
            int movedDistance;

            movedDistance = (int) (Math.random() * this.speed);
            this.coveredDistance = this.coveredDistance + movedDistance;

            System.out.println(this.color + this.name + " Moved: " + this.coveredDistance);
        }
    }

    public static class Race extends Base{
        private int participantsCount, length;
        private ArrayList<RacingSnail> participants = new ArrayList<>();

        public Race(HashMap<String, Object> Data) {
            super(Data);
            this.length = (int) Data.get("Length");
            this.participantsCount = 0;
        }
        
        public void updateRaceInfo() {
            this.participantsCount = 0;

            for (int i = 0; i < participants.size(); i++) {
                this.participantsCount =+ 1;
            }

            System.out.println(GREEN + "ParticipantCount: " + this.participantsCount);
        }

        public void AddSnail(RacingSnail snailToAdd) {
            this.participants.add(snailToAdd);

            this.updateRaceInfo();
            System.out.println(YELLOW + "Added snail: " + snailToAdd.name);
        }

        public void KillSnail(RacingSnail snailToKill) {
            this.participants.remove(snailToKill);

            this.updateRaceInfo();
            System.out.println(RED + "MURDERED snail: " + snailToKill.name);
        }

        public RacingSnail GetWinner() {
            RacingSnail Stored, CurrentRun, Winner = null;

            if (this.participantsCount > 0) {
                Stored = this.participants.get(0);

                for (RacingSnail participant : this.participants) {
                    if (participant.coveredDistance >= Stored.coveredDistance && Winner == null) {
                        CurrentRun = participant;

                        if (CurrentRun.coveredDistance >= this.length) {
                            Winner = CurrentRun;
                        }
                    }
                }
            }

            return Winner;
        }

        public void CycleOnce() {
            if (this.participantsCount > 0) {
                for (int i = 0; i < this.participants.size(); i++) {
                    this.participants.get(i).move();
                }
            }
        }

        public void StartRace() throws InterruptedException {
            if (this.participantsCount > 0) {
                while (this.GetWinner() == null) {
                    CycleOnce();
                    TimeUnit.MILLISECONDS.sleep(100);
                }

                System.out.println(GREEN + this.GetWinner().name + " won the race and beat the case!");
            }
        }
    }

    public static class Casino extends Base {
        HashMap<RacingSnail, Bet> Bets = new HashMap<>();
        final int factor;
        private Race race;

        public Casino(HashMap<String, Object> Data) {
            super(Data);
            this.factor = (int) Data.get("Factor");
            this.race = (Main.Race) Data.get("Race");
        }

        public void AddBet(HashMap<String, Object> Data) {
            Bet BetToAdd = new Bet(Data);

            if (Bets.get((RacingSnail) Data.get("Snail")) == null) {
                Bets.put((RacingSnail) Data.get("Snail"), BetToAdd);
            } else {
                System.out.println(RED + "DUPLICATE ENTRY FOUND!");
            }
        }

        public HashMap<Object, Object> ReturnAllInfo() {
            HashMap<Object, Object> TempMap = new HashMap<>();
            TempMap.putAll(this.Data);
            TempMap.putAll(this.race.Data);

            return TempMap;
        }

        public void StartBetRace() throws InterruptedException {
            this.race.StartRace();

            for (Map.Entry<RacingSnail, Bet> entry : Bets.entrySet()) {
                //System.out.println(WHITE + entry.getKey().name + ": " + entry.getValue().Data + " Winner: " + this.race.GetWinner());
                if (entry.getValue().Data.get("Snail") == this.race.GetWinner()) {
                    String Temp = GREEN + this.race.GetWinner().name + " has won and the award is: " + ( (int) entry.getValue().Data.get("BetAmount") * (int) this.factor);
                    System.out.println(Temp);
                }
            }
        }
    }

    public  static class Bet extends Base {
        public Bet(HashMap<String, Object> Data) {
            super(Data);

            RacingSnail snail = (RacingSnail) Data.get("Snail");
            int bet = Math.abs((int) Data.get("BetAmount"));
        }
    }

    //👉🚶‍♂️🔥😈
    public static void main(String[] args) throws InterruptedException {
        // Turbo snail
        HashMap<String, Object> DataTurbo = new HashMap<>();
        DataTurbo.put("Name", "Turbo");
        DataTurbo.put("Race", "Speedy Gonsalez");
        DataTurbo.put("MaxSpeed", 100);
        DataTurbo.put("DistanceTraveled", 0);
        DataTurbo.put("Color", GREEN);

        RacingSnail Turbo = new RacingSnail(DataTurbo);

        //System.out.println(GREEN + Turbo.ReturnDataAsString());

        // Turbo2 snail
        HashMap<String, Object> DataTurbo2 = new HashMap<>();
        DataTurbo2.put("Name", "Turbo2");
        DataTurbo2.put("Race", "Speedy Gonsalez");
        DataTurbo2.put("MaxSpeed", 100);
        DataTurbo2.put("DistanceTraveled", 0);
        DataTurbo2.put("Color", WHITE);

        RacingSnail Turbo2 = new RacingSnail(DataTurbo2);

        //System.out.println(GREEN + Turbo2.ReturnDataAsString());

        // LameAss snail
        HashMap<String, Object> DataLame = new HashMap<>();
        DataLame.put("Name", "Lame ass snail");
        DataLame.put("Race", "Speedy Gonsalez");
        DataLame.put("MaxSpeed", 10);
        DataLame.put("DistanceTraveled", 0);
        DataLame.put("Color", YELLOW);

        RacingSnail Lame = new RacingSnail(DataLame);

        //System.out.println(GREEN + Lame.ReturnDataAsString());

        /*
        for (int i = 0; i <= 10; i++) {
            Turbo.move();
            TimeUnit.MILLISECONDS.sleep(100);
        }
        */

        // Sebulba race
        HashMap<String, Object> DataRace = new HashMap<>();
        DataRace.put("Name", "Sebulba");
        DataRace.put("Length", 500);

        Race Sebulba = new Race(DataRace);

        Sebulba.AddSnail(Turbo);
        Sebulba.AddSnail(Turbo2);
        Sebulba.AddSnail(Lame);
        //System.out.println(GREEN + "Winner: " + Sebulba.GetWinner().name);

        TimeUnit.MILLISECONDS.sleep(100);

        /*
        Sebulba.KillSnail(Turbo);
        System.out.println(GREEN + "Winner: " + Sebulba.GetWinner());
        */

        //Sebulba.StartRace();

        //System.out.println(YELLOW + Sebulba.ReturnDataAsString());

        // Gambling stuff
        HashMap<String, Object> DataCasino = new HashMap<>();
        DataCasino.put("Factor", (int) 2);
        DataCasino.put("Race", Sebulba);

        Casino Casino1 = new Casino(DataCasino);

        // AddSnails
        HashMap<String, Object> BetInfo1 = new HashMap<>();
        BetInfo1.put("Snail", Turbo2);
        BetInfo1.put("BetAmount", (int) 200);

        HashMap<String, Object> BetInfo2 = new HashMap<>();
        BetInfo2.put("Snail", Turbo);
        BetInfo2.put("BetAmount", (int) 100);

        Casino1.AddBet(BetInfo1);
        Casino1.AddBet(BetInfo2);
        Casino1.AddBet(BetInfo1); // Dupe test

        Casino1.StartBetRace();
    }
}