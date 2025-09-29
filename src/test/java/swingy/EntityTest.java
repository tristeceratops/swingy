package swingy;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import swingy.model.Coordinate;
import swingy.model.entities.Entity;
import swingy.model.entities.ennemies.Slime;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class EntityTest {

    private static Validator validator;

    @BeforeAll
    static void setupValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void slimeShouldBeValid() {
        Entity slime = new Slime.SlimeBuilder()
                .name("Greeny")
                .coordinate(new Coordinate(1, 2))
                .level(3)
                .build();

        Set<ConstraintViolation<Entity>> violations = validator.validate(slime);
        assertTrue(violations.isEmpty(), "Slime should be valid");
    }

    @Test
    void slimeWithoutNameShouldFail() {
        Entity slime = new Slime.SlimeBuilder()
                .name("")  // invalid
                .coordinate(new Coordinate(1, 2))
                .level(3)
                .build();

        Set<ConstraintViolation<Entity>> violations = validator.validate(slime);
        assertFalse(violations.isEmpty(), "Slime without name should be invalid");

        for (ConstraintViolation<Entity> violation : violations) {
            System.out.println(violation.getPropertyPath() + " -> " + violation.getMessage());
        }
    }

    @Test
    void slimeWithoutCoordinateShouldFail() {
        Entity slime = new Slime.SlimeBuilder()
                .name("Slimy")
                .coordinate(null) // invalid
                .level(1)
                .build();

        Set<ConstraintViolation<Entity>> violations = validator.validate(slime);
        assertFalse(violations.isEmpty(), "Slime without coordinate should be invalid");
    }
}
