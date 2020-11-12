import java.applet.*;
import java.awt.*;
import java.awt.Button;
import java.awt.event.*;
import java.util.*;

public class PaintBrush extends Applet implements MouseListener,MouseMotionListener{
   Button btn_oval; 
   Button btn_rectangle; 
   Button btn_line;
   Button btn_FreeHand;
   Button btn_red;
   Button btn_green;
   Button btn_blue;
   Button btn_black;
   Checkbox btn_solid;
   Button btn_clear;
   Button btn_Eraser;
   
    int state_shape=0;
	int state_solid=0;
	int state_color=4;
	
	int width=0;
	int	height=0;
	
	line line_obj = new line();
	oval oval_obj = new oval();
	oval eraser_obj = new oval();
	lines freeHand_obj = new lines();
	rectangle rectangle_obj = new rectangle();
	boolean flag_shape= false;
	Point startPoint = new Point();
	Point endPoint = new Point();
	Point startPointClick = new Point();
   
	Vector<Geoshape> geoshape_vector= new Vector<Geoshape>();
   
  abstract class Geoshape{
	 protected int solid;
	 protected boolean eraser_flag;
	 protected int color;
	 protected boolean flag;
	 protected Point pointStart = new Point();
		protected Point pointEnd = new Point();
	
	public void setPointEnd(Point p)
		{
			pointEnd = p;
		}
		
		public Point getPointEnd()
		{
			return pointEnd;
		}	
		public void setPointStart(Point p) {
			pointStart = p;		
		}
		
		public Point getPointStart(){
			return pointStart;
		}
		public void setSolid(int s) {
			solid = s;		
		}
		
		public int getSolid(){
			return solid;
		}
		public void setColor(int c) {
			color = c;		
		}
		
		public int getColor(){
			return color;
		}
		
		public void setFlag(boolean f) {
			flag = f;		
		}
		
		public boolean getFlag(){
			return flag;
		}
		public void setEraser(boolean f) {
			eraser_flag = f;		
		}
		
		public boolean getEraser(){
			return eraser_flag;
		}
		
		public void color_red(Graphics g){
			g.setColor(Color.red);
		}
		public void color_green(Graphics g){
			g.setColor(Color.green);
		}
		public void color_blue(Graphics g){
			g.setColor(Color.blue);
		}
		public void color_black(Graphics g){
			g.setColor(Color.black);
		}
		abstract public void eraser_draw(Graphics g);
		abstract public void draw(Graphics g);
		abstract public void fill(Graphics g);
		
}
class oval extends Geoshape{
	protected int width;
	protected int height;
	
	public void setWidth(int w) {
			width = w;		
		}
		
		public int getWidth(){
			return width;
		}
		public void setHeight(int h) {
			height = h;		
		}
		
		public int getHeight(){
			return height;
		}
	
	public void draw(Graphics g){
		g.drawOval(pointStart.x,pointStart.y,width,height);
	}
	public void fill(Graphics g){
		g.fillOval(pointStart.x,pointStart.y,width,height);
	}
	
	public void eraser_draw(Graphics g){
			g.setColor(Color.white);
			g.fillOval(pointStart.x-2,pointStart.y-2,15,17);
	}
	
}
class rectangle extends Geoshape{
	protected int width;
	protected int height;
	
	public void setWidth(int w) {
			width = w;		
		}
		
		public int getWidth(){
			return width;
		}
		public void setHeight(int h) {
			height = h;		
		}
		
		public int getHeight(){
			return height;
		}
	
	public void draw(Graphics g){
		g.drawRect(pointStart.x,pointStart.y,width,height);
	}
	public void fill(Graphics g){
		g.fillRect(pointStart.x,pointStart.y,width,height);
	}
	
	public void eraser_draw(Graphics g){}
}
class line extends Geoshape{
	
	
	public void draw(Graphics g){
		g.drawLine(pointStart.x,pointStart.y, pointEnd.x,pointEnd.y);
	}
	public void fill(Graphics g){
		g.drawLine(pointStart.x,pointStart.y, pointEnd.x,pointEnd.y);
	}
	public void eraser_draw(Graphics g){}
}
class lines extends Geoshape{
		
