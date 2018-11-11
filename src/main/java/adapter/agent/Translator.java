package adapter.agent;

public interface Translator {

    String EOL = "\n";
    int CODE_MIN_LETTER_ASCII = 97;
    int CODE_MIN_UP_LETTER_ASCII = 65;

    String startGame();
    
    String endGame();
    
    String translateMoveFromHGomoku(String lastMove);
    
    String translateMoveToHGomoku(String lastMove);
    
    boolean isMoveProvided(String line);

    String getCommunicationHandler();
}
