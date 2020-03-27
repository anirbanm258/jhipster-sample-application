package co.za.bcx;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("co.za.bcx");

        noClasses()
            .that()
            .resideInAnyPackage("co.za.bcx.service..")
            .or()
            .resideInAnyPackage("co.za.bcx.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..co.za.bcx.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
