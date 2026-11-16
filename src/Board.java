/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Administrador
 */
public class Board {
    private int[][] Board= new int[3][3];
    
    public Board(){
        clearBoard();
    }
    
    public void clearBoard(){
        for(int line=0 ; line<3 ; line++)
            for(int column=0 ; column<3 ; column++)
                Board[line][column]=0;
    }
    
    public void showBoard(){
        System.out.println();
        for(int line=0 ; line<3 ; line++){
        
            for(int column=0 ; column<3 ; column++){
                
                if(Board[line][column]==-1){
                    System.out.print(" X ");
                }
                if(Board[line][column]==1){
                    System.out.print(" O ");
                }
                if(Board[line][column]==0){
                    System.out.print("   ");
                }
                
                if(column==0 || column==1)
                    System.out.print("|");
            }
            System.out.println();
        }
                
    }

    public int getPosition(int[] attempt){
        return Board[attempt[0]][attempt[1]];
    }
    
    public void setPosition(int[] attempt, int player){
        if(player == 1)
            Board[attempt[0]][attempt[1]] = -1;
        else
            Board[attempt[0]][attempt[1]] = 1;
    }

    public int checkLines(){
        for(int line=0 ; line<3 ; line++){

            if( (Board[line][0] + Board[line][1] + Board[line][2]) == -3)
                return -1;
            if( (Board[line][0] + Board[line][1] + Board[line][2]) == 3)
                return 1;
        }
        
        return 0;
                
    }
    
    public int checkColumns(){
        for(int column=0 ; column<3 ; column++){

            if( (Board[0][column] + Board[1][column] + Board[2][column]) == -3)
                return -1;
            if( (Board[0][column] + Board[1][column] + Board[2][column]) == 3)
                return 1;
        }
        
        return 0;
                
    }
    
    public int checkDiagonals(){
        if( (Board[0][0] + Board[1][1] + Board[2][2]) == -3)
            return -1;
        if( (Board[0][0] + Board[1][1] + Board[2][2]) == 3)
            return 1;
        if( (Board[0][2] + Board[1][1] + Board[2][0]) == -3)
            return -1;
        if( (Board[0][2] + Board[1][1] + Board[2][0]) == 3)
            return 1;
        
        return 0;
    }
    
    public boolean fullBoard(){
        for(int line=0 ; line<3 ; line++)
            for(int column=0 ; column<3 ; column++)
                if( Board[line][column]==0 )
                    return false;
        return true;
    }
        // Retorna true se existe alguma linha/coluna/diagonal que ainda pode ser
    // completada pelo jogador 'value' (-1 = X, 1 = O), ou seja, que nao contem
    // nenhuma marca do adversario.
    public boolean canWin(int value){
        int[][][] lines = {
            {{0,0},{0,1},{0,2}}, {{1,0},{1,1},{1,2}}, {{2,0},{2,1},{2,2}},
            {{0,0},{1,0},{2,0}}, {{0,1},{1,1},{2,1}}, {{0,2},{1,2},{2,2}},
            {{0,0},{1,1},{2,2}}, {{0,2},{1,1},{2,0}}
        };
        for(int[][] l : lines){
            boolean alive = true;
            for(int[] cell : l){
                if(Board[cell[0]][cell[1]] == -value){
                    alive = false;
                    break;
                }
            }
            if(alive)
                return true;
        }
        return false;
    }

    // Empate (Velha): ninguem venceu e nenhum jogador consegue mais vencer,
    // mesmo que ainda existam casas vazias. Cobre tambem o tabuleiro cheio.
    public boolean isDraw(){
        if(checkLines() != 0 || checkColumns() != 0 || checkDiagonals() != 0)
            return false;
        return !canWin(-1) && !canWin(1);
    }
}