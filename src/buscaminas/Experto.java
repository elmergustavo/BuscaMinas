
package buscaminas;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Experto extends JPanel implements ActionListener{
	private int casillasFaltantes = 0, filas = 16 , columnas = 30;
	int valoresIntermedio[][] = new int[filas][columnas];
	public JLabel labelD;	
	private JButton botonesDemente[][] = new JButton[filas][columnas];
	public JButton botonD;
	
	public String[] archi = {"/libreria/gano.png", "/libreria/perdio.png", "/libreria/nueva.png"};
	private String archivos[] = {"/libreria/exp0.PNG", "/libreria/1.PNG", "/libreria/exp2.PNG", "/libreria/exp3.PNG", "/libreria/exp4.PNG", "/libreria/exp5.PNG", "/libreria/6.PNG", "/libreria/7.PNG", "/libreria/8.PNG", "/libreria/9.PNG"};
	
	private ImageIcon[] imagenes = new ImageIcon[10];    
	public ImageIcon[] ima = new ImageIcon[3];   
        int cantidad;
    private boolean visibleDemente[][] = new boolean[filas][columnas];
	

	public Experto() {
		this.setLayout(null);
		for(int i=0;i<3;i++){
            ima[i] = new ImageIcon(getClass().getResource(archi[i]));
        }
		nuevaPartidaDemente();        
		this.setSize(600, 640);
		botonD = new JButton();
		botonD.setBounds(286, 5, 30, 30);
        botonD.setIcon(ima[2]);                
        this.add(botonD);
        this.botonD.addActionListener(this);
		labelD = new JLabel();
		labelD.setBounds(15, 15, 60, 15);
		this.add(labelD);
	}
	
	public void nuevaPartidaDemente(){
        casillasFaltantes = 0;     
        cantidad = 0;
        
        ponerBotonesDemente();
        verDemente(false);
        ponerMinasDemente();
        contornoDemente();
        visualizarMinasDemente();
        eventosDemente();
    }
	
	public void ponerBotonesDemente(){               
        
        for(int i=0;i<10;i++){
            imagenes[i] = new ImageIcon(getClass().getResource(archivos[i]));
        }
        
            for (int f = 0; f < filas; f++) {
                for (int c = 0; c < columnas; c++) {
                    botonesDemente[f][c] = new JButton();
                    botonesDemente[f][c].setBounds((20 * f) + 60, 20 * c + 40, 20, 20);
                    botonesDemente[f][c].setBackground(Color.GREEN);
                    this.add(botonesDemente[f][c]);
                }
            }      
    }
	
	public void ponerMinasDemente(){
        for(int f=0;f<filas;f++){
        for(int c=0;c<columnas;c++){
            valoresIntermedio[f][c]=0;
        }
        }
           int f1, c1;
        for ( int i=0;i<99;i++){
            do{
                f1=(int)(Math.random()*filas);
                c1=(int)(Math.random()*columnas);
            }while(valoresIntermedio[f1][c1]!=0);
            valoresIntermedio[f1][c1]=9;
        }
    }
	
	public void contornoDemente(){
        for(int f=0;f<filas;f++){
            for(int c=0;c<columnas;c++){
                if(valoresIntermedio[f][c]==9){
                    for(int f2=f-1;f2<=f+1;f2++){
                        for(int c2=c-1;c2<=c+1;c2++){
                            if(f2>=0 && f2<filas && c2>=0 && c2<columnas && valoresIntermedio[f2][c2]!=9)
                                valoresIntermedio[f2][c2]++;
                        }
                      }
                   }
                }
            }
        }
	
	public void verDemente(boolean valor){
        for(int f=0;f<filas;f++){
        for(int c=0;c<columnas;c++){
            visibleDemente[f][c]=valor;
        }
        }
    }
	
	public void pulsarBotonVasDemente(int f, int c){
            
        if(f>=0 && f<filas && c>=0 && c<columnas && visibleDemente[f][c]==false){
            if(visibleDemente[f][c]==false){
                visibleDemente[f][c]=true;
                if(valoresIntermedio[f][c]==9){
                    verDemente(true);
                    JOptionPane.showMessageDialog(null, "              PERDISTE" + " Numero de veces " + cantidad);
                    botonD.setIcon(ima[1]);}
            else if(visibleDemente[f][c]==true){
                casillasFaltantes++;
                if (casillasFaltantes == 480){
                    verDemente(true);
                    JOptionPane.showMessageDialog(null, "              GANASTE");
                    botonD.setIcon(ima[0]);
                    labelD.setText("");
                }
                labelD.setText(casillasFaltantes+"/480");
            }
            }
            if(valoresIntermedio[f][c]==0){
                pulsarBotonVasDemente(f, c-1);
                pulsarBotonVasDemente(f, c+1);
                pulsarBotonVasDemente(f-1, c);
                pulsarBotonVasDemente(f+1, c);
                
            }
        }
    }
	
	public void pulsarBotonDemente(int f, int c) {
            cantidad++;
        pulsarBotonVasDemente(f,c);
        visualizarMinasDemente();
    }
        
	
	public void eventosDemente(){
        for(int f=0;f<filas;f++){
        for(int c=0;c<columnas;c++){
            botonesDemente[f][c].addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    for(int f=0;f<filas;f++){
                    for(int c=0;c<columnas;c++){
                        if(e.getSource()==botonesDemente[f][c])
                            pulsarBotonDemente(f,c);
                    }
                    }
                }
                }
            );
        }
        }
    }
	
	public void visualizarMinasDemente(){
        for(int f=0;f<filas;f++){
        for(int c=0;c<columnas;c++){
         if(visibleDemente[f][c]==false){
           botonesDemente[f][c].setText("");
         }else if(visibleDemente[f][c]==true){
           if(valoresIntermedio[f][c]==0){
           botonesDemente[f][c].setIcon(imagenes[0]);
           }else if(valoresIntermedio[f][c]==1){
           botonesDemente[f][c].setIcon(imagenes[1]);
           }else if(valoresIntermedio[f][c]==2){
           botonesDemente[f][c].setIcon(imagenes[2]);
           }else if(valoresIntermedio[f][c]==3){
           botonesDemente[f][c].setIcon(imagenes[3]);
           }else if(valoresIntermedio[f][c]==4){
           botonesDemente[f][c].setIcon(imagenes[4]);
           }else if(valoresIntermedio[f][c]==5){
           botonesDemente[f][c].setIcon(imagenes[5]);
           }else if(valoresIntermedio[f][c]==6){
           botonesDemente[f][c].setIcon(imagenes[6]);
           }else if(valoresIntermedio[f][c]==7){
           botonesDemente[f][c].setIcon(imagenes[7]);
           }else if(valoresIntermedio[f][c]==8){
           botonesDemente[f][c].setIcon(imagenes[8]);
           }else if(valoresIntermedio[f][c]==9)
           botonesDemente[f][c].setIcon(imagenes[9]);
          }
        }
       }
   }
	
	public void quitarBotonesDemente(){
        for(int f1 = 0; f1<filas; f1++){
               for(int c1 = 0; c1<columnas; c1++){
                   this.remove(botonesDemente[f1][c1]);
               }
        }
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==botonD){
			botonD.setIcon(ima[2]);
            quitarBotonesDemente();
            this.setVisible(false);            
            labelD.setText("");
			nuevaPartidaDemente();
			this.setVisible(true);
		}		
	}
}

