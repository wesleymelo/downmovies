package br.ucb.filmes.format;



import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.StringTokenizer;

public class Formata{
    private static final BigDecimal BIG1 = BigDecimal.valueOf(1);

    /**
	 * Método para abreviar nomes
	 * @param pNomeCompleto String nome completo.
	 * @param pTamanhoLimite int tamanho maximo que o nome pode atingir.
	 * @return
	 */
	public static String AbreviaNome(String pNomeCompleto, int pTamanhoLimite){
	String vNome;
	String sNomeAux;
	String StrNomeAbrev;
	int    nPos;

	StrNomeAbrev = pNomeCompleto.toUpperCase();

	StrNomeAbrev = StrNomeAbrev.replaceAll(" E/OU", "");
	StrNomeAbrev = StrNomeAbrev.replaceAll(" E-OU", "");
	StrNomeAbrev = StrNomeAbrev.replaceAll(" E OU", "");
	StrNomeAbrev = StrNomeAbrev.replaceAll(" OU ", "");

	// Se o nome tiver preenchido
	if(! pNomeCompleto.equals(null)){

		// Obtendo o nome completo
		vNome = pNomeCompleto.toUpperCase().trim();

		// Se o nome completo ja for menor ou igual a 23 bytes, o nome no cartao sera igual
		if (vNome.length() <= pTamanhoLimite){
			StrNomeAbrev = vNome;
		}else{
			// Se o nome completo for maior que 23 bytes:
			// Retirando as preposições
			nPos = 0;
			vNome = vNome.replaceAll(" DE ", " ");
			vNome = vNome.replaceAll(" DOS ", " ");
			vNome = vNome.replaceAll(" DAS ", " ");
			vNome = vNome.replaceAll(" DO ", " ");
			vNome = vNome.replaceAll(" DA ", " ");
			vNome = vNome.replaceAll(" E ", " ");
			vNome = vNome.replace('.', ' ');
			vNome = vNome.replace('-', ' ');
			vNome = vNome.replaceAll(" OU ", " ");

			if (vNome.length() <= pTamanhoLimite){
				StrNomeAbrev = vNome;
			} else {
				// Se continua com mais de 23 bytes, vamos abreviar os nome do meio
	        	// pulando para o primeiro byte em branco
				StrNomeAbrev = vNome;
				nPos = vNome.indexOf(" ",0);

				// Separando o primeiro Nome
				StrNomeAbrev = vNome.substring(0, nPos);

				//Eliminando os brancos ate o segundo nome
				while ((vNome.substring(nPos,nPos+1).equals(" ")) & (nPos < vNome.length())) {
					nPos++;
				}

				// Abreviando o segundo nome
				StrNomeAbrev = StrNomeAbrev + " " + vNome.substring(nPos, nPos + 1).trim();
				nPos = vNome.indexOf(" ",nPos + 1);
				sNomeAux = StrNomeAbrev;
				StrNomeAbrev = StrNomeAbrev + vNome.substring(nPos);

				if (StrNomeAbrev.length() > pTamanhoLimite){

					// Se continuar com mais de bytes que o tamanhoLimite, o processo de abreviação continua
	            	//Eliminando os brancos
					while ((vNome.substring(nPos,nPos + 1).equals(" ")) & (nPos < vNome.length())) {
						nPos++;
					}

					//Abreviando o terceiro nome

					sNomeAux = sNomeAux + " " + vNome.substring(nPos, nPos + 1);
					nPos = vNome.indexOf(" ", nPos +1);
					if (nPos > 0){
						StrNomeAbrev = sNomeAux + vNome.substring(nPos);
					}else{
						StrNomeAbrev = sNomeAux;
					}

					if (StrNomeAbrev.length() > pTamanhoLimite){
						while((vNome.substring(nPos,nPos+1).equals(" ")) & (nPos < vNome.length())){
							nPos++;
						}

						//Abreviando o quarto nome
						sNomeAux = sNomeAux + " " + vNome.substring(nPos,nPos+1);
						nPos = vNome.indexOf(" ", nPos +1);

						if (nPos > 0){
							StrNomeAbrev = sNomeAux + vNome.substring(nPos);
						}else{
							StrNomeAbrev = sNomeAux;
						}

						if (StrNomeAbrev.length() > pTamanhoLimite){
							// Obtendo o Primeiro nome e o ultimo
							sNomeAux = StrNomeAbrev;
							nPos = sNomeAux.indexOf(" ",0);
							StrNomeAbrev = sNomeAux.substring(0,nPos);
							StrNomeAbrev = StrNomeAbrev + " " + sNomeAux.substring(((sNomeAux.lastIndexOf(" ")) + 1));

							if (StrNomeAbrev.length() > pTamanhoLimite){
								nPos = StrNomeAbrev.indexOf(" ",0);
								StrNomeAbrev = StrNomeAbrev.substring(0, nPos - 1);
							}
						}
					}
				}
			}
		}
	}

	return StrNomeAbrev;

	}//fim do método
	/**
	 * Formata uma String com um tamanho fixo de bytes. </br>
	 * @param pValor   String: string a ser formatada. </br>
	 * @param pTamanho int   : quantidade final de bytes da string. </br>
	 * @param pMascara char  : caracter com o qual será completada a string. </br>
	 * @param pPosicao char  : 0 alinhado a esquerda, 1 alinhado a direita. </br>
	 * @return String formatada.
	 */
	public static String Fill(String pValor, int pTamanho, char pMascara, int pPosicao){

		String aux = pValor;

		if (pValor.length() == pTamanho) {
			return aux;
		}else{
			if (pValor.length() < pTamanho){

				for (int i = 0; i < (pTamanho - pValor.length()); i++) {
					if (pPosicao == 0){
						aux = aux + pMascara;
					} else {
						aux = pMascara + aux;
					}
				}
				return aux;

			} else {
				return aux.substring(0,pTamanho);
			}
		}
	}

