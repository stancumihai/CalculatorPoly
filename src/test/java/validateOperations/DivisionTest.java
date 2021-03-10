package validateOperations;

import com.company.model.Polynomial;
import com.company.model.operations.Derivation;
import com.company.model.operations.Division;
import com.company.model.operations.Subtraction;
import com.company.utils.PolynomDisplay;
import com.company.utils.Regex;
import com.company.utils.StringToPolynomConverter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class DivisionTest implements AbstractValidate {

    String string = "x^2 +x";
    String input1 = "1.5x^3+x";
    String input2 = "-x";
    String input3 = "x^2";
    String input5 = "-2x";
    String input4 = "-2x^2";
    String input6 = "-2";
    String input7 = "2x^-2";
    String input8 = "x^3-x^4";
    String input9 = "1";
    String input10 = "-1";
    String input11 = "2";
    String input12 = "x^-1+x^-5";

    @Override
    @Test
    public void validate() {

        test1();
        test2();
        test3();
        test4();
    }

    @Test
    public void test1() {
        Polynomial polynomial1 = StringToPolynomConverter.regEx(Regex.getRegex(), string);
        Polynomial polynomial2 = StringToPolynomConverter.regEx(Regex.getRegex(), input2);
        Polynomial result = new Polynomial(new ArrayList<>(5));
        Division division = new Division(result);
        division.calculate(polynomial1, polynomial2);
        String str = PolynomDisplay.constructFromStringToPolynom(result);
        Assertions.assertEquals(str, "X^2");
    }

    @Test
    public void test2() {
        Polynomial polynomial1 = StringToPolynomConverter.regEx(Regex.getRegex(), string);
        Polynomial polynomial2 = StringToPolynomConverter.regEx(Regex.getRegex(), input2);
        Polynomial result = new Polynomial(new ArrayList<>(5));
        Division division = new Division(result);
        division.calculate(polynomial1, polynomial2);
        String str = PolynomDisplay.constructFromStringToPolynom(result);
        Assertions.assertEquals(str, "X^2");
    }

    @Test
    public void test3() {
        Polynomial polynomial1 = StringToPolynomConverter.regEx(Regex.getRegex(), string);
        Polynomial polynomial2 = StringToPolynomConverter.regEx(Regex.getRegex(), input2);
        Polynomial result = new Polynomial(new ArrayList<>(5));
        Division division = new Division(result);
        division.calculate(polynomial1, polynomial2);
        String str = PolynomDisplay.constructFromStringToPolynom(result);
        Assertions.assertEquals(str, "X^2");
    }

    @Test
    public void test4() {
        Polynomial polynomial1 = StringToPolynomConverter.regEx(Regex.getRegex(), string);
        Polynomial polynomial2 = StringToPolynomConverter.regEx(Regex.getRegex(), input2);
        Polynomial result = new Polynomial(new ArrayList<>(5));
        Division division = new Division(result);
        division.calculate(polynomial1, polynomial2);
        String str = PolynomDisplay.constructFromStringToPolynom(result);
        Assertions.assertEquals(str, "X^2");
    }
}
