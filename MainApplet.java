import java.applet.Applet; 
import java.awt.Button;  
import java.awt.Color; 
import java.awt.Checkbox; 
import java.awt.Graphics;  
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener; 
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;




public class MainApplet extends Applet implements MouseListener, MouseMotionListener
{       // Initializations 
        
	    Button ovalButton;
		Button rectButton;
		Button freeHandButton;
		Button lineButton;
		Button redButton;
		Button greenButton;
		Button blueButton;
		Button eraserButton;
		Button clearAllButton;
		Button undoButton;
	    Checkbox filledButton;  
		
		int x1, x2, y1, y2;
		boolean fillOption = false; // filled or not filled   
		int selectedShape;
        int selectedColor;
		Line line ;  
        Rectangle rect;		 
		Oval oval ;  
		Eraser eraser ; 
		ArrayList<Shape> shapes;
		Graphics g ;
		
	public void init () 
	{   
	    shapes = new ArrayList<Shape>();
		line = new Line(); 
		rect = new Rectangle();  
		oval = new Oval(); 
		eraser = new Eraser ();
	    addMouseListener(this);
        addMouseMotionListener(this); 
		ovalButton = new Button("Oval");
        rectButton = new Button("Rectangle");
        freeHandButton = new Button("Free Hand");
        lineButton = new Button("Line");
        redButton = new Button("Red");
        greenButton = new Button("Green");
        blueButton = new Button("Blue");
        eraserButton = new Button("Eraser");
        clearAllButton = new Button("Clear All");
        filledButton = new Checkbox("Fill Shape", fillOption);
        undoButton = new Button("Undo");  
		
		redButton.setBackground(Color.red);
        greenButton.setBackground(Color.GREEN);
        blueButton.setBackground(Color.BLUE);  
		
		add(ovalButton);
        add(rectButton);
        add(freeHandButton);
        add(lineButton);
        add(redButton);
        add(greenButton);
        add(blueButton);
        add(eraserButton);
        add(clearAllButton);
        add(undoButton);
        add(filledButton);  
		
		 ovalButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                selectedShape = 1;
            }
        });
		 rectButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                selectedShape = 2;
            }
        }); 
		
		  freeHandButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                selectedShape = 3;
            }
        });
		
		// Shapes ActionListener
		 lineButton.addActionListener(new ActionListener() {

           
            public void actionPerformed(ActionEvent e) {
                selectedShape = 4;
            }
        });   
		
		eraserButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                selectedShape = 5;
            }
        });
		
		//Colors ActionListener
		 redButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                selectedColor = 1;
            }
        });

        greenButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                selectedColor = 2;
            }
        });

        blueButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                selectedColor = 3;
            }
        });   
		filledButton.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
                fillOption = filledButton.getState();
            }
        }); 
		clearAllButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                shapes.clear();
                switch (selectedShape) {
                    case 1:
                        oval.setX1(0);
                        oval.setY1(0);
                        oval.setX2(0);
                        oval.setY2(0);
                        break;
                    case 2:
                        rect.setX1(0);
                        rect.setY1(0);
                        rect.setX2(0);
                        rect.setY2(0);
                        break;
                    case 4:
                        line.setX1(0);
                        line.setY1(0);
                        line.setX2(0);
                        line.setY2(0);
                        break;

                }
                repaint();
            }
        });  
		
		undoButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (shapes.size() > 0) {
                    shapes.remove(shapes.size() - 1);
                    switch (selectedShape) {
                        case 1:
                            oval.setX1(0);
                            oval.setY1(0);
                            oval.setX2(0);
                            oval.setY2(0);
                            break;
                        case 2:
                            rect.setX1(0);
                            rect.setY1(0);
                            rect.setX2(0);
                            rect.setY2(0);
                            break;
                        case 4:
                            line.setX1(0);
                            line.setY1(0);
                            line.setX2(0);
                            line.setY2(0);
                            break;

                    }
                    repaint();
                }
            }
        });
		
	} // end of init scope  
	
	    public void paint (Graphics g ) 
		{  
		    for (int i = 0; i < shapes.size(); i++) {
            shapes.get(i).drawShape(g);
            }  
		   switch (selectedShape) { 
		    case 1:
                oval.setShapeColor(selectedColor); 
				oval.setFillOption(fillOption) ; 
				oval.drawShape(g); 
				break;
            case 2:
                rect.setShapeColor(selectedColor); 
				rect.setFillOption(fillOption) ; 
				rect.drawShape(g); 
				break;
            case 4:
                line.setShapeColor(selectedColor); 
				line.setFillOption(fillOption) ; 
				line.drawShape(g); 
				break; 
			case 5:
                eraser.drawShape(g);
                break;
               
        }
			
		}
		public void mouseClicked(MouseEvent e) {} 
		public void mouseEntered(MouseEvent e) {} 
		public void mouseExited(MouseEvent e) {} 
		public void mouseMoved(MouseEvent e) {} 
		public void mousePressed(MouseEvent e) {
			 x1 = e.getX();
             y1 = e.getY();  
			 
			 switch (selectedShape) { 
			  case 1:
                oval.setX1(x1);
                oval.setY1(y1); 
				break;
            case 2:
                rect.setX1(x1);
                rect.setY1(y1); 
				break;
             case 4:
                line.setX1(x1);
                line.setY1(y1); 
				break; 
			 case 5:
                eraser.setX1(x1);
                eraser.setY1(y1); 
				//shapes.add(new Eraser(x1, y1, 50, 50, selectedColor, fillOption));
                break;  
               
        }
		} 
		public void mouseDragged(MouseEvent e) { 
		     x2 = e.getX();
             y2 = e.getY();  
			 
			 switch (selectedShape) { 
			  case 1:
                oval.setX2(x2);
                oval.setY2(y2);
             case 2:
                rect.setX2(x2);
                rect.setY2(y2);
             case 4:
                line.setX2(x2);
                line.setY2(y2); 
			
				
		}  
		repaint() ;
		
} 
		public void mouseReleased(MouseEvent e) { 
		  switch (selectedShape) {
            case 1:
                shapes.add(new Oval(x1, y1, x2, y2, selectedColor, fillOption));
                break;
            case 2:
                shapes.add(new Rectangle(x1, y1, x2, y2, selectedColor, fillOption));
                break;
            case 4:
                shapes.add(new Line(x1, y1, x2, y2, selectedColor, fillOption));
                break;  
			case 5 : 
				 eraser.setX2(x2);
                eraser.setY2(y2);  
				shapes.add(new Eraser(x1, y1, 50, 50, selectedColor, fillOption));   
                break;				
			
        }
			
		}
		  
       
}

