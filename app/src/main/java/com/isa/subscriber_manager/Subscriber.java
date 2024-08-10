package com.isa.subscriber_manager;

import java.io.Serializable;

public class Subscriber implements Serializable {
    private int id;
    private String msisdn;
    private String iccid;
    private String chargingType;
    private String simProfileId;
    private String serviceType;
    private String mvnoName;
    private String tariffCode;
    private String tac;
    private String brand;
    private String model;

    // Constructors, getters, and setters
    public Subscriber(int id, String msisdn, String iccid, String chargingType, String simProfileId, String serviceType, String mvnoName, String tariffCode, String tac, String brand, String model) {
        this.id = id;
        this.msisdn = msisdn;
        this.iccid = iccid;
        this.chargingType = chargingType;
        this.simProfileId = simProfileId;
        this.serviceType = serviceType;
        this.mvnoName = mvnoName;
        this.tariffCode = tariffCode;
        this.tac = tac;
        this.brand = brand;
        this.model = model;
    }

    // Getters and setters for each field
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getMsisdn() { return msisdn; }
    public void setMsisdn(String msisdn) { this.msisdn = msisdn; }

    public String getIccid() { return iccid; }
    public void setIccid(String iccid) { this.iccid = iccid; }

    public String getChargingType() { return chargingType; }
    public void setChargingType(String chargingType) { this.chargingType = chargingType; }

    public String getSimProfileId() { return simProfileId; }
    public void setSimProfileId(String simProfileId) { this.simProfileId = simProfileId; }

    public String getServiceType() { return serviceType; }
    public void setServiceType(String serviceType) { this.serviceType = serviceType; }

    public String getMvnoName() { return mvnoName; }
    public void setMvnoName(String mvnoName) { this.mvnoName = mvnoName; }

    public String getTariffCode() { return tariffCode; }
    public void setTariffCode(String tariffCode) { this.tariffCode = tariffCode; }

    public String getTac() { return tac; }
    public void setTac(String tac) { this.tac = tac; }

    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }

    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }
}
