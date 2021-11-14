package jogotecprog;

import dao.SaveLoad;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;

public class Fase extends JPanel implements ActionListener {

    protected Jogador jogador1;
    protected Jogador2 jogador2;
    protected Image fundo;//projeto do tipo imagem
    protected Timer timer;
    protected JButton button;
    private List<Inimigo> inimigo1;
    private List<Inimigo2> inimigo2;
    private List<Inimigo3> inimigo3;
    private int flagCenario = 0;
    private int numeroFase;
    private int quantInimigosVivos = 0;
    private Image imgFase;
    private Boss1 b1;
    private Boss2 b2;
    private Boss3 b3;
    private int contadorMudaFase;//variavel para deixar a tela anunciando a fase mais tempo
    private boolean ganhou = false;
    private JLabel vidaJog1, vidaJog2;
    private boolean flagMusica = false;
    private boolean fimDeJogo = false;

    public Fase(JLabel vida1, JLabel vida2, String nomeJ1, String nomeJ2, int fase, int vidaJ1, int vidaJ2) {
        /*a construtora da classe Fase recebe as labels e vida, os nomes e vida...
        dos jogadores e o numero da fase*/

        vidaJog1 = vida1;//label da vida do jogador1
        vidaJog2 = vida2;//label da vida do jogador2
        numeroFase = fase;//atributo numero fase recebe a fase passa 

        setFocusable(true);
        setDoubleBuffered(true);

        if (numeroFase == 1) {
            //colocando a tela anunciando a fase 
            ImageIcon imgF = new ImageIcon("imagens\\Fase1.png");
            imgF.setImage(imgF.getImage().getScaledInstance(1370, 700, 10));
            imgFase = imgF.getImage();
            contadorMudaFase=0;
            //colocando o cenario da fase 1 
            ImageIcon img = new ImageIcon("imagens\\cenario1.png");
            img.setImage(img.getImage().getScaledInstance(4096, 1450, 10));
            fundo = img.getImage();
            
        } else if (numeroFase == 2) {
            //colocando a tela anunciando a fase 
            ImageIcon imgF = new ImageIcon("imagens\\Fase2.png");
            imgF.setImage(imgF.getImage().getScaledInstance(1370, 700, 10));
            imgFase = imgF.getImage();
            contadorMudaFase=301;
            //colocando o cenario da fase 2 
            ImageIcon img = new ImageIcon("imagens\\cenario2.png");
            img.setImage(img.getImage().getScaledInstance(4096, 1450, 10));
            fundo = img.getImage();
        } else {
            //colocando a tela anunciando a fase  
            ImageIcon imgF = new ImageIcon("imagens\\Fase3.png");
            imgF.setImage(imgF.getImage().getScaledInstance(1370, 700, 10));
            imgFase = imgF.getImage();
            contadorMudaFase=601;
            //colocando o cenario da fase 3 
            ImageIcon img = new ImageIcon("imagens\\cenario3.png");
            img.setImage(img.getImage().getScaledInstance(4096, 1450, 10));
            fundo = img.getImage();
        }

        //colocando o jogador 1 no jogo
        jogador1 = new Jogador();
        jogador1.load();//carregando o jogador1
        jogador1.setNome(nomeJ1);
        jogador1.setVida(vidaJ1);
        jogador1.setVidaFase(vidaJ1);

        //colocando o jogador 2 no jogo
        jogador2 = new Jogador2();
        jogador2.load();//carregando o jogador2
        jogador2.setNome(nomeJ2);
        jogador2.setVida(vidaJ2);
        jogador2.setVidaFase(vidaJ2);

        //colocando os inimigos da fase onde o jogador comeca no jogo
        inicializaInimigos();

        addKeyListener(new TecladoAdapter());/*adicionando um key listener, que...
        vai detectar eventos de apertar alguma tecla*/

        timer = new Timer(1, this);
        timer.start();
    }

