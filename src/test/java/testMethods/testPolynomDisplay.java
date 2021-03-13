
package testMethods;

import com.company.model.Monomial;
import com.company.model.Polynomial;
import com.company.utils.PolynomDisplay;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import testOperations.AbstractValidate;

import java.util.ArrayList;

public class testPolynomDisplay implements AbstractValidate {

    @Override
    public void validate() {
        test1();
        test2();
        test3();
        test4();
    }

    @Test
    public void test1() {

        Polynomial polynomial = new Polynomial(new ArrayList<>(100));
        Monomial monomial1 = new Monomial(2, 3);
        Monomial monomial2 = new Monomial(2, 3);
        polynomial.getPolynom().add(monomial1);
        polynomial.getPolynom().add(monomial2);

        String res = PolynomDisplay.constructFromStringToPolynom(polynomial);
        Assertions.assertEquals(res, "2X^3+2X^3");
    }

    @Test
    public void test2() {
        Polynomial polynomial = new Polynomial(new ArrayList<>(100));
        Monomial monomial1 = new Monomial(1, 3);
        Monomial monomial2 = new Monomial(4, 5);
        polynomial.getPolynom().add(monomial1);
        polynomial.getPolynom().add(monomial2);

        String res = PolynomDisplay.constructFromStringToPolynom(polynomial);
        Assertions.assertEquals(res, "X^3+4X^5");
    }

    @Test
    public void test3() {
        Polynomial polynomial = new Polynomial(new ArrayList<>(100));
        Monomial monomial1 = new Monomial(6, 3);
        Monomial monomial2 = new Monomial(2, 8);
        polynomial.getPolynom().add(monomial1);
        polynomial.getPolynom().add(monomial2);

        String res = PolynomDisplay.constructFromStringToPolynom(polynomial);
        Assertions.assertEquals(res, "6X^3+2X^8");
    }

    @Test
    public void test4() {
        Polynomial polynomial = new Polynomial(new ArrayList<>(100));
        Monomial monomial1 = new Monomial(1, 7);
        Monomial monomial2 = new Monomial(2, 3);
        polynomial.getPolynom().add(monomial1);
        polynomial.getPolynom().add(monomial2);

        String res = PolynomDisplay.constructFromStringToPolynom(polynomial);
        Assertions.assertEquals(res, "X^7+2X^3");
    }
}
