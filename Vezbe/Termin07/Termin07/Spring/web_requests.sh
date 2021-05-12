#!/bin/bash

URL='http://localhost:8080/api'

curl -X POST -H "Content-Type: application/json" \
    -d '{"name": "Peter", "surname": "Peterson", "email": "peter.peterson@example.com", "password": "123"}' \
    "${URL}/consumer/create"; echo

curl -X POST -H "Content-Type: application/json" \
    -d '{"restaurantId": 1, "consumerId": 1, "items": [{"menuItemId": 1, "menuItemName": "Pizza", "quantity": 1}]}' \
    "${URL}/order/create"; echo

curl -X PUT -H "Content-Type: application/json" \
    "${URL}/kitchen/update/1/ACCEPTED"; echo