	public static String RetiraAcentos(String pCaracter){
		String cAcento = "ÁÀÂÃÄÅÇÈÉÊËÌÍÎÏÒÓÔÕÖÙÚÛÜÝáàâãäåçèéêëìíîïòóôõöùúûüý";
		String sAcento = "AAAAAACEEEEIIIIOOOOOUUUUYaaaaaaceeeeiiiiooooouuuuy";
		String aux = "";
		aux = pCaracter;
		if (pCaracter.length() > 0){
			for (int i = 0; i < cAcento.length(); i++) {
				aux = aux.replace(cAcento.substring(i,i+1).charAt(0),sAcento.substring(i,i+1).charAt(0));
			}
		}
		return aux;
	}

	public static String RetiraCaracterEspecial(String pCaracter){
		String aux = "";

		if (pCaracter.length() > 0){
			aux = pCaracter;
			for (int i = 0; i < aux.length(); i++) {

				int nAscii = (int) aux.substring(i,i+1).charAt(0);

				if (! (((nAscii >= 48) & (nAscii <= 57)) |
				      ((nAscii >= 65) & (nAscii <=90)) |
				      ((nAscii >= 97) & (nAscii <= 122)))){
				      	aux = aux.replace((char) nAscii, ' ');
				}
				nAscii = 0;
			}

			return aux;
		}else{
		  return aux;
		}
	}

	public static String RetiraCaracterNaoNumericos(String pCaracter){
		StringBuffer aux= new StringBuffer(pCaracter);

		if (pCaracter.length() > 0){

			for (int i = 0; i < aux.length(); i++) {

				int nAscii = (int) aux.charAt(i);

				if (! ((nAscii >= 48) & (nAscii <= 57)) ) {
						aux.deleteCharAt(i);
				}

			}


			return aux.toString();
		}else{
		  return aux.toString();
		}
	}


