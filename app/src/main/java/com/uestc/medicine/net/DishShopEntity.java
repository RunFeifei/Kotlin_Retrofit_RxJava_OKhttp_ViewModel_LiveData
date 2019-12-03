package com.uestc.medicine.net;

import java.util.List;

/**
 * Created by PengFeifei on 2019-11-29.
 */
public class DishShopEntity {

    private int group;
    private int index;
    private int kTouchInterval;
    private int kTouchTriggerCount;
    private boolean lastIsAdd;
    private int lastTouchTs;
    private int showType;
    private boolean isEndOfType;
    private String textOFtype;
    private int touchCount;
    private int isMulti;
    private boolean isTempDish;
    private boolean mustSelectSaleOut;
    private String name;
    private String aliasName;
    private String barcode;
    private long brandDishId;
    private String brandDishUuid;
    private String clearStatus;
    private String dishCode;
    private double dishIncreaseUnit;
    private String dishNameIndex;
    private long dishTypeId;
    private String enabledFlag;
    private String hasStandard;
    private String isChangePrice;
    private String isDiscountAll;
    private String isSendOutside;
    private String isSingle;
    private double marketPrice;
    private int maxNum;
    private int minNum;
    private double residueTotal;
    private double residueTotalWechat;
    private double saleTotal;
    private double saleTotalWechat;
    private String saleType;
    private String scene;
    private int shopIdenty;
    private String skuKey;
    private int sort;
    private double stepNum;
    private String type;
    private long unitId;
    private String unvalidTime;
    private String uuid;
    private String validTime;
    private int brandIdenty;
    private long serverUpdateTime;
    private String statusFlag;
    private long id;
    private List<?> standardList;

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getKTouchInterval() {
        return kTouchInterval;
    }

    public void setKTouchInterval(int kTouchInterval) {
        this.kTouchInterval = kTouchInterval;
    }

    public int getKTouchTriggerCount() {
        return kTouchTriggerCount;
    }

    public void setKTouchTriggerCount(int kTouchTriggerCount) {
        this.kTouchTriggerCount = kTouchTriggerCount;
    }

    public boolean isLastIsAdd() {
        return lastIsAdd;
    }

    public void setLastIsAdd(boolean lastIsAdd) {
        this.lastIsAdd = lastIsAdd;
    }

    public int getLastTouchTs() {
        return lastTouchTs;
    }

    public void setLastTouchTs(int lastTouchTs) {
        this.lastTouchTs = lastTouchTs;
    }

    public int getShowType() {
        return showType;
    }

    public boolean isHeader() {
        return getShowType()!=0;
    }

    public boolean getIsEndOfType() {
        return isEndOfType;
    }

    public void setIsEndOfType(boolean isEndOfType) {
        this.isEndOfType = isEndOfType;
    }

    public void setShowType(int showType) {
        this.showType = showType;
    }

    public int getTouchCount() {
        return touchCount;
    }

    public void setTouchCount(int touchCount) {
        this.touchCount = touchCount;
    }

    public int getIsMulti() {
        return isMulti;
    }

    public void setIsMulti(int isMulti) {
        this.isMulti = isMulti;
    }

    public boolean isIsTempDish() {
        return isTempDish;
    }

    public void setIsTempDish(boolean isTempDish) {
        this.isTempDish = isTempDish;
    }

    public boolean isMustSelectSaleOut() {
        return mustSelectSaleOut;
    }

