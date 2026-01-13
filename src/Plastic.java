import java.awt.*;

    public class Plastic {

        //VARIABLE DECLARATION SECTION
        //Here's where you state which variables you are going to use.
        public String name;                //holds the name of the hero
        public int xpos;                //the x position
        public int ypos;                //the y position
        public int dx;                    //the speed of the hero in the x direction
        public int dy;                    //the speed of the hero in the y direction
        public int width;
        public int height;
        public int plastic;
        public boolean isAlive;//a boolean to denote if the hero is alive or dead.
        public Rectangle hitbox;



        // METHOD DEFINITION SECTION

        // Constructor Definition
        // A constructor builds the object when called and sets variable values.


        //This is a SECOND constructor that takes 3 parameters.  This allows us to specify the hero's name and position when we build it.
        // if you put in a String, an int and an int the program will use this constructor instead of the one above.
        public Plastic (int pXpos, int pYpos) {
            xpos = pXpos;
            ypos = pYpos;
            dx = 5;
            dy = 4;
            width = 60;
            height = 60;
            isAlive = true;
            hitbox= new Rectangle(xpos,ypos,width,height);
        } // constructor

        //The move method.Everytime this is run (or "called") the hero's x position and y position change by dx and dy
        public void move() {
            if(xpos>=1000){//wrap when hits right wall- dx=5 and yx=0
                xpos = 0-width;
            }
            if(ypos<=0){//wrap when it hits the top wall- dx= 0 and yx=5
                ypos=699;
            }
            if(ypos>=700) {//wrap when it hits the bottom wall
                ypos=1;
            }

            xpos = xpos + dx;
            ypos = ypos + dy;

        }
    }

