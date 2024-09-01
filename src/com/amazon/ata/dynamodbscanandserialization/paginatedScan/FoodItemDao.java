package com.amazon.ata.dynamodbscanandserialization.paginatedScan;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Provides access to the FoodItems table.
 */
public class FoodItemDao {

    private final DynamoDBMapper mapper;

    /**
     * Allows access to and manipulation of FoodItem objects from the data store.
     * @param mapper access to DynamoDB
     */
    public FoodItemDao(DynamoDBMapper mapper) {
        this.mapper = mapper;
    }

    /**
     * Uses the scanPage() method to scan the FoodItems retrieving only the provided limit number of items.
     * @param exclusiveStartKey the item to start the scan at
     * @param limit the upper limit of items scanned by the scan
     * @return the list of FoodItems that is returned from the database
     */
    public List<FoodItem> scanFoodItemsWithLimit(final FoodItem exclusiveStartKey, final int limit) {
        //TODO: replace the below code
        //return Collections.emptyList();

        // Create a ScanRequest
        ScanRequest scanRequest = new ScanRequest()
                .withTableName("FoodItems") // Ensure this matches your table name
                .withLimit(limit);

        // Check if there's an exclusive start key for pagination
        if (exclusiveStartKey != null) {
            // Set the exclusive start key if provided
            // You need to convert your FoodItem to a key map (implement this method)
            scanRequest.withExclusiveStartKey(exclusiveStartKey.toKeyMap());
        }

        /*// Execute the scan
        ScanResult result = mapper.getAmazonDynamoDB().scan(scanRequest);

        // Convert the result to a list of FoodItem
        List<FoodItem> foodItems = result.getItems().stream()
                .map(item -> mapper.marshallIntoObject(FoodItem.class, item))
                .collect(Collectors.toList());

        return foodItems;*/

        // Execute the scan using DynamoDBMapper
        List<FoodItem> foodItems = mapper.scan(FoodItem.class, new DynamoDBMapperConfig())
                .stream()
                .limit(limit)
                .collect(Collectors.toList());

        return foodItems;

    }
}
