package com.example.pace;
import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.ArrayList;
public class CalendarData implements Serializable {
    private int month;
    private int day;
    private int year;
    private ArrayList<ClientModule> clientModuleList;

    private float percentageCalculation;

    public CalendarData(ArrayList<ClientModule> clientModuleList, int month, int day, int year) {
        this.clientModuleList = clientModuleList;
        this.month = month;
        this.day = day;
        this.year = year;
    }

    // Copy constructor
    public CalendarData(CalendarData copyData) {
        this.clientModuleList = new ArrayList<>(copyData.getClientModuleList());
        this.month = copyData.getMonth();
        this.day = copyData.getDay();
        this.year = copyData.getYear();
    }

    public ArrayList<ClientModule> getClientModuleList() {
        return clientModuleList;
    }

    public void setClientModuleList(ArrayList<ClientModule> clientModuleList) {
        this.clientModuleList = clientModuleList;
    }

    public void AddClientModule(ClientModule clientModule) {
        this.clientModuleList.add(clientModule);
    }

    public ClientModule getClientModule(int index) {
        return this.clientModuleList.get(index);
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public float CalculateClientExpenditure() {
        float total = 0.0f;
        for (int i = 0; i < this.clientModuleList.size(); ++i) {
            total = total + this.clientModuleList.get(i).CalculateExpenditure();
        }
        return total;
    }

    public void setPercentageCalculation(float percentageCalculation) { this.percentageCalculation = percentageCalculation; }

    public float getPercentageCalculation() { return this.percentageCalculation; }

}