	public void draw(Graphics g){
		if(pointEnd.x!=0&&pointEnd.y!=0){
			
		g.drawLine(pointStart.x,pointStart.y, pointEnd.x,pointEnd.y);}
	}
	public void fill(Graphics g){
		if(pointEnd.x!=0&&pointEnd.y!=0){
			
		g.drawLine(pointStart.x,pointStart.y, pointEnd.x,pointEnd.y);}
	}
	public void eraser_draw(Graphics g){}
}


public void init(){
	 btn_oval=new Button("  Oval  "); //draw oval
    btn_rectangle=new Button("  Rectangle  "); //draw Rectangle
    btn_line=new Button("  Line  "); // draw line
	btn_FreeHand=new Button("  Free Hand  "); // Free Hand
	btn_Eraser=new Button("  Eraser  "); // Free Hand
    btn_red=new Button("  Red  ");  
    btn_green=new Button("  Green  ");
    btn_blue=new Button("  Blue  "); 
	btn_black=new Button("  Black  "); 
	btn_solid=new Checkbox("  Solid  "); // make oval or rectangle solid
	btn_clear=new Button("  Clear ALL  "); //clear all your drawing
	
//Local Anonymous Inner Class////////////////
btn_clear.addActionListener(new ActionListener(){  
  public void actionPerformed(ActionEvent e){
   geoshape_vector.clear();
   repaint();
   }
  });	
btn_solid.addItemListener(new ItemListener(){  
  public void itemStateChanged(ItemEvent ie){
	  if(ie.getStateChange()==1)
			state_solid=1;
	  else 
		  state_solid=0;
	  repaint();
   }
  });	
btn_oval.addActionListener(new ActionListener(){  
  public void actionPerformed(ActionEvent e){
   state_shape=1;
   }
  });
  
btn_rectangle.addActionListener(new ActionListener(){  
  public void actionPerformed(ActionEvent e){
   state_shape=2;
   }
  });
  
btn_line.addActionListener(new ActionListener(){  
  public void actionPerformed(ActionEvent e){
   state_shape=3;
   }
  });
  
btn_FreeHand.addActionListener(new ActionListener(){  
  public void actionPerformed(ActionEvent e){
	state_shape=5;
   }
  });

btn_red.addActionListener(new ActionListener(){  
  public void actionPerformed(ActionEvent e){
   state_color=1;
   }
  });
  
btn_green.addActionListener(new ActionListener(){  
  public void actionPerformed(ActionEvent e){
   state_color=2;
   }
  });
  
btn_blue.addActionListener(new ActionListener(){  
  public void actionPerformed(ActionEvent e){
   state_color=3;
   }
  });
btn_black.addActionListener(new ActionListener(){  
  public void actionPerformed(ActionEvent e){
   state_color=4;
   }
  });
btn_Eraser.addActionListener(new ActionListener(){  
  public void actionPerformed(ActionEvent e){
   state_shape=4;
   }
  });
  /////////////////////////////////////
  
  btn_red.setBackground(Color.red);
   btn_blue.setBackground(Color.blue);
    btn_green.setBackground(Color.green);
	btn_Eraser.setBackground(Color.white);
	btn_black.setBackground(Color.gray);
	add(btn_oval);
	add(btn_rectangle);
	add(btn_line);
	add(btn_FreeHand);
	add(btn_red);
	add(btn_green);
	add(btn_blue);
	add(btn_black);
	add(btn_solid);
	add(btn_clear);
	add(btn_Eraser);
      addMouseListener(this);
	  addMouseMotionListener(this);
	}
	
	
	public void paint(Graphics g){
		    switch(state_shape){
					case 1:
					    oval_obj.setPointStart(startPoint);
					    oval_obj.setWidth(width);
						oval_obj.setHeight(height);
						if(oval_obj.getSolid()==0)
							oval_obj.draw(g);
						else if(oval_obj.getSolid()==1)
							oval_obj.fill(g);
						
					    print_draw(g);
					break;
					case 2:
						rectangle_obj.setPointStart(startPoint);
					    rectangle_obj.setWidth(width);
						rectangle_obj.setHeight(height);
						if(rectangle_obj.getSolid()==0)
							rectangle_obj.draw(g);
						else if(rectangle_obj.getSolid()==1)
							rectangle_obj.fill(g);
						
					    print_draw(g);
					break;
					case 3:
					  line_obj.setPointStart(startPoint);
					  line_obj.setPointEnd(endPoint);
					  line_obj.draw(g);
					  print_draw(g);
						
					break;
					case 4:
					eraser_obj.setPointStart(startPointClick);
						eraser_obj.eraser_draw(g);
					print_draw(g);
					break;
					case 5:
					
					freeHand_obj.draw(g);
			
					  print_draw(g);
					break;
				}
		
		
	}
	
