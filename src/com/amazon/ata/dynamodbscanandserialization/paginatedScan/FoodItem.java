package com.amazon.ata.dynamodbscanandserialization.paginatedScan;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;

import java.util.HashMap;
import java.util.Map;

@DynamoDBTable(tableName = "FoodItems")
public class FoodItem {

    private String id;
    private String expirationDate;
    private String foodName;
    private String foodSection;
    private Integer inventoryRemaining;

    @DynamoDBHashKey(attributeName = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @DynamoDBAttribute(attributeName = "expirationDate")
    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    @DynamoDBAttribute(attributeName = "foodName")
    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    @DynamoDBAttribute(attributeName = "foodSection")
    public String getFoodSection() {
        return foodSection;
    }

    public void setFoodSection(String foodSection) {
        this.foodSection = foodSection;
    }

    @DynamoDBAttribute(attributeName = "inventoryRemaining")
    public Integer getInventoryRemaining() {
        return inventoryRemaining;
    }

    public void setInventoryRemaining(Integer inventoryRemaining) {
        this.inventoryRemaining = inventoryRemaining;
    }

    // Method added to convert FoodItem to a key map
    public Map<String, AttributeValue> toKeyMap() {
        Map<String, AttributeValue> keyMap = new HashMap<>();
        keyMap.put("id", new AttributeValue().withS(this.id)); // Assuming 'id' is a String
        return keyMap;
    }
}
