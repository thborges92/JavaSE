package VIEW;

import SERVICES.CONTROLLERS.AlunoController;
import SERVICES.CONTROLLERS.ProfessorController;
import SERVICES.CONTROLLERS.TurmaController;
import SERVICES.Get;
import SERVICES.UsuarioService;

public class Menu {

    public static void inicio() {

        while(true) {

            System.out.println("\n\n======================================");
            System.out.println("Escolinha do Tio TH");
            System.out.println("======================================");
            System.out.println("\t(0) SAIR");
            System.out.println("\t(1) Usuários");
            System.out.println();
            System.out.println("\t(2) Alunos");
            System.out.println("\t(3) Professores");
            System.out.println("\t(4) Turmas");
            System.out.print("Escolha uma opção: nº ");

            int op = Get.inteiro();

            switch (op) {

                default:

                    System.err.println("\nOpção inválida!");
                    break;

                case 0:

                    System.exit(2);

                case 1:

                    usuarios();
                    break;

                case 2:

                    alunos();
                    break;

                case 3:

                    professores();
                    break;

                case 4:

                    turmas();
                    break;

            }

        }

    }

    private static void usuarios() {

        System.out.println("\n\n=============Usuários============");
        System.out.println("\t(0) VOLTAR AO MENU PRINCIPAL");
        System.out.println("\t(1) Cadastrar");
        System.out.println("\t(2) Relatório Geral");
        System.out.print("Escolha uma opção: nº ");

        int op = Get.inteiro();

        switch (op) {

            default:

                System.err.println("\nOpção inválida!");
                break;

            case 0:

                break;

            case 1:

                UsuarioService.cadastra();
                break;

            case 2:

                UsuarioService.consultar();
                break;

        }

    }

    private static void alunos() {

        System.out.println("\n\n=============Alunos============");
        System.out.println("\t(0) VOLTAR AO MENU PRINCIPAL");
        System.out.println("\t(1) Cadastrar");
        System.out.println("\t(2) Relatório Geral");
        System.out.println("\t(3) Relatório por NOME de Aluno");
        System.out.println("\t(4) Excluir");
        System.out.println("\t(5) Gerar relatório em Arquivo");
        System.out.print("Escolha uma opção: nº ");

        int op = Get.inteiro();

        switch (op) {

            default:

                System.err.println("\nOpção inválida!");
                break;

            case 0:

                break;

            case 1:

                System.out.println("+++++CADASTRO DE ALUNOS+++++");
                AlunoController.cadastrar();
                break;

            case 2:

                System.out.println("\n\n=======RELATÓRIO GERAL DE ALUNOS=========");
                AlunoController.imprimirLista(AlunoController.getAll());
                break;

            case 3:

                System.out.print("\n\nInforme o nome: ");
                String buscanome = Get.texto();

                System.out.println("\n\n=======RELATÓRIO DE ALUNOS POR NOME=========");
                AlunoController.imprimirLista(AlunoController.getStudentByName(buscanome));
                break;

            case 4:

                System.out.print("\n\nInforme o nome do aluno a ser excluído: ");
                String excluinome = Get.texto();

                if (AlunoController.deleteByName(excluinome)) {

                    System.out.println("\nAluno excluído com sucesso.");

                } else {

                    System.out.println("\nFalha ao excluir o aluno.");

                }
                break;

            case 5:

                System.out.println("\n\nArquivo gerado com sucesso.");
                AlunoController.gerarArquivo();
                break;

        }

    }

    private static void professores() {

        System.out.println("\n\n=============Professores============");
        System.out.println("\t(0) VOLTAR AO MENU PRINCIPAL");
        System.out.println("\t(1) Cadastrar");
        System.out.println("\t(2) Relatório Geral");
        System.out.println("\t(3) Relatório por NOME de Professor");
        System.out.println("\t(4) Excluir");
        System.out.println("\t(5) Gerar relatório em Arquivo");
        System.out.print("Escolha uma opção: nº ");

        int op = Get.inteiro();

        switch (op) {

            default:

                System.err.println("\nOpção inválida!");
                break;

            case 0:

                break;

            case 1:

                System.out.println("+++++CADASTRO DE PROFESSORES+++++");
                ProfessorController.cadastrar();
                break;

            case 2:

                System.out.println("\n\n=======RELATÓRIO GERAL DE PROFESSORES=========");
                ProfessorController.imprimirLista(ProfessorController.getAll());
                break;

            case 3:

                System.out.print("\n\nInforme o nome: ");
                String buscanome = Get.texto();

                System.out.println("\n\n=======RELATÓRIO DE PROFESSORES POR NOME=========");
                ProfessorController.imprimirLista(ProfessorController.getTeacherByName(buscanome));
                break;

            case 4:

                System.out.print("\n\nInforme o nome do professor a ser excluído: ");
                String excluinome = Get.texto();

                if (ProfessorController.deleteByName(excluinome)) {

                    System.out.println("\nProfessor excluído com sucesso.");

                } else {

                    System.out.println("\nFalha ao excluir o professor.");

                }
                break;

            case 5:

                System.out.println("\n\nArquivo gerado com sucesso.");
                ProfessorController.gerarArquivo();
                break;


        }

    }

    private static void turmas() {

        System.out.println("\n\n=============Turmas============");
        System.out.println("\t(0) VOLTAR AO MENU PRINCIPAL");
        System.out.println("\t(1) Cadastrar");
        System.out.println("\t(2) Relatório Geral");
        System.out.println("\t(3) Relatório de Turma por TURNO");
        System.out.println("\t(4) Excluir");
        System.out.println("\t(5) Gerar relatório em Arquivo");
        System.out.print("Escolha uma opção: nº ");

        int op = Get.inteiro();

        switch (op) {

            default:

                System.err.println("\nOpção inválida!");
                break;

            case 0:

                break;

            case 1:

                System.out.println("+++++CADASTRO DE TURMAS+++++");
                TurmaController.cadastrar();
                break;

            case 2:

                System.out.println("\n\n=======RELATÓRIO GERAL DE TURMAS=========");
                TurmaController.imprimirLista(TurmaController.getAll());
                break;

            case 3:

                System.out.print("\n\nInforme o turno: ");
                String buscaturno = Get.texto();

                System.out.println("\n\n=======RELATÓRIO DE TURMAS POR TURNO=========");
                TurmaController.imprimirLista(TurmaController.getClassByShift(buscaturno));
                break;

            case 4:

                System.out.print("\n\nInforme o turno que deseja excluir a turma: ");
                String excluiturma = Get.texto();

                if (TurmaController.deleteByShift(excluiturma)) {

                    System.out.println("\nTurma excluída com sucesso.");

                } else {

                    System.out.println("\nFalha ao excluir a turma.");

                }
                break;

            case 5:

                System.out.println("\n\nArquivo gerado com sucesso.");
                TurmaController.gerarArquivo();
                break;


        }

    }

}
