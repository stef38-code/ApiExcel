package org.api.excel.sample;

import org.api.excel.annotations.ExcelCell;
import org.api.excel.annotations.ExcelSheet;

@ExcelSheet
public class FormatData {
    @ExcelCell
    private double numeric;
    @ExcelCell(number = 1)
    private String string;
    @ExcelCell(number = 2)
    private String error;
    @ExcelCell(number = 3)
    private String blank;
    @ExcelCell(number = 4)
    private String formula;
    @ExcelCell(number = 5)
    private boolean bool;

    public double getNumeric() {
        return numeric;
    }

    public void setNumeric(double numeric) {
        this.numeric = numeric;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getBlank() {
        return blank;
    }

    public void setBlank(String blank) {
        this.blank = blank;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public boolean isBool() {
        return bool;
    }

    public void setBool(boolean bool) {
        this.bool = bool;
    }
}