    public void inicializaInimigos() {
        //classe para colocar os inimigos no jogo

        int quantInimigos = 0;
        /*analisa qual parte da fase esta para determinar quantidade de inimigos
        que serao colocados nessa parte da fase*/
        if (flagCenario == 0) {
            quantInimigos = 10;
        } else if (flagCenario == 1) {
            quantInimigos = 14;
        } else if (flagCenario == 2) {
            quantInimigos = 17;
        } else if (flagCenario == 3) {
            quantInimigos = 10;
        }

        quantInimigosVivos = quantInimigos;

        /*analisa em qual fase esta para colocar o tipo de inimigo de acordo com 
        a fase*/
        if (numeroFase == 1) {
            inimigo1 = new ArrayList<>();//lista de inimigos1

            int x = 800, y = 200;
            for (int i = 0; i < quantInimigos; i++) {
                inimigo1.add(new Inimigo(x, y));//adicionando inimigos1
                x += 200;
                y += 50;
            }

            if (flagCenario == 3) {//adicionando Boss1
                b1 = new Boss1();
                b1.load();
                quantInimigosVivos++;
            }
        } else if (numeroFase == 2) {
            inimigo2 = new ArrayList<>();//lista de inimigos2

            int x = 800, y = 200;
            for (int i = 0; i < quantInimigos; i++) {
                inimigo2.add(new Inimigo2(x, y));//adicionando inimigos2
                x += 200;
                y += 25;
            }

            if (flagCenario == 3) {//adicionando Boss2
                b2 = new Boss2();
                b2.load();
                quantInimigosVivos++;
            }
        } else if (numeroFase == 3) {

            inimigo3 = new ArrayList<>();//lista de inimigos3

            int x = 800, y = 300;
            for (int i = 0; i < quantInimigos; i++) {
                inimigo3.add(new Inimigo3(x, y));//adicionando inimigos3
                x += 200;
                y += 50;
            }

            if (flagCenario == 3) {//adicionando Boss3
                b3 = new Boss3();
                b3.load();
                quantInimigosVivos++;
            }

        }

    }

