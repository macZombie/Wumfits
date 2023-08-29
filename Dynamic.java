import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.Toolkit;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

public class Dynamic extends JPanel implements Runnable{
	
	
	 private static final long serialVersionUID = 1L;


	// Thread handling
	Thread myThread;
	 
	 
	 // Misc Global Variables
	 static int counter;
	 static int timer; 
	 static int mainTimer;
	 static int sleepTime = 25;
	 static int scopeX = Wumfits.wide;
	 static int scopeY = Wumfits.high;

     int localFrameBufferSize = Wumfits.wide * Wumfits.high;
    
     int[] localFrameBufferArray = new int[localFrameBufferSize + ( Wumfits.wide * 2 ) ];

     int myColor = 0;

     
	
	
	
	
	
	
	

	@Override
	public void run() {
		// TODO Auto-generated method stub
		 boolean running = true;

		   
         while(running){
             
             counter++;

             repaint();
            	 
            //noise();
         
            rainbow();
            
             

             //blackBackground();
        
     
     

     
             try{

            	 Thread.sleep(sleepTime);
       
     
             }
             catch(Exception e){
       
                 System.out.println("\nCatch!");
       
             }
     
     
         } // while
   
   
         return;
     }

		
    
public Dynamic(){

    myThread = new Thread(this);	
    
    myThread.start();

}




// PLOTTING AND COLOURS
@Override
public void paint(Graphics g){

    Graphics2D g2 = (Graphics2D)g;

    
    int thisX = 0;
    int thisY = 0;
    int address = 0;
    int thisPixel = 0;
    Shape pixel;
    Color c;
    int redCol = 0;
    int greenCol = 0;
    int blueCol = 0; 
    
    for( thisY = 0 ; thisY < scopeY; thisY++){
        
        for( thisX = 0 ; thisX < scopeX; thisX++){
        
            address = ( thisY * scopeX ) + thisX;
        
            thisPixel = localFrameBufferArray[address];
            pixel = new Rectangle2D.Float(thisX,thisY,1,1);
        
            blueCol = thisPixel & 0X000000FF;
            greenCol =  ( thisPixel & 0x0000FF00 ) / 256;
            redCol = ( thisPixel & 0x00FF0000 ) / 65536 ;
        
            c = new Color( redCol , greenCol , blueCol ) ;
        
            g2.setColor(c);
            g2.fill(pixel);
        
        } // for thisX

        } // for thisY
    

    return;	
    } // end of paint
    
    
    
public void setColor(Double ratio){
 
    int redCol = 0;
    int greenCol = 0;
    int blueCol =0;

    int index = 0;
    int phase = 0;
    Double factor = 0.0;
    Double lower;
    Double upper;

    if ( ratio >= 1.0 ){
    
        ratio = 0.99999;
    
    }

    ratio = ratio * 0xFFFFFF;


    for ( index = 0; index <= 6; index++ ){
        
        lower = index * 2396745.0;
        upper = lower + 2396745.0;
    
        if( (ratio >= lower ) && ( ratio < upper ) ){
    
        phase = index;
        factor = ( ratio - lower );
    
        }
    
    
    }

    Double pos = 0.0;
    pos = ( factor / 2396745.0 ) * 256.0 ;
    int posi = pos.intValue();
    int negi = 255 - posi;

    switch(phase){
    
        case 0: redCol = posi ; greenCol = 0   ; blueCol = 0   ; break;
        case 1: redCol = 255  ; greenCol = posi; blueCol = 0   ; break;
        case 2: redCol = negi ; greenCol = 255 ; blueCol = 0   ; break;
        case 3: redCol = 0    ; greenCol = 255 ; blueCol = posi; break;
        case 4: redCol = 0    ; greenCol = negi; blueCol = 255 ; break;
        case 5: redCol = posi ; greenCol = 0   ; blueCol = 255 ; break;
        case 6: redCol = 255  ; greenCol = posi; blueCol = 255 ; break;
        
        default: redCol = 0   ; greenCol = 0   ; blueCol = 0   ; break;
        
    }// switch phase



    myColor = ( redCol * 65536 ) + ( greenCol * 256 ) + blueCol;

    return;

    }
    
    
    
public void plot(int x,int y){

    y = ( scopeY - y ); // correct to make origin at bottom left

    int address = ( y * scopeX) + x ;

    int pixel = myColor;

    if ( ( x <= scopeX ) && ( y <= scopeY )){

        localFrameBufferArray[address] = pixel;

    }

    return;
}
  

// DEMO METHODS
public void noise(){

    int i;

    for ( i = 0; i < localFrameBufferSize; i = i + 1 ){
    
    
        Double thisNoise;

        thisNoise =  Math.random() * 16777216.0;

        localFrameBufferArray[i] = thisNoise.intValue() ;

    }


    return;
}



public void rainbow(){

    int x;
    
    int y;

    Double trigger = 1.0;

    setColor(0.0);

    y = 100;


    for ( x = 0; x <= scopeX; x++ ){

        for ( y = 0; y <= ( scopeY ); y++ ){

            trigger = counter / 200.0;
    
            setColor( 0.5 * ( ( x * y ) / ( scopeX * 600.0 ) )  + trigger );		
    
            plot(x,y);
  
          
            if ( counter > 180 ){
          
            	counter = 0;
            
            }
  
  

        }


    }


return;
}








}
