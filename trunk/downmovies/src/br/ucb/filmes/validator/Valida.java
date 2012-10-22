package br.ucb.filmes.validator;




import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;

import br.ucb.filmes.format.Formata;

/**

 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class Valida {

	public static final int ALGORITMO_CPF  = 1;
	public static final int ALGORITMO_RG   = 2;
	public static final int ALGORITMO_CNPJ = 3;

	/**
	 * Método que valida o dígito verificador do CPF, retornando true se o digito estiver ok.
	 * @param xCPF String o número do CPF com mascaras do tipo ./- ou sem mascaras.
	 * @return boolean se o dígito verificador estiver ok retorna true se não estivar correto retorna false.
	 */
	public static boolean validaCpf( String xCPF ){

		try{
			
			long cpfL = Long.parseLong(xCPF);
			xCPF = Formata.Fill(String.valueOf(cpfL),11,'0',1);
			
			
			//Testa se o CPF é válido ou não
			int d1,d4,xx,nCount,resto,digito1,digito2;
			String Check;
			String aux = "";
			String Separadores = "/-.";
			d1 = 0; d4 = 0; xx = 1;
		
			for (nCount = 0; nCount < xCPF.length(); nCount++) {
			
				String s_aux = xCPF.substring(nCount, nCount+1);
	
				if (Separadores.indexOf(s_aux) == -1) {
					aux = aux + xCPF.substring(nCount, nCount+1);
				};
			
			}
		
			xCPF = "";
		
			if (aux.length() < 11){
			   int qtd = 11 - aux.length();
			   for (int i = 0; i < qtd; i++) {
					aux = "0" + aux; 
				}
			}
		
			xCPF = aux;
		
			if (xCPF.equals("00000000000") || 
				xCPF.equals("11111111111") ||
				xCPF.equals("22222222222") ||
				xCPF.equals("33333333333") ||
				xCPF.equals("44444444444") ||
				xCPF.equals("55555555555") ||
				xCPF.equals("66666666666") ||
				xCPF.equals("77777777777") ||
				xCPF.equals("88888888888") ||
				xCPF.equals("99999999999")) {
					return false;
				} else {
		
					for (nCount = 0; nCount < xCPF.length() -2; nCount++)
					//for (nCount = 0; nCount < aux.length(); nCount++) 
					{
						String s_aux = xCPF.substring(nCount, nCount+1);
						//String s_aux = aux.substring(nCount, nCount+1);
						//System.out.println(s_aux);
						if (Separadores.indexOf(s_aux) == -1) {
							d1 = d1 + ( 11 - xx ) * Integer.valueOf (s_aux).intValue();
							d4 = d4 + ( 12 - xx ) * Integer.valueOf (s_aux).intValue();
							xx++;
						};
					};
				
					resto = (d1 % 11);
				
					if (resto < 2)
					{
						digito1 = 0;
					}
					else
					{	
						digito1 = 11 - resto;
					}
		
					d4 = d4 + 2 * digito1;
					resto = (d4 % 11);
					if (resto < 2)
					{
						digito2 = 0;
					}
					else
					{
						digito2 = 11 - resto;
					}
		
					Check = String.valueOf(digito1) + String.valueOf(digito2);
		
					String s_aux2 = xCPF.substring (xCPF.length()-2, xCPF.length());
					//String s_aux2 = aux.substring (aux.length()-2, aux.length());
					//System.out.println(s_aux2);
					//System.out.println(Check);
		
					if (s_aux2.compareTo (Check) != 0)
					{
						return false;
					}
					return true;
				}
			}
		catch (Exception e)
		{
			return false;		
		}
	}
	
	/**
	 * Valida a existência da Unidade da Federação do Brasil
	 * @param pUF String: nome da UF, ex.: DF
	 * @return true se encontrou a UF na lista de UFs do Brasil, e False se não encontrou.
	 */
	public static boolean validaUfBrasil(String pUF){
		
		StringTokenizer estados = new
		StringTokenizer("AC@AL@AM@AP@BA@CE@DF@ES@GO@MA@MG@MS@MT@PA@PB@PE@PI@PR@RJ@RN@RO@RR@RS@SC@SE@SP@TO@","@");
		boolean enc = false; 
		while (estados.hasMoreTokens()) {
			if (pUF.equalsIgnoreCase(estados.nextToken()))
				enc = true;
		}
		return enc;
		
	}// fim do método validaUfBrasil

	
	/**
	 * Método que verifica se uma string pode ser convertido em um long
	 * @param n String
	 * @return Retorna true se a string pode ser convertida false se não pode ser convertida
	 */
	public static boolean isLong(String n){
		try{
			long num = Long.parseLong(n);
			return true;
		}catch (NumberFormatException e) {
			return false;
		}catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * Método que verifica se uma string pode ser convertido em um int
	 * @param n String
	 * @return Retorna true se a string pode ser convertida false se não pode ser convertida
	 */
	public static boolean isInt(String n){
		try{
			int num = Integer.parseInt(n);
			return true;
		}catch (NumberFormatException e) {
			return false;
		}catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * Método que verifica se uma string pode ser convertido em um double
	 * @param n String
	 * @return Retorna true se a string pode ser convertida false se não pode ser convertida
	 */
	public static boolean isDouble(String n){
		try{
			double num = Double.parseDouble(n);
			return true;
		}catch (NumberFormatException e) {
			return false;
		}catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * Método que verifica se uma string pode ser convertido em um float
	 * @param n String
	 * @return Retorna true se a string pode ser convertida false se não pode ser convertida
	 */
	public static boolean isFloat(String n){
		try{
			float num = Float.parseFloat(n);
			return true;
		}catch (NumberFormatException e) {
			return false;
		}catch (Exception e) {
			return false;
		}
	}
	
	

	/**
	* Método que valida o dígito verificador do CNPJ, retornando true se o digito estiver ok.
	* @param xCPF String o número do CNPJ com mascaras do tipo ./- ou sem mascaras.
	* @return boolean se o dígito verificador estiver ok retorna true se não estivar correto retorna false.
	*/
	public static boolean validaCnpj(String xCGC) 
	{
		try
		{
		
			//Testa se o CGC é válido ou não
			int d1,d4,xx,nCount,fator,resto,digito1,digito2;
			String Check, s_aux;
			String aux = "";
			String Separadores = "/-.";
			d1 = 0;
			d4 = 0;
			xx = 0;
		
			for (nCount = 0; nCount < xCGC.length(); nCount++) {
			
				s_aux = xCGC.substring(nCount, nCount+1);
	
				if (Separadores.indexOf(s_aux) == -1) {
					aux = aux + xCGC.substring(nCount, nCount+1);
				};
			}
		
			xCGC = "";
		
			if (aux.length() < 14){
			   int qtd = 14 - aux.length();
			   for (int i = 0; i < qtd; i++) {
					aux = "0" + aux; 
				}
			}
		
			xCGC = aux;
		
			if (xCGC.equals("00000000000000") || 
				xCGC.equals("11111111111111") ||
				xCGC.equals("22222222222222") ||
				xCGC.equals("33333333333333") ||
				xCGC.equals("44444444444444") ||
				xCGC.equals("55555555555555") ||
				xCGC.equals("66666666666666") ||
				xCGC.equals("77777777777777") ||
				xCGC.equals("88888888888888") ||
				xCGC.equals("99999999999999")) {
					return false;
			} else {
		
				for (nCount = 0; nCount < xCGC.length()-2; nCount++) 
				{
					s_aux = xCGC.substring (nCount, nCount+1);
					if (Separadores.indexOf(s_aux) == -1) 
					{
						if (xx < 4)
						{
							fator = 5 - xx;
						}
						else
						{
							fator = 13 - xx;
						}
	         	
						d1 = d1 + Integer.valueOf (s_aux).intValue() * fator;
						if (xx < 5)
						{
							fator = 6 - xx;
						}
						else
						{
							fator = 14 - xx;
						}
						d4 += Integer.valueOf (s_aux).intValue() * fator;
						xx++;
					};
				}
				resto = (d1 % 11);
				if (resto < 2)
				{
					digito1 = 0;
				}
				else
				{
					digito1 = 11 - resto;
				}
	
				d4 = d4 + 2 * digito1;
				resto = (d4 % 11);
				if (resto < 2)
				{
					digito2 = 0;
				}
				else
				{
					digito2 = 11 - resto;
				}
	
				Check = String.valueOf(digito1) + String.valueOf(digito2);
				//System.out.println (Check);
				//System.out.println (xCGC.substring(xCGC.length()-2, xCGC.length() ));
	   	
				if (Check.compareTo(xCGC.substring(xCGC.length()-2, xCGC.length() )) !=0)
				{
					return false;
				}
	
				return true;
			}
		}
	
		catch (Exception e)
		{
			return false;		
		}
		
	}


	/**
	 * Verifica se a string é nula se for retorna um espaço em branco.
	 * @param pValor
	 * @return
	 */
	public static String isNullString(String pValor){
		if (pValor == null)
			return " ";
		else
			return pValor;
	}
	
	
	/**
	 * Retorna o character separador dos dias, meses e anos da data.
	 * @param value A String contendo a data.
	 * @return O character.
	 * @since 1.1
	 */
	public static String getDateSeparator(String value) {
		char tmp[] = value.toCharArray();

		for (int i=0; i < tmp.length; i++) {
			if (!Character.isDigit(tmp[i]))
				return Character.toString(tmp[i]);
			if(i==7)
				return "/";
		}

		return null;
	}

	/**
	 * Retorna o formato correto em que a data se apresenta, indepêndente da forma
	 * em que esta estiver.<br>
	 * Exemplo: Quando passada a data: "2/05/2002", o retorno será "d/MM/yyyy".
	 * @param value A String contendo a data.
	 * @return A String contendo o formato da data.
	 * @since 1.1
	 */
	public static String getFormatDate(String value) {
		String sep = getDateSeparator(value); // pega o separador da data
        
		if(sep == null){
			return null;
		}
        
		if(! sep.equals("/")){
			return null;
		}
        
		String[] sepData = value.split(sep); // separa dia, mês e ano

		if (sepData.length == 3) {
			String format = new String();

			for (int i = 0; i < sepData[0].length(); i++)
				format += "d";

			format += sep;

			for (int i = 0; i < sepData[1].length(); i++)
				format += "M";

			format += sep;

			for (int i = 0; i < sepData[2].length(); i++)
				format += "y";

			return format;
		}
		else
			return null;
	}


	/**
	 * Determina se uma especificada String é uma data.
	 * @param value A String contendo a data.
	 * @return true se for uma data, false caso contrário.
	 * @see IsFormattedDate(String value, String format)
	 * @since 1.0
	 */
	public static boolean IsDate(String value) {
		value = value.replace('.', '/');      // troca "." por "/"
		String format = getFormatDate(value); // recupera o formato da data

        
		if (format == null)
			return false;
		else
			return IsFormattedDate(value, format);
	}

	/**
	 * Determina se a data está dentro da formatação.
	 * @param value A String contendo a data.
	 * @param format A String contendo formato da data.
	 * @return Retorna true se a data estiver no formato, false caso contrário.
	 * @deprecated
	 * @see IsFormattedDate(String date, String format)
	 * @since 1.0
	 * @todo: Remover este método.
	 */
	public static boolean IsDate(String value, String format) {
		return IsFormattedDate(value, format);
	}

	/**
	 * Determina se a data está dentro da formatação.
	 * @param value A String contendo a data.
	 * @param format A String contendo formato da data.
	 * @return Retorna true se a data estiver no formato, false caso contrário.
	 * @since 1.1
	 */
	public static boolean IsFormattedDate(String value, String format) {
    	
		try {
			if(! format.equals("dd/MM/yyyy")){
				return false;
			}
        	
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			Date dt;
			dt = sdf.parse(value);
            
			String dts = sdf.format(dt);
			if (! dts.equals(value))
				return false;
		}
		catch (ParseException e) {
			return false;
		}
        
		return true;

	}
	
	/**
	 * Valida se o email está correto
	 * @param mail String: e-mai, ex.: emailteste@teste.com.br
	 * @return true se email estiver correto.
	 */


		public static boolean validaEmail(String mail){
			mail=mail.toLowerCase();
			if (mail.indexOf("@")<1 || mail.indexOf(".")<1)
			{
					   return false;
			}else
			{
					   if(mail.indexOf("@")+1 == mail.indexOf("."))
					   {
								   return false;
					   } 
			}
			return true;
		}

		//          fim do método validaEmail


		/**
		 * Método que valida o dígito verificador do CEI(Cadastro específico do INSS).
		 * @param numeroCei
		 * @return Retorna true se o dígito estiver OK.
		 */
		public static boolean validaCei(String numeroCei){
		    
		    String nroCei = Formata.RetiraCaracterEspecialSemEspaco(numeroCei.trim());
		    if(nroCei.length() < 12)
		        return false;
		    
		    String cNumero  = nroCei.substring(0,11);                                                                
		    String nDv      = nroCei.substring(11,12);                                                                  
		    int    nSoma    = 0;
		    String nSoma2;   
		    String matriz   = "74185216374";     
		    String nUnidade;
		    int    nTam     = 0;
		    int    nAux;
		    
		    for (int i = 0;i <= 10; i++){                                                                                                  
		        nSoma += Integer.parseInt(cNumero.substring(i,i+1)) * 
		        Integer.parseInt(matriz.substring(i,i+1));                             
		    }
		    
		    nSoma2 = String.valueOf(nSoma);                                                                                
		    nTam   = nSoma2.length();                                                                               
		    nAux   = (Integer.parseInt(nSoma2.substring(0,nTam-1)) * 1) + 
		    (Integer.parseInt(nSoma2.substring(nTam-1,nTam)) * 1);                   
		    String nAux2 = String.valueOf(nAux);                                                                                  
		    nTam   = nAux2.length();                                                                                
		    if (nTam == 1){                                                                                                  
		        nUnidade = nAux2.substring(0,1);                                                               
		    }                                                                                                  
		    else{                                                                                                  
		        nUnidade = nAux2.substring(1,2);                                                               
		    }                                                                                                  
		    
		    int nDvCalc = 10 - (Integer.parseInt(nUnidade) * 1);
		    if (nDvCalc > 9) {
		        nDvCalc = 0;
		    }
		    
		    if (String.valueOf(nDvCalc).equals(nDv)){                                                                                                  
		        return true;                                                                                         
		    }                                                                                                  
		    else{                                                                                                  
		        return false;                                                                                         
		    }                                                                                                  
		}


	
	public static void main(String[] args) {
		System.out.print(Valida.validaCpf("00095066918515"));
	}
	
} //fim da classe
	


	