class Shape 
{
	private int x1,y1,x2,y2 ;   
	int color ; 
	boolean fillOption ; 
	
	public Shape (int x1, int  y1, int x2, int  y2 , int color , boolean fillOption)
	{
		this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.color = color;
        this.fillOption = fillOption;
	} 
	public Shape () {} 
	
	
	//Setters 
    public void setX1(int x1) {
        this.x1 = x1;
    }

    public void setY1(int y1) {
        this.y1 = y1;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }
    // Getters 
    public int getX1() {
        return x1;
    }

    public int getY1() {
        return y1;
    }

    public int getX2() {
        return x2;
    }

    public int getY2() {
        return y2;
    }

    public void setShapeColor(int color) {
        this.color = color;
    }

    public int getShapeColor() {
        return color;
    }

    public boolean getFillOption() {
        return fillOption;
    }

    public void setFillOption(boolean fillOption) {
        this.fillOption = fillOption;
    }

	// Implemented by Children
    public void drawShape(Graphics g) {
    }
	
} 

class Line extends Shape 

{    public Line (int x1 , int y1 , int x2 , int y2 , int color , boolean fillOption) 
	{
		super(x1, y1, x2, y2, color, fillOption);
	}  
   
	public Line () {}  
	
	
	
	public void drawShape (Graphics g) 
	{
		switch (getShapeColor () ) 
		{
			case 1 : 
			g.setColor (Color.red);
			break ;  
			case 2 : 
			g.setColor (Color.GREEN); 
			break ;  
			case 3 : 
			g.setColor(Color.BLUE);
			break ;   
			default : 
			g.setColor(Color.BLACK);
			break ;
			
		} 
		g.drawLine(getX1() , getY1() , getX2() , getY2()); 

	}
	
} 

class Rectangle extends Shape 
{    int x,y,width,height;
 
	 public Rectangle (int x1 , int y1 , int x2 , int y2 , int color , boolean fillOption) 
	{
		super(x1, y1, x2, y2, color, fillOption);
	}  
   
	public Rectangle () {}   

      public void drawShape (Graphics g) 
	{
		switch (getShapeColor () ) 
		{
			case 1 : 
			g.setColor (Color.red);
			break ;  
			case 2 : 
			g.setColor (Color.GREEN); 
			break ;  
			case 3 : 
			g.setColor(Color.BLUE);
			break ;   
			default : 
			g.setColor(Color.BLACK);
			break ;
			
		} 
		 x = Math.min(getX1(), getX2()); 
         y = Math.min(getY1(), getY2());
         width = Math.abs(getX1() - getX2());
         height = Math.abs(getY1() - getY2());
         
        if (getFillOption() == true) {
            g.fillRect(x, y, width, height);
        } else {
            g.drawRect(x, y, width, height);
        }

	}	
	
	
	
} 


class Oval extends Shape 
{
	int x,y,width,height;

    public Oval() {
    }

    public Oval (int x1 , int y1 , int x2 , int y2 , int color , boolean fillOption) 
	{
		super(x1, y1, x2, y2, color, fillOption);
	}  

    public void drawShape(Graphics g) {
        switch (getShapeColor () ) 
		{
			case 1 : 
			g.setColor (Color.red);
			break ;  
			case 2 : 
			g.setColor (Color.GREEN); 
			break ;  
			case 3 : 
			g.setColor(Color.BLUE);
			break ;   
			default : 
			g.setColor(Color.BLACK);
			break ;
			
		}

         x = Math.min(getX1(), getX2());
         y = Math.min(getY1(), getY2());
         width = Math.abs(getX1() - getX2());
         height = Math.abs(getY1() - getY2());

        if (getFillOption() == true) {
            g.fillOval(x, y, width, height);
        } else {
            g.drawOval(x, y, width, height);
        }
    }
} 


class Eraser extends Shape {

    public Eraser() {
    }

    public Eraser(int x1, int y1, int x2, int y2, int color, boolean fillOption) {
        super(x1, y1, x2, y2, color, fillOption);
    }

    
    public void drawShape(Graphics g) {
        g.fillOval(getX1(), getY1(),50, 50);
        g.setColor(Color.WHITE);
    }
}