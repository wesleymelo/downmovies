package br.ucb.filmes.validator;

public class ValidaSenha {

	public static String verificarValidadeSenha(String senhaNova, String senhaConfirmacao) throws Exception {

		StringBuilder erros = new StringBuilder();
		if (senhaNova != ""|| senhaConfirmacao != "") {
			if (!senhaConfirmacao.equals(senhaNova)) {
				verificarCriarErro("Confirmação divergente da Senha.", 0, erros);
			}

			// validando tamanho senhas...
			if (senhaNova.length() < 8 || senhaNova.length() > 100) {
				verificarCriarErro("Senha deve ter no mínimo 8 caracteres e no máximo 100.", 0, erros);
			}

			// Senha nÃ£o pode ser toda numÃ©rica...
			try {
				@SuppressWarnings("unused")
				long novaSenhaNumerica = Long.parseLong(senhaNova);
				verificarCriarErro("Senha não pode ser somente numérica.", 0, erros);
			} catch (NumberFormatException es) {
			}
			
		}
		return erros.toString();
	}
	public static void verificarCriarErro(String msgErro, Integer codErro, StringBuilder erros) {
		erros.append(msgErro);
	}

}