	public void print_draw(Graphics g){
			for(int i=0 ;i<geoshape_vector.size() ;i++){
				switch(geoshape_vector.get(i).getSolid()){
					case 0:	
						if(geoshape_vector.get(i).getColor()==1)
							geoshape_vector.get(i).color_red(g);
					    else if(geoshape_vector.get(i).getColor()==2)	
							geoshape_vector.get(i).color_green(g);
					   else if(geoshape_vector.get(i).getColor()==3)	
						    geoshape_vector.get(i).color_blue(g); 
						else if(geoshape_vector.get(i).getColor()==4)	
						    geoshape_vector.get(i).color_black(g);
						
					
					geoshape_vector.get(i).draw(g);
					break;
					
					case 1:
						
					   if(geoshape_vector.get(i).getColor()==1)
							geoshape_vector.get(i).color_red(g);
					   else if(geoshape_vector.get(i).getColor()==2)	
							geoshape_vector.get(i).color_green(g);
					   else if(geoshape_vector.get(i).getColor()==3)	
					     	geoshape_vector.get(i).color_blue(g);
						else if(geoshape_vector.get(i).getColor()==4)	
						    geoshape_vector.get(i).color_black(g);
					
					geoshape_vector.get(i).fill(g);
					break;}
					if(geoshape_vector.get(i).getEraser()==true)
						geoshape_vector.get(i).eraser_draw(g);
					
			}
	}

	public void mousePressed(MouseEvent e){
		flag_shape = true;
		startPoint = e.getPoint();
		if(state_shape==5)
		{freeHand_obj.setPointStart(e.getPoint());}
	}
	
	public void mouseDragged(MouseEvent e){
		flag_shape=false;			
		width=e.getX()-startPoint.x;
		height=e.getY()-startPoint.y;
		endPoint= e.getPoint();
		if (state_shape==4)
		{
			startPointClick = e.getPoint();
			
		 eraser_obj.setPointStart(startPointClick);
		 eraser_obj.setEraser(true);
		 geoshape_vector.add(eraser_obj);
		 startPointClick= new Point();
				 eraser_obj=new oval();
		}
		else if(state_shape==5)
		{
		freeHand_obj.setPointEnd(e.getPoint());
		if(state_color==1)
			freeHand_obj.setColor(1);
		else if(state_color==2)
			freeHand_obj.setColor(2);
		else if(state_color==3)
			freeHand_obj.setColor(3);
		else if(state_color==4)
			freeHand_obj.setColor(4);
		 geoshape_vector.add(freeHand_obj);
		freeHand_obj=new lines();
		 freeHand_obj.setPointStart(e.getPoint());
		 
		}
		
		repaint();
	}

	public void mouseReleased(MouseEvent e) {
	    if(flag_shape==false){
			switch(state_shape){
					case 1:
							if(state_solid==0)
								oval_obj.setSolid(0);
							else if(state_solid==1)
								oval_obj.setSolid(1);
							
							if(state_color==1)
								oval_obj.setColor(1);
							else if(state_color==2)
								oval_obj.setColor(2);
							else if(state_color==3)
								oval_obj.setColor(3);
							else if(state_color==4)
								oval_obj.setColor(4);
							
						geoshape_vector.add(oval_obj);
						oval_obj = new oval();
						width=0;
						height=0;
					break;
					case 2:
						
							if(state_solid==0)
								rectangle_obj.setSolid(0);
							else if(state_solid==1)
								rectangle_obj.setSolid(1);
							if(state_color==1)
								rectangle_obj.setColor(1);
							else if(state_color==2)
								rectangle_obj.setColor(2);
							else if(state_color==3)
								rectangle_obj.setColor(3);
							else if(state_color==4)
								rectangle_obj.setColor(4);
						geoshape_vector.add(rectangle_obj);
						rectangle_obj = new rectangle();
						width=0;
						height=0;
					break;
					case 3:
							if(state_solid==0)
								line_obj.setSolid(0);
							else if(state_solid==1)
								line_obj.setSolid(1);
							if(state_color==1)
								line_obj.setColor(1);
							else if(state_color==2)
								line_obj.setColor(2);
							else if(state_color==3)
								line_obj.setColor(3);
							else if(state_color==4)
								line_obj.setColor(4);
							geoshape_vector.add(line_obj);
							line_obj = new line();
							startPoint = new Point();
						    endPoint = new Point();	
						
					break;
					
				}
		}
				repaint();
	}
	
	public void mouseMoved(MouseEvent e) {	
	}
	 public void mouseClicked(MouseEvent e) {
		 startPointClick = e.getPoint();
		 if(state_shape==4){
		 eraser_obj.setPointStart(startPointClick);
		 eraser_obj.setEraser(true);
		 geoshape_vector.add(eraser_obj);
		 startPointClick= new Point();  
		 eraser_obj=new oval();}
		
	 }
	public void mouseEntered(MouseEvent e) {}
    
    public void mouseExited(MouseEvent e) {}
    
   

}