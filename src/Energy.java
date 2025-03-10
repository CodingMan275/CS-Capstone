import javax.swing.*;

public class Energy implements Runnable {

    public Himothy player ;
    public Board board ;
    public JProgressBar energy ;
    public JLabel score;

    Energy(Himothy player, Board board, JProgressBar energy, JLabel score) {

        this.player = player ;
        this.board = board ;
        this.energy = energy ;
        this.score = score;
    }

    public void run() {

        int currentEnergy = energy.getValue() ;
        int baseEnergyChange = 1 ;
        int extraEnergyChange = 0 ;

        // Keeps going forever
        // In the future, may add conditional here
        // e.g. if currentEnergy == 0
        while (true) {

            // Wait for 1 second
            try {
                Thread.sleep(1000) ;
            } catch (InterruptedException e) {
                System.out.println(e);
            }

            // Check the tileType of player's current position
            // Change extraEnergyChange based on that amount
            switch(board.BoardTiles[player.currentTilePosition].tileType){

                case("EMPTY") -> {
                    extraEnergyChange = 0 ;
                }

                case("PERSON") -> {
                    extraEnergyChange = 2 ;
                }

                case("CAT") -> {
                    extraEnergyChange = -10 ;
                }

                case("CRUSH") -> {
                    extraEnergyChange = 4;
                }
                default -> extraEnergyChange = 0;
                
            }

            // change energy based on standard energy change and extra energy change amounts
            currentEnergy -= (baseEnergyChange + extraEnergyChange) ;

            if (currentEnergy > energy.getMaximum()){
                currentEnergy = energy.getMaximum() ;
            }
            if (currentEnergy < energy.getMinimum()){
                currentEnergy = energy.getMinimum() ;
            }

            energy.setValue(currentEnergy) ;
            player.Energy = energy.getValue();

            //Basic energy to points, person tiles
            if(extraEnergyChange > 0)
            {
                try {
                    player.UpdateScore(extraEnergyChange);
                } catch (Exception e) {
                    System.out.println(e);
                }
             
            }
        }

    }


}