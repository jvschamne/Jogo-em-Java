package dao;

public class SaveLoad {

    public void salvar(String arquivo, String nomeJ1, String nomeJ2, int fase, int vidaJ1, int vidaJ2) {
        Save save = new Save();
        SaveDaoImplementacao saveDao = new SaveDaoImplementacao();

        /*salva esses dados num arquivo de tipo especificado ao chamar o metodo*/
        saveDao.preencheDados(arquivo, save, nomeJ1, nomeJ2, fase, vidaJ1, vidaJ2);
    }

    public String[] carregar(String arquivo) {
        SaveDaoImplementacao saveDao = new SaveDaoImplementacao();

        String[] linhas = saveDao.carregarDados(arquivo);
        /*return o array de strings com cada string sendo uma informacao sobre o 
        save (nomes dos jogadores, vida, fase*/
        return linhas;
    }

    public void salvarRecorde(String arquivo, int vidaJ, String nomeJ) {
        Save save = new Save();
        SaveDaoImplementacao saveDao = new SaveDaoImplementacao();
        
        /*salva esses dados num arquivo de tipo especificado ao chamar o metodo*/
        saveDao.preencheDadosRecorde(arquivo, save, vidaJ, nomeJ);
    }

    public String[] carregarRecordes(String arquivo) {
        SaveDaoImplementacao saveDao = new SaveDaoImplementacao();
        String[] linhas = saveDao.carregarRecordes(arquivo);
        /*return o array de strings com cada string sendo o nome do jogador que 
        venceu o jogo e sua vida restante
        */
        return linhas;
    }

}
