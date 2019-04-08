package main_pack;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Main_frame {
	
	private JFrame frame;
	static JTextField[] tax_value = new JTextField[18];
	static JTextField[] tax_count = new JTextField[18];
	static Font for_label=new Font ("Tahoma", Font.PLAIN, 20);
	static Font for_text=new Font ("Dialog", Font.PLAIN, 16);
	GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
	int width_display = gd.getDisplayMode().getWidth();
	int height_display = gd.getDisplayMode().getHeight();
	int width_frame = 800;
	int height_frame = 500;
	JLabel end_sum = new JLabel();
	JButton sum = new JButton("Суммарно:");
	double counter = 0;
	static JCheckBox[] num = new JCheckBox[18];
	static double x;
	static double y;
				
	public static void main(String[] args) { 
		//в основном при разработке приложений советуют выполнять запуск программы отдельным потоком
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main_frame window = new Main_frame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	};
	public Main_frame(){
		//инициализируем выполнение фрейма и его элементов
		initialize();
	}
	
	public void initialize() {
		//создаём фрейм и задаём параметры
		frame = new JFrame();
		frame.setFont(for_label);
		frame.setTitle("Расчет квартплаты");
		frame.setResizable(false);
		frame.setBounds(width_display/2-width_frame/2, height_display/2-height_frame/2, width_frame, height_frame);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		place_components();
	}
	
	public void place_components() {
		//размещаем компоненты на фрейме
		JPanel grid1 = new JPanel();
		JPanel grid2 = new JPanel();
		int grid1size = frame.getWidth()/2;
		int grid2size = frame.getWidth()-grid1size;
		grid1.setSize(grid1size, frame.getHeight());
		grid2.setSize(grid2size, frame.getHeight());
		frame.add(grid1, BorderLayout.LINE_START);
		frame.add(grid2, BorderLayout.CENTER);
		GridLayout layoutGrid1 = new GridLayout(0, 1, 20, 5);
		GridLayout layoutGrid2 = new GridLayout(0, 4, 20, 5);
		grid1.setLayout(layoutGrid1);
		grid2.setLayout(layoutGrid2);
		
		String[] num_name = {"Содержание", "Уб.мест общ.польз.", "ХВС при СОИ", "ТЭ ГВС при СОИ", "ХВ на ГВС при СОИ", "Отв.ст.в.Х при СОИ",
				"Отв.ст.в.Г при СОИ", "Эл.эн при СОИ", "Отопление", "Хол.водоснабжение", "ХВ на нужды ГВС", "Тепл.энергия ГВС", "Отв.сточ.вод ХВ",
				"Отв.сточ.вод ГВ", "Газ", "Обращение с ТКО", "ЗПУ", "Радио"};
		JLabel[] parasum_output = new JLabel[18];
		double[] parasumm = new double[18];
		grid1.add(new JLabel(" №   "));
		grid2.add(new JLabel("Вид платежа"));
		grid2.add(new JLabel("Тариф"));
		grid2.add(new JLabel("Объём"));
		grid2.add(new JLabel("Итого"));
		//установка чекбоксов
		for(int i = 0; i < 18;i++) {
			grid1.add(num[i] = new JCheckBox(String.valueOf(i+1)));
			num[i].setSelected(true);
		}
		//установка форм ввода
		grid1.add(new JPanel());
		for(int i = 0; i < 18;i++) {
			grid2.add(new JLabel(num_name[i]));
			grid2.add(tax_value[i] = new JTextField("1"));
			grid2.add(tax_count[i] = new JTextField("1"));
			grid2.add(parasum_output[i] = new JLabel());
			check_wrong_enter(i);
		}
		grid2.add(new JPanel());
		grid2.add(new JPanel());
		grid2.add(sum);
		grid2.add(end_sum);
		
		//слушатель кнопки с выполнением логики расчётов
		sum.addActionListener(new ActionListener(){
	      public void actionPerformed(ActionEvent e){
	    	  counter = 0;
	    	  for(int i = 0; i < 18;i++) {
	    		  x = Double.valueOf(tax_value[i].getText()).doubleValue();
	    		  y = Double.valueOf(tax_count[i].getText()).doubleValue();
	    		  try{
	    			  if(num[i].isSelected()) {
	    			  parasumm[i]=parasumm(x, y);
		    		  parasum_output[i].setText(String.valueOf(parasumm(x, y)));
	    			  }
	    			  else
	    			  {
	    				  parasumm[i]=0;
	    				  parasum_output[i].setText("0");
	    			  }
	    			  endsumm(i);
	    			  
	    		  }catch(NumberFormatException exception) {
	    			  //если строка будет пустой или иметь строку, не переводимую в дабл, то окончить расчёты с ошибкой
	    			  JOptionPane.showMessageDialog(frame, "Нечисленное значение и/или одно или несколько полей пусты:"
	    			  		+ " Пожалуйста, вернитесь и введите значение", "Ошибка", JOptionPane.ERROR_MESSAGE);
	    				return;
	    		  }
	    	  }
	      end_sum.setText(String.valueOf(counter));
	      }
	    });
	}
	public static double parasumm(double x, double y) {
	//расчёт значения строки услуги	
		return x*y;
	}
	public double endsumm(int i) {
		//расчёт суммы стоимости всех услуг
		counter = counter + parasumm(x, y);
		return counter;
	}
	public void check_wrong_enter(int position){
		//запрещает нам вводить с клавиатуры иные знаки, кроме чисел или точки
		tax_value[position].addKeyListener(new KeyAdapter() {
			   public void keyTyped(KeyEvent e) {
			      char f = e.getKeyChar();
			      if ((f <= 47 || f >= 58) && f != 46){
			         e.consume(); 
			      }
			   }
			});
		tax_count[position].addKeyListener(new KeyAdapter() {
			   public void keyTyped(KeyEvent e) {
			      char f = e.getKeyChar();
			      if ((f <= 47 || f >= 58) && f != 46) {
			         e.consume(); 
			      }
			      
			   }
			});
	}
}