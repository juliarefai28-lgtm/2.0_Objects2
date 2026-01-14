//Basic Game Application
//Version 2
// Basic Object, Image, Movement
// Astronaut moves to the right.
// Threaded

//K. Chun 8/2018

//*******************************************************************************
//Import Section
//Add Java libraries needed for the game
//import java.awt.Canvas;

//Graphics Libraries
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.*;
import java.text.AttributedString;
import javax.swing.JFrame;
import javax.swing.JPanel;


//*******************************************************************************
// Class Definition Section

public class BasicGameApp implements Runnable {

   //Variable Definition Section
   //Declare the variables used in the program 
   //You can set their initial values too
   
   //Sets the width and height of the program window
	final int WIDTH = 1000;
	final int HEIGHT = 700;

   //Declare the variables needed for the graphics
	public JFrame frame;
	public Canvas canvas;
   public JPanel panel;
   
	public BufferStrategy bufferStrategy;
    public Image backgroundPic;
	public Image sharkPic;
    public Image fishPic;
    public Image plasticPic;


   //Declare the objects used in the program
   //These are things that are made up of more than one variable type
    private Shark shark;
    private Fish fish;
    private Plastic plastic;


   // Main method definition
   // This is the code that runs first and automatically
	public static void main(String[] args) {
		BasicGameApp ex = new BasicGameApp();   //creates a new instance of the game
		new Thread(ex).start();                 //creates a threads & starts up the code in the run( ) method  
	}


   // Constructor Method
   // This has the same name as the class
   // This section is the setup portion of the program
   // Initialize your variables and construct your program objects here.
	public BasicGameApp() {
      
      setUpGraphics();
       
      //variable and objects
      //create (construct) the objects needed for the game and load up
        backgroundPic = Toolkit.getDefaultToolkit().getImage("Background.png");
		sharkPic = Toolkit.getDefaultToolkit().getImage("Shark.png"); //load the picture
        fishPic = Toolkit.getDefaultToolkit().getImage("Fish.png");//load the picture
        plasticPic = Toolkit.getDefaultToolkit().getImage("Plastic.png");
        shark = new Shark(40, 20);
        fish = new Fish(200, 200);
        plastic= new Plastic(WIDTH / 2, HEIGHT / 2);


	}// BasicGameApp()

   
//*******************************************************************************
//User Method Section
//
// put your code to do things here.

   // main thread
   // this is the code that plays the game after you set things up
	public void run() {

      //for the moment we will loop things forever.
		while (true) {
            crashing();
         moveThings();  //move all the game objects
         render();  // paint the graphics
         pause(20); // sleep for 10 ms
		}
	}


    public void moveThings() {
       shark.move();
        fish.move();
        plastic.move();
    }

    public void crashing() {

        // fish vs shark (bounce)
//        if (shark.isAlive &&fish.isAlive &&
//                shark.hitbox.intersects(fish.hitbox)) {
//
//            System.out.println("Shark eats Fish!");
//            shark.dx = -shark.dx;
//            fish.dx = -fish.dx;
//        }

        // shark kills fish
        if (shark.isAlive &&
               shark.hitbox.intersects(fish.hitbox)) {

            System.out.println("Fish died :(");
            fish.isAlive = false;
        }
        // Plastic kills shark
        if (shark.isAlive && plastic.isAlive && plastic.hitbox.intersects(shark.hitbox)) {

            System.out.println("Shark dissapeared");
            shark.isAlive = false;
        }

    }
   //Pauses or sleeps the computer for the amount specified in milliseconds
   public void pause(int time ){
   		//sleep
			try {
				Thread.sleep(time);
			} catch (InterruptedException e) {

			}
   }

   //Graphics setup method
   private void setUpGraphics() {
      frame = new JFrame("Application Template");   //Create the program window or frame.  Names it.
   
      panel = (JPanel) frame.getContentPane();  //sets up a JPanel which is what goes in the frame
      panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));  //sizes the JPanel
      panel.setLayout(null);   //set the layout
   
      // creates a canvas which is a blank rectangular area of the screen onto which the application can draw
      // and trap input events (Mouse and Keyboard events)
      canvas = new Canvas();  
      canvas.setBounds(0, 0, WIDTH, HEIGHT);
      canvas.setIgnoreRepaint(true);
   
      panel.add(canvas);  // adds the canvas to the panel.
   
      // frame operations
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //makes the frame close and exit nicely
      frame.pack();  //adjusts the frame and its contents so the sizes are at their default or larger
      frame.setResizable(false);   //makes it so the frame cannot be resized
      frame.setVisible(true);      //IMPORTANT!!!  if the frame is not set to visible it will not appear on the screen!
      
      // sets up things so the screen displays images nicely.
      canvas.createBufferStrategy(2);
      bufferStrategy = canvas.getBufferStrategy();
      canvas.requestFocus();
      System.out.println("DONE graphic setup");
   
   }


	//paints things on the screen using bufferStrategy
	private void render() {
		Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
		g.clearRect(0, 0, WIDTH, HEIGHT);

        //Draw the background Pic
        g. drawImage(backgroundPic,0,0,WIDTH,HEIGHT, null);

      //draw the image of the fish
        if (fish.isAlive == true) {
            g.drawImage(fishPic, fish.xpos, fish.ypos, fish.width, fish.height, null);
        }
        //Draw the image of the shark
        if (shark.isAlive == true) {
            g.drawImage(sharkPic, shark.xpos, shark.ypos, shark.width, shark.height, null);
        }
        //Draw image of plastic
        g.drawImage(plasticPic, plastic.xpos, plastic.ypos, plastic.width, plastic.height, null);

		g.dispose();

		bufferStrategy.show();
	}
}