    public void setMustSelectSaleOut(boolean mustSelectSaleOut) {
        this.mustSelectSaleOut = mustSelectSaleOut;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAliasName() {
        return aliasName;
    }

    public void setAliasName(String aliasName) {
        this.aliasName = aliasName;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public long getBrandDishId() {
        return brandDishId;
    }

    public void setBrandDishId(long brandDishId) {
        this.brandDishId = brandDishId;
    }

    public String getBrandDishUuid() {
        return brandDishUuid;
    }

    public void setBrandDishUuid(String brandDishUuid) {
        this.brandDishUuid = brandDishUuid;
    }

    public String getClearStatus() {
        return clearStatus;
    }

    public void setClearStatus(String clearStatus) {
        this.clearStatus = clearStatus;
    }

    public String getDishCode() {
        return dishCode;
    }

    public void setDishCode(String dishCode) {
        this.dishCode = dishCode;
    }

    public double getDishIncreaseUnit() {
        return dishIncreaseUnit;
    }

    public void setDishIncreaseUnit(double dishIncreaseUnit) {
        this.dishIncreaseUnit = dishIncreaseUnit;
    }

    public String getDishNameIndex() {
        return dishNameIndex;
    }

    public void setDishNameIndex(String dishNameIndex) {
        this.dishNameIndex = dishNameIndex;
    }

    public long getDishTypeId() {
        return dishTypeId;
    }

    public void setDishTypeId(long dishTypeId) {
        this.dishTypeId = dishTypeId;
    }

    public String getEnabledFlag() {
        return enabledFlag;
    }

    public void setEnabledFlag(String enabledFlag) {
        this.enabledFlag = enabledFlag;
    }

    public String getHasStandard() {
        return hasStandard;
    }

    public void setHasStandard(String hasStandard) {
        this.hasStandard = hasStandard;
    }

    public String getIsChangePrice() {
        return isChangePrice;
    }

    public void setIsChangePrice(String isChangePrice) {
        this.isChangePrice = isChangePrice;
    }

    public String getIsDiscountAll() {
        return isDiscountAll;
    }

    public void setIsDiscountAll(String isDiscountAll) {
        this.isDiscountAll = isDiscountAll;
    }

    public String getIsSendOutside() {
        return isSendOutside;
    }

    public void setIsSendOutside(String isSendOutside) {
        this.isSendOutside = isSendOutside;
    }

    public String getIsSingle() {
        return isSingle;
    }

    public void setIsSingle(String isSingle) {
        this.isSingle = isSingle;
    }

    public double getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(double marketPrice) {
        this.marketPrice = marketPrice;
    }

    public int getMaxNum() {
        return maxNum;
    }

    public void setMaxNum(int maxNum) {
        this.maxNum = maxNum;
    }

    public int getMinNum() {
        return minNum;
    }

    public void setMinNum(int minNum) {
        this.minNum = minNum;
    }

    public double getResidueTotal() {
        return residueTotal;
    }

    public void setResidueTotal(double residueTotal) {
        this.residueTotal = residueTotal;
    }

    public double getResidueTotalWechat() {
        return residueTotalWechat;
    }

    public void setResidueTotalWechat(double residueTotalWechat) {
        this.residueTotalWechat = residueTotalWechat;
    }

    public double getSaleTotal() {
        return saleTotal;
    }

    public void setSaleTotal(double saleTotal) {
        this.saleTotal = saleTotal;
    }

    public double getSaleTotalWechat() {
        return saleTotalWechat;
    }

    public void setSaleTotalWechat(double saleTotalWechat) {
        this.saleTotalWechat = saleTotalWechat;
    }

    public String getSaleType() {
        return saleType;
    }

    public void setSaleType(String saleType) {
        this.saleType = saleType;
    }

    public String getScene() {
        return scene;
    }

    public void setScene(String scene) {
        this.scene = scene;
    }

    public int getShopIdenty() {
        return shopIdenty;
    }

    public void setShopIdenty(int shopIdenty) {
        this.shopIdenty = shopIdenty;
    }

    public String getSkuKey() {
        return skuKey;
    }

    public void setSkuKey(String skuKey) {
        this.skuKey = skuKey;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public double getStepNum() {
        return stepNum;
    }

    public void setStepNum(double stepNum) {
        this.stepNum = stepNum;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getUnitId() {
        return unitId;
    }

    public void setUnitId(long unitId) {
        this.unitId = unitId;
    }

    public String getUnvalidTime() {
        return unvalidTime;
    }

    public void setUnvalidTime(String unvalidTime) {
        this.unvalidTime = unvalidTime;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getValidTime() {
        return validTime;
    }

    public void setValidTime(String validTime) {
        this.validTime = validTime;
    }

    public int getBrandIdenty() {
        return brandIdenty;
    }

    public void setBrandIdenty(int brandIdenty) {
        this.brandIdenty = brandIdenty;
    }

    public long getServerUpdateTime() {
        return serverUpdateTime;
    }

    public void setServerUpdateTime(long serverUpdateTime) {
        this.serverUpdateTime = serverUpdateTime;
    }

    public String getStatusFlag() {
        return statusFlag;
    }

    public void setStatusFlag(String statusFlag) {
        this.statusFlag = statusFlag;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<?> getStandardList() {
        return standardList;
    }

    public void setStandardList(List<?> standardList) {
        this.standardList = standardList;
    }

    public String getTextOFtype() {
        return textOFtype;
    }

    public void setTextOFtype(String textOFtype) {
        this.textOFtype = textOFtype;
    }
}