	public static String RetiraCaracterEspecialSemEspaco(String pCaracter){
		StringBuffer aux= new StringBuffer(pCaracter);

		if (pCaracter.length() > 0){

			for (int i = 0; i < aux.length(); i++) {

				int nAscii = (int) aux.charAt(i);

				if (! (((nAscii >= 48) & (nAscii <= 57)) |
					  ((nAscii >= 65) & (nAscii <=90)) |
					  ((nAscii >= 97) & (nAscii <= 122)))){
						aux.deleteCharAt(i);
				}

			}


			return aux.toString();
		}else{
		  return aux.toString();
		}
	}

    /**
     * Retira as aspas Simples de uma String
     * @param pCaracter
     * @return
     */
	public static String RetiraAspasSimples(String pCaracter){
			String aux = "";

			if (pCaracter.length() > 0){
				aux = pCaracter;
				for (int i = 0; i < aux.length(); i++) {

					int nAscii = (int) aux.substring(i,i+1).charAt(0);

					if (nAscii == 39)
						aux = aux.replace((char) nAscii, ' ');

					nAscii = 0;
				}

				return aux;
			}else{
			  return aux;
			}
		}

	/**
	 * Retira as aspas duplas de uma String
	 * @param pCaracter
	 * @return
	 */
	public static String RetiraAspasDuplas(String pCaracter){
			String aux = "";

			if (pCaracter.length() > 0){
				aux = pCaracter;
				for (int i = 0; i < aux.length(); i++) {

					int nAscii = (int) aux.substring(i,i+1).charAt(0);

					if (nAscii == 34)
						aux = aux.replace((char) nAscii, ' ');

					nAscii = 0;
				}

				return aux;
			}else{
			  return aux;
			}
		}

	/**
	 * Retira o caracter indicado da String
	 * @param pString
	 * @param pCaracter
	 * @return
	 */
	public static String RetiraCaracter(String pString,char pCaracter){
			String aux = "";

			if (pString.length() > 0){
				aux = pString;
				for (int i = 0; i < aux.length(); i++) {

					int nAscii = (int) aux.substring(i,i+1).charAt(0);

					if (nAscii == (int) pCaracter)
						aux = aux.replace((char) nAscii, ' ');

					nAscii = 0;
				}

				return aux;
			}else{
			  return aux;
			}
		}


    /**
     * Formata o Valor Numérico (Double) em uma String (999.999.999,99)
     * @param val
     * @return
     */
    protected static String convFormat(double val) {
          NumberFormat nf = NumberFormat.getNumberInstance(new Locale("de", "DE"));
          DecimalFormat df = (DecimalFormat)nf;
          df.applyPattern("###,##0.00");
          String output = df.format(val);
          return output;
    }




    /**
     * Formata o Valor Numérico (Long) em uma String ("000000000")
     * @param val
     * @param formato
     * @return
     */
	public static String getFormatLong(long val,String formato) {
		  NumberFormat nf = NumberFormat.getNumberInstance(new Locale("de", "DE"));
		  DecimalFormat df = (DecimalFormat)nf;
		  df.applyPattern(formato);
		  String output = df.format(val);
		  return output;
	}

