package adapter.translator;

public interface Translator {
    
    String startGame();
    
    String endGame();
    
    String translateMoveFromHGomoku(String lastMove);
    
    String translateMoveToHGomoku(String lastMove);
    
    boolean isMoveProvided(String line);
}
