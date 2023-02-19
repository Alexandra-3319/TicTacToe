package com.hz;

public class PieceFactory {
	
    //use getShape method to get object of type shape 
    public Piece getPiece(String pieceType){
       if(pieceType == null){
          return null;
       }		
       if(pieceType.equalsIgnoreCase("CROSS")){
          return new Cross();
          
       } else if(pieceType.equalsIgnoreCase("CIRCLE")){
          return new Circle();
          
       } else if(pieceType.equalsIgnoreCase("PLAYER")){
          return new Player();
      
      } else if(pieceType.equalsIgnoreCase("CPU")){
         return new CPU();
      }
       
       return null;
    }
 }