    public void paint(Graphics g) {
        Graphics2D graficos = (Graphics2D) g;

        if (fimDeJogo == false) {//se o jogo nao tiver acabado... 
            /*Código para mudar o cenário conforme os jogadores avançam*/
            if ((jogador1.getVisivel() == true || jogador2.getVisivel() == true) && ganhou == false) {
                /*os jogadores so conseguem avançar caso tenha matado todos os 
                inimigos do cenario em que estavam*/

                if ((jogador1.getX() >= 1300 || jogador2.getX() >= 1300) && quantInimigosVivos <= 0) {
                    /*quando os jogadores chegam no final da tela, o cenario 
                    muda, a imagem do cenario é puxada para a esquerda e os jogadores
                    voltam ao ponto inicial da tela.
                     */
                    jogador1.setX(0);
                    jogador2.setX(0);
                    flagCenario++;/*aumenta o numero que indica o cenario*/

                    if (flagCenario == 4 && numeroFase == 3) {
                        /*se os jogadores atravessarem o ultimo cenario da ultima
                        fase, eles vencem e o jogo acaba*/
                        ganhou = true;
                    } else if (flagCenario == 4) {
                        /*se os jogadores atravessarem o ultimo cenario da fase
                        1 ou 2, o numero de fase aumenta,
                        a vidaFase(vida do save daquela fase) recebe a vida atual,
                        o indicador do cenario é zerado
                         */
                        numeroFase++;
                        jogador1.setVidaFase(jogador1.getVida());
                        jogador2.setVidaFase(jogador2.getVida());
                        flagCenario = 0;

                        if (numeroFase == 2) {
                            //colocando o cenario da fase 2
                            ImageIcon img = new ImageIcon("imagens\\cenario2.png");
                            img.setImage(img.getImage().getScaledInstance(4096, 1400, 10));
                            fundo = img.getImage();
                        } else {
                            //colocando o cenario da fase 3
                            ImageIcon img = new ImageIcon("imagens\\cenario3.png");
                            img.setImage(img.getImage().getScaledInstance(4096, 1350, 100));
                            fundo = img.getImage();
                        }

                    }
                    inicializaInimigos();
                    /*colocando os inimigos do novo cenario em que os jogadores
                    chegaram*/
                }

                /*codigo para avançar a imagem do cenario: conforme os jogadores 
                avançam a imagem é puxada para a esquerda*/
                if (flagCenario == 0) {
                    graficos.drawImage(fundo, 0, 0, null);
                } else if (flagCenario == 1) {
                    graficos.drawImage(fundo, -1000, 0, null);
                } else if (flagCenario == 2) {
                    graficos.drawImage(fundo, -1400, 0, null);
                } else if (flagCenario == 3) {
                    graficos.drawImage(fundo, -1800, 0, null);
                }
                //atualizando a imagem dos jogadores
                jogador1.load();
                graficos.drawImage(jogador1.getImage(), jogador1.getX(), jogador1.getY(), this);

                jogador2.load();
                graficos.drawImage(jogador2.getImage(), jogador2.getX(), jogador2.getY(), this);

                //atualizando a imagem dos ataques dos jogadores
                List<Soco> soco = jogador1.getSoco();
                List<Tiros> tiros2 = jogador2.getTiros();

                for (int i = 0; i < soco.size(); i++) {
                    Soco m = soco.get(i);
                    m.load();
                    graficos.drawImage(m.getImagem(), m.getX(), m.getY(), this);
                }
                for (int i = 0; i < tiros2.size(); i++) {
                    Tiros m2 = tiros2.get(i);
                    m2.load();
                    graficos.drawImage(m2.getImagem(), m2.getX(), m2.getY(), this);
                }

                //atualizando a imagem dos ataques do Boss3
                if (numeroFase == 3 && flagCenario == 3) {
                    List<PoderBoss3> poderBoss3 = b3.getPoder();
                    for (int i = 0; i < poderBoss3.size(); i++) {
                        PoderBoss3 poder = poderBoss3.get(i);
                        poder.load();
                        graficos.drawImage(poder.getImagem(), poder.getX(), poder.getY(), this);
                    }
                }
                //atualizando a imagem dos inimigos e dos chefões
                if (numeroFase == 1) {
                    for (int o = 0; o < inimigo1.size(); o++) {
                        Inimigo in = inimigo1.get(o);
                        in.mover(jogador1, jogador2);
                        graficos.drawImage(in.getImage(), in.getX(), in.getY(), this);
                    }

                    if (flagCenario == 3) {
                        b1.mover(jogador1, jogador2);
                        graficos.drawImage(b1.getImage(), b1.getX(), b1.getY(), this);
                    }
                } else if (numeroFase == 2) {
                    for (int o = 0; o < inimigo2.size(); o++) {
                        Inimigo2 in2 = inimigo2.get(o);
                        in2.mover(jogador1, jogador2);

                        graficos.drawImage(in2.getImage(), in2.getX(), in2.getY(), this);
                    }
                    if (flagCenario == 3) {
                        b2.mover(jogador1, jogador2);
                        graficos.drawImage(b2.getImage(), b2.getX(), b2.getY(), this);
                    }
                } else if (numeroFase == 3) {
                    for (int o = 0; o < inimigo3.size(); o++) {
                        Inimigo3 in3 = inimigo3.get(o);
                        in3.mover(jogador1, jogador2);

                        graficos.drawImage(in3.getImage(), in3.getX(), in3.getY(), this);
                    }
                    if (flagCenario == 3) {
                        b3.mover(jogador1, jogador2);
                        graficos.drawImage(b3.getImage(), b3.getX(), b3.getY(), this);
                    }
                }

                /*imagem de mudança de fase + impedir a movimentacao dos inimigos
                enquanto esta nessa tela*/
                if (numeroFase == 1 && contadorMudaFase <= 300) {
                    if (contadorMudaFase == 0) {
                        ImageIcon imgF = new ImageIcon("imagens\\Fase1.png");
                        imgF.setImage(imgF.getImage().getScaledInstance(1370, 700, 10));
                        imgFase = imgF.getImage();

                        for (int o = 0; o < inimigo1.size(); o++) {
                            Inimigo in = inimigo1.get(o);
                            in.setVelocidade(0);
                        }
                    } else if (contadorMudaFase == 300) {
                        for (int o = 0; o < inimigo1.size(); o++) {
                            Inimigo in = inimigo1.get(o);
                            in.setVelocidade(1);
                        }

                    }

                    graficos.drawImage(imgFase, 0, 0, null);
                    contadorMudaFase++;
                } else if (numeroFase == 2 && contadorMudaFase <= 600) {
                    if (contadorMudaFase == 301) {
                        ImageIcon imgF = new ImageIcon("imagens\\Fase2.png");
                        imgF.setImage(imgF.getImage().getScaledInstance(1370, 700, 10));
                        imgFase = imgF.getImage();

                        for (int o = 0; o < inimigo1.size(); o++) {
                            Inimigo in = inimigo1.get(o);
                            in.setVelocidade(0);

                        }
                    } else if (contadorMudaFase == 600) {
                        for (int o = 0; o < inimigo2.size(); o++) {
                            Inimigo2 in = inimigo2.get(o);
                            in.setVelocidade(1);
                        }
                    }

                    graficos.drawImage(imgFase, 0, 0, null);
                    contadorMudaFase++;
                } //imagem de mudança de fase + congelar jogadores e inimigos
                else if (numeroFase == 3 && contadorMudaFase <= 900) {
                    if (contadorMudaFase == 601) {
                        ImageIcon imgF = new ImageIcon("imagens\\Fase3.png");
                        imgF.setImage(imgF.getImage().getScaledInstance(1370, 700, 10));
                        imgFase = imgF.getImage();

                        for (int o = 0; o < inimigo3.size(); o++) {
                            Inimigo3 in = inimigo3.get(o);
                            in.setVelocidade(0);

                        }
                    } else if (contadorMudaFase == 900) {
                        for (int o = 0; o < inimigo3.size(); o++) {
                            Inimigo3 in = inimigo3.get(o);
                            in.setVelocidade(1);
                        }
                    }

                    graficos.drawImage(imgFase, 0, 0, null);
                    contadorMudaFase++;
                }
                /*chama o metodo checar colisoes para causar danos nos 
                personagens que estao sendo afetados*/
                checarColisoes();
                
            /*se os jogadores estao mortos, coloca a imagem de gameOver na tela*/    
            } else if (jogador1.getVisivel() == false && jogador2.getVisivel() == false) {
                fimDeJogo = true;
                ImageIcon referencia = new ImageIcon("imagens\\GameOver.png");
                referencia.setImage(referencia.getImage().getScaledInstance(1370, 700, 10));
                fundo = referencia.getImage();
            /*se os jogadores venceram o jogo*/  
            } else {
                fimDeJogo = true;
                //coloca a imagem de voce venceu
                ImageIcon referencia = new ImageIcon("imagens\\GameWin.png");
                referencia.setImage(referencia.getImage().getScaledInstance(1370, 700, 10));
                fundo = referencia.getImage();

                //jogador1.setVidaFase(jogador1.getVida());
                //jogador2.setVidaFase(jogador2.getVida());

                SaveLoad salvarRecorde = new SaveLoad();
                //cria um objeto da classe saveLoad para salvar os recordes
                
                /*salva o nome e a vida restante dos jogadores que chegaram ao 
                fim do jogo e venceram*/
                if (jogador1.getVisivel()) {
                    salvarRecorde.salvarRecorde("recorde.txt", jogador1.getVida(), jogador1.getNome());
                    salvarRecorde.salvarRecorde("recorde.csv", jogador1.getVida(), jogador1.getNome());
                }

                if (jogador2.getVisivel()) {
                    salvarRecorde.salvarRecorde("recorde.txt", jogador2.getVida(), jogador2.getNome());
                    salvarRecorde.salvarRecorde("recorde.csv", jogador2.getVida(), jogador2.getNome());
                }

            }
        }

        if (fimDeJogo == true) {/*se o jogo acabou, deixa pintando a tela de 
            fim de jogo (gameOver ou gameWin)*/
            graficos.drawImage(fundo, 0, 0, null);
        }
        g.dispose();
    }

