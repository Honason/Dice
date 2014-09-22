package dice;

public class GameMain {
    public static GUI userInterface = new GUI();

    public static void main(String[] args) {
        //GUI userInterface = new GUI();
    	GameEngine gEngine = new GameEngine();
        gEngine.main();

    }
}