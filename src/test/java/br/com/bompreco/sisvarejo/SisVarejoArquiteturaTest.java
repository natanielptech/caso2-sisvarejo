package br.com.bompreco.sisvarejo;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

@AnalyzeClasses(
        packages = "br.com.bompreco.sisvarejo",
        importOptions = ImportOption.DoNotIncludeTests.class)
class SisVarejoArquiteturaTest {

    @ArchTest
    static final ArchRule modulosNaoAcessamInternosDePrecos =
            noClasses().that().resideOutsideOfPackage("..precos..")
                    .should().dependOnClassesThat().resideInAPackage("..precos.interno..");

    @ArchTest
    static final ArchRule modulosNaoAcessamInternosDeVendas =
            noClasses().that().resideOutsideOfPackage("..vendas..")
                    .should().dependOnClassesThat().resideInAPackage("..vendas.interno..");

    @ArchTest
    static final ArchRule modulosNaoAcessamInternosDeEstoque =
            noClasses().that().resideOutsideOfPackage("..estoque..")
                    .should().dependOnClassesThat().resideInAPackage("..estoque.interno..");

    @ArchTest
    static final ArchRule modulosNaoAcessamInternosDeFiscal =
            noClasses().that().resideOutsideOfPackage("..fiscal..")
                    .should().dependOnClassesThat().resideInAPackage("..fiscal.interno..");

    @ArchTest
    static final ArchRule modulosNaoAcessamInternosDeFidelidade =
            noClasses().that().resideOutsideOfPackage("..fidelidade..")
                    .should().dependOnClassesThat().resideInAPackage("..fidelidade.interno..");

    @ArchTest
    static void dominioDePrecosNaoDependeDeInfraestrutura(JavaClasses classes) {
        noClasses().that().resideInAPackage("..precos.dominio..")
                .should().dependOnClassesThat()
                .resideInAnyPackage("java.sql..", "jakarta.persistence..", "org.springframework..")
                .check(classes);
    }
}
