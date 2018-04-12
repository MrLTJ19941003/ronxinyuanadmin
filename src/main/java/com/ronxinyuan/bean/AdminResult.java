package com.ronxinyuan.bean;

/**
 * Created by 13045 on 2018/3/26.
 */
public class AdminResult {
    private int results;
    private Object rows;
    private boolean hasError;
    private String error;

    public int getResults() {
        return results;
    }

    public void setResults(int results) {
        this.results = results;
    }

    public Object getRows() {
        return rows;
    }

    public void setRows(Object rows) {
        this.rows = rows;
    }

    public boolean isHasError() {
        return hasError;
    }

    public void setHasError(boolean hasError) {
        this.hasError = hasError;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "AdminResult{" +
                "results=" + results +
                ", rows=" + rows +
                ", hasError=" + hasError +
                ", error='" + error + '\'' +
                '}';
    }

    public AdminResult() {
    }

    public AdminResult(int results, Object rows, boolean hasError, String error) {
        this.results = results;
        this.rows = rows;
        this.hasError = hasError;
        this.error = error;
    }

    public AdminResult(boolean hasError, String error) {
        this.results = 0;
        this.rows = null;
        this.hasError = hasError;
        this.error = error;
    }
}
