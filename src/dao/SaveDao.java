package dao;

interface SaveDao {

    abstract public void salvar(String arquivo);

    abstract public void preencheDados(String arquivo, Save save, String nomeJ1, String nomeJ2, int fase, int vidaJ1, int vidaJ2);

    abstract public String[] carregarDados(String arquivo);

    abstract public void preencheDadosRecorde(String arquivo, Save save, int vidaJ, String nomeJ);

    abstract public String[] carregarRecordes(String arquivo);
}
