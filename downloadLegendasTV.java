import java.io.*;
import java.net.*;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class downloadLegendasTV{
	
	public static void exemplo(){
		JFrame frame = new JFrame();
		ImageIcon icone = new ImageIcon("exemplo.jpg");
		JLabel imgLabel = new JLabel(icone);
		frame.add(imgLabel);
		frame.setTitle("EXEMPLO");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
	
	
	public static boolean checaLink(String urlString, int proxi) throws MalformedURLException, IOException {

		URL u = new URL(urlString); 
		HttpURLConnection huc =  (HttpURLConnection)  u.openConnection(); 
		huc.setRequestMethod("HEAD");
		huc.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 6.0; en-US; rv:1.9.1.2) Gecko/20090729 Firefox/3.5.2 (.NET CLR 3.5.30729)");
		huc.connect(); 
		if(huc.getResponseCode() == HttpURLConnection.HTTP_OK){
			System.out.print("Link encontrado! Baixando...");
			return true;
		}else{
			System.out.println((proxi+1) + " de 60");
			return false;
		}
}

	public static void tenta(String hue, int prox){
		
		 System.out.println("Testando o link: " + hue);
		 boolean wot=false;
		 String nomeArquivo = "legenda.rar";
		 try{
			 wot = checaLink(hue,prox);
			 }catch(IOException vish){
			System.out.println(vish);
			System.exit(1);
		 }
			 if(wot){
				 try{
				 URL link = new URL(hue);
				 InputStream in = new BufferedInputStream(link.openStream());
				 ByteArrayOutputStream out = new ByteArrayOutputStream();
				 byte[] buf = new byte[1024];
				 int n = 0;
				 while (-1!=(n=in.read(buf)))
				 {
					out.write(buf, 0, n);
					System.out.print(".");
				 }
				 out.close();
				 in.close();
				 byte[] response = out.toByteArray();
		 
				 FileOutputStream fos = new FileOutputStream(nomeArquivo);
				 fos.write(response);
				 fos.close();
				 System.out.println("\nLegenda baixada. Obrigado");
				 System.exit(0);
				}catch(IOException exce){
					System.out.println(exce);
				}
			}
		
	}

    public static void main(String[] args) {

		String ano, mes, dia, hora, minuto, data;
        String[] testeLegenda = new String[60];
        
		exemplo();
        hora = JOptionPane.showInputDialog("Insira a hora em que a legenda foi postada");
        minuto = JOptionPane.showInputDialog("Insira o minuto em que a legenda foi postada");        
        dia = JOptionPane.showInputDialog("Insira o dia em que a legenda foi postada");
        mes = JOptionPane.showInputDialog("Insira o mes em que a legenda foi postada");
        ano = JOptionPane.showInputDialog("Insira o ano em que a legenda foi postada");

        System.out.println("Buscando primeira legenda com essas caracteristicas...\n\n[" + hora + ":" + minuto + " - " + dia + "/" + mes + "/" + ano + "]");

		        
        for(int i=0;i<10;i++){
			data = ano+mes+dia+hora+minuto+0+i;
			testeLegenda[i] = "http://f.legendas.tv/l/legendas_tv_"+data+".rar";
		}
		for(int i=10;i<60;i++){
			data = ano+mes+dia+hora+minuto+i;
			testeLegenda[i] = "http://f.legendas.tv/l/legendas_tv_"+data+".rar";
		}
   
	   for(int i=0;i<60;i++){
			tenta(testeLegenda[i],i);
		}


        


    }
}
