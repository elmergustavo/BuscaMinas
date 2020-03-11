/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buscaminas;



import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;



public class BuscaMinas extends JFrame implements ActionListener{

	principiante nivelPrincipiante = new principiante();
	Intermedio nivelIntermedio = new Intermedio();
	Experto nivelDemente = new Experto();
	
    private JMenuBar barra;
    
    private JMenuItem principiante, intermedio, experto;    
    private boolean prin=true, inter=false, demen=false;  
    
    private String[] archi = {"/libreria/gano.png", "/libreria/perdio.png", "/libreria/nueva.png"};
	  
    private ImageIcon[] ima = new ImageIcon[3]; 
    
    public BuscaMinas(){    	
    	for(int i=0;i<3;i++){
            ima[i] = new ImageIcon(getClass().getResource(archi[i]));
        }
    	this.add(nivelPrincipiante);
    	this.setLayout(null);
    	this.setTitle("BuscaMinas");        
        barra = new JMenuBar();
       


        principiante = new JMenuItem("Principiante");
        intermedio = new JMenuItem("Intermedio");
        experto = new JMenuItem("Experto");
        barra.add(principiante);
        barra.add(intermedio);
        barra.add(experto);
      
        this.setJMenuBar(barra);
        this.principiante.addActionListener(this); //cuando realice una accion
        this.intermedio.addActionListener(this);
        this.experto.addActionListener(this);

        //propiedades del frame
        setSize(500, 500);        
        setLocationRelativeTo(null); //mostrar en el centro de la pantalla
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    // cerrar ventana
        setIconImage(new ImageIcon(getClass().getResource("/libreria/icono.jpg")).getImage());
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {        
        if(e.getSource()==experto){
        	nivelDemente.botonD.setIcon(ima[2]);
        	nivelDemente.quitarBotonesDemente();
        	nivelDemente.setVisible(false);            
        	nivelDemente.labelD.setText("");
        	nivelDemente.nuevaPartidaDemente();
        	nivelDemente.setVisible(true);
        	if(prin){        		
        		this.remove(nivelPrincipiante);
        		this.add(nivelDemente);
        		prin=false;        		
        		demen=true;
        	}else if(inter){  
        		this.remove(nivelIntermedio);
        		this.add(nivelDemente);
        		inter=false;
        		demen=true;  
        	}
            setSize(650, 695);            
            setLocationRelativeTo(null);      
        }else if(e.getSource()==intermedio){ 
        	nivelIntermedio.botonP.setIcon(ima[2]);
        	nivelIntermedio.quitarBotonesPrincipiante();
        	nivelIntermedio.setVisible(false);            
        	nivelIntermedio.labelP.setText("");
        	nivelIntermedio.nuevaPartidaIntermedio();
        	nivelIntermedio.setVisible(true);
        	if(prin){
        		this.remove(nivelPrincipiante);
        		this.add(nivelIntermedio);
        		prin=false;
        		inter=true; 
        	}else if(demen){
        		this.remove(nivelDemente);
        		this.add(nivelIntermedio);
        		inter=true;
        		demen=false;  
        	}
            setSize(406, 495);
            setLocationRelativeTo(null);  
        }else if(e.getSource()==principiante){        	
        	nivelPrincipiante.botonP.setIcon(ima[2]);
        	nivelPrincipiante.quitarBotonesPrincipiante();
        	nivelPrincipiante.setVisible(false);            
            nivelPrincipiante.labelP.setText("");
            nivelPrincipiante.nuevaPartidaPrincipiante();
            nivelPrincipiante.setVisible(true);
        	if(inter){
        		this.remove(nivelIntermedio);
        		this.add(nivelPrincipiante);
        		inter=false;
        		prin=true; 
        	}else if(demen){ 
        		this.remove(nivelDemente);
        		this.add(nivelPrincipiante);
        		prin=true;
        		demen=false;  
        	}	               
            setSize(500, 500);
            setLocationRelativeTo(null);   
        }
    }    

    public static void main(String[] args) {               
    	BuscaMinas i = new BuscaMinas();        
    }
}