    public void checarColisoes() {
        /*Nesse metodo estao os codigos para detectar colisao entre os inimigos 
        e os jogadores, os boss e os jogadores, o poder dos jogadores e os 
        inimigos e boss e entre o poder do boss3 e os jogadores*/
        
        /*declarando os retangulos de colisoes*/
        Rectangle formaInimigo;
        Rectangle formaPoder;
        Rectangle formaPoder2;
        Rectangle formaJogador = jogador1.getBounds();
        Rectangle formaJogador2 = jogador2.getBounds();
        Rectangle formaBoss1 = null;
        Rectangle formaBoss2 = null;
        Rectangle formaBoss3 = null;

        if (numeroFase == 2 && flagCenario == 3) {
            formaBoss2 = b2.getBounds();
        } else if (numeroFase == 3 && flagCenario == 3) {
            formaBoss3 = b3.getBounds();
        }

        if (numeroFase == 1) {
            for (int i = 0; i < inimigo1.size(); i++) {
                Inimigo tempInimigo1 = inimigo1.get(i);
                formaInimigo = tempInimigo1.getBounds();
                if (formaJogador.intersects(formaInimigo)) {
                    jogador1.setVida(jogador1.getVida() - tempInimigo1.dano);
                    if (jogador1.getVida() <= 0) {
                        jogador1.setVisivel(false);

                    }
                }

                if (formaJogador2.intersects(formaInimigo)) {
                    jogador2.setVida(jogador2.getVida() - tempInimigo1.dano);
                    if (jogador2.getVida() <= 0) {
                        jogador2.setVisivel(false);
                    }

                }

            }
            //colisao entre o boss1 os jogadores   
            if (flagCenario == 3) {

                Boss1 tempBoss1 = b1;
                formaBoss1 = tempBoss1.getBounds();

                if (formaJogador.intersects(formaBoss1)) {
                    jogador1.setVida(jogador1.getVida() - tempBoss1.dano);
                    if (jogador1.getVida() <= 0) {
                        jogador1.setVisivel(false);
                    }

                }

                if (formaJogador2.intersects(formaBoss1)) {
                    jogador2.setVida(jogador2.getVida() - tempBoss1.dano);
                    if (jogador2.getVida() <= 0) {
                        jogador2.setVisivel(false);
                    }

                }
            }

        }

        if (numeroFase == 2) {
            for (int i = 0; i < inimigo2.size(); i++) {
                Inimigo2 tempInimigo1 = inimigo2.get(i);
                formaInimigo = tempInimigo1.getBounds();
                if (formaJogador.intersects(formaInimigo)) {
                    jogador1.setVida(jogador1.getVida() - tempInimigo1.dano);
                    if (jogador1.getVida() <= 0) {
                        jogador1.setVisivel(false);

                    }

                }

                if (formaJogador2.intersects(formaInimigo)) {
                    jogador2.setVida(jogador2.getVida() - tempInimigo1.dano);
                    if (jogador2.getVida() <= 0) {
                        jogador2.setVisivel(false);
                    }

                }

            }

            //colisao entre o boss2 os jogadores
            if (flagCenario == 3) {
                Boss2 tempBoss2 = b2;
                formaBoss2 = tempBoss2.getBounds();

                if (formaJogador.intersects(formaBoss2)) {
                    jogador1.setVida(jogador1.getVida() - tempBoss2.dano);
                    if (jogador1.getVida() <= 0) {
                        jogador1.setVisivel(false);
                    }

                }

                if (formaJogador2.intersects(formaBoss2)) {
                    jogador2.setVida(jogador2.getVida() - tempBoss2.dano);
                    if (jogador2.getVida() <= 0) {
                        jogador2.setVisivel(false);
                    }

                }
            }

        }

        if (numeroFase == 3) {
            for (int i = 0; i < inimigo3.size(); i++) {
                Inimigo3 tempInimigo1 = inimigo3.get(i);
                formaInimigo = tempInimigo1.getBounds();
                if (formaJogador.intersects(formaInimigo)) {
                    jogador1.setVida(jogador1.getVida() - tempInimigo1.dano);
                    if (jogador1.getVida() <= 0) {
                        jogador1.setVisivel(false);

                    }

                }

                if (formaJogador2.intersects(formaInimigo)) {
                    jogador2.setVida(jogador2.getVida() - tempInimigo1.dano);
                    if (jogador2.getVida() <= 0) {
                        jogador2.setVisivel(false);
                    }
                }
            }

            //colisao entre o boss3 os jogadores
            if (flagCenario == 3) {
                Boss3 tempBoss3 = b3;
                formaBoss3 = tempBoss3.getBounds();

                if (formaJogador.intersects(formaBoss3)) {
                    jogador1.setVida(jogador1.getVida() - tempBoss3.dano);
                    if (jogador1.getVida() <= 0) {
                        jogador1.setVisivel(false);
                    }

                }

                if (formaJogador2.intersects(formaBoss3)) {
                    jogador2.setVida(jogador2.getVida() - tempBoss3.dano);
                    if (jogador2.getVida() <= 0) {
                        jogador2.setVisivel(false);
                    }

                }

                List<PoderBoss3> poderBoss3 = b3.getPoder();
                for (int i = 0; i < poderBoss3.size(); i++) {
                    PoderBoss3 tempPoder = poderBoss3.get(i);
                    formaPoder = tempPoder.getBounds();

                    if (formaPoder.intersects(formaJogador)) {
                        jogador1.setVida(jogador1.getVida() - b3.dano);
                        tempPoder.setIsVisivel(false);
                    }

                    if (formaPoder.intersects(formaJogador2)) {
                        jogador2.setVida(jogador2.getVida() - b3.dano);
                        tempPoder.setIsVisivel(false);
                    }
                    if (jogador1.getVida() <= 0) {
                        jogador1.setVisivel(false);
                    }
                    if (jogador2.getVida() <= 0) {
                        jogador2.setVisivel(false);
                    }

                }
            }

        }

        List<Soco> soco = jogador1.getSoco();
        List<Tiros> tiros2 = jogador2.getTiros();

        for (int i = 0; i < soco.size(); i++) {
            Soco tempSoco = soco.get(i);
            formaPoder = tempSoco.getBounds();
            //colisao entre o jogador 1 e o boss1
            if (numeroFase == 1 && flagCenario == 3) {
                if (formaPoder.intersects(formaBoss1)) {
                    b1.setVida(b1.getVida() - jogador1.dano);
                    tempSoco.setIsVisivel(false);
                }
                if (b1.getVida() <= 0) {
                    b1.setVisivel(false);
                    quantInimigosVivos -= 1;
                }
            }
            //colisao entre o jogador 1 e o boss2
            if (numeroFase == 2 && flagCenario == 3) {
                if (formaPoder.intersects(formaBoss2)) {
                    b2.setVida(b2.getVida() - jogador1.dano);
                    tempSoco.setIsVisivel(false);
                }
                if (b2.getVida() <= 0) {
                    b2.setVisivel(false);
                    quantInimigosVivos -= 1;
                }
            }
            //colisao entre o jogador 1 e o boss3
            if (numeroFase == 3 && flagCenario == 3) {
                if (formaPoder.intersects(formaBoss3)) {
                    b3.setVida(b3.getVida() - jogador1.dano);
                    tempSoco.setIsVisivel(false);
                }
                if (b3.getVida() <= 0) {
                    b3.setVisivel(false);
                    quantInimigosVivos -= 1;
                }
            }

            //colisao entre o jogador1 e o mobs da fase 1
            if (numeroFase == 1) {
                for (int j = 0; j < inimigo1.size(); j++) {
                    Inimigo tempInimigo = inimigo1.get(j);
                    formaInimigo = tempInimigo.getBounds();

                    if (formaPoder.intersects(formaInimigo)) {
                        tempInimigo.setVida(tempInimigo.getVida() - jogador1.dano);
                        tempSoco.setIsVisivel(false);

                        if (tempInimigo.getVida() <= 0) {
                            tempInimigo.setVisivel(false);
                            inimigo1.remove(j);
                            quantInimigosVivos -= 1;
                        }
                    }

                }
            } //colisao entre o jogador1 e o mobs da fase 2
            else if (numeroFase == 2) {
                for (int j = 0; j < inimigo2.size(); j++) {
                    Inimigo2 tempInimigo = inimigo2.get(j);
                    formaInimigo = tempInimigo.getBounds();

                    if (formaPoder.intersects(formaInimigo)) {
                        tempInimigo.setVida(tempInimigo.getVida() - jogador1.dano);
                        tempSoco.setIsVisivel(false);

                        if (tempInimigo.getVida() <= 0) {
                            tempInimigo.setVisivel(false);
                            inimigo2.remove(j);
                            quantInimigosVivos -= 1;
                        }
                    }

                }
            }
            //colisao entre o jogador1 e o mobs da fase 3
            if (numeroFase == 3) {
                for (int j = 0; j < inimigo3.size(); j++) {
                    Inimigo3 tempInimigo = inimigo3.get(j);
                    formaInimigo = tempInimigo.getBounds();

                    if (formaPoder.intersects(formaInimigo)) {
                        tempInimigo.setVida(tempInimigo.getVida() - jogador1.dano);
                        tempSoco.setIsVisivel(false);

                        if (tempInimigo.getVida() <= 0) {
                            tempInimigo.setVisivel(false);
                            inimigo3.remove(j);
                            quantInimigosVivos -= 1;
                        }
                    }

                }
            }

        }

        //colisoes do tiro do jogador 2
        for (int i = 0; i < tiros2.size(); i++) {
            Tiros tempTiros2 = tiros2.get(i);
            formaPoder2 = tempTiros2.getBounds();

            //colisao entre o jogador2 e o boss1
            if (numeroFase == 1 && flagCenario == 3) {
                if (formaPoder2.intersects(formaBoss1)) {
                    b1.setVida(b1.getVida() - jogador2.dano);
                    tempTiros2.setIsVisivel(false);
                }
                if (b1.getVida() <= 0) {
                    b1.setVisivel(false);
                    quantInimigosVivos -= 1;
                }

            } //colisao entre o jogador2 e o boss2
            else if (numeroFase == 2 && flagCenario == 3) {
                if (formaPoder2.intersects(formaBoss2)) {
                    b2.setVida(b2.getVida() - jogador2.dano);
                    tempTiros2.setIsVisivel(false);
                }
                if (b2.getVida() <= 0) {
                    b2.setVisivel(false);
                    quantInimigosVivos -= 1;
                }
            } //colisao entre o jogador2 e o boss2
            else if (numeroFase == 3 && flagCenario == 3) {
                if (formaPoder2.intersects(formaBoss3)) {
                    b3.setVida(b3.getVida() - jogador2.dano);
                    tempTiros2.setIsVisivel(false);
                }
                if (b3.getVida() <= 0) {
                    b3.setVisivel(false);
                    quantInimigosVivos -= 1;
                }
            }

            //colisao entre o jogador2 e o mobs da fase 1
            if (numeroFase == 1) {
                for (int j = 0; j < inimigo1.size(); j++) {
                    Inimigo tempInimigo = inimigo1.get(j);
                    formaInimigo = tempInimigo.getBounds();
                    if (formaPoder2.intersects(formaInimigo)) {
                        tempInimigo.setVida(tempInimigo.getVida() - jogador2.dano);
                        tempTiros2.setIsVisivel(false);
                        if (tempInimigo.getVida() <= 0) {
                            tempInimigo.setVisivel(false);
                            inimigo1.remove(j);
                            quantInimigosVivos -= 1;
                        }
                    }

                }
            } //colisao entre o jogador2 e o mobs da fase 2
            else if (numeroFase == 2) {
                for (int j = 0; j < inimigo2.size(); j++) {
                    Inimigo2 tempInimigo = inimigo2.get(j);
                    formaInimigo = tempInimigo.getBounds();
                    if (formaPoder2.intersects(formaInimigo)) {
                        tempInimigo.setVida(tempInimigo.getVida() - jogador2.dano);
                        tempTiros2.setIsVisivel(false);
                        if (tempInimigo.getVida() <= 0) {
                            tempInimigo.setVisivel(false);
                            inimigo2.remove(j);
                            quantInimigosVivos -= 1;
                        }
                    }

                }
            } //colisao entre o jogador2 e o mobs da fase 3
            else if (numeroFase == 3) {
                for (int j = 0; j < inimigo3.size(); j++) {
                    Inimigo3 tempInimigo = inimigo3.get(j);
                    formaInimigo = tempInimigo.getBounds();

                    if (formaPoder2.intersects(formaInimigo)) {
                        tempInimigo.setVida(tempInimigo.getVida() - jogador2.dano);
                        tempTiros2.setIsVisivel(false);

                        if (tempInimigo.getVida() <= 0) {
                            tempInimigo.setVisivel(false);
                            inimigo3.remove(j);
                            quantInimigosVivos -= 1;
                        }
                    }

                }
            }

        }
        System.out.println("Vida Jogador1 = " + jogador1.getVida());
        System.out.println("Vida Jogador2 = " + jogador2.getVida());

        //controle.setVidaJogadores(jogador1.getVida(), jogador2.getVida());
        vidaJog1.setText(jogador1.getVida() + "");
        vidaJog2.setText(jogador2.getVida() + "");

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        jogador1.mover();
        jogador2.mover();

        List<Soco> soco = jogador1.getSoco();
        List<Tiros> tiros2 = jogador2.getTiros();

        if (numeroFase == 3 && flagCenario == 3) {
            List<PoderBoss3> poderBoss3 = b3.getPoder();

            for (int i = 0; i < poderBoss3.size(); i++) {
                PoderBoss3 p = poderBoss3.get(i);//get index
                if (p.getisVisivel()) {
                    p.update();
                } else {
                    poderBoss3.remove(i);
                }
            }

        }

        for (int i = 0; i < soco.size(); i++) {
            Soco m = soco.get(i);//get index
            if (m.getisVisivel()) {
                m.update();
            } else {
                soco.remove(i);
            }
        }
        for (int i = 0; i < tiros2.size(); i++) {
            Tiros m2 = tiros2.get(i);//get index
            if (m2.getisVisivel()) {
                m2.update();
            } else {
                tiros2.remove(i);
            }
        }

        repaint();
    }

    private class TecladoAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            jogador1.apertaTecla(e);
            jogador2.apertaTecla(e);
            apertaTecla(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            jogador1.soltaTecla(e);
            jogador2.soltaTecla(e);
        }
    }

    public void apertaTecla(KeyEvent tecla) {
        int codigo = tecla.getKeyCode();
        /*quando a tecla H é aperta, salva em txt, e se a tecla apertada for J, 
        salva em csv*/
        if (codigo == KeyEvent.VK_H) {
            SaveLoad saveTxt = new SaveLoad();
            saveTxt.salvar("save.txt", jogador1.getNome(), jogador2.getNome(), numeroFase, jogador1.getVidaFase(), jogador2.getVidaFase());
        } else if (codigo == KeyEvent.VK_J) {
            SaveLoad saveCsv = new SaveLoad();
            saveCsv.salvar("save.csv", jogador1.getNome(), jogador2.getNome(), numeroFase, jogador1.getVidaFase(), jogador2.getVidaFase());
        }

    }

}
