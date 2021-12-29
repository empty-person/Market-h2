# Market
java -jar [Market-0.0.1-SNAPSHOT.jar](https://github.com/empty-person/Market-h2/blob/master/target/Market-0.0.1-SNAPSHOT.jar)


## Endpoint URLs for each business scenario with needed body
### Guide
{} = body with example values where :
```diff
+ green line means value SHOULD BE INCLUDED IN BODY REQUEST
# gray line means value ALLOWED TO NOT BEING INCLUDED
```

## 

### Create new item(product) 
```diff
  Post request
  http://localhost:8080/item {
+   "name":"itemName12",
+   "price":22.2
    }
```


### Get item(product) by name  [example](https://i.ibb.co/WFt8CdQ/image.png)
```diff
  Get requst
  http://localhost:8080/item {
+   item name
    }
```

### Get all items [example](https://i.ibb.co/jLkLgtC/image.png)
```diff
  Get request
  http://localhost:8080/item/all
```

### Create new user(basket holder) [example](https://i.ibb.co/NT3bpPW/image.png)
```diff
  Post request
  http://localhost:8080/user {
+   "username":"username1",
+   "password":"password",
+   "money":222.22
    }
```

### Get user by id [example](https://i.ibb.co/89QNm2y/image.png)
```diff
  Get request
  http://localhost:8080/user/2
```

### Get all users [example](https://i.ibb.co/NsVxXPp/image.png)
```diff
  Get request
  http://localhost:8080/user/all
```

### Add item to basket [example](https://i.ibb.co/vqQmM6W/image.png)
```diff
  Post request
  http://localhost:8080/basket/add {
+   "itemName":"itemName",
#   "userName":"userName1",                                (default value = username1)
#   "quantity":2                                           (default value = 1)
    }
```

### Remove item from basket [example](https://i.ibb.co/r76hnB8/image.png)
```diff
  Post request
  http://localhost:8080/basket/remove {
+   "itemName":"itemName12",
#   "quantity":3,                                          (default value = 1)
#   "userName":"dssfg"                                     (default value = username1)
    }
```

### Get basket by username [example](https://i.ibb.co/zbh7FS4/image.png)
```diff
  Get request
  http://localhost:8080/basket{
+  "userName":"userName1"
  }
```

### Complete order for user [example](https://i.ibb.co/Hq8Kgbb/image.png)
```diff
  Post request
  http://localhost:8080/order/complete {
#   "userName":"username21"                                     (default value = username1)
    }
```

### Retrieve order history for user [example](https://i.ibb.co/RzpZPvB/image.png)
```diff
  Post request
  http://localhost:8080/order/complete {
#   "userName":"username21"                                     (default value = username1)
    }
```







