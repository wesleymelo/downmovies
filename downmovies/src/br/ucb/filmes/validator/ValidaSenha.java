package br.ucb.filmes.validator;

public class ValidaSenha {

	public static String verificarValidadeSenha(String senhaNova, String senhaConfirmacao) throws Exception {

		StringBuilder erros = new StringBuilder();
		if (senhaNova != ""|| senhaConfirmacao != "") {
			if (!senhaConfirmacao.equals(senhaNova)) {
				verificarCriarErro("Confirma��o divergente da Senha.", 0, erros);
			}

			// validando tamanho senhas...
			if (senhaNova.length() < 8 || senhaNova.length() > 100) {
				verificarCriarErro("Senha deve ter no m�nimo 8 caracteres e no m�ximo 100.", 0, erros);
			}

			// Senha não pode ser toda numérica...
			try {
				@SuppressWarnings("unused")
				long novaSenhaNumerica = Long.parseLong(senhaNova);
				verificarCriarErro("Senha n�o pode ser somente num�rica.", 0, erros);
			} catch (NumberFormatException es) {
			}
			
		}
		return erros.toString();
	}
	public static void verificarCriarErro(String msgErro, Integer codErro, StringBuilder erros) {
		erros.append(msgErro);
	}

}
