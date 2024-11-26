package com.trabalho;

import java.io.IOException;
import java.util.Scanner;
import java.util.LinkedList;

import com.trabalho.connection.*;
import com.trabalho.utils.*;
import com.trabalho.reports.*;

/**
 * App
 */
public class App {
    
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner scanner = new Scanner(System.in);


        MenuFormatter.titulo("CONFIGURAÇÕES");
        System.out.print("| Deseja reiniciar os dados do Banco?" 
                        + "\n" + "[ 0 ] - Não"
                        + "\n" + "[ 1 ] - Sim"
                        + "\n" + MenuFormatter.getLinha("-=")
                        + "\n" + "Escolha uma opção: ");
        int opcaoConfig = scanner.nextInt();

        if (opcaoConfig == 1) {
            DatabaseMongoDb.droparDatabase();
            DatabaseMongoDb.criarDatabaseCollections();
            DatabaseMongoDb.inicializarDatabase();
    
            MenuFormatter.delay(2);
        }
    
        MenuFormatter.limparTerminal();


        int opcao;
        do {
            Menu.splashScreen();
            Menu.imprimirMenu();
            opcao = scanner.nextInt();

            MenuFormatter.limparTerminal();
            Menu.splashScreen();
            
            if (opcao == 1) {
                Menu.imprimirMenuRelatorio();
                int opcaoRelatorio = scanner.nextInt();

                MenuFormatter.limparTerminal();
                switch (opcaoRelatorio) {
                    case 1:
                        MenuFormatter.titulo("RELATÓRIO");
                        LinkedList<DadosCinemaEndereco> relatorio1 = Relatorio.listarCinemaEndereco();
                        break;
                    case 2:
                        MenuFormatter.titulo("RELATÓRIO");
                        LinkedList<DadosInformacaoSessoes> relatorio2 = Relatorio.listarInfoSessoes();
                        break;
                    case 3:
                        MenuFormatter.titulo("RELATÓRIO");
                        LinkedList<DadosSomaIngressos> relatorio3 = Relatorio.listaDadosSomaIngressos();
                        break;
                    default:
                        break;
                }

                // System.out.println(relatorio);

                MenuFormatter.delay(3);

            } else if (opcao == 2) {
                    Menu.imprimirMenuInserirRegistro();
                    int opcaoInserir = scanner.nextInt();

                    MenuFormatter.limparTerminal();
                    Menu.splashScreen();

                    switch (opcaoInserir) {
                        case 1:
                            Menu.menuInserirCinema();
                            break;
                        case 2:
                            Menu.menuInserirEndereco();
                            break;
                        case 3:
                            Menu.menuInserirFilme();
                            break;
                        case 4:
                            Menu.menuInserirSessao();
                            break;
                        case 5:
                            Menu.menuInserirVenda();
                            break;
                    
                        default:
                            break;
                    }

            } else if (opcao == 3) {
                Menu.imprimirMenuAlterarRegistro();
                int opcaoAlterar = scanner.nextInt();
                                
                MenuFormatter.limparTerminal();
                Menu.splashScreen();

                switch (opcaoAlterar) {
                    case 1:
                        Menu.menuAlterarCinema();
                        break;
                    
                    case 2:
                        Menu.menuAlterarEndereco();
                        break;
                    
                    case 3:
                        Menu.menuAlterarFilme();
                        break;
                    
                    case 4:
                        Menu.menuAlterarSessao();
                        break;
                    
                    case 5:
                        Menu.menuAlterarVenda();
                        break;
                
                    default:
                        break;
                }

            } else if (opcao == 4) {
                    Menu.imprimirMenuRemoverRegistro();
                    int opcaoRemover = scanner.nextInt();

                    MenuFormatter.limparTerminal();
                    Menu.splashScreen();

                    switch (opcaoRemover) {
                        case 1:
                            Menu.menuRemoverCinema();
                            break;
                        case 2:
                            Menu.menuRemoverEndereco();
                            break;
                        case 3:
                            Menu.menuRemoverFilme();
                            break;
                        case 4:
                            Menu.menuRemoverSessao();
                            break;
                        case 5:
                            Menu.menuRemoverVenda();
                            break;
                    
                        default:
                            break;
                    }
            }

            MenuFormatter.limparTerminal();        
        } while (opcao != 0);

        MenuFormatter.msgTerminalINFO("Encerrando o sistema.");

        scanner.close();
    }
}
