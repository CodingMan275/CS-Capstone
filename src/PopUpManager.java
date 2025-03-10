import javax.swing.*;

public class PopUpManager
{		
		private boolean Active = false;
		private Himothy ref;
		private JFrame CurrentFrame;
		//See line 50
		private Board board;
		
		private int Game;
 		TileMove TMove = new TileMove();
		MusicPopup Stereo = new MusicPopup();
 		
 		PopUpManager(Himothy playerRef, Board board)
 		{
 			this.ref = playerRef;
			this.board = board;
 		}
 		
		public boolean GetActive()
		{
			return Active;
		}
		
		public void KillFrame()
		{
			CurrentFrame.setVisible(false);
            switch(Game)
            {
            	case 1-> TMove.kill();
				case 2 -> Stereo.kill();

            }
            Game = 0;
			Active = false;
			CurrentFrame = null;
		}
	
	
        public void toggleNewFrame(int game) 
        {
        	this.Game = game;
        	Active = true;
           	switch(Game)
         	{
         	case 1 -> CurrentFrame = TMove.Start();
			case 2 -> {
				//musicalBox object needs to be sent to the MusicPopup, somehow, so it uses the board
				Stereo.Stereo = board.BoardTiles[20].musicalBox;
				//Stereo.board = board;
				CurrentFrame = Stereo.Start();}
         	default -> System.out.println("Fail tile");
         	}
        	CurrentFrame.setVisible(true);
        }
        
        public void Input(String input)
        {
        	if(input.compareTo("e")==0)
         		KillFrame();
         	
             switch(Game)
             {
             case 1-> Reward(TMove.GetInput(CurrentFrame, input));
			 case 2 -> Stereo.GetInput(CurrentFrame, input);
             }
        }
        
        private void Reward(int b)
        {        		
	        	if(b==1)
	                switch(Game)
	                {
						case 1 -> ref.UpdateScore(TMove.Win);
	                	
	                }
	        	
	        	else if(b==-1)
	                switch(Game)
	                {
						case 1 -> ref.UpdateScore(TMove.Lose);
	                }
	        	if(b!=0)
	        		KillFrame();
        }
}