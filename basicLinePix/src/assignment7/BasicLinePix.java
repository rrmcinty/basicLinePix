package assignment7;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowStateListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import assignment7.Rectangle;
import assignment7.Shape;

@SuppressWarnings("serial")
public class BasicLinePix extends JFrame {

	/**
	 * @param args
	 */
	private JPanel drawingPanel; // user draws here

	private Container cp;
	private JPanel statusBar;
	private JLabel statusLabel;// used to show informational messages

	private JMenuBar drawBar;
	private JMenu drawMenu;
	private JMenuBar menuBar;
	private JMenu fileMenu;
	private EventHandler eh;
	
	public ArrayList<Shape> shapes = new ArrayList<Shape>();
	
	private enum Mode {
		LINE, RECTANGLE
	};
	//create buttons for these modes
	private Mode drawMode = Mode.LINE;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		BasicLinePix lp = new BasicLinePix();
		
		
		lp.setVisible(true);

	}

	public BasicLinePix() {
		setTitle("Basic Line Pix 1.0");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		cp = getContentPane();
		cp.setLayout(new BorderLayout());
		
		eh = new EventHandler();

		drawingPanel = makeDrawingPanel();
		drawingPanel.addMouseListener(eh);
		drawingPanel.addMouseMotionListener(eh);

		
		statusBar = createStatusBar();

		cp.add(drawingPanel, BorderLayout.CENTER);
		cp.add(statusBar, BorderLayout.SOUTH);

		
		buildMenu();

		pack();
	}

	public void buildMenu() {	
		menuBar = new JMenuBar();
		fileMenu = new JMenu("File");
		
		JMenuItem menuItem = new JMenuItem("New");
		menuItem.addActionListener(eh);
		fileMenu.add(menuItem);

		menuItem = new JMenuItem("Open");
		menuItem.addActionListener(eh);
		fileMenu.add(menuItem);

		menuItem = new JMenuItem("Save");
		menuItem.addActionListener(eh);
		fileMenu.add(menuItem);

		menuItem = new JMenuItem("Exit");
		menuItem.addActionListener(eh);
		fileMenu.add(menuItem);

		menuBar.add(fileMenu);
		
		
		setJMenuBar(menuBar);
		
		fileMenu = new JMenu("Draw");
		
		JMenuItem drawItem = new JMenuItem("Line");
		drawItem.addActionListener(eh);
		fileMenu.add(drawItem);

		drawItem = new JMenuItem("Rectangle");
		drawItem.addActionListener(eh);
		fileMenu.add(drawItem);
		
		menuBar.add(fileMenu);

	}

	private JPanel makeDrawingPanel() {
		// TODO Auto-generated method stub
		JPanel p = new JPanel();
		p.setPreferredSize(new Dimension(500, 400));
		p.setBackground(Color.YELLOW);


		return p;
	}

	private JPanel createStatusBar() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		statusLabel = new JLabel("No message");
		panel.add(statusLabel, BorderLayout.CENTER);

		panel.setBorder(new BevelBorder(BevelBorder.LOWERED));

		return panel;
	}

	//this method overrides the paint method defined in JFrame
	//add code here for drawing the lines on the drawingPanel
	@Override
	public void paint(Graphics g) {
		super.paint(g);
	


		// Send a message to each line in the drawing to
		// draw itself on g1
		for (Shape s : shapes) {
			s.draw(g);
		}
	}

	// Inner class - instances of this class handle action events
	private class EventHandler implements ActionListener, MouseListener, MouseMotionListener {

		private int startX, startY; // line's start coordinates
		private int lastX, lastY; // line's most recent end point
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			// FOR THE MENU BAR OPTIONS
			if (arg0.getActionCommand().equals("Exit")) {
				statusLabel.setText("Exiting program...");
				System.exit(0);
			}
			else if (arg0.getActionCommand().equals("New")) {

				Graphics g = drawingPanel.getGraphics();
				shapes.clear();
				g.setColor(Color.yellow);
				g.fillRect(0, 0, getWidth(), getHeight());
			}
			else if (arg0.getActionCommand().equals("Open")) {
				//read in txt file
				Graphics g = drawingPanel.getGraphics();
				JFileChooser fc = new JFileChooser();
				JDialog dialog = new JDialog();
				int returnValue = fc.showOpenDialog(dialog);
				if (returnValue == fc.APPROVE_OPTION) {
					File file = fc.getSelectedFile();
					Scanner s;
					try {
						s = new Scanner(file);
						s.useDelimiter("\\s+");
						shapes.clear();
						while(s.hasNext()) {
								if (s.next().equals("Line")) {
									Shape a = new Line(s.nextInt(),s.nextInt(),s.nextInt(),s.nextInt(),s.next());
									shapes.add(a);
								}
								 
								else  {
									
									Shape a = new Rectangle(s.nextInt(),s.nextInt(),s.nextInt(),s.nextInt(),s.next());
									shapes.add(a);	
								}
						}
					}
					
						
					catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					

				}
				System.out.println(Arrays.toString(shapes.toArray()));
				paint(g);
				
				//fc.setFileSelectionMode(mode);
				//make line objects
				//add line objects to array
				//draw lines
			}
			else if (arg0.getActionCommand().equals("Save")) {
				//write lines array into txt file
				JFileChooser fc = new JFileChooser(".");
				JDialog dialog = new JDialog();
				int returnValue = fc.showSaveDialog(dialog);
				if (returnValue == fc.APPROVE_OPTION) {
					File file = fc.getSelectedFile();
				      try {
				            FileWriter fw = new FileWriter(file+".txt");
				            for (Shape shape : shapes) {
				            	fw.write(shape.toString());	
				            	fw.write(System.getProperty( "line.separator" ));
//				            	fw.write("\n");
				            	System.out.println(shape.toString());
				            }
				            fw.close();
				            
				        } catch (Exception ex) {
				            ex.printStackTrace();
				        }
				}
			}
			else if (arg0.getActionCommand().equals("Line")) {
				drawMode = Mode.LINE;
			}
			else if (arg0.getActionCommand().equals("Rectangle")) {
				drawMode = Mode.RECTANGLE;
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
			
			startX = e.getX();
			startY = e.getY();

			// initialize lastX, lastY
			lastX = startX;
			lastY = startY;
			
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			// Implement rubber-band cursor
			Graphics g = drawingPanel.getGraphics();
			g.setColor(Color.black);
		
			g.setXORMode(drawingPanel.getBackground());
		
			// REDRAW the line that was drawn 
			// most recently during this drag
			// XOR mode means that yellow pixels turn black
			// essentially erasing the existing line
			g.drawLine(startX, startY, lastX, lastY);
		
			// draw line to current mouse position
			// XOR mode: yellow pixels become black
			// black pixels, like those from existing lines, temporarily become
			// yellow
			g.drawLine(startX, startY, e.getX(), e.getY());
			lastX = e.getX();
			lastY = e.getY();
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			Graphics g = drawingPanel.getGraphics();
			if (drawMode == Mode.LINE) {
				Line l = new Line(startX, startY, arg0.getX(), arg0.getY());
				if (l.getStartX()==l.getX() && l.getStartY()==l.getY()) {

				}
				else {
					shapes.add(l);
					l.draw(g);
				}
			}
			else if (drawMode == Mode.RECTANGLE) {
				g.setColor(Color.yellow);
				g.drawLine(startX, startY, arg0.getX(), arg0.getY());
				Rectangle r = new Rectangle(startX, startY, arg0.getX(), arg0.getY());
				if (r.getTLX()==r.getBRX() || r.getTLY()==r.getBRY()) {
					
				}
				else {
				shapes.add(r);
				r.draw(g);
				}
			}
		}

		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub

			int x=arg0.getX();
			int y =arg0.getY();
			int[] xrange = {x-10,x-9,x-8,x-7,x-6,x-5,x-4,x-3,x-2,x-1,x,x+1,x+2,x+3,x+4,x+5,x+6,x+7,x+8,x+9,x+10};
			int[] yrange = {y-10,y-9,y-8,y-7,y-6,y-5,y-4,y-3,y-2,y-1,y,y+1,y+2,y+3,y+4,y+5,y+6,y+7,y+8,y+9,y+10};
			Graphics g = drawingPanel.getGraphics();
			

			boolean contains = false;
			 //&& onLine==true??
			//need to remove all removed lines from list
			if (arg0.isControlDown()) {
				for (Shape shape : shapes) {
//					//search through lines, if line contains clicked point then remove line
//					//from lines 
					for(int i : xrange) {
						for(int j : yrange) {
							contains = shape.contains(i, j);
							if (contains) {
							//	repaint();
								//g.clearRect(0, 0, getWidth(), getHeight());
								g.setColor(Color.yellow);
								g.fillRect(0, 0, getWidth(), getHeight());
								g.setColor(getForeground());
								//repaint();
								//g = drawingPanel.getGraphics();
								
								
							//	makeDrawingPanel();
								System.out.println(Arrays.toString(shapes.toArray()));
								g.setColor(Color.black);
//								g.drawLine(line.getStartX(), line.getStartY(), line.getX(),line.getY());
								shapes.remove(shapes.indexOf(shape));
								System.out.println(Arrays.toString(shapes.toArray()));
								//g.drawLine(line.getStartX(), line.getStartY(), line.getX(),line.getY());
								for (Shape s : shapes) {
									s.draw(g);
									
								}
								break;
								
							}
							else {
								continue;
							}	
						}
					if (contains) {
						break;
					}
					}

					if(contains) {
						break;
					}
				}
			}
		}
			

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseMoved(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

	}

}

