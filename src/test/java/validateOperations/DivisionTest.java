package validateOperations;

import com.company.model.PolyCalcModel;
import com.company.model.Polynomial;
import com.company.model.operations.Division;
import com.company.utils.Regex;
import com.company.utils.StringToPolynomConverter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class DivisionTest implements AbstractValidate {

    private final PolyCalcModel polyCalcModel = new PolyCalcModel();

    String string = "x^2 +x";
    String input1 = "1.5x^3+x";
    String input2 = "-x";
    String input3 = "x^2";
    String input4 = "-2x^2";
    String input5 = "-2x";
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
        Polynomial polynomial2 = StringToPolynomConverter.regEx(Regex.getRegex(), input1);
        polyCalcModel.setResult(new Polynomial(new ArrayList<>(5)));
        String str = polyCalcModel.division(polynomial1, polynomial2);
        Assertions.assertEquals(str, "0   Rest:(X^2+X/1.50X^3+X)");
    }

    @Test
    public void test2() {
        Polynomial polynomial1 = StringToPolynomConverter.regEx(Regex.getRegex(), input2);
        Polynomial polynomial2 = StringToPolynomConverter.regEx(Regex.getRegex(), input3);
        polyCalcModel.setResult(new Polynomial(new ArrayList<>(5)));
        String str = polyCalcModel.division(polynomial1, polynomial2);
        Assertions.assertEquals(str, "0   Rest:(-X/X^2)");
    }

    @Test
    public void test3() {
        Polynomial polynomial1 = StringToPolynomConverter.regEx(Regex.getRegex(), input6);
        Polynomial polynomial2 = StringToPolynomConverter.regEx(Regex.getRegex(), input8);
        polyCalcModel.setResult(new Polynomial(new ArrayList<>(5)));
        String str = polyCalcModel.division(polynomial1, polynomial2);
        Assertions.assertEquals(str, "0   Rest:(-2/X^3-X^4)");
    }

    @Test
    public void test4() {
        Polynomial polynomial1 = StringToPolynomConverter.regEx(Regex.getRegex(), string);
        Polynomial polynomial2 = StringToPolynomConverter.regEx(Regex.getRegex(), input2);
        polyCalcModel.setResult(new Polynomial(new ArrayList<>(5)));
        String str = polyCalcModel.division(polynomial1, polynomial2);
        Assertions.assertEquals(str, "-X-1");
    }
}
