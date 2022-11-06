package org.api.excel.model;

import org.apache.logging.log4j.util.Strings;
import org.api.excel.core.annotations.Page;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.annotation.Annotation;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("java:S2699")
class SheetWriterModelTest {


    private static Page getPage(int number, String Personnes, int rowNumber) {
        return new Page() {

            @Override
            public Class<? extends Annotation> annotationType() {
                return Page.class;
            }

            @Override
            public int number() {
                return number;
            }

            @Override
            public String name() {
                return Personnes;
            }

            @Override
            public int rowNumber() {
                return rowNumber;
            }
        };
    }

    /**
     * Methods under test:
     * Exception
     * <ul>
     *   <li>{@link SheetWriterModel#aNew#pages(Page[])#create()}
     *   <li>{@link SheetWriterModel#getName()}
     *   <li>{@link SheetWriterModel#getRowNumber()}
     * </ul>
     */
    @Test
    void build_then_nullPages_when_Exception() {

        Page[] pages = null;
        Assertions.assertThatThrownBy(SheetWriterModel.aNew().pages(pages)::create)
                .isInstanceOf(NullPointerException.class)
                .hasMessage("the Collection cannot be null");
    }

    /**
     * Methods under test:
     * Exception
     * <ul>
     *   <li>{@link SheetWriterModel#aNew#pages(Page[])#create()}
     *   <li>{@link SheetWriterModel#getName()}
     *   <li>{@link SheetWriterModel#getRowNumber()}
     * </ul>
     */
    @Test
    void build_then_MultiPages_when_Exception() {
        Page page = getPage(0, "Personnes", 12);
        Page page1 = getPage(3, Strings.EMPTY, 45);
        Page[] pages = new Page[]{page, page1};
        Assertions.assertThatThrownBy(SheetWriterModel.aNew().pages(pages)::create)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Page array size not equal 1");
    }

    /**
     * Methods under test:
     * Sheet name is name propertie
     * <ul>
     *   <li>{@link SheetWriterModel#aNew#pages(Page[])#create()}
     *   <li>{@link SheetWriterModel#getName()}
     *   <li>{@link SheetWriterModel#getRowNumber()}
     * </ul>
     */
    @Test
    void build_Lorsque_SheetName_Attend_SheetName() {
        //Conditions préalables (given)
        Page page = getPage(0, "Personnes", 12);

        //Une action se produit (when)
        SheetWriterModel actual = SheetWriterModel.aNew().pages(new Page[]{page}).create();
        assertThat(actual.getName()).hasToString("Personnes");
        assertThat(actual.getRowNumber()).isEqualTo(12);
    }

    /**
     * Methods under test:
     * Generate sheet name
     *
     * <ul>
     *   <li>{@link SheetWriterModel#aNew#pages(Page[])#create()}
     *   <li>{@link SheetWriterModel#getName()}
     *   <li>{@link SheetWriterModel#getRowNumber()}
     * </ul>
     */
    @Test
    void build_Lorsque_NoSheetName_Attend_Sheet3() {
        //Conditions préalables (given)
        Page page = getPage(3, Strings.EMPTY, 45);
        //Une action se produit (when)
        SheetWriterModel actual = SheetWriterModel.aNew().pages(new Page[]{page}).create();
        assertThat(actual.getName()).hasToString("Sheet 3");
        assertThat(actual.getRowNumber()).isEqualTo(45);
    }
}

