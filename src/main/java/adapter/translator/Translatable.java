package adapter.translator;

public interface Translatable {
    
    public String startGame();
    
    public String endGame();
    
    public String translateMoveFromHGomoku(String lastMove);
    
    public String translateMoveToHGomoku(String lastMove);
    
    public boolean isMoveProvided(String line);
}