    /**
     * Desformata um valor String Moeda, e retorna uma String na forma double
     * @param val
     * @return
     */
	public static String getDesformatDouble(String val) {
		NumberFormat nf = NumberFormat.getNumberInstance(new Locale("de", "DE"));
		DecimalFormat df = (DecimalFormat)nf;
		df.applyPattern("###,##0.00");
		try {

		Number nb = df.parse(val.trim());
		return nb.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

    /**
     * Formata o Valor Numérico (Double) em uma String ("000000000")
     * @param val
     * @param formato
     * @return
     */
	public static String getFormatDouble(double  val,String formato) {
		  NumberFormat nf = NumberFormat.getNumberInstance(new Locale("de", "DE"));
		  DecimalFormat df = (DecimalFormat)nf;
		  df.applyPattern(formato);
		  String output = df.format(val);
		  return output;
	}


    /**
     * Retorna String Formatada a partir da entrada de um tipo primitivo double
     * @param valor
     * @return
     */
    public static String getMoeda(double valor) {
        return convFormat(valor);
    }

	/**
	 * Retorna String Formatada a partir da entrada de um tipo classe Double
	 * @param valor
	 * @return
	 */
    public static String getMoeda(Double valor) {
        if (valor != null) {
            return convFormat(valor.doubleValue());
        } else {
            return " ";
        }
    }

	/**
	 * Retorna String Data Formatada a partir da entrada de um tipo classe Date
	 * @param data
	 * @param formato
	 * @return
	 */
    public static String getDataS(java.util.Date data, String formato) {
        if (data != null) {
            SimpleDateFormat formatter;

            formatter = new SimpleDateFormat(formato);
            return formatter.format(data);
        } else {
            return " ";
        }
    }

	/**
	 * Retorna Date Formatada a partir da entrada de uma String
	 * @param data
	 * @param formato
	 * @return
	 */
    public static Date getDataD(String data, String formato){
        if (data != null) {
            try {
                SimpleDateFormat sdfInput =
                   new SimpleDateFormat( formato );
                Date dataRet =  sdfInput.parse( data );
                return  dataRet;
            } catch (Exception e) {
                return null;
            }
        } else {
            return null;
        }
    }

	/**
	 * Trunca uma data.
	 * @param dt
	 * @return
	 */
    public static Date trunc(java.util.Date dt){
        if (dt != null) {
            try {
                return java.sql.Date.valueOf( getDataS(dt,"yyyy-MM-dd") );
            } catch (Exception e) {
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * Formata CNPJ
     * @param cnpj
     * @return
     */
	public static String formataCNPJ(String cnpj) {
	    try {
			String aux = new String(Formata.getFormatLong(Long.parseLong(cnpj),"00000000000000"));
			String aux2 = aux.substring(0,2) + "." +
			              aux.substring(2,5) + "." +
						  aux.substring(5,8) + "/" +
						  aux.substring(8,12) + "-" +
						  aux.substring(12);

	    	return aux2;
	    } catch (Exception e ) {
	    	return null;
	    }
	}

	/**
     * Formata CEI
     * @param cei
     * @return
     */
	public static String formataCEI(String cei) {
	    try {
			String aux = new String(Formata.getFormatLong(Long.parseLong(cei),"000000000000"));
			String aux2 = aux.substring(0,2) + "." +
			              aux.substring(2,5) + "." +
						  aux.substring(5,10) + "/" +
						  aux.substring(10,12);

	    	return aux2;
	    } catch (Exception e ) {
	    	return null;
	    }
	}
	
	/**
	 * Formata CPF
	 * @param cpf
	 * @return
	 */
	public static String formataCPF(String cpf) {
		try {
			String aux = new String(Formata.getFormatLong(Long.parseLong(cpf),"00000000000"));
			String aux2 = aux.substring(0,3) + "." +
						  aux.substring(3,6) + "." +
						  aux.substring(6,9) + "-" +
						  aux.substring(9);

			return aux2;
		} catch (Exception e ) {
			return null;
		}
	}

	/**
	 * Formata CEP
	 * @param String cep
	 * @return
	 */
	public static String formataCEP(String cep) {
		try {
			String aux = new String(Formata.getFormatLong(Long.parseLong(cep),"00000000"));
			String aux2 = aux.substring(0,2) + "." +
						  aux.substring(2,5) + "-" +
						  aux.substring(5);

			return aux2;
		} catch (Exception e ) {
			return null;
		}
	}

	/**
	 * Retorna um String para ser usada no Oracle TO_DATE(dt 23:59:59,'dd/mm/yyyy hh24:mi:ss'), com a ultima hora do dia
	 * @param  Data
	 * @return
	 */
    public static String getUltHoraDataOracleS(java.util.Date dt ){
		if (dt != null) {

			try {

				return "TO_Date('" + getDataS(dt,"dd/MM/yyyy") + " 23:59:59' , 'DD/MM/YYYY HH24:MI:SS')" ;
			} catch (Exception e) {
				return null;
			}

		} else {
			return null;
		}
    }


	/**
	 * Retorna um String para ser usada no Oracle TO_DATE(dt 00:00:01,'dd/mm/yyyy hh24:mi:ss'), com a primeira hora do dia
	 * @param  Data
	 * @return
	 */
	public static String getPriHoraDataOracleS(java.util.Date dt ){
		if (dt != null) {

			try {

				return "TO_Date('" + getDataS(dt,"dd/MM/yyyy") + " 00:00:00' , 'DD/MM/YYYY HH24:MI:SS')" ;
			} catch (Exception e) {
				return null;
			}

		} else {
			return null;
		}
	}

	/**
	 * Retorna um String para ser usada no Insert ou Update do Oracle TO_DATE(dt ,'dd/mm/yyyy hh24:mi:ss')
	 * @param Data
	 * @return
	 */
	public static String getToDateOracleS(java.util.Date dt ){
		if (dt != null) {

			try {

				return "TO_Date('" + getDataS(dt,"dd/MM/yyyy HH:mm:ss") + "','DD/MM/YYYY HH24:MI:SS')" ;
			} catch (Exception e) {
				return null;
			}

		} else {
			return null;
		}
	}

	/**
	 * Retorna um String para ser usada no Insert ou Update do Oracle TO_DATE(dt ,formato), no formato especificado por "formato".
	 * @param dt
	 * @param formato
	 * @return
	 */
	public static String getToDateOracleS(java.util.Date dt,String formato ){
		if (dt != null) {

			try {

				return "TO_Date('" + getDataS(dt,formato) + "','" + formato + "')" ;
			} catch (Exception e) {
				return null;
			}

		} else {
			return null;
		}
	}


    public static String addAspas(String p){
    	return ("'" + p + "'");
    }

	public static String addAspas(long p){
		return ("'" + p + "'");
	}

	public static String addAspas(int p){
		return ("'" + p + "'");
	}

	public static String addVirg(String p){
		return (p + ",");
	}

	public static String addVirg(long p){
		return (p + ",");
	}

	public static String addVirg(int p){
		return (p + ",");
	}

	public static String addVirg(double p){
			return (p + ",");
	}

	/**
	 * Calcula uma data que seja o ultimo dia do mes
	 * @param dt
	 * @return A data do ultimo dia do mes
	 */
	public static java.util.Date getUltDiaMesS(java.util.Date dt){
		Calendar cl = Calendar.getInstance();
		cl.setTime(dt);
		cl.add(Calendar.MONDAY,1);
		cl.add(Calendar.DAY_OF_MONTH, - cl.get(Calendar.DAY_OF_MONTH));

		return cl.getTime();

	}


	/**
	 * Extrai o nome do arquivo de uma string contendo o caminho completo do arquivo:
	 * Ex: \\dir1\dir3\dir3\nome do arquivo.txt retorna nome do arquivo.txt
	 * @param pNomeArquivo
	 * @return
	 */
	public static String extrairNomeArquivo(String pNomeArquivo){
		String result = "";
		StringTokenizer nomePathArquivo = new StringTokenizer(pNomeArquivo,"\\");
		while (nomePathArquivo.hasMoreTokens())
			result = nomePathArquivo.nextToken();

		return result;
	}

	public static String extrairExtencaoArquivo(String pNomeArquivo){
		int idx = pNomeArquivo.lastIndexOf('.');
		if (idx > 0)
			return pNomeArquivo.substring(0,idx);
		else
			return pNomeArquivo;		
	}

	/**
	 * Extrai o nome do diretorio de uma string contendo o caminho completo do arquivo:
	 * Ex: \\dir1\dir3\dir3\nome do arquivo.txt retorna nome do "\\dir1\dir3\dir3\"
	 * @param pNomeCompletoArquivo
	 * @return
	 */
	public static String extrairNomeDiretorio(String pNomeCompletoArquivo){
		return pNomeCompletoArquivo.substring(0, pNomeCompletoArquivo.lastIndexOf("\\")+1);
	}

	//	Método das Unidades
	 private  static  String unidades(String num){
		  String  str;
		  String vet_unidades[] = {"Um", "Dois", "Três", "Quatro", "Cinco", "Seis", "Sete", "Oito", "Nove", "Dez", "Onze", "Doze", "Treze", "Quatorze", "Quinze", "Dezesseis", "Dezessete", "Dezoito", "Dezenove"};
		  int aux = Integer.parseInt(num.substring(num.length()-2));
		  int aux2 = Integer.parseInt(num.substring(num.length()-1));
		  if (aux < 20) {
			   if (aux > 0) {
				str = vet_unidades[aux-1];
			   } else {
				str = "";
			   }
		  } else {
			   str = vet_unidades[aux2-1];
		  }
		  return str;
	 }


	 private  static   String dezenas(String num){
		  StringBuffer str = new StringBuffer();
		  String vet_dezenas[] = {"", "Vinte", "Trinta", "Quarenta", "Cinquenta", "Sessenta", "Setenta", "Oitenta", "Noventa"};
		  int aux = Integer.parseInt(num.substring(1,2));
		  int aux2 = Integer.parseInt(num.substring(num.length()-1));
		  if (num.length() >= 2) {
			   if (aux >= 2) {
					str.append(vet_dezenas[aux-1]);
					if (aux2 > 0) {
					 str.append(" e " + unidades(num));
					}
			   } else {
					 str.append(unidades(num));
			   }
		  } else {
			 str.append(unidades(num));
		  }
		  return str.toString();
	 }

  
	 //function centenas(num, numero)
	 private static  String centenas(String num, String numero) {
		  StringBuffer str = new StringBuffer();
		  String[] vet_centenas = {"Cem", "Duzentos", "Trezentos", "Quatrocentos", "Quinhentos", "Seiscentos", "Setecentos", "Oitocentos", "Novecentos"};
		  int aux,aux2;
		  aux = Integer.parseInt(num.substring(0,1));
		  aux2 = Integer.parseInt(num.substring(1,3));
		  if (Integer.parseInt(num) > 99) {

			   if (aux2 > 0) {
				 vet_centenas[0] = "Cento";
			   }
			   str.append(vet_centenas[aux-1]);
			   if (aux2 > 0) {
				 str.append(" e ");
			   }
		  } else {
			   str.append("");
		  }
		  
		  return ((str.toString()) + dezenas(num));
		  //return (str.append(dezenas(num))).toString();  - 
	 }



	 private  static   String milhares(String num, String numero, int b) {

	  StringBuffer str = new StringBuffer();
	  String auxstr;

	  int aux = Integer.parseInt(num.substring(0,3));
	  //int aux2 = Integer.parseInt(num.substring(1,3));


	  if (aux > 0) {
		 if (b!=0)
			 auxstr = ", ";
		 else
			 auxstr = "";
	  } else {
	   auxstr = " e ";
	  }
	  if (aux != 0) {
		 str.append(centenas(num, numero) + " Mil" + auxstr);
	  } else {
	   str.append("");
	  }
	  return str.toString();
	 }



	 private  static   String milhoes(String num, String numero) {
		  StringBuffer str = new StringBuffer();
		  int aux;
		  StringBuffer strmilhao = new StringBuffer();

		  aux = Integer.parseInt(num);
		  if (aux > 0) {
			   if (aux == 1) {
				strmilhao.append("Milhão,");
			   } else {
				strmilhao.append("Milhões,");
			   }
			   str.append(centenas(num, numero) + " " + strmilhao + " ");
		  } else {
			  str.append("");

		  }
		  return str.toString();
	  }



	 private  static   String bilhoes(String num, String numero) {
		 StringBuffer str = new StringBuffer();
		 int aux;
		 StringBuffer strbilhao = new StringBuffer();
		 aux = Integer.parseInt(num);
		  if (aux > 0) {
			   if (aux == 1) {
				strbilhao.append("Bilhão,");
			   } else {
				strbilhao.append("Bilhões,");
			   }
		   str.append(centenas(num, numero) + " " + strbilhao + " ");
		  } else {
		   str.append("");
		  }
		  return str.toString();
	 }





	 private  static   String centavos(String num2) {
		 StringBuffer str = new StringBuffer();
		 //int aux;
		 StringBuffer strcent = new StringBuffer();
		 String num = "0" + num2;

		  if (Integer.parseInt(num) > 0) {
			   if (Integer.parseInt(num) == 0 || Integer.parseInt(num) == 1) {
				   strcent.append(" Centavo");
			   } else {
				   strcent.append(" Centavos");
			   }
			   str.append(centenas(num, num) + strcent.toString());
		  } else {
			   str.append("");
		  }
		  return str.toString();
	 }



	 public static String extenso(String nump) {

		 if (nump == "") {
			 return " ";
		 }

		 StringTokenizer num1 = new StringTokenizer(nump,".");
		 StringBuffer num = new StringBuffer();

		 while (num1.countTokens() != 0 ) {
			 num.append(num1.nextToken());
		 }


		 StringTokenizer num2 = new StringTokenizer(num.toString(),",");
		 String[] aux_array = new String[num2.countTokens()];

		 int i = 0;
		 while (num2.countTokens() != 0 ) {
			 aux_array[i++] = num2.nextToken();
		 }


		 String inteiro, cents;
		 if (aux_array.length > 0) {
			 inteiro = aux_array[0];
			 cents = aux_array[1];
		 } else {
			 inteiro = aux_array[0];
			 cents = "00";
		 }

		 //int sizenum = inteiro.length();
		 String inteiro2 = new DecimalFormat("000000000000").format(Long.parseLong(inteiro));

		 String bilhar  = inteiro2.substring(0,3);
		 String milhao  = inteiro2.substring(3,6);
		 String milhar  = inteiro2.substring(6,9);
		 String centena = inteiro2.substring(9,12);

		 String strreal;


		 if (Long.parseLong(inteiro2) == 1) {
			 strreal = " Real ";
		 } else if (Long.parseLong(inteiro2) == 0) {
			 strreal = "";
		 } else if ((Long.parseLong(inteiro2) > 1) && (Integer.parseInt(inteiro2.substring(9,12)) == 1) )  {
			 strreal = " Real ";
		 } else {
			 strreal = " Reais ";
		 }


		  String strvirgula;
		  if ((Integer.parseInt(cents) > 0) && (Long.parseLong(inteiro2) > 0) ) {
			 strvirgula = "e ";
		  } else {
			 strvirgula = "";
		  }

		  StringBuffer strextenso = new StringBuffer();
		  strextenso.append(bilhoes(bilhar, inteiro2));
		  strextenso.append(milhoes(milhao, inteiro2));
		  strextenso.append(milhares(milhar, inteiro2,centenas(centena, inteiro2).trim().length() ));
		  strextenso.append(centenas(centena, inteiro2));
		  strextenso.append(strreal + strvirgula + centavos(cents));
		  return strextenso.toString();

	 }


	/**
	 * Retirar trechos de sintaxe SQL da String.
	 *
	 * @param String str
	 * @return String
	 */
	public static String antiSQLInjet(String str) {

		str =  Formata.RetiraAspasSimples(
			   Formata.RetiraAspasDuplas(
			   Formata.RetiraCaracter(str,'=')));

		str = str.replaceAll(" or ","");
		str = str.replaceAll("--","");
		str = str.replaceAll("select","");
		str = str.replaceAll("insert","");
		str = str.replaceAll("update","");
		str = str.replaceAll("delete","");
		str = str.replaceAll(">","");
		str = str.replaceAll("<","");
		str = str.replaceAll("%","");
		str = str.replaceAll("&","");
		str = str.replaceAll(";","");


		return str;

	}

   /**Faz arredondamento financeiro. Aplica a seguinte regra para desprezar dígitos
    * decimais: se o dígito a desprezar é >= 5 soma 1 ao dígito da esquerda senão,
    * mantém o dígito da esquerda.
    * @return valor arredondado.
    * @param val    valor que será arredondado.
    * @param escala quantidade de casas decimais do valor arredondado.
    * @author marcoc */
   public static double roundFinan( double val, int escala){
       BigDecimal result = new BigDecimal( String.valueOf( val ) );
       return result.divide(BIG1,escala,BigDecimal.ROUND_HALF_UP).doubleValue();
   }
   
   public static String hex2dec(String str){
	   try {
		   String res="";
		   for(int i = 0; i < str.length()/2; i++){
			   String l=str.substring(2*i, 2*(i+1));
			   char c = (char)Integer.parseInt(l, 16);
			   res +=c;
		   }
		   return res;
		
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
   }
   
	/**
	 * Converte o conteudo de uma string para uma string representando o valor em hexadecimal,
	 * referente a cada caractere da string
	 * @param pString
	 * @return
	 * @throws Exception
	 */
	public static String dec2hex(String pString)throws Exception{
		try{
	
			StringBuffer sb = new StringBuffer();
			char cc[];
			int numeroAscii;
			// Monta a String para Criptografar, concatenando
			// os valores ASCII de cada caracteres, na forma Hexadecimal.
			cc = pString.toCharArray();
			for (int i = 0; i <= cc.length -1; i++) {
				  //cc = pString.substring(i,i).toCharArray();
				  numeroAscii =   (int)  cc[i];
				  sb.append(Integer.toHexString(numeroAscii));
			}		
			
			return sb.toString().trim();
		}catch (Exception e) {
			throw new Exception("String em mal formatada.");
		}
	}//fim do metodo toHexa

   /**
		* Formata o Valor Numérico (Double) em uma String (999.999.999)
		* @param val
		* @return
		*/

	   protected static String convFormatMilhar(double val) {
			 NumberFormat nf = NumberFormat.getNumberInstance(new Locale("de", "DE"));
			 DecimalFormat df = (DecimalFormat)nf;
			 df.applyPattern("#,##0");
			 String output = df.format(val);
			 return output;
	   }

 

	 /**
		* Retorna String Formatada a partir da entrada de um tipo primitivo double
		* @param valor
		* @return
		*/

	   public static String getFormataMilhar(double valor) {
		   return convFormatMilhar(valor);
	   }

 

			   /**
				* Retorna String Formatada a partir da entrada de um tipo classe Double
				* @param valor
				* @return
				*/

	   public static String getFormataMilhar(Double valor) {
		   if (valor != null) {
			   return convFormatMilhar(valor.doubleValue());
		   } else {
			   return " ";
		   }
	   }

	public static String geraHtmlComEspacos(String pCaracter){
			String espaco = " ";
			String espacoHtml = "&nbsp;";
			String aux = "";

			//aux = pCaracter;

			int cont=0;
			if (pCaracter.length() > 0){
					   for(int i=0; i<pCaracter.length();i++ ){
								   if(pCaracter.substring(i,i+1).equalsIgnoreCase(" ")){
											   cont++;
											   aux = aux.concat(pCaracter.substring(i,i+1).replaceAll(espaco,espacoHtml));
								   }else{
											   cont=0;
											   aux = aux.concat(pCaracter.substring(i,i+1));
								   }
					   }
			}

			aux=aux.substring(0,(aux.length()-(cont*6)));
			return aux;

	}



	public static void main(String[] args) {
//		System.out.println(Formata.getMoeda(-1.5));
		try {
//			System.out.println(hex2dec("30323030463233413034303141384330383030383230303030303030313030303030303031363630333532323030303433333137303830303330303030303030303030303339373030393238313032343036373838393838303730333530303932383130303130323831313030303330313330303030313130303030303030393532383337363033353232303030343333313730383D303830393130313931373930313730303030303030353237353530303030303030303735383234373030343636393836393030303138343037363030383838383434314341303131313030303030303033303133"));
//			System.out.println(dec2hex("ZYX00000"));
			System.out.println(hex2dec("3030303030363331"));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}//fim da classe
