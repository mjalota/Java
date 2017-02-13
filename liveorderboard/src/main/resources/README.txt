Assumption:
1. userId is equivalent to orderId which would be unique and used as key in cache.
2. Assuming multiple users are accessing this library concurrently, so used ConcurrentHashMap. 
3. cancel order is only supplied with UserId/OrderId. else i would have created a TreeMap with Key as price. 
   which can easily support the delete operation and getSummary operation would be very light weight. 
