package com.example.libback.dto;

import java.math.BigDecimal;

public class ReportMetricsDto {
    private BigDecimal finesCollectedMtd;
    private long newRegistrationsCount;
    private double systemFulfillmentRate;
    
    // Percentages for cluster chart bars
    private double csTechPercentage;
    private double fictionPercentage;
    private double mathPhysicsPercentage;
    
    // Inventory state metric percentages
    private double inLibraryPercentage;
    private double activeLoanPercentage;
    private double overduePercentage;

    // --- GETTERS AND SETTERS ---

    public BigDecimal getFinesCollectedMtd() {
        return finesCollectedMtd;
    }

    public void setFinesCollectedMtd(BigDecimal finesCollectedMtd) {
        this.finesCollectedMtd = finesCollectedMtd;
    }

    public long getNewRegistrationsCount() {
        return newRegistrationsCount;
    }

    // THIS IS THE MISSING LINK COMPILING ERRORED LINES
    public void setNewRegistrationsCount(long newRegistrationsCount) {
        this.newRegistrationsCount = newRegistrationsCount;
    }

    public double getSystemFulfillmentRate() {
        return systemFulfillmentRate;
    }

    public void setSystemFulfillmentRate(double systemFulfillmentRate) {
        this.systemFulfillmentRate = systemFulfillmentRate;
    }

    public double getCsTechPercentage() {
        return csTechPercentage;
    }

    public void setCsTechPercentage(double csTechPercentage) {
        this.csTechPercentage = csTechPercentage;
    }

    public double getFictionPercentage() {
        return fictionPercentage;
    }

    public void setFictionPercentage(double fictionPercentage) {
        this.fictionPercentage = fictionPercentage;
    }

    public double getMathPhysicsPercentage() {
        return mathPhysicsPercentage;
    }

    public void setMathPhysicsPercentage(double mathPhysicsPercentage) {
        this.mathPhysicsPercentage = mathPhysicsPercentage;
    }

    public double getInLibraryPercentage() {
        return inLibraryPercentage;
    }

    public void setInLibraryPercentage(double inLibraryPercentage) {
        this.inLibraryPercentage = inLibraryPercentage;
    }

    public double getActiveLoanPercentage() {
        return activeLoanPercentage;
    }

    public void setActiveLoanPercentage(double activeLoanPercentage) {
        this.activeLoanPercentage = activeLoanPercentage;
    }

    public double getOverduePercentage() {
        return overduePercentage;
    }

    public void setOverduePercentage(double overduePercentage) {
        this.overduePercentage = overduePercentage;
    }
}